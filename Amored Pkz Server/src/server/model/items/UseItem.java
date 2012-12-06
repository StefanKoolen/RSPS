package server.model.items;

import server.model.players.Client;
import server.util.Misc;
import server.Config;
import server.model.players.ArmourSets;

/**
 * 
 * @author Ryan / Lmctruck30
 *
 */

public class UseItem {

	public static void ItemonObject(Client c, int objectID, int objectX, int objectY, int itemId) {
		if (!c.getItems().playerHasItem(itemId, 1))
			return;
		switch(objectID) {
			case 2783:
				c.getSmithingInt().showSmithInterface(itemId);
			break;
			case 7965:
			case 8389:
				c.getFarming().checkItemOnObject(itemId);
			break;
			case 2728:
			case 12269:
				c.getCooking().itemOnObject(itemId);
			break;
			case 15621:
				if(c.absX == 2857 && c.absY == 3537 || c.absX == 2851 && c.absY == 3537) {
				c.getWarriorsGuild().handleArmor(c, itemId, objectX, objectY);
				}
			break;
			case 409:
				if (c.getPrayer().isBone(itemId))
					c.getPrayer().bonesOnAltar(itemId);
			break;
		default:
			if(c.playerRights == 3)
				Misc.println("Player At Object id: "+objectID+" with Item id: "+itemId);
			break;
		}
		
	}

