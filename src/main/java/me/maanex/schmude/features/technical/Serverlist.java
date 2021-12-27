package me.maanex.schmude.features.technical;

import java.util.Calendar;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Serverlist implements Listener {

  public static final String[] MOTDS = new String[] {
    "\"Manno\" - Max Zyxz",
    "\"Es gibt auch Perlen in der Muschel\" - Mo",
    "\"Linus, du bist viel lauter als ein Cello\" - Jakob",
    "\"Isst der Billy zu viel Chilli, brennt am nächsten Tag der Willi\" - Max",
    "\"Geld ist teuer\" - Bastian",
    "\"Mein Tür ist ja die Fenster\" - MinerMomo",
    "minecraft.tude.club\nminecraft.tude.club",
    "nom nom nom",
    "E"
  };

  @EventHandler
  public void onPing(ServerListPingEvent e) {
    Random r = new Random(Calendar.getInstance().get(Calendar.DATE));
    e.setMaxPlayers(r.nextInt(999999));
    e.setMotd(MOTDS[r.nextInt(MOTDS.length)]);
  }
  
}
