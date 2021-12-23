package me.maanex.schmude.common;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class Utils {
  
  public static List<Entity> getEntitiesOnBlock(Block block, Predicate<Entity> predicate) {
    Location loc = block.getLocation().add(.5, .5, .5);
    return (List<Entity>) block.getWorld().getNearbyEntities(loc, .5, .5, .5, predicate);
  }

}
