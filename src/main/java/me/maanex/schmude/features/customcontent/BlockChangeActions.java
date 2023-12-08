package me.maanex.schmude.features.customcontent;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.maanex.schmude.core.customcontent.MushroomStates;

public class BlockChangeActions implements Listener {

  @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
  public void onMushroomPhysics(BlockPhysicsEvent e) {
    if ((e.getChangedType() == Material.BROWN_MUSHROOM_BLOCK) || (e.getChangedType() == Material.RED_MUSHROOM_BLOCK)) {
      e.setCancelled(true);
      e.getBlock().getState().update(true, false);
    }
  }

  @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
  public void onMushroomPlace(BlockPlaceEvent e) {
    Block b = e.getBlock();
    if ((b.getType() == Material.BROWN_MUSHROOM_BLOCK) || (b.getType() == Material.RED_MUSHROOM_BLOCK)) {
      MultipleFacing mush = (MultipleFacing) b.getBlockData();
      boolean isSneaking = e.getPlayer().isSneaking();
      MushroomStates.applyBrownMushroomToMultiface(mush, isSneaking ? "000000" : "111111");
      b.setBlockData(mush);
    }
  }
  
}
