package server.model.minigames;

import server.model.players.Client;
import server.util.Misc;
import server.Server;

/**
 * @author Sanity
 */

public class FightPits {

	public int[] playerInPits = new int[200];
	
	private int GAME_TIMER = 140;
	private int GAME_START_TIMER = 120;
	
	private int gameTime = -1;
	private int gameStartTimer = 120;
	private int properTimer = 0;
	public int playersRemaining = 0;
	public boolean gameOn = false;
	
	public String pitsChampion = "Nobody";
	
	public void process() {
		if (gameStartTimer > 0) {
			gameStartTimer--;
			updateWaitRoom();
		} 
		if (gameStartTimer == 0 && playersRemaining <= 1) {
			startGame();
		}
		if (playersRemaining == 1) {
			endPitsGame(getLastPlayerName());
		}
	}
	
	public String getLastPlayerName() {
		for (int j = 0; j < playerInPits.length; j++) {
			if (playerInPits[j] > 0)
				return Server.playerHandler.players[playerInPits[j]].playerName;
		}	
		return "Nobody";
	}
	
	public void updateWaitRoom() {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client) Server.playerHandler.players[j];
				if (c.getPA().inPitsWait() || c.inPits) {
					c.getPA().sendQuest("Fight Pits", 15894);
					c.getPA().sendQuest("Next Game In : " + gameStartTimer + " seconds.", 15895);
					c.getPA().sendQuest("Current Champion:", 15897);
					c.getPA().sendQuest("JalYt-Ket-"+pitsChampion,15898);
					c.getPA().sendQuest("Foes Remaining: "+playersRemaining, 15899); 
					c.getPA().sendQuest("", 15896);
        				c.getPA().sendQuest("", 15900);
        				c.getPA().sendQuest("", 15901);
        				c.getPA().sendQuest("", 15902);
        				c.getPA().sendQuest("", 15903);
        				c.getPA().sendQuest("", 15904);
        				c.getPA().sendQuest("", 15905);
        				c.getPA().sendQuest("", 15906);
					c.getPA().walkableInterface(15892);
					
				}
			}
		}	
	}
	
	public void startGame() {
		if (getWaitAmount() < 2) {
			gameStartTimer = GAME_START_TIMER;
			//System.out.println("Unable to start fight pits game due to lack of players.");/
			return;
		}	
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null )  {
					Client c = (Client)Server.playerHandler.players[j];
					if (c.getPA().inPitsWait())
						addToPitsGame(j);
			}	
		}
		System.out.println("Fight Pits game started.");
		gameStartTimer = GAME_START_TIMER + GAME_TIMER;
	}
	
	public int getWaitAmount() {
		int count = 0;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null )  {
					Client c = (Client)Server.playerHandler.players[j];
					if (c.getPA().inPitsWait())
						count++;
			}	
		}
		return count;
	}
	
	public void removePlayerFromPits(int playerId) {
		for (int j = 0; j < playerInPits.length; j++) {
			if (playerInPits[j] == playerId) {
				Client c = (Client)Server.playerHandler.players[playerInPits[j]];
				c.getPA().startTeleport(2399, 5173, 0, "modern");
				playerInPits[j] = -1;
				playersRemaining--;
				c.inPits = false;
				break;
			}
		}
	}
	
	public void endPitsGame(String champion) {
		boolean giveReward = false;
		if (playersRemaining == 1)
			giveReward = true;
		for (int j = 0; j < playerInPits.length; j++) {
			if (playerInPits[j] < 0)
				continue;
			if (Server.playerHandler.players[playerInPits[j]] == null)
				continue;
			Client c = (Client)Server.playerHandler.players[playerInPits[j]];
			c.getPA().startTeleport(2399, 5173, 0,  "modern");
			c.inPits = false;
		}
		playerInPits = new int[200];	
		pitsChampion = champion;
		playersRemaining = 0;
		pitsSlot = 0;
		gameStartTimer = GAME_START_TIMER;
		System.out.println("Fight Pits game ended.");
	}
	
	private int pitsSlot = 0;
	public void addToPitsGame(int playerId) {
		if (Server.playerHandler.players[playerId] == null)
			return;
		playersRemaining++;
		Client c = (Client)Server.playerHandler.players[playerId];
		playerInPits[pitsSlot++] = playerId;
		c.getPA().startTeleport(2392 + Misc.random(12), 5139 + Misc.random(25), 0, "modern");
		c.inPits = true;
		c.getDH().talk(588, "", "FIGHT!", "", "", 2617);		
	}
}