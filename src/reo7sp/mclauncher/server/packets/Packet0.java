package reo7sp.mclauncher.server.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import reo7sp.mclauncher.server.Packet;

/**
 * Created by reo7sp on 9/17/13 at 8:35 PM
 */
public class Packet0 extends Packet {
	private boolean isPong;

	@Override
	public void readInput(DataInputStream in) throws IOException {
		isPong = in.readBoolean();
	}

	@Override
	public void writeOutput(DataOutputStream out) throws IOException {
		out.writeBoolean(isPong);
	}

	public boolean isPong() {
		return isPong;
	}

	public void setPong(boolean pong) {
		isPong = pong;
	}

	@Override
	public int getID() {
		return 0;
	}
}
