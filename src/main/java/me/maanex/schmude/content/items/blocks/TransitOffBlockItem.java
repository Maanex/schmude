package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.TransitOffBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class TransitOffBlockItem extends CustomItem implements Listener {
  
  public TransitOffBlockItem() {
    super(104, "transit_off", Material.FLINT);

    addRecipes(
      new ShapedRecipe(new NamespacedKey(Main.instance, "transit.1"), asItemStack())
        .shape("OSO", "OLO", "OOO")
        .setIngredient('O', new MaterialChoice(Material.OBSIDIAN))
        .setIngredient('L', new MaterialChoice(Material.LODESTONE))
        .setIngredient('S', new MaterialChoice(Material.CALIBRATED_SCULK_SENSOR))
    );
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, TransitOffBlock.class);
  }

}
