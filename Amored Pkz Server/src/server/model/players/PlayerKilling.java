package server.model.players;

public class PlayerKilling {

	private Client c;
	
	/**
	* Constructor class
	*/
	
	public PlayerKilling(Client Client) {
		this.c = Client;
	}
	
	/**
	* How many people you have to kill before getting points again
	* for killing the same person.
	*/
	
	public final int NEEDED_KILLS = 10;
	
	/**
	* First the method checks if the array list contains the person
	* and if it doesn't then add there name but if it does then
	* return the method false.
	*/
	
	public boolean addPlayer(String i) {
		if(!c.killedPlayers.contains(i)) {
			c.killedPlayers.add(i);
			return true;
		}
		return false;
	}
	
	/**
	* Checking if the array list contains the player and if
	* the person has killed 20 or more people since that person.
	*/
	
	public void checkForPlayer(String i) {
		if(c.killedPlayers.contains(i) && c.killedPlayers.indexOf(i) >= NEEDED_KILLS) {
			c.killedPlayers.remove(i);
		}
	}

}