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
        if(player.getKiller() == null) {
            if (main.getMoney(uuid) >= 100) {
                main.getConfig().set("money." + uuid, main.getMoney(uuid) - 100);
            } else if (main.getMoney(uuid) > 0) {
                main.getConfig().set("money." + uuid, 0);
            }
        } else {
            Player killer = player.getKiller();
            String killeruuid = killer.getUniqueId().toString();
            if (main.getMoney(uuid) >= 100) {
                main.getConfig().set("money." + uuid, main.getMoney(uuid) - 100);
                main.getConfig().set("money." + killeruuid, main.getMoney(killeruuid) + 40);
                player.sendMessage("You lost 100!");
                killer.sendMessage("You got 40!");
            } else {
                main.getConfig().set("money." + uuid, 0);
                player.sendMessage("You lost everything!");
            }
        }
        main.saveConfig();
        main.reloadConfig();
    }
}
