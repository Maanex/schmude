package me.maanex.schmude.content.items.hammers;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class StoneHammer extends CustomItem {
  
  public StoneHammer() {
    super(100, "stone_hammer", Material.STONE_PICKAXE, "Steinhammer");

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "stone_hammer.1"),
        asItemStack())
        .shape("III", "0S0", "0S0")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.SMOOTH_STONE)
    );
  }
  
}
