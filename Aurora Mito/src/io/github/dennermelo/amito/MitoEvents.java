package io.github.dennermelo.amito;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class MitoEvents implements Listener {

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

	@EventHandler
	public void onMitoDeath(PlayerDeathEvent e) {
		Player matou = e.getEntity().getPlayer().getKiller();
		Player morreu = e.getEntity().getPlayer();

		if ((e.getEntity().getKiller()) instanceof Player) {
			return;
		}
		if (dados.getBoolean("Mitos.Contains-Mito") && morreu.getName().equalsIgnoreCase(dados.getString("Mitos.Mito-Atual"))) {

			dados.set("Mitos.Mito-Atual", matou.getName());
			dados.set("Mitos.Mito-Antigo", morreu.getName());
			reloadConfig();

			for (String lista : config.getStringList("Comandos-Para-Executar")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						lista.replace("%oldmito%", morreu.getName()).replace("%newmito%", matou.getName()));
			}
			matou.sendMessage(prefix + " " + config.getString("Jogador-Virou-Mito").replace("&", "§"));
			morreu.sendMessage(prefix + " " + config.getString("Jogador-Removido-Mito").replace("&", "§"));
			Location loc = matou.getLocation();
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(2, 0, 0));
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(-2, 0, 0));
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(0, 0, 2));
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(0, 0, -2));
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(-2, 0, 2));
			loc.getWorld().strikeLightningEffect(matou.getLocation().add(2, 0, -2));
			for (Player players : Bukkit.getServer().getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onChatEvent(ChatMessageEvent e) {
		Player p = e.getSender();
		
		if(e.getTags().contains("mito") && dados.getBoolean("Mitos.Contains-Mito") && p.getName().equals(dados.getString("Mitos.Mito-Atual"))) {
					e.setTagValue("mito",config.getString("Configuracoes.PrefixLegend").replace("&", "§"));
		}

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (p.getName().equals(dados.getString("Mitos.Mito-Atual")) && dados.getBoolean("Mitos.Contains-Mito")) {
			if (config.getBoolean("Ativacoes.Anuncio-Join")) {
				Bukkit.broadcastMessage(prefix + " " + config.getString("Mensagens.Join-Mito").replace("&", "§").replace("%mitoatual%", p.getName()));
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
				}
			} else {
				return;
			}

		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (p.getName().equals(dados.getString("Mitos.Mito-Atual"))) {
			if (config.getBoolean("Ativacoes.Anuncio-Quit")) {
				Bukkit.broadcastMessage(prefix + " " + config.getString("Mensagens.Quit-Mito").replace("&", "§").replace("%mitoatual%", p.getName()));
				Location loc = p.getLocation();
				loc.getWorld().strikeLightningEffect(p.getLocation().add(2, 0, 0));
				loc.getWorld().strikeLightningEffect(p.getLocation().add(-2, 0, 0));
				loc.getWorld().strikeLightningEffect(p.getLocation().add(0, 0, 2));
				loc.getWorld().strikeLightningEffect(p.getLocation().add(0, 0, -2));
				loc.getWorld().strikeLightningEffect(p.getLocation().add(-2, 0, 2));
				loc.getWorld().strikeLightningEffect(p.getLocation().add(2, 0, -2));
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 2.0F);
				}
			} else {
				return;
			}

		}
	}

}
