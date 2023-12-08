package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import me.maanex.schmude.content.blocks.TransitOnBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class TransitOnBlockItem extends CustomItem implements Listener {
  
  public TransitOnBlockItem() {
    super(105, "transit_on", Material.FLINT);
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, TransitOnBlock.class);
  }

}
