package server.model.players.packets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.npcs.*;
import server.model.players.Client;
import server.model.players.SkillMenu;
import server.model.players.PacketType;
import server.model.players.SkillGuides;
import server.model.players.Player;
import server.util.Misc;
import server.event.EventContainer;
import server.model.minigames.GnomeGlider;
import server.event.Event;
import server.event.EventManager;
import server.model.players.packets.ClickItem;
import server.model.players.QuickCurses;
import server.model.players.QuickPrayers;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
				GnomeGlider.flightButtons(c, actionButtonId);
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		for (int i = 0; i < c.qCAB.length; i++) {
		if (actionButtonId == c.qCAB[i][0] ){
				for (int j = 0; j < c.qCS.length; j++) {
				if ( j == i ) {
				c.forcedText = c.qC+ "My " +c.qCS[j]+ " Level is " +c.getLevelForXP(c.playerXP[c.qCAB[i][1]])+ ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				}
				}
		}
	}
int[] spellIds = {4128,4130,4132,4134,4136,4139,4142,4145,4148,4151,4153,4157,4159,4161,4164,4165,4129,4133,4137,6006,6007,6026,6036,6046,6056,
			4147,6003,47005,4166,4167,4168,48157,50193,50187,50101,50061,50163,50211,50119,50081,50151,50199,50111,50071,50175,50223,50129,50091};
			for(int i=0; i < spellIds.length; i++) {
				if(actionButtonId == spellIds[i]) {
					if (c.autocasting) {
						c.autocasting = false;
						c.getPA().resetAutocast();
					} else {
							c.autocasting = true;
							c.autocastId = i;	
					}	
				}
				
			}
			if (actionButtonId >= 67050 && actionButtonId <= 67075) {
            if (c.altarPrayed == 0)
                QuickPrayers.clickPray(c, actionButtonId);
            else
                QuickCurses.clickCurse(c, actionButtonId);
        }
		switch (actionButtonId){
			//crafting + fletching interface:
			case 89223: //Deposit Inventory
				for(int i = 0; i < c.playerItems.length; i++){
					c.getItems().bankItem(c.playerItems[i], i,c.playerItemsN[i]);
				}
	
				break;
			/*case 66117:
                 switch(c.lastSummon) {
				case 6870: //wolpertinger
	if(c.getItems().playerHasItem(12437, 1)) {
		c.getItems().deleteItem(12437, 1);
		c.gfx0(1311);
	if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
	c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
else
	c.playerLevel[6] += (c.getLevelForXP(c.playerXP[6]) * .1);
	c.getPA().refreshSkill(6);
	c.sendMessage("Your Magic bonus has increased!");
	} else
	c.sendMessage("You don't have a scroll for that NPC!");
	break;*/
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
			case 2646:
			c.getItems().addItem(1779, 1);
			c.getPA().addSkillXP(1000, 12);
			break;
			
			case 70146:
			if (c.playerLevel[24] > 98) {
				c.getItems().addItem(18509, 1);
			} else {
				c.sendMessage("You must be 99 Dungeoneering to Recieve This.");
			}
			break;
				case 19136: //Toggle quick prayers
                if (c.quickPray || c.quickCurse) {
                    QuickCurses.turnOffQuicks(c);
                    return;
                }
                QuickCurses.turnOnQuicks(c);
            break;
             
            case 19137: //Select quick prayers
                QuickCurses.selectQuickInterface(c);
            break;
         
            case 67089: //quick curse confirm
                QuickCurses.clickConfirm(c);
            break;
			
			 case 70080:
				QuickCurses.turnOnQuicks(c);
			break;
 
			case 70081:
				QuickCurses.turnOffQuicks(c);
			break;
             
            case 70082: //select your quick prayers/curses
                QuickCurses.selectQuickInterface(c);
                c.getPA().sendFrame106(5);
            break;
			
                        case 66122:
switch(c.npcType) {
//CONSTRUCTION INTERFACES
			//PUBLIC - PRIVATE
			case 122099://public
				c.getPA().startTeleport2(2060, 3261, 0);
				c.sendMessage("You teleported to the public Construction zone.");
			break;

			case 122102://private
				c.getPA().startTeleport2(2060, 3261, c.playerId * 4);
				c.sendMessage("You teleported to the private Construction zone.");
			break;

			//CHOOSE WHAT TO BUILD
			case 122019://fern
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 1) {
				c.sendMessage("You need a level 1 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(249, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Fern.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(31 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13432, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
			break;

			case 122022://tree
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Tree.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(31 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13411, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
			break;

			case 122025://chair
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 19) {
				c.sendMessage("You need a level 19 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 10);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Chair.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13584, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122028://bookcase
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 29) {
				c.sendMessage("You need a level 29 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bookcase.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13598, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122031://greenman's ale
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 26) {
				c.sendMessage("You need a level 26 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Greenman's ale.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(184 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13571, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122034://small oven
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 24) {
				c.sendMessage("You need a level 24 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build a Small oven.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(80 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13533, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122037://carved oak bench
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 31) {
				c.sendMessage("You need a level 31 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Carved oak bench.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(240 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13302, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122040://painting stand
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 41) {
				c.sendMessage("You need a level 41 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 20);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Painting stand.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(240 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13717, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122043://bed
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 40) {
				c.sendMessage("You need a level 40 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 20);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bed.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(300 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13151, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122046://teak drawers
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 51) {
				c.sendMessage("You need a level 51 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1)) {
				c.getItems().deleteItem2(1539, 20);
				c.getItems().deleteItem2(8780, 1);
				c.getItems().deleteItem2(8780, 1);
				c.sendMessage("You build a Teak drawers.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13158, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122049://mithril armour
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 28) {
				c.sendMessage("You need a level 28 Construction to do that.");
				return;
			}
			if(c.playerLevel[13] < 68) {
				c.sendMessage("You need a level 68 Smithing to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1159, 1) && c.getItems().playerHasItem(1121, 1) && c.getItems().playerHasItem(1071, 1)) {
				c.getItems().deleteItem2(1159, 1);
				c.getItems().deleteItem2(1121, 1);
				c.getItems().deleteItem2(1071, 1);
				c.sendMessage("You build a Mithril armour.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(135 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
				c.getPA().checkObjectSpawn(13491, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122052://adamant armour
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 28) {
				c.sendMessage("You need a level 28 Construction to do that.");
				return;
			}
			if(c.playerLevel[13] < 88) {
				c.sendMessage("You need a level 88 Smithing to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1161, 1) && c.getItems().playerHasItem(1123, 1) && c.getItems().playerHasItem(1073, 1)) {
				c.getItems().deleteItem2(1161, 1);
				c.getItems().deleteItem2(1123, 1);
				c.getItems().deleteItem2(1073, 1);
				c.sendMessage("You build a Adamant armour.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(150 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
				c.getPA().checkObjectSpawn(13492, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122055://rune armour
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 28) {
				c.sendMessage("You need a level 28 Construction to do that.");
				return;
			}
			if(c.playerLevel[13] < 99) {
				c.sendMessage("You need a level 99 Smithing to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1163, 1) && c.getItems().playerHasItem(1127, 1) && c.getItems().playerHasItem(1079, 1)) {
				c.getItems().deleteItem2(1163, 1);
				c.getItems().deleteItem2(1127, 1);
				c.getItems().deleteItem2(1079, 1);
				c.sendMessage("You build a Rune armour.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(165 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
				c.getPA().checkObjectSpawn(13493, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;


			case 122058://rune display case
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 28) {
				c.sendMessage("You need a level 28 Construction to do that.");
				return;
			}
			if(c.playerLevel[20] < 44) {
				c.sendMessage("You need a level 44 Runecrafting to do that.");
				return;
			}
			if (c.getItems().playerHasItem(563, 100) && c.getItems().playerHasItem(561, 100) && c.getItems().playerHasItem(8780, 1)) {
				c.getItems().deleteItem2(563, 100);
				c.getItems().deleteItem2(561, 1);
				c.getItems().deleteItem2(8780, 1);
				c.sendMessage("You build a Rune display case.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(212 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().addSkillXP(44 * Config.RUNECRAFTING_EXPERIENCE, 20);
				c.getPA().checkObjectSpawn(13508, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122061://archery target
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 81) {
				c.sendMessage("You need a level 81 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 25) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1)) {
				c.getItems().deleteItem2(1539, 25);
				c.getItems().deleteItem2(8780, 1);
				c.getItems().deleteItem2(8780, 1);
				c.getItems().deleteItem2(8780, 1);
				c.sendMessage("You build an Archery target.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(600 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13402, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122064://combat stone
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 59) {
				c.sendMessage("You need a level 59 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build a Combat stone.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(200 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
				Server.npcHandler.spawnNpc(c, 4162, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122067://elemental balance
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 77) {
				c.sendMessage("You need a level 77 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build an Elemental balance.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(356 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
				Server.npcHandler.spawnNpc(c, 4095, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122070://mahogany prize chest
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 54) {
				c.sendMessage("You need a level 54 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1)) {
				c.getItems().deleteItem2(1539, 20);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.sendMessage("You build a Mahogany prize chest.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(860 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13389, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122073://lectern
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 67) {
				c.sendMessage("You need a level 67 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1)) {
				c.getItems().deleteItem2(1539, 40);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.sendMessage("You build a Lectern.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(580 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13648, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122076://crystal of power
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 66) {
				c.sendMessage("You need a level 66 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build a Crystal of power.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(890 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13661, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122079://altar
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 64) {
				c.sendMessage("You need a level 64 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build an Altar.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(910 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13191, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122082://intense burners
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 61) {
				c.sendMessage("You need a level 61 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(263, 1)) {
				c.getItems().deleteItem2(1539, 10);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(263, 1);
				c.sendMessage("You build an Intense burners.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(280 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13210, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122085://hedge
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 80) {
				c.sendMessage("You need a level 80 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(263, 1) && c.getItems().playerHasItem(263, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(263, 1);
				c.getItems().deleteItem2(263, 1);
				c.sendMessage("You build a Hedge.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(316 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13476, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122088://rocnar
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 83) {
				c.sendMessage("You need a level 83 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(2361, 1) && c.getItems().playerHasItem(2361, 1) && c.getItems().playerHasItem(263, 1) && c.getItems().playerHasItem(263, 1)) {
				c.getItems().deleteItem2(2361, 1);
				c.getItems().deleteItem2(2361, 1);
				c.getItems().deleteItem2(263, 1);
				c.getItems().deleteItem2(263, 1);
				c.sendMessage("You build a Rocnar.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(387 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(13373, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

			case 122091://bank chest
			if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[25] < 92) {
				c.sendMessage("You need a level 92 Construction to do that.");
				return;
			}
			if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
				c.getItems().deleteItem2(1539, 40);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(8782, 1);
				c.getItems().deleteItem2(2351, 1);
				c.sendMessage("You build a Bank chest.");
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(800 * Config.CONSTRUCTION_EXPERIENCE, 24);
				c.getPA().checkObjectSpawn(3193, c.absX, c.absY, c.heightLevel, 10);
				} else {
				c.sendMessage("You don't have the required materials.");
				}
				
			break;

case 6807:
case 6874:
case 6868:
case 6795:
case 6816:
case 6873:

c.sendMessage("You are now storing items inside your npc");
	c.Summoning().store();
}
			break;
			case 66127:
			if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.storing = false;
c.sendMessage("Your BoB items have drop on the floor");
} else {
c.sendMessage("You do not have a Familiar currently spawned");
}
break; 

			case 189121:
			c.getPA().sendFrame126("" + Config.BUY_SPINS + "", 12000);
			break;
			case 189118:
			if (c.Wheel == 0) {
		c.getDH().sendDialogues(57, 945);
		} else if (c.Wheel > 0) {
		c.getItems().addItem(c.getPA().Wheel(), 1);
		c.Wheel = (c.Wheel - 1);
		c.getDH().sendDialogues(58, 945);
		}
		break;	
			
			
			case 113236:
			c.getPA().sendFrame126(""+ Config.FORUMS +"", 12000);
			break;
                        case 21010:
                        c.takeAsNote = true;
                        break;
                        case 21011:
                        c.takeAsNote = false;
                        break;
			case 68244:
c.getPA().startTeleport(2676, 3711, 0, "modern");
break;
        case 54221:
		c.getPA().startTeleport(2897, 3618, 0, "modern");
		c.sendMessage("Welcome to The God Bandos's chamber");
		break;
		
		case 54231:
		c.getPA().startTeleport(2897, 3618, 4, "modern");
		c.sendMessage("Welcome to The God Saradomin's chamber");
		break;
		
		case 54228:
		c.getPA().startTeleport(2897, 3618, 8, "modern");
		c.sendMessage("Welcome to The God Armadyl's chamber");
		break;
case 68247:
c.getPA().startTeleport(2884, 9798, 0, "modern");
break;
case 68250:
c.getPA().startTeleport(3428, 3537, 0, "modern");
break;
case 68253:
c.getPA().startTeleport(2710, 9466, 0, "modern");
break;
case 69000:
c.getPA().startTeleport(2905, 9730, 0, "modern");
break;
case 69003:
c.getPA().startTeleport(2908, 9694, 0, "modern");
break;
case 69006:
if((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
		c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
		} else {
		if((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
		c.sendMessage("You need 90 Agility to enter the Strykworm's Cave");
		} else {
		if((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
		c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave");
		} else {
		if((c.playerLevel[21] > 89) && (c.playerLevel[16] >89)) {
		c.getPA().startTeleport(2515, 4632, 0, "modern");
		c.sendMessage("A sense of nervousness fills your body..");
		c.sendMessage("you find yourself in a mystery cave!");
		}
		}
		}
		}
		

		break;

			case 113237:
			c.getPA().showInterface(8134);
				c.getPA().sendFrame126("@blu@"+ Config.SERVER_NAME +" Rules:",8144); 
				c.getPA().sendFrame126("@cya@Luring is Allowed",8145); 
				c.getPA().sendFrame126("@cya@Duping = Ip Ban",8147);
				c.getPA().sendFrame126("@cya@No begging for staff -Mute",8148);
				c.getPA().sendFrame126("@cya@Report All Glitches for Rewards",8149);
				c.getPA().sendFrame126("@gre@-"+ Config.OWNER +"",8150);
			break;
			
			case 113238:
			c.getPA().showInterface(8134);
				c.getPA().sendFrame126("@red@Pk Shop Help",8144);
				c.getPA().sendFrame126("@cya@This Shop is really easy to understand",8145);
				c.getPA().sendFrame126("@cya@When you start playing, you have 0 kills",8146);
				c.getPA().sendFrame126("@cya@When you kill someone you wiull get 1 kill",8147);
				c.getPA().sendFrame126("@cya@With 0 Kills you can't see any items on shop",8148);
				c.getPA().sendFrame126("@cya@You Start to see after 5 kills",8149);
				c.getPA().sendFrame126("@cya@ 5 kills = 2 Items",8150);  
				c.getPA().sendFrame126("@cya@ 10 kills = + 2 Items",8151);
				c.getPA().sendFrame126("@cya@ 15 kills = + 2 Items",8152);
				c.getPA().sendFrame126("@cya@ 20 kills = + 2 Items",8153);
				c.getPA().sendFrame126("@cya@ And keep like that till 40,when you get 40",8154);
				c.getPA().sendFrame126("@cya@ It is 10 kills for another shop,",8155);
				c.getPA().sendFrame126("@cya@ Till you get 100 kills (Master pker)",8156);
				c.getPA().sendFrame126("@cya@ With Master Pker you can see thw whole shop",8157);
				c.getPA().sendFrame126("@cya@ ",8158);
				c.getPA().sendFrame126("@cya@ I hope you enjoy this new system!",8159);	
				c.getPA().sendFrame126("@gre@"+ Config.OWNER +"",8160);	
			break;
			
			case 113243:
			
			if(!c.isSkulled) {	
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.getItems().keepItem(1, false);	
				c.getItems().keepItem(2, false);
				c.getItems().keepItem(3, false);
				c.sendMessage("You can keep three items and a fourth if you use the protect item prayer.");
			} else {
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.sendMessage("You are skulled and will only keep one item if you use the protect item prayer.");
			}
			c.getItems().sendItemsKept();
c.getPA().showInterface(6960);
			c.getItems().resetKeepItems();
			break;



		//ArmoredPkz New Ones 100% by dr house
		
		case 113239:
			c.getPA().showInterface(8134);
				c.getPA().sendFrame126("@red@               Points Tab",8144);
				c.getPA().sendFrame126("",8146);
				c.getPA().sendFrame126("@cya@Pk Points: @gre@" + c.pkPoints + ".",8147);
				c.getPA().sendFrame126("@cya@Pest Control Points: @gre@" + c.pcPoints + ".",8148);
				c.getPA().sendFrame126("@cya@Dungeon Points: @gre@" + c.dungPoints + ".",8149);
				c.getPA().sendFrame126("@cya@Boss Points: @gre@" + c.bossPoints + ".",8150);
				c.getPA().sendFrame126("@cya@Level Points: @gre@" + c.lvlPoints + ".",8151);
				c.getPA().sendFrame126("@cya@Dungeon Points: @gre@" + c.dungPoints + ".",8152);
				c.getPA().sendFrame126("",8153);
				c.getPA().sendFrame126(" ",8154);
				c.getPA().sendFrame126(" ",8155);
				c.getPA().sendFrame126(" ",8156);
				c.getPA().sendFrame126(" ",8157);
				c.getPA().sendFrame126(" ",8158);
				c.getPA().sendFrame126(" ",8159);
			c.getPA().sendFrame126(" ",8160);
			c.getPA().sendFrame126(" ",8161);
			c.getPA().sendFrame126(" ",8162);
			c.getPA().sendFrame126(" ",8163);
			c.getPA().sendFrame126(" ",8164);
			c.getPA().sendFrame126(" ",8165);
			c.getPA().sendFrame126(" ",8166);
			c.getPA().sendFrame126(" ",8167);
			c.getPA().sendFrame126(" ",8168);
			c.getPA().sendFrame126(" ",8169);
			c.getPA().sendFrame126(" ",8170);
			c.getPA().sendFrame126(" ",8171);
			c.getPA().sendFrame126(" ",8172);
			c.getPA().sendFrame126(" ",8173);
			c.getPA().sendFrame126(" ",8174);
			c.getPA().sendFrame126(" ",8175);
			c.getPA().sendFrame126(" ",8176);
			c.getPA().sendFrame126(" ",8177);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8180);
		break;
		case 113240:
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@red@                  Achievements",8144);
			c.getPA().sendFrame126("@gre@[EASY]",8145);
			c.getPA().sendFrame126(" ",8146);
			
			if (c.KC == 0)
			c.getPA().sendFrame126("@red@Kill 20 Players [Not Started]",8147);
			if (c.KC <=24 && c.KC >= 1)
			c.getPA().sendFrame126("@yel@Kill 20 Players.@blu@[Ammount]: [" + c.KC + "].",8147);
			if (c.KC >=25)
			c.getPA().sendFrame126("@gre@Kill 20 Players.[Completed]",8147);
			
			if (c.lvlPoints == 0)
			c.getPA().sendFrame126("@red@Have 500 Level Points [Not Started]",8148);
			if (c.lvlPoints <= 499 && c.lvlPoints >= 1)
			c.getPA().sendFrame126("@yel@Have 500 Level Points.@blu@[Ammount]: [" + c.lvlPoints + "].",8148);
			if (c.lvlPoints >= 500)
			c.getPA().sendFrame126("@gre@Have 500 Level Points.[Completed]",8148);
			
			if (c.bossKC == 0)
			c.getPA().sendFrame126("@red@Slayed 25 Bosses [Not Started] ",8149);
			if (c.bossKC <= 24 && c.bossKC >= 1)
			c.getPA().sendFrame126("@yel@Slayed 25 Bosses.@blu@[Ammount]: [" + c.bossKC + "].",8149);
			if (c.bossKC >= 25)
			c.getPA().sendFrame126("@gre@Slayed 25 Bosses.[Completed] ",8149);
			
			c.getPA().sendFrame126("@gre@[MEDIUM] ",8150);
			
			if (c.lvlPoints == 0)
			c.getPA().sendFrame126("@red@Have 1500 Level Points [Not Started]",8151);
			if (c.lvlPoints <= 1499 && c.lvlPoints >= 1)
			c.getPA().sendFrame126("@yel@Have 1500 Level Points.@blu@[Ammount]: [" + c.lvlPoints + "].",8151);
			if (c.lvlPoints >= 1500)
			c.getPA().sendFrame126("@gre@Have 1500 Level Points.[Completed]",8151);
				
			if (c.playerLevel[24] == 1)
			c.getPA().sendFrame126("@red@Have 99 Dungeoneering [Not Started] ",8152);
			if (c.playerLevel[24] <= 98 && c.playerLevel[24] >= 2)
			c.getPA().sendFrame126("@yel@Have 99 Dungeoneering.[Level]: " + c.playerLevel[24] + ".",8152);
			if (c.playerLevel[24] >= 99)
			c.getPA().sendFrame126("@gre@Have 99 Dungeoneering.[Completed]",8152);
				
			if (c.bossKC == 0)
			c.getPA().sendFrame126("@red@Slayed 100 Bosses [Not Started] ",8153);
			if (c.bossKC <= 99 && c.bossKC >= 1)
			c.getPA().sendFrame126("@yel@Slayed 100 Bosses.@blu@[Ammount]: [" + c.bossKC + "].",8153);
			if (c.bossKC >= 100)
			c.getPA().sendFrame126("@gre@Slayed 100 Bosses.[Completed] ",8153);
				
			
			if (c.KC == 0)
			c.getPA().sendFrame126("@red@Kill 100 Players [Not Started]",8154);
			if (c.KC <=99 && c.KC >= 1)
			c.getPA().sendFrame126("@yel@Kill 100 Players.@blu@[Ammount]: [" + c.KC + "].",8154);
			if (c.KC >=100)
			c.getPA().sendFrame126("@gre@Kill 100 Players.[Completed]",8154);

			c.getPA().sendFrame126("@gre@[HARD]",8155);
			
			if (c.bossKC == 0)
			c.getPA().sendFrame126("@red@Slayed 250 Bosses [Not Started] ",8156);
			if (c.bossKC <= 249 && c.bossKC >= 1)
			c.getPA().sendFrame126("@yel@Slayed 250 Bosses.@blu@[Ammount]: [" + c.bossKC + "].",8156);
			if (c.bossKC >= 250)
			c.getPA().sendFrame126("@gre@Slayed 250 Bosses.[Completed] ",8156);
			
			if (c.KC == 0)
			c.getPA().sendFrame126("@red@Kill 250 Players [Not Started]",8157);
			if (c.KC <=249 && c.KC >= 1)
			c.getPA().sendFrame126("@yel@Kill 250 Players.@blu@[Ammount]: [" + c.KC + "].",8157);
			if (c.KC >=250)
			c.getPA().sendFrame126("@gre@Kill 250 Players.[Completed]",8157);
			
			if (c.lvlPoints == 0)
			c.getPA().sendFrame126("@red@Have 2000 Level Points [Not Started]",8158);
			if (c.lvlPoints <= 1999 && c.lvlPoints >= 1)
			c.getPA().sendFrame126("@yel@Have 2000 Level Points.@blu@[Ammount]: [" + c.lvlPoints + "].",8158);
			if (c.lvlPoints >= 2000)
			c.getPA().sendFrame126("@gre@Have 2000 Level Points.[Completed]",8158);
			
			c.getPA().sendFrame126(" ",8158);
			c.getPA().sendFrame126(" ",8159);
			c.getPA().sendFrame126(" ",8160);
			c.getPA().sendFrame126(" ",8161);
			c.getPA().sendFrame126(" ",8162);
			c.getPA().sendFrame126(" ",8163);
			c.getPA().sendFrame126(" ",8164);
			c.getPA().sendFrame126(" ",8165);
			c.getPA().sendFrame126(" ",8166);
			c.getPA().sendFrame126(" ",8167);
			c.getPA().sendFrame126(" ",8168);
			c.getPA().sendFrame126(" ",8169);
			c.getPA().sendFrame126(" ",8170);
			c.getPA().sendFrame126(" ",8171);
			c.getPA().sendFrame126(" ",8172);
			c.getPA().sendFrame126(" ",8173);
			c.getPA().sendFrame126(" ",8174);
			c.getPA().sendFrame126(" ",8175);
			c.getPA().sendFrame126(" ",8176);
			c.getPA().sendFrame126(" ",8177);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8180);
			
				
				
		break;
		
		case 113241:
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@red@          Player Information",8144);
			c.getPA().sendFrame126("",8145);
				c.getPA().sendFrame126("@cya@Player Name: @gre@" + c.playerName + ".",8146);
				c.getPA().sendFrame126("@cya@Combat Level @gre@" + c.combatLevel + ".",8147);
				if (c.playerRights == 0)
				c.getPA().sendFrame126("@cya@Rank:@gre@ Player",8148);
				if (c.playerRights == 1)
				c.getPA().sendFrame126("@cya@Rank:@gre@ Moderator",8148);
				if (c.playerRights == 2)
				c.getPA().sendFrame126("@cya@Rank:@gre@ Administrator",8148);
				if (c.playerRights == 3)
				c.getPA().sendFrame126("@cya@Rank:@gre@ Owner",8148);
				if (c.isDonator == 0)
				c.getPA().sendFrame126("@cya@Donator Rank: @gre@ Not Donator",8149);
				if (c.isDonator == 1)
				c.getPA().sendFrame126("@cya@Donator Rank: @gre@Regular Donator",8149);
				if (c.isDonator == 2)
				c.getPA().sendFrame126("@cya@Donator Rank: @gre@Super Donator",8149);
				c.getPA().sendFrame126("@cya@Boss Kills: @gre@" + c.dungPoints + ".",8150);
			c.getPA().sendFrame126("@cya@Player Kills: @gre@" + c.KC + ".",8151);
			c.getPA().sendFrame126("@cya@Player Deaths: @gre@" + c.DC + ".",8152);
			c.getPA().sendFrame126(" ",8153);
			c.getPA().sendFrame126(" ",8154);
			c.getPA().sendFrame126(" ",8155);
			c.getPA().sendFrame126(" ",8156);
			c.getPA().sendFrame126(" ",8157);
			c.getPA().sendFrame126(" ",8158);
			c.getPA().sendFrame126(" ",8159);
			c.getPA().sendFrame126(" ",8160);
			c.getPA().sendFrame126(" ",8161);
			c.getPA().sendFrame126(" ",8162);
			c.getPA().sendFrame126(" ",8163);
			c.getPA().sendFrame126(" ",8164);
			c.getPA().sendFrame126(" ",8165);
			c.getPA().sendFrame126(" ",8166);
			c.getPA().sendFrame126(" ",8167);
			c.getPA().sendFrame126(" ",8168);
			c.getPA().sendFrame126(" ",8169);
			c.getPA().sendFrame126(" ",8170);
			c.getPA().sendFrame126(" ",8171);
			c.getPA().sendFrame126(" ",8172);
			c.getPA().sendFrame126(" ",8173);
			c.getPA().sendFrame126(" ",8174);
			c.getPA().sendFrame126(" ",8175);
			c.getPA().sendFrame126(" ",8176);
			c.getPA().sendFrame126(" ",8177);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8178);
			c.getPA().sendFrame126(" ",8180);
		break;
		
		case 113242:
			c.sendMessage("Comming Soon...");
		break;
		
		
		//Monster teleport Interface photoshoped and edited by dr house :D
		
		case 132210:		//rock crabs
			c.getPA().spellTeleport(2676, 3711, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132211:		//taverley dungeon
			c.getPA().spellTeleport(2884, 9798, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132212:		//brihavem Dungeon
			c.getPA().spellTeleport(2710, 9466, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132213:		//slayer tower
			c.getPA().spellTeleport(3428, 3537, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132214:		//strykewyrm
			c.getPA().spellTeleport(2515, 4632, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132215:		//hill giants
			c.getPA().spellTeleport(2905, 9730, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132216:		//dark beasts
			c.getPA().spellTeleport(2908, 9694, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132217:		//yaks
			c.getPA().spellTeleport(3212, 3262, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 132218:		//close
			c.getPA().closeAllWindows();
		break;
		
		//Boss Interfaces
		
		case 133054:		//Dagannoths
			c.getPA().spellTeleport(1910, 4367, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Archer/Berserker/Seers Ring. Drop Rate: 1/20.");
		break;
		
		case 133055:		//Kbd
			c.getPA().spellTeleport(3007, 3849, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Dragonic Visage. Drop Rate: 1/30.");
		break;
		
		case 133056:		//Corporeal Beast
			c.getPA().spellTeleport(3303, 9375, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Spritual Shields. Drop Rate: 1/45.");
		break;
		
		case 133057:		//Tormented Demons
			c.getPA().spellTeleport(2717, 9805, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Dragon Claws. Drop Rate: 1/40.");
		break;
		
		case 133058:		//Nex
			c.sendMessage("Comming Soon");	
		break;
		
		case 133059:		//Forgotten Warrior
			c.getPA().spellTeleport(3502, 3570, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Primal. Drop Rate: 1/35.");
		break;
		
		case 133060:		//barrelChest
			c.getPA().spellTeleport(2971, 9517, 1);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Barrelchest Anchor. Drop Rate: 1/30.");
		break;
		
		case 133061:		//Next Button
			c.getPA().showInterface(34200);
		break;
		
		case 133062:		//close
			c.getPA().closeAllWindows();
		break;
		
		//Boss Interface 2
		
		case 133154:		//Kalphite Queen
			c.sendMessage("Comming Soon");	//Tele coords 3483 9510
		break;
		
		case 133155:		//Godwars	
			c.getPA().showInterface(14040);
		break;
		
		case 133156:		//Mad Mummy
			c.getPA().spellTeleport(3175, 2981, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Armadyl Staff. Drop Rate: 1/50.");
		break;
		
		case 133157:		//Sea Troll Queen	
			c.getPA().spellTeleport(2960, 9477, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Polypore Staff. Drop Rate: 1/50.");
		break;
		
		case 133158:		//Chaos Elemental
			c.getPA().spellTeleport(3239, 9364, 0);
			c.getPA().closeAllWindows();
		break;
		
		case 133159:		//Nomad
			c.getPA().spellTeleport(3258, 9517, 2);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Ring of Vigour. Drop Rate: 1/35.");
		break;
		
		case 133160:		//Skeletal Horror
			c.getPA().spellTeleport(2080, 3214, 0);
			c.getPA().closeAllWindows();
			c.sendMessage("Best Drop: Abyssal Vine Whip. Drop Rate: 1/50.");
		break;
		
		case 133161:		//Back
			c.getPA().showInterface(34100);	
		break;
		
		case 133162:		//Close
			c.getPA().closeAllWindows();	
		break;
		
		
		//1m Exp Lamp
			
			case 136191:
	             c.LampSelect = 0; 
                     c.sendMessage("<col=225>You select Attack");
                break;
                case 136212:
	             c.LampSelect = 2;
	             c.sendMessage("<col=225>You select Strength");
                break;
                case 136215:
	             c.LampSelect = 4;
	             c.sendMessage("<col=225>You select Ranged");
                break;
                case 136194:
	             c.LampSelect = 6;
	             c.sendMessage("<col=225>You select Magic");
                break;
                case 136233:
	             c.LampSelect = 1;
	             c.sendMessage("<col=225>You select Defence");
                break;
                case 136254:
	             c.LampSelect = 3;
	             c.sendMessage("<col=225>You select Hitpoints");
                break;
                case 136236:
	             c.LampSelect = 5;
	             c.sendMessage("<col=225>You select Prayer");
                break;
                case 137001:
	             c.LampSelect = 24;
	             c.sendMessage("<col=225>You select Dungeoneering");
                break;
                case 136203:
	             c.LampSelect = 16;
	             c.sendMessage("<col=225>You select Agility");
                break;
                case 136224:
	             c.LampSelect = 15;
	             c.sendMessage("<col=225>You select Herblore");
                break;
                case 136209:
	             c.LampSelect = 17;
	             c.sendMessage("<col=225>You select Thieving");
                break;
                case 136242:
	             c.LampSelect = 12;
	             c.sendMessage("<col=225>You select Crafting");
                break;
                case 137007:
	             c.LampSelect = 20;
	             c.sendMessage("<col=225>You select Runecrafting");
                break;
                case 136227:
	             c.LampSelect = 18;
	             c.sendMessage("<col=225>You select Slayer");
                break;
                case 136245:
	             c.LampSelect = 19;
	             c.sendMessage("<col=225>You select Farming");
                break;
                case 136197:
	             c.LampSelect = 14;
	             c.sendMessage("<col=225>You select Mining");
                break;
                case 136218:
	             c.LampSelect = 13;
	             c.sendMessage("<col=225>You select Smithing");
                break;
                case 136239:
	             c.LampSelect = 10;
	             c.sendMessage("<col=225>You select Fishing");
                break;
                case 137004:
	             c.LampSelect = 7;
	             c.sendMessage("<col=225>You select Cooking");
                break;
                case 136221:
	             c.LampSelect = 11;
	             c.sendMessage("<col=225>You select Firemaking");
                break;
                case 136230:
	             c.LampSelect = 23;
	             c.sendMessage("<col=225>You select Pking");
                break;
                case 136251:
	             c.LampSelect = 22;
	             c.sendMessage("<col=225>You select Summoning");
                break;
                case 136248:
	             c.LampSelect = 21;
	             c.sendMessage("<col=225>You select hunter");
                break;
                case 136200:
	             c.LampSelect = 8;
	             c.sendMessage("<col=225>You select Woodcutting");
                break;
                case 136206:
	             c.LampSelect = 9;
	             c.sendMessage("<col=225>You select Fletching");
                break;
                case 137013:
                if(c.antiDupe == 5) {
		        c.getPA().addSkillXP(1000000,c.LampSelect);
		        c.getItems().deleteItem(11137, 1);
		        c.sendMessage("<col=225>1M Xp added to skill chosen! Lamp Disapeared");
		        c.getPA().closeAllWindows();
                        c.antiDupe = 0;
                } else {
                        c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
                        c.getPA().closeAllWindows();
                }
	        break;
			
			
			//5m Exp Lamp
			
			case 137035:
	             c.LampSelect = 0; 
                     c.sendMessage("<col=225>You select Attack");
                break;
                case 137056:
	             c.LampSelect = 2;
	             c.sendMessage("<col=225>You select Strength");
                break;
                case 137059:
	             c.LampSelect = 4;
	             c.sendMessage("<col=225>You select Ranged");
                break;
                case 137038:
	             c.LampSelect = 6;
	             c.sendMessage("<col=225>You select Magic");
                break;
                case 137077:
	             c.LampSelect = 1;
	             c.sendMessage("<col=225>You select Defence");
                break;
                case 137098:
	             c.LampSelect = 3;
	             c.sendMessage("<col=225>You select Hitpoints");
                break;
                case 137080:
	             c.LampSelect = 5;
	             c.sendMessage("<col=225>You select Prayer");
                break;
                case 137101:
	             c.LampSelect = 24;
	             c.sendMessage("<col=225>You select Dungeoneering");
                break;
                case 137047:
	             c.LampSelect = 16;
	             c.sendMessage("<col=225>You select Agility");
                break;
                case 137068:
	             c.LampSelect = 15;
	             c.sendMessage("<col=225>You select Herblore");
                break;
                case 137053:
	             c.LampSelect = 17;
	             c.sendMessage("<col=225>You select Thieving");
                break;
                case 137086:
	             c.LampSelect = 12;
	             c.sendMessage("<col=225>You select Crafting");
                break;
                case 137107:
	             c.LampSelect = 20;
	             c.sendMessage("<col=225>You select Runecrafting");
                break;
                case 137071:
	             c.LampSelect = 18;
	             c.sendMessage("<col=225>You select Slayer");
                break;
                case 137089:
	             c.LampSelect = 19;
	             c.sendMessage("<col=225>You select Farming");
                break;
                case 137041:
	             c.LampSelect = 14;
	             c.sendMessage("<col=225>You select Mining");
                break;
                case 137062:
	             c.LampSelect = 13;
	             c.sendMessage("<col=225>You select Smithing");
                break;
                case 137083:
	             c.LampSelect = 10;
	             c.sendMessage("<col=225>You select Fishing");
                break;
                case 137104:
	             c.LampSelect = 7;
	             c.sendMessage("<col=225>You select Cooking");
                break;
                case 137065:
	             c.LampSelect = 11;
	             c.sendMessage("<col=225>You select Firemaking");
                break;
                case 137074:
	             c.LampSelect = 23;
	             c.sendMessage("<col=225>You select Pking");
                break;
                case 137095:
	             c.LampSelect = 22;
	             c.sendMessage("<col=225>You select Summoning");
                break;
                case 137092:
	             c.LampSelect = 21;
	             c.sendMessage("<col=225>You select hunter");
                break;
                case 137044:
	             c.LampSelect = 8;
	             c.sendMessage("<col=225>You select Woodcutting");
                break;
                case 137050:
	             c.LampSelect = 9;
	             c.sendMessage("<col=225>You select Fletching");
                break;
                case 137113:
                if(c.antiDupe == 5) {
		        c.getPA().addSkillXP(5000000,c.LampSelect);
		        c.getItems().deleteItem(11139, 1);
		        c.sendMessage("<col=225>1M Xp added to skill chosen! Lamp Disapeared");
		        c.getPA().closeAllWindows();
                        c.antiDupe = 0;
                } else {
                        c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
                        c.getPA().closeAllWindows();
                }
	        break;
		
				//Unmorph option for easter ring and ring of stone
				
				
		case 23132:
    c.setSidebarInterface(1, 7101);
    c.setSidebarInterface(2, 638);
    c.setSidebarInterface(3, 3213);
    c.setSidebarInterface(4, 1644);
    c.setSidebarInterface(5, 5608);
    if(c.playerMagicBook == 0) {
        c.setSidebarInterface(6, 1151);
    } else if (c.playerMagicBook == 1) {
        c.setSidebarInterface(6, 12855);
    } else if (c.playerMagicBook == 2) {
        c.setSidebarInterface(6, 29999);
    }
    c.setSidebarInterface(7, 18128);
    c.setSidebarInterface(8, 5065);
    c.setSidebarInterface(9, 5715); 
    c.setSidebarInterface(10, 2449);
    c.setSidebarInterface(11, 904);
    c.setSidebarInterface(12, 147);
    c.setSidebarInterface(13, 962);
    c.setSidebarInterface(0, 2423);
    if (c.playerEquipment[c.playerRing] == 7927) {
        c.getItems().deleteEquipment(c.playerEquipment[c.playerRing], c.playerRing);
        c.getItems().addItem(7927,1);
    }
    c.isNpc = false;
    c.updateRequired = true;
    c.appearanceUpdateRequired = true;
    break;
		
		
	//Reset Skill Tablet
		
		case 10252:
			c.tabletSelect = 0;
			c.sendMessage("You Select Attack,");
		break;
		
		case 10253:
			c.tabletSelect = 2;
			c.sendMessage("You Select Strength.");
		break;
		
		case 10254:
			c.tabletSelect = 4;
			c.sendMessage("You Select Ranged.");
		break;
		
		case 10255:
			c.tabletSelect = 6;
			c.sendMessage("You Select Magic.");
		break;
		
		case 11000:
			c.tabletSelect = 1;
			c.sendMessage("You Select Defence.");
		break;
		
		case 11001:
			c.tabletSelect = 3;
			c.sendMessage("You Select HitPoints.");
		break;
		
		case 11002:
			c.tabletSelect = 5;
			c.sendMessage("You Select Prayer.");
		break;
		
		case 11003:
			c.tabletSelect = 16;
			c.sendMessage("You Select Agility.");
		break;
		
		case 11004:
			c.tabletSelect = 15;
			c.sendMessage("You Select Herblore.");
		break;
		
		case 11005:
			c.tabletSelect = 17;
			c.sendMessage("You Select Thieving.");
		break;
		
		case 11006:
			c.tabletSelect = 12;
			c.sendMessage("You Select Crafting.");
		break;
		
		case 11007:
			c.tabletSelect = 20;
			c.sendMessage("You Select Runecrafting.");
		break;
		
		case 11008:
			c.tabletSelect = 14;
			c.sendMessage("You Select Mining.");
		break;
		
		case 11009:
			c.tabletSelect = 13;
			c.sendMessage("You Select Smithing.");
		break;
		
		case 11010:
			c.tabletSelect = 10;
			c.sendMessage("You Select Fishing.");
		break;
		
		case 11011:
			c.tabletSelect = 7;
			c.sendMessage("You Select Cooking.");
		break;
		
		case 11012:
			c.tabletSelect = 11;
			c.sendMessage("You Select Firemaking.");
		break;
		
		case 11013:
			c.tabletSelect = 8;
			c.sendMessage("You Select Woodcutting.");
		break;
		
		case 11014:
			c.tabletSelect = 9;
			c.sendMessage("You Select Fletching.");
		break;
		
		
		case 11015:
			if (c.inWild())
				return;
			if(c.antiDupe == 5) {
				for (int j = 0; j < c.playerEquipment.length; j++) {
				      if (c.playerEquipment[j] > 0) {
				      c.getPA().closeAllWindows();
					   c.sendMessage("You need to remove all your equipments before doing this");
					   c.antiDupe = 0;
					   return;
				      }
				}
				try {
					int skilld = c.tabletSelect;
					int leveld = 1;
					c.playerXP[skilld] = c.getPA().getXPForLevel(leveld)+1;
					c.playerLevel[skilld] = c.getPA().getLevelForXP(c.playerXP[skilld]);
					c.getPA().refreshSkill(skilld);
					c.sendMessage("You reseted one of your skills");
					c.antiDupe = 0;
					c.getPA().closeAllWindows();
				} catch (Exception e){}
			} else {
				c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
                c.getPA().closeAllWindows();
			}
				
		break;
		
		case 47002:
			c.tabletSelect = 18;
			c.sendMessage("You Select Slayer.");
		break;
		
		case 54090:
			c.tabletSelect = 19;
			c.sendMessage("You Select Farming.");
		break;
		
		
		
		
			//Starter Pack
		
		case 138178://pure
		if (c.antiDupe == 5) {
		c.antiDupe = 0;
		c.getItems().addItem(995, 10000000);
		c.getItems().addItem(9070, 1);
		c.getItems().addItem(9071, 1);
		c.getItems().addItem(9069, 1);
		c.getItems().addItem(841, 1);
		c.getItems().addItem(884, 500);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(892, 500);
		c.getItems().addItem(1323, 1);
		c.getItems().addItem(1333, 1);
		c.getItems().addItem(4587, 1);
		c.getItems().addItem(1712, 1);
		c.getItems().addItem(392, 1000);
		c.getItems().addItem(1381, 1);
		c.getPA().showInterface(3559);
		
		
		} else {
			c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
		}
		break;
		
		case 138181://Master
		if (c.antiDupe == 5) {
		c.antiDupe = 0;
		c.getItems().addItem(995, 10000000);
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(1127, 1);
		c.getItems().addItem(1079, 1);
		c.getItems().addItem(841, 1);
		c.getItems().addItem(884, 500);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(892, 500);
		c.getItems().addItem(1323, 1);
		c.getItems().addItem(1333, 1);
		c.getItems().addItem(4587, 1);
		c.getItems().addItem(4151, 1);
		c.getItems().addItem(1712, 1);
		c.getItems().addItem(392, 1000);
		c.getItems().addItem(1381, 1);
		c.getItems().addItem(4131, 1);
		c.getPA().showInterface(3559);
		
		
		} else {
			c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
		}
		break;
		
		case 138184://Skiller
		if (c.antiDupe == 5) {
		c.antiDupe = 0;
		c.getItems().addItem(995, 15000000);
		c.getItems().addItem(7386, 1);
		c.getItems().addItem(7390, 1);
		c.getItems().addItem(7394, 1);
		c.getItems().addItem(6739, 1);
		c.getItems().addItem(15259, 1);
		c.getItems().addItem(1837, 1);
		c.getItems().addItem(295, 1);
		c.getItems().addItem(2474, 1);
		c.getItems().addItem(2476, 1);
		c.getPA().showInterface(3559);
		
		} else {
			c.sendMessage("<shad=16711680>[Server AntiDupe System]: You Tried to Dupe, We Bringing the Cops!");
		}
		break;
		
		//End of New Ones

		/*case 114112://melee set
		if (c.inWild() && c.isBanking) {
		c.sendMessage("You cannot do this right now");
		} else if(c.getItems().freeSlots() <= 10) {
		c.sendMessage("You need atleast 10 free slot's to use this feature.");
		} else if (c.getItems().playerHasItem(995, 350000)) {
		c.getItems().deleteItem2(995, 350000);
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(1127, 1);
		c.getItems().addItem(1079, 1);
		c.getItems().addItem(3842, 1);
		c.getItems().addItem(4587, 1);
		c.getItems().addItem(1231, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(2550, 1);
		} else {
		c.sendMessage("You need atleast 350,000 coins to use this feature.");
		}
		break;
			case 46230:
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(10551, 1);
		c.getItems().addItem(4087, 1);
		c.getItems().addItem(11732, 1);
		c.getItems().addItem(13006, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(6737, 1);
		c.getItems().addItem(8850, 1);
		c.getItems().addItem(4151, 1);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 1);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 5);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			case 46234:
		c.getItems().addItem(10941, 1);
		c.getItems().addItem(10939, 1);
		c.getItems().addItem(10940, 1);
		c.getItems().addItem(10933, 1);
		c.getItems().addItem(18508, 1);
		c.getItems().addItem(2462, 1);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
			break;
			case 46227:
		c.getItems().addItem(12222, 1);
		c.getItems().addItem(6107, 1);
		c.getItems().addItem(2497, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(12988, 1);
		c.getItems().addItem(10498, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(4151, 1);
		c.getItems().addItem(892, 1000);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			
					case 114113://mage set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 7) {
				c.sendMessage("You need atleast 7 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3755, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(3842, 1);
				c.getItems().addItem(4675, 1);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;
			
								case 114114://range set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 13) {
				c.sendMessage("You need atleast 13 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(3749, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2491, 1);
				c.getItems().addItem(6328, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(9185, 1);
				c.getItems().addItem(9243, 100);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(892, 100);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
			case 114115://hybrid set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 14) {
				c.sendMessage("You need atleast 14 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(555, 300);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(565, 100);
				c.getItems().addItem(4675, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2415, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3105, 1);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
						case 114118://runes set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 10) {
				c.sendMessage("You need atleast 10 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(560,1000);
				c.getItems().addItem(555,1000);
				c.getItems().addItem(565,1000);
				c.getItems().addItem(9075,1000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(556,1000);
				c.getItems().addItem(554,1000);
				c.getItems().addItem(562,1000);
				c.getItems().addItem(561,1000);
				c.getItems().addItem(563,1000);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;
			
									case 114119://barrage set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 2000000)) {
				c.getItems().deleteItem2(995, 2000000);
				c.getItems().addItem(555,6000);
				c.getItems().addItem(560,4000);
				c.getItems().addItem(565,2000);
			} else {
				c.sendMessage("You need atleast 2,000,000 coins to use this feature.");
			}
			break;
			
			case 114120://veng set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(560,200);
				c.getItems().addItem(9075,400);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
			case 114123://shark set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(385,1000);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
						case 114124://tuna pot set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 150000)) {
				c.getItems().deleteItem2(995, 150000);
				c.getItems().addItem(7060,1000);
			} else {
				c.sendMessage("You need atleast 150,000 coins to use this feature.");
			}
			break;
			
			case 114125://super set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 80000)) {
				c.getItems().deleteItem2(995, 80000);
				c.getItems().addItem(146,100);
				c.getItems().addItem(158,100);
				c.getItems().addItem(164,100);
			} else {
				c.sendMessage("You need atleast 80,000 coins to use this feature.");
			}
			break;
			
						case 114126://super restores biatch
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3025,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114127://mage pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3041,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114128://range pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 36000)) {
				c.getItems().deleteItem2(995, 36000);
				c.getItems().addItem(2445,100);
			} else {
				c.sendMessage("You need atleast 36,000 coins to use this feature.");
			}
			break;*/
			
			
			


		
			
		case 17111://stop viewing viewing orb
                c.setSidebarInterface(10, 2449);
                c.viewingOrb = false;
                c.teleportToX = 2399;
                c.teleportToY = 5171;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59139://viewing orb southwest
                c.viewingOrb = true;
                c.teleportToX = 2388;
                c.teleportToY = 5138;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59138://viewing orb southeast
                c.viewingOrb = true;
                c.teleportToX = 2411;
                c.teleportToY = 5137;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59137://viewing orb northeast
                c.viewingOrb = true;
                c.teleportToX = 2409;
                c.teleportToY = 5158;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59136://viewing orb northwest
                c.viewingOrb = true;
                c.teleportToX = 2384;
                c.teleportToY = 5157;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59135://viewing orb middle
                c.viewingOrb = true;
                c.teleportToX = 2398;
                c.teleportToY = 5150;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;
			case 107229:
				if (c.isDonator == 1 && c.inGWD()) {
				c.Arma = 15;
				c.Band = 15;
				c.Sara = 15;
					c.sendMessage("Your magical donator rank forces your KC to raise to 15!");
				} else {
					c.sendMessage("You must be a donator and be in godwars dungeon to use this!");
				} 
			break;

				case 108003:
				if (c.isDonator == 1) {
				c.setSidebarInterface(4, 27620);
				} else {
				c.sendMessage("You must be an donator to view this tab!");
				return;				
				}
				break;
				
 
                                case 82020:
				for(int i = 0; i < c.playerItems.length; i++) {
					c.getItems().bankItem(c.playerItems[i], i,c.playerItemsN[i]);
				}
				break;

				case 107231:
				if (c.isDonator == 1) {
					c.getPA().spellTeleport(2722, 4897, 0);
					c.sendMessage("<img=0>Welcome to New Donator Zone, Enjoy Bro!<img=0>");
				} else {
					c.sendMessage("You must be an donator to teleport to the donator Island!");
					return;				
				}
				break;
			case 108006:
				if (c.xpLock == false) {
					c.xpLock = true;
					c.sendMessage("Your XP are now LOCKED!");
				} else {
					c.xpLock = false;
					c.sendMessage("Your XP are now UNLOCKED!");
				} 
				break;
				case 113247:
				if (c.xpLock == false) {
					c.xpLock = true;
					c.sendMessage("Your XP are now LOCKED!");
				} else {
					c.xpLock = false;
					c.sendMessage("Your XP are now UNLOCKED!");
				} 
		
			break;
			case 107230:
			if(c.isDonator == 0 || c.inWild()) {
			c.sendMessage("You must be outside wilderness and be a donator to use this!");
			return;
			}
			if (c.playerMagicBook == 0 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.setSidebarInterface(0, 328);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				return;
			}	
			if (c.playerMagicBook == 1 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 2;
				c.setSidebarInterface(0, 328);
				c.setSidebarInterface(6, 16640);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				return;
			}
			if (c.playerMagicBook == 2 && c.isDonator == 1 && !c.inWild()) {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.setSidebarInterface(0, 328);
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				return;
			}
			break;
					case 94142:
if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.sendMessage("Your BoB items have drop on the floor");
} else {
c.sendMessage("You do not have a familiar currently spawned");
}
			//1st tele option
		
			case 9190:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15086, 1);
						c.sendMessage("You get a six-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15092, 1);
						c.sendMessage("You get a ten-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				
				} else if (c.teleAction == 3) {
                                        c.sendMessage("GodWars is under construction atm please wait untill its fixed");
                                } else if (c.teleAction == 4) {
					//varrock wildy
					c.getPA().spellTeleport(2539, 4716, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				} else if (c.teleAction == 20) {
					//Varrock
					c.getPA().spellTeleport(3210,3424, 0); 
				} else if (c.teleAction == 8) {
					c.getPA().spellTeleport(2960, 9477, 0);//sea troll queen
					
					
				//Milestone Capes 
				
				} else if (c.dialogueAction == 401) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(10)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20000, 1);
					c.sendMessage("You made a [10] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 10 of every skill to make this Cape.");
				}
				return;
				}
				
				
				} else if (c.dialogueAction == 402) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(50)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20004, 1);
					c.sendMessage("You made a [50] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 50 of every skill to make this Cape.");
				}
				return;
				}
				
				
				} else if (c.dialogueAction == 403) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(90)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20008, 1);
					c.sendMessage("You made a [90] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 90 of every skill to make this Cape.");
				}
				return;
				}
				
				} else if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2786, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				} else if (c.teleAction == 21) {
					c.getPA().spellTeleport(2480, 3437, 0);
					c.dialogueAction = -1;
				}
				break;
				case 62158:
				c.getPA().showInterface(26099);
				c.getPA().sendFrame200(26101, 9847);//chatid
				c.getPA().sendFrame185(26101);
				if (c.KC > c.DC) {
				c.getPA().sendFrame126("@or1@Kills: @gre@"+c.KC+"", 26105);
				c.getPA().sendFrame126("@or1@Deaths: @red@"+c.DC+"", 26106);
				}
				if (c.KC < c.DC) {
				c.getPA().sendFrame126("@or1@Kills: @red@"+c.KC+"", 26105);
				c.getPA().sendFrame126("@or1@Deaths: @gre@"+c.DC+"", 26106);
				}
				c.getPA().sendFrame126("@or1@Name: @gre@"+c.playerName+"", 26107);
				c.getPA().sendFrame126("@or1@Combat Level: @gre@"+c.combatLevel+"", 26108);
					if (c.playerRights == 1) {
						c.getPA().sendFrame126("@or1@Rank: @gre@Moderator", 26109);
					}
					if (c.playerRights == 2) {
						c.getPA().sendFrame126("@or1@Rank: @gre@Admin", 26109);
					}
					if (c.playerRights == 3) {
						c.getPA().sendFrame126("@or1@Rank: @gre@Owner", 26109);
					}
					if (c.playerRights == 0) {
						c.getPA().sendFrame126("@or1@Rank: @gre@Player", 26109);
					}
					if(c.playerRights == 4) {
						c.getPA().sendFrame126("@or1@Rank: @gre@Donator", 26109);
					}
				c.getPA().sendFrame126("@or1@Source Points: @gre@0", 26111);
				c.getPA().sendFrame126("@or1@Activity Points: @gre@"+c.pcPoints+"", 26112);
				c.getPA().sendFrame126("@or1@PK Points: @gre@0", 26113);
				c.getPA().sendFrame126("@or1@Boss Points: @gre@0", 26115);
				c.getPA().sendFrame126("@or1@Pest Points: @gre@0", 26116);
				c.getPA().sendFrame126("@or1@Assault Points: @gre@0", 26117);
				
				c.getPA().sendFrame126("@or1@Gambles Won: @gre@0", 26118);
				c.getPA().sendFrame126("@or1@Gambles Lost: @gre@0", 26119);
				c.getPA().sendFrame126("@or1@Battles Won: @gre@0", 26120);
				c.getPA().sendFrame126("@or1@Battles Lost: @gre@0", 26121);
				c.getPA().sendFrame126("@or1@NPC Kills: @gre@0", 26122);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0
			
			case 66126: //Summoning Special Moves	
			if (c.summonSpec < 1){
				if (c.lastsummon == 7344) {
					final int damage = Misc.random(30) + 10;
					if(c.npcIndex > 0) {
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Steel Titan Damages your Opponent.");
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Steel Titan Damages your Opponent.");
							c.startAnimation(1914);
					}
				} else if (c.lastsummon == 7340) {
					final int damage = Misc.random(25) + 5;
					if(c.npcIndex > 0) {
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Geyser Titan Damages your Opponent.");
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Geyser Titan Damages your Opponent.");
							c.startAnimation(1914);
					}
				} else if (c.lastsummon == 7356) {
					final int damage = Misc.random(20) + 5;
					if(c.npcIndex > 0) {
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Fire Titan Damages your Opponent.");
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Fire Titan Damages your Opponent.");
							c.startAnimation(1914);
					}
				} else if (c.lastsummon == 7350) {
					final int damage = Misc.random(19) + 4;
					if(c.npcIndex > 0) {
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Abyssal Titan Damages your Opponent.");
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Abyssal Titan Damages your Opponent.");
							c.startAnimation(1914);
					}
				} else if (c.lastsummon == 7358) {
					final int damage = Misc.random(17) + 4;
					if(c.npcIndex > 0) {
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Moss Titan Damages your Opponent.");
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Moss Titan Damages your Opponent.");
							c.startAnimation(1914);
					}
				} else if (c.lastsummon == 6874) {
					c.getItems().addItem(15272, 3);
					c.sendMessage("Your Pak Yack's Special Supplys you with Food!");
				} else if (c.lastsummon == 6823) {
					c.playerLevel[3] += 25;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.sendMessage("Your Unicorn's Special Heals you for 250 HP!");
					c.getPA().refreshSkill(3);
				} else if (c.lastsummon == 6814) {
					c.playerLevel[3] += 13;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.sendMessage("Your Bunyip's Special Heals you for 130 HP!");
					
					c.getPA().refreshSkill(3);
				} else if (c.lastsummon == 6870) {
					c.playerLevel[3] += 15;
					c.playerLevel[6] += 6;
					c.sendMessage("Your Wolpertinger's Special Heals you for 150 HP!");
					c.sendMessage("Your Wolpertinger's Increases and Restores your Magic!");
					if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
					c.playerLevel[6] = c.getLevelForXP(c.playerXP[6])+6;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(6);
				} else {
					c.sendMessage("You have no familiar with special attack spawned!");
				}
			c.summonSpec = 240;
			} else {
				c.sendMessage("You must wait at least 4 Minutes before using this again.");
			}
			break;
				
				
			
  case 154:
                        if(System.currentTimeMillis() - c.logoutDelay < 8000) {
                                c.sendMessage("You cannot do skillcape emotes in combat!");
                        return;
                        }
                        if(System.currentTimeMillis() - c.lastEmote >= 7000) {
                        if(c.getPA().wearingCape(c.playerEquipment[c.playerCape])) {
                                c.stopMovement();
                                c.gfx0(c.getPA().skillcapeGfx(c.playerEquipment[c.playerCape]));
                                c.startAnimation(c.getPA().skillcapeEmote(c.playerEquipment[c.playerCape]));
                } else if(c.playerEquipment[c.playerCape] == 20013) {
                        c.getPA().compemote(c);
				} else if(c.playerEquipment[c.playerCape] == 20011) {
                        c.getPA().compemote(c);
				} else if(c.playerEquipment[c.playerCape] == 20009) {
                        c.getPA().compemote(c);
				} else if(c.playerEquipment[c.playerCape] == 20015) {
                        c.getPA().veteranemote();
				} else if(c.playerEquipment[c.playerCape] == 18509) {
                        c.getPA().dungemote2(c);
                } else if(c.playerEquipment[c.playerCape] == 19709) {
                        c.getPA().dungemote(c);
                } else {
                                c.sendMessage("You must be wearing a Skillcape to do this emote.");
                        }
                                c.lastEmote = System.currentTimeMillis();
                }
                break;

			//2nd tele option
			case 9191:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15088, 1);
						c.sendMessage("You get two six-sided dice out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15094, 1);
						c.sendMessage("You get a twelve-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(2662, 2650, 0);
				} else if (c.teleAction == 3) {
					//kbd
					c.getPA().spellTeleport(3007, 3849, 0);
				} else if (c.teleAction == 4) {
					//graveyard
					c.getPA().spellTeleport(3243, 3517, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079, 9502,0);
				
				} else if (c.teleAction == 8) {
					c.getPA().spellTeleport(3239, 9364,0);
					c.sendMessage("PowerFul Boss Watch out, Requiered 2+ People!");
				} else if (c.teleAction == 20) {
					//Falador	
					c.getPA().spellTeleport(2965,3380, 0);
					
					//Milestone Capes 
				
				} else if (c.dialogueAction == 401) {
					
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(20)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20001, 1);
					c.sendMessage("You made a [20] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 20 of every skill to make this Cape.");
				}
				return;
				}
				
					
				} else if (c.dialogueAction == 402) {	
					
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(60)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20005, 1);
					c.sendMessage("You made a [60] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 60 of every skill to make this Cape.");
				}
				return;
				}	
					
				} else if (c.dialogueAction == 403) {		
				} else if (c.dialogueAction == 90) {
					c.getShops().openShop(40);
				} else if (c.dialogueAction == 91) {
					c.getShops().openShop(42);
				} else if (c.dialogueAction == 92) {
					c.getShops().openShop(44);
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2796, 4818, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				}
				if (c.teleAction == 21) {
					c.getPA().spellTeleport(2151, 5099, 0);
					c.dialogueAction = -1;
				}
				break;
			//3rd tele option
			
			


			case 9192:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15100, 1);
						c.sendMessage("You get a four-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15096, 1);
						c.sendMessage("You get a twenty-sided die out of the dice bag.");
				}
					c.getPA().closeAllWindows();
				}
				
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2438, 5168, 0);
					c.sendMessage("To fight Jad, enter the cave.");
				} else if (c.teleAction == 3) {
					//dag kings
					c.getPA().spellTeleport(1910, 4367, 0);
					c.sendMessage("Climb down the ladder to get into the lair.");
				} else if (c.teleAction == 4) {
					//Lava Crossing
					c.getPA().spellTeleport(3367, 3935, 0);
					
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2597,3408,0);
				}
				  else if (c.teleAction == 21) {
					c.getPA().spellTeleport(3022,9828,0);
				
                                
				} else if (c.teleAction == 20) {
					//Lumbridge	
					c.getPA().spellTeleport(3222,3218, 0);
				
				
				//Milestone Capes 
				
				} else if (c.dialogueAction == 401) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(30)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20002, 1);
					c.sendMessage("You made a [30] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 30 of every skill to make this Cape.");
				}
				return;
				}
				
				} else if (c.dialogueAction == 402) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(70)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20006, 1);
					c.sendMessage("You made a [70] Milestone Cape.");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 70 of every skill to make this Cape.");
				}
				return;
				}
				
				} else if (c.dialogueAction == 403) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(99)) {
				if (c.getItems().playerHasItem(1759, 1))	{
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20009, 1);
					c.getItems().addItem(20010, 1);
					c.getItems().addItem(20011, 1);
					c.getItems().addItem(20012, 1);
					c.sendMessage("You made a Completionist Cape.");
					c.sendMessage("Congratz,You are the master of skilling!");
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need 99 of every skill to make this Cape.");
				}
				return;
				}	
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				}
				if (c.teleAction == 8) {
					c.getPA().startTeleport(3258, 9517, 2, "ancient");
					c.sendMessage("Approach Nomad with caution.");
				}
				break;

			case 9193:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15090, 1);
						c.sendMessage("You get an eight-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15098, 1);
						c.sendMessage("You get the percentile dice out of the dice bag.");
				}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
					c.sendMessage("You teleported to brimhaven dungeon, be sure to bring antifire-shield.");
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//Tormented Demons
					c.getPA().spellTeleport(2717, 9805, 0);
				} else if (c.teleAction == 21) {
				  if((c.playerLevel[10] >= 80)) {
					c.getPA().spellTeleport(2611,3396,0);
					c.sendMessage("You teleport to the fishing guild ewww fishy!");
					} else {
					c.getDH().sendDialogues(59, 945);
				  }
				 } else if (c.teleAction == 21) {
				  if((c.playerLevel[10] < 90)) {
					c.sendMessage("You must be at least 90 Fishing to Enter!");
				  }
				} else if (c.teleAction == 4) {
					//Fala
				c.getPA().spellTeleport(3086, 3516, 0);

				
				//Milestone Capes 
				
				} else if (c.dialogueAction == 401) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(40)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20003, 1);
					c.sendMessage("You made a [40] Milestone Cape");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 80 of every skill to make this Cape");
				}
				return;
				}
				
				
				} else if (c.dialogueAction == 402) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(80)) {
				if (c.getItems().playerHasItem(1759, 1))	{
				
					c.startAnimation(5);
					c.getItems().deleteItem(1759, 1);
					c.getItems().addItem(20007, 1);
					c.sendMessage("You made a [80] Milestone Cape");
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You need at least 80 of every skill to make this Cape");
				}
				return;
				}
				
				
				
				} else if (c.dialogueAction == 403) {
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 90) {
					c.getShops().openShop(41);	
				} else if (c.dialogueAction == 91) {
					c.getShops().openShop(43);	
				} else if (c.dialogueAction == 92) {
					c.getShops().openShop(45);	
				} else if (c.teleAction == 20) {
					//Canifes
					c.getPA().spellTeleport(3496,3488, 0);
				}
				if (c.teleAction == 8) {
					c.getPA().startTeleport(2080, 3214, 0, "ancient");
					c.sendMessage("Beware of the Snakes!.");
				}
				break;
			case 9194:
				if (c.dialogueAction == 190) {
					c.getDH().sendDialogues(191, 4286);
					return;
					}
				if (c.dialogueAction == 191) {
					c.getDH().sendDialogues(0, 4287);
					return;
					}
				if (c.dialogueAction == 107) {
					c.getDH().sendDialogues(106, 4288);
					return;
					}
				if (c.dialogueAction == 106) {
					c.getDH().sendDialogues(107, 4289);
					return;
					}
				
				//Milestone Capes 
				
				if (c.dialogueAction == 401) {
					c.getDH().sendDialogues(401, 0);
				
				} else if (c.dialogueAction == 402) {
					c.getDH().sendDialogues(402, 0);	
				
				} else if (c.dialogueAction == 403) {
				
				for (int i = 0; i < 25; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(99)) {
				if (c.getItems().playerHasItem(1759, 1)) {
				
					c.startAnimation(5);
					c.getItems().addItem(20013, 1);
					c.getItems().addItem(20014, 1);
					c.sendMessage("You made a Max Cape.");
					c.sendMessage("Congratz,You are the master of skilling!");
					c.getItems().deleteItem(1759, 1);
				
				} else {
					c.sendMessage("You need a Ball of Wool to make this Cape.");
				}
				} else {
					c.sendMessage("You Need 99 of every skill to make this Cape.");
				}
				return;
				}
				}
				
				
				
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(3117, 9847, 0);
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.getPA().spellTeleport(2865,3546,0);
					//c.getPA().closeAllWindows();
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.sendMessage("Talk to the Zombie to start the Nazi's");
					c.getPA().spellTeleport(2606, 9893, 0);
				} else if (c.teleAction == 3) {
					c.getPA().spellTeleport(3302,9372,0);
					c.sendMessage("Enter the gate to fight the mighty Corporeal Beast!");
					c.sendMessage("Note: Magic protect, Ruby bolts (e) and Diamond bolts (e) are recommended!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					c.getPA().spellTeleport(2980, 3617, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				}

				if (c.teleAction == 21) {
					c.getPA().spellTeleport(3348, 3334, 0);
					c.dialogueAction = -1;
				}

				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				
				} else if (c.teleAction == 20) {
				//Camelot
					c.getPA().spellTeleport(2757,3477,0);
				}
				break;
			
			case 71074:
				if (c.clanId >= 0 && Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
					if (c.CSLS == 0) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						Server.clanChat.clans[c.clanId].CS = 1;
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
						c.CSLS = 1;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					if (c.CSLS == 1) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 2;
						Server.clanChat.clans[c.clanId].CS = 2;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
			c.lastEmote = System.currentTimeMillis();
						return;

				}	
				}	
					if (c.CSLS == 2) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						if(Server.clanChat.clans[c.clanId].playerz == 1) {
						c.sendMessage("There must be atleast 2 members in the clan chat to toggle Coinshare ON.");
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
						}
						c.CSLS = 3;
						Server.clanChat.clans[c.clanId].CS = 3;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
						return;

				}	
				}	
					if (c.CSLS == 3) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					} else {
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;
			
		
			
			case 15147:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15146:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
									case 10247:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
												case 9110:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 28;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15150:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15149:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15148:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 28;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 15159:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15158:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15157:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 15156:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 28;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29017:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 29016:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 24253:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 16062:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 28;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29022:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 29020:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 29019:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
						case 29018:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 28;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 29026:
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
				
			break;
			case 29025://smelt 5
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 5;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 29024://smelt 10
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 10;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 108005:
			c.getPA().showInterface(19148);
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
			if (c.dialogueAction == 850) {
if (System.currentTimeMillis() - c.lastButton > 600) {
if (c.gotStarter == 0) {
c.easyMode = true;
c.finishedTut = true;
c.getPA().removeAllWindows();
c.antiDupe = 5;
c.gotStarter = 1;
c.getPA().showInterface(35500);
c.lastButton = System.currentTimeMillis();
} else {
c.easyMode = true;
c.finishedTut = true;
c.getPA().removeAllWindows();
c.lastButton = System.currentTimeMillis();
}
}
}
           int npcType = 6138;			
			  if(c.dialogueAction == 42) {
                                
                                if (c.inWild())
				return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
				      if (c.playerEquipment[j] > 0) {
				      c.getPA().closeAllWindows();
					   c.getDH().sendDialogues(420, npcType);
					   return;
				      }
				}
				try {
					int skilld = 1;
					int leveld = 1;
					c.playerXP[skilld] = c.getPA().getXPForLevel(leveld)+5;
					c.playerLevel[skilld] = c.getPA().getLevelForXP(c.playerXP[skilld]);
					c.getPA().refreshSkill(skilld);
								//	c.getPA().closeAllWindows();
				c.getDH().sendDialogues(230, npcType);
				} catch (Exception e){}
			}
                                if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
				}
				if(c.dialogueAction == 100) {
					c.getDH().sendDialogues(25, 946);
				}
					
			break;
			
			
			case 9179:
			if (c.dialogueAction == 850) {
if (System.currentTimeMillis() - c.lastButton > 600) {
        c.hardMode = true;
c.finishedTut = true;
c.getPA().removeAllWindows();
c.lastButton = System.currentTimeMillis();
}
}
	   npcType = 6138;
                         if(c.dialogueAction == 42) { //prayer
				if (c.inWild())
				return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
					c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skillp = 5;
					int levelp = 1;
					c.playerXP[skillp] = c.getPA().getXPForLevel(levelp)+5;
					c.playerLevel[skillp] = c.getPA().getLevelForXP(c.playerXP[skillp]);
					c.getPA().refreshSkill(skillp);
									//c.getPA().closeAllWindows();
				c.getDH().sendDialogues(260, npcType);
				} catch (Exception e){}
			}
                                if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(21, 946);
				}
				if(c.dialogueAction == 100) {
					c.getGamble().gambleBlackJack(c);
				}	

				else if (c.dialogueAction == 509) {
                                        c.sendMessage("You Pull Your Christmas Cracker with Santa Claus");
                                        c.getItems().deleteItem2(962, 1);
                                        c.getItems().addItem(c.getPA().randomPhat(), 1);
                                        c.dialogueAction = -1;
                                }
			break;
			
			case 9180:
				npcType = 6138;
			if(c.dialogueAction == 42) { //attack
			       if (c.inWild())
			       return;
			       for (int j = 0; j < c.playerEquipment.length; j++) {
				     if (c.playerEquipment[j] > 0) {
					c.getPA().closeAllWindows();
					      c.getDH().sendDialogues(420, npcType);
					      return;
					}
				}
				try {
					int skill = 0;
					int levela = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(levela)+5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
									//c.getPA().closeAllWindows();
				c.getDH().sendDialogues(240, npcType);
				} catch (Exception e){}
			}
                                if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(23, 946);
				}
				if(c.dialogueAction == 100) {
					if(!c.getItems().playerHasItem(995, 1000000)) {
						c.sendMessage("You need at least 1M coins to play this game!");
						c.getPA().removeAllWindows();
						break;
					}
					c.getGamble().playGame(c);
				}
			break;
			
			case 9181:
			if (c.dialogueAction == 850) {
if (System.currentTimeMillis() - c.lastButton > 600) {
        c.extremeMode = true;
c.finishedTut = true;
c.getPA().removeAllWindows();
c.lastButton = System.currentTimeMillis();
}
}

	  npcType = 6138;
			if(c.dialogueAction == 42) { //allstats
			       if (c.inWild())
			       return;
			       for (int j = 0; j < c.playerEquipment.length; j++) {
				     if (c.playerEquipment[j] > 0) {
				     c.getPA().closeAllWindows();
					   c.getDH().sendDialogues(420, npcType);
					   return;
				      }
				}
				try {
					int skill1 = 0;
					int level = 1;
					c.playerXP[skill1] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill1] = c.getPA().getLevelForXP(c.playerXP[skill1]);
					c.getPA().refreshSkill(skill1);
					int skill2 = 1;
				//	int level = 1;
					c.playerXP[skill2] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill2] = c.getPA().getLevelForXP(c.playerXP[skill2]);
					c.getPA().refreshSkill(skill2);
					int skill3 = 2;
				//	int level = 1;
					c.playerXP[skill3] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill3] = c.getPA().getLevelForXP(c.playerXP[skill3]);
					c.getPA().refreshSkill(skill3);
					int skill4 = 3;
					level = 10;
					c.playerXP[skill4] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill4] = c.getPA().getLevelForXP(c.playerXP[skill4]);
					c.getPA().refreshSkill(skill4);
					int skill5 = 4;
					level = 1;
					c.playerXP[skill5] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill5] = c.getPA().getLevelForXP(c.playerXP[skill5]);
					c.getPA().refreshSkill(skill5);
					int skill6 = 5;
				//	int level = 1;
					c.playerXP[skill6] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill6] = c.getPA().getLevelForXP(c.playerXP[skill6]);
					c.getPA().refreshSkill(skill6);
					int skill7 = 6;
				//	int level = 1;
					c.playerXP[skill7] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill7] = c.getPA().getLevelForXP(c.playerXP[skill7]);
					c.getPA().refreshSkill(skill7);
				//	c.getPA().closeAllWindows();
				c.getDH().sendDialogues(250, npcType);
				} catch (Exception e){}
			}
                                if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				} else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				} else {
					c.getPA().removeAllWindows();
				}
				c.dialogueAction = 0;
				break;
			
			case 1093:
			case 1094:
			case 1097:
			case 15486:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 18355)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient, chaotic staff or a SOL.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;


			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
					} else if (c.dialogueAction == 1000) {
                    c.getItems().addItem(c.floweritem, 1);
                    c.getPA().objectToRemove(ClickItem.flowerX, ClickItem.flowerY); 
                    ClickItem.flowerX = 0;
                    ClickItem.flowerY = 0;
                    ClickItem.flowers = 0;
c.getPA().closeAllWindows();
					//Tax Bags Anti-Dupe
					
					} else if (c.dialogueAction == 366) {
						c.antiDupe = 5;
						if (c.getItems().playerHasItem(995, 250000000) && c.antiDupe == 5) {
							c.getItems().deleteItem(10832, 1);
							c.getItems().deleteItem(995, 250000000);
							c.getItems().addItem(10833, 1);
							c.antiDupe = 0;
							c.sendMessage("<shad=0>You Have Upgraded Your Tax Bag to </col><shad=16711680>[500M]</col><shad=0>.");
					} else {
							c.sendMessage("<shad=0>You Need </col><shad=16711680>[250m]</col> <shad=0>to upgrade your tax bag.");
							c.antiDupe = 0;
					} 
					if (c.antiDupe == 0) {
					}
					
					} else if (c.dialogueAction == 367) {
						c.antiDupe = 5;
					if (c.getItems().playerHasItem(995, 500000000) && c.antiDupe == 5) {
							c.getItems().deleteItem(10833, 1);
							c.getItems().deleteItem(995, 500000000);
							c.getItems().addItem(10834, 1);
							c.antiDupe = 0;
							c.sendMessage("<shad=0>You Have Upgraded Your Tax Bag to </col><shad=16711680>[1000M]</col><shad=0>.");
					} else {
							c.sendMessage("<shad=0>You Need </col><shad=16711680>[500m]</col> <shad=0>to upgrade your tax bag.");
							c.antiDupe = 0;
					}
					if (c.antiDupe == 0) {
					}
					
					} else if (c.dialogueAction == 368) {
						c.antiDupe = 5;
						if (c.getItems().playerHasItem(995, 1000000000) && c.antiDupe == 5) {
							c.getItems().deleteItem(10834, 1);
							c.getItems().deleteItem(995, 1000000000);
							c.getItems().addItem(10835, 1);
							c.antiDupe = 0;
							c.sendMessage("<shad=0>You Have Upgraded Your Tax Bag to </col><shad=16711680>[2000M]</col><shad=0>.");
					} else {
							c.sendMessage("<shad=0>You Need </col><shad=16711680>[1000m]</col> <shad=0>to upgrade your tax bag.");
							c.antiDupe = 0;
					}
					if (c.antiDupe == 0) {
					}
					
					
					
					
} else if (c.dialogueAction == 601) {
	c.getPA().movePlayer(2607, 9669, 0);
	c.getPA().removeAllWindows();
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);
				} else if (c.dialogueAction == 56) {
					
		if (c.Wheel == 0) {
		c.getDH().sendDialogues(57, 945);
		} else if (c.Wheel > 0) {
		c.getItems().addItem(c.getPA().Wheel(), 1);
		c.Wheel = (c.Wheel - 1);
		c.getDH().sendDialogues(58, 945);
		}
		break;	
				} else if (c.dialogueAction == 44) {
					c.getPA().enterZombies();
					c.sendMessage("Good luck! Have a Happy Zombie RAMPAGE!");		
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
				} else if (c.dialogueAction == 186) {
                                        Server.lottery.enterLottery(c);
                                        c.getPA().removeAllWindows();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(2957,3218,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 50) {
					c.getPA().startTeleport(2661,3307,0,"modern");
					c.sendMessage("This is PVP");
			} else if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3007,3631,0,"modern");
				} else if (c.dialogueAction == 8) {
					c.getPA().resetBarrows();
					c.sendMessage("Your barrows have been reset.");
				} else if (c.dialogueAction == 13) {
					c.getPA().spellTeleport(1762, 5180, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 18) {
		c.sendMessage("<shad=299910><img=2>You will be starting Dung Now!<img=2>");
		c.getPA().movePlayer(2847, 9637, 0);
					c.dialogueAction = -1;
					//melee Shop
					} else if (c.dialogueAction == 92) {
					c.getShops().openShop(46);
					
 } else if (c.dialogueAction == 27) {
					c.getPA().movePlayer(3210, 3424, 0);
					c.monkeyk0ed = 0;
					c.Jail = false;
		c.forcedText = "I swear to god that i will never break the rules anymore!";
			c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
				}
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				break;
			
			case 9158:
				if (c.dialogueAction == 92) {
					c.getShops().openShop(47);
				} else if (c.dialogueAction == 50) {
					c.getPA().startTeleport(2559,3089,0,"modern");
					c.sendMessage("This is PVP!");
			} else if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3243,3790,0,"modern");
				} else if (c.dialogueAction == 1000) {
                    c.getPA().closeAllWindows();
                    ClickItem.flowerTime = 20;
                //Tax Bags

				} else if (c.dialogueAction == 366) {
						c.antiDupe = 5;
						if (c.antiDupe == 5) {
							c.getItems().deleteItem(10832, 1);
							c.getItems().addItem(995, 250000000);
							c.getItems().addItem(10831, 1);
							c.sendMessage("<shad=0>You have picked </col><shad=16711680>[250M]</col> <shad=0>From your tax bag.");
							c.antiDupe = 0;
					} else if (c.antiDupe == 0) {	
					}
					
					} else if (c.dialogueAction == 367) {
						c.antiDupe = 5;
						if (c.antiDupe == 5) {
							c.getItems().deleteItem(10833, 1);
							c.getItems().addItem(995, 500000000);
							c.getItems().addItem(10831, 1);
							c.sendMessage("<shad=0>You have picked </col><shad=16711680>[500M]</col> <shad=0>From your tax bag.");
							c.antiDupe = 0;
					} else if (c.antiDupe == 0) {
					}
					
					} else if (c.dialogueAction == 368) {
						c.antiDupe = 5;
						if (c.antiDupe == 5) {
							c.getItems().deleteItem(10834, 1);
							c.getItems().addItem(995, 1000000000);
							c.getItems().addItem(10831, 1);
							c.sendMessage("<shad=0>You have picked </col><shad=16711680>[1000M]</col> <shad=0>From your tax bag.");
							c.antiDupe = 0;
					} else if (c.antiDupe == 0) {
					}

				
                     
                
				} else if (c.dialogueAction == 13) {
					c.getPA().spellTeleport(3202, 3859, 0);
					c.dialogueAction = -1;
					//melee Shop
					
				} else if (c.dialogueAction == 18) {
		c.sendMessage("<shad=299910><img=2>You will be starting Dung Now!<img=2>");
		c.sendMessage("<shad=599910><img=1>This Is Not Multi Combat Zone!<img=1>");
		c.getPA().movePlayer(3503, 9495, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 34) {
					c.getPA().removeAllWindows();
					c.dialogueAction = -1;
					}
					

				if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
				} else {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				}
				break;
			case 9159:
				if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3351,3659,0,"modern");
					}
				break;
			case 107243:
				c.setSidebarInterface(4, 1644);
				break;

			case 107215:
				c.setSidebarInterface(11, 904);
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			if(c.playerEquipment[c.playerWeapon] == 13902) {
			c.specBarId = 7486;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			} else {
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			}
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

			case 30108:
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			if(c.playerEquipment[c.playerWeapon] == 15486) {
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(1958);
				c.SolProtect = 120;
				c.startAnimation(10518);
				c.getItems().updateSpecialBar();
			c.usingSpecial = !c.usingSpecial;
			c.sendMessage("All damage will be split into half for 1 minute.");
			c.forcedChat("I am Protected By the Light!");
			c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", 7562);
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}	
			}			
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			
case 153:
	c.startAnimation(5713);
