package cc.mcpvp.baseplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;

import cc.mcpvp.baseplugin.BasicPlugin;
import cc.mcpvp.baseplugin.ModuleLoader;

public class BasicCommand implements CommandExecutor {
	private BasicPlugin plugin;

	public BasicCommand(BasicPlugin plugin) {

		this.plugin = plugin;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 0) {

			return false;
		}

		if (args[0].equals("reload")) {

			this.plugin.reloadConfig();
			sender.sendMessage("成功重载配置文件.");
			ModuleLoader.reloadModules();

		}
		
		if(args[0].equals("list")){
			
			
			
			
			
			for(RegisteredListener listener:HandlerList.getRegisteredListeners(plugin)){
				Bukkit.broadcastMessage(listener.getListener().toString());
			}
			
/*			for(Module mod:ModuleLoader.getModules()){
				Bukkit.broadcastMessage(mod.getName()+" 状态: "+HandlerList.getRegisteredListeners(plugin).contains(mod));
			}*/
			
		}

		return false;
	}

}
