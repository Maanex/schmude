package me.maanex.schmude.content.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.maanex.schmude.content.items.blocks.DwayneBlockItem;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class DwayneBlock extends CustomBlock {
  
  public DwayneBlock() {
    super(0);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, DwayneBlockItem.class);
  }

}
