package server.model.minigames;


import java.util.ArrayList;

import server.Server;
import server.model.players.Client;
import server.util.Misc;

public class lottery {
	
	public lottery() { //constructor
	
	}
	
	public ArrayList<String> lotteryPlayersNames = new ArrayList<String> (); //players (playername)
	public ArrayList<String> unclaimedWinners = new ArrayList<String> (); //Winners that havent claimed
	public int lotteryFund = 0;
	public int prizeAmount = 50; //jackpot amount (in millions)
	public int entryPrice = 5; //price to enter lottery (in millions)
	public int maximumEntryTimes = 5; //maximum times 1 player can enter per draw
	public long lastAnnouncment;
	public int announcmentFrequency = 15; //announcment frequency in mins

	public void process() {
		if (lotteryFund >= prizeAmount * 2000000) {
			drawLottery();
		}
		if (System.currentTimeMillis() - lastAnnouncment > (1000 * 60 * announcmentFrequency)) {
			announceFund();
			lastAnnouncment = System.currentTimeMillis();
		}
		
	}
	public int checkEntriesCount(Client c) {
		int entries = 0;
		entries = 0;
		for (int indexes = 0; indexes < lotteryPlayersNames.size(); indexes++) {
			if (lotteryPlayersNames.get(indexes).equalsIgnoreCase("" + c.playerName)) {
				entries += 1;
			}
		}		
		return entries;
	}
	public void enterLottery(Client c) {
		if (checkEntriesCount(c) < maximumEntryTimes) {
			if (c.getItems().playerHasItem(995, entryPrice * 1000000)) {
				lotteryPlayersNames.add(c.playerName);
				lotteryFund += entryPrice * 1000000;
				c.getItems().deleteItem2(995, entryPrice * 1000000);
				c.sendMessage("You have been entered into the lottery, when the lottery fund reaches 50M a winner");
				c.sendMessage("will be drawn");	
			} else {
				c.sendMessage("You dont have enough cash!");
			}
		} else {
			c.sendMessage("You have already entered 5 times!");
		}
	}
		
	public void drawLottery() {
		boolean prizeGiven = false;
		int arraySize = lotteryPlayersNames.size() -1;
		int winner = Misc.random(arraySize);
		try {
			String player = lotteryPlayersNames.get(winner);
			Client c = null;
			for(int i = 0; i < Server.playerHandler.players.length; i++) {
				if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(player)) {
						c = (Client)Server.playerHandler.players[i];
						c.sendMessage("You have won the lottery!");
						prizeGiven = true;
						if (c.getItems().freeSlots() > 0) {
							c.getItems().addItem(995,prizeAmount * 1000000);
						} else {
							c.sendMessage("You do not have enough room in your inventory to claim your reward!");
							c.sendMessage("We will try to add your reward again when you next login.");
							unclaimedWinners.add(c.playerName);
						}
					}
				}
			}
			if (prizeGiven == false) {
				unclaimedWinners.add(lotteryPlayersNames.get(winner));
				prizeGiven = true;
			}
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client all = (Client)Server.playerHandler.players[j];
					all.sendMessage("<shad=40960>[Lottery]<shad=-1> The Lottery has been won by " + lotteryPlayersNames.get(winner));
				}
			}
		} catch (Exception e) {
			System.out.println("Lottery draw failed!");
		}
		lotteryFund = 0;
		lotteryPlayersNames.clear();
		prizeGiven = false;
	}
	public void announceFund() {
		int fund = lotteryFund / 1000000;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client all = (Client)Server.playerHandler.players[j];
				all.sendMessage("<shad=40960>[Lottery]<shad=-1> The Lottery Fund is currently at " + fund + "m. Enter by talking to lottie at Home!");
			}
		}
	}
	public void checkUnclaimedWinners(Client c) {
		if (unclaimedWinners.contains(c.playerName)) {
			if (c.getItems().freeSlots() > 0) {
				c.sendMessage("You have been given your reward for winning the lottery!");
				c.getItems().addItem(995,prizeAmount * 1000000);
				unclaimedWinners.remove(unclaimedWinners.indexOf(c.playerName));
			} else {
				c.sendMessage("You have won the lottery but do not have space for the reward!");
			}
		}
	}
}