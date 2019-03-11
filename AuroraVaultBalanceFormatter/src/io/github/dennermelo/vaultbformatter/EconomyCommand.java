package io.github.dennermelo.vaultbformatter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		if ((command.getName().equalsIgnoreCase("bal")) || (command.getName().equalsIgnoreCase("balance"))
				|| (command.getName().equalsIgnoreCase("money"))) {
			if ((sender.hasPermission("moneyformat.balance")) || (sender.hasPermission("moneyformat.balance.other"))) {
				Player p = null;
				if ((sender instanceof Player)) {
					p = (Player) sender;
					String playername = p.getName();
					if (args.length != 1) {
						if (Main.economia.getBalance(playername) == 0.0D) {
							p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
									.replaceAll("%player%", p.getName()).replaceAll("%money%", "0"));
							return true;
						}
						if (Main.getInstance().getConfig().getBoolean("Shortener")) {
							if ((Main.economia.getBalance(playername) >= 1000000.0D)
									&& (Main.economia.getBalance(playername) < 1.0E9D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName())
										.replaceAll("%money%", Formatos.milhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E9D)
									&& (Main.economia.getBalance(playername) < 1.0E12D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName())
										.replaceAll("%money%", Formatos.bilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E12D)
									&& (Main.economia.getBalance(playername) < 1.0E15D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.trilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E15D)
									&& (Main.economia.getBalance(playername) < 1.0E18D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.quadrilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E18D)
									&& (Main.economia.getBalance(playername) < 1.0E21D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.quintilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E21D)
									&& (Main.economia.getBalance(playername) < 1.0E24D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.sextilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E24D)
									&& (Main.economia.getBalance(playername) < 1.0E27D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.septilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E27D)
									&& (Main.economia.getBalance(playername) < 1.0E30D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.octilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E30D)
									&& (Main.economia.getBalance(playername) < 1.0E33D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.nonilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E33D)
									&& (Main.economia.getBalance(playername) < 1.0E36D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.decilhoes(Main.economia.getBalance(playername))));
							} else if ((Main.economia.getBalance(playername) >= 1.0E39D)
									&& (Main.economia.getBalance(playername) < 1.0E42D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.milhares(Main.economia.getBalance(playername))));
							} else {
								p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
										.replaceAll("%player%", p.getName()).replaceAll("%money%",
												Formatos.centenas(Main.economia.getBalance(playername))));
							}
						} else {
							p.sendMessage(Main.getInstance().getConfig().getString("Balance").replaceAll("&", "§")
									.replaceAll("%player%", p.getName())
									.replaceAll("%money%", Formatos.centenas(Main.economia.getBalance(playername))));
						}
					}
					if ((p.hasPermission("moneyformat.balance.other")) && (args.length == 1)) {
						Player find = Bukkit.getPlayerExact(args[0]);
						if (find == null) {
							p.sendMessage(ChatColor.RED + "Error. " + args[0] + " not found!");
							return true;
						}
						String findname = find.getName();
						if (Main.economia.getBalance(findname) == 0.0D) {
							p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
									.replaceAll("%player%", find.getName()).replaceAll("%money%", "0"));
							return true;
						}
						if (Main.getInstance().getConfig().getBoolean("Shortener")) {
							if ((Main.economia.getBalance(findname) >= 1000000.0D)
									&& (Main.economia.getBalance(findname) < 1.0E9D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.milhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E9D)
									&& (Main.economia.getBalance(findname) < 1.0E12D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.bilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E12D)
									&& (Main.economia.getBalance(findname) < 1.0E15D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.trilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E15D)
									&& (Main.economia.getBalance(findname) < 1.0E18D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName()).replaceAll("%money%",
												Formatos.quadrilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E18D)
									&& (Main.economia.getBalance(findname) < 1.0E21D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName()).replaceAll("%money%",
												Formatos.quintilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E21D)
									&& (Main.economia.getBalance(findname) < 1.0E24D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName()).replaceAll("%money%",
												Formatos.sextilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E24D)
									&& (Main.economia.getBalance(findname) < 1.0E27D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName()).replaceAll("%money%",
												Formatos.septilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E27D)
									&& (Main.economia.getBalance(findname) < 1.0E30D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.octilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E30D)
									&& (Main.economia.getBalance(findname) < 1.0E33D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.nonilhoes(Main.economia.getBalance(findname))));
							} else if ((Main.economia.getBalance(findname) >= 1.0E33D)
									&& (Main.economia.getBalance(findname) < 1.0E36D)) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.decilhoes(Main.economia.getBalance(findname))));
							} else if (Main.economia.getBalance(findname) > 999.99D) {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.milhares(Main.economia.getBalance(findname))));
							} else {
								p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
										.replaceAll("%player%", find.getName())
										.replaceAll("%money%", Formatos.centenas(Main.economia.getBalance(findname))));
							}
						} else {
							p.sendMessage(Main.getInstance().getConfig().getString("Others").replaceAll("&", "§")
									.replaceAll("%player%", find.getName())
									.replaceAll("%money%", Formatos.centenas(Main.economia.getBalance(findname))));
						}
					}
				} else {
					sender.sendMessage("§c[Aurora VaultBalanceFormatter] Somente players podem usar isto.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to issue that command!");
			}
		}
		if ((command.getName().equalsIgnoreCase("moneyformat")) || (command.getName().equalsIgnoreCase("mf"))) {
			Player p = null;
			if ((sender instanceof Player)) {
				p = (Player) sender;
				if ((sender.hasPermission("moneyformat.reload")) || (sender.isOp())) {
					if ((args.length < 1) || (args.length > 2)) {
						p.sendMessage(ChatColor.GREEN + "Money Format made by Barchtic");
						p.sendMessage(ChatColor.GREEN + "/moneyformat reload - " + ChatColor.GRAY + "Reloads config");
						p.sendMessage(
								ChatColor.GREEN + "/moneyformat ab 1 - " + ChatColor.GRAY + "List of Abbreviations");
					} else if (args.length == 1) {
						if ((args[0].equalsIgnoreCase("reload")) || (args[0].equalsIgnoreCase("rl"))) {
							Main.getInstance().reloadConfig();
							sender.sendMessage(ChatColor.GREEN + "Reloaded configuration!");
						} else if ((args[0].equalsIgnoreCase("abbreviation")) || (args[0].equalsIgnoreCase("abbrev"))
								|| (args[0].equalsIgnoreCase("ab")) || (args[0].equalsIgnoreCase("abbreviations"))) {
							p.sendMessage("§7�m-----�r �8[�cPage 1�8] �7�m-----");
							p.sendMessage("§b�lMillion�6/�b�lMn");
							p.sendMessage("§b�lBillion�6/�b�lBn");
							p.sendMessage("§b�lTrillion�6/�b�lTn");
							p.sendMessage("§b�lQuadrillion�6/�b�lQd");
							p.sendMessage("§b�lQuintillion�6/�b�lQn");
							p.sendMessage("§b�lSextillion�6/�b�lSx");
							p.sendMessage("§7�m-----�r �8[�c/mf ab 2�8] �7�m-----");
						}
					} else if ((args.length == 2) && ((args[0].equalsIgnoreCase("abbreviation"))
							|| (args[0].equalsIgnoreCase("abbrev")) || (args[0].equalsIgnoreCase("ab"))
							|| (args[0].equalsIgnoreCase("abbreviations")))) {
						if (args[1].equalsIgnoreCase("1")) {
							p.sendMessage("§7�m-----�r �8[�cPage 1�8] �7�m-----");
							p.sendMessage("§b�lMillion�6/�b�lMn");
							p.sendMessage("§b�lBillion�6/�b�lBn");
							p.sendMessage("§b�lTrillion�6/�b�lTn");
							p.sendMessage("§b�lQuadrillion�6/�b�lQd");
							p.sendMessage("§b�lQuintillion�6/�b�lQn");
							p.sendMessage("§b�lSextillion�6/�b�lSx");
							p.sendMessage("§7�m-----�r �8[�c/mf ab 2�8] �7�m-----");
						} else if (args[1].equalsIgnoreCase("2")) {
							p.sendMessage("§7�m-----�r �8[�cPage 2�8] �7�m-----");
							p.sendMessage("§b�lSeptillion�6/�b�lSp");
							p.sendMessage("§b�lOctillion�6/�b�lOn");
							p.sendMessage("§b�lNonillion�6/�b�lNn");
							p.sendMessage("§b�lDecillion�6/�b�lDe");
							p.sendMessage("§b�lUndecillion�6/�b�lUn");
							p.sendMessage("§b�lDuodecillion�6/�b�lDe");
							p.sendMessage("§7�m---�r �cEnd of Page! �7�m---");
						} else {
							p.sendMessage(ChatColor.GREEN + "Money Format made by Barchtic");
							p.sendMessage(
									ChatColor.GREEN + "/moneyformat reload - " + ChatColor.GRAY + "Reloads config");
							p.sendMessage(
									ChatColor.GREEN + "/moneyformat ab - " + ChatColor.GRAY + "List of Abbreviations");
						}
					}
				} else if ((sender instanceof Player)) {
					p = (Player) sender;
					if ((args.length < 1) || (args.length > 2)) {
						p.sendMessage(ChatColor.GREEN + "Money Format made by Barchtic");
						p.sendMessage(ChatColor.RED + "/moneyformat reload - " + ChatColor.GRAY + "No permission");
						p.sendMessage(
								ChatColor.GREEN + "/moneyformat ab 1- " + ChatColor.GRAY + "List of Abbreviations");
					} else if (args.length == 1) {
						if ((args[0].equalsIgnoreCase("reload")) || (args[0].equalsIgnoreCase("rl"))) {
							p.sendMessage("§cYou do not have permission.");
						} else if ((args[0].equalsIgnoreCase("abbreviation")) || (args[0].equalsIgnoreCase("abbrev"))
								|| (args[0].equalsIgnoreCase("ab")) || (args[0].equalsIgnoreCase("abbreviations"))) {
							p.sendMessage("§7�m-----�r �8[�cPage 1�8] �7�m-----");
							p.sendMessage("§b�lMillion�6/�b�lMn");
							p.sendMessage("§b�lBillion�6/�b�lBn");
							p.sendMessage("§b�lTrillion�6/�b�lTn");
							p.sendMessage("§b�lQuadrillion�6/�b�lQd");
							p.sendMessage("§b�lQuintillion�6/�b�lQn");
							p.sendMessage("§b�lSextillion�6/�b�lSx");
							p.sendMessage("§7�m-----�r �8[�c/mf ab 2�8] �7�m-----");
						}
					} else if ((args.length == 2) && ((args[0].equalsIgnoreCase("abbreviation"))
							|| (args[0].equalsIgnoreCase("abbrev")) || (args[0].equalsIgnoreCase("ab"))
							|| (args[0].equalsIgnoreCase("abbreviations")))) {
						if (args[1].equalsIgnoreCase("1")) {
							p.sendMessage("§7�m-----�r �8[�cPage 1�8] �7�m-----");
							p.sendMessage("§b�lMillion�6/�b�lMn");
							p.sendMessage("§b�lBillion�6/�b�lBn");
							p.sendMessage("§b�lTrillion�6/�b�lTn");
							p.sendMessage("§b�lQuadrillion�6/�b�lQd");
							p.sendMessage("§b�lQuintillion�6/�b�lQn");
							p.sendMessage("§b�lSextillion�6/�b�lSx");
							p.sendMessage("§7�m-----�r �8[�c/mf ab 2�8] �7�m-----");
						} else if (args[1].equalsIgnoreCase("2")) {
							p.sendMessage("§7�m-----�r �8[�cPage 2�8] �7�m-----");
							p.sendMessage("§b�lSeptillion�6/�b�lSp");
							p.sendMessage("§b�lOctillion�6/�b�lOn");
							p.sendMessage("§b�lNonillion�6/�b�lNn");
							p.sendMessage("§b�lDecillion�6/�b�lDe");
							p.sendMessage("§b�lUndecillion�6/�b�lUn");
							p.sendMessage("§b�lDuodecillion�6/�b�lDe");
							p.sendMessage("§7�m---�r �cEnd of Page! �7�m---");
						} else {
							p.sendMessage(ChatColor.GREEN + "Money Format made by Barchtic");
							p.sendMessage(ChatColor.RED + "/moneyformat reload - " + ChatColor.GRAY + "No permission");
							p.sendMessage(
									ChatColor.GREEN + "/moneyformat ab - " + ChatColor.GRAY + "List of Abbreviations");
						}
					}
				} else {
					sender.sendMessage("§c[Aurora VaultBalanceFormatter] Somente players podem usar isto.");
				}
			} else {
				sender.sendMessage("§c[Aurora VaultBalanceFormatter] Somente players podem usar isto.");
				return true;
			}
		}
		return false;

	}

}
