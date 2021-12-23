package me.maanex.schmude.features.customcontent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockChangeActions implements Listener {

  @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
  public void onMushroomPhysics(BlockPhysicsEvent e) {
    if ((e.getChangedType() == Material.BROWN_MUSHROOM_BLOCK) || (e.getChangedType() == Material.RED_MUSHROOM_BLOCK)) {
      e.setCancelled(true);
      e.getBlock().getState().update(true, false);
    }
  }
  
}
