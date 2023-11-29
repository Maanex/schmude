package me.maanex.schmude.content.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.DwayneBlock;
import me.maanex.schmude.core.customcontent.structs.CustomItem;

public class DwayneBlockItem extends CustomItem implements Listener {
  
  public DwayneBlockItem() {
    super(100, "dwayne", Material.FLINT);

    addRecipes(
      new SmithingTransformRecipe(
        new NamespacedKey(Main.instance, "dwayne.1"),
        this.asItemStack(),
        new MaterialChoice(Material.SLIME_BALL),
        new MaterialChoice(Material.EXPERIENCE_BOTTLE),
        new MaterialChoice(Material.STONE)
      )
    );
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    placeOnInteract(e, DwayneBlock.class);
  }

}
