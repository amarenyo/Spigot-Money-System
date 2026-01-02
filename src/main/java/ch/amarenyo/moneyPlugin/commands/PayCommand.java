package ch.amarenyo.moneyPlugin.commands;

import ch.amarenyo.moneyPlugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Main main = Main.getPlugin(Main.class);
        if(sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            if (args.length == 2) {
                int amount = Integer.parseInt(args[1]);
                Player target = Bukkit.getPlayerExact(args[0]);
                if(main.getMoney(uuid) >= amount) {
                    if (target == null) {
                        sender.sendMessage("Player is not online!");
                        return true;
                    } else {
                        String targetuuid = target.getUniqueId().toString();
                        main.getConfig().set("money." + uuid, main.getMoney(uuid) - amount);
                        main.getConfig().set("money." + targetuuid, main.getMoney(targetuuid) + amount);
                        main.saveConfig();
                        main.reloadConfig();
                        player.sendMessage("You sent " + amount + " to " + target.getName() + "!");
                        target.sendMessage("You received " + amount + " from " + player.getName() + "!");
                    }
                }
            } else {
                sender.sendMessage("Usage: /pay <player> <amount>");
            }
            return true;
        } else {
            sender.sendMessage("You must be a player to run this command!");
            return false;
        }
    }
}
