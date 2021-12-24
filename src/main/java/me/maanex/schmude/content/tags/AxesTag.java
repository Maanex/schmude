package me.maanex.schmude.content.tags;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import me.maanex.schmude.Main;

public class AxesTag implements Tag<Material> {
  
  private static final AxesTag INSTANCE;
  private static final Set<Material> VALUES;

  static {
    VALUES = new HashSet<Material>();
    VALUES.add(Material.WOODEN_AXE);
    VALUES.add(Material.STONE_AXE);
    VALUES.add(Material.IRON_AXE);
    VALUES.add(Material.GOLDEN_AXE);
    VALUES.add(Material.DIAMOND_AXE);
    VALUES.add(Material.NETHERITE_AXE);

    INSTANCE = new AxesTag();
  }

  public static AxesTag get() {
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
