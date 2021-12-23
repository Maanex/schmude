package me.maanex.schmude.content.items;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.content.blocks.FleshBlock;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class FleshBlockItem extends CustomItem implements Listener {
  
  public FleshBlockItem() {
    super(101, "fleshblock", Material.FLINT, "Fleischblock");
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
    if (!isThisItem(e.getItem())) return;

    CustomContent
      .getCustomBlockInstance(FleshBlock.class)
      .attemptPlayerPlacing(e);
  }

}
