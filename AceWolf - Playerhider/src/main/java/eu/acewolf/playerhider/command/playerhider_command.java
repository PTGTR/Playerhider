package eu.acewolf.playerhider.command;

import eu.acewolf.playerhider.Playerhider;
import eu.acewolf.playerhider.utils.GUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class playerhider_command implements CommandExecutor {

    GUIManager guiManager = new GUIManager();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        guiManager.openInventory(p);
        return false;
    }
}
