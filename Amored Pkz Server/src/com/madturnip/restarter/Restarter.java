package com.madturnip.restarter;

import java.text.DecimalFormat;

/**
* @author MadTurnip, Martin
* AutoRestarter Main class
*/

public class Restarter extends Thread {

	
	public Restarter() {
    }
	
	public static void main(String[] ags){
		r = new Restarter();
		r.start();
	}
	public static Restarter r = null;
	
	public final int howOftenCheck = 120;//120 seconds
	
	public final String ip = "localhost";
	public final int port = 43594;
	
	public static int failed = 0;
	public static int allowFailed = 5;
	public static Login loginClass = new Login();
	public static int failed99 = 0;
	
	public static final long SERVER_STARTED_AT = System.currentTimeMillis();
	
	public void run(){
		//System.out.println("Starting Auto-Restarter By Mad Turnip.");
		try {
			Thread.sleep(60000);//wait a minute incase of massing loggin in and alot of time spent loading files
		} catch(Exception e){}
		while(true){
			try{
				Thread.sleep((1000*howOftenCheck));
				//System.out.println("testing server avability.");
				boolean k = loginClass.login(ip,port);
				boolean j = false;
				failed = 0;
				while(!k){
					System.out.println("Restarting Server in: "+(allowFailed - failed));
					//couldnt connect so server could be down
					Thread.sleep(1000);//wait 1 second so the server has a chance to handle the large ammount of players it might have
					k = loginClass.login(ip,port);
					if(!k){
						failed++;
					}					
					if(failed >= allowFailed){
						//System.exit(0);//oh yes shutdown the server
						//failed99++;
						//System.out.println("Restarting server, Restart count: "+failed99);
						Thread.sleep(1000);
						Runtime.getRuntime().exec("TASKKILL /f /im java.exe");
						//j = true;
						//k = true;
					}
				}
				if(!j){
					System.out.println("Server is online, Online time: "+timeSince(SERVER_STARTED_AT));
				}
				j = false;
			} catch (Exception e) {
			}
		}
	}
	private final DecimalFormat format = new DecimalFormat("00");
	
	public final String timeSince(long time) {
		int seconds = (int) ((System.currentTimeMillis() - time) / 1000);
		int minutes = (int) (seconds / 60);
		int hours = (int) (minutes / 60);
		int days = (int) (hours / 24);
		String dayStr = "";
		if (days > 0)
			dayStr = days + " days, ";
		String s = null;
		synchronized (format) {
			s = dayStr + format.format(hours % 24) + ":"
					+ format.format(minutes % 60) + ":"
					+ format.format(seconds % 60);
		}
		return s;
	}
}