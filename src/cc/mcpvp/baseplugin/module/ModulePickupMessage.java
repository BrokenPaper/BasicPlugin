package cc.mcpvp.baseplugin.module;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.hook.LanguageManager;
import cc.mcpvp.baseplugin.utils.Utils;

public class ModulePickupMessage extends Module {

	public ModulePickupMessage(BasicPlugin plugin) {
		super(plugin, "pickup-message");
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void OnPlayerPickup(PlayerPickupItemEvent event) {
/*		if (!isEnabled()) {
			return;
		}*/
		if (event.isCancelled()) {
			return;
		}
		//Player player = e.getPlayer();
		Item item = event.getItem();
		ItemStack itemStack = item.getItemStack();
		String name = null;
		
		if (itemStack.getItemMeta().hasDisplayName()) {
			name = itemStack.getItemMeta().getDisplayName();
		} else{
			name = LanguageManager.getItemName(itemStack);
		}
		Utils.sendActionbar(
				event.getPlayer(), Utils.translateColor(getSection().getString("format-pick")
						.replaceAll("%item%", name))
						.replaceAll("%amount%", ""+itemStack.getAmount())
				
				);
		

	}
	
	  @EventHandler(ignoreCancelled=true, priority=EventPriority.MONITOR)
	  public void onItemDrop(PlayerDropItemEvent event)
	  {
	    if (event.isCancelled()) {
	      return;
	    }
	    Item item = event.getItemDrop();
	    ItemStack itemStack = item.getItemStack();
	    String name = null;
		
		if (itemStack.getItemMeta().hasDisplayName()) {
			name = itemStack.getItemMeta().getDisplayName();
		} else{
			name = LanguageManager.getItemName(itemStack);
		}
		Utils.sendActionbar(
				event.getPlayer(), Utils.translateColor(getSection().getString("format-drop")
						.replaceAll("%item%", name))
						.replaceAll("%amount%", ""+itemStack.getAmount())
				
				);
		
	    
	  }

}
