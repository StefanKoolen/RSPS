package server.model.players;

import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;

/**
 * @author InsidiaX
 */

public class Potions {

	private Client c;
	
	public Potions(Client c) {
		this.c = c;
	}
	
	public void handlePotion(int itemId, int slot) {
		if (c.duelRule[5]) {
			c.sendMessage("You may not drink potions in this duel.");
			return;
		}
		if (c.InDung()) {
					c.sendMessage("You cant do that in Dungeoneering.");
					return;
				}
		if (System.currentTimeMillis() - c.potDelay >= 1500) {
			c.potDelay = System.currentTimeMillis();
			c.foodDelay = c.potDelay;
			c.getCombat().resetPlayerAttack();
			c.attackTimer++;
                c.sendMessage("You drink some of your "+ server.model.items.Item.getItemName(itemId) +".");
		String item =  server.model.items.Item.getItemName(itemId);
		if(item.endsWith("(4)"))
			{
				c.sendMessage("You have 3 doses of potion left.");
			} else if(item.endsWith("(3)"))
			{
				c.sendMessage("You have 2 doses of potion left.");
			} else if(item.endsWith("(2)"))
			{
				c.sendMessage("You have 1 dose of potion left.");
			} else if(item.endsWith("(1)"))
			{
				c.sendMessage("You have finished your potion.");
			}
			switch (itemId) {
                                case 15332:
				doOverload(itemId, 15333, slot);
				break;
				case 15333:
				doOverload(itemId, 15334, slot);
				break;
				case 15334:
				doOverload(itemId, 15335, slot);
				break;
				case 15335:
				doOverload(itemId, 229, slot);
				break;
				case 6685:	//brews
				doTheBrew(itemId, 6687, slot);
				break;
				case 15272:	//brews
				Rocktail(itemId, 15272, slot);
				break;
				case 3040:
				drinkStatPotion(itemId,3042,slot,6,false);
				break;
				case 3042:
				drinkStatPotion(itemId,3044,slot,6,false);
				break;
				case 3044:
				drinkStatPotion(itemId,3046,slot,6,false);
				break;
				case 3046:
				drinkStatPotion(itemId,229,slot,6,false);
                                break;
				case 6687:
				doTheBrew(itemId, 6689, slot);
				break;
				case 6689:
				doTheBrew(itemId, 6691, slot);
				break;
				case 6691:
				doTheBrew(itemId, 229, slot);
				break;
				case 2436:
				drinkStatPotion(itemId,145,slot,0,true); //sup attack
				break;
				case 145:
				drinkStatPotion(itemId,147,slot,0,true);
				break;
				case 147:
				drinkStatPotion(itemId,149,slot,0,true);
				break;
				case 149:
				drinkStatPotion(itemId,229,slot,0,true);
				break;
				case 2440:
				drinkStatPotion(itemId,157,slot,2,true); //sup str
				break;
				case 157:
				drinkStatPotion(itemId,159,slot,2,true);
				break;
				case 159:
				drinkStatPotion(itemId,161,slot,2,true);
				break;
				case 161:
				drinkStatPotion(itemId,229,slot,2,true);
				break;
				case 2444:
				drinkStatPotion(itemId,169,slot,4,false); //range pot
				break;
				case 169:
				drinkStatPotion(itemId,171,slot,4,false);
				break;
				case 171:
				drinkStatPotion(itemId,173,slot,4,false);
				break;
				case 173:
				drinkStatPotion(itemId,229,slot,4,false);
				break;
				case 2432:
				drinkStatPotion(itemId,133,slot,1,false); //def pot
				break;
				case 133:
				drinkStatPotion(itemId,135,slot,1,false);
				break;
				case 135:
				drinkStatPotion(itemId,137,slot,1,false);
				break;
				case 137:
				drinkStatPotion(itemId,229,slot,1,false);
				break;
				case 113:
				drinkStatPotion(itemId,115,slot,2,false); //str pot
				break;
				case 115:
				drinkStatPotion(itemId,117,slot,2,false);
				break;
				case 117:
				drinkStatPotion(itemId,119,slot,2,false);
				break;
				case 119:
				drinkStatPotion(itemId,229,slot,2,false);
				break;
				case 2428:
				drinkStatPotion(itemId,121,slot,0,false); //attack pot
				break;
				case 121:
				drinkStatPotion(itemId,123,slot,0,false);
				break;
				case 123:
				drinkStatPotion(itemId,125,slot,0,false);
				break;
				case 125:
				drinkStatPotion(itemId,229,slot,0,false);
				break;
				case 2442:
				drinkStatPotion(itemId,163,slot,1,true); //super def pot
				break;
				case 163:
				drinkStatPotion(itemId,165,slot,1,true);
				break;
				case 165:
				drinkStatPotion(itemId,167,slot,1,true);
				break;
				case 167:
				drinkStatPotion(itemId,229,slot,1,true);
				break;
				case 3024:
				drinkPrayerPot(itemId,3026,slot,true); //sup restore
				break;
				case 3026:
				drinkPrayerPot(itemId,3028,slot,true);
				break;
				case 3028:
				drinkPrayerPot(itemId,3030,slot,true);
				break;
				case 3030:
				drinkPrayerPot(itemId,229,slot,true);
				break;
				case 10925:
				drinkPrayerPot(itemId,10927,slot,true); //sanfew serums
				curePoison(300000);
				break;
				case 10927:
				drinkPrayerPot(itemId,10929,slot,true);
				curePoison(300000);
				break;
				case 10929:
				drinkPrayerPot(itemId,10931,slot,true);
				curePoison(300000);
				break;
				case 10931:
				drinkPrayerPot(itemId,229,slot,true);
				curePoison(300000);
				break;
				case 2434:
				drinkPrayerPot(itemId,139,slot,false); //pray pot
				break;
				case 15300: //Recover Special
				recoverSpecial(itemId, 15301, slot);
				break;
				case 15301: //Recover Special
				recoverSpecial(itemId, 15302, slot);
				break;
				case 15302: //Recover Special
				recoverSpecial(itemId, 15303, slot);
				break;
				case 15303: //Recover Special
				recoverSpecial(itemId, 229, slot);
				break;
				case 15312: //Extreme Strength
				drinkExtremePotion(itemId, 15313, slot, 2, false);
				break;
				case 15313: //Extreme Strength
				drinkExtremePotion(itemId, 15314, slot, 2, false);
				break;
				case 15314: //Extreme Strength
				drinkExtremePotion(itemId, 15315, slot, 2, false);
				break;
				case 15315: //Extreme Strength
				drinkExtremePotion(itemId, 229, slot, 2, false);
				break;
				case 15308: //Extreme Attack
				drinkExtremePotion(itemId, 15309, slot, 0, false);
				break;
				case 15309: //Extreme Attack
				drinkExtremePotion(itemId, 15310, slot, 0, false);
				break;
				case 15310: //Extreme Attack
				drinkExtremePotion(itemId, 15311, slot, 0, false);
				break;
				case 15311: //Extreme Attack
				drinkExtremePotion(itemId, 229, slot, 0, false);
				break;
				case 15316: //Extreme Defence
				drinkExtremePotion(itemId, 15317, slot, 1, false);
				break;
				case 15317: //Extreme Defence
				drinkExtremePotion(itemId, 15318, slot, 1, false);
				break;
				case 15318: //Extreme Defence
				drinkExtremePotion(itemId, 15319, slot, 1, false);
				break;
				case 15319: //Extreme Defence
				drinkExtremePotion(itemId, 229, slot, 1, false);
				break;
				case 15324: //Extreme Ranging
				drinkExtremePotion(itemId, 15325, slot, 4, false);
				break;
				case 15325: //Extreme Ranging
				drinkExtremePotion(itemId, 15326, slot, 4, false);
				break;
				case 15326: //Extreme Ranging
				drinkExtremePotion(itemId, 15327, slot, 4, false);
				break;
				case 15327: //Extreme Ranging
				drinkExtremePotion(itemId, 229, slot, 4, false);
				break;
				case 15320: //Extreme Magic
				drinkExtremePotion(itemId, 15321, slot, 6, false);
				break;
				case 15321: //Extreme Magic
				drinkExtremePotion(itemId, 15322, slot, 6, false);
				break;
				case 15322: //Extreme Magic
				drinkExtremePotion(itemId, 15323, slot, 6, false);
				break;
				case 15323: //Extreme Magic
				drinkExtremePotion(itemId, 229, slot, 6, false);
				break;
				case 15328: //Super Prayer
				drinkExtremePrayer(itemId, 15329, slot, true);
				break;
				case 15329: //Super Prayer
				drinkExtremePrayer(itemId, 15330, slot, true);
				break;
				case 15330: //Super Prayer
				drinkExtremePrayer(itemId, 15331, slot, true);
				break;
				case 15331: //Super Prayer
				drinkExtremePrayer(itemId, 229, slot, true);
				break;
				case 139:
				drinkPrayerPot(itemId,141,slot,false);
				break;
				case 141:
				drinkPrayerPot(itemId,143,slot,false);
				break;
				case 143:
				drinkPrayerPot(itemId,229,slot,false);
				break;
				case 2446:
				drinkAntiPoison(itemId,175,slot,30000); //anti poisons
				break;
				case 175:
				drinkAntiPoison(itemId,177,slot,30000);
				break;
				case 177:
				drinkAntiPoison(itemId,179,slot,30000);
				break;
				case 179:
				drinkAntiPoison(itemId,229,slot,30000);
				break;
				case 2448:
				drinkAntiPoison(itemId,181,slot,300000); //anti poisons
				break;
				case 181:
				drinkAntiPoison(itemId,183,slot,300000);
				break;
				case 183:
				drinkAntiPoison(itemId,185,slot,300000);
				break;
				case 185:
				drinkAntiPoison(itemId,229,slot,300000);
				break;
				
				case 2438:
				doOverload(itemId, 151, slot);
				break;
				case 151:
				doOverload(itemId, 153, slot);
				break;
				case 153:
				doOverload(itemId, 155, slot);
				break;
				case 155:
				doOverload(itemId, 229, slot);
			}
		}	
	}
	
	
public void doOverload(int itemId, int replaceItem, int slot) {
		int health = c.playerLevel[3];
		int herbLevel = c.playerLevel[15];
		if (health < 50) {
			c.sendMessage("I should get some more lifepoints before using this!");
			return;
		}
		if (herbLevel < 96) {
			c.sendMessage("You need 96 herblore to drink an overload");
			return;
		}
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.hasOverloadBoost = true;
		doOverloadBoost();
		handleOverloadTimers();
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
	}
	
