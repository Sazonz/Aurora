package io.github.dennermelo.amito;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage("§5[Aurora Mito] Inicializando...");
		registerCMD();
		registerEVT();
		ConfigManager.createConfig("dados");
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§5[Aurora Mito] Inicializado com sucesso.");
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {
		getCommand("mito").setExecutor(new MitoCommand());
		getCommand("setmito").setExecutor(new MitoCommand());
		getCommand("removemito").setExecutor(new MitoCommand());

	}
	private void registerEVT() {
		Bukkit.getPluginManager().registerEvents(new MitoEvents(), this);

	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
