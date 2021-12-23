package me.maanex.schmude.content.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.maanex.schmude.content.items.FleshBlockItem;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class FleshBlock extends CustomBlock {
  
  public FleshBlock() {
    super(1);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, FleshBlockItem.class);
  }

}
