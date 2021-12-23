package me.maanex.schmude.content.items;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.content.blocks.DwayneBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class DwayneBlockItem extends CustomItem implements Listener {
  
  public DwayneBlockItem() {
    super(100, "dwayne", Material.FLINT, "Dwayne \"The Block\" Johnson");
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, DwayneBlock.class);
  }

}
