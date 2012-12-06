package server.model.players.contents;

import server.model.players.Client;
import server.Server;
import server.model.players.PlayerHandler;
import server.model.players.Player;

/**
 * Killing Streak System
 * 
 * @author Dakota Chest
 */

public class KillingStreak {

		Client c;
	
		public KillingStreak(Client c) {
			this.c = c;
		}
	
		/**
		* Sends the message throughout the server
		*/
		
		public void yell(String msg) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
				c2.sM(msg);
				}
			}	
		}
		
		/**
		* Add rewards to the player who died
		* with the kill streak
		* 
		*/
		
		public void Rewards() {
			Client o = (Client) PlayerHandler.players[c.killerId];
			if (o.killStreak == 2) {
					c.getItems().addItem(995,2000000);		
			} else if (o.killStreak == 3) {
					c.getItems().addItem(995,3000000);
			} else if (o.killStreak == 4) {
					c.getItems().addItem(995,4000000);
			} else if (o.killStreak == 5) {
					c.getItems().addItem(995,5000000);
			} else if (o.killStreak == 6) {
					c.getItems().addItem(995,6000000);
			} else if (c..killStreak == 2) {
					c.getItems().addItem(995,2000000);						
			} else if (c.killStreak == 3) {
					c.getItems().addItem(995,3000000);
			} else if (c.killStreak == 4) {
					c.getItems().addItem(995,4000000);
			} else if (c.killStreak == 5) {
					c.getItems().addItem(995,5000000);
			} else if (c.killStreak == 6) {
					c.getItems().addItem(995,6000000);							
			}
		}
		
		/**
		* Checks the player if they have
		* a killstreak of 2 or more
		* can add on
		* 
		*/
		
		public void checkKillStreak() {
			switch (c.killStreak){
				case 2:
					yell("@red@PvP System:@bla@ "+c.playerName+", is on a killing streak of two, Bounty: 2M!");
					break;
				case 3:
					yell("@red@PvP System:@bla@ "+c.playerName+", is on a killing streak of three, Bounty: 3M!");
					break;
				case 4:
					yell("@red@PvP System:@bla@ "+c.playerName+", is on a killing streak of four, Bounty: 4M!");
					break;
				case 5:
					yell("@red@PvP System:@bla@ "+c.playerName+", is on a killing streak of five, Bounty: 5M!");
					break;
				case 6:
					yell("@red@PvP System:@bla@ "+c.playerName+", is on a killing streak of six, Bounty: 6M!");
					break;
				}		
			}

		/**
		* Checks if the player with the killstreak
		* died add items to the killer
		* 
		*/	
			
			public void killedPlayer() {
			Client o = (Client) PlayerHandler.players[c.killerId];
				if (o.killStreak >= 2 || c.killerId != o.playerId) {
					yell("@red@PvP System:@bla@ "+c.playerName+" has ended "+o.playerName+" killing streak of "+o.killStreak+", and was rewarded.");
						Rewards();
						}
				  }	  
			}