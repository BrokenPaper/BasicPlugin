package cc.mcpvp.baseplugin.module;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import cc.mcpvp.baseplugin.BasicPlugin;

public class ModuleProjectileKnockback extends Module {

	public ModuleProjectileKnockback(BasicPlugin plugin) {
		super(plugin, "projectile-knockback");		
	}

	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent event) {
/*		if (!isEnabled()) {
			return;
		}*/

		EntityType type = event.getDamager().getType();
		switch (type) {
		case SNOWBALL:
		case EGG:
		case ENDER_PEARL:
			event.setDamage(getSection().getDouble("damage." + type.toString().toLowerCase()));
		default:
			break;
		}
	}

}
