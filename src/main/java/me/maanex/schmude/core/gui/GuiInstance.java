package me.maanex.schmude.core.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiInstance {

  public final Inventory inventory;
  public final Player holder;
  public final GuiInteractions interactions;

  public GuiInstance(Inventory inventory, Player holder, GuiInteractions interactions) {
    this.inventory = inventory;
    this.holder = holder;
    this.interactions = interactions;
  }
  
}
