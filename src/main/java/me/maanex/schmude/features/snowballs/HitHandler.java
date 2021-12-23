package me.maanex.schmude.features.snowballs;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;


public class HitHandler implements Listener {
  
  @EventHandler
  public void onHit(ProjectileHitEvent e) {
    if (!(e.getEntity() instanceof Snowball))
      return;
    if (e.getHitEntity() == null || !(e.getHitEntity() instanceof Player))
      return;

    Player player = (Player) e.getHitEntity();
    player.damage(0.5D, e.getEntity());
  }

}
