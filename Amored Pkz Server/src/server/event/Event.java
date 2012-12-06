package server.event;


/**
 * A simple interface for an event.
 * 
 * @author Graham
 * 
 */
public interface Event {

	/**
	 * Called when the event is executed.
	 * 
	 * @param container
	 *            The event container, so the event can dynamically change the
	 *            tick time etc.
	 */
	public void execute(EventContainer container);

}
