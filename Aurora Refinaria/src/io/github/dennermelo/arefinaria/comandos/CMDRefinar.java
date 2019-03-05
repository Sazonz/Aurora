package io.github.dennermelo.arefinaria.comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.dennermelo.arefinaria.Main;

public class CMDRefinar implements CommandExecutor, Listener {
	int taskID;
	int taskID1;
	int taskID2;
	int taskID3;
	int taskID4;
	int taskID5;
	int taskID6;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("refinar")) {

			criarGUI(p);

		}
		return false;
	}

	public void criarGUI(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§7Refinar | Inventário");

		ItemStack refinemenu = new ItemStack(Material.FURNACE);
		ItemMeta refinemenum = refinemenu.getItemMeta();
		refinemenum.setDisplayName("§bRefinar Materiais §7- §cREFINARIA");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§7Esta ferramenta serve para refinar seus minérios brutos,");
		lore.add("§7poupando tempo em troca de um valor por item.");
		lore.add("");
		refinemenum.setLore(lore);
		refinemenu.setItemMeta(refinemenum);

		inv.setItem(13, refinemenu);
		p.openInventory(inv);
	}

	@EventHandler
	public void onClicar(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals("§7Refinar | Inventário")) {
			if (e.getCurrentItem().getType() == null) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.FURNACE) {
				p.closeInventory();
				criarGUI2(p);
				e.setCancelled(true);
			}
		}
		if (e.getInventory().getName().equals("§7Refinar | Materiais")) {
			if (e.getCurrentItem().getType() == null) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.COAL_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.COAL_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.coal_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.COAL_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Carvões§7...");
						p.closeInventory();

						taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.COAL_ORE));
							double valor = (double) Main.getInstance().getConfig().getInt("Valores.coal_ore");

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.COAL_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.COAL, 1));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(), valor);
									if (!p.getInventory().contains(Material.COAL_ORE)) {
										Bukkit.getScheduler().cancelTask(taskID);
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.coal_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.IRON_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.IRON_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Ferros§7...");
						p.closeInventory();

						taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.IRON_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.IRON_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.iron_ore"));
									if (!p.getInventory().contains(Material.IRON_ORE)) {
										Bukkit.getScheduler().cancelTask(taskID1);
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID1);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.iron_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.DIAMOND_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.DIAMOND_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Diamantes§7...");
						p.closeInventory();

						taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.DIAMOND_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.DIAMOND_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.diamond_ore"));
									if (!p.getInventory().contains(Material.DIAMOND_ORE)) {
										Bukkit.getScheduler().cancelTask(taskID2);
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID2);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.diamond_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.GOLD_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.GOLD_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Ouros§7...");
						p.closeInventory();

						taskID3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.GOLD_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.GOLD_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.gold_ore"));
									if (!p.getInventory().contains(Material.GOLD_ORE)) {
										Bukkit.getScheduler().cancelTask(taskID3);
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");
									}
								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID3);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");

								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.gold_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.EMERALD_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.EMERALD_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Esmeraldas§7...");
						p.closeInventory();

						taskID4 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.EMERALD_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.EMERALD_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.emerald_ore"));
									if (!p.getInventory().contains(Material.EMERALD_ORE)) {
										Bukkit.getScheduler().cancelTask(taskID4);
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID4);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.emerald_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.LAPIS_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.LAPIS_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Lapis§7...");
						p.closeInventory();

						taskID5 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.LAPIS_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.LAPIS_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (short) 4));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.lapis_ore"));
									if (!p.getInventory().contains(Material.LAPIS_ORE)) {
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");

										Bukkit.getScheduler().cancelTask(taskID5);
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID5);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}
						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.lapis_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.REDSTONE_ORE) {
				int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
				int valor = Main.getInstance().getConfig().getInt("Valores.redstone_ore") * quantidade;
				if (!Main.economy.has(p, valor)) {
					p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
							+ " §cVocê não possui dinheiro suficiente para isto.");
					p.closeInventory();
					e.setCancelled(true);
				} else {
					if (getAmount(p, new ItemStack(Material.REDSTONE_ORE)) > 0) {

						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ " §7Refinando §e" + quantidade + "x Redstone§7...");
						p.closeInventory();

						taskID6 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							int quantidade = getAmount(p, new ItemStack(Material.REDSTONE_ORE));

							@Override
							public void run() {

								if (quantidade > 0) {
									quantidade--;
									removeInventoryItems(p.getInventory(), Material.REDSTONE_ORE, 1);
									p.getInventory().addItem(new ItemStack(Material.REDSTONE));
									p.updateInventory();
									Main.economy.bankWithdraw(p.getName(),
											Main.getInstance().getConfig().getInt("Valores.redstone_ore"));
									if (!p.getInventory().contains(Material.REDSTONE_ORE)) {
										p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&",
												"§")
												+ " §cOcorreu um problema, você não pode tirar o item do inventário em refinação.");

										Bukkit.getScheduler().cancelTask(taskID6);
									}

								}
								if (quantidade == 0) {
									Bukkit.getServer().getScheduler().cancelTask(taskID6);
									p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
											+ " §7Todos os materiais brutos foram refinados.");
								}

							}

						}, 0L, 20L * Main.getInstance().getConfig().getInt("Tempos.redstone_ore"));
					} else {
						p.sendMessage(Main.getInstance().getConfig().getString("prefix").replace("&", "§")
								+ "§c Você não possui itens para refinar.");
						p.closeInventory();
						e.setCancelled(true);
					}
				}
			}

		}
	}

	public void criarGUI2(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§7Refinar | Materiais");
		Main main = Main.getInstance();
		FileConfiguration config = main.getConfig();
		int multiplicador = config.getInt("Valores.coal_ore");
		int amount = getAmount(p, new ItemStack(Material.COAL_ORE));
		int x = amount * multiplicador;

		int multiplicador1 = config.getInt("Valores.diamond_ore");
		int amount1 = getAmount(p, new ItemStack(Material.DIAMOND_ORE));
		int dima = amount1 * multiplicador1;

		int multiplicador2 = config.getInt("Valores.gold_ore");
		int amount2 = getAmount(p, new ItemStack(Material.GOLD_ORE));
		int ouro = amount2 * multiplicador2;

		int multiplicador3 = config.getInt("Valores.emerald_ore");
		int amount3 = getAmount(p, new ItemStack(Material.EMERALD_ORE));
		int emerald = amount3 * multiplicador3;

		int multiplicador4 = config.getInt("Valores.iron_ore");
		int amount4 = getAmount(p, new ItemStack(Material.IRON_ORE));
		int ferro = amount4 * multiplicador4;

		int multiplicador6 = config.getInt("Valores.redstone_ore");
		int amount6 = getAmount(p, new ItemStack(Material.REDSTONE_ORE));
		int redstone = amount6 * multiplicador6;

		int multiplicador7 = config.getInt("Valores.lapis_ore");
		int amount7 = getAmount(p, new ItemStack(Material.LAPIS_ORE));
		int lapis = amount7 * multiplicador7;

		ItemStack item1 = new ItemStack(Material.DIAMOND_ORE);
		ItemMeta item1m = item1.getItemMeta();
		item1m.setDisplayName("§7Refinar §bDiamante bruto");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.DIAMOND_ORE)) + "x §7diamantes" + "§7por §a$"
				+ dima + "§7.");
		lore.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.diamond_ore")
				+ "s/unidade");
		if (Main.economy.has(p, dima)) {
			lore.add("");
			lore.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore.add("");
			lore.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item1m.setLore(lore);
		item1.setItemMeta(item1m);

		ItemStack item2 = new ItemStack(Material.GOLD_ORE);
		ItemMeta item2m = item2.getItemMeta();
		item2m.setDisplayName("§7Refinar §6Ouro bruto");
		ArrayList<String> lore1 = new ArrayList<>();
		lore1.add("");
		lore1.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.GOLD_ORE)) + "x §7ouros" + " §7por §a$"
				+ ouro + "§7.");
		lore1.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.gold_ore")
				+ "s/unidade");
		if (Main.economy.has(p, ouro)) {
			lore1.add("");
			lore1.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore1.add("");
			lore1.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item2m.setLore(lore1);
		item2.setItemMeta(item2m);

		ItemStack item3 = new ItemStack(Material.EMERALD_ORE);
		ItemMeta item3m = item3.getItemMeta();
		item3m.setDisplayName("§7Refinar §2Esmeralda bruta");
		ArrayList<String> lore2 = new ArrayList<>();
		lore2.add("");
		lore2.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.EMERALD_ORE)) + "x §7esmeraldas "
				+ "§7por §a$" + emerald + "§7.");
		lore2.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.emerald_ore")
				+ "s/unidade");
		if (Main.economy.has(p, emerald)) {
			lore2.add("");
			lore2.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore2.add("");
			lore2.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item3m.setLore(lore2);
		item3.setItemMeta(item3m);

		ItemStack item4 = new ItemStack(Material.IRON_ORE);
		ItemMeta item4m = item4.getItemMeta();
		item4m.setDisplayName("§7Refinar §fFerro bruto");
		ArrayList<String> lore3 = new ArrayList<>();
		lore3.add("");
		lore3.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.IRON_ORE)) + "x §7ferros" + "§7 por §a$"
				+ ferro + "§7.");
		lore3.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.iron_ore")
				+ "s/unidade");
		if (Main.economy.has(p, ferro)) {
			lore3.add("");
			lore3.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore3.add("");
			lore3.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item4m.setLore(lore3);
		item4.setItemMeta(item4m);

		ItemStack item5 = new ItemStack(Material.REDSTONE_ORE);
		ItemMeta item5m = item5.getItemMeta();
		item5m.setDisplayName("§7Refinar §cRedstone bruta");
		ArrayList<String> lore4 = new ArrayList<>();
		lore4.add("");
		lore4.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.REDSTONE_ORE)) + "x §7redstones"
				+ " §7por §a$" + redstone + "§7.");
		lore4.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.redstone_ore")
				+ "s/unidade");
		if (Main.economy.has(p, redstone)) {
			lore4.add("");
			lore4.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore4.add("");
			lore4.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item5m.setLore(lore4);
		item5.setItemMeta(item5m);

		ItemStack item6 = new ItemStack(Material.COAL_ORE);
		ItemMeta item6m = item6.getItemMeta();
		item6m.setDisplayName("§7Refinar §0Carvão bruto");
		ArrayList<String> lore5 = new ArrayList<>();
		lore5.add("");
		lore5.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.COAL_ORE)) + "x §7carvões §7por §a$" + x
				+ "§7.");
		lore5.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.coal_ore")
				+ "s/unidade");
		if (Main.economy.has(p, x)) {
			lore5.add("");
			lore5.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore5.add("");
			lore5.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item6m.setLore(lore5);
		item6.setItemMeta(item6m);

		ItemStack item7 = new ItemStack(Material.LAPIS_ORE);
		ItemMeta item7m = item7.getItemMeta();
		item7m.setDisplayName("§7Refinar §1Lápis Lazuli");
		ArrayList<String> lore6 = new ArrayList<>();
		lore6.add("");
		lore6.add("§7 Transformar §e" + getAmount(p, new ItemStack(Material.LAPIS_ORE)) + "x §7lápis lazuli"
				+ "§7por §a$" + lapis + "§7.");
		lore6.add("§7 Tempo para refinar item: §e" + Main.getInstance().getConfig().getInt("Tempos.lapis_ore")
				+ "s/unidade");
		if (Main.economy.has(p, lapis)) {
			lore6.add("");
			lore6.add("§a§lCLIQUE PARA REFINAR");
		} else {
			lore6.add("");
			lore6.add("§c§lSEM DINHEIRO SUFICIENTE");
		}
		item7m.setLore(lore6);
		item7.setItemMeta(item7m);

		inv.setItem(10, item1);
		inv.setItem(11, item2);
		inv.setItem(12, item3);
		inv.setItem(13, item4);
		inv.setItem(14, item5);
		inv.setItem(15, item6);
		inv.setItem(16, item7);
		p.openInventory(inv);

	}

	public static int getAmount(Player arg0, ItemStack arg1) {
		if (arg1 == null)
			return 0;
		int amount = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack slot = arg0.getInventory().getItem(i);
			if (slot == null || !slot.isSimilar(arg1))
				continue;
			amount += slot.getAmount();
		}
		return amount;
	}

	public static void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
		for (ItemStack is : inv.getContents()) {
			if (is != null && is.getType() == type) {
				int newamount = is.getAmount() - amount;
				if (newamount > 0) {
					is.setAmount(newamount);
					break;
				} else {
					inv.remove(is);
					amount = -newamount;
					if (amount == 0)
						break;
				}
			}
		}
	}

}
