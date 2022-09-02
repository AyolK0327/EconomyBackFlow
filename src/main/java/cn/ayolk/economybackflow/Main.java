package cn.ayolk.economybackflow;

import com.Zrips.CMI.CMI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getOfflinePlayer;
import static org.bukkit.Bukkit.getOfflinePlayers;

public class Main extends JavaPlugin{
    static FileConfiguration config;
    static Permission perms = null;
    static Economy econ = null;
    static String ServerBank;
    static boolean ResidenceEnable;
    //static ResidenceApi resAPI;
    static Plugin resPlug;
    static OfflinePlayer offlinePlayer;
    static OfflinePlayer offlinePlayerID;

    static String Prefix;
    static String MeTips;
    static String MeGive;
    static String MeHelp;
    static String MeVer;
    private static final Logger log = Logger.getLogger("Minecraft");
    @Override
    public void onLoad() {
        reloadConfig();
        saveDefaultConfig();
        config = getConfig();
        Prefix = Objects.requireNonNull(config.getString("Message.Prefix")).replace("&","§");
        MeGive = Objects.requireNonNull(config.getString("Message.help.Give")).replace("&","§");
        MeTips = Objects.requireNonNull(config.getString("Message.help.tips")).replace("&","§");
        MeHelp = Objects.requireNonNull(config.getString("Message.help.help")).replace("&","§");
        MeVer = Objects.requireNonNull(config.getString("Message.help.Version")).replace("&","§");
        ServerBank = config.getString("ServerBank");
        ResidenceEnable = config.getBoolean("Residence.enable");
        UUID id = UUID.fromString("2858b29e-906a-3526-966d-32cbf6012bf3");
        offlinePlayer = getOfflinePlayer(id);
        getLogger().info("插件正在载入...");
        super.onLoad();
        Plugin cmiPlug =getServer().getPluginManager().getPlugin("CMI");
        if(cmiPlug != null){
           CMI cmiAPI = CMI.getInstance();
        }
        if (!setupEconomy()) {
            getLogger().info("vault加载失败！");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
    }

    @Override
    public void onEnable() {
        this.getCommand("economybackflow").setExecutor(new Command());
        getCommand("economybackflow").setTabCompleter(new TabCompleter());
        this.getServer().getPluginManager().registerEvents(new BalanceChangeEvent(),this);
        getLogger().info("插件加载成功!");
        super.onEnable();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }
}