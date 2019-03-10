package io.github.dennermelo.aurorass;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {

		registerCMD();
		registerEVT();
		carregar();
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dPlugin ativado com sucesso!");
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dRegistrando comandos...");
		getCommand("ss").setExecutor(new ScreenshareCommand());
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dComando §fSS §dregistado com sucesso.");

	}
	private void registerEVT() {
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dRegistrando eventos...");
		Bukkit.getPluginManager().registerEvents(new ScreenshareEvents(), this);
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dEventos registrados com sucesso.");

	}
	private void carregar() {
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dCarregando mensagens...");
		Messages.loadMessages();
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dMensagens carregadas com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dCarregando configurações...");
		Settings.LoadSettings();
		Bukkit.getConsoleSender().sendMessage("§5§l[Aurora SS] §dConfigurações carregadas com sucesso!");
		

	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
