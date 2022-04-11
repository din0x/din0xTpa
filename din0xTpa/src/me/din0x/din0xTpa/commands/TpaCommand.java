package me.din0x.din0xTpa.commands;

import me.din0x.din0xTpa.storage.TpaRequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class TpaCommand implements CommandExecutor {

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("din0xTpa");
    FileConfiguration config = this.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) { //Sprawdza czy gracz użył komendy.
            getServer().getConsoleSender().sendMessage(ChatColor.RED + config.getString("ConsoleCantUse"));

        } else {
            if (cmd.getName().equalsIgnoreCase("tpa")) {
                if (!(args.length >= 1)) { //Sprawdza czy zostały podane argumenty.
                    player.sendMessage(ChatColor.RED + config.getString("InvalidTpaCommandUsage"));

                } else {
                    Player target = getServer().getPlayer(args[0]);
                    if (target == null) { //Sprawdza czy gracz jest online.
                        player.sendMessage(ChatColor.RED + config.getString("PlayerIsOffline"));

                    } else {
                        TpaRequestManager.NewRequest(player, target);
                        player.sendMessage(ChatColor.GRAY + this.plugin.getConfig().getString("TpaRequestSent") + target.getName());
                        target.sendMessage(ChatColor.GRAY + player.getName() + config.getString("TpaReceivedRequest"));
                    }
                }
            }
        }
        return true;
    }
}
