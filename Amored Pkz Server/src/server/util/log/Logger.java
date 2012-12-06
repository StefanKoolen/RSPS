package server.util.log;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import server.util.SimpleTimer;

/**
 * 
 * @author nick
 */
public class Logger extends PrintStream {

	private DateFormat dateFormat = new SimpleDateFormat();
	private Date cachedDate = new Date();
	private SimpleTimer refreshTimer = new SimpleTimer();

	public Logger(PrintStream out) {
		super(out);
	}

	@Override
	public void print(String str) {
		if (str.startsWith("debug:"))
			super.print("[" + getPrefix() + "] DEBUG: " + str.substring(6));
		else
			super.print("[" + getPrefix() + "]: " + str);
	}

	private String getPrefix() {
		if (refreshTimer.elapsed() > 1000) {
			refreshTimer.reset();
			cachedDate = new Date();
		}
		return dateFormat.format(cachedDate);
	}
}
