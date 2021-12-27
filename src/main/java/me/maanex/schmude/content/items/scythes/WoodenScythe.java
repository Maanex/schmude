package me.maanex.schmude.content.items.scythes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class WoodenScythe extends CustomItem {
  
  public WoodenScythe() {
    super(100, "wooden_scythe", Material.WOODEN_HOE, "Holzsense");

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "wooden_scythe.1"),
        asItemStack())
        .shape("III", "00S", "00S")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', new MaterialChoice(Tag.PLANKS))
    );
  }
  
}
