package cc.mcpvp.baseplugin.module.server;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.module.Module;

public class ModuleKeepInventory extends Module {

	public ModuleKeepInventory(BasicPlugin plugin) {
		
		super(plugin, "keep-inventory");

	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.setKeepInventory(getSection().getBoolean("staff"));
		event.setKeepLevel(getSection().getBoolean("exp"));

	}

}
