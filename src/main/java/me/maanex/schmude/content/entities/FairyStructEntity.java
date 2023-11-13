package me.maanex.schmude.content.entities;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.maanex.schmude.content.entities.FairyWorldEntity.FairyBehaviourState;
import me.maanex.schmude.content.items.misc.FairyInABottle;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.structs.CustomItem;
import me.maanex.schmude.core.customcontent.structs.CustomStructEntity;
import me.maanex.schmude.core.customcontent.world.WorldEntityManager;

public class FairyStructEntity extends CustomStructEntity implements Listener {
  
  public FairyStructEntity() {
    super(0);
  }

  public void spawnAt(Location loc) {
    new FairyWorldEntity(loc).spawn();
  }

  // CATCH FAIRIES

  @EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent e) {
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		if (item == null) return;
		if (!item.getType().equals(Material.GLASS_BOTTLE)) return;

		List<FairyWorldEntity> fairies = WorldEntityManager.getEntitiesOfType(FairyWorldEntity.class);
		if (fairies.isEmpty()) return;
		
		for (FairyWorldEntity f : fairies) {
			double distance = f.getLocation().distance(e.getPlayer().getEyeLocation());
			if (distance > 6) continue;
			
			Location checkpos = e.getPlayer().getEyeLocation().clone();
			for (int dist = 0; dist < 7; dist++) {
				checkpos.add(e.getPlayer().getLocation().getDirection());
				if (checkpos.distance(f.getLocation()) < 1.2) {
					catchFairy(e.getPlayer(), f);
					return;
				}
			}
		}
	}

  // FAIRY FLIGHT
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) return;
		List<FairyWorldEntity> faries = WorldEntityManager.getEntitiesOfType(FairyWorldEntity.class);
		if (faries.isEmpty()) return;
		for (FairyWorldEntity f : faries) {
			if (!e.getTo().getWorld().equals(f.getLocation().getWorld())) continue;
			double distance = f.getLocation().distance(e.getTo().clone().add(0, 1, 0));
			if (distance < 8) f.switchState(FairyBehaviourState.FLIGHT);
		}
	}

  //

	private void catchFairy(Player p, FairyWorldEntity f) {
		f.destroy();
		p.getInventory().getItemInMainHand().setAmount(p.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);

		CustomItem drop = CustomContent.getCustomItemInstance(FairyInABottle.class);
		p.getWorld().dropItem(p.getEyeLocation(), drop.asItemStack());
	}

}
