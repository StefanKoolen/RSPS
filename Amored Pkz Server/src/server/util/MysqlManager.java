package server.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.model.players.Client;

/**
 * MySQL Class
 * @author Ryan / Lmctruck30
 *
 */

public class MysqlManager {

	/** MySQL Connection */
	public static Connection conn = null;
	public static Statement statement = null;
	public static ResultSet results = null;
	
	public static String MySQLDataBase = "a7287600_vote";
	public static String MySQLURL = "mysql6.000webhost.com";
	public static String MySQLUser = "a7287600_vote";
	public static String MySQLPassword = "lolbroek1";
	
	/**
	 * Creates a Connection to the MySQL Database
	 */
	public synchronized static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//conn = DriverManager.getConnection("jdbc:mysql://riotscape.com/riot_forum", "riot_forum", "ryan16");
			//Misc.println("MySQL Connected");
		} 
		catch(Exception e) {			
			//e.printStackTrace();
		}
	}
	
	public synchronized static void destroyConnection() {
		try {
			statement.close();
			conn.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public synchronized static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = statement.executeQuery(s);
				return rs;
			} else {
				statement.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			destroyConnection();
			createConnection();
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Save Sessions HighScores
	 * @param clientToSave The session that saves their stats
	 * @return The flag true if successful
	 */
	public synchronized static boolean saveHighScore(Client clientToSave) {
		try {
			query("DELETE FROM `skills` WHERE playerName = '"+clientToSave.playerName+"';");
			query("DELETE FROM `skillsoverall` WHERE playerName = '"+clientToSave.playerName+"';");
			query("INSERT INTO `skills` (`playerName`,`Attacklvl`,`Attackxp`,`Defencelvl`,`Defencexp`,`Strengthlvl`,`Strengthxp`,`Hitpointslvl`,`Hitpointsxp`,`Rangelvl`,`Rangexp`,`Prayerlvl`,`Prayerxp`,`Magiclvl`,`Magicxp`,`Cookinglvl`,`Cookingxp`,`Woodcuttinglvl`,`Woodcuttingxp`,`Fletchinglvl`,`Fletchingxp`,`Fishinglvl`,`Fishingxp`,`Firemakinglvl`,`Firemakingxp`,`Craftinglvl`,`Craftingxp`,`Smithinglvl`,`Smithingxp`,`Mininglvl`,`Miningxp`,`Herblorelvl`,`Herblorexp`,`Agilitylvl`,`Agilityxp`,`Thievinglvl`,`Thievingxp`,`Slayerlvl`,`Slayerxp`,`Farminglvl`,`Farmingxp`,`Runecraftlvl`,`Runecraftxp`) VALUES ('"+clientToSave.playerName+"',"+clientToSave.playerLevel[0]+","+clientToSave.playerXP[0]+","+clientToSave.playerLevel[1]+","+clientToSave.playerXP[1]+","+clientToSave.playerLevel[2]+","+clientToSave.playerXP[2]+","+clientToSave.playerLevel[3]+","+clientToSave.playerXP[3]+","+clientToSave.playerLevel[4]+","+clientToSave.playerXP[4]+","+clientToSave.playerLevel[5]+","+clientToSave.playerXP[5]+","+clientToSave.playerLevel[6]+","+clientToSave.playerXP[6]+","+clientToSave.playerLevel[7]+","+clientToSave.playerXP[7]+","+clientToSave.playerLevel[8]+","+clientToSave.playerXP[8]+","+clientToSave.playerLevel[9]+","+clientToSave.playerXP[9]+","+clientToSave.playerLevel[10]+","+clientToSave.playerXP[10]+","+clientToSave.playerLevel[11]+","+clientToSave.playerXP[11]+","+clientToSave.playerLevel[12]+","+clientToSave.playerXP[12]+","+clientToSave.playerLevel[13]+","+clientToSave.playerXP[13]+","+clientToSave.playerLevel[14]+","+clientToSave.playerXP[14]+","+clientToSave.playerLevel[15]+","+clientToSave.playerXP[15]+","+clientToSave.playerLevel[16]+","+clientToSave.playerXP[16]+","+clientToSave.playerLevel[17]+","+clientToSave.playerXP[17]+","+clientToSave.playerLevel[18]+","+clientToSave.playerXP[18]+","+clientToSave.playerLevel[19]+","+clientToSave.playerXP[19]+","+clientToSave.playerLevel[20]+","+clientToSave.playerXP[20]+");");
			query("INSERT INTO `skillsoverall` (`playerName`,`lvl`,`xp`) VALUES ('"+clientToSave.playerName+"',"+(clientToSave.getLevelForXP(clientToSave.playerXP[0]) + clientToSave.getLevelForXP(clientToSave.playerXP[1]) + clientToSave.getLevelForXP(clientToSave.playerXP[2]) + clientToSave.getLevelForXP(clientToSave.playerXP[3]) + clientToSave.getLevelForXP(clientToSave.playerXP[4]) + clientToSave.getLevelForXP(clientToSave.playerXP[5]) + clientToSave.getLevelForXP(clientToSave.playerXP[6]) + clientToSave.getLevelForXP(clientToSave.playerXP[7]) + clientToSave.getLevelForXP(clientToSave.playerXP[8]) + clientToSave.getLevelForXP(clientToSave.playerXP[9]) + clientToSave.getLevelForXP(clientToSave.playerXP[10]) + clientToSave.getLevelForXP(clientToSave.playerXP[11]) + clientToSave.getLevelForXP(clientToSave.playerXP[12]) + clientToSave.getLevelForXP(clientToSave.playerXP[13]) + clientToSave.getLevelForXP(clientToSave.playerXP[14]) + clientToSave.getLevelForXP(clientToSave.playerXP[15]) + clientToSave.getLevelForXP(clientToSave.playerXP[16]) + clientToSave.getLevelForXP(clientToSave.playerXP[17]) + clientToSave.getLevelForXP(clientToSave.playerXP[18]) + clientToSave.getLevelForXP(clientToSave.playerXP[19]) + clientToSave.getLevelForXP(clientToSave.playerXP[20]))+","+((clientToSave.playerXP[0]) + (clientToSave.playerXP[1]) + (clientToSave.playerXP[2]) + (clientToSave.playerXP[3]) + (clientToSave.playerXP[4]) + (clientToSave.playerXP[5]) + (clientToSave.playerXP[6]) + (clientToSave.playerXP[7]) + (clientToSave.playerXP[8]) + (clientToSave.playerXP[9]) + (clientToSave.playerXP[10]) + (clientToSave.playerXP[11]) + (clientToSave.playerXP[12]) + (clientToSave.playerXP[13]) + (clientToSave.playerXP[14]) + (clientToSave.playerXP[15]) + (clientToSave.playerXP[16]) + (clientToSave.playerXP[17]) + (clientToSave.playerXP[18]) + (clientToSave.playerXP[19]) + (clientToSave.playerXP[20]))+");");
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Save Voting Point Info
	 * @param c The session's client
	 * @return The flag if true was successful
	 */
	public static boolean saveVotingInfo(Client c) {
		try {
			query("INSERT INTO `skills` (`playerName`,`playerPass') VALUES ('"+c.playerName+"',"+c.playerPass+");");
		} catch(Exception e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static int loadVotingPoints(Client c) {
		try {
			ResultSet group = statement.executeQuery("SELECT * FROM user WHERE username = '"+c.playerName+"'");
			while(group.next()) {
				String groupp = group.getString("usergroupid");
				int mgroup = Integer.parseInt(groupp);
				if(mgroup > 0) {
					return mgroup;
				}
				return 0;
			}
		} catch(Exception e) {
			return -1;
		}
		return -1;
	}
	
	public static int loadDonationPoints(Client c) {
		try {
			ResultSet group = statement.executeQuery("SELECT * FROM user WHERE username = '"+c.playerName+"'");
			while(group.next()) {
				String groupp = group.getString("usergroupid");
				int mgroup = Integer.parseInt(groupp);
				if(mgroup > 0) {
					return mgroup;
				}
				return 0;
			}
		} catch(Exception e) {
			return -1;
		}
		return -1;
	}
	
}
