package me.din0x.din0xTpa;

import me.din0x.din0xTpa.commands.TpaAcceptCommand;
import me.din0x.din0xTpa.commands.TpaCancelCommand;
import me.din0x.din0xTpa.commands.TpaCommand;
import me.din0x.din0xTpa.commands.TpaDenyCommand;
import me.din0x.din0xTpa.storage.TpaRequestManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Din0xTpa extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
        getCommand("tpadeny").setExecutor(new TpaDenyCommand());
        getCommand("tpacancel").setExecutor(new TpaCancelCommand());

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                TpaRequestManager.UpdateTime(300);
                //getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Requests has been updated.");
            }
        }, 0L, 300L);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[din0xTpa] is enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[din0xTpa] is disabled");
    }
}
