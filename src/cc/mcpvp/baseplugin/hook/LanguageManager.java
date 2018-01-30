package cc.mcpvp.baseplugin.hook;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.mythwars.language.LanguageAPI;

public class LanguageManager {

	public static String getItemName(ItemStack itemStack) {

		if (Bukkit.getPluginManager().isPluginEnabled("LanguageAPI")) {

			return LanguageAPI.getItemName(itemStack);
			
		} else if (Bukkit.getPluginManager().isPluginEnabled("LangUtils")) {

			// To do
		}

		// 只在 1.12.2Paper 有效
		/*
		 * try {
		 * 
		 * return itemStack.getI18NDisplayName(); } catch (Exception e) { return
		 * itemStack.getType().name(); }
		 */

		return itemStack.getType().name();

	}

}
