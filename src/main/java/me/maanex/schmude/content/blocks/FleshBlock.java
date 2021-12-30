package me.maanex.schmude.content.blocks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.content.items.blocks.FleshBlockItem;
import me.maanex.schmude.content.items.food.DonerMeat;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;

public class FleshBlock extends CustomBlock implements Listener {

  private static final Map<String, Integer> LEVELS = new HashMap<>();

  //
  
  public FleshBlock() {
    super(1);
  }

  private String locationAsString(Location l) {
    return l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ();
  }

  @Override
  public void onBroken(Block b, Player by) {
    dropOnBreak(b, by, FleshBlockItem.class);
    String loc = locationAsString(b.getLocation());
    if (LEVELS.containsKey(loc))
      LEVELS.remove(loc);
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
    if (e.getClickedBlock() == null) return;
    if (e.getItem() == null) return;
    if (!e.getItem().getType().toString().endsWith("_SWORD")) return;
    if (!isThisBlock(e.getClickedBlock())) return;
    Location loc = e.getClickedBlock().getLocation().clone().add(e.getBlockFace().getDirection().multiply(.8));
    loc.getWorld().dropItemNaturally(loc, CustomContent.getCustomItemInstance(DonerMeat.class).asItemStack());
    e.getPlayer().swingMainHand();

    String blocloc = locationAsString(e.getClickedBlock().getLocation());
    if (!LEVELS.containsKey(blocloc)) {
      LEVELS.put(blocloc, 8);
    } else {
      int level = LEVELS.get(blocloc);
      if (level > 1) {
        LEVELS.put(blocloc, level - 1);
      } else {
        e.getClickedBlock().breakNaturally();
        LEVELS.remove(blocloc);
      }
    }    
  }

}
