package io.github.dennermelo.acrash;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CrashCommand implements CommandExecutor {

	FileConfiguration config = Main.getInstance().getConfig();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("crashar")) {
			if (!p.hasPermission("Permissoes.Permission-Necessaria-Crash")) {
				p.sendMessage(config.getString("Mensagens.Jogador-Sem-Permissao").replace("&", "§"));
				return true;
			}

			if (args.length == 1) {
				p.sendMessage(config.getString("Mensagens.Comando-Usage").replace("&", "§"));
				return true;
			}
			Player alvo = Bukkit.getPlayer(args[0]);
			if (alvo == null) {
				p.sendMessage(config.getString("Mensagens.Jogador-Offline").replace("&", "§"));
				return true;
			}
			if (config.getBoolean("Configuracoes.Crashar-Player-Com-OP")) {
				if (alvo.isOp()) {
					CrashAPI.crashPlayer(alvo);
					p.sendMessage(config.getString("Mensagens.Jogador-Crashado").replace("%player%", alvo.getName()));
					return true;
				}
			} else {
				p.sendMessage(config.getString("Mensagens.Jogador-Nao-Pode-Ser-Crashado").replace("&", "§"));
				return true;
			}
			if (config.getBoolean("Configuracoes.Crashar-Player-Com-Permission")) {
				if (alvo.hasPermission(config.getString("Permissoes.Permission-Necessaria-Player-Nao-Crash"))) {
					CrashAPI.crashPlayer(alvo);
					p.sendMessage(config.getString("Mensagens.Jogador-Crashado").replace("%player%", alvo.getName()));
					return true;
				}
			} else {
				p.sendMessage(config.getString("Mensagens.Jogador-Nao-Pode-Ser-Crashado").replace("&", "§"));
				return true;
			}
			CrashAPI.crashPlayer(alvo);
			p.sendMessage(config.getString("Mensagens.Jogador-Crashado").replace("%player%", alvo.getName()));
			if (config.getBoolean("Configuracoes.Ativar-Sound-Crash")) {
				p.playSound(p.getLocation(), Sound.valueOf(config.getString("Configuracoes.Sound-Crash").toUpperCase()),
						1.0F, 1.0F);
			}
			if (config.getBoolean("Configuracoes.Ativar-Title")) {
				TitleAPI.sendTitle(p, 20, 20, 20, config.getString("Mensagens.Title-Crashado").replace("&", "§"),
						config.getString("Mensagens.Subtitle-Crashado").replace("&", "§").replace("%player%",
								alvo.getName()));
			}
			return true;

		}
		return false;
	}

}
