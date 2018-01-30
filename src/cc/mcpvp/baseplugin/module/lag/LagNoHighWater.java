package cc.mcpvp.baseplugin.module.lag;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFromToEvent;

import cc.mcpvp.baseplugin.BasicPlugin;

public class LagNoHighWater extends ModuleLag {

	public LagNoHighWater(BasicPlugin plugin) {
		super(plugin, "no-high-water");
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onBlockFromTo(BlockFromToEvent event) {
		
	
		Block to = event.getToBlock();
		if (to == null) {
			return;
		}

		if (isAirBottom(to, getSection().getInt("limit"))) {
			event.setCancelled(true);
		}

	}

	private boolean isAirBottom(Block b, int checkDistance) {
		Block nowCheckBlock = b;
		while (checkDistance-- > 0) {
			nowCheckBlock = nowCheckBlock.getRelative(BlockFace.DOWN);
			if (nowCheckBlock != null) {
				return false;
			}
		}
		return true;
	}

}
