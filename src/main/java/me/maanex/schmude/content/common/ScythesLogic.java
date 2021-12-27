package me.maanex.schmude.content.common;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.core.customcontent.structs.CustomLogic;

public class ScythesLogic extends CustomLogic implements Listener {

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
    if (e.getItem() == null) return;
    if (!e.getItem().getType().toString().endsWith("_HOE")) return;
    if (!e.getItem().hasItemMeta()) return;
    if (!e.getItem().getItemMeta().hasCustomModelData()) return;
    int cmd = e.getItem().getItemMeta().getCustomModelData();
    if (cmd < 100 || cmd >= 200) return;

    int unbreaking = e.getItem().getEnchantmentLevel(Enchantment.DURABILITY);
    Random r = new Random();

    out:
    for (int x = -1; x < 2; x++)
      for (int z = -1; z < 2; z++) {
        boolean used = harvest(e.getClickedBlock().getLocation().clone().add(x, 0, z));

        if (used && r.nextInt(Enchantment.DURABILITY.getMaxLevel() + 1) >= unbreaking) {
          Damageable d = ((Damageable) e.getItem().getItemMeta());
          d.setDamage(d.getDamage() + 1);
          e.getItem().setItemMeta((ItemMeta) d);
          if (d.getDamage() >= e.getItem().getType().getMaxDurability()) {
            e.getPlayer().playSound(e.getPlayer().getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            e.getItem().setAmount(0);
            break out;
          }
        }
      }
  }

  public boolean harvest(Location l) {
    ItemStack s = getDrop(l.getBlock().getType());
    if (s == null) return false;

    Ageable a = (Ageable) l.getBlock().getBlockData();
    if (a.getAge() < a.getMaximumAge())
      return false;

    l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1);

    Material m = l.getBlock().getType();
    l.getBlock().setType(Material.AIR);
    l.getBlock().setType(m);

    l.getWorld().dropItemNaturally(l, s);

    return true;
  }

  public ItemStack getDrop(Material m) {
    switch (m) {
      case WHEAT: return new ItemStack(Material.WHEAT);
      case BEETROOTS: return new ItemStack(Material.BEETROOT);
      case CARROTS: return new ItemStack(Material.CARROT);
      case POTATOES: return new ItemStack(Material.POTATO);
      default: return null;
    }
  }
  
}
