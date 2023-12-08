package me.maanex.schmude.core.customcontent.structs;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.MushroomStates;
import me.maanex.schmude.lib.Utils;

public abstract class CustomBlock implements CustomElement {

  private int id;

  public CustomBlock(int id) {
    this.id = id;
    
  }

  //

  public int getId() {
    return id;
  }

  public int getIdGroup() {
    return id / 25;
  }

  public int getIdElement() {
    return id % 25;
  }

  //

  public void onPlaced(Block b, Player by) { }

  public void onBroken(Block b, Player by) { }

  //

  public boolean isThisBlock(Block b) {
    if (b == null) return false;
    if (!b.getType().equals(Material.BROWN_MUSHROOM_BLOCK)) return false;
    int bid = MushroomStates.getIdFromBrownMushroomData((MultipleFacing) b.getBlockData());
    return bid == this.id;
  }

  protected void dropOnBreak(Block b, Player by, ItemStack item) {
    if (!by.getGameMode().equals(GameMode.CREATIVE))
      b.getWorld().dropItemNaturally(b.getLocation(), item);
  }

  protected void dropOnBreak(Block b, Player by, Class<? extends CustomItem> item) {
    CustomItem instance = CustomContent.getCustomItemInstance(item);
    if (instance != null)
      this.dropOnBreak(b, by, instance.asItemStack());
  }

  @Override
  public void addRecipes(Recipe... recipes) {
    CustomContent.addRecipes(recipes);
  }

  //

  public void placeAt(Block b, Player by) {
    placeAt(b);
    onPlaced(b, by);
  }

  public void placeAt(Block b) {
    b.setType(Material.BROWN_MUSHROOM_BLOCK);
    MultipleFacing mush = (MultipleFacing) b.getBlockData();
    MushroomStates.applyBrownMushroomToMultiface(mush, id);
    b.setBlockData(mush);
  }

  public void attemptPlayerPlacing(PlayerInteractEvent e) {
    Block target = e.getClickedBlock().getRelative(e.getBlockFace());
    if (!target.isEmpty()) return;

    if (!Utils.getEntitiesOnBlock(target, en -> en instanceof LivingEntity).isEmpty())
      return;

    if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
      e.getItem().setAmount(e.getItem().getAmount() - 1);

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      this.placeAt(target, e.getPlayer());
      e.getPlayer().swingMainHand();
    }, 1);
  }
  
}
