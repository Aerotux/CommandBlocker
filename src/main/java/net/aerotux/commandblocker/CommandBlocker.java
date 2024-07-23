package net.aerotux.commandblocker;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.aerotux.commandblocker.command.MainCommand;
import net.aerotux.commandblocker.event.CommandListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class CommandBlocker extends JavaPlugin
{

    @Getter
    private static CommandBlocker instance;

    private PaperCommandManager commandManager;

    public static CommandBlocker getInstance()
    {
        return instance;
    }

    public PaperCommandManager getCommandManager()
    {
        return commandManager;
    }

    @Override
    public void onEnable()
    {

        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getLogger().info("Enabled!");
        getLogger().info("Please join the Discord if there are issues!");

        registerCommands();
        registerEvents();

    }

    private void registerCommands()
    {
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new MainCommand());
    }

    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CommandListener(), this);
    }

    public List<String> getBlockedCommands()
    {
        return getConfig().getStringList("blocked-commands");
    }

}
