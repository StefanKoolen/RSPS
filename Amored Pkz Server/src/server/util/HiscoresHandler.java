package server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import server.model.players.Client;

/**
 * 
 * @author Joshua F
 * @author PJNoMore
 *
 */

public class HiscoresHandler {
	private static boolean HiScores = true;
	
	private static final String DB = "a4248182_Scores";
	private static final String URL = "mysql3.000webhost.com";
	private static final String USER = "a4248182_Scores";
	private static final String PASS = "leeds09";
	private static final Properties prop;
	static {
		prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASS);
		//prop.put("autoReconnect", "true");
		//prop.put("maxReconnects", "4");
	}
	
	public static Connection conn = null;
	
	/**
	 * Connects to the database
	 */
	public static synchronized void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + DB, prop);
			System.out.println("Hiscores Handler: Success");
		} catch (Exception e) {
			System.out.println("Hiscores Handler Error: "+ e);
			System.out.println("Setting hiscores to false to help not cause anymore errors.");
			HiScores = false;
		}
	}
	
	public static synchronized Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:mysql://" + URL + "/"
						+ DB, prop);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * The main method that is called upon logout
	 */
	public static void hiscoresHandler(Client c) {
		if (HiScores == true) {
			deleteHiscores(c);
			saveHiscores(c);
		}
	} 
	
	/**
	 * Part of the main method to save the hiscores
	 */
	private static synchronized void saveHiscores(Client c) {
		try {
			int overallLVL = 0;
			double overallXP = 0;
			for (int i = 0; i < 21; i++) {
				overallLVL += c.getLevelForXP(c.playerXP[i]);
				overallXP += c.playerXP[i];
			}
			getConnection().createStatement().execute(
					"INSERT INTO `hiscores` VALUES ('" + c.playerName + "', '"
							+ c.playerRights + "', '" + c.isDonator + "', '"
							+ overallLVL + "', '" + overallXP + "', '"
							+ c.playerXP[0] + "', '" + c.playerXP[1] + "', '"
							+ c.playerXP[2] + "', '" + c.playerXP[3] + "', '"
							+ c.playerXP[4] + "', '" + c.playerXP[5] + "', '"
							+ c.playerXP[6] + "', '" + c.playerXP[7] + "', '"
							+ c.playerXP[8] + "', '" + c.playerXP[9] + "', '"
							+ c.playerXP[10] + "', '" + c.playerXP[11] + "', '"
							+ c.playerXP[12] + "', '" + c.playerXP[13] + "', '"
							+ c.playerXP[14] + "', '" + c.playerXP[15] + "', '"
							+ c.playerXP[16] + "', '" + c.playerXP[17] + "', '"
							+ c.playerXP[18] + "', '" + c.playerXP[19] + "', '"
							+ c.playerXP[20] + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Part of the main method to save the hiscores
	 */
	private static synchronized void deleteHiscores(Client c) {
		try {
			getConnection().createStatement().execute(
					"DELETE FROM `hiscores` WHERE `playerName` = '"
							+ c.playerName + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Will wipe out the hiscores table, therefor cleaning them
	 */
	public static synchronized void clearHiscores() {
		try {
			getConnection().createStatement().execute(
					"TRUNCATE TABLE `hiscores`");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
