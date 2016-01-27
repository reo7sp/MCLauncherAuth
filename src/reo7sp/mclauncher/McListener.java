package reo7sp.mclauncher;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by reo7sp on 9/17/13 at 7:54 PM
 */
public class McListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!Core.getInstance().getAuthServer().isVerified(event.getPlayer().getName())) {
			event.getPlayer().kickPlayer("Please restart the game with our launcher");
			Core.getInstance().getLogger().info("Kicked " + event.getPlayer().getName() + " because he or she is without our launcher!");
		}
	}
}
