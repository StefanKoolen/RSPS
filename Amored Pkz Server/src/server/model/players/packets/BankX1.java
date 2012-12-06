package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
/**
 * Bank X Items
 **/
public class BankX1 implements PacketType {

	public static final int PART1 = 135;
	public static final int	PART2 = 208;
	public int XremoveSlot, XinterfaceID, XremoveID, Xamount;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if (packetType == 135) {
			if(c.storing) {
				
				return;
			}
			c.xRemoveSlot = c.getInStream().readSignedWordBigEndian();
			c.xInterfaceId = c.getInStream().readUnsignedWordA();
			c.xRemoveId = c.getInStream().readSignedWordBigEndian();
		}
if (c.xInterfaceId == 3900) {
    c.buyingX = true;
    c.outStream.createFrame(27);
    return;
}
			if(c.storing) {
				
				return;
			}

		if(packetType == PART1) {
			synchronized(c) {
			if(c.storing) {
				
				return;
			}
				c.getOutStream().createFrame(27);
			}			
		}
	
	}
}
