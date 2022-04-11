package me.din0x.din0xTpa.commands;

import me.din0x.din0xTpa.storage.TpaRequest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static me.din0x.din0xTpa.storage.TpaRequestManager.Requests;
import static org.bukkit.Bukkit.getServer;

public class TpaCancelCommand implements CommandExecutor {

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("din0xTpa");
    FileConfiguration config = this.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) { //Sprawdza czy gracz użył komendy.
            getServer().getConsoleSender().sendMessage(ChatColor.RED + config.getString("ConsoleCantUse"));
            return true; }

        Player target = getServer().getPlayer(args[0]);

        if (cmd.getName().equalsIgnoreCase("tpacancel")){
            for (TpaRequest r: Requests) {
                if (r.CmdUser == player && r.Target == target && r.Time > 0){
                    r.Time = -1;
                    player.sendMessage(ChatColor.RED + config.getString( "TpaCancel"));
                    r.CmdUser.sendMessage(ChatColor.RED + config.getString( "TpaCanceled"));
                }else{
                    player.sendMessage(ChatColor.RED + config.getString( "NoRequest"));
                }
            }
        }
        return true;
    }
}
