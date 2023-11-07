package dev.amok.DeathNote;

import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import dev.amok.DeathNote.Database.SQLite;
import dev.amok.DeathNote.Initializers.CommandInit;
import dev.amok.DeathNote.Initializers.EventInit;

public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    private static Plugin instance;

    @Override
    public void onEnable() {
        Plugin.plugin = this;
        instance = this;
        getLogger().info("Loading config...");
        this.saveDefaultConfig();
        getLogger().info("Loading database...");
        try { SQLite.getConnection(); } catch (SQLException e) { e.printStackTrace(); }
        try { SQLite.createDB(); } catch (SQLException e) { e.printStackTrace(); }
        CommandInit.init();
        EventInit.init();
        getLogger().info("Plugin started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Stopping database...");
        try { SQLite.closeConnection(); } catch (SQLException e) { e.printStackTrace(); }
        getLogger().info("Plugin stopped!");
    }

    public static Plugin getInstance() {
        return instance;
        }
}
