package me.maanex.schmude.content.tickhooks;

import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import me.maanex.schmude.Main;
import me.maanex.schmude.content.entities.FairyWorldEntity;
import me.maanex.schmude.core.customcontent.world.WorldTickHook;
import me.maanex.schmude.lib.ArrayUtils;

public class FairySpawner extends WorldTickHook {

	public void schedule() {
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this::tick, 6000, 6000);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this::tick, 100, 100);
	}
	
	public void tick() {
		Player p = (Player) ArrayUtils.random(Bukkit.getOnlinePlayers().toArray());
    if (!p.getWorld().getBiome(p.getLocation()).toString().contains("FOREST")) return;
		new FairyWorldEntity(p.getEyeLocation().clone().add(0, 200, 0)).spawn();
    System.out.println("pogger");
	}
}
