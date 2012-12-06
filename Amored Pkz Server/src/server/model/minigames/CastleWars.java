package server.model.minigames;


import java.util.ArrayList;

import server.Server;
import server.model.players.Client;
import server.util.Misc;

public class CastleWars {
	
	private CastleWarObjects cwo = new CastleWarObjects();
	
	public CastleWars() {
		/**
		* Constructor
		*/
	}
	
	public ArrayList<Integer> saradomin = new ArrayList<Integer> (); //team 1
	public ArrayList<Integer> zamorak = new ArrayList<Integer> (); //team 2
	public ArrayList<Integer> saradominWait = new ArrayList<Integer> (); //team 1
	public ArrayList<Integer> zamorakWait = new ArrayList<Integer> (); //team 2
	public int saradominScore = 0;
	public int zamorakScore = 0;
	public boolean saraFlagSafe = true;
	public boolean zammyFlagSafe = true;
	private final int GAME_TIMER = 200; //1500 * 600 = 900000ms = 15 minutes
	private final int GAME_START_TIMER = 20;	
	public int timeRemaining = -1;
	public int gameStartTimer = GAME_START_TIMER;
	

	public void joinWait(Client c, int team) {
		if (c.playerEquipment[c.playerHat] > 0 || c.playerEquipment[c.playerCape] > 0) {
			c.sendMessage("You may not wear capes or helmets inside of castle wars.");
			return;
		}
		if (team == 1) {
			if (saradominWait.size() > zamorakWait.size()) {
				c.sendMessage("This team is currently full.");
				return;
			} else {
				saradominWait.add(c.playerId);
				c.castleWarsTeam = 1;
			}
		} else if (team == 2) {
			if (zamorakWait.size() > saradominWait.size()) {
				c.sendMessage("This team is currently full.");
				return;
			} else {
				zamorakWait.add(c.playerId);
				c.castleWarsTeam = 2;
			}
		} else {
			if (zamorakWait.size() > saradominWait.size()) {
				saradominWait.add(c.playerId);
				c.sendMessage("You have been added to the Saradomin team.");
				c.castleWarsTeam = 1;
			} else {
				zamorakWait.add(c.playerId);
				c.sendMessage("You have been added to the Zamorak team.");
				c.castleWarsTeam = 2;
			}
		}
		toWaitingRoom(c, c.castleWarsTeam);
	}

	public void toWaitingRoom(Client c, int team) {
		if (team == 1) {
			c.getPA().movePlayer(2377 + Misc.random(10), 9485 + Misc.random(5),0);
		} else if (team == 2) {
			c.getPA().movePlayer(2417 + Misc.random(10), 9485 + Misc.random(7),0);
		}
		c.inCwWait = true;
	}
	
	public void leaveWaitingRoom(Client c) {
		c.inCwWait = false;
		c.getPA().movePlayer(2439 + Misc.random(4), 3085 + Misc.random(5), 0);
		if (c.castleWarsTeam == 1) {
			saradominWait.remove(saradomin.indexOf(c.playerId));
			c.castleWarsTeam = -1;
		} else if (c.castleWarsTeam == 2) {
			zamorakWait.remove(zamorak.indexOf(c.playerId));
			c.castleWarsTeam = -1;
		}			
	}
	
	
	int properTimer = 0;
	public void process() {
		if (properTimer > 0) {
			properTimer--;
			return;
		} else {
			properTimer = 4;
		}
			
		if (gameStartTimer > 0) {
			gameStartTimer--;
			updatePlayers();
		} else if (gameStartTimer == 0) {
			startGame();
		}
		if (timeRemaining > 0) {
			timeRemaining--;
			updateInGamePlayers();
		} else if (timeRemaining == 0) {
			endGame();
		}
		/** 
		* The process of the castle wars game - called every 3 seconds
		*/
	}
	
	public void updatePlayers() {
		//saradomin players
		for (int player : saradominWait) {
			if (Server.playerHandler.players[player] != null) {
				Client c = (Client) Server.playerHandler.players[player];
				c.getPA().walkableInterface(6673);
				c.getPA().sendFrame126("Next Game Begins In : " + ((gameStartTimer * 3) + (timeRemaining * 3)) + " seconds.", 6570);
				c.getPA().sendFrame126("", 6572);
				c.getPA().sendFrame126("", 6664);
			//update interface here
			} else {
				saradominWait.remove(saradominWait.indexOf(player));
			}
		}
		for (int player : zamorakWait) {
			if (Server.playerHandler.players[player] != null) {
				Client c = (Client) Server.playerHandler.players[player];
				c.getPA().walkableInterface(6673);
				c.getPA().sendFrame126("Next Game Begins In : " + gameStartTimer * 3 + " seconds.", 6570);
				c.getPA().sendFrame126("", 6572);
				c.getPA().sendFrame126("", 6664);
			//update interface here
			} else {
				zamorakWait.remove(zamorakWait.indexOf(player));
			}
		}
	}
	
