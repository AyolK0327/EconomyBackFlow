package cn.ayolk.economybackflow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    List<String> arguments = new ArrayList<>();
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            arguments.clear();
            arguments.add("help");
            arguments.add("versions");
            arguments.add("give");
            for (String s : arguments) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }
        if(args[0].equalsIgnoreCase("give") && args.length == 2){
            arguments.clear();
            Collection <? extends Player> player = sender.getServer().getOnlinePlayers();
            for (Player a : player){
                arguments.add(String.valueOf(a));
            }
        }
        if(args[0].equalsIgnoreCase("give") && args.length >= 3){
            arguments.clear();
        }

        /*
        else if (args.length == 2) {
            arguments.clear();
            arguments.add("add");
            arguments.add("clear");
            arguments.add("gui");
            arguments.add(sender.getName());
            if (args[0].equalsIgnoreCase("drop")) {
                for (String s : arguments) {
                    if (s.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(s);
                    }
                }
            }
            return result;
        }
        */
        return null;
    }

}
