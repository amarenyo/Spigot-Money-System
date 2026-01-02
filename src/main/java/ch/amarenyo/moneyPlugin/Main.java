package ch.amarenyo.moneyPlugin;

import ch.amarenyo.moneyPlugin.commands.MoneyCommand;
import ch.amarenyo.moneyPlugin.commands.PayCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("pay").setExecutor(new PayCommand());
        this.getConfig().set("money.jason", 200);
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public int getMoney(String player) {
        return this.getConfig().getInt("money." + player);
    }
}
