package me.maanex.schmude.content.tags;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import me.maanex.schmude.Main;

public class TrampleableTag implements Tag<Material> {
  
  private static final TrampleableTag INSTANCE;
  private static final Set<Material> VALUES;

  static {
    VALUES = new HashSet<Material>();
    VALUES.add(Material.WHEAT);
    VALUES.add(Material.BEETROOTS);
    VALUES.add(Material.CARROTS);
    VALUES.add(Material.POTATOES);
    VALUES.add(Material.MELON_STEM);
    VALUES.add(Material.PUMPKIN_STEM);

    INSTANCE = new TrampleableTag();
  }

  public static TrampleableTag get() {
    return INSTANCE;
  }

  //

  @Override
  public NamespacedKey getKey() {
    return new NamespacedKey(Main.instance, "tag." + getClass().getName().replace("Tag", "").toLowerCase());
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