break;
case 152:
	c.startAnimation(5713);
break;
			
			case 9154:
			c.logout();
			break;

			case 82016:
   			c.takeAsNote = !c.takeAsNote;
    			break;
			
					

			//home teleports
			case 117048:
			case 4171:
			case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(2399, 4444, 0, type);	
			break;
			
			//case 4171:
			/*case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(3086, 3493, 0, type);	
			break;*/
			
			/*case 50235:
			case 4140:
			case 117112:
			c.setSidebarInterface(6, 45300);
			break;*/

			
			case 4143:
			case 50245:
			case 117123:
			c.setSidebarInterface(6, 45200);
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.getPA().showInterface(34100);
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.setSidebarInterface(6, 45600);
			break;
			
			case 50235:
			case 4140:
			case 117112:
			c.getPA().showInterface(34000);
			
			//c.getDH().sendOption5("Rock Crabs", "Taverly Dungeon", "Slayer Tower", "Brimhaven Dungeon", "-More Options-");

			//c.teleAction = 1;
			break;
			/*
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Pest Control", "Zombie Minigame (NEW)", "Duel Arena", "Warrior Guild");
			c.teleAction = 2;
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.getDH().sendOption5("Godwars", "King Black Dragon (Wild)", "Dagannoth Kings", "Tormented Demons", "Corporeal Beast");
			c.teleAction = 3;
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Mage Bank", "Varrock PK", "Lava Crossing (Multi)", "Edgeville", "Green Dragons");
			c.teleAction = 4;
			break;			
			
			*/case 51013:
			case 6004:	
			case 117162:	
			c.getPA().startTeleport(2852, 3432, 0, "modern");
			//c.getDH().sendOption5("Mining", "Smithing", "Fishing/Cooking", "Woodcutting", "Farming");
			//c.teleAction = 5;
			break;
			
			
			case 117186:	
			c.dialogueId = 190;
			c.getDH().sendDialogues(c.dialogueId, 0);
			c.teleAction = 8;
			break; 
			
			
			case 51023:
			case 6005:
			c.getDH().sendOption5("Varrock", "Falador", "Lumbridge", "Canifis", "Camelot");
			c.teleAction = 20;
			break; 
			
			
			case 51031:
			case 29031:
			c.getDH().sendOption5("Agility", "Hunter", "Mining", "Fishing Guild", "Thieving");
			c.teleAction = 21;
			break; 		

			case 72038:
			case 51039:
			c.sendMessage("Comming Soon");
			break; 
			
      			case 9125: //Accurate
			case 6221: // range accurate
			case 22230: //kick (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)

			case 30088: //claws
			case 1177: //hammer
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22228: //punch (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			case 1175: //accurate (darts)
			case 30089: //stab (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22229: //block (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 30091: //claws
			case 1176: //stat hammer
			case 8236: //slash (dagger)

			case 30090: //claws
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

			 /**Prayers**/  
            case 87231: // thick skin
            c.getCurse().activateCurse(0);
            return;
            case 87233: // burst of str
            c.getCurse().activateCurse(1);
            break;  
            case 87235: // charity of thought
            c.getCurse().activateCurse(2);
            break;  
            case 87237: // range
            c.getCurse().activateCurse(3);
            break;
            case 87239: // mage
            c.getCurse().activateCurse(4);
            break;
            case 87241: // rockskin
            c.getCurse().activateCurse(5);
            break;
            case 87243: // super human
            c.getCurse().activateCurse(6);
            break;
            case 87245: //defmage
                        if(c.curseActive[7]) {
                        c.curseActive[7] = false;
                        c.getPA().sendFrame36(88, 0);
                        c.headIcon = -1;
                        c.getPA().requestUpdates();
                        } else {
                        c.getCurse().activateCurse(7);
                        c.getPA().sendFrame36(90, 0); //defmellee
                        c.getPA().sendFrame36(89, 0);//defrang
                        c.getPA().sendFrame36(97, 0);//soulsplit
                        c.getPA().sendFrame36(96, 0);//warth
                        c.getPA().sendFrame36(88, 1);//deflmag
                        }
            break;
            case 87247: //defrng
                        if(c.curseActive[8]) {
                        c.getPA().sendFrame36(89, 0);
                        c.curseActive[8] = false;
                        c.headIcon = -1;
                        c.getPA().requestUpdates();
                        } else {
                        c.getCurse().activateCurse(8);
                        c.getPA().sendFrame36(90, 0); //defmellee
                        c.getPA().sendFrame36(89, 1);//defrang
                        c.getPA().sendFrame36(88, 0);//deflmag
                        c.getPA().sendFrame36(97, 0);//soulsplit
                        c.getPA().sendFrame36(96, 0);//warth
                        }
            break;
            case 87249://defmel
                        if(c.curseActive[9]) {
                        c.getPA().sendFrame36(90, 0);
                        c.curseActive[9] = false;
                        c.headIcon = -1;
                        c.getPA().requestUpdates();
                        } else {
                        c.getCurse().activateCurse(9);
                        c.getPA().sendFrame36(90, 1); //defmellee
                        c.getPA().sendFrame36(89, 0);//defrang
                        c.getPA().sendFrame36(88, 0);//deflmag
                        c.getPA().sendFrame36(97, 0);//soulsplit
                        c.getPA().sendFrame36(96, 0);//warth
                        }
            break;
             
            case 87251: // leeech attack
                if(c.curseActive[10]) {
                        c.getPA().sendFrame36(91, 0); //str
                        c.curseActive[10] = false;
                        } else {
                        c.getCurse().activateCurse(10);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(91, 1); //attack leech
                        c.getPA().sendFrame36(105, 0);// turmoil
                        }
            break;          
            case 87253: // leech range
                        if(c.curseActive[11]) {
                        c.getPA().sendFrame36(103, 0); //str
                        c.curseActive[11] = false;
                        } else {
                        c.getCurse().activateCurse(11);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(105, 0);// turmoil
                        c.getPA().sendFrame36(103, 1); //range
                        }
            break;
            case 87255: // leech magic
                        if(c.curseActive[12]) {
                        c.getPA().sendFrame36(104, 0); //str
                        c.curseActive[12] = false;
                        } else {
                        c.getCurse().activateCurse(12);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(105, 0);// turmoil
                        c.getPA().sendFrame36(104, 1); //mage
                        }
            break;  
            case 88001: // leech def
                            if(c.curseActive[13]) {
                        c.getPA().sendFrame36(92, 0); //str
                        c.curseActive[13] = false;
                        } else {
                        c.getCurse().activateCurse(13);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(105, 0);// turmoil
                        c.getPA().sendFrame36(92, 1); //def
                        }
            break;
            case 88003: // leech str
                        if(c.curseActive[14]) {
                        c.getPA().sendFrame36(93, 0); //str
                        c.curseActive[14] = false;
                        } else {
                        c.getCurse().activateCurse(14);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(105, 0);// turmoil
                        c.getPA().sendFrame36(93, 1);  //str
                        }
            break;
