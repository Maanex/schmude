package me.maanex.schmude.core.customcontent.world;

import java.util.UUID;

import org.bukkit.Location;

public abstract class CustomWorldEntity {

	private String type;
	private String name;
	private Location loc;
	private UUID uuid;

	public CustomWorldEntity(String type, String name, Location loc) {
		this.type = type;
		this.name = name;
		this.loc = loc;

		this.uuid = UUID.randomUUID();
	}

	//

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return loc;
	}

	public UUID getUUID() {
		return uuid;
	}

	public void destroy() {
		beforeDestroy();
		WorldEntityManager.removeEntity(this);
	}

	public void spawn() {
		WorldEntityManager.addEntity(this);
	}

	//

	public abstract void update(int tick);

	protected abstract void beforeDestroy();

}
