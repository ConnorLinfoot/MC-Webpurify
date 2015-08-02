package me.dexton.messageit_webpurifyaddon;

import me.dexton.messageit_webpurifyaddon.config.Config;
import me.dexton.messageit_webpurifyaddon.listener.PlayerListener;
import net.md_5.bungee.api.plugin.Plugin;

public class WebpurifyAddon extends Plugin {
	
	private Config config;
	
	@Override
	public void onEnable() {
		config = new Config(this);
		config.loadConfig();
		registerListeners();
	}
	
	@Override
	public void onDisable() {
		config.saveConfig();
	}
	
	public Config getConfig() {
		return config;
	}
	
	private void registerListeners() {
		getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
	}
}