/*          .getCurse().activateCurse(15);
            c.sendMessage("Doesn't work yet");
            return; */
            case 88007: // protect from magic
                            if(c.curseActive[16]) {
                        c.getPA().sendFrame36(95, 0); //str
                        c.curseActive[16] = false;
                        } else {
                        c.getCurse().activateCurse(16);
                        c.curseActive[19] = false;
                        c.getPA().sendFrame36(105, 0);// turmoil
                        c.getPA().sendFrame36(95, 1); //def
                        }
            return;         
            case 88009: // protect from range
                        if(c.curseActive[17]) {
                        c.getPA().sendFrame36(96, 0);
                        c.curseActive[17] = false;
                        c.headIcon = -1;
                        c.getPA().requestUpdates();
                        } else {
                        c.getCurse().activateCurse(17);
                        c.getPA().sendFrame36(90, 0); //defmellee
                        c.getPA().sendFrame36(89, 0);//defrang
                        c.getPA().sendFrame36(88, 0);//deflmag
                        c.getPA().sendFrame36(97, 0);//soulsplit
                        c.getPA().sendFrame36(96, 1);//warth
                        }
            break;
            case 88011: // protect from melee
                        if(c.curseActive[18]) {
                        c.getPA().sendFrame36(97, 0);
                        c.curseActive[18] = false;
                        c.headIcon = -1;
                        c.getPA().requestUpdates();
                        } else {
                        c.getCurse().activateCurse(18);
                        c.getPA().sendFrame36(90, 0); //defmellee
                        c.getPA().sendFrame36(89, 0);//defrang
                        c.getPA().sendFrame36(88, 0);//deflmag
                        c.getPA().sendFrame36(97, 1);//soulsplit
                        c.getPA().sendFrame36(96, 0);//warth
                        }
            break;
            case 88013: // 44 range
                        if(c.curseActive[19]) {
                        c.getPA().sendFrame36(105, 0); //str
                        c.curseActive[19] = false;
                        } else {
                        c.getCurse().activateCurse(19);
                        c.curseActive[10] = false;
                        c.curseActive[11] = false;
                        c.curseActive[12] = false;
                        c.curseActive[13] = false;
                        c.curseActive[14] = false;
                        c.getPA().sendFrame36(91, 0); //attack leech
                        c.getPA().sendFrame36(105, 1);// turmoil
                        c.getPA().sendFrame36(93, 0);  //str
                        c.getPA().sendFrame36(92, 0); //def
                        c.getPA().sendFrame36(104, 0); //mage
                        c.getPA().sendFrame36(103, 0); //range
                        c.getPA().sendFrame36(95, 0);//spec
                        c.getPA().sendFrame36(96, 0);//run
                        }
            break;          
            /**End of curse prayers**/
			
			
			/**Prayers**/
			case 97168: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 97170: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 97172: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 97174: // range
			c.getCombat().activatePrayer(3);
			break;
			case 97176: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 97178: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 97180: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 97182:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 97184: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 97186:
			c.getCombat().activatePrayer(9);
			break;
			case 97188: // protect Item
			/*if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}*/
			c.getCombat().activatePrayer(10);
			break;			
			case 97190: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 97192: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 97194: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 97196: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 97198: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 97200: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 97202: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 97204: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 97206: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 97208: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 97210: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 97212: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 97214: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 97216: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 97218: // piety
			c.getCombat().activatePrayer(25);
			break;

					
			case 13092:
                        if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}
			
		
			break;
					
			case 13218:
                         if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						c.sendMessage("Trade accepted.");
						c.SaveGame();
						ot1.SaveGame();
						ot1.sendMessage("Trade accepted.");
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;			
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 67100://Accept
					c.getPA().showInterface(3559);
					c.newPlayer = false;
					c.sendMessage("You need to click on you agree before you can continue on.");
				break;
			case 67103://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 100231:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 100237:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
				
			/*case 85252: //The Action button ID
    c.getPA().bankAll();
    break;	*/
				
			case 168:
                c.startAnimation(855);		c.stopMovement();
            break;
            case 169:
                c.startAnimation(856);		c.stopMovement();
            break;
            case 162:
                c.startAnimation(857);		c.stopMovement();
            break;
            case 164:
                c.startAnimation(858);		c.stopMovement();
            break;
            case 165:
                c.startAnimation(859);		c.stopMovement();
            break;
            case 161:
                c.startAnimation(860);		c.stopMovement();
            break;
            case 170:
                c.startAnimation(861);		c.stopMovement();
            break;
            case 171:
                c.startAnimation(862);		c.stopMovement();
            break;
            case 163:
                c.startAnimation(863);		c.stopMovement();
            break;
            case 167:
                c.startAnimation(864);		c.stopMovement();
            break;
            case 172:
                c.startAnimation(865);		c.stopMovement();
            break;
            case 166:
                c.startAnimation(866);		c.stopMovement();
            break;
            case 52050:
                c.startAnimation(2105);		c.stopMovement();
            break;
            case 52051:
                c.startAnimation(2106);		c.stopMovement();
            break;
            case 52052:
                c.startAnimation(2107);		c.stopMovement();
            break;
            case 52053:
                c.startAnimation(2108);		c.stopMovement();
            break;
            case 52054:
                c.startAnimation(2109);		c.stopMovement();
            break;
            case 52055:
                c.startAnimation(2110);		c.stopMovement();
            break;
            case 52056:
                c.startAnimation(2111);		c.stopMovement();
            break;
            case 52057:
                c.startAnimation(2112);		c.stopMovement();
            break;
            case 52058:
                c.startAnimation(2113);		c.stopMovement();
            break;
            case 43092:
                c.startAnimation(0x558);		c.stopMovement();
				c.gfx0(574);
            break;
            case 2155:
                c.startAnimation(11044);		c.stopMovement();
				c.gfx0(1973);
            break;
            case 25103:
                c.startAnimation(10530);		c.stopMovement();
				c.gfx0(1864);
            break;
            case 25106:
				c.startAnimation(8770);
				c.gfx0(1553);		c.stopMovement();
            break;
            case 2154:
                c.startAnimation(7531);		c.stopMovement();
            break;
            case 52071:
                c.startAnimation(0x84F);		c.stopMovement();
            break;
            case 52072:
                c.startAnimation(0x850);		c.stopMovement();
            break;
            case 73003:
		c.startAnimation(6111);	c.stopMovement();
            break;
            case 73001:
                c.startAnimation(3544);		c.stopMovement();
            break;
            case 73000:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");
