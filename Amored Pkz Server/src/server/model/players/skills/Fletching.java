package server.model.players.skills;


import server.model.players.Client;
import server.Config;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fletching {

	Client c;
	
	public Fletching(Client c) {
		this.c = c;
	}
	
	int[][] arrows = {{52,314,53,15,1},{53,39,882,40,1},{53,40,884,58,15},{53,41,886,95,30},{53,42,888,132,45},{53,43,890,170,60},{53,44,892,207,75}};
	public void makeArrows(int item1, int item2) {
		for (int j = 0; j < arrows.length; j++) {
			if ((item1 == arrows[j][0] && item2 == arrows[j][1]) || (item2 == arrows[j][0] && item1 == arrows[j][1])) {
				if (c.getItems().playerHasItem(item1, 15) && c.getItems().playerHasItem(item2,15)) {
					if (c.playerLevel[c.playerFletching] >= arrows[j][4]) {
						c.getItems().deleteItem(item1, c.getItems().getItemSlot(item1), 15);
						c.getItems().deleteItem(item2, c.getItems().getItemSlot(item2), 15);
						c.getItems().addItem(arrows[j][2], 15);
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(arrows[j][3] * Config.FLETCHING_EXPERIENCE, c.playerFletching);
					} else {
						c.sendMessage("You need a fletching level of " + arrows[j][4] + " to fletch this.");
					}
				} else {
					c.sendMessage("You must have 15 of each supply to do this.");
				}
			}		
		}	
	}
	
	
	public boolean fletching = false;
	int fletchType = 0, amount = 0, log = 0;
	public void handleFletchingClick(int clickId) {
		for (int j = 0; j < buttons.length; j++) {
			if (buttons[j][0] == clickId) {
				fletchType = buttons[j][1];
				amount = buttons[j][2];
				for (int i = 0; i < logType.length; i++)
					if (log == logType[i]) {
						fletchBow(i);
						break;
					}
				break;
			}
		}	
	}
	
	public void fletchBow(int index) {
		int toAdd = getItemToAdd(index);
		int amountToAdd = getAmountToAdd(toAdd);
		for (int j = 0; j < amount; j++) {
			if (c.getItems().playerHasItem(logType[index],1)) {
				if (c.playerLevel[c.playerFletching] >= reqs[index] || fletchType == 3) {
					c.getItems().deleteItem(logType[index], c.getItems().getItemSlot(logType[index]),1);
					c.getItems().addItem(toAdd, amountToAdd);
						c.getPA().closeAllWindows();
					c.getPA().addSkillXP(getExp(index) * Config.FLETCHING_EXPERIENCE, c.playerFletching);
				} else {
					c.sendMessage("You need a fletching level of " + reqs[index] + " to fletch this item.");
					break;
				}
			} else {
				break;
			}
		}	
	}
	
	public int getExp(int index) {
		if (fletchType == 3)
			return 5;
		else if (fletchType == 1)
			return exps[index];
		else
			return exps[index] + 8;
	
	}
	
	public int getItemToAdd(int index) {
		if (fletchType == 3)
			return shaft;
		else if (fletchType == 1)
			return shortbows[index];
		else if (fletchType == 2)
			return longbows[index];
		return 0;
	}
	
	public int getAmountToAdd(int id) {
		if (id == 52)
			return 15;
		else
			return 1;	
	}
	
	public int shaft = 52;
	public int[] logType = {1511,1521,1519,1517,1515,1513};
	public int[] shortbows = {841,843,849,853,857,861};
	public int[] longbows = {839,845,847,851,855,859};
	public int[] exps = {5,16,33,50,67,83};
	public int[] reqs = {5,20,35,50,65,80};
	
	public void handleLog(int item1, int item2) {
		if (item1 == 946) {
			openFletching(item2);
		} else {
			openFletching(item1);
		}
	}
	
	public int[][] buttons = {{34185,1,1},{34184,1,5},{34183,1,10},{34182,1,27},{34189,2,1},{34188,2,5},{34187,2,10},{34186,2,27},{34193,3,1},{34193,3,5},{34193,3,10},{34193,3,27}};
	
	public void openFletching(int item) {
		if (item == 1511) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 839); // middle
     		c.getPA().sendFrame246(8883, 250, 841); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Shortbow", 8889);
     		c.getPA().sendFrame126("Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;
		} else if (item == 1521) {
        	c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 845); // middle
     		c.getPA().sendFrame246(8883, 250, 843); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Oak Shortbow", 8889);
     		c.getPA().sendFrame126("Oak Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;			
		} else if (item == 1519) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 847); // middle
     		c.getPA().sendFrame246(8883, 250, 849); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Willow Shortbow", 8889);
     		c.getPA().sendFrame126("Willow Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;
		} else if (item == 1517) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 851); // middle
     		c.getPA().sendFrame246(8883, 250, 853); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Maple Shortbow", 8889);
     		c.getPA().sendFrame126("Maple Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;
		} else if (item == 1515) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 855); // middle
     		c.getPA().sendFrame246(8883, 250, 857); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Yew Shortbow", 8889);
     		c.getPA().sendFrame126("Yew Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;
		} else if (item == 1513) {
			c.getPA().sendFrame164(8880);
         	c.getPA().sendFrame126("What would you like to make?", 8879);
         	c.getPA().sendFrame246(8884, 250, 859); // middle
     		c.getPA().sendFrame246(8883, 250, 861); // left picture
     		c.getPA().sendFrame246(8885, 250, 52); // right pic
     		c.getPA().sendFrame126("Magic Shortbow", 8889);
     		c.getPA().sendFrame126("Magic Longbow", 8893);
     		c.getPA().sendFrame126("Arrow Shafts", 8897);
			log = item;
		}
		fletching = true;
	}
}