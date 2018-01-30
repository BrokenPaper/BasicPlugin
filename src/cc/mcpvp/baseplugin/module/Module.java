package cc.mcpvp.baseplugin.module;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;

import cc.mcpvp.baseplugin.BasicPlugin;

public class Module implements Listener {

	protected BasicPlugin plugin;
	protected String name;
	protected String configName;

	public Module(BasicPlugin plugin, String configName) {

		this.plugin = plugin;
		this.configName = configName;
		this.name = configName.toUpperCase().replaceAll("-", "_");

	}

	public String getName() {
		return name;
	}

	public String getConfigName() {
		return this.configName;
	}

	/*
	 * public boolean isEnabled() {
	 * 
	 * ConfigurationSection section =
	 * plugin.getConfig().getConfigurationSection(name); if (section == null) {
	 * System.err.println("Tried to check module '" + name +
	 * "', but it didn't exist!"); return false; } if
	 * (section.getBoolean("enabled")) { return true; } return false; }
	 */

	public boolean isEnabled() {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection(this.configName);
		if (section == null) {
			System.err.println("Tried to check module '" + name + "', but it didn't exist!(Default Enabled)");
			return true;
		}

		if (section.getBoolean("enabled")) {
			return true;
		}
		return false;

	}

	// 没用的世界限制
	/*
	 * public boolean isEnabled(World world) { ConfigurationSection section =
	 * plugin.getConfig().getConfigurationSection(name); if (section == null) {
	 * System.err.println("Tried to check module '" + name +
	 * "', but it didn't exist!"); return false; } if
	 * (section.getBoolean("enabled")) { List<String> list =
	 * section.getStringList("worlds");
	 * 
	 * if ((world != null) && (list != null) && (list.size() > 0)) { for (String
	 * wname : list) { if (wname.equalsIgnoreCase(world.getName())) { return
	 * true; } } return false; } return true; } return false; }
	 */

	public ConfigurationSection getSection() {
		return this.plugin.getConfig().getConfigurationSection(this.configName);
	}

}