return;
}
                c.startAnimation(3543);		c.stopMovement();
            break;
			case 72032:
				c.startAnimation(9990);		c.stopMovement();
				c.gfx0(1734);
            break;
			case 72033:
				c.startAnimation(4278);		c.stopMovement();
            break;
			case 59062:
				c.startAnimation(4280);		c.stopMovement();
            break;
			case 72254:
				c.startAnimation(4275);		c.stopMovement();
            break;
			case 73004:
				c.startAnimation(7272);		c.stopMovement();
				c.gfx0(1244);
            break;
			case 72255:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");		c.stopMovement();
return;
}
				c.startAnimation(2414);
				c.gfx0(1537);
			break;
			/* END OF EMOTES */
			case 28166:
				
				break;
case 118098:
c.getPA().castVeng();
break; 
			
		case 121210:			
			c.forcedText = "[QC] My Slayer level is  " + c.getPA().getLevelForXP(c.playerXP[18]) + ".";
			c.sendMessage("I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask));
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
			
	case 121170:
		c.forcedText = "[QC] My Hunter level is  " + c.getPA().getLevelForXP(c.playerXP[21]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121135:
		c.forcedText = "[QC] My Attack level is  " + c.getPA().getLevelForXP(c.playerXP[0]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121140:
		c.forcedText = "[QC] My Strength level is  " + c.getPA().getLevelForXP(c.playerXP[2]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121145:
		c.forcedText = "[QC] My Defence level is  " + c.getPA().getLevelForXP(c.playerXP[1]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121180:
		c.forcedText = "[QC] My Constitution level is  " + c.getPA().getLevelForXP(c.playerXP[3]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121150:
		c.forcedText = "[QC] My Range level is  " + c.getPA().getLevelForXP(c.playerXP[4]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121155:
		c.forcedText = "[QC] My Prayer level is  " + c.getPA().getLevelForXP(c.playerXP[5]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121160:
		c.forcedText = "[QC] My Magic level is  " + c.getPA().getLevelForXP(c.playerXP[6]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121240:
		c.forcedText = "[QC] My Cooking level is  " + c.getPA().getLevelForXP(c.playerXP[7]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121250:
		c.forcedText = "[QC] My Woodcutting level is  " + c.getPA().getLevelForXP(c.playerXP[8]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121205:
		c.forcedText = "[QC] My Fletching level is  " + c.getPA().getLevelForXP(c.playerXP[9]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121235:
		c.forcedText = "[QC] My Fishing level is  " + c.getPA().getLevelForXP(c.playerXP[10]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121245:
		c.forcedText = "[QC] My Firemaking level is  " + c.getPA().getLevelForXP(c.playerXP[11]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121200:
		c.forcedText = "[QC] My Crafting level is  " + c.getPA().getLevelForXP(c.playerXP[12]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121230:
		c.forcedText = "[QC] My Smithing level is  " + c.getPA().getLevelForXP(c.playerXP[13]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121225:
		c.forcedText = "[QC] My Mining level is  " + c.getPA().getLevelForXP(c.playerXP[14]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121190:
		c.forcedText = "[QC] My Herblore level is  " + c.getPA().getLevelForXP(c.playerXP[15]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121185:
		c.forcedText = "[QC] My Agility level is  " + c.getPA().getLevelForXP(c.playerXP[16]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121195:
		c.forcedText = "[QC] My Thieving level is  " + c.getPA().getLevelForXP(c.playerXP[17]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121255:
		c.forcedText = "[QC] My Farming level is  " + c.getPA().getLevelForXP(c.playerXP[19]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121165:
		c.forcedText = "[QC] My Runecrafting level is  " + c.getPA().getLevelForXP(c.playerXP[20]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121215:
		c.forcedText = "[QC] My Summoning level is  " + c.getPA().getLevelForXP(c.playerXP[22]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 122004:
		c.forcedText = "[QC] My PK'ing level is  " + c.getPA().getLevelForXP(c.playerXP[23]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	break;
	case 121175:
	if(c.playerXP[24] >= 0 && c.playerXP[24] <= 14391160) {
		c.forcedText = "[QC] My Dungeoneering level is  " + c.getPA().getLevelForXP(c.playerXP[24]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
} else if(c.playerXP[24] >= 14391161 && c.playerXP[24] <= 15889108) {
    c.forcedText = "[QC] My Dungeoneering level is 100.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
} else if(c.playerXP[24] >= 15889109 && c.playerXP[24] <= 17542976) {
     c.forcedText = "[QC] My Dungeoneering level is 101.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 17542977 && c.playerXP[24] <= 19368991) {
     c.forcedText = "[QC] My Dungeoneering level is 102.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 19368992 && c.playerXP[24] <= 21385072) {
    c.forcedText = "[QC] My Dungeoneering level is 103.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 21385073 && c.playerXP[24] <= 23611005) {
     c.forcedText = "[QC] My Dungeoneering level is 104.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 23611006 && c.playerXP[24] <= 26068631) {
     c.forcedText = "[QC] My Dungeoneering level is 105.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 26068632 && c.playerXP[24] <= 28782068) {
     c.forcedText = "[QC] My Dungeoneering level is 106.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 28782069 && c.playerXP[24] <= 31777942) {
    c.forcedText = "[QC] My Dungeoneering level is 107.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 31777943 && c.playerXP[24] <= 35085653) {
     c.forcedText = "[QC] My Dungeoneering level is 108.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 35085654 && c.playerXP[24] <= 38737660) {
     c.forcedText = "[QC] My Dungeoneering level is 109.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 38737661 && c.playerXP[24] <= 42769799) {
   c.forcedText = "[QC] My Dungeoneering level is 110.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 42769800 && c.playerXP[24] <= 47221639) {
   c.forcedText = "[QC] My Dungeoneering level is 111.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 47221640 && c.playerXP[24] <= 52136868) {
     c.forcedText = "[QC] My Dungeoneering level is 112.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 52136869 && c.playerXP[24] <= 57563717) {
     c.forcedText = "[QC] My Dungeoneering level is 113.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 57563718 && c.playerXP[24] <= 63555442) {
     c.forcedText = "[QC] My Dungeoneering level is 114.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 63555443 && c.playerXP[24] <= 70170839) {
    c.forcedText = "[QC] My Dungeoneering level is 115.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 70170840 && c.playerXP[24] <= 77474827) {
     c.forcedText = "[QC] My Dungeoneering level is 116.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 77474828 && c.playerXP[24] <= 85539081) {
    c.forcedText = "[QC] My Dungeoneering level is 117.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 85539082 && c.playerXP[24] <= 94442735) {
     c.forcedText = "[QC] My Dungeoneering level is 118.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 94442736 && c.playerXP[24] <= 104273166) {
     c.forcedText = "[QC] My Dungeoneering level is 119.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else if(c.playerXP[24] >= 104273167 && c.playerXP[24] <= 200000000) {
     c.forcedText = "[QC] My Dungeoneering level is 120.";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
 } else c.forcedText = "[QC] Dungeoneering level is  " + c.getPA().getLevelForXP(c.playerXP[24]) + "."; {
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
		}
	break;
	
	case 77036:
if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.sendMessage("Your BoB items have drop on the floor");
} else {
c.sendMessage("You do not have a npc currently spawned");
}
/*
		 * Dungeoneering Start.
 		 *
		 */
		case 70132:
		if (c.dungPoints >= 1000) {
		c.dungPoints -= 1000;
		c.sendMessage("You buy a Ring of Vigour!");
		c.getItems().addItem(19669, 1); 
		} else {
		c.sendMessage("You don't have enough Dungeoneering points!");
		}
		break;
		case 70133:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18359, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70148:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18363, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70147:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18361, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70138:
		if(c.dungPoints >= 50) {
		c.dungPoints -= 50;
		c.getItems().addItem(4447, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70134:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13354, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70135:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13352, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70136:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13346, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70137:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13348, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70139:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13350, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70140:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13355, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70144:
		if(c.dungPoints >= 1200) {
		c.dungPoints -= 1200;
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70143:
		if(c.dungPoints >= 1000) {
		c.dungPoints -= 1000;
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70142:
		if(c.dungPoints >= 900) {
		c.dungPoints -= 900;
		c.getItems().addItem(4753, 1);
		c.getItems().addItem(4755, 1);
		c.getItems().addItem(4757, 1);
		c.getItems().addItem(4759, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70145:
		if(c.dungPoints >= 800) {
		c.dungPoints -= 800;
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70141:
		if(c.dungPoints >= 900) {
		c.dungPoints -= 900;
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 66156:
		if(c.playerLevel[6] <= 9) {
		c.sendMessage("You must be 10+ Magic To Choose Magic Class");
		} else {
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(15786, 1);
		c.getItems().addItem(15797, 1);
		c.getItems().addItem(15837, 1);
		c.getItems().addItem(15892, 1);
		c.getItems().addItem(16185, 1);
		c.getItems().addItem(16153, 1);
		c.getItems().addItem(391, 3);
		c.getItems().addItem(995, 2000000);
		c.getItems().addItem(554, 50000);
		c.getItems().addItem(555, 50000);
		c.getItems().addItem(556, 50000);
		c.getItems().addItem(557, 50000);
		c.getItems().addItem(558, 50000);
		c.getItems().addItem(559, 50000);
		c.getItems().addItem(560, 50000);
		c.getItems().addItem(561, 50000);
		c.getItems().addItem(562, 50000);
		c.getItems().addItem(563, 50000);
		c.getItems().addItem(565, 50000);
		c.getItems().addItem(564, 50000);
		c.getItems().addItem(566, 50000);
		c.playerMagicBook = 1;
		c.setSidebarInterface(6, 12855);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Mage equipment and 2M.");
		}
	}
		break;
		case 66157:
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		                                c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(15808, 1);
		c.getItems().addItem(15914, 1);
		c.getItems().addItem(15925, 1);
		c.getItems().addItem(15936, 1);
		c.getItems().addItem(16013, 1);
		c.getItems().addItem(16035, 1);
		c.getItems().addItem(16127, 1);
		c.getItems().addItem(16262, 1);
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(391, 3);
		c.getItems().addItem(995, 2000000);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Melee equipment and 2M.");
		}
	
		break;
		case 66158:
		if(c.playerLevel[4] <= 9) {
		c.sendMessage("You must be 10+ Ranged To Choose Ranged Class");
		} else {
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		                                c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(16002, 1);
		c.getItems().addItem(16046, 1);
		c.getItems().addItem(16057, 1);
		c.getItems().addItem(16068, 1);
		c.getItems().addItem(16105, 1);
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(892, 10000);
		c.getItems().addItem(397, 3);
		c.getItems().addItem(995, 2000000);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Ranged equipment and 2M.");
		}
	}
		break;
		//Dungeoneering finish
		case 177190:
		c.getPA().showInterface(14040);
		break;
		case 177206:
		c.getPA().spellTeleport(3007, 3849, 0);
		break;
		case 177209:
		c.getPA().spellTeleport(1910, 4367, 0);
		break;
		case 177212:
		c.getPA().spellTeleport(2717, 9805, 0);
		break;
		case 177221:
		c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
		break;
		case 176177:
		c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
		break;
		case 178065:
		c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
		break;
		case 178034:
		c.getPA().spellTeleport(2539, 4716, 0);
		break;
		case 178050:
		c.getPA().spellTeleport(3243, 3517, 0);
		break;
		case 178053:
		c.getPA().spellTeleport(3367, 3935, 0);
		break;
		case 178056:
		c.getPA().spellTeleport(3086, 3516, 0);
		break;
		case 178059:
		c.getPA().spellTeleport(3344, 3667, 0);
		break;
		case 176162:
		c.getPA().spellTeleport(3565, 3314, 0);
		break;
		case 176168:
		c.getPA().spellTeleport(2438, 5172, 0);
		break;
		case 176146:
		c.getPA().spellTeleport(3366, 3266, 0);
		c.sendMessage("Toggle snow on/off with ::snowon or ::snowoff");
		break;
		case 176165:
		c.getPA().spellTeleport(2662, 2650, 0);
		break;		
		case 176171:
		c.getPA().spellTeleport(2865, 3546, 0);
		break;
		case 176246:
		c.getPA().spellTeleport(2676, 3715, 0);
		break;
		case 177006:
		c.getPA().spellTeleport(2884, 9798, 0);
		break;
		case 177009:
		c.getPA().spellTeleport(2710, 9466, 0);
		break;
		case 177012:
		c.getPA().spellTeleport(3428, 3527, 0);
		break;
		case 177015:
		c.getPA().spellTeleport(3117, 9847, 0);
		break;
		case 177021:
		c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
		break;
		case 177215:
		c.getPA().spellTeleport(3303, 9375, 0);
		break;

case 69009:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
			} else if(c.playerMagicBook == 1){
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
