package com.connorlinfoot.mc_webpurify;

import com.connorlinfoot.mc_webpurify.listener.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Webpurify extends JavaPlugin {
	private static Webpurify webpurify;
	private String webpurifyAPIKey = "";
	private String message = ChatColor.RED + "Message not appropriate.";

	@Override
	public void onEnable() {
		webpurify = this;
		Server server = getServer();
		ConsoleCommandSender console = server.getConsoleSender();

		getConfig().options().copyDefaults(true);
		saveConfig();

		if( getConfig().isSet("webpurify-key") ) {
			webpurifyAPIKey = getConfig().getString("webpurify-key");
		}

		if( getConfig().isSet("not-appropriate-message") ) {
			message = ChatColor.translateAlternateColorCodes('&', getConfig().getString("not-appropriate-message") );
		}

		if( webpurifyAPIKey.equals("") ) {
			console.sendMessage(ChatColor.RED + getDescription().getName() + " has not been enabled! Missing Webpurify API key!");
			return;
		}
		console.sendMessage(ChatColor.AQUA + getDescription().getName() + " V" + getDescription().getVersion() + " has been enabled!");

		registerListeners();
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	public static Webpurify getWebpurify() {
		return webpurify;
	}

	public String getWebpurifyAPIKey() {
		return webpurifyAPIKey;
	}

	public String getMessage() {
		return message;
	}

}
