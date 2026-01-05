package ch.amarenyo.moneyPlugin.listeners;

import ch.amarenyo.moneyPlugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        Main main = Main.getPlugin(Main.class);
        
        if(!main.getConfig().contains("money." + uuid)) {
            main.getConfig().set("money." + uuid, 2000);
            player.sendMessage("You got 2000 as a starter bonus!");
            main.saveConfig();
            main.reloadConfig();
        }
    }
}
