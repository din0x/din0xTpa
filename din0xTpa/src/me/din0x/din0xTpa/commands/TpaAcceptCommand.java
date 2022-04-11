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

public class TpaAcceptCommand implements CommandExecutor {

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("din0xTpa");
    FileConfiguration config = this.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) { //Sprawdza czy gracz użył komendy.
            getServer().getConsoleSender().sendMessage(ChatColor.RED + config.getString("ConsoleCantUse"));

        } else {
            if (cmd.getName().equalsIgnoreCase("tpaaccept")){
                if (!(args.length >= 1)) { //Sprawdza czy zostały podane argumenty.
                    player.sendMessage(ChatColor.RED + config.getString("InvalidTpaCommandUsage"));

                } else {
                    Player CmdUser = getServer().getPlayer(args[0]);
                    if (CmdUser == null) {
                        player.sendMessage(ChatColor.RED + config.getString("PlayerIsOffline"));

                    } else {
                        if(TpaRequestManager.SearchReq(CmdUser, player).equals("Success")){
                            CmdUser.teleport(player);
                            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Tpa] Teleported " +
                                    CmdUser.getName() + " to " + player.getName());

                        } else if (TpaRequestManager.SearchReq(CmdUser, player).equals("Expired")){
                            player.sendMessage(ChatColor.RED + config.getString("RequestHasExpired"));

                        } else {
                            player.sendMessage(ChatColor.RED + config.getString("NoRequest"));
                        }
                    }
                }
            }
        }
        return true;
    }
}
