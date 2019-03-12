package io.github.dennermelo.auroraenquetes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Iniciando plugin...");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Criando ou carregando arquivo §fconfig.yml§c...");
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Arquivo §fconfig.yml §ccriado com sucesso.");
		registerCMD();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Registrando comandos...");

	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
