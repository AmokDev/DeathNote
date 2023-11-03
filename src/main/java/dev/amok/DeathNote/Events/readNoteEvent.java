package dev.amok.DeathNote.Events;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
// import org.bukkit.inventory.ItemStack;
// import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
// import org.bukkit.entity.EntityType;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Bukkit;
// import org.bukkit.Sound;

import dev.amok.DeathNote.Deaths;
import dev.amok.DeathNote.Plugin;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.hover.content.Content;

import java.util.Arrays;
import java.util.List;

public class readNoteEvent implements Listener  {

    public static String name;
    public static String cause;

    @EventHandler
    public void readNote(PlayerEditBookEvent event) {
        BookMeta meta = event.getNewBookMeta();
        Player s = (Player) event.getPlayer();

        if (s.hasPermission("deathnote.kira") == false) {
            s.sendMessage("§7Death Note §8>> §7У Вас недостаточно прав для использования тетради смерти!");
            return;
        }
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            String ists = lore.get(0);
            String eq = "kira";
            List<String> page = meta.getPages();
            String page_meta = page.get(0);
            try {
                if (!page_meta.split(" ")[1].equals("by")) {
                    s.sendMessage("§7Death Note §8>> §7Неправильный ввод! Пример - \"Name by lightning\"");
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                s.sendMessage("§7Death Note §8>> §7Неправильный ввод! Пример - \"Name by lightning\"");
                return;
            }
            try {
                name = page_meta.split(" by ")[0];
                cause = page_meta.split(" by ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                s.sendMessage("§7Death Note §8>> §7Неправильный ввод! Пример - \"Name by lightning\"");
                return;
            }
            Player v = (Player) Bukkit.getPlayerExact(name);
            try {
                v.getArrowsInBody();
            } catch (Exception e) {
                s.sendMessage("§7Death Note §8>> §7Ваша цель не в сети!");
                return;
            }
            if (ists.equals(eq)) {
                if (v.getGameMode() != GameMode.SURVIVAL) {
                    s.sendMessage("§7Death Note §8>> §7Игрок находится в недоступном для тетради режиме игры!");
                    return;
                }
                if (cause.equals("lightning")) {
                    Deaths.Lightning(v);
                } else if (cause.equals("guardian")) {
                    Deaths.Guardian(v);
                } else {
                    s.sendMessage("§7Death Note §8>> §7Не нашел подходящего способа смерти в своей копилке! Попробуйте найти что-то нужное по комманде /kills");
                    return;
                }
                
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                
                // String page1 = meta.getPage(1);

                bookMeta.setTitle(meta.getDisplayName());
                bookMeta.setDisplayName(meta.getDisplayName());
                bookMeta.setAuthor(s.getName());
                bookMeta.setLore(Arrays.asList("kira"));

                bookMeta.addPage("Any custom text");

                book.setItemMeta(bookMeta);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Player ss = (Player) event.getPlayer();
                        for (int i = 0; i < ss.getInventory().getSize(); i++) {
                            if (ss.getInventory().getItem(i) == null) {
                                ;
                            } else if (s.getInventory().getItem(i).getType().equals(Material.WRITABLE_BOOK)) {
                                Bukkit.getLogger().info(s.getInventory().getItem(i).getItemMeta().getLore().get(0));
                                ss.getInventory().setItem(i, book);
                            }
                        }
                    }
                }.runTaskLater(Plugin.getInstance(), 1);                
            }
        }
    }
}
