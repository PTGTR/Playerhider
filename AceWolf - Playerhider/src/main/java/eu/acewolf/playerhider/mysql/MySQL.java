package eu.acewolf.playerhider.mysql;

import eu.acewolf.playerhider.Playerhider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static String HOST;

    private static String DATABASE;

    private static String USER;

    private static String PASSWORD;

    private static Connection con;

    public static int MySQLSchedulerID;

    FileManager fileManager = new FileManager();
    public void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
                Bukkit.getConsoleSender().sendMessage("Verbindung zur MySQL ist erfolgreich. §7[§aPlayerhider§7]");
                onReconnectScheduler();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("Verbindung zur MySQL ist fehlgeschlagen! Fehler: "+ e.getMessage() + "  §7[§aPlayerhider§7]" );
            }
    }

    public static void disconnect() {
        try {
            if (con != null) {
                con.close();
                Bukkit.getConsoleSender().sendMessage("Verbindung zur MySQl wurde erfolgreich beendet.");
                if (Bukkit.getScheduler().isCurrentlyRunning(MySQLSchedulerID))
                    Bukkit.getScheduler().cancelTask(MySQLSchedulerID);
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Verbindung zur MySQL wurde abgebrochen! Fehler: "+ e.getMessage() + "  §7[§aPlayerhider§7]");
        }
    }

    private static void onReconnectScheduler() {
        MySQLSchedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)  Playerhider.getInstance(), new Runnable() {
            public void run() {
                onReconnect();
            }
        },  432000L, 432000L);
    }

    public static void onReconnect() {
        if (con != null)
            try {
                con.close();
                System.out.println("MySQL-Verbindung beendet!");
            } catch (SQLException e) {
                System.err.println("MySQL-Verbindung konnte nicht getrennt werden!");
                e.printStackTrace();
            }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Playerhider.getInstance(), new Runnable() {
            public void run() {
                try {
                    MySQL.con = DriverManager.getConnection("jdbc:mysql://" + MySQL.HOST + ":3306/" + MySQL.DATABASE + "?autoReconnect=true", MySQL.USER, MySQL.PASSWORD);
                    System.out.println("MySQL-Verbindung hergestellt!");
                } catch (SQLException e) {
                    System.err.println("MySQL-Verbindung konnte nicht hergestellt werden!");
                    e.printStackTrace();
                }
            }
        },1L);
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static Connection getConnection() {
        return con;
    }

    public void readMySQL() {
        FileConfiguration cfg = fileManager.getConfiguration();
        HOST = cfg.getString("mysql.host");
        USER = cfg.getString("mysql.user");
        DATABASE = cfg.getString("mysql.datenbase");
        PASSWORD = cfg.getString("mysql.passwort");
    }
}



