package server.model.players.skills;

import server.model.players.Client;
import server.Server;
import server.util.Misc;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Slayer.java
 *
 * @author Sanity
 *
 **/
 
public class Slayer {
	
	private Client c;
		
	public Slayer(Client c) {
		this.c = c;
	}
	
	public int[] lowTasks = {117, 1265, 103, 78, 119, 18,1265,181};
	public int[] lowReqs = {1, 1, 1, 1, 1, 1, 1, 1,1,1,1};
	public int[] medTasks = {1643,1618,941,119,82,52,1612,117,1265,112,125,1341};
	public int[] medReqs = {45,50,1,1,1,1,15,1,1,1,1,1};
	public int[] highTasks = {1624,1610,1613,1615,55,84,49,1618,941,82,2783,53};
	public int[] highReqs = {65,75,80,85,1,1,1,50,1,90,1,70};
	public int[] eliteTasks = {3943,8596,3847,8528,53,9947,10775,9467,9465,9463,6260,2881,2882,2883,8133,8349};
	public int[] eliteReqs = {80,95,83,90,70,99,92,72,82,86,92,85,85,85,96,94};
	
	public void giveTask() {
		if (c.combatLevel < 50)
			giveTask(1);
		else if (c.combatLevel >= 50 && c.combatLevel <= 90)
			giveTask(2);
		else if (c.combatLevel > 90 && c.combatLevel <= 121)
			giveTask(3);
		else if (c.combatLevel >= 122 && c.combatLevel <= 138)
			giveTask(4);
		else
			giveTask(2);	
	}
	
	public void giveTask2() {
		for (int j = 0; j < lowTasks.length; j++) {
			if (lowTasks[j] == c.slayerTask) {
				c.sendMessage("You already have an easy task... to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".");
				return;		
			}	
		}
		giveTask(1);
	}
	
	public void giveTask(int taskLevel) {
		int given = 0;
		int random = 0;
		if (taskLevel == 1) {
			random = (int)(Math.random() * (lowTasks.length - 1));
			given = lowTasks[random];
		} else if (taskLevel == 2) {
			random = (int)(Math.random() * (medTasks.length - 1));
			given = medTasks[random];		
		} else if (taskLevel == 3) {
			random = (int)(Math.random() * (highTasks.length - 1));
			given = highTasks[random];	
		} else if (taskLevel == 4) {
			random = (int)(Math.random() * (eliteTasks.length - 1));
			given = eliteTasks[random];				
		}
		if (taskLevel <= 3) {
			c.slayerTask = given;
			c.taskAmount = Misc.random(15) + 15;
			c.sendMessage("You have been assigned to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(given) + " as a slayer task.");
		} else {
			c.slayerTask = given;
			c.taskAmount = Misc.random(5) + 1;
			c.sendMessage("You have been assigned to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(given) + " as an elite slayer task.");
		}
		/*if (!canDoTask(taskLevel,random)) {
			giveTask(taskLevel);
			return;
		}*/
	}
	
	public boolean canDoTask(int taskLevel, int random) {
		if (taskLevel == 1) {
			return c.playerLevel[c.playerSlayer] >= lowReqs[random];		
		} else if (taskLevel == 2) {
			return c.playerLevel[c.playerSlayer] >= medReqs[random];		
		} else if (taskLevel == 3) {
			return c.playerLevel[c.playerSlayer] >= highReqs[random];	
		} else if (taskLevel == 4) {
			return c.playerLevel[c.playerSlayer] >= eliteReqs[random];			
		}
		return false;
	}
}