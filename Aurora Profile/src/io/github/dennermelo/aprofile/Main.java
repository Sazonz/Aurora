package io.github.dennermelo.aprofile;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dennermelo.aprofile.commands.CMDPerfil;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			/*
			 * We register the EventListeneres here, when PlaceholderAPI is installed. Since
			 * all events are in the main class (this class), we simply use "this"
			 */
			Bukkit.getPluginManager().registerEvents(this, this);
		} else {
			throw new RuntimeException("Nao foi encontrado PlaceholdersAPI, plugin não funciona sem ele!");
		}

		getCommand("perfil").setExecutor(new CMDPerfil());
		Bukkit.getPluginManager().registerEvents(new CMDPerfil(), this);
		saveDefaultConfig();
		registerCMD();
		registerEVT();

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
