package io.github.dennermelo.auroratickets;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dennermelo.auroratickets.comandos.TicketsCommand;
import io.github.dennermelo.auroratickets.configuracoes.Configuracoes;
import io.github.dennermelo.auroratickets.configuracoes.Mensagens;
import io.github.dennermelo.auroratickets.managers.ConfigManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage("§c[Aurora Tickets] Iniciando plugin...");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Tickets] Criando ou carregando arquivo §fconfig.yml§c...");
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Tickets] Arquivo §fconfig.yml §ccriado com sucesso.");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Tickets] Criando arquivos extras...");
		criarConfigs();
		registerCMD();
		Mensagens.carregarMensagens();
		Configuracoes.carregarConfiguracoes();

	}

	@Override
	public void onDisable() {

	}

	private void registerCMD() {
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Tickets] Registrando comandos...");
		getCommand("ticket").setExecutor(new TicketsCommand());

	}

	private void criarConfigs() {
		ConfigManager.createConfig("tickets");
		Bukkit.getConsoleSender().sendMessage("§c[Aurora Enquentes] §fTickets.yml §ccriado com sucesso.");

	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
