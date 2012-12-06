package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;

import server.event.EventManager;
import server.model.npcs.NPCHandler;
import server.model.npcs.NPCDrops;
import server.model.players.PlayerHandler;
import server.model.players.Player;
import server.model.players.Client;
import server.model.minigames.lottery;
import server.model.players.PlayerSave;
import server.model.minigames.*;
import server.net.ConnectionHandler;
import server.net.ConnectionThrottleFilter;
import server.util.ShutDownHook;
import server.util.SimpleTimer;
import server.util.log.Logger;
import server.event.Event; 
import server.event.EventContainer;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
//import server.util.MadTurnipConnection;
import server.world.ShopHandler;
import server.world.map.VirtualWorld;
import server.world.ClanChatHandler;
import server.world.WorldMap;
import server.world.WalkingHandler;

/**
 * Server.java
 *
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30
 *
 */

public class Server {
	
	
	public static boolean sleeping;
	public static int cycleRate;
        public static lottery lottery = new lottery();
	public static boolean UpdateServer = false;

	public static long lastMassSave = System.currentTimeMillis();
	private static IoAcceptor acceptor;
	private static ConnectionHandler connectionHandler;
	private static ConnectionThrottleFilter throttleFilter;
	private static SimpleTimer engineTimer, debugTimer;
	public static ZombieCaves zombieCaves = new ZombieCaves();
	//public static ZombieMiniGame zombieMiniGame = new ZombieMiniGame();
	private static long cycleTime, cycles, totalCycleTime, sleepTime;
	private static DecimalFormat debugPercentFormat;
	public static boolean shutdownServer = false;		
	public static boolean shutdownClientHandler;			
	public static int serverlistenerPort; 
	public static ItemHandler itemHandler = new ItemHandler();
	public static PlayerHandler playerHandler = new PlayerHandler();
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
	public static CastleWars castleWars = new CastleWars();
	public static FightPits fightPits = new FightPits();
	public static PestControl pestControl = new PestControl();
	public static int days, hours, minutes, secundes;
	public static NPCDrops npcDrops = new NPCDrops();
	public static ClanChatHandler clanChat = new ClanChatHandler();
	public static FightCaves fightCaves = new FightCaves();
	public static RFD rfd = new RFD();
	//public static WorldMap worldMap = new WorldMap();
	public static long[] TIMES = new long[5];
	public static void shutdown() {
    shutdownServer = true;
    System.exit(0);
    }
	//private static final WorkerThread engine = new WorkerThread();
	
	static {
		if(!Config.SERVER_DEBUG) {
			serverlistenerPort = 43594;
		} else {
			serverlistenerPort = 43594;
		}
		cycleRate = 474;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}
	//height,absX,absY,toAbsX,toAbsY,type
    /*public static final boolean checkPos(int height,int absX,int absY,int toAbsX,int toAbsY,int type)
    {
        return I.I(height,absX,absY,toAbsX,toAbsY,type);
    }*/
	public static void main(java.lang.String args[]) throws NullPointerException, IOException {
	Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					for(Player p : PlayerHandler.players) {
						if(p == null)
							continue;
					        System.out.println("Saving all players...");
					        PlayerSave.saveGame((Client)p);
					}
				}
			});
try {
	WalkingHandler.getSingleton().initialize();
} catch(Exception ex) {
	ex.printStackTrace();
}
		
		/**
		 * Starting Up Server
		 */
		 
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));
		System.out.println("Launching WortelScape");
		//MadTurnipConnection md = new MadTurnipConnection();
	      //md.start();
		  
		/**
		 * World Map Loader
		 */
		//if(!Config.SERVER_DEBUG)
			//VirtualWorld.init();
		//WorldMap.loadWorldMap();	

		/**
		 * Script Loader
		 */
		//ScriptManager.loadScripts();
		
		/**
		 * Accepting Connections
		 */
		acceptor = new SocketAcceptor();
		connectionHandler = new ConnectionHandler();
		
		SocketAcceptorConfig sac = new SocketAcceptorConfig();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		
		throttleFilter = new ConnectionThrottleFilter(Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", throttleFilter);
		acceptor.bind(new InetSocketAddress(serverlistenerPort), connectionHandler, sac);

		/**
		 * Initialise Handlers
		 */
		//VoteForCash.createConnection();
		EventManager.initialize();
		Connection.initialize();
		//PlayerSaving.initialize();
		//MysqlManager.createConnection();
		
		/**
		 * Clipped Following (NPC)
		 */
		try {
		 WalkingHandler.getSingleton().initialize();
		} catch(Exception ex) {
		ex.printStackTrace();
		}
		/**
		 * Server Successfully Loaded 
		 */
		System.out.println("Server listening on port 127.0.0.1:" + serverlistenerPort);
		/**
		 * Main Server Tick
		 */
		try {
			while (!Server.shutdownServer) {
				if (sleepTime > 0)
					Thread.sleep(sleepTime);
				engineTimer.reset();
				itemHandler.process();
				playerHandler.process();	
	                        npcHandler.process();
				shopHandler.process();
				objectManager.process();
                                lottery.process();
				fightPits.process();
				pestControl.process();
				cycleTime = engineTimer.elapsed();
				if(cycleTime < 575)
					sleepTime = cycleRate - cycleTime;
				else
					sleepTime = 0;
				totalCycleTime += cycleTime;
				cycles++;
				debug();
				if(Config.SERVER_DEBUG) //i see.... i used wrong symbol lol LOL !
					//System.out.println(cycleTime+"--"+sleepTime);
				secundes++;
				if(secundes == 120){
					minutes++;
					secundes = 0;
				}
				if(minutes == 60){
					hours++;
					minutes = 0;
				}
				if(hours == 24){
					days++;
					hours = 0;
				}
				if(hours == 2 && minutes == 0 && secundes == 20){
				PlayerHandler.updateSeconds = 60;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
				}
				if(UpdateServer) {
				if (System.currentTimeMillis() - PlayerHandler.updateStartTime > 15000) {
					
		System.gc();
		Server.shutdown();
				
				}
				}
			
			if (System.currentTimeMillis() - lastMassSave > 10000000) {
					for(Player p : PlayerHandler.players) {
						if(p == null)
							continue;						
						PlayerSave.saveGame((Client)p);
						System.out.println("Saved game for " + p.playerName + ".");
						lastMassSave = System.currentTimeMillis();
					}
				
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;						
				PlayerSave.saveGame((Client)p);
				System.out.println("Saved game for " + p.playerName + ".");
			}
		}
		acceptor = null;
		connectionHandler = null;
		sac = null;
		System.exit(0);
	}
	
	public static void processAllPackets() {
		for (int j = 0; j < playerHandler.players.length; j++) {
			if (playerHandler.players[j] != null) {
				while(playerHandler.players[j].processQueuedPackets());			
			}	
		}
	}
	
	public static boolean playerExecuted = false;
	private static void debug() {
		if (debugTimer.elapsed() > 360*1000 || playerExecuted) {
			long averageCycleTime = totalCycleTime / cycles;
			System.out.println("Average Cycle Time: " + averageCycleTime + "ms");
			double engineLoad = ((double) averageCycleTime / (double) cycleRate);
			System.out.println("Players online: " + PlayerHandler.playerCount+ ", engine load: "+ debugPercentFormat.format(engineLoad));
			totalCycleTime = 0;
			cycles = 0;
			System.gc();
			System.runFinalization();
			debugTimer.reset();
			playerExecuted = false;
		}
	}
	
	public static long getSleepTimer() {
		return sleepTime;
	}
	
}
