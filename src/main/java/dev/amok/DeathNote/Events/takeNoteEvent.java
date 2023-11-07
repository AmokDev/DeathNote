package dev.amok.DeathNote.Events;

import java.sql.SQLException;
import java.util.Arrays;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.amok.DeathNote.Plugin;
import dev.amok.DeathNote.Database.SQLite;

import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

public class takeNoteEvent implements Listener  {
    
    @EventHandler
    public void readNote(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player s = (Player) event.getWhoClicked();
            FileConfiguration cfg = Plugin.getInstance().getConfig();
            ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
            Inventory inv = s.getInventory();
            ItemMeta meta = item.getItemMeta();

            String tag = cfg.getString("_translation._other.tag");
            String book_name = cfg.getString("_translation._other.death_note_name");
            String inv_name = cfg.getString("_translation._other.inventory_command_kira_name");
            String death_note_picked_up = cfg.getString("_translation._events.give_death_note.death_note_picked_up");
            int kira_cooldown = cfg.getInt("kira_cooldown");

            meta.setDisplayName(book_name);
            meta.setLore(Arrays.asList("kira"));
            item.setItemMeta(meta);
            if (event.getClickedInventory() != null) {
                if (event.getView().getTitle().equals(inv_name)) {
                    if (event.isRightClick() || event.isLeftClick()) {
                        event.setCancelled(true);
                        inv.addItem(item);
                        s.sendMessage("\n" + tag + " " + death_note_picked_up + "\n§7");
                        s.closeInventory();
                        long newdata = System.currentTimeMillis() / 1000 + kira_cooldown;
                        try {
                            SQLite.updatePlayerData(s.getName(), newdata * 1000);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
