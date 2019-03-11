package io.github.dennermelo.aurorabans;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;


public class BanUtils {
	
	static File file = DataManager.getFile("bans");
	public static FileConfiguration bans = DataManager.getConfiguration(file);

}
