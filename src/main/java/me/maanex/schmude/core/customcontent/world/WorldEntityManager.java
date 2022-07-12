package me.maanex.schmude.core.customcontent.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import me.maanex.schmude.Main;


public class WorldEntityManager {

	private static List<CustomWorldEntity> entities = new ArrayList<>();
	private static int tick = 0;

	//

	private WorldEntityManager() {
	}

	//

	public static void init() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, WorldEntityManager::tick, 1, 1);
	}

	//

	public static void addEntity(CustomWorldEntity entity) {
		entities.add(entity);
	}

	public static void removeEntity(CustomWorldEntity entity) {
		entities.remove(entity);
	}

	//

	public static void tick() {
		tick = ++tick % 20;
		for (CustomWorldEntity e : entities)
			e.update(tick);
	}

	@SuppressWarnings("unchecked")
	public static <T extends CustomWorldEntity> List<T> getEntitiesOfType(Class<T> type) {
		List<T> out = new ArrayList<>();
		for (CustomWorldEntity e : entities)
			if (type.isInstance(e))
				out.add((T) e);
		return out;
	}

}
