package dev.amok.DeathNote.Events;

import java.util.Arrays;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

public class takeNoteEvent implements Listener  {
    
    @EventHandler
    public void readNote(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player s = (Player) event.getWhoClicked();
            ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
            Inventory inv = s.getInventory();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§x§5§3§0§0§0§0§lD§x§3§8§1§9§1§9§le§x§2§9§2§9§2§9§la§x§2§c§2§c§2§c§lt§x§3§9§1§7§1§7§lh §x§4§2§0§0§0§0§lN§x§3§0§0§0§0§0§lo§x§2§b§1§2§1§2§lt§x§2§f§2§f§2§f§le");
            meta.setLore(Arrays.asList("kira"));
            item.setItemMeta(meta);
            if (event.getClickedInventory() != null) {
                if (event.getView().getTitle().equals("§x§4§b§0§0§0§0§lК§x§4§f§0§0§0§0§lи§x§3§8§0§0§0§0§lр§x§6§a§0§0§0§0§lа")) {
                    if (event.isRightClick() || event.isLeftClick()) {
                        event.setCancelled(true);
                        inv.addItem(item);
                        s.sendMessage("§7Death Note §8>> §7Вы подобрали тетрадь смерти!");
                        s.closeInventory();
                    }
                }
            }
        }
    }
}
