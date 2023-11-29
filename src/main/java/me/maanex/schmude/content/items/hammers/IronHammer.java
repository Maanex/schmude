package me.maanex.schmude.content.items.hammers;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class IronHammer extends CustomItem {
  
  public IronHammer() {
    super(100, "iron_hammer", Material.IRON_PICKAXE);

    addRecipes(
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "iron_hammer.1"),
        asItemStack())
        .shape("III", "0S0", "0S0")
        .setIngredient('S', Material.STICK)
        .setIngredient('I', Material.IRON_BLOCK)
    );
  }
  
}
