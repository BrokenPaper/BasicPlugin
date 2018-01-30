package cc.mcpvp.baseplugin.module;

import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

import cc.mcpvp.baseplugin.BasicPlugin;

public class ModuleFishingKnockback extends Module {

	public ModuleFishingKnockback(BasicPlugin plugin) {
		super(plugin, "fishing-knockback");
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRodLand(ProjectileHitEvent event) {
/*		if (!isEnabled()) {
			return;
		}*/

		Entity hookEntity = event.getEntity();

		if (event.getEntityType() != EntityType.FISHING_HOOK) {
			return;
		}

		Entity hitent = null;
		try {
			hitent = event.getHitEntity();
		} catch (NoSuchMethodError Exception) {
			Collection<Entity> entities = (Collection<Entity>) hookEntity.getWorld().getNearbyEntities(hookEntity.getLocation(), 0.25, 0.25, 0.25);
			Iterator<Entity> iterator = entities.iterator();
			if (iterator.hasNext()) {
				final Entity entity = iterator.next();
				if (entity instanceof Player) {
					hitent = entity;
				}
			}
		}

		if (hitent == null) {
			return;
		}
		if (!(hitent instanceof Player)) {
			return;
		}
		FishHook hook = (FishHook) hookEntity;
		Player rodder = (Player) hook.getShooter();
		Player player = (Player) hitent;

		// 对自己也要造成伤害,所以去掉了
		/*
		 * if (player.getUniqueId().equals(rodder.getUniqueId())) { return; }
		 */

		if (player.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		double damage = this.getSection().getDouble("damage");
		if (damage < 0.0) {
			damage = 0.2;
		}

		EntityDamageEvent ede = this.makeEvent(rodder, player, damage);
		Bukkit.getPluginManager().callEvent(ede);

		if (this.getSection().getBoolean("checkCancelled") && ede.isCancelled()) {

			return;
		}

		player.damage(damage);
		Location loc = player.getLocation().add(0.0, 0.5, 0.0);
		player.teleport(loc);
		player.setVelocity(loc.subtract(rodder.getLocation()).toVector().normalize().multiply(0.4));
	}

	// 收回鱼竿
	/*
	 * @EventHandler(priority = EventPriority.HIGHEST) private void
	 * onReelIn(final PlayerFishEvent e) { if
	 * (!this.isSettingEnabled("cancelDraggingIn") || e.getState() !=
	 * PlayerFishEvent.State.CAUGHT_ENTITY) { return; } e.getHook().remove();
	 * e.setCancelled(true); }
	 */

	@SuppressWarnings("deprecation")
	private EntityDamageByEntityEvent makeEvent(Player rodder, Player player, double damage) {

		return new EntityDamageByEntityEvent(rodder, player, DamageCause.PROJECTILE, damage);

		// return new EntityDamageByEntityEvent((Entity)rodder, (Entity)player,
		// EntityDamageEvent.DamageCause.PROJECTILE, (Map)new
		// EnumMap((Map)ImmutableMap.of((Object)EntityDamageEvent.DamageModifier.BASE,
		// (Object)damage)), (Map)new
		// EnumMap((Map)ImmutableMap.of((Object)EntityDamageEvent.DamageModifier.BASE,
		// (Object)Functions.constant((Object)damage))));
	}

}
