package me.maanex.schmude.core.customcontent.structs;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.core.customcontent.CustomContent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class CustomItem implements CustomElement {

  private static final Map<Material, Integer> MATERIAL_INDEX_MAP;

  static {
    MATERIAL_INDEX_MAP = new HashMap<Material, Integer>();

    MATERIAL_INDEX_MAP.put(Material.WOODEN_HOE, 101_000);
    MATERIAL_INDEX_MAP.put(Material.STONE_HOE, 102_000);
    MATERIAL_INDEX_MAP.put(Material.IRON_HOE, 103_000);
    MATERIAL_INDEX_MAP.put(Material.GOLDEN_HOE, 104_000);
    MATERIAL_INDEX_MAP.put(Material.DIAMOND_HOE, 105_000);
    MATERIAL_INDEX_MAP.put(Material.NETHERITE_HOE, 106_000);

    MATERIAL_INDEX_MAP.put(Material.WOODEN_PICKAXE, 111_000);
    MATERIAL_INDEX_MAP.put(Material.STONE_PICKAXE, 112_000);
    MATERIAL_INDEX_MAP.put(Material.IRON_PICKAXE, 113_000);
    MATERIAL_INDEX_MAP.put(Material.GOLDEN_PICKAXE, 114_000);
    MATERIAL_INDEX_MAP.put(Material.DIAMOND_PICKAXE, 115_000);
    MATERIAL_INDEX_MAP.put(Material.NETHERITE_PICKAXE, 116_000);
  }

  //

  private int id;
  private String name;
  private Material baseMaterial;

  public CustomItem(int id, String name, Material baseMaterial) {
    this.id = id;
    this.name = name;
    this.baseMaterial = baseMaterial;
  }

  //

  public int getId() {
    if (MATERIAL_INDEX_MAP.containsKey(this.baseMaterial))
      return MATERIAL_INDEX_MAP.get(this.baseMaterial) + id;

    return id;
  }

  //

  public ItemStack asItemStack() {
    ItemStack out = new ItemStack(this.baseMaterial);
    ItemMeta m = out.getItemMeta();
    m.displayName(Component.translatable(String.format("schmude.item.%s.name", this.name)).decoration(TextDecoration.ITALIC, false));
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
