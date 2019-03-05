package io.github.dennermelo.aurora.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.dennermelo.aurora.Main;

public class CMDSpawn implements CommandExecutor {
	
	int taskID;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			
			if(!p.hasPermission("aurora.setspawn")) {
				p.sendMessage("§d§lAurora Spawn: §cVocê não possui permissão para isto.");
				return true;
			} else {
				
				double x = p.getLocation().getX();
				double y = p.getLocation().getY();
				double z = p.getLocation().getZ();
				float yaw = (float)p.getLocation().getYaw();
				float pitch = (float)p.getLocation().getPitch();
				String world = p.getWorld().getName();
				
				Main.getInstance().getConfig().set("Location.spawn.world", world);
				Main.getInstance().getConfig().set("Location.spawn.x", x);
				Main.getInstance().getConfig().set("Location.spawn.y", y);
				Main.getInstance().getConfig().set("Location.spawn.z", z);
				Main.getInstance().getConfig().set("Location.spawn.yaw", yaw);
				Main.getInstance().getConfig().set("Location.spawn.pitch", pitch);
				Main.getInstance().saveConfig();
				Main.getInstance().reloadConfig();
				
				
				p.sendMessage("§d§lAurora Spawn: §7Você setou o spawn com sucesso!");
				p.playSound(p.getLocation(), Sound.DIG_GRASS, 1.5F, 1.5F);
				return true;
			}
			
		}
		
		return false;
	}
	

}
