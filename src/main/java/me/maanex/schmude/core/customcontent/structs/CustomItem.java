package me.maanex.schmude.core.customcontent.structs;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.core.customcontent.CustomContent;

public abstract class CustomItem implements CustomElement {

  private int id;
  private String name;
  private Material baseMaterial;
  private String displayName;

  public CustomItem(int id, String name, Material baseMaterial, String displayName) {
    this.id = id;
    this.name = name;
    this.baseMaterial = baseMaterial;
    this.displayName = displayName;
  }

  //

  public int getId() {
    return id;
  }

  //

  public ItemStack asItemStack() {
    ItemStack out = new ItemStack(this.baseMaterial);
    ItemMeta m = out.getItemMeta();
    m.setLocalizedName("schmude.item." + this.name + ".name");
    m.setDisplayName("§r" + displayName);
    m.setCustomModelData(id);
    out.setItemMeta(m);
    return out;
  }

  public RecipeChoice asRecipeChoice() {
    return new ExactChoice(asItemStack());
  }

  //

  protected boolean isThisItem(ItemStack item) {
    if (item == null) return false;
    if (!item.getType().equals(this.baseMaterial)) return false;
    if (!item.hasItemMeta()) return false;
    ItemMeta meta = item.getItemMeta();
    if (!meta.hasCustomModelData()) return false;
    if (meta.getCustomModelData() != this.id) return false;

    return true;
  }

  protected void placeOnInteract(PlayerInteractEvent e, Class<? extends CustomBlock> block) {
    if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
    if (!isThisItem(e.getItem())) return;

    CustomContent
      .getCustomBlockInstance(block)
      .attemptPlayerPlacing(e);
  }

  @Override
  public void addRecipes(Recipe... recipes) {
    CustomContent.addRecipes(recipes);
  }
  
}
