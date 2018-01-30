package cc.mcpvp.baseplugin.module.lag;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkUnloadEvent;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.module.Module;

public class LagChunkUnloadRemove extends Module {

	public LagChunkUnloadRemove(BasicPlugin plugin) {
		super(plugin, "chunk-unload-remove");
	}

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onChunkUnload(ChunkUnloadEvent event) {

		for (Entity entity : event.getChunk().getEntities()) {

			if (entity instanceof Monster && getSection().getBoolean("monster")) {
				entity.remove();
			} else if (entity instanceof Animals && getSection().getBoolean("animal")) {
				entity.remove();
			} else if (entity instanceof Arrow && getSection().getBoolean("arrow")) {
				entity.remove();
			} else if (entity instanceof ExperienceOrb && getSection().getBoolean("experience_orb")) {
				entity.remove();
			}

			if (getSection().getStringList("custom") != null)
				for (String string : getSection().getStringList("custom")) {
					if (entity.getType().getName().equalsIgnoreCase(string)) {
						entity.remove();
					}
				}

		}

	}

}
