package cn.ayolk.economybackflow;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static cn.ayolk.economybackflow.Main.*;


public class BalanceChangeEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWithdraw(CMIUserBalanceChangeEvent event) {
        if(event.getUser().getPlayer().getName().equals(offlinePlayer.getName())){return;}
        //withdraw的钱
        double Cost = event.getFrom() - event.getTo();
        if(event.getActionType().equals("Withdraw") && event.getSource() == null) {
            econ.depositPlayer(offlinePlayer, Cost);
        }
    }
}

