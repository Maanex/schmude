package me.maanex.schmude.content.items.hammers;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class DiamondHammer extends CustomItem {
  
  public DiamondHammer() {
    super(100, "diamond_hammer", Material.DIAMOND_PICKAXE, "Diamanthammer");

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "diamond_hammer.1"),
        asItemStack())
        .shape("III", "0S0", "0S0")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.DIAMOND_BLOCK)
    );
  }
  
}
