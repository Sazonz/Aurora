package io.github.dennermelo.auroraenquetes;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {

		registerCMD();
		registerEVT();
		saveDefaultConfig();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {

	}
	private void registerEVT() {

	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
