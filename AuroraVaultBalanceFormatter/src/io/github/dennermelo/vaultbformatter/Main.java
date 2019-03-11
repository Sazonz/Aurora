package io.github.dennermelo.vaultbformatter;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;


public class Main extends JavaPlugin {
	public static Economy economia = null;
	
	@Override
	public void onEnable() {

		setupEconomy();
		registerCMD();
		registerEVT();
		saveDefaultConfig();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {
		getCommand("bal").setExecutor(new EconomyCommand());
		

	}
	private void registerEVT() {

	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	 private boolean setupEconomy()
	  {
	    RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
	    if (econProvider != null) {
	      economia = (Economy)econProvider.getProvider();
	    }
	    return economia != null;
	  }

}
