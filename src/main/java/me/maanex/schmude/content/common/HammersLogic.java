package me.maanex.schmude.content.common;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.core.customcontent.structs.CustomLogic;
import me.maanex.schmude.lib.BlockUtils;

public class HammersLogic extends CustomLogic implements Listener {

  private HashMap<String, BlockFace> face = new HashMap<>();

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK))
      return;

    face.put(e.getPlayer().getUniqueId().toString(), e.getBlockFace());
  }

  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
    if (i == null) return;
    if (!i.getType().toString().endsWith("_PICKAXE")) return;
    if (!i.hasItemMeta()) return;
    if (!i.getItemMeta().hasCustomModelData()) return;
    int cmd = i.getItemMeta().getCustomModelData();
    if (cmd < 100 || cmd >= 200) return;

    int unbreaking = i.getEnchantmentLevel(Enchantment.DURABILITY);
    Random r = new Random();
    Location origin = e.getBlock().getLocation();
    BlockFace f = face.get(e.getPlayer().getUniqueId().toString());

    if (f == null) return;

    int dx = f.getModX() != 0 ? 0 : 1;
    int dy = f.getModY() != 0 ? 0 : 1;
    int dz = f.getModZ() != 0 ? 0 : 1;

    out:
    for (int x = -dx; x <= dx; x++) {
      for (int y = -dy; y <= dy; y++) {
        for (int z = -dz; z <= dz; z++) {
          Location l = origin.clone().add(x, y, z);
          if (!BlockUtils.isStoneTypeBlock(l.getBlock().getType()) && !BlockUtils.isNaturalOreBlock(l.getBlock().getType()))
            continue;

          l.getBlock().breakNaturally(i);

          if (r.nextBoolean() && r.nextInt(Enchantment.DURABILITY.getMaxLevel() + 1) >= unbreaking) {
            Damageable d = ((Damageable) i.getItemMeta());
            d.setDamage(d.getDamage() + 1);
            i.setItemMeta((ItemMeta) d);
            if (d.getDamage() >= i.getType().getMaxDurability()) {
              e.getPlayer().playSound(e.getPlayer().getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
              i.setAmount(0);
              break out;
            }
          }
        }
      }
    }
  }

}
