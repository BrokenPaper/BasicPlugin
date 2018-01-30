package cc.mcpvp.baseplugin.module.lag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFromToEvent;

import cc.mcpvp.baseplugin.BasicPlugin;

public class LagNoliquidMove extends ModuleLag{

	public LagNoliquidMove(BasicPlugin plugin) {
		super(plugin, "no-liquid-move");
		// TODO 自动生成的构造函数存根
	}
	

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onBlockFromTo(BlockFromToEvent event) {
		event.setCancelled(true);
		
	}

}
