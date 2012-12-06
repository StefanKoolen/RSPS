package server.model.minigames;

import server.model.players.Client;
import server.event.Event;
import server.event.EventManager;
import server.event.EventContainer;
import server.Server;

/**
 * @author Brandyn
 */
public class WarriorsGuild {
	
	public WarriorsGuild() {
		//TO DO
	}
	
	private int xCoord, yCoord;
	
	private boolean spawned;
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	private final int[][] ARMORREQUIRED = {{1075, 1117, 1155, 4278, 20, 5, 30, 100}, {1067, 1115, 1153, 4279, 30, 7, 40, 100}, {1069, 1119, 1157, 4280, 40, 10, 60, 100}, {1077, 1125, 1165, 4281, 50, 15, 70, 100},
									{1071, 1121, 1159, 4282, 60, 15, 80, 100}, {1073, 1123, 1161, 4283, 80, 17, 90, 10}, {1079, 1127, 1163, 4284, 100, 20, 100, 100}};
	
	/**
	 * Handles which armor through ItemOnObject
	 * @param itemId The item id used
	 * @param X The object X-Coordinate
	 * @param Y The object Y-Coordinate
	 */
	public void handleArmor(Client c, int itemId, int X, int Y) {
		if(!spawned) {
			this.xCoord = X;
			this.yCoord = Y;
			for(int i = 0; i < ARMORREQUIRED.length; i++) {
				for(int f = 0; f < ARMORREQUIRED[0].length; f++) {
					if(itemId == ARMORREQUIRED[i][f]) {
						if(hasItem(c, ARMORREQUIRED[i][0], true) && hasItem(c, ARMORREQUIRED[i][1], true) && hasItem(c, ARMORREQUIRED[i][2], true)) {
							animateArmor(c, new int[] {ARMORREQUIRED[i][0], ARMORREQUIRED[i][1], ARMORREQUIRED[i][2]}, ARMORREQUIRED[i][3], ARMORREQUIRED[i][4], ARMORREQUIRED[i][5], ARMORREQUIRED[i][6], ARMORREQUIRED[i][7]);
						}
					}
				}
			}
		} else {
			c.sendMessage("You already have armor spawned!");
		}
	}
	
	/**
	 * Checks if the player has an item in his inventory and/or equipment.
	 * @param itemId The item id needed
	 * @param inventory If the item needs to be in inventory, or in equipment
	 * @return returns if it has the item.
	 */
	private boolean hasItem(Client c, int itemId, boolean inventory) {
		return inventory ? c.getItems().playerHasItem(itemId) : c.getItems().playerHasItem(itemId) || c.playerEquipment[5] == itemId;
	}
	
	/**
	 * Animates the armor, deletes items, and walks 3 steps back.
	 *
	 */
	private void animateArmor(Client c, int[] usedArmor, int npcId, int hitPoints, int maxHit, int attack, int defence) {
		c.getPA().walkTo(0, 3);
		for(int i = 0; i < 3; i++) {
			c.getItems().deleteItem(usedArmor[i], 1);
		}
		spawned = true;
		Server.npcHandler.spawnNpc(c, npcId, xCoord, yCoord, 0, 0, hitPoints, maxHit, attack, defence, true, true);
	}
	
	private final String[][] DEFENDER = { {"17273", "Flameburst"},{"20072", "Dragon"}, {"8850", "Rune"}, {"8849", "Adamant"}, {"8848", "Mithril"}, {"8847", "Black"}, {"8846", "Steel"}, {"8845", "Iron"}, {"8844", "Bronze"}};
	
	/**
	 * Gets the cyclops' drop itemid.
	 */
	public int getCyclopsDrop(Client c) {
		if(!c.kamfreenaDone)
			return Integer.parseInt(DEFENDER[6][0]);
		for(int i = 0; i < DEFENDER.length; i++) {
			try {
				if(hasItem(c, Integer.parseInt(DEFENDER[i][0]), false))
					return Integer.parseInt(DEFENDER[i - 1][0]);
			} catch(Exception e) {
				return Integer.parseInt(DEFENDER[0][0]);
			}
		}
		return Integer.parseInt(DEFENDER[6][0]);
	}
	
	/**
	 * @return Returns the defender dropped in text.
	 */
	public String getCyclopsDrop126(Client c) {
		for(int i = 0; i < DEFENDER.length; i++) {
			if(Integer.parseInt(DEFENDER[i][0]) == getCyclopsDrop(c)) {
				return DEFENDER[i][1];
			}
		}
		return DEFENDER[0][1];
	}
	
	/**
	 * Handles Kamfreena's Dialogue
	 */
	public void handleKamfreena(Client c, boolean door) {
		if(door) {

				if(c.UsedTimer == 0 && c.UsedTimer2 == 0) {
					if(c.kamfreenaDone) {
					c.getDH().sendDialogues(47, 4289);
					}
					if(!c.kamfreenaDone) {
					c.getDH().sendDialogues(45, 4289);
					}
			if(!c.getItems().playerHasItem(8851, 100)) {
				c.sendMessage("You need 100 tokens to go in!");
				return;
			}
				c.getPA().movePlayer(2848, 3541, 2);
				executeCyclopsTimer(c);
					c.UsedTimer = 1;
					c.UsedTimer2 = 1;
					c.inCyclops = true;
				return;
				}

				if(c.UsedTimer == 1 && c.UsedTimer2 == 1) {
				c.getPA().movePlayer(2846, 3540, 2);
					c.UsedTimer = 1;
					c.UsedTimer2 = 2;
					c.inCyclops = false;
				return;
				}

				if(c.UsedTimer == 1 && c.UsedTimer2 == 2) {
					if(c.kamfreenaDone) {
					c.getDH().sendDialogues(47, 4289);
					}
					if(!c.kamfreenaDone) {
					c.getDH().sendDialogues(45, 4289);
					}
			if(!c.getItems().playerHasItem(8851, 100)) {
				c.sendMessage("You need 100 tokens to go in!");
				return;
			}
					c.UsedTimer = 1;
					c.UsedTimer2 = 1;
					c.inCyclops = true;
				c.getPA().movePlayer(2848, 3541, 2);
				return;
				}	
		}
	}
	
	/**
	 * 60 Second timer removing 20 tokens.
	 * @return returns the timer event
	 */
	private Event cyclopsTimer(final Client c) {
		return new Event() {
			public void execute(EventContainer e) {
				if(c.getItems().playerHasItem(8851, 20) && c.inCyclops) {
					c.sendMessage("20 Warrior-Guild tokens crumbles to dust...");
					c.getItems().deleteItem2(8851, 20);
					}
					if(!c.getItems().playerHasItem(8851, 20)) {
					if(!c.InDung());
						c.inCyclops = false;
						c.sendMessage("You have ran out of tokens!");
						c.getPA().startTeleport(2846, 3541, 2, "modern");
					c.UsedTimer = 0;
					c.UsedTimer2 = 0;
						e.stop();
 				} else {
				}
			}
		};
	}
	
	/**
	 * Executes the timed token removal. (60 seconds / 1 minute)
	 */
	private void executeCyclopsTimer(Client c) {
		c.inCyclops = true;
		EventManager.getSingleton().addEvent(cyclopsTimer(c), 70000);
	}
}