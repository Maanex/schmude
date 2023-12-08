package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import me.maanex.schmude.content.blocks.TransitOffBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class TransitOffBlockItem extends CustomItem implements Listener {
  
  public TransitOffBlockItem() {
    super(104, "transit_off", Material.FLINT);
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, TransitOffBlock.class);
  }

}
