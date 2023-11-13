package me.maanex.schmude.features.mobgriefing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import me.maanex.schmude.Main;

public class CreeperExplosions implements Listener {

	List<FallingBlock> pushed = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
    e.setCancelled(true);

    for (Entity en : e.getEntity().getNearbyEntities(e.getYield() / 3, e.getYield() / 3, e.getYield() / 3)) {
      try {
        if (e instanceof Projectile) {
          Entity damager = (Entity) ((Projectile) e.getEntity()).getShooter();
          ((LivingEntity) en).damage(en.getLocation().distance(e.getLocation()), damager);
        } else((LivingEntity) en).damage(en.getLocation().distance(e.getLocation()));
      } catch (Exception e2) {}
    }

    e.setYield(0);

    for (final Block b : e.blockList()) {
      for (Player p : Bukkit.getOnlinePlayers())
        p.sendBlockChange(b.getLocation(), b.getType().isSolid() ? Material.BARRIER : Material.AIR, (byte) 0);
      b.getWorld().spawnParticle(Particle.BLOCK_CRACK, b.getLocation(), 5, b.getBlockData());

      Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {

        @Override
        public void run() {
          for (Player p : Bukkit.getOnlinePlayers())
            p.sendBlockChange(b.getLocation(), b.getBlockData());
          b.getWorld().spawnParticle(Particle.BLOCK_CRACK, b.getLocation(), 5, b.getBlockData());
        }
      }, new Random().nextInt(40) + 60);

      if (b.getType().isSolid() && new Random().nextInt(e.blockList().size() < 20 ? 1 : 2) == 0) {
        Location l = b.getLocation();
        FallingBlock f = l.getWorld().spawnFallingBlock(l, b.getType(), b.getData());

        f.setVelocity((l.subtract(e.getEntity().getLocation())).toVector().normalize().add(new Vector(0, 1, 0)));
        f.setDropItem(false);

        pushed.add(f);
      }
		}
	}

	@EventHandler
	public void onFallingBlockLand(EntityChangeBlockEvent e) {
		if (e.getEntity() instanceof FallingBlock) {
			FallingBlock b = (FallingBlock) e.getEntity();
			if (pushed.contains(b)) {
				e.setCancelled(true);
				b.getWorld().spawnParticle(Particle.BLOCK_CRACK, b.getLocation(), 5, b.getBlockData());

				for (Entity en : b.getNearbyEntities(2, 2, 2)) {
					try {
						((LivingEntity) en).damage(1);
					} catch (Exception e2) {}
				}
			}
		}
	}

}
