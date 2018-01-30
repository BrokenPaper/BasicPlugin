package cc.mcpvp.baseplugin.module.lag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.scheduler.BukkitRunnable;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.ModuleLoader;

public class LagRedstoneCheck extends ModuleLag {

	private HashMap<Location, Integer> syncCache = new HashMap<Location, Integer>();
	private ConcurrentHashMap<Location, Integer> asyncCache = new ConcurrentHashMap<Location, Integer>();

	public LagRedstoneCheck(BasicPlugin plugin) {
		super(plugin, "redstone-check");

		BukkitRunnable task = new BukkitRunnable() {

			@Override
			public void run() {
				
				if (!ModuleLoader.getModules().contains(this)) {
					cancel();
					return;
				}

				LagRedstoneCheck.this.check();

			}

		};

		task.runTaskTimer(this.plugin, 0, 20L * 60);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onBlockRedstone(BlockRedstoneEvent event) {

		ArrayList<Integer> block = new ArrayList<Integer>(Arrays.asList(55, 152, 75, 76));

		if (!block.contains(event.getBlock().getTypeId())) {
			return;
		}

		if (this.syncCache.containsKey(event.getBlock().getLocation())) {
			this.syncCache.put(event.getBlock().getLocation(), Integer.valueOf(((Integer) this.syncCache.get(event.getBlock().getLocation())).intValue() + 1));
		} else {
			this.syncCache.put(event.getBlock().getLocation(), Integer.valueOf(1));
		}

	}

	public void check() {
		if (!this.syncCache.isEmpty()) {
			for (Entry<Location, Integer> entry : syncCache.entrySet()) {

				if (entry.getValue() >= getSection().getInt("limit")) {
					Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
						@Override
						public void run() {
							entry.getKey().getBlock().breakNaturally();

						}
					}, 2L);

				}

				entry.setValue(0);

			}
		}
	}

}
