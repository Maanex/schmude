package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.DwayneBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class DwayneBlockItem extends CustomItem implements Listener {
  
  public DwayneBlockItem() {
    super(100, "dwayne", Material.FLINT, "Dwayne \"The Block\" Johnson");

    addRecipes(
      new SmithingRecipe(
        new NamespacedKey(Main.instance, "dwayne.1"),
        this.asItemStack(),
        new MaterialChoice(Material.COBBLESTONE),
        new MaterialChoice(Material.COBBLESTONE)
      ),
      new ShapedRecipe(
        new NamespacedKey(Main.instance, "dwayne.2"),
        new ItemStack(Material.COAL))
        .shape("XXX", "XXX", "XXX")
        .setIngredient('X', asRecipeChoice())
    );
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, DwayneBlock.class);
  }

}
