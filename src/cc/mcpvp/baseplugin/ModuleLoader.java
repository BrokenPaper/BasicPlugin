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

						plugin.getLogger().info("[+] �ɹ�����ģ�� " + module.getName() + " (����״̬)");
						plugin.getServer().getPluginManager().registerEvents(module, plugin);
					} else {

						plugin.getLogger().info("[+] �ɹ�����ģ�� " + module.getName() + " (�ر�״̬)");
					}

				} catch (Exception e) {
					plugin.getLogger().warning("[!] ���� " + module.getName() + " ʧ��!");
				}

			}

		} else {

			try {

				HandlerList.unregisterAll(module);

				ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);

				plugin.getLogger().info("[-] �ɹ�ж��ģ�� " + module.getName());
			} catch (Exception e) {
				plugin.getLogger().warning("[!] ж��ģ�� " + module.getName() + " ʧ��!");
			}
		}
	}

}
