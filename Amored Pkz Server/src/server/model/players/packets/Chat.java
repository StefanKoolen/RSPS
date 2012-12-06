package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.Connection;
import server.util.Misc;

/**
 * Chat : Credits to Coder Alex account
 **/
public class Chat implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
        c.setChatTextSize((byte)(c.packetSize - 2));
        c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
			String[] stuffz = { ",net", ",runescape", ",org", ",com", ",be", ",nl", ",info", "dspk",".info" , ".org", ".tk", ".net", ".com", ".co.uk", ".be", ".nl", ".dk", ".co.cz", ".co", ".us", ".biz", ".eu", ".de", ".cc", ".i n f o", ".o r g", ".t k", ".n e t", ".c o m", ".c o . u k", ".b e", ".n l", ".d k", ".c o . c z", ".c o", ".u s", ".b i z", ".e u", ".d e", ".c c"};
					String term = Misc.textUnpack(c.getChatText(), c.packetSize - 2).toLowerCase();
			if(System.currentTimeMillis() < c.muteEnd) {
			c.sendMessage("You are muted and cannot talk for another " + c.muteEnd + " Seconds.");
			return;
			} else {
			c.muteEnd = 0;
			}
			if(c.said >= 15000 && c.playerRights <= 3){
			c.sendMessage("You have been muted!");
			Connection.addNameToMuteList(c.playerName);
			}
			for(int i = 0; i < stuffz.length; i++) {
					if(term.contains(stuffz[i]) && c.playerRights <= 2) {
					c.said++;
					c.sendMessage("Advertising has been disabled, GF!");
c.sendMessage("Dont spam this or you will be automuted!");
						return;
					}
					}
if (!Connection.isMuted(c)){
	c.setChatTextUpdateRequired(true);
} else if (Connection.isMuted(c)){
	c.sendMessage("You are muted, no one can hear you.");
	return;
		}
	}
}