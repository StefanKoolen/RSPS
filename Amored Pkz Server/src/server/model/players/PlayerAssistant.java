package server.model.players;

import server.Config;
import server.Server;
import server.model.npcs.NPCHandler;
import server.util.Misc;
import server.world.map.*;
import java.util.Properties;
import server.model.players.PlayerSave;
import java.io.*;
import server.model.minigames.FightPits;
import server.model.players.Client;
import java.util.GregorianCalendar;
import java.util.Calendar;
import server.event.EventContainer;
import server.event.Event;
import server.event.EventManager;
import server.model.players.Player;
import server.Connection;


public class PlayerAssistant{
public boolean teleTabTeleport(int x, int y, int height, String teleportType){
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } else if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out of Jail!");
		} else if (c.InDung){
			c.sendMessage("You cant teleport from Dungeoneering.");
        } else if(c.duelStatus == 5){
		c.sendMessage("You can't teleport during a duel!");
        } else if(c.inFightCaves()) {
                c.sendMessage("You can't teleport out of this minigame!");
	} else if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL){
		c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
	} else if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength){
		c.sendMessage("You are teleblocked and can't teleport.");
	} else if(!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6){
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		c.stopMovement();
		removeAllWindows();			
		c.teleX = x;
		c.teleY = y;
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.faceUpdate(0);
		c.teleHeight = height;
		if(teleportType.equalsIgnoreCase("teleTab")) {
			c.startAnimation(9597);
			c.teleEndAnimation = 11973;
			c.teleTimer = 8;
			c.gfx0(1680);
		}
		return true;
	}
	return false;
}
	public String getTotalAmount(Client c, int j) {
		if(j >= 10000 && j < 10000000) {
			return j / 1000 + "K";
		} else if(j >= 10000000 && j  <= 2147483647) {
			return j / 1000000 + "M";
		} else {
			return ""+ j +" gp";
		}
	}
	
	private Client c;
	public PlayerAssistant(Client Client) {
		this.c = Client;
	}
	
			public void veteranemote() {
               c.gfx0(715);
               c.startAnimation(4021);
           }
                       public void compemote(final Client c) {
			    EventManager.getSingleton().addEvent(new Event() {
				int comptime = 28;
				 public void execute(EventContainer comp) {
				   if (comptime == 28) {
					c.startAnimation(13190);
				    }
				   if (comptime == 27) {
					c.npcId2 = 8596;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(11197);
				c.playerStandIndex = 11195;
				    }
				   if (comptime == 23) {
					c.npcId2 = 8597;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(11202);
				c.playerStandIndex = 11200;
				    }
				   if (comptime == 20) {
					c.npcId2 = 8591;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				c.playerStandIndex = 9724;
				    }
				 if (comptime == 17) {
					c.npcId2 = 8281;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				  c.startAnimation(13192);
				c.startAnimation(10680);
				c.startAnimation(10681);
				c.playerStandIndex = 10665;
				    }
				 
				 if (comptime == 13) {
					c.npcId2 = 10220;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13157);
				c.playerStandIndex = 13156;
				    }
				   if (comptime == 11) {
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13152);
					c.gfx100(2465);
				c.playerStandIndex = 13156;
				    }
				   if (comptime == 7) {
					c.npcId2 = 10770;
					 c.isNpc = true;
     				 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13156);
				c.playerStandIndex = 13156;
				    }
				   if (comptime == 0) {
					c.isNpc = false;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				c.playerStandIndex = 0x328;
					c.startAnimation(12567);
				    }
				   if (c == null || comptime <= 0) {
				       comp.stop();
                                                                         return; 
				    }
				   if (comptime >= 0) {
					comptime--;
				    }
				}
			    }, 600);
			}
					   public void dungemote2(final Client c) {
                            EventManager.getSingleton().addEvent(new Event() {
                                int dungtime = 5;
                                 public void execute(EventContainer dung) {
                                    int randomdung = Misc.random(1);
                                    if (dungtime == 5) {
                                       c.gfx0(2442);
                                        c.startAnimation(13190);
                                    }
                                   if (dungtime == 5 && randomdung == 0) {
                                        c.npcId2 = 11228;
                                         c.isNpc = true;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                        c.startAnimation(13192);
                                    }
                                   if (dungtime == 5 && randomdung == 1) {
                                        c.npcId2 = 11227;
                                         c.isNpc = true;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                        c.startAnimation(13193);
                                    }
                                    if (dungtime == 0) {
                                        c.isNpc = false;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                    }
                                   if (c == null || dungtime <= 0) {
                                       dung.stop();
                                                                         return; 
                                    }
                                   if (dungtime >= 0) {
                                        dungtime--;
                                    }
                                }
                            }, 600);
                        }
                       public void dungemote(final Client c) {
                            EventManager.getSingleton().addEvent(new Event() {
                                int dungtime = 16;
                                 public void execute(EventContainer dung) {
                                   if (dungtime == 16) {
                                       c.gfx0(2442);
                                        c.startAnimation(13190);
                                    }
                                   if (dungtime == 15) {
                                        c.npcId2 = 11228;
                                         c.isNpc = true;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                        c.startAnimation(13192);
                                    }
                                   if (dungtime == 10) {
                                        c.npcId2 = 11227;
                                         c.isNpc = true;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                        c.startAnimation(13193);
                                    }
                                   if (dungtime == 6) {
                                       c.gfx0(2442);
                                    }
                                   if (dungtime == 5) {
                                        c.npcId2 = 11229;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                        c.startAnimation(13194);
                                    }
                                   if (dungtime == 0) {
                                        c.isNpc = false;
                                         c.updateRequired = true;
                                        c.appearanceUpdateRequired = true;
                                    }
                                   if (c == null || dungtime <= 0) {
                                       dung.stop();
                                                                         return; 
                                    }
                                   if (dungtime >= 0) {
                                        dungtime--;
                                    }
                                }
                            }, 600);
                        }
		public void writePMLog(String data)
	{
		checkDateAndTime();	
		String filePath = "./Data/PMLogs/" + c.playerName + ".txt";
		BufferedWriter bw = null;
		
		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " " + checkTimeOfDay() + "]: " + "[" + c.connectedFrom + "]: " + "" + data + " ");
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}
	public void writeChatLog(String data)
	{
		checkDateAndTime();	
		String filePath = "./Data/ChatLogs/" + c.playerName + ".txt";
		BufferedWriter bw = null;
		
		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " " + checkTimeOfDay() + "]: " + "[" + c.connectedFrom + "]: " + "" + data + " ");
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}
	
	
	
	public void writeCommandLog(String command)
	{
		checkDateAndTime();	
		String filePath = "./Data/Commands2.txt";
		BufferedWriter bw = null;
		
		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " " + checkTimeOfDay() + "]: " 
				+ "[" + c.playerName + "]: " + "[" + c.connectedFrom + "] "
				 +  "::" + command);
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}

	public int getWearingAmount2() {
		int totalCash = 0;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if(c.playerEquipment[i] > 0) {
				totalCash += getItemValue(c.playerEquipment[i]);
			}
		}
        for (int i = 0; i < c.playerItems.length; i++) {
			if(c.playerItems[i] > 0) {
			    totalCash += getItemValue(c.playerItems[i]);
			}
        }
        return totalCash;
	}	

	public int getItemValue(int ItemID) {
		int shopValue = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					shopValue = (int) Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		return shopValue;
	}

	public int backupItems[] = new int[Config.BANK_SIZE];
	public int backupItemsN[] = new int[Config.BANK_SIZE];

	public void otherBank(Client c, Client o) {
		if(o == c || o == null || c == null)
		{
		return;
		}

		for (int i = 0; i < o.bankItems.length; i++)
		{
			backupItems[i] = c.bankItems[i]; backupItemsN[i] = c.bankItemsN[i];
			c.bankItemsN[i] = o.bankItemsN[i]; c.bankItems[i] = o.bankItems[i];
		}
			openUpBank();

		for (int i = 0; i < o.bankItems.length; i++)
		{
		c.bankItemsN[i] = backupItemsN[i]; c.bankItems[i] = backupItems[i];
		}
	}

	
	public void displayItemOnInterface(int frame, int item, int slot, int amount) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.outStream.createFrameVarSizeWord(34);
				c.outStream.writeWord(frame);
				c.outStream.writeByte(slot);
				c.outStream.writeWord(item + 1);
				c.outStream.writeByte(255);
				c.outStream.writeDWord(amount);
				c.outStream.endFrameVarSizeWord();
			}
		}
	}


	public void appendVengeanceNPC(int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		if (c.npcIndex > 0 && Server.npcHandler.npcs[c.npcIndex] != null) {
		  if (c.playerName.equalsIgnoreCase("limited brid")){
			c.forcedText = "I SUCK DICK!";
		    c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.vengOn = false;
		   }
			c.forcedText = "Taste Vengeance!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.vengOn = false;
			if ((Server.npcHandler.npcs[c.npcIndex].HP - damage) > 0) {
				damage = (int)(damage * 0.75);
				if (damage > Server.npcHandler.npcs[c.npcIndex].HP) {
					damage = Server.npcHandler.npcs[c.npcIndex].HP;
				}
				Server.npcHandler.npcs[c.npcIndex].HP -= damage;
				Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
				Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
				Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
			}
		}	
		c.updateRequired = true;
	}

	int tmpNWCY[] = new int[50];
	int tmpNWCX[] = new int[50];
	public void fmwalkto(int i, int j)
	{
		c.newWalkCmdSteps = 0;
		if(++c.newWalkCmdSteps > 50)
		c.newWalkCmdSteps = 0;
		int k = c.absX + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = c.absY + j;
		l -= c.mapRegionY * 8;
		c.isRunning2 = false;
		c.isRunning = false;
		c.getNewWalkCmdX()[0] += k;
		c.getNewWalkCmdY()[0] += l;
		c.poimiY = l;
		c.poimiX = k;
	}

	public String GetNpcName(int NpcID) {
		for (int i = 0; i < NPCHandler.maxListedNPCs; i++) {
			if (NPCHandler.NpcList[i] != null) {
				if (NPCHandler.NpcList[i].npcId == NpcID) {
					return NPCHandler.NpcList[i].npcName;
				}
			}
		}
		return "NPC Not Listed" + NpcID;
	}

	public void sendQuest(String s, int id) {
		try {
			c.outStream.createFrameVarSizeWord(126);
			c.outStream.writeString(s);
			c.outStream.writeWordA(id);
			c.outStream.endFrameVarSizeWord();
		} catch (Exception e) {
		}
	}

	public String checkTimeOfDay()
	{	
		Calendar cal = new GregorianCalendar();	
		int TIME_OF_DAY = cal.get(Calendar.AM_PM);		
		if (TIME_OF_DAY > 0)
			return "PM";
		else
			return "AM";
	}
	public void GWKC() {
		walkableInterface(16210);
		sendFrame126(""+c.Arma+"", 16216);
		sendFrame126(""+c.Band+"", 16217);
		sendFrame126(""+c.Sara+"", 16218);
		sendFrame126(""+c.Zammy+"", 16219);
	}
public void ResetGWKC() {
if(c.inGWD()) {
c.Arma = 0;
c.Band = 0;
c.Sara = 0;
c.Zammy = 0;
c.sendMessage("A magical force reseted your kill count!");
}
}
	
	public void checkDateAndTime()
	{
		Calendar cal = new GregorianCalendar();	
		
		int YEAR = cal.get(Calendar.YEAR);
		int MONTH = cal.get(Calendar.MONTH) + 1;
		int DAY = cal.get(Calendar.DAY_OF_MONTH);
		int HOUR = cal.get(Calendar.HOUR_OF_DAY);
		int MIN = cal.get(Calendar.MINUTE);
		int SECOND = cal.get(Calendar.SECOND);
		
		String day = "";
		String month = "";
		String hour = "";
		String minute = "";
		String second = "";
		
		if (DAY < 10)
			day = "0" + DAY;
		else 
			day = "" + DAY;
		if (MONTH < 10)
			month = "0" + MONTH;	
		else
			month = "" + MONTH;
		if (HOUR < 10)
			hour = "0" + HOUR;
		else 
			hour = "" + HOUR;
		if (MIN < 10)
			minute = "0" + MIN;
		else
			minute = "" + MIN;
		if (SECOND < 10)
			second = "0" + SECOND;
		else
			second = "" + SECOND;
			
		c.date = day + "/" + month + "/" + YEAR;	
		c.currentTime = hour + ":" + minute + ":" + second;
	}	
	Properties p = new Properties();
	
	public void loadAnnouncements()
	{
		try
		{
			loadIni();
		
			if (p.getProperty("ArmoredPkzAnnouncement1").length() > 0) {
				c.sendMessage(p.getProperty("ArmoredPkzPkzAnnouncement1"));
			}
			if (p.getProperty("ArmoredPkzAnnouncement2").length() > 0) {
				c.sendMessage(p.getProperty("ArmoredPkzAnnouncement2"));
			}
			if (p.getProperty("ArmoredPkzAnnouncement3").length() > 0) {
				c.sendMessage(p.getProperty("ArmoredPkzAnnouncement3"));
			}
		}
		catch (Exception e)
		{
		}
	}
	
	private void loadIni()
	{		
		try 
		{
			p.load(new FileInputStream("./Announcements.ini"));
		}
		catch (Exception e)
		{
		}
	}
	
	public int CraftInt, Dcolor, FletchInt;
	
	/**
	 * MulitCombat icon
	 * @param i1 0 = off 1 = on
	 */
	public void multiWay(int i1) {
		synchronized(c) {
			c.outStream.createFrame(61);
			c.outStream.writeByte(i1);
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
	}
	
	public void clearClanChat() {
		c.clanId = -1;
		c.inAclan = false;
		c.CSLS = 0;
		c.getPA().sendFrame126("chat: ", 18139);
		c.getPA().sendFrame126("Clan Chat Owner: ", 18140);
		for (int j = 18144; j < 18244; j++)
			c.getPA().sendFrame126("", j);
	}
	
	public void resetAutocast() {
		c.autocastId = -1;
		c.autocasting = false;
		c.setSidebarInterface(0, 328);
		c.getPA().sendFrame36(108, 0);
		c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
	}
	
	public void sendFrame126(String s, int id) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(126);
				c.getOutStream().writeString(s);
				c.getOutStream().writeWordA(id);
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}
	}
	
	
	public void sendLink(String s) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(187);
				c.getOutStream().writeString(s);
			}
		}	
	}
	
	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(134);
				c.getOutStream().writeByte(skillNum);
				c.getOutStream().writeDWord_v1(XP);
				c.getOutStream().writeByte(currentLevel);
				c.flushOutStream();
			}
		}
	}
