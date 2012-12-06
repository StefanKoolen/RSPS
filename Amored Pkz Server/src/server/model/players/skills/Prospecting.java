package server.model.players.skills;

import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.model.players.Client;

/**
* Prospecting action. Part of the mining skill.
* @author Fire cape.
*/
public class Prospecting {
	
	/**
	 * Prospects the rock.
	 * @param c The client class.
	 * @param itemId The name of the item within the object.
	 */
	public void prospectRock(final Client c, final String itemName) {
		c.sendMessage("You examine the rock for ores...");
		EventManager.getSingleton().addEvent(new Event() {

			@Override
			public void execute(EventContainer container) {
				c.sendMessage("This rock contains "+itemName+".");
				container.stop();
			}
			
		}, 3000);
	}
}