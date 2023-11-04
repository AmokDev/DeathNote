package dev.amok.DeathNote.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class kiraCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        Player s = (Player) sender;
        int inventorySize = 9;
        String inventoryName = "§x§4§b§0§0§0§0§lК§x§4§f§0§0§0§0§lи§x§3§8§0§0§0§0§lр§x§6§a§0§0§0§0§lа";
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
        meta.setDisplayName("§x§5§3§0§0§0§0§lD§x§3§8§1§9§1§9§le§x§2§9§2§9§2§9§la§x§2§c§2§c§2§c§lt§x§3§9§1§7§1§7§lh §x§4§2§0§0§0§0§lN§x§3§0§0§0§0§0§lo§x§2§b§1§2§1§2§lt§x§2§f§2§f§2§f§le");
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
