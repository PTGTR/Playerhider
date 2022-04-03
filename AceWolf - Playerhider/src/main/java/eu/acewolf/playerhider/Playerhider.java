package eu.acewolf.playerhider;

import eu.acewolf.playerhider.command.playerhider_command;
import eu.acewolf.playerhider.listener.InventoryClickListener;
import eu.acewolf.playerhider.listener.JoinListener;
import eu.acewolf.playerhider.listener.QuitListener;
import eu.acewolf.playerhider.mysql.FileManager;
import eu.acewolf.playerhider.mysql.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.logging.Logger;

public class Playerhider extends JavaPlugin {

    public static Playerhider instance;
    public static HashMap<Player, Integer> playerhidechach = new HashMap<>();

    FileManager fileManager = new FileManager();
    MySQL mySQL = new MySQL();


    @Override
    public void onEnable() {
        instance = this;
        fileManager.createFile();
        mySQL.readMySQL();
        mySQL.connect();
        this.loadDatenbank();

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);

        getCommand("playerhider").setExecutor(new playerhider_command());

    }

    public static Playerhider getInstance() {
        return instance;
    }

    private void loadDatenbank() {
        try {
            PreparedStatement ps1 = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerhidermanager (UUID VARCHAR(64),MODE int)");
            ps1.executeUpdate();
        } catch (Exception ignored) {}
    }
}
