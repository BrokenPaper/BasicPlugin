package cc.mcpvp.baseplugin.module;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cc.mcpvp.baseplugin.BasicPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class ModuleAntiBot extends Module {

	ArrayList<Bot> bots = new ArrayList<ModuleAntiBot.Bot>();

	public ModuleAntiBot(BasicPlugin plugin) {

		super(plugin, "anti-bot");

		init();

		BukkitRunnable task = new BukkitRunnable() {

			@Override
			public void run() {

				for (Bot bot : bots) {
					bot.setLoginTime(0);
				}

			}
		};

		task.runTaskTimerAsynchronously(plugin, 0, 20 * 60);
	}

	public void init() {

		PacketAdapter PacketB = new PacketAdapter(plugin, ListenerPriority.HIGHEST, new PacketType[] { PacketType.Login.Client.START }) {

			public void onPacketReceiving(PacketEvent event) {

				Bot bot = new Bot(event.getPlayer().getAddress().getAddress().getHostAddress());

				if (!bots.contains(bot)) {
					bots.add(bot);
				}
				// bot.setLoginTime(bot.getLoginTime() + 1);
				Player player = event.getPlayer();
				if (bot.getLoginTime() > getSection().getInt("login-time")) {

					player.kickPlayer("你登陆的次数过多,请稍等一段时间再次登陆.");

					plugin.getLogger().info(String.format("[!] %s (%s) 超过登陆次数,已被禁止登陆", player.getName(), bot.getAddress()));

					event.setCancelled(true);

				}

			}
		};
		ProtocolLibrary.getProtocolManager().addPacketListener(PacketB);

	}

	class Bot {

		private String address;
		private int loginTime = 0;

		public Bot(String address) {

			this.address = address;

		}

		public String getAddress() {
			return address;
		}

		public int getLoginTime() {
			return loginTime;
		}

		public void setLoginTime(int loginTime) {
			this.loginTime = loginTime;
		}
	}

}
