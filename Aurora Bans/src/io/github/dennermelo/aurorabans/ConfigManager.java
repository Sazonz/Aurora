package io.github.dennermelo.aurorabans;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	public static void createConfig(String file) {
		if (!new File(Main.getInstance().getDataFolder(), file + ".yml").exists()) {
			Main.getInstance().saveResource(file + ".yml", false); 
		}
	}
	
	public static FileConfiguration getConfig(String file) {
      	File arquivo = new File(Main.getInstance().getDataFolder() + File.separator + file + ".yml");
      	FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(arquivo);
      	return config;
	}

}