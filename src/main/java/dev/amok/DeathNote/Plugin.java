package dev.amok.DeathNote;

import org.bukkit.plugin.java.JavaPlugin;

import dev.amok.DeathNote.Initializers.CommandInit;
import dev.amok.DeathNote.Initializers.EventInit;


public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    private static Plugin instance;
    
    @Override
    public void onEnable() {
        Plugin.plugin = this;
        instance = this;
        this.saveDefaultConfig();
        CommandInit.init();
        EventInit.init();
        getLogger().info("Plugin started!");
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin stopped!");
    }

    public static Plugin getInstance() {
        return instance;
        }
}
