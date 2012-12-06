package server.model.players.packets;


import server.model.players.Client;
import server.model.players.PacketType;


public class IdleLogout implements PacketType {
	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		//if (!c.playerName.equalsIgnoreCase("Sabsabi"))
			//c.logout();
	}
}