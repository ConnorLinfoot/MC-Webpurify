package me.dexton.messageit_webpurifyaddon;

import me.dexton.messageit_webpurifyaddon.config.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class WebpurifyAddon extends Plugin {
	
	private Config config;
	
	@Override
	public void onEnable() {
		config = new Config(this);
		config.loadConfig();
	}
	
	@Override
	public void onDisable() {
		config.saveConfig();
	}
	
	public Config getConfig() {
		return config;
	}
}
