package me.maanex.schmude.features.customcontent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventItemDefaults implements Listener {

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    if (e.getItem() == null) return;
    if (!e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
    if (!e.getItem().getType().equals(Material.MAP)) return;
    if (!e.getItem().hasItemMeta()) return;
    if (!e.getItem().getItemMeta().hasCustomModelData()) return;

    if (e.getItem().getItemMeta().getCustomModelData() > 10) {
      e.setCancelled(true);
    }
  }
  
}
