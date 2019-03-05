package io.github.dennermelo.arefinaria;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dennermelo.arefinaria.comandos.CMDRefinar;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	
	
	private static Main instance;
	   public static Permission permission = null;
	    public static Economy economy = null;
	    public static Chat chat = null;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		Bukkit.getConsoleSender().sendMessage("§d§l[Aurora Refinaria] §7Plugin carregado e ativado com sucesso!");
		setupChat();
		setupEconomy();
		setupPermissions();
		registerCMD();
		registerEVT();
		
	}
	
	@Override
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("§d§l[Aurora Refinaria] §7Plugin salvo e desativado com sucesso!");

		
	}
	private void registerCMD() {
		
		getCommand("refine").setExecutor(new CMDRefinar());
		getCommand("refinar").setExecutor(new CMDRefinar());

	}
	private void registerEVT() {
		
		Bukkit.getPluginManager().registerEvents(new CMDRefinar(), this);

	}
	
	private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    public static Main getInstance() { return getPlugin(Main.class); }


}
