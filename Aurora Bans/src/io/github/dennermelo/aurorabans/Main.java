package io.github.dennermelo.aurorabans;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Inicializando o plugin...");
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Arquivo §fconfig.yml §ccarregado com sucesso.");
		ConfigManager.createConfig("bans");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Arquivo §fbans.yml §ccriado com sucesso.");
		registerCMD();
		registerEVT();
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Carregando mensagens...");
		Messages.loadMessages();

	}

	@Override
	public void onDisable() {

	}

	private void registerCMD() {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Registrando comandos...");
		getCommand("ban").setExecutor(new BanCommand());
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Comando §fBAN §cregistrado com sucesso.");

	}

	private void registerEVT() {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Bans] Registrando eventos...");

	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
