package me.maanex.schmude.features.qol;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.content.tags.TrampleableTag;

public class PreventTrampling implements Listener {
  
  @EventHandler
	public void onTrample(PlayerInteractEvent event) {
		if (event.getAction() != Action.PHYSICAL) return;
		Block soil = event.getClickedBlock();
		if (soil == null || soil.getType() != Material.FARMLAND) return;
		event.setUseInteractedBlock(Event.Result.DENY);
		event.setCancelled(true);

		Material org = soil.getRelative(BlockFace.UP).getType();

		if (!TrampleableTag.get().isTagged(org))
			return;

		soil.getRelative(BlockFace.UP).setType(Material.AIR);
		soil.getRelative(BlockFace.UP).setType(org);
		soil.getWorld().playSound(soil.getLocation(), Sound.BLOCK_CROP_BREAK, 1, 1);
	}

}
