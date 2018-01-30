package cc.mcpvp.baseplugin.utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
public class Utils {
	
	
	  public static String translateColor(String string)
	  {
	    return ChatColor.translateAlternateColorCodes('&', string);
	  }
	  
	  

	  
	  
	  
	  public static String convertItemStackToJson(ItemStack itemStack)
	  {
	    try
	    {
	      Class<?> craftItemStackClazz = getCBClass("inventory.CraftItemStack");
	      Method asNMSCopyMethod = craftItemStackClazz.getMethod("asNMSCopy", new Class[] { ItemStack.class });
	      
	      Class<?> nmsItemStackClazz = getNMSClass("ItemStack");
	      Class<?> nbtTagCompoundClazz = getNMSClass("NBTTagCompound");
	      Method saveNmsItemStackMethod = nmsItemStackClazz.getMethod("save", new Class[] { nbtTagCompoundClazz });
	      
	      Object nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
	      Object nmsItemStackObj = asNMSCopyMethod.invoke(null, new Object[] { itemStack });
	      Object itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, new Object[] { nmsNbtTagCompoundObj });
	      
	      return itemAsJsonObject.toString();
	    }
	    catch (Throwable t)
	    {
	    }
	    return null;
	  }
	  
	
	 public static void sendActionbar(Player player, String message)
	  {

	  }
	  
	  public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
	  {
	    try
	    {
	      if (title != null)
	      {
	        title = ChatColor.translateAlternateColorCodes('&', title);
	        
	        Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
	        Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, 
	          new Object[] { "{\"text\":\"" + title + "\"}" });
	        Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
	          new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	        Object titlePacket = titleConstructor.newInstance(new Object[] { enumTitle, chatTitle, fadeIn, stay, fadeOut });
	        sendPacket(player, titlePacket);
	      }
	      if (subtitle != null)
	      {
	        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
	        
	        Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
	        Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, 
	          new Object[] { "{\"text\":\"" + subtitle + "\"}" });
	        Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
	          new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	        Object subtitlePacket = subtitleConstructor.newInstance(new Object[] { enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut });
	        sendPacket(player, subtitlePacket);
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  public static String getVersion()
	  {
	    return org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	  }
	  
	  private static void sendPacket(Player player, Object packet)
	  {
	    try
	    {
	      Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
	      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
	      playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  private static Class<?> getNMSClass(String class_name)
	  {
	    String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    try
	    {
	      return Class.forName("net.minecraft.server." + version + "." + class_name);
	    }
	    catch (ClassNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static Class<?> getCBClass(String class_name)
	  {
	    String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    try
	    {
	      return Class.forName("org.bukkit.craftbukkit." + version + "." + class_name);
	    }
	    catch (ClassNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    return null;
	  }
}
