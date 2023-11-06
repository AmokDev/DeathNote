package dev.amok.DeathNote.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Bukkit;

import dev.amok.DeathNote.Deaths;
import dev.amok.DeathNote.Plugin;

import java.util.Arrays;
import java.util.List;

public class readNoteEvent implements Listener  {

    public static String name;
    public static String cause;

    @EventHandler
    public void readNote(PlayerEditBookEvent event) {
        BookMeta meta = event.getNewBookMeta();
        Player s = (Player) event.getPlayer();
        FileConfiguration cfg = Plugin.getInstance().getConfig();

        String tag = cfg.getString("_translation._other.tag");
        String not_enough_rights = cfg.getString("_translation._events.write_victim.not_enough_rights");
        String incorrect_input = cfg.getString("_translation._events.write_victim.incorrect_input");
        String victim_offline = cfg.getString("_translation._events.write_victim.victim_offline");
        String inaccessible_game_mode = cfg.getString("_translation._events.write_victim.inaccessible_game_mode");
        String cant_find_a_way_of_death = cfg.getString("_translation._events.write_victim.cant_find_a_way_of_death");

        String incorrect_input_text = "\n" + tag + " " + incorrect_input + "\n";

        if (s.hasPermission("deathnote.kira") == false) {
            s.sendMessage("\n" + tag + " " + not_enough_rights + "\n");
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
                    s.sendMessage(incorrect_input_text);
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                s.sendMessage(incorrect_input_text);
                return;
            }
            try {
                name = page_meta.split(" by ")[0];
                cause = page_meta.split(" by ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                s.sendMessage(incorrect_input_text);
                return;
            }
            Player v = (Player) Bukkit.getPlayerExact(name);
            try {
                v.getArrowsInBody();
            } catch (Exception e) {
                s.sendMessage("\n" + tag + " " + victim_offline + "\n");
                return;
            }
            if (ists.equals(eq)) {
                if (v.getGameMode() != GameMode.SURVIVAL) {
                    s.sendMessage("\n" + tag + " " + inaccessible_game_mode + "\n");
                    return;
                }
                if (cause.equals("lightning")) {
                    Deaths.Lightning(v);
                } else if (cause.equals("guardian")) {
                    Deaths.Guardian(v);
                } else {
                    s.sendMessage("\n" + tag + " " + cant_find_a_way_of_death + "\n");
                    return;
                }
                
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                
                String page1 = meta.getPage(1);

                bookMeta.setTitle("kek");
                bookMeta.setDisplayName(meta.getDisplayName());
                bookMeta.setAuthor(s.getName());
                bookMeta.setLore(Arrays.asList("kira"));
                bookMeta.addPage(page1);

                book.setItemMeta(bookMeta);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Player ss = (Player) event.getPlayer();
                        for (int i = 0; i < ss.getInventory().getSize(); i++) {
                            if (ss.getInventory().getItem(i) == null) {
                                ;
                            } else if (s.getInventory().getItem(i).getType().equals(Material.WRITABLE_BOOK)) {
                                if (s.getInventory().getItem(i).getItemMeta().getLore() != null) {
                                    if (s.getInventory().getItem(i).getItemMeta().getLore().get(0).equals("kira")) {
                                        ss.getInventory().setItem(i, book);
                                    }
                                }
                            }
                        }
                    }
                }.runTaskLater(Plugin.getInstance(), 1);                
            }
        }
    }
}
