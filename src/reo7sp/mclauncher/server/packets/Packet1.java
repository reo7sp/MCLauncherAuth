package reo7sp.mclauncher.server.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import reo7sp.mclauncher.server.Packet;

/**
 * Created by reo7sp on 9/17/13 at 8:30 PM
 */
public class Packet1 extends Packet {
	private String playerName;

	@Override
	public void readInput(DataInputStream in) throws IOException {
		playerName = in.readUTF();
	}

	@Override
	public void writeOutput(DataOutputStream out) throws IOException {
	}

	@Override
	public int getID() {
		return 1;
	}

	public String getPlayerName() {
		return playerName;
	}
}
