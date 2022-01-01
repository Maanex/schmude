package me.maanex.schmude.features.technical;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import me.maanex.schmude.Main;

public class ForceResoucrepack implements Listener {

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> e.getPlayer().setResourcePack(Main.RESOURCEPACK_URL), 20);
	}

	@EventHandler
	public void packDeny(PlayerResourcePackStatusEvent e) {
		final String HEY_RAW =
		      "               "
			+ "\n # # ### # # # "
			+ "\n # # #   # # # "
			+ "\n ### ##  ### # "
			+ "\n # # #    #    "
			+ "\n # # ###  #  # "
			+ "\n               ";
		String HEY = "";
		for (String line : HEY_RAW.split("\n")) {
			for (char c : line.toCharArray()) {
				HEY += c == '#' ? "§a" : "§8";
				HEY += "█";
			}
			HEY += "\n";
		}
		if (e.getStatus().equals(Status.DECLINED))
      e.getPlayer().kickPlayer(HEY + "\n\n§aWir haben jetzt eigene Blöcke und Items auf dem Server.\nDamit du diese sehen kannst, musst du\nallerdings das Resourcepack herunterladen!\n\n§fKlingt nach nem Deal?\nKlicke in der Serverliste auf diesen Server, bearbeiten\nund setze \"Server-Resoucepacket\" auf Aktiviert!\n\n§7Gibts Probleme oder Fragen? Einfach bei Andi melden, danke :)");
	}
  
}
