package io.github.dennermelo.aprofile.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import io.github.dennermelo.aprofile.Main;
import me.clip.placeholderapi.PlaceholderAPI;

public class CMDPerfil implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Você não é um jogador. (Player)");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("perfil")) {
			if (args.length == 0) {

				abrirGUI(p);

				return true;

			}
			Player target = Bukkit.getServer().getPlayer(args[0]);

			if (target == null) {
				p.sendMessage("§dAurora Perfil: §cJogador §f" + args[0] + "§c não está online.");
				return true;
			} else {

				abrirGUI2(p, target);
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void onClicarInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String nome = Main.getInstance().getConfig().getString("HeadPerfil.Display_Name").replace("&", "§");
		String nomenovo = PlaceholderAPI.setPlaceholders(p, nome);

		if (e.getInventory().getName().equalsIgnoreCase("§eInspecionar Perfil")) {

				if (e.getCurrentItem().getItemMeta().hasLore()) {
					e.setCancelled(true);
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains(nomenovo)) {
				e.setCancelled(true);
			}

		}

	public void abrirGUI(Player p) {
		Inventory inv = Bukkit.createInventory(p, Main.getInstance().getConfig().getInt("SlotsInventario"),
				"§eInspecionar Perfil");

		for (String key : Main.getInstance().getConfig().getConfigurationSection("InventarioPerfil").getKeys(false)) {
			String material = Main.getInstance().getConfig().getString("InventarioPerfil." + key + ".Material")
					.toUpperCase();
			String nome = Main.getInstance().getConfig().getString("InventarioPerfil." + key + ".Display_Name");
			String nomenovo = PlaceholderAPI.setPlaceholders(p, nome);

			ItemStack item = new ItemStack(Material.getMaterial(material));
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName(nomenovo.replace("&", "§"));
			ArrayList<String> lore = new ArrayList<>();
			for (String all : Main.getInstance().getConfig().getStringList("InventarioPerfil." + key + ".Lore")) {
				String set = PlaceholderAPI.setPlaceholders(p, all);
				lore.add(set.replace("&", "§"));

			}
			itemm.setLore(lore);
			item.setItemMeta(itemm);

			inv.setItem(Main.getInstance().getConfig().getInt("InventarioPerfil." + key + ".Slot"), item);

		}
		String nome = Main.getInstance().getConfig().getString("HeadPerfil.Display_Name").replace("&", "§");
		String nomenovo = PlaceholderAPI.setPlaceholders(p, nome);
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemm = (SkullMeta) item.getItemMeta();
		itemm.setOwner(p.getName());
		itemm.setDisplayName(nomenovo);
		ArrayList<String> lore = new ArrayList<>();
		for (String all : Main.getInstance().getConfig().getStringList("HeadPerfil.Lore")) {
			String set = PlaceholderAPI.setPlaceholders(p, all);
			lore.add(set.replace("&", "§"));
		}
		itemm.setLore(lore);
		item.setItemMeta(itemm);

		inv.setItem(Main.getInstance().getConfig().getInt("HeadPerfil.Slot"), item);

		p.openInventory(inv);
	}

	public void abrirGUI2(Player p, Player alvo) {
		Inventory inv = Bukkit.createInventory(p, Main.getInstance().getConfig().getInt("SlotsInventario"),
				"§eInspecionar Perfil");

		for (String key : Main.getInstance().getConfig().getConfigurationSection("InventarioPerfil").getKeys(false)) {
			String material = Main.getInstance().getConfig().getString("InventarioPerfil." + key + ".Material")
					.toUpperCase();
			String nome = Main.getInstance().getConfig().getString("InventarioPerfil." + key + ".Display_Name");
			String nomenovo = PlaceholderAPI.setPlaceholders(alvo, nome);

			ItemStack item = new ItemStack(Material.getMaterial(material));
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName(nomenovo.replace("&", "§"));
			ArrayList<String> lore = new ArrayList<>();
			for (String all : Main.getInstance().getConfig().getStringList("InventarioPerfil." + key + ".Lore")) {
				String allnovo = PlaceholderAPI.setPlaceholders(alvo, all);
				lore.add(allnovo.replace("&", "§"));

			}
			itemm.setLore(lore);
			item.setItemMeta(itemm);

			inv.setItem(Main.getInstance().getConfig().getInt("InventarioPerfil." + key + ".Slot"), item);

		}
		String nome = Main.getInstance().getConfig().getString("HeadPerfil.Display_Name").replace("&", "§");
		String nomenovo = PlaceholderAPI.setPlaceholders(alvo, nome);
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemm = (SkullMeta) item.getItemMeta();
		itemm.setOwner(alvo.getName());
		itemm.setDisplayName(nomenovo.replace("&", "§"));
		ArrayList<String> lore = new ArrayList<>();
		for (String all : Main.getInstance().getConfig().getStringList("HeadPerfil.Lore")) {
			String allnovo = PlaceholderAPI.setPlaceholders(alvo, all);
			lore.add(allnovo.replace("&", "§"));
		}
		itemm.setLore(lore);
		item.setItemMeta(itemm);

		inv.setItem(Main.getInstance().getConfig().getInt("HeadPerfil.Slot"), item);

		p.openInventory(inv);
	}

}
