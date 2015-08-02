package me.dexton.messageit_webpurifyaddon.listener;

import me.dexton.messageit.event.MessageEvent;
import me.dexton.messageit_webpurifyaddon.WebpurifyAddon;
import me.dexton.messageit_webpurifyaddon.webpurifymessage.WebpurifyMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {
	
	private WebpurifyAddon plugin;
	
	public PlayerListener(WebpurifyAddon plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMessage(MessageEvent event) {
		
		if(!plugin.getConfig().enabled()) {
			return;
		}
		
		WebpurifyMessage m = new WebpurifyMessage(plugin, event.getMessage());
		
		if(!m.isAppropriate()) {
			event.getSender().sendMessage(ChatColor.RED + "Message not appropriate.");
			event.setCancelled(true);
		}
		
	}
}
