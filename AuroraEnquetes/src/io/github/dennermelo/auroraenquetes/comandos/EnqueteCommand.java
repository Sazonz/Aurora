package io.github.dennermelo.auroraenquetes.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.dennermelo.auroraenquetes.utils.Mode;

public class EnqueteCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§c[Aurora Enquetes] Você não é um jogador válido.");
			return true;
		}
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("enquete")) {
			p.sendMessage(Mode.getString(3, Mode.NUMERIC));
			return true;
			
			
		}
		return false;
	}
	

}
