package me.dexton.messageit_webpurifyaddon.util;

import net.md_5.bungee.api.ChatColor;

public class Chat {

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}	
}
