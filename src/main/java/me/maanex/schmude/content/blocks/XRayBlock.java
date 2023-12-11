package me.maanex.schmude.content.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.maanex.schmude.content.items.blocks.XRayBlockItem;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class XRayBlock extends CustomBlock implements Listener {
  
  public XRayBlock() {
    super(4);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, XRayBlockItem.class);
  }

  @EventHandler
  public void onMove(PlayerMoveEvent e) {
    Block toBlock = e.getTo().clone().add(0, -1, 0).getBlock();
    if (isThisBlock(toBlock)) {
      if (e.getPlayer().getInventory().isEmpty()) on(toBlock);
      else off(toBlock);
      return;
    }

    Block fromBlock = e.getFrom().clone().add(0, -1, 0).getBlock();
    if (isThisBlock(fromBlock))
      off(fromBlock);
  }

  private void on(Block b) {
    Block down = b.getRelative(BlockFace.DOWN);
    if (down.isEmpty())
      down.setType(Material.REDSTONE_BLOCK);
  }

  private void off(Block b) {
    Block down = b.getRelative(BlockFace.DOWN);
    if (down.getType().equals(Material.REDSTONE_BLOCK))
      down.setType(Material.AIR);
  }

}
