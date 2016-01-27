package reo7sp.mclauncher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import reo7sp.mclauncher.server.AuthServer;

/**
 * Created by reo7sp on 9/17/13 at 7:48 PM
 */
public class Core extends JavaPlugin {
	private static Core instance;
	private AuthServer authServer;

	@Override
	public void onEnable() {
		instance = this;

		// auth server
		try {
			authServer = new AuthServer();
		} catch (IOException err) {
			err.printStackTrace();
		}

		// listener
		Bukkit.getPluginManager().registerEvents(new McListener(), this);
	}

	public static Core getInstance() {
		return instance;
	}

	public AuthServer getAuthServer() {
		return authServer;
	}
}
