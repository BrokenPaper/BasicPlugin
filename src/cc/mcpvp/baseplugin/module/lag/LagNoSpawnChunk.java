package cc.mcpvp.baseplugin.module.lag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.WorldInitEvent;

import cc.mcpvp.baseplugin.BasicPlugin;

public class LagNoSpawnChunk extends ModuleLag {

	public LagNoSpawnChunk(BasicPlugin plugin) {
                
		super(plugin, "no-spawn-chunk");

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onWorldLoad(WorldInitEvent e) {
		e.getWorld().setKeepSpawnInMemory(false);
	}
}
