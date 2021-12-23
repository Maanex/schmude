package me.maanex.schmude.core.gui;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiHandler implements Listener {

  private static HashMap<String, GuiInstance> guis = new HashMap<>();

  public static void registerInstance(GuiInstance i) {
    guis.put(i.holder.getUniqueId().toString(), i);
  }

  //

  @EventHandler
  public void onClick(InventoryClickEvent e) {
    GuiInstance found = guis.get(e.getWhoClicked().getUniqueId().toString());
    if (found == null) return;

    found.interactions.onGuiClick(e, found);
  }

  @EventHandler
  public void onClose(InventoryCloseEvent e) {
    GuiInstance found = guis.get(e.getPlayer().getUniqueId().toString());
    if (found == null) return;

    guis.remove(e.getPlayer().getUniqueId().toString());
  }
  
}
