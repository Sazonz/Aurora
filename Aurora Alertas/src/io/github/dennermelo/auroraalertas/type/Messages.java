package io.github.dennermelo.auroraalertas.type;

import org.bukkit.command.CommandSender;

import io.github.dennermelo.auroraalertas.Main;

public enum Messages {
	
	
	SEM_PERMISSAO(Main.getInstance().getConfig().getString("Mensagens.Sem-Permissao").replace("&", "§")),
	ENVIADO_SUCESSO(Main.getInstance().getConfig().getString("Mensagens.Aviso-Enviado").replace("&", "§")),
	ERRO_AO_ENVIAR(Main.getInstance().getConfig().getString("Mensagens.Erro-Ao-Enviar").replace("&", "§")),
	USAGE_COMANDO(Main.getInstance().getConfig().getString("Mensagens.Usage-Comando").replace("&", "§"));
	
	
private String message;
	
	private Messages(String message) {
		this.message = message;
	}
	
	public void send(CommandSender sender) {
		sender.sendMessage(message);
	}
	

}
