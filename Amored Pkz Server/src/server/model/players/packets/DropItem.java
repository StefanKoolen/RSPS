package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		if (c.inTrade) {
			c.sendMessage("You cannot drop items in the trade screen.");
			return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You can't drop items inside Dung!");
			return;
		}	
		if(!c.getItems().playerHasItem(itemId,1,slot)) {
			return;
		}
		if (c.playerRights == 2)
		{
		c.sendMessage("Admins are not allowed to drop items!");
		return;
		}
		if (c.playerName.equalsIgnoreCase("layne")) {
		c.sendMessage("Get A Life Kid!");
		return;
		}
		if (System.currentTimeMillis() - c.alchDelay < 1800)
			return;
		boolean droppable = true;
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				droppable = false;
				break;
			}
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(droppable) {
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 10000) {
						c.sendMessage("You may not drop items worth more than 10,000gp while in combat.");
						return;
					}
				}

				Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			c.SaveGame();
			} else {
				c.sendMessage("This item cannot be dropped.");
			}
		}
			if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
if(!c.getItems().playerHasItem(itemId,1,slot)) {
			c.sendMessage("Stop cheating!");
			return;
			}
		}
	}
}
