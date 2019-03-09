package io.github.dennermelo.amito;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class MitoCommand implements CommandExecutor {
	FileConfiguration config = Main.getInstance().getConfig();
	String prefix = config.getString("Configuracoes.Prefix").replace("&", "§");
	
	static File file = DataManager.getFile("dados");
	static FileConfiguration dados = DataManager.getConfiguration(file);

	
	 public static void loadConfig() {
	        dados = YamlConfiguration.loadConfiguration(file);
	    }

	    public static void reloadConfig() {
	        loadConfig();
	    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("mito")) {

			if (args.length == 0) {

				if (!dados.getBoolean("Mitos.Contains-Mito")) {
					p.sendMessage(prefix + " " + config.getString("Mensagens.Sem-Mito").replace("&", "§"));
					return true;
				} else {
					p.sendMessage(prefix + " " + config.getString("Mensagens.Mostrar-Mito").replace("&", "§")
							.replace("%mitoatual%", dados.getString("Mitos.Mito-Atual")));
					return true;
				}
			}
				if (args[0].equalsIgnoreCase("help")) {
					if (!p.hasPermission(config.getString("Configuracoes.Permission-Comandos"))) {
						p.sendMessage(prefix + " " + config.getString("Mensagens.Sem-Permissao").replace("&", "§"));
						return true;
					}

					p.sendMessage(prefix + " §7Lista de comandos:");
					p.sendMessage("§5/mito §7- Visualiza o Mito atual.");
					p.sendMessage("§5/setmito <jogador> §7- Seta o novo Mito do PvP.");
					p.sendMessage("§5/removemito <jogador> §7- Remove o Mito atual.");
					return true;
				}

		}
		if (cmd.getName().equalsIgnoreCase("setmito")) {

			if (!p.hasPermission(config.getString("Configuracoes.Permission-Comandos"))) {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Sem-Permissao").replace("&", "§"));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Usage-Error").replace("&", "§"));
				return true;
			}
			Player alvo = Bukkit.getPlayer(args[0]);
			if(!dados.getBoolean("Mitos.Contains-Mito")) {
				if (alvo == null) {
					p.sendMessage(prefix + " " + config.getString("Mensagens.Jogador-Offline").replace("&", "§"));
					return true;
				} else {

					dados.set("Mitos.Mito-Antigo", dados.get("Mitos.Mito-Atual"));
					dados.set("Mitos.Mito-Atual", alvo.getName());
					dados.set("Mitos.Contains-Mito", true);
					try {
						dados.save(file);
						reloadConfig();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					
					for (String lista : config.getStringList("Comandos-Para-Executar")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								lista.replace("%oldmito%", dados.getString("Mitos.Mito-Antigo")).replace("%newmito%",
										dados.getString("Mitos.Mito-Atual")));
					}
					Bukkit.broadcastMessage(prefix + " " + config.getString("Mensagens.Novo-Mito").replace("&", "§")
							.replace("%newmito%", alvo.getName()));

					Location loc = p.getLocation();
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(2, 0, 0));
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(-2, 0, 0));
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(0, 0, 2));
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(0, 0, -2));
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(-2, 0, 2));
					loc.getWorld().strikeLightningEffect(alvo.getLocation().add(2, 0, -2));
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
					}
					return true;

				}
			} else {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Has-Mito").replace("&", "§"));
				return true;
			}
			

		}
		if (cmd.getName().equalsIgnoreCase("removemito")) {

			if (!p.hasPermission(config.getString("Configuracoes.Permission-Comandos"))) {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Sem-Permissao").replace("&", "§"));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Usage-Error-2").replace("&", "§"));
				return true;
			}
			String alvo = args[0];
			if (!dados.getString("Mitos.Mito-Atual").equals(alvo)) {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Jogador-Nao-Mito").replace("&", "§"));
				return true;
			} else {
				p.sendMessage(prefix + " " + config.getString("Mensagens.Jogador-Removido-Mito").replace("&", "§"));
				Bukkit.broadcastMessage(prefix + " " + config.getString("Mensagens.Mito-Removido").replace("&", "§"));

				dados.set("Mitos.Mito-Atual", "ninguem");
				dados.set("Mitos.Mito-Antigo", alvo);
				dados.set("Mitos.Contains-Mito", false);
				try {
					dados.save(file);
					reloadConfig();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (String lista : config.getStringList("Comandos-Para-Executar-Mito-Removido")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), lista.replace("%oldmito%", alvo));
				}
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
				}
				return true;
			}

		}
		return false;
	}

}
