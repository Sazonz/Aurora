package io.github.dennermelo.auroraenquetes.configuracoes;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.dennermelo.auroraenquetes.Main;

public class Configuracoes {
	
	public static String Permissao_Usage_Admin;
	
	
	public static void carregarConfiguracoes() {

		FileConfiguration config = Main.getInstance().getConfig();
		Permissao_Usage_Admin = config.getString("Configuracoes.Permissao-Necessaria-Admin");
	}

}
