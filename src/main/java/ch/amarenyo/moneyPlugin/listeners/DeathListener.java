package ch.amarenyo.moneyPlugin.listeners;

import ch.amarenyo.moneyPlugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Main main = Main.getPlugin(Main.class);
        Player player = event.getEntity();
        String uuid = player.getUniqueId().toString();
        if(player.getKiller() != null) {
            Player killer = player.getKiller();
            String killeruuid = killer.getUniqueId().toString();
            if (main.getMoney(uuid) >= 70) {
                main.getConfig().set("money." + uuid, main.getMoney(uuid) - 70);
                main.getConfig().set("money." + killeruuid, main.getMoney(killeruuid) + 70);
                player.sendMessage("You lost 70!");
                killer.sendMessage("You earned 70!");
            } else {
                main.getConfig().set("money." + killeruuid, main.getMoney(killeruuid) + main.getMoney(uuid) );
                killer.sendMessage("You earned " + main.getMoney(uuid) + "!");
                main.getConfig().set("money." + uuid, 0);
                player.sendMessage("You lost everything!");
            }
            main.saveConfig();
            main.reloadConfig();
        }

    }
}
