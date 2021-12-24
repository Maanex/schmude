package me.maanex.schmude.features.qol;

import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.maanex.schmude.content.tags.AxesTag;
import me.maanex.schmude.content.tags.StripableTag;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SlowdownAxe implements Listener {
  
  @EventHandler
	public void onStrip(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		if (!AxesTag.get().isTagged(e.getItem().getType())) return;
		if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if (!StripableTag.get().isTagged(e.getClickedBlock().getType())) return;
		if (e.getPlayer().isSneaking()) return;

		e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Sneaken um Block zu schälen!"));
		e.setCancelled(true);
		e.getClickedBlock().getWorld().spawnParticle(
			Particle.ELECTRIC_SPARK,
			e.getClickedBlock().getLocation().clone().add(0.5, 0.5, 0.5),
			20
		);
	}

}
