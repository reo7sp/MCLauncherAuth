package reo7sp.mclauncher.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by reo7sp on 9/17/13 at 7:57 PM
 */
public abstract class Packet {
	public abstract void readInput(DataInputStream in) throws IOException;

	public abstract void writeOutput(DataOutputStream out) throws IOException;

	public abstract int getID();
}
