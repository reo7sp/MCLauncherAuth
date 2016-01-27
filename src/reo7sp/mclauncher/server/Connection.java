package reo7sp.mclauncher.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import reo7sp.mclauncher.Core;
import reo7sp.mclauncher.server.packets.Packet0;
import reo7sp.mclauncher.server.packets.Packet1;

/**
 * Created by reo7sp on 9/17/13 at 7:54 PM
 */
public class Connection extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String playerName;

	public Connection(Socket socket) throws IOException {
		super("MCLauncher-Connection");
		Core.getInstance().getLogger().info("New connection from " + socket.getInetAddress());
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		start();
	}

	@Override
	public void run() {
		try {
			while (!socket.isClosed()) {
				int id = in.readByte();
				Packet packet = PacketFactory.newPacket(id);
				packet.readInput(in);
				handlePacket(packet);
			}
		} catch (Exception err) {
			try {
				disconnect();
			} catch (IOException ignored) {
			}
		}
	}

	private void handlePacket(Packet packetRaw) throws Exception {
		switch (packetRaw.getID()) {
			case 0:
				Packet0 packet0 = (Packet0) packetRaw;
				if (!packet0.isPong()) {
					Packet0 packetToSend = new Packet0();
					packetToSend.setPong(true);
					sendPacket(packetToSend);
				}
				break;

			case 1:
				Packet1 packet1 = (Packet1) packetRaw;
				playerName = packet1.getPlayerName();
				Core.getInstance().getLogger().info(socket.getInetAddress() + " logined with nick \"" + playerName + "\"");
				break;
		}
	}

	public void sendPacket(Packet packet) throws IOException {
		out.writeByte(packet.getID());
		packet.writeOutput(out);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void disconnect() throws IOException {
		Core.getInstance().getLogger().info(socket.getInetAddress() + " \"" + playerName + "\"disconnected");
		in.close();
		out.close();
		socket.close();
	}
}
