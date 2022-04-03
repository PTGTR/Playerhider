package eu.acewolf.playerhider.listener;

import eu.acewolf.playerhider.Playerhider;
import eu.acewolf.playerhider.mysql.DatenbankMethods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class QuitListener implements Listener {

    DatenbankMethods datenbankMethods = new DatenbankMethods();

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        datenbankMethods.setMode(e.getPlayer().getUniqueId().toString(), Playerhider.playerhidechach.get(e.getPlayer()));
    }
}
