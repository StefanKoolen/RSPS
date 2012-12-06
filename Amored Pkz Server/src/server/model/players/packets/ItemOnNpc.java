package server.model.players.packets;

import server.Server;
import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;


public class ItemOnNpc implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		int i = c.getInStream().readSignedWordA();
		int slot = c.getInStream().readSignedWordBigEndian();
		int npcId = Server.npcHandler.npcs[i].npcType;
		
		UseItem.ItemonNpc(c, itemId, npcId, slot);
		

	}

}