	public void handleOverloadTimers() {
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer b) {
				if (c == null)
					b.stop();
				c.hasOverloadBoost = false;
			}
			public void stop() { }
		}, 300000); //5 minutes
		EventManager.getSingleton().addEvent(new Event() {
			
			public void execute(EventContainer b) {
				if (c != null){
					if (c.hasOverloadBoost)
						doOverloadBoost();
						
					else {
						b.stop();
						
						c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
						int[] toNormalise = {0,1,2,4,6};
						for (int i = 0; i < toNormalise.length; i++) {
							c.playerLevel[toNormalise[i]] = c.getLevelForXP(c.playerXP[toNormalise[i]]);
						}
					}
				} else
					b.stop();
					c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
				
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);

			}
			public void stop() {}
		}, 15000); //15 seconds
		EventManager.getSingleton().addEvent(new Event() {
			int counter2 = 0;
			@Override
			public void execute(EventContainer b) {
				if (c == null)
					b.stop();
				if (counter2 < 5) {
					c.startAnimation(2383);
					c.dealDamage(10);
					c.handleHitMask(10); 
	
					c.getPA().refreshSkill(3);
					counter2++;
				} else
					b.stop();
					c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			}

			
			public void stop() {}
		}, 600); //1 tick (600ms)
	}

	public void doOverloadBoost() {
		int[] toIncrease = {0,1,2,4,6};
		int boost;
		for (int i = 0; i < toIncrease.length; i++) {
			boost = (int)(getOverloadBoost(toIncrease[i]));
			c.playerLevel[toIncrease[i]] += boost;
			if (c.playerLevel[toIncrease[i]] > (c.getLevelForXP(c.playerXP[toIncrease[i]]) + boost))
				c.playerLevel[toIncrease[i]] = (c.getLevelForXP(c.playerXP[toIncrease[i]]) + boost);
			c.getPA().refreshSkill(toIncrease[i]);
		}
	}

	public double getOverloadBoost(int skill) {
		double boost = 1;
		switch(skill) {
		case 0:
		case 1:
		case 2:
			boost = 5+ (c.getLevelForXP(c.playerXP[skill]) * .22);
			break;
		case 4:
			boost = 3+ (c.getLevelForXP(c.playerXP[skill]) * .22);
			break;
		case 6:
			boost = 7;
			break;
		}
		return boost;
	}
	
               
	public void drinkAntiPoison(int itemId, int replaceItem, int slot, long delay) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		curePoison(delay);
	}
	
	public void recoverSpecial(int itemId, int replaceItem, int slot) {
		if (c.inWild()) {
			c.sendMessage("You are unable to restore special in the wilderness.");
			return;
		} else if (c.specAmount >= 7.5) {
			c.sendMessage("You are unable to drink the potion as your special is above 75%.");
		} else if (c.specRestore > 0) {
			c.specAmount += 2.5;
			c.startAnimation(829);
			c.sendMessage("As you drink drink the potion, you feel your special attack slightly regenerate.");
			c.playerItems[slot] = replaceItem + 1;
			c.getItems().resetItems(3214);
			c.getItems().updateSpecialBar();
			c.specRestore = 180;
		} else {
			c.sendMessage("You can only restore your special once every 3 minutes.");
			}
		}
	

	public void drinkExtremePotion(int itemId, int replaceItem, int slot, int stat, boolean sup) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceStat2(stat,sup);
	}

	public void drinkExtremePrayer(int itemId, int replaceItem, int slot, boolean rest) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.playerLevel[5] += (c.getLevelForXP(c.playerXP[5]) * .38);
		if (rest)
			c.playerLevel[5] += 1;
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		c.getPA().refreshSkill(5);
		if (rest)
			restoreStats();
	}

	public void enchanceStat2(int skillID, boolean sup) {
		c.playerLevel[skillID] += getExtremeStat(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public int getExtremeStat(int skill, boolean sup) {
		int increaseBy = 0;
		if (sup)
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.25);
		else
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.25) + 1;
		if (c.playerLevel[skill] + increaseBy > c.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy - c.playerLevel[skill];
		}
		return increaseBy;
	}

	public void curePoison(long delay) {
		c.poisonDamage = 0;
		c.poisonImmune = delay;
		c.lastPoisonSip = System.currentTimeMillis();
	}
	
	public void drinkStatPotion(int itemId, int replaceItem, int slot, int stat, boolean sup) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceStat(stat,sup);
	}
	
	public void drinkPrayerPot(int itemId, int replaceItem, int slot, boolean rest) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.playerLevel[5] += (c.getLevelForXP(c.playerXP[5]) * .33);
		if (rest)
			c.playerLevel[5] += 1;
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		c.getPA().refreshSkill(5);
		if (rest)
			restoreStats();
	}
	
	public void restoreStats() {
		for (int j = 0; j <= 6; j++) {
			if (j == 5 || j == 3)
				continue;
			if (c.playerLevel[j] < c.getLevelForXP(c.playerXP[j])) {
				c.playerLevel[j] += (c.getLevelForXP(c.playerXP[j]) * .33);
				if (c.playerLevel[j] > c.getLevelForXP(c.playerXP[j])) {
					c.playerLevel[j] = c.getLevelForXP(c.playerXP[j]);				
				}
				c.getPA().refreshSkill(j);
				c.getPA().setSkillLevel(j, c.playerLevel[j], c.playerXP[j]);
			}			
		}
	}

	public void Rocktail(int itemId, int replaceItem, int slot) {
			c.startAnimation(829);
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.startAnimation(829);
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 24;
				c.playerLevel[3] += getBrewStat(3, .15);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
			}
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3]))) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.09);
		}
		c.getPA().refreshSkill(3);
	}
	
	public void doTheBrew(int itemId, int replaceItem, int slot) {
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		if (c.hasOverloadBoost = true && c.playerLevel[2] > (int)(c.getLevelForXP(c.playerXP[3])*1.24)) {
				double ammount = c.torva() ? 1.22 : 1.17;
				c.startAnimation(829);
				c.playerItems[slot] = replaceItem + 1;
				c.getItems().resetItems(3214);
				c.playerLevel[3] += c.torva() ? getBrewStat(3, .21) : getBrewStat(3, .15);
				if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])* ammount + 1)) {
					c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*ammount);
				}
				
				c.getPA().refreshSkill(3);
				c.sendMessage("Your overload prevents your stats from draining!");
				return;
			} else {
			c.startAnimation(829);
			c.playerItems[slot] = replaceItem + 1;
			c.getItems().resetItems(3214);
			int[] toDecrease = {0,2,4,6};
		
			int[] toIncrease = {1,3};
			for (int tD : toDecrease) {
			c.playerLevel[tD] -= getBrewStat(tD, .10);
			if (c.playerLevel[tD] < 0)
				c.playerLevel[tD] = 1;
				c.getPA().refreshSkill(tD);
				c.getPA().setSkillLevel(tD, c.playerLevel[tD], c.playerXP[tD]);
			}
		c.playerLevel[1] += getBrewStat(1, .20);		
		if (c.playerLevel[1] > (c.getLevelForXP(c.playerXP[1])*1.2 + 1)) {
			c.playerLevel[1] = (int)(c.getLevelForXP(c.playerXP[1])*1.2);
		}
		c.getPA().refreshSkill(1);
		double ammount = c.torva() ? 1.22 : 1.17;
		c.playerLevel[3] += c.torva() ? getBrewStat(3, .21) : getBrewStat(3, .15);
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])* ammount + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*ammount);
		}
		c.getPA().refreshSkill(3);
				
		}
	}
	
	
	
	public void enchanceStat(int skillID, boolean sup) {
		c.playerLevel[skillID] += getBoostedStat(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}
	
	public int getBrewStat(int skill, double amount) {
		return (int)(c.getLevelForXP(c.playerXP[skill]) * amount);
	}
	
	public int getBoostedStat(int skill, boolean sup) {
		int increaseBy = 0;
		if (sup)
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.20);
		else
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.13) + 1;
		if (c.playerLevel[skill] + increaseBy > c.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy - c.playerLevel[skill];
		}
		return increaseBy;
	}
	
	public boolean isPotion(int itemId) {
		String name = c.getItems().getItemName(itemId);
		return name.contains("(4)") || name.contains("(3)") || name.contains("(2)") || name.contains("(1)");	
	}
}
