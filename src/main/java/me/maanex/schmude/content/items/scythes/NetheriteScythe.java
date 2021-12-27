package me.maanex.schmude.content.items.scythes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class NetheriteScythe extends CustomItem {
  
  public NetheriteScythe() {
    super(100, "netherite_scythe", Material.NETHERITE_HOE, "Netheritsense");

    addRecipes(
      new SmithingRecipe(
        new NamespacedKey(Main.instance, "netherite_scythe.1"),
        asItemStack(),
        CustomContent.getCustomItemInstance(DiamondScythe.class).asRecipeChoice(),
        new MaterialChoice(Material.NETHERITE_INGOT)
      )
    );
  }
  
}
