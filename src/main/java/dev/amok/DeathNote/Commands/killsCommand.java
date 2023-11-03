package dev.amok.DeathNote.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class killsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        Player s = (Player) sender;
        s.sendMessage("§7Death Note §8>> §7Список причин для смертей\n • lightning - удар молнией\n • guardian - убийство стражем");
        return true;
    }

}
