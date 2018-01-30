package cc.mcpvp.baseplugin.module.server;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.module.Module;

public class ModuleSafeJoin extends Module {

	public ModuleSafeJoin(BasicPlugin plugin, String configName) {
		super(plugin, configName);
		// TODO 自动生成的构造函数存根
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location location = player.getLocation();

	}

}
