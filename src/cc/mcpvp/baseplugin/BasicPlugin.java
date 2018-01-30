package cc.mcpvp.baseplugin;

import org.bukkit.plugin.java.JavaPlugin;

import cc.mcpvp.baseplugin.command.BasicCommand;
import cc.mcpvp.baseplugin.module.ModuleAntiBot;
import cc.mcpvp.baseplugin.module.ModuleDisableCrafting;
import cc.mcpvp.baseplugin.module.ModuleFishingKnockback;
import cc.mcpvp.baseplugin.module.ModuleGroundItemName;
import cc.mcpvp.baseplugin.module.ModuleProjectileKnockback;
import cc.mcpvp.baseplugin.module.lag.LagChunkUnloadRemove;
import cc.mcpvp.baseplugin.module.lag.LagNoHighWater;
import cc.mcpvp.baseplugin.module.lag.LagNoSpawnChunk;
import cc.mcpvp.baseplugin.module.lag.LagNoliquidMove;
import cc.mcpvp.baseplugin.module.lag.LagRedstoneCheck;
import cc.mcpvp.baseplugin.module.server.ModuleFireSpread;
import cc.mcpvp.baseplugin.module.server.ModuleKeepInventory;

public class BasicPlugin extends JavaPlugin {
	
	/** 
	* 
	*----------Dragon be here!----------/ 
	* 　　　┏┓　　　┏┓ 
	* 　　┏┛┻━━━┛┻┓ 
	* 　　┃　　　　　　　┃ 
	* 　　┃　　　━　　　┃ 
	* 　　┃　┳┛　┗┳　┃ 
	* 　　┃　　　　　　　┃ 
	* 　　┃　　　┻　　　┃ 
	* 　　┃　　　　　　　┃ 
	* 　　┗━┓　　　┏━┛ 
	* 　　　　┃　　　┃神兽保佑 
	* 　　　　┃　　　┃代码无BUG！ 
	* 　　　　┃　　　┗━━━┓ 
	* 　　　　┃　　　　　　　┣┓ 
	* 　　　　┃　　　　　　　┏┛ 
	* 　　　　┗┓┓┏━┳┓┏┛ 
	* 　　　　　┃┫┫　┃┫┫ 
	* 　　　　　┗┻┛　┗┻┛ 
	* ━━━━━━神兽出没━━━━━━
	*/  
	
	
	private static BasicPlugin instance;

	public static BasicPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {

		instance = this;

		saveDefaultConfig();
		this.registerAllEvents();

		getLogger().info("Basic Plugin Enabled. Enjoy yourself!");

		getCommand("basicplugin").setExecutor(new BasicCommand(this));
	}

	@Override
	public void onDisable() {
		
		//这可能会让插件兼容Plugman
		ModuleLoader.unloadModules();
		getLogger().info("Basic Plugin Disabled. Bye!");

	}

	public void registerAllEvents() {
		
		//1.10版本以后没有弹射物伤害了,这个把伤害加回来了
		ModuleLoader.loadModule(new ModuleProjectileKnockback(this));
		//钓鱼竿的伤害,同上
		ModuleLoader.loadModule(new ModuleFishingKnockback(this));
		//禁止合成一些物品
		ModuleLoader.loadModule(new ModuleDisableCrafting(this));
		//防止压测,还没做好
		ModuleLoader.loadModule(new ModuleAntiBot(this));
		//掉落物品的名字显示
		ModuleLoader.loadModule(new ModuleGroundItemName(this));
		
		//取消出生点区块常驻
		ModuleLoader.loadModule(new LagNoSpawnChunk(this));
		//高频红石检测
		ModuleLoader.loadModule(new LagRedstoneCheck(this));
		//卸载区块时移除生物
		ModuleLoader.loadModule(new LagChunkUnloadRemove(this));
		//禁止高空流水
		ModuleLoader.loadModule(new LagNoHighWater(this));
		//禁止流水
		ModuleLoader.loadModule(new LagNoliquidMove(this));
		
		//禁止火焰传播
		ModuleLoader.loadModule(new ModuleFireSpread(this));
		//保持背包
		ModuleLoader.loadModule(new ModuleKeepInventory(this));
		
		
		
	}

}
