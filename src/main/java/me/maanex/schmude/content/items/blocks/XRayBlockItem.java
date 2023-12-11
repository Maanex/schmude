package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapedRecipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.XRayBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class XRayBlockItem extends CustomItem implements Listener {
  
  public XRayBlockItem() {
    super(106, "xrayblock", Material.FLINT);

    addRecipes(
      new ShapedRecipe(new NamespacedKey(Main.instance, "xray.1"), asItemStack())
        .shape("DGD", "DPD", "DRD")
        .setIngredient('D', new MaterialChoice(Material.DEEPSLATE))
        .setIngredient('G', new MaterialChoice(Material.LIGHT_WEIGHTED_PRESSURE_PLATE))
        .setIngredient('P', new MaterialChoice(Material.PISTON))
        .setIngredient('R', new MaterialChoice(Material.REDSTONE_BLOCK))
    );
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, XRayBlock.class);
  }

}
