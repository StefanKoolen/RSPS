package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
/**
 * Bank 10 Items
 **/
public class Bank10 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	int interfaceId = c.getInStream().readUnsignedWordBigEndian();
	int removeId = c.getInStream().readUnsignedWordA();
	int removeSlot = c.getInStream().readUnsignedWordA();
					
		switch(interfaceId){
			case 1688:
				c.getPA().useOperate(removeId);
			break;
			case 3900:
			c.getShops().buyItem(removeId, removeSlot, 5);
			break;
			
			case 3823:
			c.getShops().sellItem(removeId, removeSlot, 5);
			break;	

			case 5064:
				if(c.inTrade) {
				c.sendMessage("You can't store items while trading!");
				return;
			}
			if(c.storing) {
				
				return;
			}
			c.getItems().bankItem(removeId, removeSlot, 10);
			break;
			
			case 5382:
			if(c.storing) {
				
				return;
			}
			c.getItems().fromBank(removeId, removeSlot, 10);
			break;
			
			case 3322:
			if(c.storing) {
				
				return;
			}
			if(c.duelStatus <= 0) { 
                c.getTradeAndDuel().tradeItem(removeId, removeSlot, 10);
           	} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 10);
			}	
			break;
			
			case 3415:
			if(c.storing) {
				
				return;
			}
			if(c.duelStatus <= 0) { 
				c.getTradeAndDuel().fromTrade(removeId, removeSlot, 10);
           	} 
			break;
			
			case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 10);
			break;
			
			case 1119:
			case 1120:
			case 1121:
			case 1122:
			case 1123:
				c.getSmithing().readInput(c.playerLevel[c.playerSmithing], Integer.toString(removeId), c, 5);
			break;
		}	
	}

}
