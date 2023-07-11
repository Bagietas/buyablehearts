package cf.bagieta.hearts;

import cf.bagieta.hearts.commands.BuyHeartCommand;
import cf.bagieta.hearts.listeners.EventPlayerDeath;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hearts extends JavaPlugin {
    public Essentials esen;

    public static Hearts INSTANCE;
    @Override
    public void onEnable() {
        esen = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

        this.saveDefaultConfig();
        INSTANCE = this;

        getCommand("buyheart").setExecutor(new BuyHeartCommand());

        getServer().getPluginManager().registerEvents(new EventPlayerDeath(), this);
    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
