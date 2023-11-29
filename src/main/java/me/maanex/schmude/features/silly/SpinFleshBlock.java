package me.maanex.schmude.features.silly;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Vector3fc;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.blocks.FleshBlock;
import me.maanex.schmude.content.items.blocks.FleshBlockItem;
import me.maanex.schmude.core.customcontent.CustomContent;

public class SpinFleshBlock implements Listener {

  private HashSet<String> currentlySpinning = new HashSet<String>();

  @EventHandler
  public void onClick(PlayerInteractEvent e) {
    if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
      return;
      System.out.println(e.getPlayer().getInventory().getItemInMainHand());
    if (!e.getPlayer().getInventory().getItemInMainHand().isEmpty())
      return;

    if (!CustomContent.getCustomBlockInstance(FleshBlock.class).isThisBlock(e.getClickedBlock()))
      return;

    Block b = e.getClickedBlock();
    Location loc = b.getLocation();
    String locstr = getLocString(loc);
    if (currentlySpinning.contains(locstr)) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
        e.getPlayer().sendBlockChange(loc, Material.BARRIER.createBlockData());
      }, 1);
      return;
    }
    
    spinner(loc);
    for (int i = 0; i < 10; i++) {
      Location l2 = loc.clone().add(0, i, 0);
      if (!CustomContent.getCustomBlockInstance(FleshBlock.class).isThisBlock(l2.getBlock()))
        continue;
      spinner(l2);
    }
    for (int i = 0; i < 10; i++) {
      Location l2 = loc.clone().add(0, -i, 0);
      if (!CustomContent.getCustomBlockInstance(FleshBlock.class).isThisBlock(l2.getBlock()))
        continue;
      spinner(l2);
    }
  }

  private String getLocString(Location loc) {
    return loc.getX() + "," + loc.getY() + "," + loc.getZ();
  }

  private void spinner(Location loc) {
    String locstr = getLocString(loc);
    if (currentlySpinning.contains(locstr)) return;
    currentlySpinning.add(locstr);

    ItemDisplay d = (ItemDisplay) loc.getWorld().spawnEntity(loc.clone().add(0.5, 0.5, 0.5), EntityType.ITEM_DISPLAY);
    d.setItemStack(CustomContent.getCustomItemInstance(FleshBlockItem.class).asItemStack());
    d.setInterpolationDuration(20);

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      updateBlock(loc, true);
    }, 1);
    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      updateBlock(loc, true);
      d.setInterpolationDelay(0);
      Transformation t = d.getTransformation();
      t.getRightRotation().rotateAxis((float) Math.PI / 2, 0, 1, 0);
      d.setTransformation(t);
    }, 3);
    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      updateBlock(loc, false);
    }, 23);
    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      currentlySpinning.remove(locstr);
      d.remove();
    }, 24);
  }

  private void updateBlock(Location loc, boolean barrier) {
    if (barrier) {
      Bukkit.getOnlinePlayers().forEach(p -> p.sendBlockChange(loc, Material.BARRIER.createBlockData()));
    } else {
      Bukkit.getOnlinePlayers().forEach(p -> p.sendBlockChange(loc, loc.getBlock().getBlockData()));
    }
  }

}
