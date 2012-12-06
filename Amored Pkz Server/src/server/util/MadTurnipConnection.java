package server.util;

import java.sql.*;
import server.model.players.Client;

public class MadTurnipConnection extends Thread {

	public static Connection con = null;
	public static Statement stm;

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://93.174.92.235/destidat_donate", "destidat_donate", "destintokill");
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public MadTurnipConnection(){
		
	}
	
	public void run() {
		while(true) {		
			try {
				if(con == null)
					createConnection(); 
				else
					ping();
				Thread.sleep(10000);//10 seconds
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ping(){
		try {
			String query = "SELECT * FROM donation WHERE username = 'null'";
			query(query);
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public static void addDonateItems(final Client c,final String name){
		if(con == null){
			if(stm != null){
				try {
					stm = con.createStatement();
				} catch(Exception e){
					con = null;
					stm = null;
					//put a sendmessage here telling them to relog in 30 seconds
					return;
				}
			} else {
				//put a sendmessage here telling them to relog in 30 seconds
				return;
			}
		}
		new Thread(){
			@Override
			public void run()
			{
				try {
					String name2 = name.replaceAll(" ","_");
					String query = "SELECT * FROM donation WHERE username = '"+name2+"'";
					ResultSet rs = query(query);
					boolean b = false;
					while(rs.next()){
						int prod = Integer.parseInt(rs.getString("productid"));
						int price = Integer.parseInt(rs.getString("price"));
						if(prod == 1 && price == 15){
							c.getItems().addItem(11694,1);
							b = true;
						} else if(prod == 29 && price == 20){
							c.playerRights = 4;
							c.isDonator = 1;
							b = true;
						} else if(prod == 2 && price == 20){
							c.getItems().addItem(11724,1);
							c.getItems().addItem(11726,1);
							c.getItems().addItem(11728,1);
							b = true;
						} else if(prod == 3 && price == 20){
							c.getItems().addItem(1042,1);
							b = true;
						} else if(prod == 4 && price == 7){
							c.getItems().addItem(4151,1);
							b = true;
						} else if(prod == 5 && price == 20.00){
							c.getItems().addItem(11718,1);
							c.getItems().addItem(11720,1);
							c.getItems().addItem(11722,1);
							b = true;
						} else if(prod == 6 && price == 13){
							c.getItems().addItem(11698,1);
							b = true;
						} else if(prod == 7 && price == 13){
							c.getItems().addItem(11700,1);
							b = true;
						} else if(prod == 8 && price == 13){
							c.getItems().addItem(11696,1);
							b = true;
						} else if(prod == 9 && price == 11){ // sarasword
							c.getItems().addItem(11730,1);
							b = true;
						} else if(prod == 10 && price == 10){
							c.getItems().addItem(11335,1);
							b = true;
						} else if(prod == 11 && price == 13){
							c.getItems().addItem(6666,1);
							b = true;
						} else if(prod == 12 && price == 6){
							c.getItems().addItem(11235,1);
							b = true;
						} else if(prod == 13 && price == 4){ // torso
							c.getItems().addItem(10551,1);
							b = true;
						} else if(prod == 14 && price == 7){
							c.getItems().addItem(6570,1);
							b = true;
						} else if(prod == 15 && price == 9){
							c.getItems().addItem(11283,1);
							b = true;
						} else if(prod == 16 && price == 30){
							c.getItems().addItem(1053,1);
							c.getItems().addItem(1055,1);
							c.getItems().addItem(1057,1);
							b = true;
						} else if(prod == 17 && price == 80){
							c.getItems().addItem(1038,1);
							c.getItems().addItem(1040,1);
							c.getItems().addItem(1042,1);
							c.getItems().addItem(1044,1);
							c.getItems().addItem(1046,1);
							c.getItems().addItem(1048,1);
							b = true;
						} else if(prod == 18 && price == 20){ 
							c.getItems().addItem(1046,1);
							b = true;
						} else if(prod == 19 && price == 20){
							c.getItems().addItem(1048,1);
							b = true;
						} else if(prod == 20 && price == 20){
							c.getItems().addItem(1044,1);
							b = true;
						} else if(prod == 21 && price == 20){
							c.getItems().addItem(1040,1);
							b = true;
						} else if(prod == 22 && price == 20){ 
							c.getItems().addItem(1038,1);
							b = true;
						} else if(prod == 23 && price == 6){ // void
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11664,1);
							b = true;
						} else if(prod == 24 && price == 6){
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11665,1);
							b = true;
						} else if(prod == 25 && price == 5){
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11663,1);
							b = true;
						} else if(prod == 26 && price == 7){
							c.getItems().addItem(995,100000000);
							b = true;
						} else if(prod == 27 && price == 14){
							c.getItems().addItem(1050,1);
							b = true;
						} else if(prod == 28 && price == 10){//what u want price to be
							c.getItems().addItem(15018,1);
							c.getItems().addItem(15017,1);
							c.getItems().addItem(15019,1);
							c.getItems().addItem(13902,1);
							b = true;
						} else if(prod == 30 && price == 2){//what u want price to be
							c.getItems().addItem(6585,1);
							b = true;
						}
					}
					if(b){
						query("DELETE FROM `donation` WHERE `username` = '"+name2+"';");
							c.sendMessage("You have received your donation set.");
							c.sendMessage("Thank-you for donating!");
							c.SaveGame();
							c.saveCharacter = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					con = null;
					stm = null;
				}
			}
		}.start();
	}
	
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stm.executeQuery(s);
				return rs;
			} else {
				stm.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
		return null;
	}
}