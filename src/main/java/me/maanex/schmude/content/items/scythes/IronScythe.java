package me.maanex.schmude.content.items.scythes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class IronScythe extends CustomItem {
  
  public IronScythe() {
    super(100, "iron_scythe", Material.IRON_HOE);

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "iron_scythe.1"),
        asItemStack())
        .shape("III", "00S", "00S")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.IRON_INGOT)
    );
  }
  
}
