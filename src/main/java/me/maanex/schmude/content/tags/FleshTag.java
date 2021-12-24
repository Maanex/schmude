package me.maanex.schmude.content.tags;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import me.maanex.schmude.Main;

public class FleshTag implements Tag<Material> {
  
  private static final FleshTag INSTANCE;
  private static final Set<Material> VALUES;

  static {
    VALUES = new HashSet<Material>();
    VALUES.add(Material.PORKCHOP);
    VALUES.add(Material.CHICKEN);
    VALUES.add(Material.RABBIT);
    VALUES.add(Material.MUTTON);
    VALUES.add(Material.BEEF);

    INSTANCE = new FleshTag();
  }

  public static FleshTag get() {
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
