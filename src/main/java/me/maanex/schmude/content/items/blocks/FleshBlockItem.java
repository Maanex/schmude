package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.FleshBlock;
import me.maanex.schmude.content.tags.FleshTag;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class FleshBlockItem extends CustomItem implements Listener {
  
  public FleshBlockItem() {
    super(101, "fleshblock", Material.FLINT);

    addRecipes(
      new ShapedRecipe(new NamespacedKey(Main.instance, "fleshblock.1"), asItemStack())
        .shape("XXX", "XXX", "XXX")
        .setIngredient('X', new MaterialChoice(FleshTag.get()))
    );
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, FleshBlock.class);
  }

}
