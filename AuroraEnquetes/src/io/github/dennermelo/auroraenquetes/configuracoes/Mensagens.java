package io.github.dennermelo.auroraenquetes.configuracoes;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.dennermelo.auroraenquetes.Main;

public class Mensagens {
	
	
	public static String Sem_Permissao;
	public static String ID_Nao_Encontrado;
	public static String Ja_Contem_Alternativa;
	public static String Usage_Comando_Enquete;
	public static String Usage_Comando_Enquete_User;
	public static String Usage_Comando_SetAlternativa;
	public static String Usage_Comando_SetAlternativa2;
	public static String Enquete_Criada;
	public static String Enquete_Removida;
	public static String Enquete_Inexistente;
	
	public static void carregarMensagens() {
		FileConfiguration config = Main.getInstance().getConfig();
		
		Sem_Permissao = config.getString("Mensagens.Sem-Permissao").replace("&", "§");
		ID_Nao_Encontrado = config.getString("Mensagens.ID-Nao-Encontrado").replace("&", "§");
		Ja_Contem_Alternativa = config.getString("Mensagens.Ja-Contem-Alternativa").replace("&", "§");
		Usage_Comando_Enquete = config.getString("Mensagens.Usage-Enquete").replace("&", "§");
		Usage_Comando_Enquete_User = config.getString("Mensagens.Usage-Enquete-Usuario").replace("&", "§");
		Usage_Comando_SetAlternativa = config.getString("Mensagens.Usage-Comando-SetAlternativa").replace("&", "§");
		Usage_Comando_SetAlternativa2 = config.getString("Mensagens.Usage-Comando-SetAlternativa2").replace("&", "§");
		Enquete_Criada = config.getString("Mensagens.Enquete-Criada").replace("&", "§");
		Enquete_Removida = config.getString("Mensagens.Enquente-Removida").replace("&", "§");
		Enquete_Inexistente = config.getString("Mensagens.Enquete-Inexistente").replace("&", "§");
		
	}

}
