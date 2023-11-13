package me.maanex.schmude.content.tickhooks;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.maanex.schmude.Main;
import me.maanex.schmude.lib.ArrayUtils;

public class Enderdragon implements Listener {

	final int DRAGON_HEALTH = 1000;
	int phase = 0;

	int shootupChance = 100000;
	int ticksSinceLightning = 0;
	int doDisappear = 0;
  
  public void schedule() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this::tick, 5, 5);
	}
	
	public void tick() {
		if (Bukkit.getOnlinePlayers().size() == 0) return;

		List<Player> playersInEnd = Bukkit.getOnlinePlayers().stream()
			.filter(p -> p.getLocation().getWorld().getEnvironment().equals(Environment.THE_END))
			.collect(Collectors.toList());
		if (playersInEnd.size() == 0) return;

		World world = playersInEnd.get(0).getWorld();
		DragonBattle battle = world.getEnderDragonBattle();
		if (battle == null) return;
		EnderDragon dragon = battle.getEnderDragon();
		if (dragon == null) return;

		int phase = getPhase(dragon, playersInEnd);
		battle.getBossBar().setStyle(BarStyle.SEGMENTED_6);

		createAmbiance(world, playersInEnd, dragon, phase);
		modPlayers(world, playersInEnd, phase);
		upgradeDragon(dragon, phase);
	}

	@EventHandler
	public void onDragonSpawn(EntitySpawnEvent e) {
		if (e.getEntityType().equals(EntityType.ENDER_DRAGON)) {
			EnderDragon dragon = (EnderDragon) e.getEntity();
			dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(DRAGON_HEALTH);
			dragon.setHealth(DRAGON_HEALTH);
		}
	}

	@EventHandler
	public void onLightning(LightningStrikeEvent e) {
		if (!e.getWorld().getEnvironment().equals(Environment.THE_END))
			return;

		Location loc = e.getLightning().getLocation().clone().add(0, -2, 0);
		if (!loc.getBlock().isSolid())
			return;

		if (loc.getNearbyEntities(3, 3, 3).stream().filter(n -> n.getType().equals(EntityType.PLAYER)).count() > 0)
			return;

		TNTPrimed ent = (TNTPrimed) e.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
		ent.setFuseTicks(0);
		ent.setSilent(true);
		ent.setYield(14);

		for (Player p : e.getWorld().getPlayers())
			p.hideEntity(Main.instance, ent);
	}

	//

	private int getPhase(EnderDragon dragon, List<Player> playersInEnd) {
		int realPhase = 6 - (int) Math.floor(dragon.getHealth() / DRAGON_HEALTH * 6);
		if (realPhase > phase) {
			phase = realPhase;
			onNextPhase(dragon, playersInEnd, phase);
		}

		if (dragon.getHealth() == DRAGON_HEALTH)
			phase = 1;
		return phase;
	}

	public void onNextPhase(EnderDragon dragon, List<Player> playersInEnd, int newPhase) {
		if (newPhase < 6) {
			doDisappear = 4 * 10; // 10s
		}

		if (newPhase == 5) {
			dragon.getWorld().getEntitiesByClass(Enderman.class)
				.stream()
				.forEach(e -> {
					e.setTarget(ArrayUtils.random(playersInEnd));
				});
		}
	}

	private void upgradeDragon(EnderDragon dragon, int phase) {
		dragon.getBossBar().setColor(BarColor.values()[phase]);

		if (doDisappear > 0) {
			doDisappear--;
			if (dragon.getY() < 130) {
				dragon.setVelocity(dragon.getVelocity().setY(1));
			}
		}
	}

	private void createAmbiance(World world, List<Player> playersInEnd, EnderDragon dragon, int phase) {
		if (doThunder(world)) {
			ticksSinceLightning = 0;
			Location pos = ArrayUtils.random(playersInEnd).getLocation();
			if (phase <= 3) {
				pos = pos.clone().add(0, 200, 0);
				world.strikeLightningEffect(pos);
			} else {
				double range = Math.max(0.1, Math.random() * Math.random()) * 50;
				pos = pos.clone().add(
					Math.random() * range*2 - range,
					200,
					Math.random() * range - range
				);
				pos = world.getHighestBlockAt(pos).getLocation();
				world.strikeLightning(pos);
			}
		}

		if (phase >= 5) {
			shootupChance++;
			if (shootupChance > 200 && Math.random() * 5000 < shootupChance) {
				int duration = (int) (5 + Math.floor(Math.random() * 10)) * 20;
				for (Player p : playersInEnd) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 1, true, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, duration, 1, true, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, duration, 1, true, false));
					p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, 1, 1);
					Bukkit.getScheduler().scheduleSyncDelayedTask(
						Main.instance,
						() -> p.removePotionEffect(PotionEffectType.NIGHT_VISION),
						duration
					);

					for (int i = 0; i < (int) (Math.sqrt(playersInEnd.size()) * 30); i++) {
						Location loc = p.getLocation().clone().add(
							Math.random() * 64 - 32,
							Math.random() * 140 + 70,
							Math.random() * 64 - 32
						);
						DragonFireball e = (DragonFireball) world.spawnEntity(loc, EntityType.DRAGON_FIREBALL);
						e.setDirection(new Vector(0, -0.1, 0));
					}
				}

				shootupChance = 0;
			}

			playersInEnd.forEach(p -> p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 15, 0, true, false)));
		}

		if (phase == 6) {
			circleEffect(world, playersInEnd, dragon.getDragonBattle());

			world.getEntitiesByClass(Enderman.class)
				.stream()
				.filter(e -> (Math.random() < 0.002))
				.forEach(e -> {
					e.setTarget(ArrayUtils.random(playersInEnd));
				});

			playersInEnd.forEach(p -> p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 15, 0, true, false)));
		}
	}

	private boolean doThunder(World world) {
		ticksSinceLightning++;
		if (ticksSinceLightning <= 1) return false;

		double x = (world.getGameTime() % 20000) / 80;
		double y = Math.sin(x) + Math.sin(x/3 + 1.2) + Math.sin(x/13+2);
		return Math.random() < y/3;
	}

	private void modPlayers(World world, List<Player> playersInEnd, int phase) {
		for (Player p : playersInEnd) {
			if (phase == 4 && Math.random() < 0.005) {
				p.playEffect(EntityEffect.TELEPORT_ENDER);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*5, 2, true, false));
			}
		}
	}

	private void circleEffect(World world, List<Player> playersInEnd, DragonBattle battle) {
		int radius = 60;

		for (int i = 0; i < 6; i++) {
			double rad = Math.random() * 360;
			int x = (int) (Math.sin(rad) * radius);
			int y = (int) (Math.cos(rad) * radius);

			Location loc = new Location(world, x, battle.getEndPortalLocation().getY() - 15 + (int) (Math.random() * 40), y);
			playersInEnd.forEach(p -> p.sendBlockChange(loc, Material.END_GATEWAY.createBlockData()));

			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
				BlockData dat = world.getBlockData(loc);
				playersInEnd.forEach(p -> p.sendBlockChange(loc, dat));
			}, 120);
		}
	}
}
