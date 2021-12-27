package me.maanex.schmude.content.items.scythes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class StoneScythe extends CustomItem {
  
  public StoneScythe() {
    super(100, "stone_scythe", Material.STONE_HOE, "Steinsense");

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "stone_scythe.1"),
        asItemStack())
        .shape("III", "00S", "00S")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.COBBLESTONE)
    );
  }
  
}
