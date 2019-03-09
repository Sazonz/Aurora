package io.github.dennermelo.acrash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	private static Version version;

	public void onEnable() {

		registerCMD();
		checkServerVersion();
		saveDefaultConfig();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	private void registerCMD() {
		getCommand("crashar").setExecutor(new CrashCommand());

	}
	private Version checkServerVersion() {
		String ver = Bukkit.getVersion();

		if (ver.contains("1.13"))
			return Version.v1_13;
		else if (ver.contains("1.12"))
			return Version.v1_12;
		else if (ver.contains("1.11"))
			return Version.v1_11;
		else if (ver.contains("1.10"))
			return Version.v1_10;
		else if (ver.contains("1.9"))
			return Version.v1_9;
		else if (ver.contains("1.8"))
			return Version.v1_8;
		else if (ver.contains("1.7"))
			return Version.v1_7;
		else if (ver.contains("1.6"))
			return Version.v1_6;
		else if (ver.contains("1.5"))
			return Version.v1_5;
		else
			return Version.DESCONHECIDA;
	}

	public static Version getVersion() {
		return version;
	}
	public static void load() 
	{
		try 
		{
			CrashAPI.load();
		} 
		catch (Exception e) {}
		
	}
	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
