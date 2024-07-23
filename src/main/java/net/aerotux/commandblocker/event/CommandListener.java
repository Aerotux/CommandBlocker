package net.aerotux.commandblocker.event;

import net.aerotux.commandblocker.CommandBlocker;
import net.aerotux.commandblocker.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandListener implements Listener
{

    CommandBlocker instance = CommandBlocker.getInstance();
    List<String> blockedCommands = instance.getBlockedCommands();
    List<String> pluginListCommands = new ArrayList<>();

    public List<String> blockedCommands()
    {
        blockedCommands.add("/pl");
        blockedCommands.add("/plugins");
        blockedCommands.add("/bukkit:pl");
        blockedCommands.add("/bukkit:plugins");
        blockedCommands.add("/bukkit:?");
        blockedCommands.add("/bukkit:help");
        blockedCommands.add("/?");
        blockedCommands.add("/help");
        return blockedCommands;
    }

    @EventHandler
    public void onCommandProcess(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String[] commandArgs = message.split(" ");

        if (commandArgs.length > 0)
        {
            String command = commandArgs[0].substring(1);

            if (!player.hasPermission("aerocb.bypass"))
            {
                if (blockedCommands.contains(command))
                {
                    player.sendMessage(CC.translate(instance.getConfig().getString("blocked-commands-message")));
                    event.setCancelled(true);
                }
            }
        }

        if (instance.getConfig().getStringList("blocked-commands").contains(blockedCommands()) || instance.getConfig().getStringList("blocked-commands").contains(blockedCommands))
        {
            if (!player.hasPermission("aerocb.admin"))
            {
                player.sendMessage(CC.translate(instance.getConfig().getString("blocked-commands-message")));
            }
            else
            {
                for (Plugin plugins : Bukkit.getServer().getPluginManager().getPlugins())
                {
                    player.sendMessage(CC.translate("&aPlease check your server console!"));
                    System.out.println(plugins);
                }
            }
        }
    }
}
