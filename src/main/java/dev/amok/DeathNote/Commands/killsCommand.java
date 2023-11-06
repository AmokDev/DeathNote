package dev.amok.DeathNote.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import dev.amok.DeathNote.Plugin;

import java.util.List;

public class killsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        Player s = (Player) sender;
        FileConfiguration cfg = Plugin.getInstance().getConfig();
        List<String> kills_text = cfg.getStringList("_translation._commands.kills");
        String tag = cfg.getString("_translation._other.tag");
        String kills_txt = "";

        // Kills iterator
        for (int i = 0; i < kills_text.size(); i++) {
            kills_txt += kills_text.get(i) + "\n";
        }

        // Message
        s.sendMessage("\n" + tag + " " + kills_txt);
        return true;
    }

}
