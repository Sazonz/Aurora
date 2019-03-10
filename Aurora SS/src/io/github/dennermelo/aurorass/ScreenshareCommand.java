package io.github.dennermelo.aurorass;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ScreenshareCommand implements CommandExecutor {

	FileConfiguration config = Main.getInstance().getConfig();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§5[Aurora SS] §dVocê não é um jogador.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ss")) {

			if (!p.hasPermission(Settings.SS_Permission)) {
				p.sendMessage(Messages.No_Permission);
				return true;
			}
			if (args.length != 1) {
				p.sendMessage(Messages.Command_Usage);
				return true;
			}
			Player alvo = Bukkit.getPlayer(args[0]);
			if(alvo == p) {
				p.sendMessage(Messages.You_Can_Not_Add_Yourself);
				return true;
			}

			if (alvo == null) {
				p.sendMessage(Messages.Player_Offline);
				return true;
			}
			int i;
			
			if (ScreenshareUtil.ss.contains(alvo.getName())) {
				ScreenshareUtil.ss.remove(p.getName());
				p.sendMessage(Messages.Player_Removed_SS.replace("%player%", alvo.getName()));

				for (i = 0; i < 100; i++) {
					alvo.sendMessage(" ");
				}
				alvo.sendMessage(Messages.Removed_SS);
				ScreenshareUtil.ss.remove(alvo.getName());
				return true;
			}
			ScreenshareUtil.ss.add(alvo.getName());
			p.sendMessage(Messages.Player_Added_SS.replace("%player%", alvo.getName()));
			for (i = 0; i < 100; i++) {
				alvo.sendMessage(" ");
			}
			for (String list : config.getStringList("Messages.You-Are-In-SS")) {
				alvo.sendMessage(list.replace("&", "§"));
			}
		
			return true;
		}
		return false;
	}

}