public void totallevelsupdate() {
int totalLevel = (getLevelForXP(c.playerXP[0]) + getLevelForXP(c.playerXP[1]) + getLevelForXP(c.playerXP[2]) + getLevelForXP(c.playerXP[3]) + getLevelForXP(c.playerXP[4]) + getLevelForXP(c.playerXP[5]) + getLevelForXP(c.playerXP[6]) + getLevelForXP(c.playerXP[7]) + getLevelForXP(c.playerXP[8]) + getLevelForXP(c.playerXP[9]) + getLevelForXP(c.playerXP[10]) + getLevelForXP(c.playerXP[11]) + getLevelForXP(c.playerXP[12]) + getLevelForXP(c.playerXP[13]) + getLevelForXP(c.playerXP[14]) + getLevelForXP(c.playerXP[15]) + getLevelForXP(c.playerXP[16]) + getLevelForXP(c.playerXP[17]) + getLevelForXP(c.playerXP[18]) + getLevelForXP(c.playerXP[19]) + getLevelForXP(c.playerXP[20]) + getLevelForXP(c.playerXP[21]) + getLevelForXP(c.playerXP[22]));
		sendFrame126("Total Level: "+totalLevel, 7127);
}
	
	public void sendFrame106(int sideIcon) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(106);
				c.getOutStream().writeByteC(sideIcon);
				c.flushOutStream();
				requestUpdates();
			}
		}
	}
	
	public void sendFrame107() {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(107);
				c.flushOutStream();
			}
		}
	}
	public void sendFrame36(int id, int state) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(36);
				c.getOutStream().writeWordBigEndian(id);
				c.getOutStream().writeByte(state);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame185(int Frame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(185);
				c.getOutStream().writeWordBigEndianA(Frame);
			}
		}
	}
	
	public void showInterface(int interfaceid) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(97);
				c.getOutStream().writeWord(interfaceid);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame248(int MainFrame, int SubFrame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(248);
				c.getOutStream().writeWordA(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(246);
				c.getOutStream().writeWordBigEndian(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.getOutStream().writeWord(SubFrame2);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame171(int MainFrame, int SubFrame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(171);
				c.getOutStream().writeByte(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame200(int MainFrame, int SubFrame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(200);
				c.getOutStream().writeWord(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame70(int i, int o, int id) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(70);
				c.getOutStream().writeWord(i);
				c.getOutStream().writeWordBigEndian(o);
				c.getOutStream().writeWordBigEndian(id);
				c.flushOutStream();
			}
		}
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(75);
				c.getOutStream().writeWordBigEndianA(MainFrame);
				c.getOutStream().writeWordBigEndianA(SubFrame);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame164(int Frame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(164);
				c.getOutStream().writeWordBigEndian_dup(Frame);
				c.flushOutStream();
			}
		}
	}
	
	public void setPrivateMessaging(int i) { // friends and ignore list status
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
		        c.getOutStream().createFrame(221);
		        c.getOutStream().writeByte(i);
				c.flushOutStream();
			}
		}
    }
	
	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(206);
				c.getOutStream().writeByte(publicChat);	
				c.getOutStream().writeByte(privateChat);	
				c.getOutStream().writeByte(tradeBlock);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame87(int id, int state) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(87);
				c.getOutStream().writeWordBigEndian_dup(id);	
				c.getOutStream().writeDWord_v1(state);
				c.flushOutStream();
			}
		}
	}
	
	public void sendPM(long name, int rights, byte[] chatmessage, int messagesize) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSize(196);
				c.getOutStream().writeQWord(name);
				c.getOutStream().writeDWord(c.lastChatId++);
				c.getOutStream().writeByte(rights);
				c.getOutStream().writeBytes(chatmessage, messagesize, 0);
				c.getOutStream().endFrameVarSize();
				c.flushOutStream();
				String chatmessagegot = Misc.textUnpack(chatmessage, messagesize);
				String target = Misc.longToPlayerName(name);
			}	
		}
	}
	
	public void createPlayerHints(int type, int id) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(254);
				c.getOutStream().writeByte(type);
				c.getOutStream().writeWord(id); 
				c.getOutStream().write3Byte(0);
				c.flushOutStream();
			}
		}
	}

	public void createObjectHints(int x, int y, int height, int pos) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(254);
				c.getOutStream().writeByte(pos);
				c.getOutStream().writeWord(x);
				c.getOutStream().writeWord(y);
				c.getOutStream().writeByte(height);
				c.flushOutStream();
			}
		}
	}
	
	public void loadPM(long playerName, int world) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(world != 0) {
		            world += 9;
				} else if(!Config.WORLD_LIST_FIX) {
					world += 1;
				}	
				c.getOutStream().createFrame(50);
				c.getOutStream().writeQWord(playerName);
				c.getOutStream().writeByte(world);
				c.flushOutStream();
			}
		}
	}
	
	public void removeAllWindows() {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getPA().resetVariables();
				c.getOutStream().createFrame(219);
				c.flushOutStream();
			}
		}
	}
	
	public void closeAllWindows() {
		if(!c.isSmelting){
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
			//if (c.inPCC)
				//{
					//for (int i = 0; i < c.PCItems.length; i++)
				//	{
						//c.getItems().addItem(c.PCItems[i], c.PCItemsN[i]);
						//c.PCItems[i] = 0;
						//c.PCItemsN[i] = 0;
					//}
				//}
				//c.inPCC = false;
				c.getOutStream().createFrame(219);
				c.flushOutStream();
				c.isBanking = false;
				c.isShopping = false;
				c.getTradeAndDuel().declineTrade();
			}
		}
	}else{
	c.getOutStream().createFrame(219);
	}
	}
	
	
	public void sendFrame34(int id, int slot, int column, int amount) {
		
			if(c.getOutStream() != null && c != null) {
				c.outStream.createFrameVarSizeWord(34); // init item to smith screen
				c.outStream.writeWord(column); // Column Across Smith Screen
				c.outStream.writeByte(4); // Total Rows?
				c.outStream.writeDWord(slot); // Row Down The Smith Screen
				c.outStream.writeWord(id+1); // item
				c.outStream.writeByte(amount); // how many there are?
				c.outStream.endFrameVarSizeWord();
			}
		}
	
	
	public void Frame34(int frame, int item, int slot, int amount){
		
			if(c.getOutStream() != null && c != null) {
				c.outStream.createFrameVarSizeWord(34);
				c.outStream.writeWord(frame);
				c.outStream.writeByte(slot);
				c.outStream.writeWord(item+1);
				c.outStream.writeByte(255);
				c.outStream.writeDWord(amount);
				c.outStream.endFrameVarSizeWord();
			}
		
	}


	public void Summon(int frame, int item, int slot, int amount){
		
			if(c.getOutStream() != null && c != null) {
				c.outStream.createFrameVarSizeWord(34);
				c.outStream.writeWord(frame);
				c.outStream.writeByte(c.summoningslot);
				c.outStream.writeWord(item+1);
				c.outStream.writeByte(255);
				c.outStream.writeDWord(amount);
				c.outStream.endFrameVarSizeWord();
			}
		
	}
	
	public void walkableInterface(int id) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(208);
		        c.getOutStream().writeWordBigEndian_dup(id);
				c.flushOutStream();
			}
		}
	}
	
	public int mapStatus = 0;
	public void sendFrame99(int state) { // used for disabling map
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(mapStatus != state) {
					mapStatus = state;
					c.getOutStream().createFrame(99);
			        c.getOutStream().writeByte(state);
					c.flushOutStream();
				}
			}
		}
	}
	
	public void sendCrashFrame() { // used for crashing cheat clients
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(123);
				c.flushOutStream();
			}
		}
	}
	
	/**
	* Reseting animations for everyone
	**/

	public void frame1() {
		synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
					Client person = (Client)Server.playerHandler.players[i];
					if(person != null) {
						if(person.getOutStream() != null && !person.disconnected) {
							if(c.distanceToPoint(person.getX(), person.getY()) <= 25){	
								person.getOutStream().createFrame(1);
								person.flushOutStream();
								person.getPA().requestUpdates();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	* Creating projectile
	**/
	public void createProjectile(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time) {      
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
		        c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
		        c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
		        c.getOutStream().createFrame(117);
		        c.getOutStream().writeByte(angle);
		        c.getOutStream().writeByte(offY);
		        c.getOutStream().writeByte(offX);
		        c.getOutStream().writeWord(lockon);
		        c.getOutStream().writeWord(gfxMoving);
		        c.getOutStream().writeByte(startHeight);
		        c.getOutStream().writeByte(endHeight);
		        c.getOutStream().writeWord(time);
			    c.getOutStream().writeWord(speed);
				c.getOutStream().writeByte(16);
				c.getOutStream().writeByte(64);
				c.flushOutStream();
			}
		}
    }
	
	public void createProjectile2(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time, int slope) {      
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
		        c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
		        c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
		        c.getOutStream().createFrame(117);
		        c.getOutStream().writeByte(angle);
		        c.getOutStream().writeByte(offY);
		        c.getOutStream().writeByte(offX);
		        c.getOutStream().writeWord(lockon);
		        c.getOutStream().writeWord(gfxMoving);
		        c.getOutStream().writeByte(startHeight);
		        c.getOutStream().writeByte(endHeight);
		        c.getOutStream().writeWord(time);
			    c.getOutStream().writeWord(speed);
				c.getOutStream().writeByte(slope);
				c.getOutStream().writeByte(64);
				c.flushOutStream();
			}
		}
    }
	
	// projectiles for everyone within 25 squares
	public void createPlayersProjectile(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time) {
		synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){
								if (p.heightLevel == c.heightLevel)
									person.getPA().createProjectile(x, y, offX, offY, angle, speed, gfxMoving, startHeight, endHeight, lockon, time);
							}
						}
					}	
				}
			}
		}
	}
	
	public void createPlayersProjectile2(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time, int slope) {
		synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){	
								person.getPA().createProjectile2(x, y, offX, offY, angle, speed, gfxMoving, startHeight, endHeight, lockon, time, slope);	
							}
						}
					}	
				}
			}
		}
	}
	

	/**
	** GFX
	**/
	public void stillGfx(int id, int x, int y, int height, int time) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(y - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(x - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(4);
				c.getOutStream().writeByte(0);
				c.getOutStream().writeWord(id);
				c.getOutStream().writeByte(height);
				c.getOutStream().writeWord(time);
				c.flushOutStream();
			}
		}
	}
	
	//creates gfx for everyone
	public void createPlayersStillGfx(int id, int x, int y, int height, int time) {
		synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){	
								person.getPA().stillGfx(id, x, y, height, time);
							}
						}
					}	
				}
			}
		}
	}
	
	/**
	* Objects, add and remove
	**/
	public void object(int objectId, int objectX, int objectY, int face, int objectType) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(101);
				c.getOutStream().writeByteC((objectType<<2) + (face&3));
				c.getOutStream().writeByte(0);
			
				if (objectId != -1) { // removing
					c.getOutStream().createFrame(151);
					c.getOutStream().writeByteS(0);
					c.getOutStream().writeWordBigEndian(objectId);
					c.getOutStream().writeByteS((objectType<<2) + (face&3));
				}
				c.flushOutStream();
			}	
		}
	}
	
	public void checkObjectSpawn(int objectId, int objectX, int objectY, int face, int objectType) {
		if (c.distanceToPoint(objectX, objectY) > 60)
			return;
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(101);
				c.getOutStream().writeByteC((objectType<<2) + (face&3));
				c.getOutStream().writeByte(0);
			
				if (objectId != -1) { // removing
					c.getOutStream().createFrame(151);
					c.getOutStream().writeByteS(0);
					c.getOutStream().writeWordBigEndian(objectId);
					c.getOutStream().writeByteS((objectType<<2) + (face&3));
				}
				c.flushOutStream();
			}	
		}
	}
	

	/**
	* Show option, attack, trade, follow etc
	**/
	public String optionType = "null";
	public void showOption(int i, int l, String s, int a) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(!optionType.equalsIgnoreCase(s)) {
					optionType = s;
					c.getOutStream().createFrameVarSize(104);
					c.getOutStream().writeByteC(i);
					c.getOutStream().writeByteA(l);
					c.getOutStream().writeString(s);
					c.getOutStream().endFrameVarSize();
					c.flushOutStream();
				}
			}
		}
	}
	
	/**
	* Open bank
	**/
	public void openUpBank(){
			synchronized(c) {
				if(c.inWild())
				{
					c.sendMessage("You can't bank in the wilderness!");
					return;
				}
				if(c.getOutStream() != null && c != null) {
					c.getItems().resetItems(5064);
					c.getItems().rearrangeBank();
					c.getItems().resetBank();
					c.getItems().resetTempItems();
					c.getOutStream().createFrame(248);
					c.getOutStream().writeWordA(23000);
					c.getOutStream().writeWord(5063);
					c.flushOutStream();
				}
			}
		}
	
	/**
	* Private Messaging
	**/	
	public void logIntoPM() {
		setPrivateMessaging(2);
		for(int i1 = 0; i1 < Config.MAX_PLAYERS; i1++) {
			Player p = Server.playerHandler.players[i1];
			if(p != null && p.isActive) {
				Client o = (Client)p;
				if(o != null) {
					o.getPA().updatePM(c.playerId, 1);
				}
			}
		}
		boolean pmLoaded = false;

		for(int i = 0; i < c.friends.length; i++) {
			if(c.friends[i] != 0)  {
				for(int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
					Player p = Server.playerHandler.players[i2];
					if (p != null && p.isActive && Misc.playerNameToInt64(p.playerName) == c.friends[i])  {
						Client o = (Client)p;
						if(o != null) {
							if (c.playerRights >= 2 || p.privateChat == 0 || (p.privateChat == 1 && o.getPA().isInPM(Misc.playerNameToInt64(c.playerName)))) {
			 		 			loadPM(c.friends[i], 1);
			 		 			pmLoaded = true;
							}
							break;
						}
					}
				}
				if(!pmLoaded) {	
					loadPM(c.friends[i], 0);
				}
				pmLoaded = false;
			}
			for(int i1 = 1; i1 < Config.MAX_PLAYERS; i1++) {
				Player p = Server.playerHandler.players[i1];
    			if(p != null && p.isActive) {
					Client o = (Client)p;
					if(o != null) {
						o.getPA().updatePM(c.playerId, 1);
					}
				}
			}
		}
	}
	
	
	public void updatePM(int pID, int world) { // used for private chat updates
		Player p = Server.playerHandler.players[pID];
		if(p == null || p.playerName == null || p.playerName.equals("null")){
			return;
		}
		Client o = (Client)p;
		if(o == null) {
			return;
		}
        long l = Misc.playerNameToInt64(Server.playerHandler.players[pID].playerName);

        if (p.privateChat == 0) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i]) {
                        loadPM(l, world);
                        return;
                    }
                }
            }
        } else if (p.privateChat == 1) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i]) {
                        if (o.getPA().isInPM(Misc.playerNameToInt64(c.playerName))) {
                            loadPM(l, world);
                            return;
                        } else {
                            loadPM(l, 0);
                            return;
                        }
                    }
                }
            }
        } else if (p.privateChat == 2) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i] && c.playerRights < 2) {
                        loadPM(l, 0);
                        return;
                    }
                }
            }
        }
    }
	
	public boolean isInPM(long l) {
        for (int i = 0; i < c.friends.length; i++) {
            if (c.friends[i] != 0) {
                if (l == c.friends[i]) {
                    return true;
                }
            }
        }
        return false;
    }

	
	
	/**
	 * Drink AntiPosion Potions
	 * @param itemId The itemId
	 * @param itemSlot The itemSlot
	 * @param newItemId The new item After Drinking
	 * @param healType The type of poison it heals
	 */
	public void potionPoisonHeal(int itemId, int itemSlot, int newItemId, int healType) {
		c.attackTimer = c.getCombat().getAttackDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
		if(c.duelRule[5]) {
			c.sendMessage("Potions has been disabled in this duel!");
			return;
		}
		if(!c.isDead && System.currentTimeMillis() - c.foodDelay > 2000) {
			if(c.getItems().playerHasItem(itemId, 1, itemSlot)) {
				c.sendMessage("You drink the "+c.getItems().getItemName(itemId).toLowerCase()+".");
				c.foodDelay = System.currentTimeMillis();
				// Actions
				if(healType == 1) {
					//Cures The Poison
				} else if(healType == 2) {
					//Cures The Poison + protects from getting poison again
				}
				c.startAnimation(0x33D);
				c.getItems().deleteItem(itemId, itemSlot, 1);
				c.getItems().addItem(newItemId, 1);
				requestUpdates();
			}
		}
	}
	
	
	/**
	* Magic on items
	**/
	
	public void magicOnItems(int slot, int itemId, int spellId) {
		switch(spellId) {
			case 1162: // low alch
			if(System.currentTimeMillis() - c.alchDelay > 1000) {	
				if(!c.getCombat().checkMagicReqs(49)) {
					break;
				}
				if(itemId == 995) {
					c.sendMessage("You can't alch coins.");
					break;
				}
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995, c.getShops().getItemShopValue(itemId)/3);
				c.startAnimation(c.MAGIC_SPELLS[49][2]);
				c.gfx100(c.MAGIC_SPELLS[49][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[49][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;
			
			case 1178: // high alch
			if(System.currentTimeMillis() - c.alchDelay > 2000) {	
				if(!c.getCombat().checkMagicReqs(50)) {
					break;
				}
				if(itemId == 995) {
					c.sendMessage("You can't alch coins.");
					break;
				}				
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995, (int)(c.getShops().getItemShopValue(itemId)*.75));
				c.startAnimation(c.MAGIC_SPELLS[50][2]);
				c.gfx100(c.MAGIC_SPELLS[50][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[50][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;
			case 8349:
				handleAlt(itemId);
			break;
		}
	}
	
	/**
	* Dieing
	**/

	
    public String getKM() {
		int kMCount = Misc.random(11);
		switch (kMCount) {
			case 0: return "With a crushing blow, you defeat "+ c.playerName+".";
			case 1: return "It's a humiliating defeat for "+ c.playerName+".";
			case 2: return c.playerName +" didn't stand a chance against you.";
			case 3: return "You've defeated "+ c.playerName+".";
			case 4: return c.playerName +" regrets the day they met you in combat.";
			case 5: return "It's all over for "+c.playerName+".";
			case 6: return c.playerName +" falls before you might.";
			case 7: return "Can anyone defeat you? Certainly not "+c.playerName+".";
			case 8: return c.playerName +" has fallen under your might.";
			case 9: return c.playerName +" crumbled under your power.";
			case 10: return "You have proven "+ c.playerName+" to be an unworthy opponent.";
			default: return "You were clearly a better fighter than "+c.playerName+".";
		}
	}
private static int[][] xEP = {{15015,1},{14883,1},{14880,1},{14880,1},{14878,1},{14878,1},{14880,1},{14882,1},{868,50},{14885,1},{14890,1},{14886,1},{14888,1},{1099,1},{1165,1},{1351,1},{1319,1},{1333,1},{1359,1},{1149,1},{1185,1},{1704,1},{157,3},{145,2},{175,4},{995,14700},{995,22500},{14886,1},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},{4720,1},{4722,1},{4736,1},{4738,1},{4749,1},{4751,1},{4675,1},{4091,1},{4093,1},{4095,1},{4097,1},{4101,1},{4103,1},{4105,1},{15013,1},{4107,1},{4111,1},{4113,1},{4115,1},{4117,1},{4131,1},{1079,1},{1093,1},{1127,1},
		{1163,1},{1201,1},{14889,1},{14880,1},{14886,1},{14885,1},{14891,1},{14877,1},{14882,1},{14892,1},{14876,1},{4587,1},{14891,1},{14884,1},{1340,1},{1725,1},{1729,1},{1731,1},{1149,1},{1351,1},{1319,1},{1333,1},{1359,1},{15007,1},{995,5815},{995,300000},{1085,1},{1089,1},{1351,1},{1079,1},{14886,1},{15009,1},{14877,1},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},{4720,1},{4722,1},{4736,1},{4738,1},{4749,1},{4751,1},{14876,1},{14881,1},{15012,1},{1127,1},{1093,1},{4087,1},{4585,1},{3140,1},{6737,1},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},
		{4720,1},{14878,1},{14879,1},{14890,1},{14876,1},{4722,1},{4736,1},{14887,1},{14892,1},{14877,1},{14879,1},{4738,1},{1704,1},{157,3},{1379,1},{1381,1},{1393,1},{861,1},{145,2},{175,4},{4749,1},{4751,1},{11732,1},
		{1340,1},{1725,1},{14877,1},{1729,1},{14880,1},{14877,1},{14892,1},{14892,1},{14886,1},{14881,1},{14887,1},{14889,1},{1731,1},{11235,1},{1079,1},{1127,1},{1099,1},{1165,1},{1149,1},{1351,1},{1319,1},{1333,1},{1359,1},{1379,1},{1381,1},{1393,1},{861,1},{1185,1},{1093,1},{4087,1},{4585,1},{3140,1},{995,5815},{995,300000},{1085,1},{1089,1},{1340,1},{1725,1},{1729,1},{1731,1},{1351,1},{4675,1},{1340,1},{1725,1},{1729,1},{1351,1},{1319,1},{1333,1},{1359,1},{1731,1},{1351,1},{14879,1},{1319,1},{1333,1},{1359,1},{4091,1},{4093,1},{4095,1},{4097,1},{4101,1},{4103,1},{1340,1},{1725,1},{1729,1},{1731,1},{4105,1},{14886,1},{4107,1},{4111,1},{4113,1},{4115,1},{4117,1},{4131,1},{1079,1},{1093,1},{1127,1},{1163,1},{1201,1},{4587,1},{1149,1},{6737,1},{6731,1},{11335,1},{11212,40},{4151,1},{6585,1},{1187,1},{4675,1},{1351,1},{1319,1},{1333,1},{1359,1},
		{15018,1},{1099,1},{14890,1},{14882,1},{14881,1},{14880,1},{14881,1},{14888,1},{14876,1},{14878,1},{14878,1},{14879,1},{14890,1},{14889,1},{14890,1},{1165,1},{14883,1},{14885,1},{1149,1},{1185,1},{14876,1},{15020,1},{995,5815},{1351,1},{1319,1},{1333,1},{1359,1},{995,300000},{1085,1},{1089,1},{1351,1},{13901,1},{13879,1},{13883,1},{13904,1},{13907,1},{13872,1},{13878,1},{13860,1},{13863,1},{13866,1},{13886,1},{13892,1},{13898,1},{13889,1},{13894,1}};
	
    public void applyDead() { 
		c.respawnTimer = 15;
		c.isDead = false;
		walkableInterface(18483);
		Client o = (Client) Server.playerHandler.players[c.killerId];
		Client c2 = (Client)Server.playerHandler.players[c.killerId];
		c.getPA().sendFrame126(":quicks:off", -1);
		if(c.duelStatus != 6) {
			c.killerId = findKiller();
			if(o != null) {
				c.playerKilled = c.playerId;
				if(o.duelStatus == 5) {
					o.duelStatus++;
				}
				if (Server.playerHandler.players[c.killerId].connectedFrom == Server.playerHandler.players[c.playerKilled].connectedFrom) {
		c.faceUpdate(0);
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.stopMovement();
		if(c.duelStatus <= 4) {
			c.sendMessage("Oh dear you are dead!");
			c.killStreak = 0;

		} else if(c.duelStatus != 6) {
			c.sendMessage("You have lost the duel!");
		}
		resetDamageDone();
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		// sendFrame126("PkP: "+c.pkPoints+" K: "+c.kills+" D: "+c.deaths+"", 663);  //demise sendframe
		c.tradeResetNeeded = true;
		return;
		}
		if (Server.playerHandler.players[c.killerId].connectedFrom.equals(Server.playerHandler.players[c.playerKilled].connectedFrom)) {
					o.sendMessage("Multiple IP detected, PK point reward removed.");
		c.faceUpdate(0);
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.stopMovement();
		if(c.duelStatus <= 4) {
			c.sendMessage("Oh dear you are dead!");///Pk One
			c.killStreak = 0;

		} else if(c.duelStatus != 6) {
			c.sendMessage("You have lost the duel!");
		}
		resetDamageDone();
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		//sendFrame126("PkP: "+c.pkPoints+" K: "+c.kills+" D: "+c.deaths+"", 663); demise sendframe
		c.tradeResetNeeded = true;
		return;
		}
				if (Server.playerHandler.players[c.playerId].connectedFrom != o.lastKilled && c.duelStatus == 0) {
					o.pkPoints = (o.pkPoints + 10);
					//o.SPoints += 10;
					o.sendMessage("You recieved 10 PkPoints");
					o.sendMessage("You have defeated " +Misc.optimizeText(c.playerName)+ "!");
					o.getPA().addSkillXP(9000, 23);
						
				if (o.earningPotential >= 85) {
				o.earningPotential -= 40 + Misc.random(50);
				int random = (int)(Math.random() * (xEP.length - 1));
				Server.itemHandler.createGroundItem(o, xEP[random][0], c.absX, c.absY, 
											xEP[random][1], o.playerId);
											o.sendMessage("You recieved an EP drop.");
				o.sendMessage("Your EP decreased to: "+c.earningPotential+".");
				}
					if (o.earningPotential >= 85) {
				o.earningPotential -= 40 + Misc.random(50);
				int random = (int)(Math.random() * (xEP.length - 1));
				Server.itemHandler.createGroundItem(o, xEP[random][0], c.absX, c.absY, 
											xEP[random][1], c.playerId);
				o.sendMessage("Your EP decreased to: "+c.earningPotential+".");
}
				}
			}
		}
		c.faceUpdate(0);
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.stopMovement();
					if (c.duelStatus <= 4) {
				c.sendMessage("Oh dear you are dead!");
				c.killStreak = 0;
				c.getPA().addSkillXP(-4000, 23);
			} else if(c.duelStatus != 6 || !c.inArena()) {
				c.sendMessage("You have lost the duel!");
				/*o.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);*/
			}
		resetDamageDone();
		c.DC++;
		c.KC++;	
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
		if (Server.playerHandler.players[c.killerId].connectedFrom.equals(Server.playerHandler.players[c.playerKilled].connectedFrom)) {
					o.sendMessage("You Don't Recieve PK Points for killing yourself!");
		c.faceUpdate(0);
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.stopMovement();
		if(c.duelStatus <= 4) {
			c.sendMessage("Oh dear you are dead!");
			c.killStreak = 0;

			
		} else if(c.duelStatus != 6) {
			c.sendMessage("You have lost the duel!");
		}
		resetDamageDone();
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		c.tradeResetNeeded = true;
		return;
		}
	}
	
			public void dropitems() 
	{
	if(c.lastsummon > 0) {
if(c.totalstored > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.storing = false;
c.sendMessage("Your BoB items have drop on the floor");
}	
	}
	/**
	* Location change for digging, levers etc
	**/
	
	
	public void resetDamageDone() {
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				PlayerHandler.players[i].damageTaken[c.playerId] = 0;			
			}		
		}	
	}
	/*
*Vengeance
*/
	public void castVeng() {
	if(c.playerLevel[6] < 94) {
		c.sendMessage("You need a magic level of 94 to cast this spell.");
		return;
	}
	if(c.playerLevel[1] < 40) {
		c.sendMessage("You need a defence level of 40 to cast this spell.");
		return;
	}
	if(!c.getItems().playerHasItem(9075, 4) || !c.getItems().playerHasItem(557, 10) || !c.getItems().playerHasItem(560, 2)) {
		c.sendMessage("You don't have the required runes to cast this spell.");
		return;
	}
	if(System.currentTimeMillis() - c.lastCast < 30000) {
		c.sendMessage("You can only cast vengeance every 30 seconds.");
		return;
	}
	if(c.vengOn) {
		c.sendMessage("You already have vengeance casted.");
		return;
	}
	c.startAnimation(4410);
	c.gfx100(726);//Just use c.gfx100
	c.getItems().deleteItem2(9075, 4);
	c.getItems().deleteItem2(557, 10);//For these you need to change to deleteItem(item, itemslot, amount);.
	c.getItems().deleteItem2(560, 2);
	addSkillXP(2000, 6);
	c.stopMovement();
	refreshSkill(6);
	c.vengOn = true;
	c.lastCast = System.currentTimeMillis();
	}

	public void vengOther() {	
	if (c.playerIndex > 0) {	
	Player q = Server.playerHandler.players[c.playerIndex];			
	final int oX = q.getX();
	final int oY = q.getY();
	if(c.playerLevel[6] < 93) {
		c.sendMessage("You need a magic level of 93 to cast this spell.");
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.turnPlayerTo(oX,oY);
		return;
	}
                if (!q.acceptAid) {
                c.sendMessage("This player has their accept Aid off, therefore you cannot veng them!");
                return;
                }

	if(c.playerLevel[1] < 40) {
		c.sendMessage("You need a defence level of 40 to cast this spell.");
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.turnPlayerTo(oX,oY);
		return;
	}
	if(!c.getItems().playerHasItem(9075, 3) || !c.getItems().playerHasItem(557, 10) || !c.getItems().playerHasItem(560, 2)) {
		c.sendMessage("You don't have the required runes to cast this spell.");
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.turnPlayerTo(oX,oY);
		return;
	}
	if(System.currentTimeMillis() - c.lastCast < 30000) {
		c.sendMessage("You can only cast vengeance every 30 seconds.");
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.turnPlayerTo(oX,oY);
		return;
	}
	if(q.vengOn) {
		c.sendMessage("That player already have vengeance casted.");
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.turnPlayerTo(oX,oY);
		return;
	}
	c.startAnimation(4411);
	q.gfx100(725);//Just use c.gfx100
	c.getItems().deleteItem2(9075, 3);
	c.getItems().deleteItem2(557, 10);//For these you need to change to deleteItem(item, itemslot, amount);.
	c.getItems().deleteItem2(560, 2);
	q.vengOn = true;
	addSkillXP(2000, 6);
	c.turnPlayerTo(oX,oY);
	refreshSkill(6);
	c.getCombat().resetPlayerAttack();
	c.stopMovement();
	c.lastCast = System.currentTimeMillis();
}
}
public boolean wearingCape(int cape) {
int capes[] = {
9747, 9748, 9750, 9751,
9753, 9754, 9756, 9757,
9759, 9760, 9762, 9763,
9765, 9766, 9768, 9769,
9771, 9772, 9774, 9775,
9777, 9778, 9780, 9781,
9783, 9784, 9786, 9787,
9789, 9790, 9792, 9793,
9795, 9796, 9798, 9799,
9801, 9802, 9804, 9805,
9807, 9808, 9810, 9811,
9813, 9948, 9949, 12170
};
for(int i = 0; i < capes.length; i++) {
if(capes[i] == cape) {
return true;
}
}
return false;
    }
	public void vengMe() {
		if (System.currentTimeMillis() - c.lastVeng > 30000) {
			if (c.getItems().playerHasItem(557,10) && c.getItems().playerHasItem(9075,4) && c.getItems().playerHasItem(560,2)) {
				c.vengOn = true;
				c.lastVeng = System.currentTimeMillis();
				c.startAnimation(4410);
				c.gfx100(726);
				c.getItems().deleteItem(557,c.getItems().getItemSlot(557),10);
				c.getItems().deleteItem(560,c.getItems().getItemSlot(560),2);
				c.getItems().deleteItem(9075,c.getItems().getItemSlot(9075),4);
			} else {
				c.sendMessage("You do not have the required runes to cast this spell. (9075 for astrals)");
			}
		} else {
			c.sendMessage("You must wait 30 seconds before casting this again.");
		}
	}
	
	public int skillcapeGfx(int cape) {
		int capeGfx[][] = {
			{9747, 823}, {9748, 823},
			{9750, 828}, {9751, 828},
			{9753, 824}, {9754, 824},
			{9756, 832}, {9757, 832},
			{9759, 829}, {9760, 829},
			{9762, 813}, {9763, 813},
			{9765, 817}, {9766, 817},
			{9768, 833}, {9769, 833},
			{9771, 830}, {9772, 830},
			{9774, 835}, {9775, 835},
			{9777, 826}, {9778, 826},
			{9780, 818}, {9781, 818},
			{9783, 812}, {9784, 812},
			{9786, 827}, {9787, 827},
			{9789, 820}, {9790, 820},
			{9792, 814}, {9793, 814},
			{9795, 815}, {9796, 815},
			{9798, 819}, {9799, 819},
			{9801, 821}, {9802, 821},
			{9804, 831}, {9805, 831},
			{9807, 822}, {9808, 822},
			{9810, 825}, {9811, 825},
			{9948, 907}, {9949, 907},
			{9813, 816}, {12170, 1515}
		};
		for(int i = 0; i < capeGfx.length; i++) {
			if(capeGfx[i][0] == cape) {
				return capeGfx[i][1];
			}
		}
		return -1;
	}
	
	public int skillcapeEmote(int cape) {
		int capeEmote[][] = {
			{9747, 4959}, {9748, 4959},
			{9750, 4981}, {9751, 4981},
			{9753, 4961}, {9754, 4961},
			{9756, 4973}, {9757, 4973},
			{9759, 4979}, {9760, 4979},
			{9762, 4939}, {9763, 4939},
			{9765, 4947}, {9766, 4947},
			{9768, 4971}, {9769, 4971},
			{9771, 4977}, {9772, 4977},
			{9774, 4969}, {9775, 4969},
			{9777, 4965}, {9778, 4965},
			{9780, 4949}, {9781, 4949},
			{9783, 4937}, {9784, 4937},
			{9786, 4967}, {9787, 4967},
			{9789, 4953}, {9790, 4953},
			{9792, 4941}, {9793, 4941},
			{9795, 4943}, {9796, 4943},
			{9798, 4951}, {9799, 4951},
			{9801, 4955}, {9802, 4955},
			{9804, 4975}, {9805, 4975},
			{9807, 4957}, {9808, 4957},
			{9810, 4963}, {9811, 4963},
			{9948, 5158}, {9949, 5158},
			{9813, 4945}, {12170, 8525}
		};
		for(int i = 0; i < capeEmote.length; i++) {
			if(capeEmote[i][0] == cape) {
				return capeEmote[i][1];
			}
		}
		return -1;
	}

	public void resetTb() {
		c.teleBlockLength = 0;
		c.teleBlockDelay = 0;	
	}
	
	public void handleStatus(int i, int i2, int i3) {
		//Sanity u so smart
	}
	
	public void resetFollowers() {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				if (Server.playerHandler.players[j].followId == c.playerId) {
					Client c = (Client)Server.playerHandler.players[j];
					c.getPA().resetFollow();
				}			
			}		
		}	
	}
	
	public void giveLife() {
		c.isDead = false;
		c.faceUpdate(-1);
		c.freezeTimer = 0;
		
		
		if(c.playerRights == 3) {
		for (int i = 0; i < 20; i++) {
			c.playerLevel[i] = getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		

		c.getCombat().resetPrayers();

		c.teleportToX = 3300;
		c.teleportToY = 3300;
PlayerSave.saveGame(c);
c.getPA().resetTzhaar();
requestUpdates();
return;
		
		}
		if(c.duelStatus <= 4 && !c.getPA().inPitsWait()) { // if we are not in a duel we must be in wildy so remove items
			if (!c.inPits && !c.inFightCaves() && !c.inPcGame() && !c.inZombieCaves() && !c.inFunPk()) {
					c.getItems().resetKeepItems();
				if((c.playerRights == 2 && Config.ADMIN_DROP_ITEMS) || c.playerRights != 2) {
					if(!c.isSkulled && !c.isInArd()) {	// what items to keep
						c.getItems().keepItem(0, true);
						c.getItems().keepItem(1, true);	
						c.getItems().keepItem(2, true);
					}	
					if(c.prayerActive[10] || c.curseActive[0] && System.currentTimeMillis() - c.lastProtItem > 700) {
						c.getItems().keepItem(3, true);
					}
					c.getItems().dropAllItems(); // drop all items
					c.getItems().deleteAllItems(); // delete all items
				
					if(!c.isSkulled && !c.isInArd()) { // add the kept items once we finish deleting and dropping them	
						for (int i1 = 0; i1 < 3; i1++) {
							if(c.itemKeptId[i1] > 0) {
								c.getItems().addItem(c.itemKeptId[i1], 1);
							}
						}
					}	
					if(c.prayerActive[10] || c.isInArd()) { // if we have protect items 
						if(c.itemKeptId[3] > 0) {
							c.getItems().addItem(c.itemKeptId[3], 1);
						}
					}
				}
				c.getItems().resetKeepItems();
			} else if (c.inPits) {
				Server.fightPits.removePlayerFromPits(c.playerId);
				c.pitsStatus = 1;
				c.duelStatus = 0;
			}
		}
		c.getCombat().resetPrayers();
		for (int i = 0; i < 25; i++) {
			c.playerLevel[i] = getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		if (c.playerEquipment[c.playerRing] == 2570) {
			if (c.playerLevel[3] > 0 && c.playerLevel[3] <= c.getLevelForXP(c.playerXP[3]) / 10 && c.underAttackBy > 0) {
				int wildlvl = (((c.absY - 3520) / 8) + 1);
				if (wildlvl < 20) {
					c.getItems().deleteEquipment(2570, c.playerRing);
			c.getPA().startTeleport(2834, 3335, 0, "modern");
			}
		}
	}
		if (c.pitsStatus == 1) {
			c.pitsStatus = 0;
			movePlayer(2399, 5173, 0);
		} else if(c.duelStatus <= 4) { // if we are not in a duel repawn to wildy
movePlayer(c.SETHOMEX, c.SETHOMEY, 0);
c.isSkulled = false;
c.skullTimer = 0;
c.attackedPlayers.clear(); 
		} else if (c.inFightCaves()) {
			c.getPA().resetTzhaar();
		} else if (c.inZombieCaves()) {
			c.getPA().resetZombies();
		} else { // we are in a duel, respawn outside of arena
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o != null) {
				o.getPA().createPlayerHints(10, -1);
				if(o.duelStatus == 6) {
					o.getTradeAndDuel().duelVictory();
				}
			}
			movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
			if(c.duelStatus != 6) { // if we have won but have died, don't reset the duel status.
				c.getTradeAndDuel().resetDuel();
			}
		}
		//PlayerSaving.getSingleton().requestSave(c.playerId);
		PlayerSave.saveGame(c);
		c.getCombat().resetPlayerAttack();
		resetAnimation();
		c.startAnimation(-1);
		frame1();
		resetTb();
		c.isSkulled = false;
		c.attackedPlayers.clear();
		c.headIconPk = -1;
		c.skullTimer = -1;
		c.damageTaken = new int[Config.MAX_PLAYERS];
		c.getPA().requestUpdates();
		removeAllWindows();
		c.tradeResetNeeded = true;	


	}
		
	/**
	* Location change for digging, levers etc
	**/
	
	public void changeLocation() {
		switch(c.newLocation) {
			case 1:
			sendFrame99(2);
			movePlayer(3578,9706,-1);
			break;
			case 2:
			sendFrame99(2);
			movePlayer(3568,9683,-1);
			break;
			case 3:
			sendFrame99(2);
			movePlayer(3557,9703,-1);
			break;
			case 4:
			sendFrame99(2);
			movePlayer(3556,9718,-1);
			break;
			case 5:
			sendFrame99(2);
			movePlayer(3534,9704,-1);
			break;
			case 6:
			sendFrame99(2);
			movePlayer(3546,9684,-1);
			break;
			case 7:
			sendFrame99(2);
			movePlayer(3546,9684,-1);
			break;
		}
		c.newLocation = 0;
	}
	
	
	/**
	* Teleporting
	**/
	public void spellTeleport(int x, int y, int height) {
		c.getPA().startTeleport(x, y, height, c.playerMagicBook == 1 ? "ancient" : "modern");
	}
	public void startMovement(int x, int y, int height) {
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You cannot teleport out of Dungeoneering!.");
			return;
		}
		if(c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if(c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if(c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if(c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return;
		}
		if(c.inGWD()) {
		ResetGWKC();
		}
		if(c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0)
				c.getCombat().resetPlayerAttack();
			c.stopMovement();
			EarningPotential.checkTeleport(c);
			removeAllWindows();			
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;

			}
		
		
	}	
	public void startTeleport(int x, int y, int height, String teleportType) {
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL && !c.inFunPk()) {
                        c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
                        return;
        }
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You cannot teleport out of Dungeoneering!.");
			return;
		}
		if(c.inPits || c.viewingOrb || inPitsWait()) {
			c.sendMessage("You can't teleport in here!");
			return;
		}
		if(c.inGWD()) {
		ResetGWKC();
		}
		if(c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking fool!");
			return;
		}
		if(c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking goon!");
			return;
		}
		if(c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return;
		}
		if(c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if(c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(c.inWild() && !c.getItems().playerHasItem(563, 0)) {
			c.sendMessage("You must have a law rune to teleport out of the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0)
				c.getCombat().resetPlayerAttack();
			c.stopMovement();
			EarningPotential.checkTeleport(c);
			removeAllWindows();			
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
 if(teleportType.equalsIgnoreCase("modern")) {
				c.startAnimation(8939);
				c.teleTimer = 9;
				c.gfx0(1576);
				c.teleEndAnimation = 8941;
			} 

			if(teleportType.equalsIgnoreCase("dungtele")) {
			        c.startAnimation(13652);
				c.teleTimer = 16;
				c.gfx0(2602);
				c.teleEndAnimation = 13654;
			}
                        
                        if(teleportType.equalsIgnoreCase("ancient")) {
				c.startAnimation(1979);
				c.teleGfx = 0;
				c.teleTimer = 11;
				c.teleEndAnimation = 0;
				c.gfx0(1681);
			}
			
		}
	}
	public void startTeleport2(int x, int y, int height) {
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You cannot teleport out of Dungeoneering!.");
			return;
		}
		if(c.inGWD()) {
		ResetGWKC();
		}
		if(c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking goon!");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(!c.isDead && c.teleTimer == 0) {			
			c.stopMovement();
			removeAllWindows();			
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			c.startAnimation(714);
			c.teleTimer = 11;
			c.teleGfx = 308;
			c.teleEndAnimation = 715;
			
		}
	} 

	public void processTeleport() {
		c.teleportToX = c.teleX;
		c.teleportToY = c.teleY;
		c.heightLevel = c.teleHeight;
		if(c.teleEndAnimation > 0) {
			c.startAnimation(c.teleEndAnimation);
		}
	}
		
	public void movePlayer(int x, int y, int h) {
		c.resetWalkingQueue();
		c.teleportToX = x;
		c.teleportToY = y;
		c.heightLevel = h;
		requestUpdates();
	}
	
	/**
	* Following
	**/
	
	/*public void Player() {
		if(Server.playerHandler.players[c.followId] == null || Server.playerHandler.players[c.followId].isDead) {
			c.getPA().resetFollow();
			return;
		}		
		if(c.freezeTimer > 0) {
			return;
		}
		int otherX = Server.playerHandler.players[c.followId].getX();
		int otherY = Server.playerHandler.players[c.followId].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 6);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean sameSpot = (c.absX == otherX && c.absY == otherY);
		if(!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			c.getPA().resetFollow();
			return;
		}
		c.faceUpdate(c.followId+32768);
		if ((c.usingBow || c.mageFollow || c.autocastId > 0 && (c.npcIndex > 0 || c.playerIndex > 0)) && bowDistance && !sameSpot) {
			c.stopMovement();
			return;
		}	
		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot && (c.npcIndex > 0 || c.playerIndex > 0)) {
			c.stopMovement();
			return;
		}	
		if(c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1) && !sameSpot) {
			return;
		}
		c.outStream.createFrame(174);
		boolean followPlayer = c.followId > 0;
		if (c.freezeTimer <= 0)
			if (followPlayer)
				c.outStream.writeWord(c.followId);
			else 
				c.outStream.writeWord(c.followId2);
		else
			c.outStream.writeWord(0);
		
		if (followPlayer)
			c.outStream.writeByte(1);
		else
			c.outStream.writeByte(0);
		if (c.usingBow && c.playerIndex > 0)
			c.followDistance = 5;
		else if (c.usingRangeWeapon && c.playerIndex > 0)
			c.followDistance = 3;
		else if (c.spellId > 0 && c.playerIndex > 0)
			c.followDistance = 5;
		else
			c.followDistance = 1;
		c.outStream.writeWord(c.followDistance);
	}*/
	
	public void followPlayer(int i) {
		if(Server.playerHandler.players[c.followId] == null || Server.playerHandler.players[c.followId].isDead) {
			c.followId = 0;
			return;
		}		
		if(c.freezeTimer > 0) {
			return;
		}
		if(c.inWG()) {
			c.followId = 0;
			return;
		}
		if(c.inDuelArena()) {
			c.followId = 0;
			return;
		}
		if(inPitsWait()) {
			c.followId = 0;
		}
		if(c.InDung()) {
                        c.sendMessage("you cannot follow in Dungoneering!");
                        c.followId = 0;
                        return;
                }
                if(c.inJail() && c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if(c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0)
			return;
		
		int otherX = Server.playerHandler.players[c.followId].getX();
		int otherY = Server.playerHandler.players[c.followId].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean goodDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if(!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			return;
		}
		if(c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1)) {
			if (otherX != c.getX() && otherY != c.getY()) {
				stopDiagonal(otherX, otherY);
				return;
			}
		}
		
		if((c.usingBow || c.mageFollow || (c.playerIndex > 0 && c.autocastId > 0)) && bowDistance && !sameSpot) {
			return;
		}

		if(c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if(c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}
		
		c.faceUpdate(c.followId+32768);
		
		if (otherY == c.getY() && otherX == c.getX()) {			
			switch(Misc.random(3))  {
				case 0:
				walkTo(0, getMove(c.getX(), otherX - 1));
				break;
				
				case 1:
				walkTo(0, getMove(c.getX(), otherX + 1));
				break;
				
				case 2:
				walkTo(0, getMove(c.getY(), otherY + 1));
				break;
				
				case 3:
				walkTo(0, getMove(c.getY(), otherY - 1));
				break;
			}
		}		
		if(c.isRunning2 && !withinDistance) {
			if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} 		
		} else {
			if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY + 1));
			} 
		}
	}
	
	public void followNpc() {
		if(Server.npcHandler.npcs[c.followId2] == null || Server.npcHandler.npcs[c.followId2].isDead) {
			c.followId2 = 0;
			return;
		}		
		if(c.freezeTimer > 0) {
			return;
		}
		if(c.inWG()) {
			c.followId = 0;
			return;
		}
		if(c.inDuelArena()) {
			c.followId = 0;
			return;
		}
		if(c.inJail() && c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if(c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0)
			return;
		
		int otherX = Server.npcHandler.npcs[c.followId2].getX();
		int otherY = Server.npcHandler.npcs[c.followId2].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean goodDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if(!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId2 = 0;
			return;
		}
		if(c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1)) {
			if (otherX != c.getX() && otherY != c.getY()) {
				stopDiagonal(otherX, otherY);
				return;
			}
		}
		
		if((c.usingBow || c.mageFollow || (c.npcIndex > 0 && c.autocastId > 0)) && bowDistance && !sameSpot) {
			return;
		}

		if(c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if(c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}
		
		c.faceUpdate(c.followId2);
		if (otherX == c.absX && otherY == c.absY) {
			int r = Misc.random(3);
			switch (r) {
				case 0:
					walkTo(0,-1);
				break;
				case 1:
					walkTo(0,1);
				break;
				case 2:
					walkTo(1,0);
				break;
				case 3:
					walkTo(-1,0);
				break;			
			}		
		} else if(c.isRunning2 && !withinDistance) {
			if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} 
		} else {
			if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY + 1));
			} 
		}
		c.faceUpdate(c.followId2);
	}
	

	
	
	public int getRunningMove(int i, int j) {
		if (j - i > 2)
			return 2;
		else if (j - i < -2)
			return -2;
		else return j-i;
	}
	
	public void resetFollow() {
		c.followId = 0;
		c.followId2 = 0;
		c.mageFollow = false;
		c.outStream.createFrame(174);
		c.outStream.writeWord(0);
		c.outStream.writeByte(0);
		c.outStream.writeWord(1);
	}
	
	public void walkTo(int i, int j) {
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
        int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;

        for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
    }
	
	public void walkTo2(int i, int j) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
		int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;

        for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
    }
	
	public void stopDiagonal(int otherX, int otherY) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 1;
		int xMove = otherX - c.getX();
		int yMove = 0;
		if (xMove == 0)
			yMove = otherY - c.getY();
		/*if (!clipHor) {
			yMove = 0;
		} else if (!clipVer) {
			xMove = 0;	
		}*/
		
		int k = c.getX() + xMove;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + yMove;
        l -= c.mapRegionY * 8;
		
		for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
		
	}
	
		
	
	public void walkToCheck(int i, int j) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
		int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;
		
		for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
	}
	

	public int getMove(int place1,int place2) {
		if (System.currentTimeMillis() - c.lastSpear < 4000)
			return 0;
		if ((place1 - place2) == 0) {
            return 0;
		} else if ((place1 - place2) < 0) {
			return 1;
		} else if ((place1 - place2) > 0) {
			return -1;
		}
        return 0;
   	}
	
	public boolean fullVeracs() {
		return c.playerEquipment[c.playerHat] == 4753 && c.playerEquipment[c.playerChest] == 4757 && c.playerEquipment[c.playerLegs] == 4759 && c.playerEquipment[c.playerWeapon] == 4755;
	}
	public boolean fullGuthans() {
		return c.playerEquipment[c.playerHat] == 4724 && c.playerEquipment[c.playerChest] == 4728 && c.playerEquipment[c.playerLegs] == 4730 && c.playerEquipment[c.playerWeapon] == 4726;
	}
	
	/**
	* reseting animation
	**/
	public void resetAnimation() {
		c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
		c.startAnimation(c.playerStandIndex);
		requestUpdates();
	}

	public void requestUpdates() {
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}
	
	public void handleAlt(int id) {
		if (!c.getItems().playerHasItem(id)) {
			c.getItems().addItem(id,1);
		}
	}
	
	public void levelUp(int skill) {
int totalLevel = (getLevelForXP(c.playerXP[0]) + getLevelForXP(c.playerXP[1]) + getLevelForXP(c.playerXP[2]) + getLevelForXP(c.playerXP[3]) + getLevelForXP(c.playerXP[4]) + getLevelForXP(c.playerXP[5]) + getLevelForXP(c.playerXP[6]) + getLevelForXP(c.playerXP[7]) + getLevelForXP(c.playerXP[8]) + getLevelForXP(c.playerXP[9]) + getLevelForXP(c.playerXP[10]) + getLevelForXP(c.playerXP[11]) + getLevelForXP(c.playerXP[12]) + getLevelForXP(c.playerXP[13]) + getLevelForXP(c.playerXP[14]) + getLevelForXP(c.playerXP[15]) + getLevelForXP(c.playerXP[16]) + getLevelForXP(c.playerXP[17]) + getLevelForXP(c.playerXP[18]) + getLevelForXP(c.playerXP[19]) + getLevelForXP(c.playerXP[20]) + getLevelForXP(c.playerXP[21]) + getLevelForXP(c.playerXP[22]) + getLevelForXP(c.playerXP[23]) + getLevelForXP(c.playerXP[24]));
		sendFrame126("Levels: "+totalLevel, 13983);
		switch(skill) {
	case 0: // Attack
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced an Attack level!", 6248);
		sendFrame126("Your Attack level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6249);
		c.sendMessage("Congratulations, you just advanced an Attack level.");	
		sendFrame164(6247);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Attack!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Attack!");
			c.sendMessage("For a bonus Ream has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9748, 1);
		c.getItems().addItem(9749, 1);
		break;

	case 1: // Defence
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Defence level!", 6254);
		sendFrame126("Your Defence level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6255);
		c.sendMessage("Congratulations, you just advanced a Defence level.");
		sendFrame164(6253);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Defence!");
		}
			c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Defence!");
			c.sendMessage("For a bonus Ream has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9754, 1);
		c.getItems().addItem(9755, 1);
		break;

	case 2: // Strength
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Strength level!", 6207);
		sendFrame126("Your Strength level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6208);
		c.sendMessage("Congratulations, you just advanced a Strength level.");
		sendFrame164(6206);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j]; 
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Strength!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Strength!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9751, 1);
		c.getItems().addItem(9752, 1);
		break;

	case 3: // Constitution
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Constitution level!", 6217);
		sendFrame126("Your Constitution level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6218);
		c.sendMessage("Congratulations, you just advanced a Constitution level.");
		sendFrame164(6216);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Constitution!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Constitution!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9769, 1);
		c.getItems().addItem(9770, 1);
		break;

	case 4: // Ranged
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Ranged level!", 5453);
		sendFrame126("Your Ranged level is now " + getLevelForXP(c.playerXP[skill]) + ".", 5454);
		c.sendMessage("Congratulations, you just advanced a Ranged level.");
		sendFrame164(4443);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Ranged!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Ranged!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9757, 1);
		c.getItems().addItem(9758, 1);
		break;

	case 5: // Prayer
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Prayer level!", 6243);
		sendFrame126("Your Prayer level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6244);
		c.sendMessage("Congratulations, you just advanced a Prayer level.");
		sendFrame164(6242);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Prayer!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Prayer!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9760, 1);
		c.getItems().addItem(9761, 1);
		break;

	case 6: // Magic
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Magic level!", 6212);
		sendFrame126("Your Magic level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6213);
		c.sendMessage("Congratulations, you just advanced a Magic level.");
		sendFrame164(6211);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Magic!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Magic!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9762, 1);
		c.getItems().addItem(9764, 1);
		break;

	case 7: // Cooking
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Cooking level!", 6227);
		sendFrame126("Your cooking level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6228);
		c.sendMessage("Congratulations, you just advanced a Cooking level.");
		sendFrame164(6226);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Cooking!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Cooking!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9802, 1);
		c.getItems().addItem(9803, 1);
		break;

	case 8: // Woodcutting
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Woodcutting level!", 4273);
		sendFrame126("Your woodcutting level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4274);
		c.sendMessage("Congratulations, you just advanced a Woodcutting level.");
		sendFrame164(4272);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Woodcutting!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Woodcutting!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9808, 1);
		c.getItems().addItem(9809, 1);
		break;

	case 9: // Fletching
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Fletching level!", 6232);
		sendFrame126("Your Fletching level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6233);
		c.sendMessage("Congratulations, you just advanced a Fletching level.");
		sendFrame164(6231);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Fletching!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Fletching!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9784, 1);
		c.getItems().addItem(9785, 1);
		break;

	case 10: // Fishing
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Fishing level!", 6259);
		sendFrame126("Your Fishing level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6260);
		c.sendMessage("Congratulations, you just advanced a Fishing level.");
		sendFrame164(6258);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Fishing!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Fishing!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9799, 1);
		c.getItems().addItem(9800, 1);
		break;

	case 11: // Firemaking
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Firemaking level!", 4283);
		sendFrame126("Your Firemaking level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4284);
		c.sendMessage("Congratulations, you just advanced a Firemaking level.");
		sendFrame164(4282);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Firemaking!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Firemaking!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9805, 1);
		c.getItems().addItem(9806, 1);
		break;

	case 12: // Crafting
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Crafting level!", 6264);
		sendFrame126("Your Crafting level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6265);
		c.sendMessage("Congratulations, you just advanced a Crafting level.");
		sendFrame164(6263);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Crafting!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Crafting!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9805, 1);
		c.getItems().addItem(9806, 1);
		break;

	case 13: // Smithing
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Smithing level!", 6222);
		sendFrame126("Your Smithing level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6223);
		c.sendMessage("Congratulations, you just advanced a Smithing level.");
		sendFrame164(6221);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Smithing!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Smithing!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9796, 1);
		c.getItems().addItem(9797, 1);
		break;

	case 14: // Mining
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Mining level!", 4417);
		sendFrame126("Your Mining level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4438);
		c.sendMessage("Congratulations, you just advanced a Mining level.");
		sendFrame164(4416);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Mining!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Mining!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9793, 1);
		c.getItems().addItem(9794, 1);
		break;

	case 15: // Herblore
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Herblore level!", 6238);
		sendFrame126("Your herblore level is now " + getLevelForXP(c.playerXP[skill]) + ".", 6239);
		c.sendMessage("Congratulations, you just advanced a Herblore level.");
		sendFrame164(6237);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Herblore!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Herblore!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(9775, 1);
		c.getItems().addItem(9776, 1);
		break;

	case 16: // Agility
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Agility level!", 4278);
		sendFrame126("Your Agility level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4279);
		c.sendMessage("Congratulations, you just advanced an Agility level.");
		sendFrame164(4277);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Agility!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Agility!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9772, 1);
		c.getItems().addItem(9773, 1);
		break;

	case 17: // Thieving
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Thieving level!", 4263);
		sendFrame126("Your Theiving level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4264);
		c.sendMessage("Congratulations, you just advanced a Thieving level.");
		sendFrame164(4261);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Thieving!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Thieving!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9778, 1);
		c.getItems().addItem(9779, 1);
		break;

	case 18: // Slayer
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Slayer level!", 12123);
		sendFrame126("Your slayer level is now " + getLevelForXP(c.playerXP[skill]) + ".", 12124);
		c.sendMessage("Congratulations, you just advanced a Slayer level.");
		sendFrame164(12122);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j]; 
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Slayer!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Slayer!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9787, 1);
		c.getItems().addItem(9788, 1);
		break;

	case 19: // Farming
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Farming level!", 12123);
		sendFrame126("Your Farming level is now " + getLevelForXP(c.playerXP[skill]) + ".", 12124);
		c.sendMessage("Congratulations, you just advanced a Farming level.");
		sendFrame164(12122);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Farming!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Farming!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9811, 1);
		c.getItems().addItem(9812, 1);
		break;

	case 20: // Runecrafting
	c.lvlPoints +=2;
		sendFrame126("Congratulations, you just advanced a Runecrafting level!", 4268);
		sendFrame126("Your Runecrafting level is now " + getLevelForXP(c.playerXP[skill]) + ".", 4269);
		c.sendMessage("Congratulations, you just advanced a Runecrafting level.");
		sendFrame164(4267);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			 all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Runecrafting!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Runecrafting!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
			c.getItems().addItem(9766, 1);
		c.getItems().addItem(9767, 1);
		break;
	
			case 21:
			c.lvlPoints +=2;
			sendFrame126("Congratulations, you just advanced a Hunter level!", 4268);
			sendFrame126("Your Hunter level is now "+getLevelForXP(c.playerXP[skill])+".", 4269);
			c.sendMessage("Congratulations, you just advanced a hunter level.");
           sendFrame164(4267);
		if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Hunter!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Hunter!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9766, 1);
		c.getItems().addItem(9767, 1);
		break;
		
			case 22:
			c.lvlPoints +=2;
			sendFrame126("Congratulations, you just advanced a Summoning level!", 4268);
			sendFrame126("Your Summoning level is now "+getLevelForXP(c.playerXP[skill])+".", 4269);
			c.sendMessage("Congratulations, you just advanced a Summoning level.");
			sendFrame164(4267);
			if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Summoning!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Summoning!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		c.getItems().addItem(9787, 1);
		c.getItems().addItem(9788, 1);
		break;
			
		case 23:
		c.lvlPoints +=2;
			sendFrame126("Congratulations, you just advanced a PK'ing level!", 12123);
			sendFrame126("Your PK'ing level is now "+getLevelForXP(c.playerXP[skill])+".", 12124);
			c.sendMessage("Congratulations, you just advanced a PK'ing level.");
			sendFrame164(12122);
			if (getLevelForXP(c.playerXP[skill]) <= 98)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " PK'ing!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in PK'ing!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!");
		break;
			
			case 24:
			c.lvlPoints +=2;
			sendFrame126("Congratulations, you just advanced a Dungeoneering level!", 12123);
			sendFrame126("Your Dungeoneering level is now "+getLevelForXP(c.playerXP[skill])+".", 12124);
			c.sendMessage("Congratulations, you just advanced a Dungeoneering level.");
			sendFrame164(12122);
			if (getLevelForXP(c.playerXP[skill]) <= 119)
			break;
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] == null)
				continue;
			Client all = (Client)Server.playerHandler.players[j];			
			all.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " has just achieved level " + getLevelForXP(c.playerXP[skill]) + " Dungeoneering!");
		}
		c.lvlPoints +=15;
			c.sendMessage("Congratulations you have reached level 99 in Dungeonnering!");
			c.sendMessage("For a bonus ArmoredPkz has given you 15 Lvl points! ENJOY!"); 
		c.getItems().addItem(18509, 1);
		c.getItems().addItem(18510, 1);
		break;
		}
		c.dialogueAction = 0;
		c.nextChat = 0;
	}
	
	public void refreshSkill(int i) {
		switch (i) {
			case 0://attack
			sendFrame126("" + c.playerLevel[0] + "", 31114);
			sendFrame126("" + getLevelForXP(c.playerXP[0]) + "", 31115);
			sendFrame126("Exp: " + c.playerXP[0] + "", 31113);
			break;
			
			case 1://defence
			sendFrame126("" + c.playerLevel[1] + "", 31124);
			sendFrame126("" + getLevelForXP(c.playerXP[1]) + "", 31125);
			sendFrame126("Exp: " + c.playerXP[1] + "", 31123);
			break;
			
			case 2://strength
			sendFrame126("" + c.playerLevel[2] + "", 31119);
			sendFrame126("" + getLevelForXP(c.playerXP[2]) + "", 31120);
			sendFrame126("Exp: " + c.playerXP[2] + "", 31118);
			break;
			
			case 3://Constitution
			sendFrame126("" + c.playerLevel[3] + "", 31159);
			sendFrame126("" + getLevelForXP(c.playerXP[3]) + "", 31160);
			sendFrame126("Exp: " + c.playerXP[3] + "", 31158);
			break;
			
			case 4://ranged
			sendFrame126("" + c.playerLevel[4] + "", 31129);
			sendFrame126("" + getLevelForXP(c.playerXP[4]) + "", 31130);
			sendFrame126("Exp: " + c.playerXP[4] + "", 31128);
			break;
			
			case 5://prayer
			sendFrame126("" + c.playerLevel[5] + "", 31134);
			sendFrame126("" + getLevelForXP(c.playerXP[5]) + "", 31135);
			sendFrame126("Exp: " + c.playerXP[5] + "", 31133);
			sendFrame126("" +c.playerLevel[5]+"/"+getLevelForXP(c.playerXP[5])+"", 687);//Prayer frame
			break;
			
			case 6://magic
			sendFrame126("" + c.playerLevel[6] + "", 31139);
			sendFrame126("" + getLevelForXP(c.playerXP[6]) + "", 31140);
			sendFrame126("Exp: " + c.playerXP[6] + "", 31138);
			break;
			
			case 7://cooking
			sendFrame126("" + c.playerLevel[7] + "", 31219);
			sendFrame126("" + getLevelForXP(c.playerXP[7]) + "", 31220);
			sendFrame126("Exp:" + c.playerXP[7] + "", 31218);
			break;
			
			case 8://woodcutting
			sendFrame126("" + c.playerLevel[8] + "", 31229);
			sendFrame126("" + getLevelForXP(c.playerXP[8]) + "", 31230);
			sendFrame126("Exp: " + c.playerXP[8] + "", 31228);
			break;
			
			case 9://fletching
			sendFrame126("" + c.playerLevel[9] + "", 31184);
			sendFrame126("" + getLevelForXP(c.playerXP[9]) + "", 31185);
			sendFrame126("Exp: " + c.playerXP[9] + "", 31183);
			break;
			
			case 10://fishing
			sendFrame126("" + c.playerLevel[10] + "", 31214);
			sendFrame126("" + getLevelForXP(c.playerXP[10]) + "", 31215);
			sendFrame126("Exp: " + c.playerXP[10] + "", 31213);
			break;
			
			case 11://firemaking
			sendFrame126("" + c.playerLevel[11] + "", 31224);
			sendFrame126("" + getLevelForXP(c.playerXP[11]) + "", 31225);
			sendFrame126("Exp: " + c.playerXP[11] + "", 31223);
			break;
			
			case 12://crafting
			sendFrame126("" + c.playerLevel[12] + "", 31179);
			sendFrame126("" + getLevelForXP(c.playerXP[12]) + "", 31180);
			sendFrame126("Exp: " + c.playerXP[12] + "", 31178);
			break;
			
			case 13://smithing
			sendFrame126("" + c.playerLevel[13] + "", 31209);
			sendFrame126("" + getLevelForXP(c.playerXP[13]) + "", 31210);
			sendFrame126("Exp: " + c.playerXP[13] + "", 31208);
			break;
			
			case 14://mining
			sendFrame126("" + c.playerLevel[14] + "", 31204);
			sendFrame126("" + getLevelForXP(c.playerXP[14]) + "", 31205);
			sendFrame126("Exp: " + c.playerXP[14] + "", 31203);
			break;
			
			case 15://herblore
			sendFrame126("" + c.playerLevel[15] + "", 31169);
			sendFrame126("" + getLevelForXP(c.playerXP[15]) + "", 31170);
			sendFrame126("Exp: " + c.playerXP[15] + "", 31168);
			break;
			
			case 16://agility
			sendFrame126("" + c.playerLevel[16] + "", 31164);
			sendFrame126("" + getLevelForXP(c.playerXP[16]) + "", 31165);
			sendFrame126("Exp: " + c.playerXP[16] + "", 31163);
			break;
			
			case 17://thieving
			sendFrame126("" + c.playerLevel[17] + "", 31174);
			sendFrame126("" + getLevelForXP(c.playerXP[17]) + "", 31175);
			sendFrame126("Exp: " + c.playerXP[17] + "", 31173);
			break;
			
			case 18://slayer
			sendFrame126("" + c.playerLevel[18] + "", 31189);
			sendFrame126("" + getLevelForXP(c.playerXP[18]) + "", 31190);
			sendFrame126("Exp: " + c.playerXP[18] + "", 31188);
			break;
			
			case 19://farming
			sendFrame126("" + c.playerLevel[19] + "", 31234);
			sendFrame126("" + getLevelForXP(c.playerXP[19]) + "", 31235);
			sendFrame126("Exp: " + c.playerXP[19] + "", 31233);
			break;
			
			case 20://runecrfating
			sendFrame126("" + c.playerLevel[20] + "", 31144);
			sendFrame126("" + getLevelForXP(c.playerXP[20]) + "", 31145);
			sendFrame126("Exp: " + c.playerXP[20] + "", 31143);
			break;

			case 21://hunter
sendFrame126("" + c.playerLevel[21] + "", 31149);
sendFrame126("" + getLevelForXP(c.playerXP[21]) + "", 31150);
sendFrame126("Exp: " + c.playerXP[21] + "", 31148);
break;

case 22://sum
sendFrame126("" + c.playerLevel[22] + "", 31194);
sendFrame126("" + getLevelForXP(c.playerXP[22]) + "", 31195);
sendFrame126("Exp: " + c.playerXP[22] + "", 31193);
break;

case 23://pking
sendFrame126("" + c.playerLevel[23] + "", 31239);
sendFrame126("" + getLevelForXP(c.playerXP[23]) + "", 31240);
sendFrame126("Exp: " + c.playerXP[23] + "", 31238);
break;

case 24://dung
if(c.playerXP[24] >= 0 && c.playerXP[24] <= 14391160) { 
sendFrame126("" + c.playerLevel[24] + "", 31154);
sendFrame126("" + getLevelForXP(c.playerXP[24]) + "", 31155);
sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
  } else if(c.playerXP[24] >= 14391161 && c.playerXP[24] <= 15889108) {
    sendFrame126("100", 31154);
	sendFrame126("100", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 15889109 && c.playerXP[24] <= 17542976) {
    sendFrame126("101", 31154);
    sendFrame126("101", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 17542977 && c.playerXP[24] <= 19368991) {
    sendFrame126("102", 31154);
    sendFrame126("102", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 19368992 && c.playerXP[24] <= 21385072) {
    sendFrame126("103", 31154);
    sendFrame126("103", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 21385073 && c.playerXP[24] <= 23611005) {
    sendFrame126("104", 31154);
    sendFrame126("104", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 23611006 && c.playerXP[24] <= 26068631) {
    sendFrame126("105", 31154);
    sendFrame126("105", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 26068632 && c.playerXP[24] <= 28782068) {
    sendFrame126("106", 31154);
    sendFrame126("106", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 28782069 && c.playerXP[24] <= 31777942) {
    sendFrame126("107", 31154);
    sendFrame126("107", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 31777943 && c.playerXP[24] <= 35085653) {
    sendFrame126("108", 31154);
    sendFrame126("108", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 35085654 && c.playerXP[24] <= 38737660) {
    sendFrame126("109", 31154);
    sendFrame126("109", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 38737661 && c.playerXP[24] <= 42769799) {
    sendFrame126("110", 31154);
    sendFrame126("110", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 42769800 && c.playerXP[24] <= 47221639) {
    sendFrame126("111", 31154);
    sendFrame126("111", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 47221640 && c.playerXP[24] <= 52136868) {
    sendFrame126("112", 31154);
    sendFrame126("112", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 52136869 && c.playerXP[24] <= 57563717) {
    sendFrame126("113", 31154);
    sendFrame126("113", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 57563718 && c.playerXP[24] <= 63555442) {
    sendFrame126("114", 31154);
    sendFrame126("114", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 63555443 && c.playerXP[24] <= 70170839) {
    sendFrame126("115", 31154);
    sendFrame126("115", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 70170840 && c.playerXP[24] <= 77474827) {
    sendFrame126("116", 31154);
    sendFrame126("116", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 77474828 && c.playerXP[24] <= 85539081) {
    sendFrame126("117", 31154);
    sendFrame126("117", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 85539082 && c.playerXP[24] <= 94442735) {
    sendFrame126("118", 31154);
    sendFrame126("118", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 94442736 && c.playerXP[24] <= 104273166) {
    sendFrame126("119", 31154);
    sendFrame126("119", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 } else if(c.playerXP[24] >= 104273167 && c.playerXP[24] <= 210000000) {
    sendFrame126("120", 31154);
    sendFrame126("120", 31155);
	sendFrame126("Exp: " + c.playerXP[24] + "", 31153);
 }
 break;
 }
 }




	
	
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			if (lvl >= level)
				return output;
			output = (int)Math.floor(points / 4);
		}
		return 0;
	}

	public int Wheel() {
		return Wheel[(int)(Math.random()*Wheel.length)];
	}
	
	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430)
			return 99;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
	
		/*int shrogan = 0;
		int output2 = 0;
	    if (exp > 130344300)
		    return 120;
		for (int lvl = 1; lvl <= 120; lvl++) {
			shrogan += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output2 = (int) Math.floor(shrogan / 4);
			if (output2 >= exp) {
				return lvl;
			}
		}*/
		return 0;
	}
	
	public boolean addSkillXP(int amount, int skill){
if (c.xpLock == true || c.Jail == true) {
    return false;
}
		if (amount+c.playerXP[skill] < 0 || c.playerXP[skill] > 200000000) {
			if(c.playerXP[skill] > 200000000) {
				c.playerXP[skill] = 200000000;
			}
			return false;
		}
		amount *= Config.SERVER_EXP_BONUS;
		int oldLevel = getLevelForXP(c.playerXP[skill]);
		c.playerXP[skill] += amount;
		if (c.playerEquipment[c.playerCape] == 7803) {
		c.playerXP[skill] += amount * 2;
		}
		if (c.playerEquipment[c.playerCape] == 295) {
		c.playerXP[skill] += amount * 1.2;
		}
		
		if (oldLevel < getLevelForXP(c.playerXP[skill])) {
			if (c.playerLevel[skill] < c.getLevelForXP(c.playerXP[skill]) && skill != 3 && skill != 5)
				c.playerLevel[skill] = c.getLevelForXP(c.playerXP[skill]);
			levelUp(skill);
			c.gfx100(199);
			requestUpdates();
		}
		setSkillLevel(skill, c.playerLevel[skill], c.playerXP[skill]);
		refreshSkill(skill);
		return true;
	}


	public void resetBarrows() {
		c.barrowsNpcs[0][1] = 0;
		c.barrowsNpcs[1][1] = 0;
		c.barrowsNpcs[2][1] = 0;
		c.barrowsNpcs[3][1] = 0;
		c.barrowsNpcs[4][1] = 0;
		c.barrowsNpcs[5][1] = 0;
		c.barrowsKillCount = 0;
		c.randomCoffin = Misc.random(3) + 1;
	}
	
	public static int Barrows[] = {4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759};
	public static int Crystal[] = {1113,1127,1147,1163,1185,8650,8652,8654,8656,8658,8660,8662,8664,8666,8668,8670,8672,8674,8676,8678,8680,4037,4039,10400,10402,10404,10406,10408,10410,10412,10414,10416,10418,10420,10422,10424,10426,10428,10430,10432,10434,10436,10438,7668,2651,2978,2979,2980,2981,2982,2983,2984,2985,2986,2986,2987,2988,2989,2990,2991,2992,2993,2994,2995,6182,4151,10069,10074,10171,4708,4710,4712,4714,4753,4755,4757,4759,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4716,4718,4720,4722,};
	public static int Runes[] = {4740,558,560,565};
	public static int Clue1[] = {};
	public static int Partyhats[] = {1038,1040,1032,1034,1036,1038};
	public static int Pots[] = {};
	public static int arti[] = {14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886, 14887, 14888, 14889, 14890, 14891, 14892};
	public static int Wheel[] = {4740, 558, 560, 565, 4151, 11732, 14484, 4708, 4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759};
	
	public int randomBarrows() {
		return Barrows[(int)(Math.random()*Barrows.length)];
	}

        public int randomPhat() {
                return Partyhats[(int)(Math.random()*Partyhats.length)];
        }

	public int randomClue1() {
		return Clue1[(int)(Math.random()*Clue1.length)];
	}
	
	public int randomCrystal() {
	return Crystal[(int) (Math.random()*Crystal.length)];
	}


	public int randomRunes() {
		return Runes[(int) (Math.random()*Runes.length)];
	}
	
	public int randomPots() {
		return Pots[(int) (Math.random()*Pots.length)];
	}
	/**
	 * Show an arrow icon on the selected player.
	 * @Param i - Either 0 or 1; 1 is arrow, 0 is none.
	 * @Param j - The player/Npc that the arrow will be displayed above.
	 * @Param k - Keep this set as 0
	 * @Param l - Keep this set as 0
	 */
	public void drawHeadicon(int i, int j, int k, int l) {
		synchronized(c) {
			c.outStream.createFrame(254);
			c.outStream.writeByte(i);
	
			if (i == 1 || i == 10) {
				c.outStream.writeWord(j);
				c.outStream.writeWord(k);
				c.outStream.writeByte(l);
			} else {
				c.outStream.writeWord(k);
				c.outStream.writeWord(l);
				c.outStream.writeByte(j);
			}
		}
	}
	
	public int getNpcId(int id) {
		for(int i = 0; i < NPCHandler.maxNPCs; i++) {
			if(NPCHandler.npcs[i] != null) {
				if(Server.npcHandler.npcs[i].npcId == id) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public void removeObject(int x, int y) {
		object(-1, x, x, 10, 10);
	}
	
	public void objectToRemove(int X, int Y) {
		object(-1, X, Y, 10, 10);
	}

	public void objectToRemove2(int X, int Y) {
		object(-1, X, Y, -1, 0);
	}
	
	public void removeObjects() {
		objectToRemove2(2638, 4688);
		objectToRemove(2638, 4688);
		objectToRemove(2844, 3440);
		objectToRemove(2846, 3437);
		objectToRemove(2840, 3439);
		objectToRemove(2841, 3443);
		objectToRemove2(2635, 4693);
		objectToRemove2(2634, 4693);
		objectToRemove2(2794, 9327);
		objectToRemove(3206, 3263);
		objectToRemove(3193, 3274);
		objectToRemove(3193, 3273);
	}
	
	
	public void handleGlory(int gloryId) {
		c.getDH().sendOption4("Edgeville", "Al Kharid", "Karamja", "Mage Bank");
		c.usingGlory = true;
	}
	
	public void resetVariables() {
		c.getCrafting().resetCrafting();
		c.usingGlory = false;
		c.smeltInterface = false;
		c.smeltType = 0;
		c.smeltAmount = 0;
		c.woodcut[0] = c.woodcut[1] = c.woodcut[2] = 0;
		c.mining[0] = c.mining[1] = c.mining[2] = 0;
	}
	
	public boolean inPitsWait() {
		return c.getX() <= 2404 && c.getX() >= 2394 && c.getY() <= 5175 && c.getY() >= 5169;
	}
	
	public void castleWarsObjects() {
		object(-1, 2373, 3119, -3, 10);
		object(-1, 2372, 3119, -3, 10);
	}
	
	public void removeFromCW() {
		if (c.castleWarsTeam == 1) {
			if (c.inCwWait) {
				Server.castleWars.saradominWait.remove(Server.castleWars.saradominWait.indexOf(c.playerId));
			} else {
				Server.castleWars.saradomin.remove(Server.castleWars.saradomin.indexOf(c.playerId));
			}
		} else if (c.castleWarsTeam == 2) {
			if (c.inCwWait) {
				Server.castleWars.zamorakWait.remove(Server.castleWars.zamorakWait.indexOf(c.playerId));
			} else {
				Server.castleWars.zamorak.remove(Server.castleWars.zamorak.indexOf(c.playerId));
			}		
		}
	}
	
public int antiFire() {
		int toReturn = 0;
		if (c.antiFirePot)
			toReturn++;
		if (c.playerEquipment[c.playerShield] == 1540 || c.prayerActive[12] || c.playerEquipment[c.playerShield] == 11284 || c.playerEquipment[c.playerShield] == 11283)
			toReturn++;
		c.getCombat().addCharge(c);
		return toReturn;	
	}
	
	public int backupInvItems[] = new int[28];
	public int backupInvItemsN[] = new int[28];
	
	public void otherInv(Client c, Client o) {
		if(o == c || o == null || c == null)
		{
		return;
		}

		for (int i = 0; i < o.playerItems.length; i++)
		{
			backupInvItems[i] = c.playerItems[i]; c.playerItemsN[i] = c.playerItemsN[i];
			c.playerItemsN[i] = o.playerItemsN[i]; c.playerItems[i] = o.playerItems[i];
		}
		
		c.getItems().updateInventory();
		
		for (int i = 0; i < o.playerItems.length; i++)
		{
		c.playerItemsN[i] = backupInvItemsN[i]; c.playerItems[i] = backupInvItems[i];
		}
	}
	
	public boolean checkForFlags() {
		int[][] itemsToCheck = {{995,100000000},{35,5},{667,5},{2402,5},{746,5},{4151,150},{565,100000},{560,100000},{555,300000},{11235,10}};
		for (int j = 0; j < itemsToCheck.length; j++) {
			if (itemsToCheck[j][1] < c.getItems().getTotalCount(itemsToCheck[j][0]))
				return true;		
		}
		return false;
	}
	
		public void addStarter() {
		if (!Connection.hasRecieved1stStarter(Server.playerHandler.players[c.playerId].connectedFrom)) {
		c.trade11 = 0;
		c.gotStarter = 0;
			Connection.addIpToStarterList1(Server.playerHandler.players[c.playerId].connectedFrom);
			Connection.addIpToStarter1(Server.playerHandler.players[c.playerId].connectedFrom);
			c.sendMessage("<shad=15007744>You have recieved 1 out of 2 starter packages! You have 1 left from this IP ADRESS.</col>");
			c.sendMessage("<col=132>Welcome to "+ Config.SERVER_NAME +"!<col=132>");
			c.sendMessage("If you are new do <col=132>::rules<col=132> and then do <col=132>::commands<col=132>");
			c.sendMessage("Relog to Walk :D");
				c.canChangeAppearance = true;
		} else if (Connection.hasRecieved1stStarter(Server.playerHandler.players[c.playerId].connectedFrom) && !Connection.hasRecieved2ndStarter(Server.playerHandler.players[c.playerId].connectedFrom)) {
		c.trade11 = 0;
		c.gotStarter = 0;
			c.sendMessage("<shad=15007744>You have recieved 2 out of 2 starter packages on this IP address.</col>");
			c.sendMessage("<col=132>Welcome to "+ Config.SERVER_NAME +"!<col=132>");
			c.sendMessage("If you are new do <col=132>::rules<col=132> and then do <col=132>::commands<col=132>");
			c.sendMessage("Relog to Walk :D");
				c.canChangeAppearance = true;
			Connection.addIpToStarterList2(Server.playerHandler.players[c.playerId].connectedFrom);
			Connection.addIpToStarter2(Server.playerHandler.players[c.playerId].connectedFrom);
		} else if (Connection.hasRecieved1stStarter(Server.playerHandler.players[c.playerId].connectedFrom) && Connection.hasRecieved2ndStarter(Server.playerHandler.players[c.playerId].connectedFrom)) {
			c.gotStarter = 1;
			c.sendMessage("<shad=15007744>You have already recieved 2 starters! STOP TRYING TO GET FREE ITEMS!</col>");
			c.sendMessage("<col=132>Welcome to "+ Config.SERVER_NAME +"!<col=132>");
			c.sendMessage("If you are new do <col=132>::rules<col=132> and then do <col=132>::commands<col=132>");
			c.sendMessage("Relog to Walk :D");
				c.canChangeAppearance = true;
		}
	}
	
	public void useOperate(int itemId) {
		switch (itemId) {
			case 1712:
			case 1710:
			case 1708:
			case 1706:
			handleGlory(itemId);
			break;
			case 11283:
			case 11284:
				c.sendMessage("Your shield has "+c.dfsCount+" charges");
			if (c.playerIndex > 0) {
				c.getCombat().handleDfs();				
			} else if (c.npcIndex > 0) {
				c.getCombat().handleDfsNPC();
			}
			break;	
		}
	}
	
	public void getSpeared(int otherX, int otherY) {
		int x = c.absX - otherX;
		int y = c.absY - otherY;
		if (x > 0)
			x = 1;
		else if (x < 0)
			x = -1;
		if (y > 0)
			y = 1;
		else if (y < 0)
			y = -1;
		moveCheck(x,y);
		c.lastSpear = System.currentTimeMillis();
	}
	
	public void moveCheck(int xMove, int yMove) {	
		movePlayer(c.absX + xMove, c.absY + yMove, c.heightLevel);
	}
	
	public int findKiller() {
		int killer = c.playerId;
		int damage = 0;
		for (int j = 0; j < Config.MAX_PLAYERS; j++) {
			if (PlayerHandler.players[j] == null)
				continue;
			if (j == c.playerId)
				continue;
			if (c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, 40) 
				|| c.goodDistance(c.absX, c.absY + 9400, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, 40)
				|| c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY + 9400, 40))
				if (c.damageTaken[j] > damage) {
					damage = c.damageTaken[j];
					killer = j;
				}
		}
		return killer;
	}
	
	public void resetTzhaar() {
		c.waveId = -1;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;	
		c.getPA().movePlayer(3088,3488,0);
	}

	public void resetRFD() {
		c.waveId = -1;
		c.RFDToKill = -1;
		c.RFDKilled = -1;	
		c.getPA().movePlayer(3091,3486,0);
	}

	public void enterRFD() {
			if (c.Culin == true) {
			c.sendMessage("You have already finished this minigame!");
			return;
			}
			if (c.Agrith == true && c.Flambeed == false) {
			c.waveId = 1;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
		Server.rfd.spawnNextWave(c);
		return;
			} 
		if(c.Flambeed == true && c.Karamel == false) {
			c.waveId = 2;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
		Server.rfd.spawnNextWave(c);
		return;
			} 
		if(c.Karamel == true && c.Dessourt == false) {
			c.waveId = 3;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
		Server.rfd.spawnNextWave(c);
		return;
			} 
		if(c.Dessourt == true && c.Culin == false) {
			c.waveId = 4;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
		Server.rfd.spawnNextWave(c);
			return;
			} 
			if (c.Agrith == false) {
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
		c.waveId = 0;
		c.RFDToKill = -1;
		c.RFDKilled = -1;
		Server.rfd.spawnNextWave(c);
	}
	}
	
	public void enterCaves() {
		c.getPA().movePlayer(2413,5117, c.playerId * 4);
		c.waveId = 0;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
		Server.fightCaves.spawnNextWave(c);
		c.jadSpawn();
	}

	public void resetZombies() {
		c.zombiesToKill = -1;
		c.zombiesKilled = -1;	
		c.getPA().movePlayer(2824,3354,0);
	}
	
	public void enterZombies() {
		c.getPA().movePlayer(2604,9901, c.playerId * 4);
		c.waveId = 0;
		c.zombiesToKill = -1;
		c.zombiesKilled = -1;
		Server.zombieCaves.spawnNextWave(c);
	}
	
	public void appendPoison(int damage) {
		if (System.currentTimeMillis() - c.lastPoisonSip > c.poisonImmune) {
			c.sendMessage("You have been poisoned.");
			c.poisonDamage = damage;
		}	
	}
	
	public boolean checkForPlayer(int x, int y) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				if (p.getX() == x && p.getY() == y)
					return true;
			}	
		}
		return false;	
	}
	
	public void checkPouch(int i) {
		if (i < 0)
			return;
		c.sendMessage("This pouch has " + c.pouches[i] + " rune ess in it.");		
	}
	
	public void fillPouch(int i) {
		if (i < 0)
			return;
		int toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > c.getItems().getItemAmount(1436)) {
			toAdd = c.getItems().getItemAmount(1436);
		}
		if (toAdd > c.POUCH_SIZE[i] - c.pouches[i])
			toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > 0) {
			c.getItems().deleteItem(1436, toAdd);
			c.pouches[i] += toAdd;
		}		
	}
	
	public void emptyPouch(int i) {
		if (i < 0)
			return;
		int toAdd = c.pouches[i];
		if (toAdd > c.getItems().freeSlots()) {
			toAdd = c.getItems().freeSlots();
		}
		if (toAdd > 0) {
			c.getItems().addItem(1436, toAdd);
			c.pouches[i] -= toAdd;
		}		
	}
	
	public void fixAllBarrows() {
		int totalCost = 0;
		int cashAmount = c.getItems().getItemAmount(995);
		for (int j = 0; j < c.playerItems.length; j++) {
			boolean breakOut = false;
			for (int i = 0; i < c.getItems().brokenBarrows.length; i++) {
				if (c.playerItems[j]-1 == c.getItems().brokenBarrows[i][1]) {					
					if (totalCost + 80000 > cashAmount) {
						breakOut = true;
						c.sendMessage("You have run out of money.");
						break;
					} else {
						totalCost += 80000;
					}
					c.playerItems[j] = c.getItems().brokenBarrows[i][0]+1;
				}		
			}
			if (breakOut)		
				break;
		}
		if (totalCost > 0)
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995), totalCost);		
	}
	
	public void handleLoginText() {
		c.getPA().sendFrame126("Monster Teleport", 13037);
		c.getPA().sendFrame126("Minigame Teleport", 13047);
		c.getPA().sendFrame126("Boss Teleport", 13055);
		c.getPA().sendFrame126("Pking Teleport", 13063);
		c.getPA().sendFrame126("Skill Teleport", 13071);
		c.getPA().sendFrame126("Monster Teleport", 1300);
		c.getPA().sendFrame126("Minigame Teleport", 1325);
		c.getPA().sendFrame126("Boss Teleport", 1350);
		c.getPA().sendFrame126("Pking Teleport", 1382);
		c.getPA().sendFrame126("Skill Teleport", 1415);
		c.getPA().sendFrame126("City Teleport", 1454);	
		c.getPA().sendFrame126("Advanced Skilling", 7457);
		c.getPA().sendFrame126("Comming Soon", 13097);
		c.getPA().sendFrame126("Advanced Skilling", 13089);
		c.getPA().sendFrame126("City Teleport", 13081);
   	}
	
	public void handleWeaponStyle() {
		if (c.fightMode == 0) {
			c.getPA().sendFrame36(43, c.fightMode);
		} else if (c.fightMode == 1) {
			c.getPA().sendFrame36(43, 3);
		} else if (c.fightMode == 2) {
			c.getPA().sendFrame36(43, 1);
		} else if (c.fightMode == 3) {
			c.getPA().sendFrame36(43, 2);
		}
	}
	
	
	
}
