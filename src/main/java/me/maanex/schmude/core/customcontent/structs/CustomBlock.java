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

import me.maanex.schmude.Main;
import me.maanex.schmude.common.Utils;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.MushroomStates;

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

  protected boolean isThisBlock(Block b) {
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
    this.dropOnBreak(b, by, CustomContent.getCustomItemInstance(item).asItemStack());
  }

  //

  public void placeAt(Block b, Player by) {
    b.setType(Material.BROWN_MUSHROOM_BLOCK);
    MultipleFacing  mush = (MultipleFacing) b.getBlockData();
    MushroomStates.applyBrownMushroomToMultiface(mush, id);
    b.setBlockData(mush);
    onPlaced(b, by);
  }

  public void attemptPlayerPlacing(PlayerInteractEvent e) {
    Block target = e.getClickedBlock().getRelative(e.getBlockFace());
    if (!target.isEmpty()) return;

    if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
      e.getItem().setAmount(e.getItem().getAmount() - 1);

    if (!Utils.getEntitiesOnBlock(target, en -> en instanceof LivingEntity).isEmpty())
      return;

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
      this.placeAt(target, e.getPlayer());
      e.getPlayer().swingMainHand();
    }, 1);
  }
  
}
