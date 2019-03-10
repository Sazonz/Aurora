package io.github.dennermelo.aurorass;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScreenshareEvents implements Listener {
	public static FileConfiguration config = Main.getInstance().getConfig();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (ScreenshareUtil.ss.contains(p.getName())) {
			e.setTo(e.getFrom());
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				p.sendMessage(list.replace("&", "§"));
			}
		}
	}

	@EventHandler
	public static void onPlace(BlockPlaceEvent e) {
		Player placer = e.getPlayer();
		if (ScreenshareUtil.ss.contains(placer.getName())) {
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				placer.sendMessage(list.replace("&", "§"));
			}
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ScreenshareUtil.ss.contains(p.getName())) {
			if (Settings.Ban_logout) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						Settings.Ban_Command.replace("%player%", p.getName()));
				ScreenshareUtil.ss.remove(p.getName());
				for (Player op : Bukkit.getOnlinePlayers()) {
					if (op.hasPermission(Settings.SS_Permission)) {
						op.sendMessage(Messages.Player_Logout.replace("%player%", p.getName()));
					}
				}
			}
		}
	}

	@EventHandler
	public static void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (ScreenshareUtil.ss.contains(p.getName())) {
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				p.sendMessage(list.replace("&", "§"));
			}
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		List<String> commands = config.getStringList("Settings.Command-List");
		for (String command : commands) {
			if (event.getMessage().startsWith(command) && ScreenshareUtil.ss.contains(p.getName())) {
				event.setCancelled(false);
				return;
			}
			if(!event.getMessage().startsWith(command) && ScreenshareUtil.ss.contains(p.getName())) {
				for (String list : config.getStringList("Messages.You-Are-In-SS")) {
					p.sendMessage(list.replace("&", "§"));
				}
				event.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public static void event(BlockBreakEvent e) {
		Player breaker = e.getPlayer();
		if (ScreenshareUtil.ss.contains(breaker.getName())) {
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				breaker.sendMessage(list.replace("&", "§"));
			}
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onHitPlayer(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && ScreenshareUtil.ss.contains(event.getEntity().getName())) {

			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && ScreenshareUtil.ss.contains(e.getDamager().getName())) {
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				e.getDamager().sendMessage(list.replace("&", "§"));
			}
			e.setCancelled(true);
		}
	}

}
