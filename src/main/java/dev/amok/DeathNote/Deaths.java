package dev.amok.DeathNote;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Deaths {

    public static void Lightning(Player v) {
        FileConfiguration cfg = Plugin.getInstance().getConfig();
        String tag = cfg.getString("_translation._other.tag");
        String was_killed_by = cfg.getString("_translation._deaths.was_killed_by");
        String name_color = cfg.getString("_translation._deaths.name_color");
        String murderer_color = cfg.getString("_translation._deaths.murderer_color");
        String murder_lightning = cfg.getString("_translation._deaths.murders.lightning");

        new BukkitRunnable() {    
            @Override
            public void run() {
                v.getWorld().strikeLightningEffect(v.getLocation());
                v.damage(15000);
                Bukkit.broadcastMessage(tag + " " + name_color + v.getName() + " " + was_killed_by + " " + murderer_color + murder_lightning);
            }
        }.runTaskLater(Plugin.getInstance(), 1 * 20);
    }

    public static void Guardian(Player v) {
        FileConfiguration cfg = Plugin.getInstance().getConfig();
        String tag = cfg.getString("_translation._other.tag");
        String was_killed_by = cfg.getString("_translation._deaths.was_killed_by");
        String name_color = cfg.getString("_translation._deaths.name_color");
        String murderer_color = cfg.getString("_translation._deaths.murderer_color");
        String murder_guardian = cfg.getString("_translation._deaths.murders.guardian");

        v.spawnParticle(Particle.MOB_APPEARANCE, v.getEyeLocation(), 1, 0, 0, 0, 0);
        v.getWorld().createExplosion(v.getLocation().getX(), v.getLocation().getY(), v.getLocation().getZ(), 0, false, false);
        v.spawnParticle(Particle.TOTEM, v.getEyeLocation(), 50, 5, 3, 2, 0);
        new BukkitRunnable() {    
            @Override
            public void run() {
                v.damage(15000);
                Bukkit.broadcastMessage(tag + " " + name_color + v.getName() + " " + was_killed_by + " " + murderer_color + murder_guardian);
            }
        }.runTaskLater(Plugin.getInstance(), 1 * 20);
    }

}
