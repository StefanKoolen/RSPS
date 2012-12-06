package server.model.players.skills;

import server.model.players.*;
import server.Config;
import server.util.Misc;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* @Author Sanity
*/

public class Mining {
	
	Client c;
	
	private final int VALID_PICK[] = {1265,1267,1269,1273,1271,1275};
	private final int[] PICK_REQS = {1,1,6,6,21,31,41,61};
	private final int[] RANDOM_GEMS = {1623,1621,1619,1617,1631};
	private int oreType;
	private int exp;
	private int levelReq;
	private int pickType;
	private final int EMOTE = 624;
	
	public Mining(Client c) {
		this.c = c;
	}
	
	public void startMining(int oreType, int levelReq, int exp) {
		c.turnPlayerTo(c.objectX, c.objectY);
		if (goodPick() > 0) {
			if (c.playerLevel[c.playerMining] >= levelReq) {
				this.oreType = oreType;
				this.exp = exp;
				this.levelReq = levelReq;
				this.pickType = goodPick();
				c.sendMessage("You swing your pick at the rock.");
				c.miningTimer = getMiningTimer(oreType);
				c.startAnimation(EMOTE);
			} else {
				resetMining();
				c.sendMessage("You need a mining level of " + levelReq + " to mine this rock.");
				c.startAnimation(65535);
			}		
		} else {
			resetMining();
			c.sendMessage("You need a pickaxe to mine this rock.");
			c.startAnimation(65535);
			c.getPA().resetVariables();
		}
	}
	
	public void mineOre() {
		if (c.getItems().addItem(oreType,1)) {
			c.startAnimation(EMOTE);
			c.sendMessage("You manage to mine some ore.");
			c.getPA().addSkillXP(exp * Config.MINING_EXPERIENCE, c.playerMining);
			c.getPA().refreshSkill(c.playerMining);
			c.miningTimer = getMiningTimer(oreType);
			if (Misc.random(25) == 10) {
				c.getItems().addItem(RANDOM_GEMS[(int)(RANDOM_GEMS.length * Math.random())], 1);
				c.sendMessage("You find a gem!");
			}
		} else {
			c.getPA().resetVariables();
			c.startAnimation(65535);
		}
	}
	
	public void resetMining() {
		this.oreType = -1;
		this.exp = -1;
		this.levelReq = -1;
		this.pickType = -1;
	}
	
	public int goodPick() {
		for (int j = VALID_PICK.length - 1; j >= 0; j--) {
			if (c.playerEquipment[c.playerWeapon] == VALID_PICK[j]) {
				if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
					return VALID_PICK[j];
			}		
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			for (int j = VALID_PICK.length - 1; j >= 0; j--) {
				if (c.playerItems[i] == VALID_PICK[j] + 1) {
					if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
						return VALID_PICK[j];
				}
			}		
		}
		return - 1;
	}
	
	public int getMiningTimer(int ore) {
		int time = Misc.random(5);
		if (ore == 451) {
			time += 4;
		}
		return time;
	}
	
}