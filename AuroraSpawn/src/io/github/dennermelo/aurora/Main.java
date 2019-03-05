package io.github.dennermelo.aurora;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dennermelo.aurora.commands.CMDSpawn;

public class Main extends JavaPlugin implements Listener {

	public static Plugin pl;
	int taskID;

	@Override
	public void onEnable() {

		registerCMD();
		registerEVT();

		saveDefaultConfig();
	}

	@Override
	public void onDisable() {

	}

	private void registerCMD() {
		getCommand("setspawn").setExecutor(new CMDSpawn());

	}

	private void registerEVT() {

	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

	@EventHandler
	public void onEntrar(PlayerJoinEvent e) {

		Player p = e.getPlayer();
		p.teleport(p.getWorld().getSpawnLocation());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawn")) {
			Player p = (Player) sender;
			p.sendMessage("§d§lAurora Spawn: §eTeletransportando em 3 segundos...");
			FileConfiguration config = getInstance().getConfig();

			World world = Bukkit.getWorld(config.getString("Location.spawn.world"));
			double x = getConfig().getDouble("Location.spawn.x");
			double y = getConfig().getDouble("Location.spawn.y");
			double z = getConfig().getDouble("Location.spawn.z");
			float pitch = (float) getConfig().getDouble("Location.spawn.pitch");
			float yaw = (float) getConfig().getDouble("Location.spawn.yaw");

			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

				int tempo = 3;
				Location loc = new Location(world, x, y, z, yaw, pitch);

				@Override
				public void run() {
					if (tempo > 0) {
						tempo--;
						if (p.getLocation().getBlockZ() == p.getLocation().getBlockZ() + 1) {
							p.sendMessage("§dAurora Spawn: §cVocê se moveu durante o teletransporte.");
							Bukkit.getScheduler().cancelTask(taskID);
						}
					}
					if (tempo == 0) {
						p.teleport(loc);
						p.sendMessage("§d§lAurora Spawn: §eVocê foi teletransportado ao spawn.");
						p.playSound(p.getLocation(), Sound.DIG_GRASS, 1.5F, 1.5F);
						Bukkit.getScheduler().cancelTask(taskID);
					}

				}
			}, 0L, 20L);
			return true;
		}
		return super.onCommand(sender, cmd, lbl, args);
	}

}
