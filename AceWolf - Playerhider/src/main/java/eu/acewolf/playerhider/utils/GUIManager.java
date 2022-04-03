package eu.acewolf.playerhider.utils;

import eu.acewolf.playerhider.Playerhider;
import eu.acewolf.playerhider.mysql.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class GUIManager {

    FileManager fileManager = new FileManager();

    public void openInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, fileManager.getConfiguration().getString("gui.title").replace("&", "§"));
        int i;
        for (i = 0; i < 27; i++) {
            inv.setItem(i, ItemManager.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, 0, "§a"));
        }

        if (Playerhider.playerhidechach.get(player) == 1){
            inv.setItem(10, ItemManager.createItemEntchants(Material.GREEN_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.allplayers").replace("&", "§")));
        } else {
            inv.setItem(10, ItemManager.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.allplayers").replace("&", "§")));
        }
        if (Playerhider.playerhidechach.get(player) == 2){
            inv.setItem(13, ItemManager.createItemEntchants(Material.MAGENTA_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.onlyteam").replace("&", "§")));
        } else {
            inv.setItem(13, ItemManager.createItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.onlyteam").replace("&", "§")));
        }
        if (Playerhider.playerhidechach.get(player) == 3){
            inv.setItem(16, ItemManager.createItemEntchants(Material.RED_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.nobody").replace("&", "§")));
        } else {
            inv.setItem(16, ItemManager.createItem(Material.RED_STAINED_GLASS_PANE, 1, 0, fileManager.getConfiguration().getString("gui.nobody").replace("&", "§")));
        }

        player.openInventory(inv);
    }
}
