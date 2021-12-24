package me.maanex.schmude.lib;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiItems {

  public static ItemStack getBlockingItem() {
    ItemStack s = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    ItemMeta m = s.getItemMeta();
    m.setDisplayName("§7");
    m.setLocalizedName("GUI:empty");
    s.setItemMeta(m);
    return s;
  }

  public static ItemStack getBackButton() {
    ItemStack s = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    ItemMeta m = s.getItemMeta();
    m.setDisplayName("§aBack");
    m.setLocalizedName("GUI:back");
    s.setItemMeta(m);
    return s;
  }

  public static ItemStack getNextButton() {
    ItemStack s = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    ItemMeta m = s.getItemMeta();
    m.setDisplayName("§aNext");
    m.setLocalizedName("GUI:next");
    s.setItemMeta(m);
    return s;
  }

  public static ItemStack getCustomButton(Material material, String name, String action) {
    ItemStack s = new ItemStack(material);
    ItemMeta m = s.getItemMeta();
    m.setDisplayName(name);
    m.setLocalizedName("GUI:" + action);
    s.setItemMeta(m);
    return s;
  }

}
