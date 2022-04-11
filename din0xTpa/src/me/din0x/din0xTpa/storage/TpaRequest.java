package me.din0x.din0xTpa.storage;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TpaRequest {

    public Player CmdUser;
    public Player Target;
    public int Time;


    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("din0xTpa");
    FileConfiguration config = this.plugin.getConfig();

    public TpaRequest(Player cmdUser, Player target) {
        Time = config.getInt("TpaRequestExpireTime");
        CmdUser = cmdUser;
        Target = target;
    }
}
