package cn.ayolk.economybackflow;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static cn.ayolk.economybackflow.Main.*;


public class BalanceChangeEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWithdraw(CMIUserBalanceChangeEvent event) {

        if(perms.has((Player)offlinePlayer,"ebf.ServerBank")){return;}
        //withdraw的钱
        double cost = event.getFrom() - event.getTo();
        if(event.getActionType().equals("Withdraw") && event.getSource() == null) {
            econ.depositPlayer(offlinePlayer, cost);

        }
    }
}

