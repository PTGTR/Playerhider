package eu.acewolf.playerhider.listener;

import eu.acewolf.playerhider.Playerhider;
import eu.acewolf.playerhider.mysql.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;


public class InventoryClickListener implements Listener {

    FileManager fileManager = new FileManager();

    @EventHandler
    public void onClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            if(e.getView().getTitle().equals(fileManager.getConfiguration().getString("gui.title").replace("&","§"))){
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(fileManager.getConfiguration().getString("gui.allplayers").replace("&","§"))){
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 5);
                if (Playerhider.playerhidechach.get(p) == 1){
                    p.sendMessage(fileManager.getConfiguration().getString("message.already_had_it").replace("&","§"));
                } else {
                    p.sendMessage(fileManager.getConfiguration().getString("message.switch.all").replace("&","§"));
                    Playerhider.playerhidechach.put(p, 1);
                    for (Player all : Bukkit.getOnlinePlayers()){
                        p.showPlayer(all);
                    }
                }
                p.closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(fileManager.getConfiguration().getString("gui.onlyteam").replace("&","§"))){
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 5);
                if (Playerhider.playerhidechach.get(p) == 2){
                    p.sendMessage(fileManager.getConfiguration().getString("message.already_had_it").replace("&","§"));
                } else {
                    p.sendMessage(fileManager.getConfiguration().getString("message.switch.onlyteam").replace("&","§"));
                    Playerhider.playerhidechach.put(p, 2);
                    for (Player all : Bukkit.getOnlinePlayers()){
                        if (all.hasPermission("system.team")) {

                        } else {
                            p.hidePlayer(all);
                        }
                    }
                }
                p.closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(fileManager.getConfiguration().getString("gui.nobody").replace("&","§"))){
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 5);
                if (Playerhider.playerhidechach.get(p) == 3){
                    p.sendMessage(fileManager.getConfiguration().getString("message.already_had_it").replace("&","§"));
                } else {
                    p.sendMessage(fileManager.getConfiguration().getString("message.switch.nobody").replace("&","§"));
                    Playerhider.playerhidechach.put(p, 3);
                    for (Player all : Bukkit.getOnlinePlayers()){
                        p.hidePlayer(all);
                    }
                }
                p.closeInventory();
            }
        }catch (Exception ignored){

        }
    }
}
