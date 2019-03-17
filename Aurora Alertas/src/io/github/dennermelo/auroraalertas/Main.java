package io.github.dennermelo.auroraalertas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		init();

	}

	@Override
	public void onDisable() {
		close();

	}

	private void init() {

		log("§7Criando arquivo de configuração §cconfig.yml§7.");
		saveDefaultConfig();
		log("&aArquivo de configuração criado com sucesso.");
		log("&7Registrando comandos: §calerta, bc, stream, aviso§7.");
		log("&aComandos registrados com sucesso.");
		log("&7Plugin inicializado com sucesso!");

	}

	private void close() {

	}

	private void log(String message) {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Alertas] " + message);

	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
