package cc.mcpvp.baseplugin.module.lag;

import org.bukkit.configuration.ConfigurationSection;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.module.Module;

public class ModuleLag extends Module {

	public ModuleLag(BasicPlugin plugin, String configName) {

		super(plugin, configName);
	}
	

	@Override
	public ConfigurationSection getSection() {
		return this.plugin.getConfig().getConfigurationSection("lag." + this.configName);
	}

	@Override
	public boolean isEnabled() {

		ConfigurationSection section = plugin.getConfig().getConfigurationSection("lag." + this.configName);
		if (section == null) {
			System.err.println("Tried to check module '" + name + "', but it didn't exist!");
			return false;
		}

		if (section.getBoolean("enabled")) {
			return true;
		}
		return false;

	}

}
