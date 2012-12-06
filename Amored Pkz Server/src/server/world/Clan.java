package server.world;

import server.model.players.Client;

/**
 * @author Sanity
 */

public class Clan {

	public Clan(Client c, String name) {
		this.owner = c.playerName;
		this.name = name;
	}
		
	public int[] members = new int [50];
	public String name;
	public String owner;
	public int playerz = 0;
	public int CS = 0;
	public boolean lootshare, coinshare;
}