package dev.amok.DeathNote;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Deaths {

    public static void Lightning(Player v) {
        new BukkitRunnable() {    
            @Override
            public void run() {
                v.getWorld().strikeLightningEffect(v.getLocation());
                v.damage(15000);
                Bukkit.broadcastMessage("§7Death Note §8>> §4" + v.getName() + " §7был убит §eмолнией");
            }
        }.runTaskLater(Plugin.getInstance(), 1 * 20);
    }

    public static void Guardian(Player v) {
        v.spawnParticle(Particle.MOB_APPEARANCE, v.getEyeLocation(), 1, 0, 0, 0, 0);
        v.getWorld().createExplosion(v.getLocation().getX(), v.getLocation().getY(), v.getLocation().getZ(), 0, false, false);
        v.spawnParticle(Particle.TOTEM, v.getEyeLocation(), 50, 5, 3, 2, 0);
        new BukkitRunnable() {    
            @Override
            public void run() {
                v.damage(15000);
                Bukkit.broadcastMessage("§7Death Note §8>> §4" + v.getName() + " §7был убит §eстражем");
            }
        }.runTaskLater(Plugin.getInstance(), 1 * 20);
    }

}
