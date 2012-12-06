package server.model.minigames;

import server.model.players.Client;
import server.Server;
import server.model.npcs.*;
import server.model.players.*;

/**
 * @author Sanity
 */

public class PestControl {
	
	public PestControl() {
		
	}
	
	public final int GAME_TIMER = 40; //5 minutes
	public final int WAIT_TIMER = 7;
	
	public int gameTimer = -1;
	public int waitTimer = 15;
	public int properTimer = 0;
	
	public void process() {
		setInterface();
		if (properTimer > 0) {
			properTimer--;
			return;
		} else {
			properTimer = 4;
		}
		if (waitTimer > 0)
			waitTimer--;
		else if (waitTimer == 0)
			startGame();
		if (gameTimer > 0) {
			gameTimer--;
			if (allPortalsDead()) {
				endGame(true);
			}
		} else if (gameTimer == 0)
			endGame(false);
	}
	
	public void startGame() {
		if (playersInBoat() > 1) {
			gameTimer = GAME_TIMER;
			waitTimer = -1;
			//spawn npcs
			spawnNpcs();	
			setInterface();
			//move players into game
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].inPcBoat()) {
						movePlayer(j);
					}			
				}		
			}
		} else {
			waitTimer = WAIT_TIMER;
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].inPcBoat()) {
						Client c = (Client)Server.playerHandler.players[j];
						c.sendMessage("There need to be at least 2 players to start a game of pest control.");
					}			
				}		
			}
		}
	}
	
	public int playersInBoat() {
		int count = 0;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				if (Server.playerHandler.players[j].inPcBoat()) {
						count++;
				}
			}
		}
		return count;
	}
	
	public void endGame(boolean won) {
		gameTimer = -1;
		waitTimer = WAIT_TIMER;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				if (Server.playerHandler.players[j].inPcGame()) {
					Client c = (Client)Server.playerHandler.players[j];
					c.getPA().movePlayer(2657, 2639, 0);
					if (won && c.pcDamage > 4) {
						c.sendMessage("You have won the pest control game and have been awarded 8 pest control points.");
						c.pcPoints += 8;
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
						c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
						c.specAmount = 10;
						c.getItems().addItem(995, c.combatLevel * 50);
						c.getPA().refreshSkill(3);
						c.getPA().refreshSkill(5);
					} else if (won) {
						c.sendMessage("The void knights notice your lack of zeal.");
					} else {
						c.sendMessage("You failed to kill all the portals in 5 minutes and have not been awarded any points.");
					}
					c.pcDamage = 0;
					c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
					c.getCombat().resetPrayers();
				}			
			}		
		}

		for (int j = 0; j < Server.npcHandler.npcs.length; j++) {
			if (Server.npcHandler.npcs[j] != null) {
				if (Server.npcHandler.npcs[j].npcType > 6144 && Server.npcHandler.npcs[j].npcType < 6146)
					Server.npcHandler.npcs[j] = null;
			}			
		}
	}
	public void setInterface() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].inPcBoat()) {
					Client c = (Client) PlayerHandler.players[j];
					c.getPA().sendFrame126("Next Departure: "+waitTimer+"", 21006);
					c.getPA().sendFrame126("Players Ready: "+playersInBoat()+"", 21007);
					c.getPA().sendFrame126("(Need 2 to 25 players)", 21008);
					c.getPA().sendFrame126("Points: "+c.pcPoints+"", 21009);
			  c.getPA().walkableInterface(21005);
				}
				if (PlayerHandler.players[j].inPcGame()) {
					Client c = (Client) PlayerHandler.players[j];
					for (j = 0; j < NPCHandler.npcs.length; j++) {
						if (NPCHandler.npcs[j] != null) {
							if (NPCHandler.npcs[j].npcType == 6145)
								c.getPA().sendFrame126("" + NPCHandler.npcs[j].HP + "", 21114);
						}
					}

					c.getPA().sendFrame126("PORTAL", 21110);
					c.getPA().sendFrame126(""+c.pcDamage+"", 21115);
					c.getPA().sendFrame126("500", 21116);
					c.getPA().sendFrame126("Time remaining: "+gameTimer+"", 21117);
					 c.getPA().walkableInterface(21100);
				}
			}
		}
	}
	
	public boolean allPortalsDead() {
		int count = 0;
		for (int j = 0; j < Server.npcHandler.npcs.length; j++) {
			if (Server.npcHandler.npcs[j] != null) {
				if (Server.npcHandler.npcs[j].npcType > 6144 && Server.npcHandler.npcs[j].npcType < 6146)
					if (Server.npcHandler.npcs[j].needRespawn)
						count++;		
			}			
		}
		return count >= 1;	
	}
	
	public void movePlayer(int index) {
		Client c = (Client)Server.playerHandler.players[index];
		if (c.combatLevel < 10) {
			c.sendMessage("You must be at least 10 combat level to enter this boat.");
			return;
		}
		c.getPA().movePlayer(2658,2611,0);
	}
	
	public void spawnNpcs() {
		Server.npcHandler.spawnNpc2(6145,2656,2592,0,0,1200,0,0,100);

	}


}