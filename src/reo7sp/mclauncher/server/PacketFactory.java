package reo7sp.mclauncher.server;

import reo7sp.mclauncher.server.packets.Packet0;
import reo7sp.mclauncher.server.packets.Packet1;

/**
 * Created by reo7sp on 9/17/13 at 8:24 PM
 */
public class PacketFactory {
	private static final Class[] packets = {
			Packet0.class,
			Packet1.class,
	};

	public static Packet newPacket(int id) throws Exception {
		return (Packet) packets[id].newInstance();
	}
}
