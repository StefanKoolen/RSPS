package server.model.players.skills;


import server.Config;
import server.Server;
import server.model.players.Client;
import server.util.Misc;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Prayer {

	Client c;
	
	public int[][] bonesExp = {
        {526, 5},   //  NPC BONES
        {528, 5},   //  BURNT BONES
        {530, 5},   //  BAT BONES
        {2859, 5},  //  WOLF BONES
        {3179, 5},  //  MONKEY BONES
        {3180, 5},  //  MONKEY BONES
        {3181, 5},  //  MONKEY BONES
        {3182, 5},  //  MONKEY BONES
        {3183, 5},  //  MONKEY BONES
        {3185, 5},  //  MONKEY BONES
        {3186, 5},  //  MONKEY BONES
        {3187, 5},  //  MONKEY BONES
        {532, 15},  //  BIG BONES
        {534, 30},  //  BABY DRAGON BONES
        {536, 72},  //  DRAGON BONES
        {2530, 5},  //  PLAYER BONES
        {3123, 25},     //  SHAIKAHAN BONES
        {3125, 23},     //  JOGRE BONES
        {3127, 25},     //  BURNT JOGRE BONES
        {4812, 82},     //  ZOGRE BONES
        {4830, 84},     //  FAYGR BONES
        {4832, 96}, //  RAURG BONES
        {4834, 140},    //  OURG BONES
        {6729, 125},    //  DAGANNOTH BONES
        {6812, 50},     //  WYVERN BONES
        {10976, 1000}, //   LONG BONE
        {10977, 1250}, //   CURVED BONE
        {11337, 2000}, //   MANGLED BONE
        {11338, 2500}, //   CHEWED BONE
    };
	
	public Prayer(Client c) {
		this.c = c;
	}
	
	public void buryBone(int id, int slot) {
		if(System.currentTimeMillis() - c.buryDelay > 1500) {
			c.getItems().deleteItem(id, slot, 1);
			c.sendMessage("You bury the bones.");
			c.getPA().addSkillXP(getExp(id)*Config.PRAYER_EXPERIENCE,5);
			c.buryDelay = System.currentTimeMillis();
			c.startAnimation(827);
			//handleZombie();
		}	
	}
		/*public void handleZombie() {
		int random = Misc.random(50);
		if(random == 50) {
			if(c.combatLevel >= 3 && c.combatLevel <= 25) {
				Server.npcHandler.spawnNpc(c, 419, c.getX(), c.getY()-1, 0, 0, 120, 5, 50, 50, true, false);
			} else if(c.combatLevel >= 26 && c.combatLevel <= 50) {
				Server.npcHandler.spawnNpc(c, 421, c.getX(), c.getY()-1, 0, 0, 120, 8, 75, 75, true, false);
			} else if(c.combatLevel >= 51 && c.combatLevel <= 99) {
				Server.npcHandler.spawnNpc(c, 422, c.getX(), c.getY()-1, 0, 0, 120, 13, 120, 120, true, false);
			} else if(c.combatLevel >= 100 && c.combatLevel < 126) {
				Server.npcHandler.spawnNpc(c, 423, c.getX(), c.getY()-1, 0, 0, 120, 18, 175, 175, true, false);
			} else if(c.combatLevel == 126) {
				Server.npcHandler.spawnNpc(c, 424, c.getX(), c.getY()-1, 0, 0, 120, 18, 210, 210, true, false);
			}
	}
	}*/
	public void bonesOnAltar(int id) {
		c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
		c.sendMessage("The gods are pleased with your offering.");
		c.getPA().addSkillXP(getExp(id)*2*Config.PRAYER_EXPERIENCE, 5);
		//handleZombie();
	}
	
	public boolean isBone(int id) {
		for (int j = 0; j < bonesExp.length; j++)
			if (bonesExp[j][0] == id)
				return true;
		return false;
	}
	
	public int getExp(int id) {
		for (int j = 0; j < bonesExp.length; j++) {
			if (bonesExp[j][0] == id)
				return bonesExp[j][1];
		}
		return 0;
	}
}