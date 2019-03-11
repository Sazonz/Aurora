package io.github.dennermelo.aurorabans;

import org.bukkit.configuration.file.FileConfiguration;

public class Messages {
	
	
	public static String No_Permission;
	public static String Usage_Command;
	public static String Ban_Screen;
	public static Boolean Enable_Broadcast;
	public static String No_Reason;
	public static String Broadcast_Format;
	
	public static void loadMessages() {
		FileConfiguration config = Main.getInstance().getConfig();
		No_Permission = config.getString("Messages.No-Permission").replace("&", "§");
		Usage_Command = config.getString("Messages.Usage-Command").replace("&", "§");
		Ban_Screen = config.getString("Settings.Ban-Format").replace("&", "§");
		No_Reason = config.getString("Messages.No-Reason");
		Broadcast_Format = config.getString("Messages.Broadcast-Format");
		
	}
	

}