	public void updateInGamePlayers() {
		if (saradomin.size() > 0) {
			for (int player : saradomin) {
				if (Server.playerHandler.players[player] != null) {
					Client c = (Client) Server.playerHandler.players[player];
					c.getPA().walkableInterface(11146);
					c.getPA().sendFrame126(saradominScore + " = Saradomin", 11148);
					c.getPA().sendFrame126("Zamorak = " + zamorakScore, 11147);
					c.getPA().sendFrame126(gameStartTimer * 3 + " secs", 11155);
					c.getPA().sendFrame126("", 11158);
					c.getPA().sendFrame126("", 11160);
					c.getPA().sendFrame126("", 11162);
					c.getPA().sendFrame126("", 11164);
					c.getPA().sendFrame126("", 11168);
					if (saraFlagSafe) {
						c.getPA().sendFrame126("@gre@Safe", 11166);
					} else {
						c.getPA().sendFrame126("@red@Taken", 11166);
					}
					if (zammyFlagSafe) {
						c.getPA().sendFrame126("@gre@Safe", 11167);
					} else {
						c.getPA().sendFrame126("@red@Taken", 11167);
					}
				//update interface here
				} else {
					saradomin.remove(saradomin.indexOf(player));
				}
			
			}
		}
		if (zamorak.size() > 0) {
			for (int player : zamorak) {
				if (Server.playerHandler.players[player] != null) {
					Client c = (Client) Server.playerHandler.players[player];
					c.getPA().walkableInterface(11146);
					c.getPA().sendFrame126(saradominScore + " = Saradomin", 11148);
					c.getPA().sendFrame126("Zamorak = " + zamorakScore, 11147);
					c.getPA().sendFrame126(gameStartTimer * 3 + " secs", 11155);
					c.getPA().sendFrame126("", 11158);
					c.getPA().sendFrame126("", 11160);
					c.getPA().sendFrame126("", 11162);
					c.getPA().sendFrame126("", 11164);
					c.getPA().sendFrame126("", 11168);
					if (saraFlagSafe) {
						c.getPA().sendFrame126("@gre@Safe", 11166);
					} else {
						c.getPA().sendFrame126("@red@Taken", 11166);
					}
					if (zammyFlagSafe) {
						c.getPA().sendFrame126("@gre@Safe", 11167);
					} else {
						c.getPA().sendFrame126("@red@Taken", 11167);
					}
				//update interface here
				} else {
					zamorak.remove(zamorak.indexOf(player));
				}
			
			}
		}	
	
	}
	public void startGame() {
		gameStartTimer = -1;
		System.out.println("Starting Castle Wars game.");
		timeRemaining = GAME_TIMER;
		for (int player : saradominWait) {
			if (Server.playerHandler.players[player] != null) {
				//put player @ coords
				Client c = (Client)Server.playerHandler.players[player];
				c.getPA().walkableInterface(-1);
				c.getPA().movePlayer(2426 + Misc.random(3), 3076 - Misc.random(3), 1);
				saradomin.add(player);
				c.inCwWait = false;
				c.inCwGame = true;
			}		
		}
		saradominWait.clear();
		
		for (int player : zamorakWait) {
			if (Server.playerHandler.players[player] != null) {
				//put player @ coords
				Client c = (Client)Server.playerHandler.players[player];
				c.getPA().walkableInterface(-1);
				c.getPA().movePlayer(2373 + Misc.random(3), 3131 - Misc.random(3), 1);
				zamorak.add(player);
				c.inCwWait = false;
				c.inCwGame = true;
			}		
		}
		saradominWait.clear();
	}
	
	public void endGame() {
		timeRemaining = -1;
		System.out.println("Ending Castle Wars game.");
		gameStartTimer = GAME_START_TIMER;
	}
	
	public void handleObjects(Client c, int objectId, int objectX, int objectY){
		cwo.handleObject(c, objectId, objectX, objectY);
	}
	
	


}