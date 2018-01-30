package cc.mcpvp.baseplugin.module;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import cc.mcpvp.baseplugin.BasicPlugin;

public class ModuleDisableCrafting extends Module {

	private HashSet<Material> denied = new HashSet<Material>();

	public ModuleDisableCrafting(BasicPlugin plugin) {

		super(plugin, "disable-crafting");

		List<String> list = getSection().getStringList("denied");

		if (list != null)
			for (String name : list) {
				Material material = Material.getMaterial(name);
				if (material != null) {
					this.denied.add(material);
				}
			}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {

		/*
		 * if (!isEnabled()){ return; }
		 */

		if (event.getViewers().size() < 1) {
			return;
		}

		CraftingInventory inv = event.getInventory();
		ItemStack result = inv.getResult();

		if ((result != null) && (this.denied.contains(result.getType()))) {
			inv.setResult(null);
		}
	}
}
