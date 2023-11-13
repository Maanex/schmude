package me.maanex.schmude.content.tickhooks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.entities.FairyWorldEntity;
import me.maanex.schmude.core.customcontent.world.WorldTickHook;
import me.maanex.schmude.lib.ArrayUtils;

public class FairySpawner extends WorldTickHook {

	public void schedule() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this::tick, 5000, 5000);
		// Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this::tick, 100, 100);
	}
	
	public void tick() {
		if (Bukkit.getOnlinePlayers().size() == 0) return;

		Player p = (Player) ArrayUtils.random(Bukkit.getOnlinePlayers().toArray());
    if (!p.getWorld().getBiome(p.getLocation()).toString().contains("FOREST")) return;

		Location spawnLoc = p
			.getEyeLocation()
			.clone()
			.add((int) (Math.random() * 30) - 15, 200, (int) (Math.random() * 30) - 15);
		if (!spawnLoc.getWorld().getBiome(spawnLoc).toString().contains("FOREST")) return;
		
		new FairyWorldEntity(spawnLoc).spawn();
	}
}
