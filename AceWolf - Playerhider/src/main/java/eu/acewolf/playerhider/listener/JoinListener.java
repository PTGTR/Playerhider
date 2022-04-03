package eu.acewolf.playerhider.listener;

import eu.acewolf.playerhider.Playerhider;
import eu.acewolf.playerhider.mysql.DatenbankMethods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.Date;

public class JoinListener implements Listener {

    DatenbankMethods datenbankMethods = new DatenbankMethods();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        datenbankMethods.addDatabase(e.getPlayer().getUniqueId().toString());
        Playerhider.playerhidechach.put(e.getPlayer(), datenbankMethods.getMode(e.getPlayer().getUniqueId().toString()));

            for (Player all : Bukkit.getOnlinePlayers()) {
               if (Playerhider.playerhidechach.get(all) == 2){
                   if (e.getPlayer().hasPermission("system.team")) {

                   } else {
                       all.hidePlayer(e.getPlayer());
                   }
                }
            }
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (Playerhider.playerhidechach.get(all) == 3){
                all.hidePlayer(e.getPlayer());
            }
        }

    }

}
