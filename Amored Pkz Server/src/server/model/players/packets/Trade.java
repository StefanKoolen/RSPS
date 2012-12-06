package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Trading
 */
public class Trade implements PacketType {
	public boolean inTrade;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		
		if (c.inTrade) {
		c.sendMessage("You cannot walk while in a trade.");
		return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
                if (c.InDung()) {
                         c.sendMessage("You cannot trade inside Dungoneering!");
                         return;
		}
		if (c.inWild()) {
                         c.sendMessage("You can't trade in the wilderness!");
                         return;
		}
		if(c.playerRights == 2 && !Config.ADMIN_CAN_TRADE) {
			c.sendMessage("Trading as an admin has been disabled.");
			return;
		}
		if (c.playerName.equalsIgnoreCase("")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}

		if (c.playerName.equalsIgnoreCase("death days")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}

		if (c.playerName.equalsIgnoreCase("layne")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}
		
}
