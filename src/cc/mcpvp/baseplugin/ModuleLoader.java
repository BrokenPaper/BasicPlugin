package cc.mcpvp.baseplugin;

import java.util.ArrayList;

import org.bukkit.event.HandlerList;

import cc.mcpvp.baseplugin.module.Module;

import com.comphenix.protocol.ProtocolLibrary;

public class ModuleLoader {

	private static BasicPlugin plugin = BasicPlugin.getInstance();

	private static ArrayList<Module> modules = new ArrayList<Module>();



	public static ArrayList<Module> getModules() {
		return modules;
	}

	public static void loadModule(Module module) {

		SetState(module, true);
		modules.add(module);
	}

	public static void unloadModule(Module module) {

		SetState(module, false);

		modules.remove(module);
	}

	public static void reload(Module module) {

		unloadModule(module);
		loadModule(module);

	}

	public static void unloadModules() {
		for (Module mod : modules) {
			SetState(mod, false);
		}
		modules.clear();
	}

	public static void reloadModules() {

		for (Module mod : modules) {
			SetState(mod, false);
		}
		modules.clear();
		plugin.registerAllEvents();

	}

	private static void SetState(Module module, boolean state) {

		if (state) {
			if (!HandlerList.getRegisteredListeners(plugin).contains(module)) {

				try {

					if (module.isEnabled()) {

						plugin.getLogger().info("[+] 成功加载模块 " + module.getName() + " (开启状态)");
						plugin.getServer().getPluginManager().registerEvents(module, plugin);
					} else {

						plugin.getLogger().info("[+] 成功加载模块 " + module.getName() + " (关闭状态)");
					}

				} catch (Exception e) {
					plugin.getLogger().warning("[!] 加载 " + module.getName() + " 失败!");
				}

			}

		} else {

			try {

				HandlerList.unregisterAll(module);

				ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);

				plugin.getLogger().info("[-] 成功卸载模块 " + module.getName());
			} catch (Exception e) {
				plugin.getLogger().warning("[!] 卸载模块 " + module.getName() + " 失败!");
			}
		}
	}

}
