package net.aerotux.commandblocker.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.aerotux.commandblocker.CommandBlocker;
import net.aerotux.commandblocker.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("aerocommandblocker|acb")
@CommandPermission("aerocb.admin")
public class MainCommand extends BaseCommand
{

    CommandBlocker instance = CommandBlocker.getInstance();

    @Default
    public void main(Player player)
    {
        player.sendMessage(CC.translate("&cUse: /aerocommandblocker [argument]"));
    }

    @Subcommand("help")
    @CommandPermission("aerocb.admin")
    public void help(Player player)
    {
        player.sendMessage(CC.translate("&aAEROCOMMANDBLOCKER"));
        player.sendMessage(CC.translate("&7A light-weight command blocker plugin by Aerotux"));
        player.sendMessage("");
        player.sendMessage(CC.translate("&7/acb reload &8| &eReloads the config!"));
        player.sendMessage(CC.translate("&7/acb blocked-commands &8| &eLists all blocked commands!"));
        player.sendMessage(CC.translate("&7/acb add-command <command> &8| &eAdds a new command to the list!"));
    }

    @Subcommand("reload")
    @CommandPermission("aerocb.admin")
    public void reload(Player player)
    {
        player.sendMessage(CC.translate("&aYou have reloaded AeroCommandBlocker by Aerotux"));
        CommandBlocker.getInstance().reloadConfig();
    }

    @Subcommand("blocked-commands")
    @CommandPermission("aerocb.admin")
    public void listBlockedCommands(Player player)
    {
        player.sendMessage(CC.translate("&aBLOCKED COMMANDS"));
        player.sendMessage(CC.translate("&7Plugin by Aerotux"));
        player.sendMessage("");
        for (int i = 0; i < instance.getConfig().getStringList("blocked-commands").size(); i++)
        {
            player.sendMessage(CC.translate(instance.getConfig().getStringList("blocked-commands").get(i)));
        }
        player.sendMessage("");
    }

    @Subcommand("add-command")
    @CommandPermission("aerocb.admin")
    public void addBlockedCommand(Player player, String command)
    {
        if (!instance.getConfig().getStringList("blocked-commands").contains("/" + command))
        {
            instance.getConfig().getStringList("blocked-commands").add("/" + command);
            player.sendMessage(CC.translate("&aYou have blocked another command!"));
            instance.reloadConfig();
        }
        else
        {
            player.sendMessage(CC.translate("&cThis command is already blocked! Check the config for all blocked commands!"));
        }
    }

    @Subcommand("remove-command")
    @CommandPermission("aerocb.admin")
    public void removeBlockedCommand(Player player, String command)
    {
        if (instance.getConfig().getStringList("blocked-commands").contains("/" + command))
        {
            instance.getConfig().getStringList("blocked-commands").remove("/" + command);
            player.sendMessage(CC.translate("&aYou have successfully removed the command, &f/" + command + " &afrom blocked commands!"));
            instance.reloadConfig();
        }
        else
        {
            player.sendMessage(CC.translate("&cThis command is not blocked! Check the config for all blocked commands!"));
        }

    }

    @Subcommand("clear-commands")
    @CommandPermission("aerocb.admin")
    public void clearCommands(Player player)
    {
        instance.getConfig().getStringList("blocked-commands").clear();
        instance.getConfig().getStringList("blocked-commands").add("");
        player.sendMessage(CC.translate("&aYou have cleared all blocked commands!"));
        instance.reloadConfig();
        for (Player staff : Bukkit.getOnlinePlayers())
        {
            if (staff.hasPermission("aerocb.admin"))
            {
                staff.sendMessage(CC.translate("&a&lSTAFF &7" + player.getName() + " &ehas cleared all blocked-commands!"));
            }
        }
    }

}
