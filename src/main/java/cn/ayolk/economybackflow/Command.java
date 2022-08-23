package cn.ayolk.economybackflow;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static cn.ayolk.economybackflow.Main.*;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, String[] args) {


        if (args.length == 0 || args[0].equals("help")) {
            sender.sendMessage("=                       =");
            sender.sendMessage("/ebf give [player] [amounts] - 给予玩家金币.");
            sender.sendMessage("/ebf help - 查看帮助.");
            sender.sendMessage("/ebf versions - 查看版本");
            sender.sendMessage("=                       =");
            return true;
        }
        if (args[0].equals("versions")) {
            sender.sendMessage("版本 1.0.0");
        }
        if (!(sender instanceof Player) || (sender instanceof Player && perms.has(((Player) sender).getPlayer(),"ebf.DoNotGivePlayer.give"))) {
            if (args[0].equalsIgnoreCase("give") && args.length == 3) {
                String players = args[1];

                UUID uuid = Bukkit.getOfflinePlayer(players).getUniqueId();
                OfflinePlayer offlinePlayer1 = Bukkit.getOfflinePlayer(uuid);
                double cost = Double.parseDouble(args[2]);
                if (econ.has(offlinePlayer, cost)) {
                    CommandEvent.giveC2(offlinePlayer, cost);
                    CommandEvent.giveC(offlinePlayer1, cost);
                    offlinePlayer1.getPlayer().sendRawMessage("收到来自 Server 的 " + cost + " 金币.");
                    return true;
                } else if (!econ.has(offlinePlayer, cost)) {
                    offlinePlayer1.getPlayer().sendRawMessage("服务器中央银行没钱了,取消本次金币奖励.");
                    return true;
                }
                return true;
            }else{
                sender.sendMessage("使用方法/ebf give [player] [amounts]");
            }
        }
/*
        if (args[0].equalsIgnoreCase("give") && args.length == 3 && perms.has((Player) sender, "ebf.DoNotGiveToPlayer.give")) {
            return false;
        }else{

        }

 */


        /*
        if((sender instanceof Player)){
            return false;
         */
        //重写给钱
        return true;
    }
}


