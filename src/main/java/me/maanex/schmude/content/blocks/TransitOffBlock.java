package me.maanex.schmude.content.blocks;

import java.util.Arrays;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.content.items.blocks.TransitOffBlockItem;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class TransitOffBlock extends CustomBlock implements Listener {
  
  public TransitOffBlock() {
    super(2);
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, TransitOffBlockItem.class);
  }
  
  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
    if (e.getClickedBlock() == null) return;
    if (e.getItem() == null) return;
    if (!e.getItem().getType().equals(Material.ECHO_SHARD)) return;
    if (!isThisBlock(e.getClickedBlock())) return;
    CustomContent.getCustomBlockInstance(TransitOnBlock.class).placeAt(e.getClickedBlock());
    e.getPlayer().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, 1, 1);
    e.getPlayer().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 1);
    e.getPlayer().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1, 1);
    e.getPlayer().getWorld().spawnParticle(Particle.SOUL, e.getClickedBlock().getLocation().clone().add(0.5, 0.5, 0.5), 20, 0.52, 0.52, 0.52, 0);
    e.getPlayer().swingMainHand();

    ItemStack i = e.getItem();
    ItemMeta m = i.getItemMeta();
    String coords = e.getClickedBlock().getLocation().blockX() + ", "
      + e.getClickedBlock().getLocation().blockY() + ", "
      + e.getClickedBlock().getLocation().blockZ() + ", "
      + e.getClickedBlock().getWorld().getName();
    m.lore(Arrays.asList(
      Component.text("Linked with:", NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, false),
      Component.text("[" + coords + "]", NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, false)
    ));
    m.displayName(Component.translatable("item.minecraft.echo_shard", NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false));
    i.setItemMeta(m);
  }

}
