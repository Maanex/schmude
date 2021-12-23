package me.maanex.schmude.features.customcontent;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.Main;
import me.maanex.schmude.common.Language;
import me.maanex.schmude.core.customcontent.CustomContent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PreventBlockDefaults implements Listener {

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent e) {
    if (isForbidden(e.getBlock())) {
      e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Language.CANNOT_PLACE_BLOCK__RESERVED_CUSTOM__NOTEBLOCKS));
      e.setCancelled(true);
      // sanitizeNoteblock(e.getBlock(), 1);
    }
  }

  @EventHandler
  public void onPistonMove(BlockPistonExtendEvent e) {
    if (e.getBlocks().size() == 0) return;
    for (Block b : e.getBlocks()) {
      Material moved = b.getType();
      Block target = b.getRelative(e.getDirection());
      if (isNotblockPlacementForbidden(target, moved)) {
        b.breakNaturally();
        e.setCancelled(true);
        // sanitizeNoteblock(b, 2); <- no work
      }
    }
  }

  @EventHandler
  public void onPistonMove(BlockPistonRetractEvent e) {
    if (e.getBlocks().size() == 0) return;
    for (Block b : e.getBlocks()) {
      Material moved = b.getType();
      Block target = b.getRelative(e.getDirection());
      if (isNotblockPlacementForbidden(target, moved)) {
        b.breakNaturally();
        e.setCancelled(true);
        // sanitizeNoteblock(b, 2); <- no work
      }
    }
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent e) {
    if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
    if (e.getPlayer().isSneaking()) return; // you may shift click to place blocks adjacent
    if (e.getClickedBlock() == null) return;
    Block b = e.getClickedBlock();
    if (!b.getType().equals(Material.NOTE_BLOCK)) return;

    NoteBlock note = (NoteBlock) b.getBlockData();
    if (CustomContent.CUSTOMBLOCK_INSTRUMENTS.values().contains(note.getInstrument()))
      e.setCancelled(true);
  }

  //

  private boolean isForbidden(Block b) {
    if (b == null) return false;

    if (isNotblockPlacementForbidden(b, null))
      return true;

    return false;
  }

  private boolean isNotblockPlacementForbidden(Block b, Material blockType) {
    if (blockType == null) blockType = b.getType();
    if (blockType.equals(Material.NOTE_BLOCK)) {
      Material below = b.getRelative(BlockFace.DOWN).getType();
      if (CustomContent.FORBIDDEN_INSTRUMENT_BLOCKS.contains(below))
        return true;
    } else if (CustomContent.FORBIDDEN_INSTRUMENT_BLOCKS.contains(blockType)) {
      Material above = b.getRelative(BlockFace.UP).getType();
      if (above.equals(Material.NOTE_BLOCK))
        return true;
    }

    return false;
  }

  @SuppressWarnings("unused")
  private void sanitizeNoteblock(final Block b, int delay) {
    if (delay > 0) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> sanitizeNoteblock(b, 0), delay);
      return;
    }

    if (b.getType().equals(Material.NOTE_BLOCK)) {
      NoteBlock note = (NoteBlock) b.getBlockData();
      note.setInstrument(Instrument.GUITAR);
      b.setBlockData(note);
    } else {
      Block rel = b.getRelative(BlockFace.UP);
      NoteBlock note = (NoteBlock) rel.getBlockData();
      note.setInstrument(Instrument.GUITAR);
      rel.setBlockData(note);
    }
  }

}
