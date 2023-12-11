package me.maanex.schmude.content.blocks;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.items.blocks.TransitOffBlockItem;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class TransitOnBlock extends CustomBlock implements Listener {
  
  public TransitOnBlock() {
    super(3);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, TransitOffBlockItem.class);
  }

  @EventHandler
  public void onClick(PlayerInteractEvent e) {
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
    ItemStack item = e.getItem();
    if (item == null) return;
    if (!item.getType().equals(Material.ECHO_SHARD)) return;
    if (!item.getItemMeta().hasLore()) return;

    List<Component> lore = item.getItemMeta().lore();
    for (Component c : lore) {
      if (!(c instanceof TextComponent)) continue;
      String content = ((TextComponent) c).content();
      if (!content.startsWith("[")) continue;
      String inner = content.replace("[", "").replace("]", "");
      String[] split = inner.split(", ");
      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);
      int z = Integer.parseInt(split[2]);
      String worldName = split[3];

      World world = Bukkit.getWorld(worldName);
      if (world == null) continue;
      Block target = world.getBlockAt(x, y, z);
      if (!isThisBlock(target)) continue;
      trigger(target);
      e.getPlayer().swingMainHand();
      e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, 1, 1);
    }
  }

  private void trigger(Block b) {
    Block down = b.getRelative(BlockFace.DOWN);
    if (!down.isEmpty()) return;
    down.setType(Material.REDSTONE_BLOCK);

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      if (down.getType().equals(Material.REDSTONE_BLOCK))
        down.setType(Material.AIR);
    }, 20);
  }

}
