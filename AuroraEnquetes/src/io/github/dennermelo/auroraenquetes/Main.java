package io.github.dennermelo.auroraenquetes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dennermelo.auroraenquetes.comandos.EnqueteCommand;
import io.github.dennermelo.auroraenquetes.managers.ConfigManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Iniciando plugin...");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Criando ou carregando arquivo §fconfig.yml§c...");
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Arquivo §fconfig.yml §ccriado com sucesso.");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Criando arquivos extras...");
		criarConfigs();
		registerCMD();

	}

	@Override
	public void onDisable() {

	}

	private void registerCMD() {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquetes] Registrando comandos...");
		getCommand("enquetes").setExecutor(new EnqueteCommand());

	}

	private void criarConfigs() {
		ConfigManager.createConfig("enquetes");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquentes] §fEnquetes.yml §ccriado com sucesso.");

	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
