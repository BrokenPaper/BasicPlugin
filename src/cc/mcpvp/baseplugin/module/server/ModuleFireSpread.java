package cc.mcpvp.baseplugin.module.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.module.Module;

public class ModuleFireSpread extends Module{

	public ModuleFireSpread(BasicPlugin plugin) {
		super(plugin, "fire-spread");
		// TODO �Զ����ɵĹ��캯�����
	}

	@EventHandler
	public void fireSpread( BlockIgniteEvent e) {

		if (BlockIgniteEvent.IgniteCause.SPREAD == e.getCause()) {

				e.setCancelled(true);
			
		}
	}

}
