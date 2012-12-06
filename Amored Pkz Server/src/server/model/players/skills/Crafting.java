package server.model.players.skills;

import server.model.players.Client;
import server.Config;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Crafting {

	Client c;
	
	public Crafting(Client c) {
		this.c = c;
	}
	
	public int hideType = 0, makeId = 0, amount = 0, craftType = 0, exp = 0, index = 0;
	public void resetCrafting() {
		hideType = 0;
		makeId = 0;
		amount = 0;
		c.craftingLeather = false;
		craftType = 0;
	}
	
	public void handleChisel(int id1, int id2) {
		if (id1 == 1755)
			cutGem(id2);
		else
			cutGem(id1);	
	}
	
	public int[][] gems = {{1623,1727,1,50},{1621,1729,27,68},{1619,1725,34,85},{1617,1731,43,108},{1631,1712,55,138},{6571,6585,67,168}};	
	public void cutGem(int id) {
		for (int j = 0; j < gems.length; j++) {
			if (gems[j][0] == id) {
				if (c.playerLevel[c.playerCrafting] >= gems[j][2]) {
					c.getItems().deleteItem(id, c.getItems().getItemSlot(id),1);
					c.getItems().addItem(gems[j][1], 1);
					c.getPA().addSkillXP(gems[j][3] * Config.CRAFTING_EXPERIENCE, c.playerCrafting);
					break;
				}			
			}		
		}
	}
	
	public void handleCraftingClick(int clickId) {
		for (int j = 0; j < buttons.length; j++) {
			if (buttons[j][0] == clickId) {
				craftType = buttons[j][1];
				amount = buttons[j][2];
				checkRequirements();
				break;
			}
		}	
	}
	
	public void checkRequirements() {
		for (int j = 0; j < expsAndLevels.length; j++) {
			if (expsAndLevels[j][0] == hideType) {
				if (c.playerLevel[c.playerCrafting] >= expsAndLevels[j][1]) {
					if (c.getItems().playerHasItem(hideType, 1)) {
						c.getPA().closeAllWindows();
						exp = expsAndLevels[j][2];
						index = j;
						craftHides(hideType);
					}
				} else {
					c.sendMessage("You need a crafting level of " + expsAndLevels[j][1] + " to craft this.");
				}
			}
		}	
	}
	
	public void craftHides(int id) {
		for (int j = 0; j < amount; j++) {
			if (!c.getItems().playerHasItem(id,craftType))
				break;
			c.getItems().deleteItem(id, craftType);
			if (getItemToAdd() <= 0)
				break;
			c.getItems().addItem(getItemToAdd(), 1);
			c.getPA().addSkillXP(exp * Config.CRAFTING_EXPERIENCE, c.playerCrafting);
		}
		resetCrafting();
	}
	
	public int getItemToAdd() {
		if (craftType == 1) {
			return vambs[index];
		} else if (craftType == 2) {
			return chaps[index];
		} else if (craftType == 3) {
			return bodys[index];
		}	
		return -1;
	}
	
	public int[] vambs = {1065,2487,2489,2491};
	public int[] chaps = {1099,2493,2495,2497};
	public int[] bodys = {1135,2499,2501,2503};
	
	
	public void handleLeather(int item1, int item2) {
		if (item1 == 1733) {
			openLeather(item2);
		} else {
			openLeather(item1);
		}
	}
	
	public int[][] buttons = {{34185,1,1},{34184,1,5},{34183,1,10},{34182,1,27},{34189,2,1},{34188,2,5},{34187,2,10},{34186,2,27},{34193,3,1},{34192,3,5},{34191,3,10},{34190,3,27}};
	
	public int[][] expsAndLevels = {{1745,62,57},{2505,66,70},{2507,73,78},{2509,79,86}};
	
	
	public void openLeather(int item) {
		if (item == 1745) {
			c.getPA().sendFrame164(8880); //green dhide
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 1099); // middle
     		c.getPA().sendFrame246(8883, 250, 1065); // left picture
     		c.getPA().sendFrame246(8885, 250, 1135); // right pic
     		c.getPA().sendFrame126("Vambs", 8889);
     		c.getPA().sendFrame126("Chaps", 8893);
     		c.getPA().sendFrame126("Body", 8897);
			hideType = item;
		} else if (item == 2505) {
        	c.getPA().sendFrame164(8880); //blue
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 2493); // middle
     		c.getPA().sendFrame246(8883, 250, 2487); // left picture
     		c.getPA().sendFrame246(8885, 250, 2499); // right pic
     		c.getPA().sendFrame126("Vambs", 8889);
     		c.getPA().sendFrame126("Chaps", 8893);
     		c.getPA().sendFrame126("Body", 8897);
			hideType = item;			
		} else if (item == 2507) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 2495); // middle
     		c.getPA().sendFrame246(8883, 250, 2489); // left picture
     		c.getPA().sendFrame246(8885, 250, 2501); // right pic
     		c.getPA().sendFrame126("Vambs", 8889);
     		c.getPA().sendFrame126("Chaps", 8893);
     		c.getPA().sendFrame126("Body", 8897);
			hideType = item;
		} else if (item == 2509) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 2497); // middle
     		c.getPA().sendFrame246(8883, 250, 2491); // left picture
     		c.getPA().sendFrame246(8885, 250, 2503); // right pic
     		c.getPA().sendFrame126("Vambs", 8889);
     		c.getPA().sendFrame126("Chaps", 8893);
     		c.getPA().sendFrame126("Body", 8897);
			hideType = item;			
		}
		c.craftingLeather = true;
	}

}