package ch.amarenyo.moneyPlugin.commands;

import ch.amarenyo.moneyPlugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player) {
            Main main = Main.getPlugin(Main.class);
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            int money = main.getMoney(uuid);
            sender.sendMessage("Your current balance is: " + money);

            return true;
        } else {
            sender.sendMessage("You must be a player to run this command!");
            return false;
        }
    }
}
