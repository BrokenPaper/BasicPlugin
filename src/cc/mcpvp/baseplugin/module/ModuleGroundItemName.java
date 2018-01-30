package cc.mcpvp.baseplugin.module;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.hook.LanguageManager;

public class ModuleGroundItemName extends Module {

	public ModuleGroundItemName(BasicPlugin plugin) {
		super(plugin, "ground-item-name");
	}

	@EventHandler
	public void onItemDrop(ItemSpawnEvent e) {
		showItemName(e.getEntity());
	}

	@EventHandler
	public void onItemMerge(final ItemMergeEvent e) {
		Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
			public void run() {
				ModuleGroundItemName.this.showItemName(e.getTarget());
			}
		}, 1L);
	}

	private void showItemName(Item item) {
		if (item.getPickupDelay() > 45) {
			return;
		}
		ItemStack itemStack = item.getItemStack();

		String name = null;
		if (itemStack.getItemMeta().hasDisplayName()) {
			name = itemStack.getItemMeta().getDisplayName();
		} else {
			name = LanguageManager.getItemName(itemStack);
		}
		Integer amount = itemStack.getAmount();
		String info;

		if (amount.intValue() == 1) {
			info = name;
		} else {
			info = name + "*" + amount.toString();
		}
		item.setCustomName(info);
		item.setCustomNameVisible(true);
	}

}
