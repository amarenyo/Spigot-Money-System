package ch.amarenyo.moneyPlugin.listeners;

import ch.amarenyo.moneyPlugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            Main main = Main.getPlugin(Main.class);
            String uuid = player.getUniqueId().toString();
            main.getConfig().set("money." + uuid, 2000);
            player.sendMessage("You got 2000 as a starter bonus!");
            main.saveConfig();
            main.reloadConfig();
        }
    }
}
