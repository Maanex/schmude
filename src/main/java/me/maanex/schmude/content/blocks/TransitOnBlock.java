package me.maanex.schmude.content.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.maanex.schmude.content.items.blocks.TransitOffBlockItem;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class TransitOnBlock extends CustomBlock {
  
  public TransitOnBlock() {
    super(3);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, TransitOffBlockItem.class);
  }

}
