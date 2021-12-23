package me.maanex.schmude.core.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface GuiInteractions {

  public void onGuiClick(InventoryClickEvent e, GuiInstance i);
  
}
