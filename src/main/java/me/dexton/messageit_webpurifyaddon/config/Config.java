package me.dexton.messageit_webpurifyaddon.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.dexton.messageit_webpurifyaddon.WebpurifyAddon;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Config {

	WebpurifyAddon plugin;
	File config;
	ConfigurationProvider yaml;

	private String webpurifyKey;
	private boolean enabled;

	public Config(WebpurifyAddon plugin) {
		this.plugin = plugin;
		this.config = new File(plugin.getDataFolder(), "config.yml");
		this.config.getParentFile().mkdirs();
		this.yaml = ConfigurationProvider.getProvider(YamlConfiguration.class);
	}

	public void loadConfig() {
		try {
			config.createNewFile();
			reloadConfig();
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
		}
	}

	public void saveConfig() {
		try {
			Configuration conf = yaml.load(config);
			yaml.save(conf, config);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot save configuration file.", e);
		}
	}

	public void reloadConfig() {
		try {
			Configuration conf = yaml.load(config);

			if(conf.get("webpurify-key") == null) {
				conf.set("webpurify-key", "key");
				enabled = false;
			} else {
				webpurifyKey = (conf.getString("webpurify-key"));
				enabled = true;
			}

			yaml.save(conf, config);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
		}
	}

	public Configuration getRunningConfig() {
		try {
			return yaml.load(config);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
			e.printStackTrace();
		}
		return null;
	}

	public String getWebpurifyKey() {
		return webpurifyKey;
	}
	
	public boolean enabled() {
		return enabled;
	}
}
