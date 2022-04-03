package eu.acewolf.playerhider.mysql;

import eu.acewolf.playerhider.Playerhider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private final File getFile() {
        return new File("plugins/Playerhider", "config.yml");
    }

    public FileConfiguration getConfiguration() {
        return (FileConfiguration) YamlConfiguration.loadConfiguration(getFile());
    }

    public void createFile() {
        if (!Playerhider.getInstance().getDataFolder().exists()) {
            Playerhider.getInstance().getDataFolder().mkdir();
        }
        File file = getFile();
        if (!file.exists())
            try {
                file.createNewFile();
                FileConfiguration cfg = getConfiguration();
                cfg.set("mysql.host", "localhost");
                cfg.set("mysql.user", "username");
                cfg.set("mysql.datenbase", "username");
                cfg.set("mysql.passwort", "passwort");
                cfg.set("gui.title", "&7Playerhider");
                cfg.set("gui.allplayers", "&aAlle");
                cfg.set("gui.onlyteam", "&5Nur Teammitglieder");
                cfg.set("gui.nobody", "&cKeinem");
                cfg.set("message.already_had_it", "&cDu hast diese Option bereits gewählt.");
                cfg.set("message.switch.all", "&aDu siehst nun alle Spieler");
                cfg.set("message.switch.onlyteam", "&aDu siehst nun nur Teammitglieder");
                cfg.set("message.switch.nobody", "&aDu siehst nun niemanden");
                cfg.save(getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
