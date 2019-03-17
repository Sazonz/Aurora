package io.github.dennermelo.auroratickets.configuracoes;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.dennermelo.auroratickets.Main;

public class Mensagens {
	
	
	public static String Sem_Permissao;
	public static String ID_Nao_Encontrado;
	public static String Usage_Comando_Ticket;
	public static String Usage_Comando_Ticket_User;
	public static String Ticket_Criado;
	public static String Ticket_Removido;
	public static String Ticket_Inexistente;
	public static String Ja_Contem_Ticket;
	
	public static void carregarMensagens() {
		FileConfiguration config = Main.getInstance().getConfig();
		
		Sem_Permissao = config.getString("Mensagens.Sem-Permissao").replace("&", "§");
		ID_Nao_Encontrado = config.getString("Mensagens.Ticket-Nao-Encontrado").replace("&", "§");
		Usage_Comando_Ticket = config.getString("Mensagens.Usage-Ticket").replace("&", "§");
		Usage_Comando_Ticket_User = config.getString("Mensagens.Usage-Ticket-Usuario").replace("&", "§");
		Ticket_Criado = config.getString("Mensagens.Ticket-Criado").replace("&", "§");
		Ticket_Removido = config.getString("Mensagens.Ticket-Removido").replace("&", "§");
		Ticket_Inexistente = config.getString("Mensagens.Ticket-Inexistente").replace("&", "§");
		Ja_Contem_Ticket = config.getString("Mensagens.Ja-Contem-Ticket").replace("&", "§");
		
	}

}
