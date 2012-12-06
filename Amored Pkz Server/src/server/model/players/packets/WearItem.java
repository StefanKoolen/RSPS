package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;


/**
 * Wear Item
 **/
 
public class WearItem implements PacketType {

		@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		boolean torvaChanged = false;
		if (!c.getItems().playerHasItem(c.wearId, 1, c.wearSlot)) {
			return;
		}
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		if (c.wearId >= 5509 && c.wearId <= 5515) {
			int pouch = -1;
			int a = c.wearId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().emptyPouch(pouch);
			return;
		}
		
		

if(c.wearId == 7927) {
		c.resetWalkingQueue();
for (int i = 0; i < 14; i++) {
	c.setSidebarInterface(i, 6014);
}
c.sendMessage("As you put on the ring you turn into an egg!");
//c.npcId2 = 3689 + Misc.random(5);
c.isNpc = true;
c.updateRequired = true;
c.appearanceUpdateRequired = true;
}
if(c.wearId == 4565) {
c.sendMessage("You Are Now Skipping!!!!");
c.startAnimation(1836);
c.updateRequired = true;
c.appearanceUpdateRequired = true;
}
if (c.wearSlot == 0 || c.wearSlot == 4 || c.wearSlot == 7) {
if (c.playerEquipment[c.wearSlot] == 20143 || c.playerEquipment[c.wearSlot] == 20139 || c.playerEquipment[c.wearSlot] == 20135)
torvaChanged = true;
}
c.getItems().wearItem(c.wearId, c.wearSlot);
if (torvaChanged && c.playerLevel[3] > c.calculateMaxLifePoints()) {
c.playerLevel[3] = c.calculateMaxLifePoints();
c.getPA().refreshSkill(3);
}
                        //c.attackTimer = oldCombatTimer;
		c.getItems().wearItem(c.wearId, c.wearSlot);
	}
}