package io.github.dennermelo.auroraenquetes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnqueteCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("�c[Aurora Enquetes] Voc� n�o � um jogador v�lido.");
			return true;
		}
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("enquete")) {
			
		}
		return false;
	}
	

}
