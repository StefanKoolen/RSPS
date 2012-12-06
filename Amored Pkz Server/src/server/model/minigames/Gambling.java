package server.model.minigames;

import server.model.players.Client;
import server.util.Misc;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;

public class Gambling {

	private static Client c;
	
	public Gambling(Client c) {
		this.c = c;	
	}

	public static void playGame(final Client c) {
		int random = Misc.random(9);
		int number1 = Misc.random(9);
		int number2 = Misc.random(9);
		c.sendMessage("Your numbers are: [<col=255>"+number1+"</col>] and [<col=255>"+number2+"</col>].");
		c.getPA().removeAllWindows();
		c.sendMessage("The winning number is: [<col=255>"+random+"</col>]." );
		if (random == number1 || random == number2) {
			c.sendMessage("Congratulations! You won random items!");
			rewardFive(c, true);
		} else if (random == number1 && random == number2) {
			c.sendMessage("<col=2676846>Congratulations you win a double prize!</col>");
		} else {
			c.sendMessage("<col=526584>Sorry you win nothing this round.</col>");
		}
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 1000000);
	}

	public static void rewardFive(Client c, boolean isDouble) {
		int chance = Misc.random(10);
		int i1 = Misc.random(1);
		int i2 = randomPrize[3][Misc.random(90)];

		c.getItems().addItem(i2, i1);
		c.getPA().showInterface(6960);
		c.getPA().displayItemOnInterface(6963, i2, 0, i1);

		if(isDouble) {
			addReward(c, 2, 40, 1, "Great! You win a double prize!");
			addReward(c, 2, 40, 2, "You won a double random prize!");
			return;
		}

		if(chance >= 0 && chance <= 5) {
			addReward(c, 0, 32, 1, "Better luck next time! You win a low standard item.");
		} else if(chance >= 6 && chance <= 9) {
			addReward(c, 1, 33, 1, "Well done! You win a standard item!");
		} else if(chance == 10) {
			addReward(c, 2, 40, 1, "Exellent! You win a high item!");
		}
		c.getPA().displayItemOnInterface(6963, -1, 2, -1);
	}

	public static void addReward(Client c, int i, int l, int t, String s) {
		int i1 = randomPrize[i][Misc.random(l)];
		c.getItems().addItem(i1, 1);
		c.getPA().displayItemOnInterface(6963, i1, t, 1);
		if(s.startsWith("")) {
			return;
		}
		c.sendMessage(s);
	}

	public static int[][] randomPrize = {
		{4151, 1333, 4087, 4585, 6916, 6918, 6920, 6922, 6924, 6926,
		 7362, 7364, 7366, 7368, 7370, 7372, 7374, 7376, 7378, 7380,
		 7382, 7384, 7386, 7388, 7390, 7392, 7394, 7396, 7398, 7399,
		 7400, -1},

		{1305, 1377, 1434, 2583, 2585, 2587, 2589, 2591, 2593, 2595, 
		 2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 
		 2617, 2619, 2621, 2623, 2625, 2627, 2629, -1},

		{2631, 2633, 2635, 2637, 2639, 2641, 2643, 2645, 2647, 
		 2649, 2651, 2653, 2655, 2657, 2659, 2661, 2663, 2665, 2667, 
		 2669, 2671, 2673, 2675, 10354, 3481, 3483, 3485, 3486, 3488, -1},

		{1060, 1066, 1080, 1094, 1114, 1216, 1334, 1518, 2441, 2443, 
		 2437, 2435, 9244, 1713, 1616, 1320, 1713, 4151, 1333, 4087, 4585, 6916, 6918, 6920, 6922, 6924, 6926,
		 7362, 7364, 7366, 7368, 7370, 7372, 7374, 7376, 7378, 7380,
		 7382, 7384, 7386, 7388, 7390, 7392, 7394, 7396, 7398, 7399,
		 7400, 1305, 1377, 1434, 2583, 2585, 2587, 2589, 2591, 2593, 2595, 
		 2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 
		 2617, 2619, 2621, 2623, 2625, 2627, 2629, 2631, 2633, 2635, 2637, 2639, 2641, 2643, 2645, 2647, 
		 2649, 2651, 2653, 2655, 2657, 2659, 2661, 2663, 2665, 2667, 
		 2669, 2671, 2673, 2675, 10354, 3481, 3483, 3485, 3486, 3488, -1}
	};

	private static boolean hasBetEnough;
	private static boolean blackJack;
	public static int playerBet = 0;
	public static boolean betting = false;

	public static void gambleBlackJack(Client c) {
		if(!c.getItems().playerHasItem(995, 100000)) {
			c.sendMessage("You need at least 100,000 coins to place a bet!");
			stopTheGame(c);
			return;
		}
		betting = true;
		hasBetEnough = true;
		c.outStream.createFrame(27);
	}

	private static void stopTheGame(Client c) {
		c.getPA().removeAllWindows();
		playerBet = -1;
		betting = false;
		hasBetEnough = false;
	}

	public static void blackJack(Client c) {
		if(playerBet < 100000) {
			c.sendMessage("You need to bet atleast 100,000 coins to play this game.");
			stopTheGame(c);
			return;
		}
		if(playerBet > 2000000) {
			c.sendMessage("You can only bet up to 2M coins per game.");
			stopTheGame(c);
			return;
		}
		if((playerBet * 2) > 2147000000) {
			c.sendMessage("Bank some money before gambling!");
			stopTheGame(c);
			return;
		}
		if(!c.getItems().playerHasItem(995, playerBet)) {
			c.sendMessage("You don't have that much!");
			stopTheGame(c);
			return;
		}
		c.getItems().deleteItem(995, c.getItems().getItemSlot(995), playerBet);	
		int computerTotalHit = Misc.random(22);
		int playerTotalHit = Misc.random(22);
		if((computerTotalHit) > 21) {
			c.sendMessage("You automatically win because the mage got bust.");
			recieveGambleBet(c);
		} else if((playerTotalHit) > 21) {
			c.sendMessage("BUST!");
		}
		if((playerTotalHit > computerTotalHit)) {
			c.sendMessage("You win with "+ playerTotalHit +"! The gambler had "+ computerTotalHit +".");
			recieveGambleBet(c);
		} else if((computerTotalHit > playerTotalHit)) {
			c.sendMessage("The gambler won with "+ computerTotalHit +". You had "+ playerTotalHit +".");
		}
		if(playerTotalHit == 21) {
			c.sendMessage("BLACKJACK!");
			blackJack = true;
			recieveGambleBet(c);
		}
		stopTheGame(c);
	}

	private static void recieveGambleBet(Client c) {
		if(hasBetEnough) {
			c.getItems().addItem(995, (blackJack ? (playerBet * 2) + (playerBet / 2) : (playerBet * 2)));
			c.sendMessage("You win "+ (blackJack ? (playerBet * 2) + (playerBet / 2) : (playerBet * 2)) +" coins!");
			hasBetEnough = false;
		}
		if(blackJack) {
			blackJack = false;
		}
	}
}