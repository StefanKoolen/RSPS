package server.model.minigames;

import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class FightCaves {
	private final int[][] WAVES = {{2627},{2627},{2627},{2743},{2630},{2630},{2630},{2630},{2630},{2631},{2627},{2631},{2631},{2631},{2631},{2631},{2741},{2741},{2630},{2741},{2741},{2741},
									{2743},{2743},{2743},{2743},{2631},{2741},{2741},{2743},{2743},{2745}};
	private int[][] coordinates = {{2398,5086},{2387,5095},{2407,5098},{2417,5082},{2390,5076},{2410, 5090}};
	//2743 = 360, 2627 = 22, 2630 = 45, 2631 = 90, 2741 = 180
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
				Server.npcHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk, def, true, false);				
			}
			c.tzhaarToKill = npcAmount;
			c.tzhaarKilled = 0;
		}
	}
	
	public int getHp(int npc) {
		switch (npc) {
			case 2627:
			return 10;
			case 2630:
			return 20;
			case 2631:
			return 40;
			case 2741:
			return 80;
			case 2743: 
			return 150;
			case 2745:
			return 250;		
		}
		return 100;
	}
	
	public int getMax(int npc) {
		switch (npc) {
			case 2627:
			return 4;
			case 2630:
			return 7;
			case 2631:
			return 13;
			case 2741:
			return 28;
			case 2743: 
			return 54;
			case 2745:
			return 97;		
		}
		return 5;
	}
	
	public int getAtk(int npc) {
		switch (npc) {
			case 2627:
			return 30;
			case 2630:
			return 50;
			case 2631:
			return 100;
			case 2741:
			return 150;
			case 2743: 
			return 450;
			case 2745:
			return 650;		
		}
		return 100;
	}
	
	public int getDef(int npc) {
		switch (npc) {
			case 2627:
			return 30;
			case 2630:
			return 50;
			case 2631:
			return 100;
			case 2741:
			return 150;
			case 2743: 
			return 300;
			case 2745:
			return 500;		
		}
		return 100;
	}
	

}