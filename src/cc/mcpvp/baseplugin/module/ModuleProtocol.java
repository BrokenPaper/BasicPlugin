package cc.mcpvp.baseplugin.module;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ModuleProtocol {

	private static final int PLAYER_INVENTORY = 0;
	private static final int CRAFTING_SIZE = 5;
	private static final int ARMOR_SIZE = 4;
	private static final int MAIN_SIZE = 27;
	private static final int HOTBAR_SIZE = 9;

	public void sendBlankInventoryPacket(Player player) {
		ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
		PacketContainer inventoryPacket = protocolManager.createPacket(PacketType.Play.Server.WINDOW_ITEMS);
		inventoryPacket.getIntegers().write(0, Integer.valueOf(0));
		int inventorySize = 45;
		ItemStack[] blankInventory = new ItemStack[inventorySize];
		Arrays.fill(blankInventory, new ItemStack(Material.AIR));
		StructureModifier<ItemStack[]> itemArrayModifier = inventoryPacket.getItemArrayModifier();
		if (itemArrayModifier.size() > 0) {
			itemArrayModifier.write(0, blankInventory);
		} else {
			StructureModifier<List<ItemStack>> itemListModifier = inventoryPacket.getItemListModifier();
			itemListModifier.write(0, Arrays.asList(blankInventory));
		}
		try {
			protocolManager.sendServerPacket(player, inventoryPacket, false);
		} catch (InvocationTargetException invocationExc) {
		}
	}
}
