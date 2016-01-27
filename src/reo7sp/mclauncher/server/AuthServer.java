package reo7sp.mclauncher.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import reo7sp.mclauncher.Core;
import reo7sp.mclauncher.server.packets.Packet0;

/**
 * Created by reo7sp on 9/17/13 at 7:54 PM
 */
public class AuthServer {
	private static final int PORT = 29517;
	private Collection<Connection> connections = new HashSet<Connection>();

	public AuthServer() throws IOException {
		initCommunicatorThread();
		initPingThread();
	}

	public boolean isVerified(String playerName) {
		for (Connection connection : connections) {
			if (connection.getPlayerName() != null && connection.getPlayerName().equals(playerName)) {
				return true;
			}
		}
		return false;
	}

	private void initCommunicatorThread() throws IOException {
		new Communicator().start();
	}

	private void initPingThread() {
		new Thread("MCLauncher-Ping") {
			@Override
			public void run() {
				try {
					while (!isInterrupted()) {
						Thread.sleep(30000);

						for (Iterator<Connection> iterator = connections.iterator(); iterator.hasNext(); ) {
							Connection connection = iterator.next();
							try {
								connection.sendPacket(new Packet0());
							} catch (IOException err) {
								try {
									connection.disconnect();
									iterator.remove();
								} catch (IOException ignored) {
								}
							}
						}
					}
				} catch (InterruptedException ignored) {
				}
			}
		}.start();
	}

	private class Communicator extends Thread {
		public ServerSocket socket;

		public Communicator() throws IOException {
			super("MCLauncher-AuthServerCommunicator");
			Core.getInstance().getLogger().info("Binding port " + PORT);
			socket = new ServerSocket(PORT);
		}

		@Override
		public void run() {
			try {
				while (!isInterrupted()) {
					connections.add(new Connection(socket.accept()));
				}
			} catch (IOException err) {
				err.printStackTrace();
			}

			try {
				for (Connection connection : connections) {
					connection.disconnect();
				}
				connections.clear();
				socket.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
}
