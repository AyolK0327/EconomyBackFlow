package cn.ayolk.economybackflow;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import static cn.ayolk.economybackflow.Main.econ;
import static cn.ayolk.economybackflow.Main.offlinePlayer;

public class CommandEvent extends JavaPlugin {
    public static void getDeposit(OfflinePlayer offlinePlayer, double cost){
        econ.depositPlayer(offlinePlayer,cost);
    }
    public static  void getWithdraw(OfflinePlayer offlinePlayer, double cost){
        econ.withdrawPlayer(offlinePlayer,cost);
    }
}
