package io.github.dennermelo.auroratickets.comandos;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.dennermelo.auroratickets.Main;
import io.github.dennermelo.auroratickets.configuracoes.Configuracoes;
import io.github.dennermelo.auroratickets.configuracoes.Mensagens;
import io.github.dennermelo.auroratickets.managers.DataManager;

public class TicketsCommand implements CommandExecutor {

	public int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt((upper - lower) + 1) + lower;
	}
	public String Mensagem(String[] args) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}
		return sb.toString();
	}

	FileConfiguration config = Main.getInstance().getConfig();
	File file = DataManager.getFile("tickets");
	FileConfiguration tickets = DataManager.getConfiguration(file);
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c[Aurora Tickets] Você não é um jogador válido.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ticket")) {

			if (args.length == 0) {
				if (p.hasPermission("a")) {
					p.sendMessage(Mensagens.Usage_Comando_Ticket);
					return true;
				} else {
					p.sendMessage(Mensagens.Usage_Comando_Ticket_User);
					return true;
				}
			}
			if(args.length > 0) {
				
				String mensagem = Mensagem(args);
				for(String key : tickets.getConfigurationSection("Tickets").getKeys(false)) {
					if(!tickets.getString("Tickets." + key + ".Jogador").contains(p.getName())) {
						tickets.set("Tickets." + key + ".Jogador", p.getName());
						tickets.set("Tickets." + key + ".Mensagem", mensagem);
						try {
							tickets.save(file);
						} catch (Exception e) {
						}
						return true;
					} else {
						p.sendMessage(Mensagens.Ja_Contem_Ticket);
						return true;
					}
				}
				
				
			}

		}
		return false;
	}

}
