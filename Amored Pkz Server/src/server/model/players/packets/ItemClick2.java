package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		
		if (!c.getItems().playerHasItem(itemId,1))
			return;

		switch (itemId) {
			case 11283:
			case 11284:
			case 11285:
			c.sendMessage("Your shield has "+c.dfsCount+" charges");
			break;

			case 11694:

				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11696:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11698:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11700:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			
			
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId);
			break;
		}

	}

}
