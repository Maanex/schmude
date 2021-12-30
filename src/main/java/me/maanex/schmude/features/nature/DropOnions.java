package me.maanex.schmude.features.nature;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.maanex.schmude.content.items.food.Onion;
import me.maanex.schmude.core.customcontent.CustomContent;

public class DropOnions implements Listener {

  public static final float DROP_CHANCE = 0.05F;

  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    if (!e.getBlock().getType().equals(Material.POTATOES)) return;
    Ageable a = (Ageable) e.getBlock().getBlockData();
    if (a.getAge() < a.getMaximumAge()) return;

    dropPerhaps(e.getBlock().getLocation());
  }

  public static void dropPerhaps(Location l) {
    if (Math.random() < DROP_CHANCE)
      l.getWorld().dropItemNaturally(l, CustomContent.getCustomItemInstance(Onion.class).asItemStack());
  }
  
}
