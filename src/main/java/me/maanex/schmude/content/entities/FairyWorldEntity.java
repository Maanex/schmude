package me.maanex.schmude.content.entities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import me.maanex.schmude.core.customcontent.world.CustomWorldEntity;

public class FairyWorldEntity extends CustomWorldEntity {

  private Vector velocity = new Vector();
	private FairyBehaviourState state = FairyBehaviourState.DECEND;
	private int lastStateChange = 0;

	public FairyWorldEntity(Location loc) {
		super("fairy", "Fairy", loc);
	}

	@Override
	public void update(int tick) {
		if (tick == 0) lastStateChange++;
		move(tick);
		render();
	}

	@Override
	protected void beforeDestroy() {
	}
	
	private void render() {
		switch (this.state) {
			case FLIGHT:
				getLocation().getWorld().spawnParticle(Particle.END_ROD, getLocation(), 1, 0.1, 0.1, 0.1, 0, null, true);
				return;
			default:
				getLocation().getWorld().spawnParticle(Particle.END_ROD, getLocation(), 1, 0, 0, 0, 0, null, true);
				return;
		}
	}
	
	private void move(int tick) {
		double highY = this.getLocation().getWorld().getHighestBlockYAt(this.getLocation());
		double delta = this.getLocation().getY() - highY;
		double yshift = 0;
		switch (this.state) {
			case IDLE:
				this.velocity.multiply(.9);
				if (lastStateChange > 2 && tick % 20 == 0) {
					if (Math.random() < .3) switchState(FairyBehaviourState.SEARCH);
				}
				break;

			case SEARCH:
				if (delta < 2) {
					yshift += Math.max(0, delta) / 4;
				}
				if (delta > 6) {
					yshift -= Math.max(2, delta - 6) / 4;
				}
				this.velocity.add(new Vector(
					(Math.random() - .5) / 15,
					(Math.random() - .5 + yshift) / 15,
					(Math.random() - .5) / 15
				));
				this.velocity.normalize().multiply(.1);
				if (lastStateChange > 5 && tick % 10 == 0) {
					if (Math.random() < .2) switchState(FairyBehaviourState.IDLE);
				}
				break;

			case FLIGHT:
				if (delta < 2) {
					yshift += Math.max(0, delta) / 4;
				}
				if (delta > 6) {
					yshift -= Math.max(2, delta - 6) / 4;
				}
				this.velocity.add(new Vector(
					(Math.random() - .5) / 15,
					(Math.random() - .5 + yshift) / 15,
					(Math.random() - .5) / 15
				));
				Entity closest = null;
				double cdist = 100;
				for (Entity e : this.getLocation().getWorld().getNearbyEntities(getLocation(), 10, 10, 10)) {
					double dist = e.getLocation().distance(getLocation());
					if (!e.getType().equals(EntityType.PLAYER)) dist *= 3;
					if (dist < cdist) {
						cdist = dist;
						closest = e;
					}
				}
				if (closest != null) {
					this.velocity.add(getLocation().clone().subtract(closest.getLocation()).toVector().normalize().multiply(.5));
				}
				this.velocity.normalize().multiply(.25);
				if (lastStateChange > 10 && cdist > 20) {
					if (Math.random() < .5) switchState(FairyBehaviourState.IDLE);
				}
				break;
				
			case DECEND:
				this.velocity.setY(-Math.max(.2, delta / 50));
				if (delta < 4) switchState(FairyBehaviourState.SEARCH);
				break;
				
			case ASCEND:
				this.velocity.setY(Math.max(.2, delta / 50));
				if (delta > 200) this.destroy();
				break;
		}
		
		long time = getLocation().getWorld().getTime();
		if (time > 22000) {
			double timedelt = 1 - Math.max(1, (23400 - time) / 1400);
			if (Math.random() < timedelt) switchState(FairyBehaviourState.ASCEND);
		}
		
		this.getLocation().add(this.velocity);

		// Correct position
		if (!this.getLocation().getBlock().isPassable()) {
			Vector d = this.velocity.clone().multiply(.1);
			int it = 0;

			while (!this.getLocation().getBlock().isPassable() && it++ < 10)
				this.getLocation().subtract(d);
		}
	}
	
	public void switchState(FairyBehaviourState newState) {
		this.state = newState;
		this.lastStateChange = 0;
	}
	
	//
	
	public enum FairyBehaviourState {
		IDLE, // Idling
		SEARCH, // Searching
		FLIGHT, // When moving player nearby
		DECEND, // When getting spawned
		ASCEND, // When it's getting daytime
	}
  
}
