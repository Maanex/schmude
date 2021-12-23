package me.maanex.schmude.features.customcontent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockChangeActions implements Listener {

  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPhysics(BlockPhysicsEvent e) {
    if (e.getSourceBlock().getType().equals(Material.NOTE_BLOCK) && e.getChangedType().equals(Material.NOTE_BLOCK))
      noteblockChange(e.getSourceBlock(), e);
  }

  //

  private void noteblockChange(Block b, BlockPhysicsEvent e) {
    NoteBlock note = (NoteBlock) b.getBlockData();
    Bukkit.broadcastMessage(note.getInstrument().toString());
    // e.setCancelled(true);
  }
  
}
