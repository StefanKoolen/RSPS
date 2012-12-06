package server.model.minigames;

import server.model.players.Client;
import server.Server;
import server.model.npcs.NPCHandler;

/**
 * @author Runeika
 */

public class ZombieCaves {
	private final int[][] WAVES = {{73},{73,73},{73,73,73,73},{73,73,73,73,73,73,73,1465},{1465,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73},{1465,1465,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73}};
	private int[][] coordinates = {{2603,9906},{2604,9906},{2603,9907},{2603,9908},{2602,9906},{2601,9906}};
	//73,74,75 normal zombies 1465,1466 monkey zombies
	public void spawnNextWave(Client c) {
		if (c != null) {
			if (c.waveId >= WAVES.length) {
				c.waveId = 0;
				return;				
			}
			if (c.waveId < 0){
			return;
			}
			int npcAmount = WAVES[c.waveId].length;
			for (int j = 0; j < npcAmount; j++) {
				int npc = WAVES[c.waveId][j];
				int X = coordinates[j][0];
				int Y = coordinates[j][1];
				int H = c.heightLevel;
				int hp = getHp(npc);
				int max = getMax(npc);
				int atk = getAtk(npc);
				int def = getDef(npc);
				Server.npcHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk, def, true, true);				
			}
			c.zombiesKilled = npcAmount;
			c.zombiesToKill = 0;
		}
	}
	
	public int getHp(int npc) {
		switch (npc) {
			case 3493:
			return 254;
			case 3494:
			return 254;
			case 3495:
			return 254;
			case 3496:
			return 254;
			case 3491: 
			return 254;		
		}
		return 100;
	}
	
	public int getMax(int npc) {
		switch (npc) {
			case 3493:
			return 20;
			case 3494:
			return 25;
			case 3495:
			return 15;
			case 3496:
			return 25;
			case 3491:
			return 30;		
		}
		return 5;
	}
	
	public int getAtk(int npc) {
		switch (npc) {
			case 3493:
			return 225;
			case 3494:
			return 250;
			case 3495:
			return 300;
			case 3496:
			return 329;
			case 3491: 
			return 400;		
		}
		return 100;
	}
	
	public int getDef(int npc) {
		switch (npc) {
			case 3493:
			return 300;
			case 3494:
			return 350;
			case 3495:
			return 400;
			case 3496:
			return 520;
			case 3491:
			return 600;		
		}
		return 100;
	}
	

}