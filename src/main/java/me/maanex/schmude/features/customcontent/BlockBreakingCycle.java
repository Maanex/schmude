package me.maanex.schmude.features.customcontent;

import org.bukkit.Material;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.MushroomStates;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class BlockBreakingCycle implements Listener {

  @EventHandler(priority = EventPriority.NORMAL)
  public void onBreak(BlockBreakEvent e) {
    if (e.getBlock() == null) return;
    if (!e.getBlock().getType().equals(Material.BROWN_MUSHROOM_BLOCK)) return;
    int id = MushroomStates.getIdFromBrownMushroomData((MultipleFacing) e.getBlock().getBlockData());
    if (id == -1) return;
    CustomBlock b = CustomContent.customBlocks.get(id);
    if (b == null) return;
    b.onBroken(e.getBlock(), e.getPlayer());
    e.setDropItems(false);
    e.setExpToDrop(0);
  }
  
}
