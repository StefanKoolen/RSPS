package server.model.players.skills;

import server.Config;
import server.Server;
import server.util.Misc;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.model.objects.Objects;
import server.model.players.Client;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fishing {
	
	private Client c;
	public Fishing(Client c) {
		this.c = c;
	}

public void FishingProcess() {
	//Fishing <3
        if (c.fishtimer > 0) {
            c.fishtimer--;
        }

        if (c.fishing && c.getItems().freeSlots() <= 0) {
            c.fishing = false;
            c.sendMessage("Your inventory is full");
            c.frame1();
        }

        if (c.fishing && c.fishtimer <= 0 && c.getItems().freeSlots() > 0) {
            if (c.getItems().playerHasItem(c.fishitem)) {
                if (c.playerLevel[10] >= c.fishreqt) {
                    if (c.fishitem == 307 && !c.getItems().playerHasItem(313)) {
                        c.sendMessage("You need bait to fish here!");
                        c.fishing = false;
                    } else if (c.fishitem == 309 && !c.getItems().playerHasItem(314)) {
                       c.sendMessage("You need feathers to fish here!");
                        c.fishing = false;
                    } else {
                        if (c.fishreq2 != 0 && c.playerLevel[10] >= c.fishreq2 && Misc.random(1) == 1) {
                            c.getItems().addItem(c.fishies2, 1);
                        c.getPA().addSkillXP(c.fishXP, 10);
                        } else {
                            c.getItems().addItem(c.fishies, 1);
                        c.getPA().addSkillXP(c.fishXP, 10);
                        }
                        if (c.fishitem == 307)
                        c.getPA().addSkillXP(c.fishXP, 10);
                        c.fishtimer = Misc.random(fishtime(c.fishies, c.fishreqt));
                        //c.sendMessage("You catch a " + c.getItems().getItemName(c.fishies).toLowerCase());
                    }
                } else {
                    c.fishing = false;
                    c.sendMessage("You need a fishing level of " + c.fishreqt + " to fish for " + c.getItems().getItemName(c.fishies));
                }
            } else {
                c.fishing = false;
               c.sendMessage("You need a " + c.getItems().getItemName(c.fishitem) + " to fish " + c.getItems().getItemName(c.fishies));
            }
        }

        if (c.fishing) {
            c.startAnimation(c.fishemote);
            //frame174(378, 3);
	c.stopMovement();
        }
	
      
	
		if (c.attemptingfish)
           if(c.clickObjectType > 0 && c.goodDistance(c.objectX + c.objectXOffset, c.objectY + c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
                c.attemptingfish = false;
                c.fishing = true;
            }
	}
	public void Lol() {
	c.fishing = true;
        c.startAnimation(c.fishemote);
	return;
	}       
	
	 public int fishtime(int fish, int req) {
        int time = 10;
        if (fish == 317) {//Shrimp 1
            time = 10;
        }
        if (fish == 327) {//Sardine 5
            time = 15;
        }
        if (fish == 355) {//Trout 20
            time = 20;
        }
        if (fish == 341) {//Cods 23
            time = 25;
        }
        if (fish == 349) {//Pike 25
            time = 28;
        }
        if (fish == 359) {//Tuna 35
            time = 30;
        }
        if (fish == 377) {//Lobsters 40
            time = 35;
        }
        if (fish == 383) {//Sharks 79
            time = 40;
        }
        if (fish == 17797) {
		    time = 60;
		}
	   if (fish == 389) {//Manta ray 85
            time = 45;
        }
	if (fish == 15272) {//Rocktail 95
            time = 45;
        }
        int LevelXP = c.playerLevel[10] - req;
        if (LevelXP > req / 3)
            LevelXP = req / 3;
        time -= LevelXP;
        return time;
    }
}