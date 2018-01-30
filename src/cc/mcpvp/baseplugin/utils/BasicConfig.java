package cc.mcpvp.baseplugin.utils;

import cc.mcpvp.baseplugin.BasicPlugin;

public class BasicConfig {

	private static BasicPlugin plugin = BasicPlugin.getInstance();

	// private static FileConfiguration config =plugin.getConfig();

	public static void reload() {
		plugin.reloadConfig();

		// (
		// (ModuleDisableCrafting)ModuleLoader.getMods().get("disable-crafting")).reload();

		// config = plugin.getConfig();

	}
}
