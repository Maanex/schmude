package me.maanex.schmude.content.tags;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import me.maanex.schmude.Main;

public class StripableTag implements Tag<Material> {
  
  private static final StripableTag INSTANCE;
  private static final Set<Material> VALUES;

  static {
    VALUES = new HashSet<Material>();
    VALUES.add(Material.OAK_LOG);
    VALUES.add(Material.SPRUCE_LOG);
    VALUES.add(Material.BIRCH_LOG);
    VALUES.add(Material.JUNGLE_LOG);
    VALUES.add(Material.ACACIA_LOG);
    VALUES.add(Material.DARK_OAK_LOG);
    VALUES.add(Material.CRIMSON_STEM);
    VALUES.add(Material.WARPED_STEM);

    VALUES.add(Material.COPPER_BLOCK);
    VALUES.add(Material.EXPOSED_COPPER);
    VALUES.add(Material.OXIDIZED_COPPER);
    VALUES.add(Material.WEATHERED_COPPER);

    // VALUES.add(Material.CUT_COPPER);
    // VALUES.add(Material.CUT_COPPER_SLAB);
    // VALUES.add(Material.CUT_COPPER_STAIRS);
    VALUES.add(Material.EXPOSED_CUT_COPPER);
    VALUES.add(Material.EXPOSED_CUT_COPPER_SLAB);
    VALUES.add(Material.EXPOSED_CUT_COPPER_STAIRS);
    VALUES.add(Material.OXIDIZED_CUT_COPPER);
    VALUES.add(Material.OXIDIZED_CUT_COPPER_SLAB);
    VALUES.add(Material.OXIDIZED_CUT_COPPER_STAIRS);
    VALUES.add(Material.WEATHERED_CUT_COPPER);
    VALUES.add(Material.WEATHERED_CUT_COPPER_SLAB);
    VALUES.add(Material.WEATHERED_CUT_COPPER_STAIRS);
    VALUES.add(Material.WAXED_CUT_COPPER);
    VALUES.add(Material.WAXED_CUT_COPPER_SLAB);
    VALUES.add(Material.WAXED_CUT_COPPER_STAIRS);
    VALUES.add(Material.WAXED_EXPOSED_CUT_COPPER);
    VALUES.add(Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
    VALUES.add(Material.WAXED_EXPOSED_CUT_COPPER_STAIRS);
    VALUES.add(Material.WAXED_OXIDIZED_CUT_COPPER);
    VALUES.add(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
    VALUES.add(Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
    VALUES.add(Material.WAXED_WEATHERED_CUT_COPPER);
    VALUES.add(Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
    VALUES.add(Material.WAXED_WEATHERED_CUT_COPPER_STAIRS);

    INSTANCE = new StripableTag();
  }

  public static StripableTag get() {
    return INSTANCE;
  }

  //

  @Override
  public NamespacedKey getKey() {
    return new NamespacedKey(Main.instance, "tag.trampleable");
  }

  @Override
  public boolean isTagged(Material item) {
    return getValues().contains(item);
  }

  @Override
  public Set<Material> getValues() {
    return VALUES;
  }
  
}
