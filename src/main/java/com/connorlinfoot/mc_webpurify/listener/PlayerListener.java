package com.connorlinfoot.mc_webpurify.listener;

import com.connorlinfoot.mc_webpurify.Webpurify;
import com.connorlinfoot.mc_webpurify.webpurifymessage.WebpurifyMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		WebpurifyMessage m = new WebpurifyMessage(Webpurify.getWebpurify(), event.getMessage());
		
		if(!m.isAppropriate()) {
			event.getPlayer().sendMessage(Webpurify.getWebpurify().getMessage());
			event.setCancelled(true);
		}
		
	}
}