	public static void ItemonItem(Client c, int itemUsed, int useWith) {
		c.Summoning.ItemonItem(itemUsed, useWith);			
		if (itemUsed == 227 || useWith == 227)
			c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getItems().getItemName(itemUsed).contains("(") && c.getItems().getItemName(useWith).contains("("))
			c.getPotMixing().mixPotion2(itemUsed, useWith);
		if (itemUsed == 1733 || useWith == 1733)
			c.getCrafting().handleLeather(itemUsed, useWith);
		if (itemUsed == 1755 || useWith == 1755)
			c.getCrafting().handleChisel(itemUsed,useWith);
		if (itemUsed == 946 || useWith == 946)
			c.getFletching().handleLog(itemUsed,useWith);
		if (itemUsed == 53 || useWith == 53 || itemUsed == 52 || useWith == 52)
			c.getFletching().makeArrows(itemUsed, useWith);
		if ((itemUsed == 1540 && useWith == 11286) || (itemUsed == 11286 && useWith == 1540)) {
			if (c.playerLevel[c.playerSmithing] >= 95) {
				c.getItems().deleteItem(1540, c.getItems().getItemSlot(1540), 1);
				c.getItems().deleteItem(11286, c.getItems().getItemSlot(11286), 1);
				c.getItems().addItem(11283,1);
				c.sendMessage("You combine the two materials to create a dragonfire shield.");
				c.getPA().addSkillXP(500 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
			} else {
				c.sendMessage("You need a smithing level of 95 to create a dragonfire shield.");
			}
		}
		if ((itemUsed == 1755 && useWith == 6571) || (itemUsed == 6571 && useWith == 1755)) {
			if (c.playerLevel[c.playerCrafting] >= 65) {
				c.getItems().deleteItem(1755, c.getItems().getItemSlot(1755), 1);
				c.getItems().deleteItem(6571, c.getItems().getItemSlot(6571), 1);
				c.getItems().addItem(6585,1);
				c.sendMessage("You combine the two materials to create a Amulnt Of Fury.");
				c.getPA().addSkillXP(500 * Config.CRAFTING_EXPERIENCE, c.playerCrafting);
			} else {
				c.sendMessage("You need a Crafting level of 65 to create a Amulnt Of Fury.");
			}
		}
		if (itemUsed == 13305 || useWith == 13305){
            if (ArmourSets.isSet(itemUsed)){
                ArmourSets.handleSet(c, itemUsed);
            } else if (ArmourSets.isSet(useWith)){
                ArmourSets.handleSet(c, useWith);
            }
        }
		if (itemUsed == 261 && useWith == 2436) {
			if (c.getItems().playerHasItem(2436, 1) && c.getItems().playerHasItem(261, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(261, c.getItems().getItemSlot(261),1);
					c.getItems().deleteItem(2436, c.getItems().getItemSlot(2436),1);
					c.getItems().addItem(15308,1);
					c.sendMessage("You make an Extreme Attack (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Attack (4) and Clean Avantoe for this potion.");
			}
		}
		if (itemUsed == 267 && useWith == 2440) {
			if (c.getItems().playerHasItem(2440, 1) && c.getItems().playerHasItem(267, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(267, c.getItems().getItemSlot(267),1);
					c.getItems().deleteItem(2440, c.getItems().getItemSlot(2440),1);
					c.getItems().addItem(15312,1);
					c.sendMessage("You make an Extreme Strength (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Strength (4) and Clean Dwarf Weed for this potion.");
			}
		}
		if (itemUsed == 2481 && useWith == 2442) {
			if (c.getItems().playerHasItem(2442, 1) && c.getItems().playerHasItem(2481, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(2481, c.getItems().getItemSlot(2481),1);
					c.getItems().deleteItem(2442, c.getItems().getItemSlot(2442),1);
					c.getItems().addItem(15316,1);
					c.sendMessage("You make an Extreme Defence (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Defence (4) and Clean Lantadyme for this potion.");
			}
		}
		if (itemUsed == 3000 && useWith == 3040) {
			if (c.getItems().playerHasItem(3040, 1) && c.getItems().playerHasItem(3000, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
					c.getItems().deleteItem(3040, c.getItems().getItemSlot(3040),1);
					c.getItems().addItem(15320,1);
					c.sendMessage("You make an Extreme Magic (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Magic Potion (4) and Clean Snapdragon for this potion.");
			}
		}
		if (itemUsed == 259 && useWith == 2444) {
			if (c.getItems().playerHasItem(2444, 1) && c.getItems().playerHasItem(259, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(259, c.getItems().getItemSlot(259),1);
					c.getItems().deleteItem(2444, c.getItems().getItemSlot(2444),1);
					c.getItems().addItem(15324,1);
					c.sendMessage("You make an Extreme Ranging (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Ranging Potion (4) and Clean Irit for this potion.");
			}
		}
		if (itemUsed == 269 && useWith == 15308 || itemUsed == 269 && useWith == 15312 || itemUsed == 269 && useWith == 15316 || itemUsed == 269 && useWith == 15320 || itemUsed == 269 && useWith == 15324) {
       		if (c.getItems().playerHasItem(15308, 1) && c.getItems().playerHasItem(15312, 1) && c.getItems().playerHasItem(15316, 1) && c.getItems().playerHasItem(15320, 1) && c.getItems().playerHasItem(15324, 1)){
            	if (c.playerLevel[c.playerHerblore] >= 96) {
            	c.getItems().deleteItem(269, c.getItems().getItemSlot(269),1);
            	c.getItems().deleteItem(15308, c.getItems().getItemSlot(15308),1);
            	c.getItems().deleteItem(15312, c.getItems().getItemSlot(15312),1);
            	c.getItems().deleteItem(15316, c.getItems().getItemSlot(15316),1);
            	c.getItems().deleteItem(15320, c.getItems().getItemSlot(15320),1);
            	c.getItems().deleteItem(15324, c.getItems().getItemSlot(15324),1);
            	c.getItems().addItem(15332,1);
                c.sendMessage("You make a Overload Potion (4).");
                c.getPA().addSkillXP(500 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
            	} else {
                c.sendMessage("You need a herblore level of 96 to make that potion.");
            	}
            	} else {
                	c.sendMessage("You need all extreme potions to make a Overload.");
            	}
        }
		if (itemUsed == 12435) { 
			if (c.lastsummon == 6874) {
			if(c.gwdelay > 1);
				c.getItems().bankItem(useWith, c.getItems().getItemSlot(useWith), 1);
				c.getItems().deleteItem(itemUsed, 1);
				c.sendMessage("Your Pack yak sends an item to your bank."); 
				c.gfx0(1316);
				c.startAnimation(7660);
				c.sendMessage("You can only do this every 5 Minutes!");
				c.gwdelay = 600;
			} else {
				c.sendMessage("You must wait another 5 Minutes to use this scroll.");
			}
		}
		if (itemUsed == 9142 && useWith == 9190 || itemUsed == 9190 && useWith == 9142) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9241, boltsMade);
				c.getPA().addSkillXP(boltsMade * 6 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}
		}
		if (itemUsed == 9143 && useWith == 9191 || itemUsed == 9191 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9242, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}		
		}
		if (itemUsed == 9143 && useWith == 9192 || itemUsed == 9192 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9243, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}		
		}
		if (itemUsed == 4151 && useWith == 20060 || itemUsed == 20060 && useWith == 4151) {
				if (c.playerLevel[c.playerSmithing] >= 70) {
					c.getItems().deleteItem(20060, c.getItems().getItemSlot(20060),1);
					c.getItems().deleteItem(4151, c.getItems().getItemSlot(4151),1);
					c.getItems().addItem(20061,1);
					c.sendMessage("You make an Abyssal Vine Whip!.");
					c.getPA().addSkillXP(1000 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
				} else {
					c.sendMessage("You need a Smithing level of 70 to make an Abyssal Vine Whip!.");
			}
		}
		if (itemUsed == 9144 && useWith == 9193 || itemUsed == 9193 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9244, boltsMade);
				c.getPA().addSkillXP(boltsMade * 10 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9194 || itemUsed == 9194 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9245, boltsMade);
				c.getPA().addSkillXP(boltsMade * 13 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}		
		}
		if (itemUsed == 1601 && useWith == 1755 || itemUsed == 1755 && useWith == 1601) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				c.getItems().deleteItem(1601, c.getItems().getItemSlot(1601), 1);
				c.getItems().addItem(9192, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}
		}
		if (itemUsed == 1607 && useWith == 1755 || itemUsed == 1755 && useWith == 1607) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				c.getItems().deleteItem(1607, c.getItems().getItemSlot(1607), 1);
				c.getItems().addItem(9189, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}
		}
		if (itemUsed == 1605 && useWith == 1755 || itemUsed == 1755 && useWith == 1605) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				c.getItems().deleteItem(1605, c.getItems().getItemSlot(1605), 1);
				c.getItems().addItem(9190, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}
		}
		if (itemUsed == 1603 && useWith == 1755 || itemUsed == 1755 && useWith == 1603) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1603, c.getItems().getItemSlot(1603), 1);
				c.getItems().addItem(9191, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed == 1615 && useWith == 1755 || itemUsed == 1755 && useWith == 1615) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1615, c.getItems().getItemSlot(1615), 1);
				c.getItems().addItem(9193, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed >= 11710 && itemUsed <= 11714 && useWith >= 11710 && useWith <= 11714) {
			if (c.getItems().hasAllShards()) {
				c.getItems().makeBlade();
			}		
		}
		if (itemUsed == 985 && useWith == 987 || itemUsed == 987 && useWith == 985) {
c.getItems().deleteItem(985, c.getItems().getItemSlot(985),1);
c.getItems().deleteItem(987, c.getItems().getItemSlot(987),1);
c.getItems().addItem(989,1);
}
		if (itemUsed == 2368 && useWith == 2366 || itemUsed == 2366 && useWith == 2368) {
			c.getItems().deleteItem(2368, c.getItems().getItemSlot(2368),1);
			c.getItems().deleteItem(2366, c.getItems().getItemSlot(2366),1);
			c.getItems().addItem(1187,1);
		}
		if (c.getItems().isHilt(itemUsed) || c.getItems().isHilt(useWith)) {
			int hilt = c.getItems().isHilt(itemUsed) ? itemUsed : useWith;
			int blade = c.getItems().isHilt(itemUsed) ? useWith : itemUsed;
			if (blade == 11690) {
				c.getItems().makeGodsword(hilt);
			}
		}
		if (itemUsed == 4151 && useWith == 10531 || itemUsed == 10531 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15444,1);	
		}

		if (itemUsed == 4151 && useWith == 10537 || itemUsed == 10537 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15443,1);	
		}

		if (itemUsed == 4151 && useWith == 10533 || itemUsed == 10533 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15442,1);	
		}

		if (itemUsed == 4151 && useWith == 10534 || itemUsed == 10534 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15441,1);	
		}

		if (itemUsed == 11235 && useWith == 10531 || itemUsed == 10531 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15704,1);	
		}

		if (itemUsed == 11235 && useWith == 10537 || itemUsed == 10537 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15703,1);	
		}

		if (itemUsed == 3188 && useWith == 15441 || itemUsed == 15441 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15442 || itemUsed == 15442 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15443 || itemUsed == 15443 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15444 || itemUsed == 15444 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15701 || itemUsed == 15701 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15702 || itemUsed == 15702 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15703 || itemUsed == 15703 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15704 || itemUsed == 15704 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 11235 && useWith == 10533 || itemUsed == 10533 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15702,1);	
		}

		if (itemUsed == 11235 && useWith == 10534 || itemUsed == 10534 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15701,1);	
		}

		if (itemUsed == 13736 && useWith == 13746 || itemUsed == 13746 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13738,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13748 || itemUsed == 13748 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13740,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13750 || itemUsed == 13750 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13742,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13752 || itemUsed == 13752 && useWith == 13736) {
				if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13744,1);
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}				
		
		if (itemUsed == 13734 && useWith == 13754 || itemUsed == 13754 && useWith == 13734) {
		if (c.playerLevel[c.playerPrayer] >= 75) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13736,1);	
					} else {
				c.sendMessage("You need a Prayer level of 75 to Make a blessed spiritshield.");
			}
		}
		switch(itemUsed) {
			case 1511:
			case 1521:
			case 1519:
			case 1517:
			case 1515:
			case 1513:
			case 590:
				c.getFiremaking().checkLogType(itemUsed, useWith);
				//c.sendMessage("Firemaking is disabled.");
			break;
			
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemUsed+" with Item id: "+useWith);
			break;
		}	
	}
	public static void ItemonNpc(Client c, int itemId, int npcId, int slot) {
		switch(itemId) {
		
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemId+" with Npc id: "+npcId+" With Slot : "+slot);
			break;
		}
		
	}


}
