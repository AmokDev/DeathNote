package dev.amok.DeathNote.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import dev.amok.DeathNote.Plugin;

public class kiraCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        Player s = (Player) sender;
        FileConfiguration cfg = Plugin.getInstance().getConfig();
        int inventorySize = 9;
        String inventoryName = cfg.getString("_translation._other.inventory_command_kira_name");
        String book_name = cfg.getString("_translation._other.death_note_name");
        Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryName);

        // Glass Meta
        ItemStack red_glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta red_glass_meta = red_glass.getItemMeta();
        ItemMeta black_glass_meta = black_glass.getItemMeta();

        red_glass_meta.setDisplayName("§8*");
        black_glass_meta.setDisplayName("§x*");
        
        red_glass.setItemMeta(red_glass_meta);
        black_glass.setItemMeta(black_glass_meta);

        // Death Note
        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(book_name);
        item.setItemMeta(meta);

        inventory.setItem(0, red_glass);
        inventory.setItem(1, black_glass);
        inventory.setItem(2, red_glass);
        inventory.setItem(3, red_glass);
        inventory.setItem(4, item);
        inventory.setItem(5, red_glass);
        inventory.setItem(6, red_glass);
        inventory.setItem(7, black_glass);
        inventory.setItem(8, red_glass);
        s.openInventory(inventory);
        return true;
    }
}
