package me.maanex.schmude.content.items.hammers;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class WoodenHammer extends CustomItem {
  
  public WoodenHammer() {
    super(100, "wooden_hammer", Material.WOODEN_PICKAXE, "Holzhammer");

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "wooden_hammer.1"),
        asItemStack())
        .shape("III", "0S0", "0S0")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', new MaterialChoice(Tag.LOGS))
    );
  }
  
}
