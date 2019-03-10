package io.github.dennermelo.aurorass;


import org.bukkit.configuration.file.FileConfiguration;

public class Messages {
	
	
	public static String No_Permission;
	public static String Player_Added_SS;
	public static String Player_Removed_SS;
	public static String You_Can_Not_Add_Yourself;
	public static String Player_Logout;
	public static String Player_Offline;
	public static String Reason_Ban;
	public static String Command_Usage;
	public static String Removed_SS;
	
	
	public static void loadMessages() {
		FileConfiguration config = Main.getInstance().getConfig();
		
		No_Permission = config.getString("Messages.No-Permission").replace("&", "§");
		Player_Added_SS = config.getString("Messages.Player-Added-SS").replace("&", "§");
		Player_Removed_SS = config.getString("Messages.Player-Removed-SS").replace("&", "§");
		You_Can_Not_Add_Yourself = config.getString("Messages.You-Can-Not-Add-Yourself").replace("&", "§");
		Player_Logout = config.getString("Messages.Player-Logout").replace("&", "§");
		Player_Offline = config.getString("Messages.Player-Is-Offline").replace("&", "§");
		Reason_Ban = config.getString("Messages.Reason-Ban");
		Command_Usage = config.getString("Messages.Command-Usage").replace("&", "§");
		Removed_SS = config.getString("Messages.You-Removed-From-SS").replace("&", "§");
	}

}
