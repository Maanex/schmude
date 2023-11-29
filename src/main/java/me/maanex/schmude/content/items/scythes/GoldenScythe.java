package me.maanex.schmude.content.items.scythes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class GoldenScythe extends CustomItem {
  
  public GoldenScythe() {
    super(100, "golden_scythe", Material.GOLDEN_HOE);

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "golden_scythe.1"),
        asItemStack())
        .shape("III", "00S", "00S")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.GOLD_INGOT)
    );
  }
  
}
