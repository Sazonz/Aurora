package io.github.dennermelo.aurorass;

import org.bukkit.configuration.file.FileConfiguration;

public class Settings {
	
	
	public static Boolean Ban_logout;
	public static String Ban_Command;
	public static String SS_Permission;
	
	
	public static void LoadSettings() {
		FileConfiguration config = Main.getInstance().getConfig();
		Ban_logout = config.getBoolean("Settings.Ban-Logout");
		Ban_Command = config.getString("Settings.Ban-Command").replace("%reason%", Messages.Reason_Ban);
		SS_Permission = config.getString("Settings.SS-Permission");
	}

}
