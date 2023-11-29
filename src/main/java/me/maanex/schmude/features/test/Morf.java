package me.maanex.schmude.features.test;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Transformation;

import me.maanex.schmude.Main;

public class Morf implements Listener {

  @EventHandler
  public void onSneako(PlayerToggleSneakEvent e) {
    if (!e.isSneaking()) return;

    Material[] mats = new Material[]{
      Material.CYAN_CONCRETE,
      Material.BLACK_CONCRETE,
      Material.BLUE_CONCRETE,
      Material.BROWN_CONCRETE,
      Material.GRAY_CONCRETE,
      Material.GREEN_CONCRETE,
      Material.LIGHT_BLUE_CONCRETE,
      Material.LIGHT_GRAY_CONCRETE,
      Material.LIME_CONCRETE,
      Material.MAGENTA_CONCRETE,
      Material.ORANGE_CONCRETE,
      Material.PINK_CONCRETE,
      Material.PURPLE_CONCRETE,
      Material.RED_CONCRETE,
      Material.WHITE_CONCRETE,
      Material.YELLOW_CONCRETE,
      Material.BLACK_CONCRETE_POWDER,
      Material.BLUE_CONCRETE_POWDER,
      Material.BROWN_CONCRETE_POWDER,
      Material.CYAN_CONCRETE_POWDER,
      Material.GRAY_CONCRETE_POWDER,
      Material.GREEN_CONCRETE_POWDER,
      Material.LIME_CONCRETE_POWDER,
      Material.MAGENTA_CONCRETE_POWDER,
      Material.ORANGE_CONCRETE_POWDER,
      Material.PINK_CONCRETE_POWDER,
      Material.PURPLE_CONCRETE_POWDER,
      Material.RED_CONCRETE_POWDER,
      Material.WHITE_CONCRETE_POWDER,
      Material.YELLOW_CONCRETE_POWDER,
      Material.LIGHT_BLUE_CONCRETE_POWDER,
      Material.LIGHT_GRAY_CONCRETE_POWDER,
      Material.BLACK_GLAZED_TERRACOTTA,
      Material.BLUE_GLAZED_TERRACOTTA,
      Material.BROWN_GLAZED_TERRACOTTA,
      Material.CYAN_GLAZED_TERRACOTTA,
      Material.GRAY_GLAZED_TERRACOTTA,
      Material.GREEN_GLAZED_TERRACOTTA,
      Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
      Material.LIME_GLAZED_TERRACOTTA,
      Material.MAGENTA_GLAZED_TERRACOTTA,
      Material.ORANGE_GLAZED_TERRACOTTA,
      Material.PINK_GLAZED_TERRACOTTA,
      Material.PURPLE_GLAZED_TERRACOTTA,
      Material.RED_GLAZED_TERRACOTTA,
      Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
      Material.WHITE_GLAZED_TERRACOTTA,
      Material.YELLOW_GLAZED_TERRACOTTA,
      Material.BLACK_TERRACOTTA,
      Material.BLUE_TERRACOTTA,
      Material.BROWN_TERRACOTTA,
      Material.CYAN_TERRACOTTA,
      Material.GRAY_TERRACOTTA,
      Material.GREEN_TERRACOTTA,
      Material.LIGHT_BLUE_TERRACOTTA,
      Material.LIME_TERRACOTTA,
      Material.MAGENTA_TERRACOTTA,
      Material.ORANGE_TERRACOTTA,
      Material.PINK_TERRACOTTA,
      Material.PURPLE_TERRACOTTA,
      Material.RED_TERRACOTTA,
      Material.LIGHT_GRAY_TERRACOTTA,
      Material.WHITE_TERRACOTTA,
      Material.YELLOW_TERRACOTTA,
      Material.COAL_ORE,
      Material.DIAMOND_ORE,
      Material.EMERALD_ORE,
      Material.GOLD_ORE,
      Material.IRON_ORE,
      Material.LAPIS_ORE,
      Material.NETHER_QUARTZ_ORE,
      Material.REDSTONE_ORE,
      Material.COPPER_ORE,
      Material.DEEPSLATE_COAL_ORE,
      Material.DEEPSLATE_DIAMOND_ORE,
      Material.DEEPSLATE_EMERALD_ORE,
      Material.DEEPSLATE_GOLD_ORE,
      Material.DEEPSLATE_IRON_ORE,
      Material.DEEPSLATE_LAPIS_ORE,
      Material.DEEPSLATE_REDSTONE_ORE,
      Material.DEEPSLATE_COPPER_ORE,
      Material.NETHER_GOLD_ORE,
      Material.COAL_BLOCK,
      Material.DIAMOND_BLOCK,
      Material.EMERALD_BLOCK,
      Material.GOLD_BLOCK,
      Material.IRON_BLOCK,
      Material.LAPIS_BLOCK,
      Material.QUARTZ_BLOCK,
      Material.REDSTONE_BLOCK,
      Material.FURNACE,
      Material.BLAST_FURNACE,
      Material.SMOKER,
      Material.CARTOGRAPHY_TABLE,
      Material.FLETCHING_TABLE,
      Material.LECTERN,
      Material.LOOM,
      Material.STONECUTTER,
      Material.GRINDSTONE,
      Material.SMITHING_TABLE,
      Material.ANVIL,
      Material.ENCHANTING_TABLE,
      Material.JUKEBOX,
      Material.NOTE_BLOCK,
      Material.BREWING_STAND,
      Material.CAULDRON,
      Material.COMPOSTER,
      Material.CHEST,
      Material.ENDER_CHEST,
      Material.TRAPPED_CHEST,
      Material.BARREL,
      Material.STONE,
      Material.COBBLESTONE,
      Material.MOSSY_COBBLESTONE,
      Material.INFESTED_COBBLESTONE,
      Material.ANDESITE,
      Material.POLISHED_ANDESITE,
      Material.DIORITE,
      Material.POLISHED_DIORITE,
      Material.GRANITE,
      Material.POLISHED_GRANITE,
      Material.BRICK,
      Material.NETHER_BRICK,
      Material.SANDSTONE,
      Material.CHISELED_SANDSTONE,
      Material.SMOOTH_SANDSTONE,
      Material.CUT_SANDSTONE,
      Material.RED_SANDSTONE,
      Material.CHISELED_RED_SANDSTONE,
      Material.CUT_RED_SANDSTONE,
      Material.SMOOTH_RED_SANDSTONE,
      Material.PRISMARINE,
      Material.NETHERRACK,
      Material.END_STONE,
      Material.END_STONE_BRICKS,
      Material.PURPUR_BLOCK,
      Material.PURPUR_PILLAR,
      Material.DEEPSLATE,
      Material.DEEPSLATE_BRICKS,
      Material.DEEPSLATE_TILES,
      Material.POLISHED_DEEPSLATE,
      Material.INFESTED_DEEPSLATE,
      Material.BASALT,
      Material.SMOOTH_BASALT,
      Material.CALCITE,
      Material.ACACIA_FENCE_GATE,
      Material.BIRCH_FENCE_GATE,
      Material.DARK_OAK_FENCE_GATE,
      Material.JUNGLE_FENCE_GATE,
      Material.OAK_FENCE_GATE,
      Material.SPRUCE_FENCE_GATE
    };

    Block b = e.getPlayer().getTargetBlock(20);
    if (b.isPassable()) return;
    Location loc = b.getLocation();
    Random r = new Random();

    BlockDisplay d = (BlockDisplay) b.getWorld().spawnEntity(loc.clone().add(0.5, 0.5, 0.5), EntityType.BLOCK_DISPLAY);
    d.setBlock(b.getBlockData());
    d.setInterpolationDuration(20);
    e.getPlayer().sendBlockChange(loc, Material.BARRIER.createBlockData());

    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, () -> {
      d.setInterpolationDelay(0);
      Transformation t = d.getTransformation();
      t.getScale().set(r.nextFloat() * 2, r.nextFloat() * 2, r.nextFloat() * 2);
      t.getTranslation().x = t.getScale().x * -0.5F;
      t.getTranslation().y = t.getScale().y * -0.5F;
      t.getTranslation().z = t.getScale().z * -0.5F;
      d.setTransformation(t);
    }, 0, 20);
    System.out.println("poggie");
  }
  
}
