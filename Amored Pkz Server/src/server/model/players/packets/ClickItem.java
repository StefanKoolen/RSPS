package server.model.players.packets;

import server.util.Misc;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.Server;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	public static int flower[] = {2980,2981,2982,2983,2984,2985,2986,2987};
    public int randomflower() {
                return flower[(int)(Math.random()*flower.length)];
        }
    public static int flowerX = 0;
    public static int flowerY = 0;
    public static int flowerTime = -1;
    public static int flowers = 0;
	
	@Override
    
	
	
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
		}
                /*if(itemId == 8007) {
				if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId == 15098 && System.currentTimeMillis() - c.diceDelay >= 1200) { //Dice Bag ID
				if (c.clanId >= 0);
				c.forcedText = "["+ Misc.optimizeText(c.playerName) +"] Just Rolled "+ Misc.random(100) +" On The Dice!";
				c.startAnimation(11900);
				c.gfx0(2075);
				c.diceDelay = System.currentTimeMillis();
			} else {
			if (c.clanId != -1)
			return;

			}*/

					if (itemId == 2677) { // Clue1
			c.sendMessage("Amongst the death house in stacked up boxes.");
		}

                if(itemId == 8008) {
				if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId > 15085 && itemId < 15102){
			c.useDice(itemId, false);
		}
		
		if (itemId == 15084){
			c.getDH().sendDialogues(106, 4289);
		}
				if(itemId == 15707) {
                   c.getPA().startTeleport(2417, 3526, 0, "dungtele");
				   c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
                }
              if(itemId == 8009) {
			  if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
            if(itemId == 8010) {
			if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8011) {
		  if(!c.InDung);
				c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
				}
          if(itemId == 8012) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8013) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
		if(itemId == 4447) {	
			c.getPA().addSkillXP(3000, 24);
			c.sendMessage("You rub the lamp and are raped by dungeoneering.");
			c.getItems().deleteItem(4447, 1);	
		}
               if (itemId == 1) {
	c.Wheel +=1;
	c.lvlPoints +=20;
c.sendMessage("<shad=3424254>You Recieve 1 Spin and 20 Level Points!");
}
			//AmoredPkz New ones
			
			//1m exp lamp
			
			if(itemId == 11137) {
				c.getPA().showInterface(35000);
				c.antiDupe = 5;
			}
			
			if (itemId == 7509) {
            if (System.currentTimeMillis() - c.foodDelay >= 1800 && c.playerLevel[3] > 0) {
                c.startAnimation(829);
                final int rcakeDamage = 10;
                c.hitDiff2 = rcakeDamage;
                c.setHitUpdateRequired2(true);
                c.dealDamage(rcakeDamage);
                c.getPA().refreshSkill(3);
                c.forcedChat("Ow! I nearly broke a tooth!");
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                                c.foodDelay = System.currentTimeMillis();
            }
        }
			
			//5m exp lamp
			
			if(itemId == 11139) {
				c.getPA().showInterface(35100);
				c.antiDupe = 5;
			}
			
			//Mithril Seeds
			
		if(itemId == 299 && c.seedtimer == 0) {
			flowers = randomflower();
			flowerX += c.absX;
			flowerY += c.absY;
			c.getPA().object(flowers, c.absX, c.absY, 0, 10);
			c.sendMessage("You plant the seed...");
			c.sendMessage("and You Gotta Wait 20 Secs to Plant Again...");
			c.seedtimer += 20;
			c.getItems().deleteItem(299, 1);
			c.getPA().walkTo(-1,0); 
			c.getDH().sendDialogues(9999, -1);   
		} else {
		
		}
			
		/*Tax Bags	100% Anti-Dupe by Dr House

		10831	Empty tax bag
		10832	Light tax bag
		10833	Normal tax bag
		10834	Hefty tax bag
		10835	Bulging taxbag*/
		
		
			
		if(itemId == 10831) {
			c.antiDupe = 5;
			if (c.getItems().playerHasItem(995, 250000000) && c.antiDupe == 5) {
				c.getItems().deleteItem(10831, 1);
				c.getItems().deleteItem(995, 250000000);
				c.getItems().addItem(10832, 1);
				c.antiDupe = 0;
				c.sendMessage("<shad=0>You Have Upgraded Your Tax Bag to </col><shad=16711680>[250M]</col><shad=0>.");
			} else {
				c.sendMessage("<shad=0>You Need </col><shad=16711680>[250m]</col> <shad=0>to upgrade your tax bag");
				c.antiDupe = 0;
			}
			if (c.antiDupe == 0) {
			}
		}
				
		if(itemId == 10832) {
			c.getDH().sendDialogues(365, -1);
		}
		
		if(itemId == 10833) {
			c.getDH().sendDialogues(366, -1);
		}
		
		if(itemId == 10834) {
			c.getDH().sendDialogues(367, -1);
		}
		
		if(itemId == 10835) {
			c.antiDupe = 5;
			if (c.antiDupe == 5) {
				c.getItems().deleteItem(10835, 1);
				c.getItems().addItem(995, 2000000000);
				c.getItems().addItem(10831, 1);
				c.antiDupe = 0;
				c.sendMessage("<shad=0>You have picked </col><shad=16711680>[2000M]</col> <shad=0>From your tax bag.");
			} else if (c.antiDupe == 0) {
				c.sendMessage("<shad=16711680>[Server AntiDupe System]</col> <shad=0>: You Tried to Dupe, We Bringing the Cops!</col>");
			}
		}
			
		
		//Reset Skills Tablet
		
		if(itemId == 14696) {
			c.getPA().showInterface(2808);
			c.antiDupe = 5;
		}
			
			
			
			
			
			
			
			
			

               if (itemId == 6199) {
            int mysteryReward = Misc.random(10);
            if (mysteryReward == 1) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(4151, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 2) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(13371, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 3) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(1, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 4) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(612, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 5) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(995, Misc.random(5000000));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 6) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(632, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 7) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(14484, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 8) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(11724, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 9) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(630, Misc.random(1));
                c.sendMessage("You open the box.");
    }
    else if (mysteryReward == 10) {
                c.getItems().deleteItem(6199, 1);
        c.getItems().addItem(6570, Misc.random(1));
                c.sendMessage("You open the box.");
    }
}
		if(itemId == 15262) {
			c.getItems().addItem(18016, 10000);
			c.getItems().deleteItem(15262, 1);
		}
		
			
		if(itemId == 6796) {
			c.playerLevel[0] = 99;
			c.playerLevel[2] = 99;
			c.playerLevel[3] = 99;
			c.playerLevel[4] = 99;
			c.playerLevel[6] = 99;
			c.playerXP[0] = c.getPA().getXPForLevel(100);
			c.playerXP[2] = c.getPA().getXPForLevel(100);
			c.playerXP[3] = c.getPA().getXPForLevel(100);
			c.playerXP[4] = c.getPA().getXPForLevel(100);
			c.playerXP[6] = c.getPA().getXPForLevel(100);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			c.getItems().deleteItem(6796, 1);
			c.logout();
			}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 2528) {
		c.getItems().deleteItem(2528,1);
		c.getPA().showInterface(2808);
		}
		if (itemId == 11850) {
		c.getItems().deleteItem(11850,1);
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		}
		if (itemId == 11852) {
		c.getItems().deleteItem(11852,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11854) {
		c.getItems().deleteItem(11854,1);
		c.getItems().addItem(4745,1);
		c.getItems().addItem(4747,1);
		c.getItems().addItem(4749,1);
		c.getItems().addItem(4751,1);
		}
		if (itemId == 11856) {
		c.getItems().deleteItem(11856,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11848) {
		c.getItems().deleteItem(11848,1);
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		}
		if (itemId == 11846) {
		c.getItems().deleteItem(11846,1);
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		}
		//Begin artifacts by Hirukos
		if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		//End of artifacts By Hirukos
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			}
		}
	}
	

}
