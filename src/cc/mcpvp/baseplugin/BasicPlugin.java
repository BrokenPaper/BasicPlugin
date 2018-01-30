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
	* �������������������� 
	* ���������ߩ��������ߩ� 
	* ���������������������� 
	* ���������������������� 
	* ���������ש������ס��� 
	* ���������������������� 
	* �������������ߡ������� 
	* ���������������������� 
	* ���������������������� 
	* ���������������������ޱ��� 
	* ������������������������BUG�� 
	* �������������������������� 
	* �������������������������ǩ� 
	* ���������������������������� 
	* �������������������ש����� 
	* �������������ϩϡ����ϩ� 
	* �������������ߩ������ߩ� 
	* ���������������޳�û������������
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
		
		//����ܻ��ò������Plugman
		ModuleLoader.unloadModules();
		getLogger().info("Basic Plugin Disabled. Bye!");

	}

	public void registerAllEvents() {
		
		//1.10�汾�Ժ�û�е������˺���,������˺��ӻ�����
		ModuleLoader.loadModule(new ModuleProjectileKnockback(this));
		//����͵��˺�,ͬ��
		ModuleLoader.loadModule(new ModuleFishingKnockback(this));
		//��ֹ�ϳ�һЩ��Ʒ
		ModuleLoader.loadModule(new ModuleDisableCrafting(this));
		//��ֹѹ��,��û����
		ModuleLoader.loadModule(new ModuleAntiBot(this));
		//������Ʒ��������ʾ
		ModuleLoader.loadModule(new ModuleGroundItemName(this));
		
		//ȡ�����������鳣פ
		ModuleLoader.loadModule(new LagNoSpawnChunk(this));
		//��Ƶ��ʯ���
		ModuleLoader.loadModule(new LagRedstoneCheck(this));
		//ж������ʱ�Ƴ�����
		ModuleLoader.loadModule(new LagChunkUnloadRemove(this));
		//��ֹ�߿���ˮ
		ModuleLoader.loadModule(new LagNoHighWater(this));
		//��ֹ��ˮ
		ModuleLoader.loadModule(new LagNoliquidMove(this));
		
		//��ֹ���洫��
		ModuleLoader.loadModule(new ModuleFireSpread(this));
		//���ֱ���
		ModuleLoader.loadModule(new ModuleKeepInventory(this));
		
		
		
	}

}
