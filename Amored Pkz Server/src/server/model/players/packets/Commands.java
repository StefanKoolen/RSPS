/* 
Rewritten Commands of ArmoredPkz
*/
package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import java.text.DecimalFormat;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.model.players.CombatAssistant;
import server.model.players.PlayerSave;
import server.model.players.Player;
import java.io.*;
import server.Connection;

/**
 * Commands
 **/
public class Commands implements PacketType 
{
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
		if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
    if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
    if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 6 || c.playerRights == 3) 
        moderatorCommands(c, playerCommand);
    if (c.playerRights == 2 || c.playerRights == 6 || c.playerRights == 3) 
        administratorCommands(c, playerCommand);
    if (c.playerRights == 3 || c.playerRights == 6)
        ownerCommands(c, playerCommand);
        if (c.playerRights == 4) 
        DonatorCommands(c, playerCommand);
    
    }

    
    public void playerCommands(Client c, String playerCommand)
    {
		if (playerCommand.equalsIgnoreCase("pkrunes")) {
			c.getItems();
				int itemsToAdd[] = {9075,560,557,565,555};
					for (int i = 0; i < itemsToAdd.length; i++) {
						c.getItems().addItem(itemsToAdd[i], 100000000);
		}
		}	
	if(playerCommand.startsWith("withdraw")) {
		String[] cAmount = playerCommand.split(" ");
		int amount = Integer.parseInt(cAmount[1]);
		if (c.inWild()) {
			c.sendMessage("You cannot do this in the wilderness");
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
			return;
		}
		if(amount <= 0) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                        if (Server.playerHandler.players[j] != null) {
                        Client c3 = (Client) Server.playerHandler.players[j];
            c3.sendMessage("<shad=65535>" + c.playerName + " Tried to Dupe on the MoneyPouch! JAIL HIS SORRY ASSS!");
                        }
                        }
                        return;
                }
		if (c.InDung()) {
			c.sendMessage("You cannot do this in the dungeoneering");
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
			return;
		}
		if(amount == 0) {
			c.sendMessage("Why would I withdraw no coins?");
			return;
		}
		if(c.MoneyCash == 0) {
			c.sendMessage("You don't have any cash in the bag.");
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
			return;
		}
		if(c.MoneyCash < amount) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
			} else  {
				c.sendMessage("You withdraw "+c.MoneyCash+" coins.");
			}
			c.getItems().addItem(995, c.MoneyCash);
			c.MoneyCash = 0;
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8134); 
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
			return;
		}
		if(c.MoneyCash != 0) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
			} else  {
				c.sendMessage("You withdraw "+amount+" coins.");
			}
				c.MoneyCash -= amount;
				c.getItems().addItem(995, amount);
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
		if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
		c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134); 
		} else if(c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
			c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134);
		} else {
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8134);
			}
		c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
		}
	 }

	if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
   try {
   BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Reports.txt", true));
   String Report = playerCommand.substring(7);
   try {	
	report.newLine();
	report.write(c.playerName + ": " + Report);
	c.sendMessage("You have successfully submitted your report.");
	} finally {
	report.close();
	}
	} catch (IOException e) {
                e.printStackTrace();
	}
}
if (playerCommand.startsWith("dzone")) {
	if (c.playerRights >= 1) {
		c.getPA().startTeleport(2722, 4897, 0, "modern");
		c.heightLevel = 0;
	} else {
		c.sendMessage("You Need to be Donator to teleport.");
	}
}

if (playerCommand.equals("maxhit")) {
                c.sendMessage("Your current maxhit is: <shad="+c.getCombat().calculateMeleeMaxHit());
            }

if (playerCommand.startsWith("rtask")) { //command name
                c.taskAmount = -1; //vars
                c.slayerTask = 0;  //vars
                c.sendMessage("Your slayer task has been reset, speak to Duradel to request a new one.");
		                                        
        } //end
				if (playerCommand.startsWith("trade") && c.teleBlockLength == 0) {
				c.getPA().startTeleport(2605, 3097, 0, "modern");
				c.sendMessage("You teleport to the trade area.");
			}
			if (playerCommand.startsWith("commands")) {
				if (c.playerRights == 3){
				c.getPA().showInterface(8134);
				c.getPA().sendFrame126("@red@~ "+ Config.SERVER_NAME +" Commands ~",8144);
				c.getPA().sendFrame126("@cya@Normal Commands",8145);
				c.getPA().sendFrame126("@cya@",8146);
				c.getPA().sendFrame126("@red@players",8147);
				c.getPA().sendFrame126("@cya@afk",8148);
				c.getPA().sendFrame126("@cya@sit",8159);  
				c.getPA().sendFrame126("@cya@unsit",8150);
				c.getPA().sendFrame126("@gre@rank",8151);
				c.getPA().sendFrame126("@gre@withdraw",8152);
				c.getPA().sendFrame126("@cya@help",8153);
				c.getPA().sendFrame126("@cya@mithdrags",8154);
				c.getPA().sendFrame126("@gre@darkcastle",8155);
				c.getPA().sendFrame126("@cya@players",8156);
				c.getPA().sendFrame126("@cya@empty",8157);
				c.getPA().sendFrame126("@cya@pkrunes",8158);
				c.getPA().sendFrame126("@cya@report",8159);
				c.getPA().sendFrame126("@cya@maxhit",8160);
				c.getPA().sendFrame126("@cya@rtask",8161);
				c.getPA().sendFrame126("@cya@trade",8162);
				c.getPA().sendFrame126("@cya@stask",8163);
				c.getPA().sendFrame126("@cya@slevel",8164);
				c.getPA().sendFrame126("@cya@back",8165);
				c.getPA().sendFrame126("@cya@vote",8166);
				c.getPA().sendFrame126("@cya@r ??",8167);
				c.getPA().sendFrame126("@cya@claim ??",8168);
				c.getPA().sendFrame126("@cya@skele",8169);
				c.getPA().sendFrame126("@cya@players",8170);
				c.getPA().sendFrame126("@cya@player",8171);
				c.getPA().sendFrame126("@cya@snow on",8172);
				c.getPA().sendFrame126("@cya@snow off",8173);
				c.getPA().sendFrame126("@cya@pushups",8174);
				c.getPA().sendFrame126("@cya@6pack",8175);
				c.getPA().sendFrame126("@cya@changpassword",8176);
				c.getPA().sendFrame126("@cya@afk",8177);
				c.getPA().sendFrame126("@cya@funpk",8178);
				c.getPA().sendFrame126("@cya@home",8179);
				c.getPA().sendFrame126("@cya@highpk",8180);	
				c.getPA().sendFrame126("@cya@dennis",8181);
				c.getPA().sendFrame126("@cya@hightrain",8182);
				c.getPA().sendFrame126("@cya@train",8183);
				c.getPA().sendFrame126("@cya@cool",8184);
				c.getPA().sendFrame126("@cya@fall",8185);
				c.getPA().sendFrame126("@cya@return",8186);
				c.getPA().sendFrame126("@cya@logout",8187);
				c.getPA().sendFrame126("@cya@rules",8188);
				c.getPA().sendFrame126("@cya@skull",8189);
				c.getPA().sendFrame126("@cya@donate",8190);
				c.getPA().sendFrame126("@cya@pkp",8191);
				c.getPA().sendFrame126("@cya@kdr",8192);
				c.getPA().sendFrame126("@cya@sit",8193);
				c.getPA().sendFrame126("@cya@unsite",8194);
				c.getPA().sendFrame126("@cya@yell",8195);
				c.getPA().sendFrame126("@cya@jail",8196);
				c.getPA().sendFrame126("@cya@unjail",8197);
				c.getPA().sendFrame126("@cya@mark",8198);
				c.getPA().sendFrame126("@cya@timedmute",8199);
				c.getPA().sendFrame126("@cya@mute",8200);
				c.getPA().sendFrame126("@cya@unmute",8201);
				c.getPA().sendFrame126("@cya@ban",8202);
				c.getPA().sendFrame126("@cya@timedban",8203);
				c.getPA().sendFrame126("@cya@unban",8204);
				c.getPA().sendFrame126("@cya@xteleto",8205);
				c.getPA().sendFrame126("@cya@checkbank",8206);
				c.getPA().sendFrame126("@cya@kick",8207);
				c.getPA().sendFrame126("@cya@alert",8208);
				c.getPA().sendFrame126("@cya@bank",8209);
				c.getPA().sendFrame126("@cya@ipmute",8210);
				c.getPA().sendFrame126("@cya@object",8211);
				c.getPA().sendFrame126("@cya@rape",8212);
				c.getPA().sendFrame126("@cya@mypos",8213);
				c.getPA().sendFrame126("@cya@checkinv",8214);
				c.getPA().sendFrame126("@cya@interface",8215);
				c.getPA().sendFrame126("@cya@givedonor",8216);
				c.getPA().sendFrame126("@cya@gfx",8217);
				c.getPA().sendFrame126("@cya@tele_XXXX_YYYY",8218);
				c.getPA().sendFrame126("@cya@xteletome",8219);
				c.getPA().sendFrame126("@cya@sm",8220);
				c.getPA().sendFrame126("@cya@reloadshops",8201);
				c.getPA().sendFrame126("@cya@onenyan",8202);
				c.getPA().sendFrame126("@cya@nyan",8203);
				c.getPA().sendFrame126("@cya@fhome",8204);
				c.getPA().sendFrame126("@cya@xteleto",8205);
				c.getPA().sendFrame126("@cya@npc",8206);
				c.getPA().sendFrame126("@cya@pnpc",8207);
				c.getPA().sendFrame126("@cya@god",8208);
				c.getPA().sendFrame126("@cya@unipmute",8209);
				c.getPA().sendFrame126("@cya@ipban",8210);
				c.getPA().sendFrame126("@cya@unipban",8211);
				c.getPA().sendFrame126("@cya@ownerzone",8212);
				c.getPA().sendFrame126("@Red@dzone",8213);
				c.getPA().sendFrame126("@cya@staffzone",8214);
				c.getPA().sendFrame126("@cya@yelltag",8215);
				c.getPA().sendFrame126("@cya@demon",8216);
				c.getPA().sendFrame126("@cya@shop",8217);
				c.getPA().sendFrame126("@cya@item",8218);
				c.getPA().sendFrame126("@cya@ringofgod",8219);
				c.getPA().sendFrame126("@cya@uidban",8220);
				c.getPA().sendFrame126("@cya@unuidban",8221);
				c.getPA().sendFrame126("@cya@set1",8222);
				c.getPA().sendFrame126("@cya@getnpc",8223);
				c.getPA().sendFrame126("@cya@getip",8224);
				c.getPA().sendFrame126("@cya@setlevel",8225);
				c.getPA().sendFrame126("@cya@snow on",8226);
				c.getPA().sendFrame126("@cya@snow off",8227);
				c.getPA().sendFrame126("@cya@bank",8228);
				c.getPA().sendFrame126("@cya@sethome",8229);
				c.getPA().sendFrame126("@cya@hail",8230);
				c.getPA().sendFrame126("@cya@heal",8231);
				c.getPA().sendFrame126("@cya@update",8232);
				c.getPA().sendFrame126("@cya@who",8233);
				c.getPA().sendFrame126("@cya@copy",8234);
				c.getPA().sendFrame126("@cya@anim",8235);
				c.getPA().sendFrame126("@cya@npcall",8236);
				c.getPA().sendFrame126("@cya@scare",8237);
				c.getPA().sendFrame126("@cya@unpc",8238);
				c.getPA().sendFrame126("@cya@kill",8239);
				c.getPA().sendFrame126("@cya@giveitem",8240);
				c.getPA().sendFrame126("@cya@takeitem",8241);
				c.getPA().sendFrame126("@cya@levelids",8242);
				c.getPA().sendFrame126("@cya@givemod",8243);
				c.getPA().sendFrame126("@cya@giveadmin",8244);
				c.getPA().sendFrame126("@cya@giveowner",8245);
				c.getPA().sendFrame126("@cya@givespins",8246);
				c.getPA().sendFrame126("@cya@givedung",8247);
				c.getPA().sendFrame126("@cya@givepk",8248);
				c.getPA().sendFrame126("@cya@givepf",8249);
				c.getPA().sendFrame126("@cya@givelvl",8250);
				c.getPA().sendFrame126("@cya@givepoints",8251);
				c.getPA().sendFrame126("@cya@spec",8252);
				c.getPA().sendFrame126("@cya@master",8253);
				c.getPA().sendFrame126("@cya@unmaster",8254);
				c.getPA().sendFrame126("@cya@switch",8255);
				c.getPA().sendFrame126("@cya@brid",8256);
				c.getPA().sendFrame126("@cya@dropparty",8257);
				c.getPA().sendFrame126("@cya@alltome",8258);
				c.getPA().sendFrame126("@cya@demote",8259);
				c.getPA().sendFrame126("@cya@fakels",8260);
				c.getPA().sendFrame126("@cya@restart",8261);
				c.getPA().sendFrame126("@cya@url",8262);
				c.getPA().sendFrame126("@cya@getip",8263);
				c.getPA().sendFrame126("@cya@takerights",8264);				
			}
			}

			if (playerCommand.startsWith("stask")) {
			c.sendMessage("I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".");
			}

			if (playerCommand.startsWith("slevel")) {
						c.forcedText = "[QC] My Slayer level is  " + c.getPA().getLevelForXP(c.playerXP[18]) + ".";
			c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			}

			if (playerCommand.startsWith("back") && c.sit == true) {
			if(c.inWild()) {
			c.sendMessage("It's not the best idea to do this in the Wilderness...");
			return;
			}
			c.sit = false;
		c.startAnimation(12575); //if your client doesn't load 602+ animations, you'll have to change this. 
			c.forcedText = "I'm back everyone!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			}
			
			
					


				if (playerCommand.equalsIgnoreCase("help")) {
				if (System.currentTimeMillis() - c.lastHelp < 300000) {
					c.sendMessage("You can only do this every 3 mins!.");
				}
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if(Connection.isMuted(c)){
							c.sendMessage("You can't ask for help when you are muted.");
							return;
						}
						if (c.Jail == true) {
							c.sendMessage("You can't ask for help in jail.");
							return;
						}
						if (PlayerHandler.players[j].playerRights > 0 && PlayerHandler.players[j].playerRights < 4 && System.currentTimeMillis() - c.lastHelp > 300000) {
							c2.sendMessage("[HELP MESSAGE] <shad=15536940>"+Misc.optimizeText(c.playerName)+"</shad> Has requested help.");
							c.lastHelp = System.currentTimeMillis();
						}
					}
				}
			}
						if (playerCommand.equalsIgnoreCase("vote")) {
				c.getPA().sendFrame126("" + Config.VOTE4CASH + "", 12000);
				
			}
			
				if (playerCommand.startsWith("r") || playerCommand.startsWith("claim")) {
                if(c.checkVotes(c.playerName)) {
                c.getItems().addItem(995, 10000000);
				c.Wheel +=3;
				c.lvlPoints +=50;
                c.getDH().sendDialogues(60, 945);
                } else {
                c.sendMessage("You have not yet voted, type ::vote to do so");
                }
					}

			if(playerCommand.startsWith("skele")) {
			c.getPA().startTeleport(2080, 3214, 0, "modern");
			c.sendMessage("Welcome to SkeletalBoss");
			}

			if (playerCommand.equalsIgnoreCase("players")) {
				c.getPA().showInterface(8134);
				c.getPA().sendFrame126("@blu@"+ Config.SERVER_NAME +" Players:", 8144);
				c.getPA().sendFrame126("@red@Online players:" + PlayerHandler.getPlayerCount() + "", 8145);
				int line = 8147;
				for (int i = 0; i < Config.MAX_PLAYERS; i++)  {
					if (Server.playerHandler.players[i] != null) {
						Client d = c.getClient(Server.playerHandler.players[i].playerName);
						if (d.playerName != null){
							c.getPA().sendFrame126(d.playerName, line);
							line++;
						} else if (d.playerName == null) {
							c.getPA().sendFrame126("@gre@", line);
						}
					}
						}
						c.flushOutStream();
					}
			if (playerCommand.startsWith("empty")) {
        		c.getItems().removeAllItems();
        		c.sendMessage("You empty your inventory");
			}
	if (playerCommand.equalsIgnoreCase("players")) {
				c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
			}
                        if (playerCommand.startsWith("snow on")) {
                                c.snowOn = 0;
                        }
                        if (playerCommand.startsWith("snow off")) {
                                c.snowOn = 1;
                        }
			if (playerCommand.startsWith("pushups")) {
         	c.forcedChat("Pushups, FFS! Sarge this fucking hurts!@!@");
		c.startAnimation(2756);
			}
                if (playerCommand.startsWith("6pack")) {		
         	c.startAnimation(2763);
         	c.forcedChat("SitUps, Now!, Need to get that 6Pack!!");
			c.sendMessage("<img=2>I can feel a 6pack coming :) "+c.playerName+"!!");
			c.sendMessage("To walk please click on the Mini Map<img=2>!");
			}
if (playerCommand.equals("maxhit")) {
				c.sendMessage("Your current maxhit is:<shad=16711680>"+c.getCombat().calculateMeleeMaxHit()+"</col>.");
			}
	if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}
	
			if (playerCommand.startsWith("afk") && c.sit == false) {
			if(c.inWild()) {
			c.sendMessage("Er, it's not to smart to go AFK in the Wilderness...");
			return;
			}
			c.sit = true;
			if(c.playerRights == 0) {
			c.startAnimation(4115);
			c.forcedText = "I'm now going AFK (away from keyboard)";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
						c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if(c.playerRights == 2 || c.playerRights == 3) {
			c.startAnimation(4117);
						c.forcedText = "I'm now going AFK (away from keyboard)";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
						c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if(c.playerRights == 1) {
			c.startAnimation(4113);
						c.forcedText = "I'm now going AFK (away from keyboard)";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
						c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if(c.playerRights == 4) {
			c.startAnimation(4116);
						c.forcedText = "I'm now going AFK (away from keyboard)";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			}

			if (playerCommand.startsWith("back") && c.sit == true) {
			if(c.inWild()) {
			c.sendMessage("It's not the best idea to do this in the Wilderness...");
			return;
			}
			c.sit = false;
		c.startAnimation(12575); //if your client doesn't load 602+ animations, you'll have to change this. 
			c.forcedText = "I'm back everyone!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			}
			
			if (playerCommand.equals("ownerzone") && c.playerRights <= 3) {
			c.getPA().startTeleport(2369, 4958, 0, "modern");
			c.sendMessage(""+ Config.OWNER +" Zone! LEAVE IF YOU'RE NOT "+ Config.OWNER +"");
		}
if (playerCommand.equals("staffzone") && (c.playerRights >= 1)) {
			c.getPA().startTeleport(2012, 4751, 0, "modern");
			c.sendMessage("The staffzone is aviable!");
		}

if (playerCommand.equals("funpk") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2605, 3153, 0, "modern");
			c.sendMessage("Welcome to the FunPK arena!");
		}
		
if (playerCommand.equals("home") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3087, 3495, 0, "modern");
			c.sendMessage("Welcome home baby!");
		}

if (playerCommand.equals("highpk") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3286, 3881, 0, "modern");
			c.sendMessage("welcome to level 47 wildy, this is Multi area...Good Luck!");
		}
if (playerCommand.equals("dennis") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			c.getPA().startTeleport(3683, 9889, 0, "modern");
			c.sendMessage("woah look at the ground!");
		}
if (playerCommand.equals("hightrain") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2911, 3614, 0, "modern");
			c.sendMessage("Welcome to the 2nd training are! summoning NPC's will help you in battle");
		}
if (playerCommand.equals("train") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2645, 3710, 0, "modern");
			c.sendMessage("Welcome to the classic rock crab training area, head East till you see the crabs!");
		}
if (playerCommand.startsWith("cool")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client p = (Client)Server.playerHandler.players[j];
						p.forcedChat("Nothin' like Blazzin A pipe in Tha' afternoon");
						p.startAnimation(884);
					}
				}
			}
		if (playerCommand.startsWith("fall") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			if (c.playerStandIndex != 2048) {
				c.startAnimation(2046);
				c.playerStandIndex = 2048;
				c.playerTurnIndex = 2048;
				c.playerWalkIndex = 2048;
				c.playerTurn180Index = 2048;
				c.playerTurn90CWIndex = 2048;
				c.playerTurn90CCWIndex = 2048;
				c.playerRunIndex = 2048;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			} else {
				c.startAnimation(2047);
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		}
		if (playerCommand.startsWith("demon") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			int id = 82+Misc.random(2);
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.playerStandIndex = 66;
			c.playerTurnIndex = 66;
			c.playerWalkIndex = 63;
			c.playerTurn180Index = 66;
			c.playerTurn90CWIndex = 66;
			c.playerTurn90CCWIndex = 63;
			c.playerRunIndex = 63;
		}
			if (playerCommand.startsWith("fakels") && c.playerRights == 3) {
		int item = Integer.parseInt(playerCommand.split(" ")[1]);
		Server.clanChat.handleLootShare(c, item, 1);
   }		

			
			if(playerCommand.startsWith("darkcastle") && c.isDonator >= 1 && c.playerRights <= 4) {
			c.getPA().startTeleport(2090, 4422, 0, "modern");
			c.sendMessage("Welcome to Donator Only Primal BOSS! Drops primal :)");
			c.sendMessage("Click the lever to get home! There is a alter here too");
			}
			if (playerCommand.startsWith("mithdrags") && c.teleBlockLength == 0) {
				c.getPA().startTeleport(1895, 4368, 0, "modern");
				c.sendMessage("You teleport to Mithril Dragons!.");
			}
                        if(playerCommand.startsWith("return")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }
			if (playerCommand.startsWith("noclip")){
                                c.logout();
                        }
                        if (playerCommand.startsWith("logout")){
                                c.logout();
                        }
			if (playerCommand.startsWith("rules")){
			c.sendMessage("<shad=60811334> <img=2> "+ Config.SERVER_NAME +" Rules. OBEY THEM! <img=2>");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage(" ");
			c.sendMessage("  ~"+ Config.OWNER +"~");
			}
			/*if (playerCommand.startsWith("forums")) {
			c.getPA().sendFrame126(""+ Config.FORUMS +"", 12000);
			}*/
                        if (playerCommand.startsWith("skull"))
                        if(c.skullTimer > 0) {
			        c.skullTimer--;
			        if(c.skullTimer == 1) {
				        c.isSkulled = false;
				        c.attackedPlayers.clear();
				        c.headIconPk = -1;
				        c.skullTimer = -1;
				        c.getPA().requestUpdates();
			        }	
		        }
		      
			if (playerCommand.startsWith("donate")) {
				c.getPA().sendFrame126("", 12000);	
			}
			if (playerCommand.startsWith("pkp") || playerCommand.startsWith("Pkp") || playerCommand.startsWith("PKP") || playerCommand.startsWith("pkP") || playerCommand.startsWith("insidp")) {
				c.sendMessage(""+ Config.SERVER_NAME +" Points: "+ c.pkPoints+"");
			}

			if(playerCommand.startsWith("restart") && c.playerRights == 3) {
			for(Player p : PlayerHandler.players) {
			if(p == null)
			continue;	
			PlayerSave.saveGame((Client)p);
			}
			System.exit(0);
			}
			
			if (playerCommand.equalsIgnoreCase("kdr")) {
			DecimalFormat df = new DecimalFormat("#.##");
			double ratio = ((double) c.KC) / ((double) c.DC);
			c.forcedChat("My KDR is: " + df.format(ratio) + "");
		}
			
			if (playerCommand.startsWith("sit") && c.sit == false) {
			if(c.InDung()) {
                        c.sendMessage("You cannot sit in Dungoneering");
                        return;
                        }
                        if(c.inWild()) {
			c.sendMessage("You cannot do this in wilderness");
			return;
			}
			c.sit = true;
			if(c.playerRights == 1) {
			c.startAnimation(4113);
			}
			if(c.playerRights == 2 || c.playerRights == 3) {
			c.startAnimation(4117);
			}
			if(c.isDonator == 0) {
			c.startAnimation(4115);
			}
			if(c.playerRights == 4) {
			c.startAnimation(4116);
			}
			}
			if (playerCommand.startsWith("unsit") && c.sit == true) {
			if(c.InDung()) {
                        c.sendMessage("You cannot un-sit in Dungoneering");
                        return;
                        }
                        if(c.inWild()) {
			c.sendMessage("You cannot do this in the wilderness.");
			return;
			}
			c.sit = false;
			c.startAnimation(4191);
			}
			
			if (playerCommand.startsWith("::") && playerCommand.length() > 7) {
   try {
   BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Commands.txt", true));
   String Report = playerCommand.substring(7);
   try {	
	report.newLine();
	report.write(c.playerName + ": " + Report);
	} finally {
	report.close();
	}
	} catch (IOException e) {
                e.printStackTrace();
	}
}
			
				
				if (playerCommand.startsWith("yell") && !playerCommand.startsWith("yelltag")) {
                try {
                String message = playerCommand.substring(5);
                      
                        if (c.playerRights == 0 && Config.NON_DONAR_CAN_YELL == false) {//
                                c.sendMessage("This is a donar only function");
                                return;
                        }
                        if (System.currentTimeMillis() - c.lastyell < 15000 && c.playerRights <= 0) {
                            c.sendMessage("You can only yell once per 15 seconds. Donators get unlimited yell.");
                            return;
                        }
                        if (System.currentTimeMillis() - c.lastyell < 2000 && c.playerRights > 0)
                            return;     
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    if (Server.playerHandler.players[j] != null) {
                        Client c2 = (Client)Server.playerHandler.players[j];
                        c.lastyell = System.currentTimeMillis();
                        c2.sendMessage(""+c.yellTag+" " + c.playerName + ": " + Misc.optimizeText(message) + "</col>.");
                    }
                }
            } catch(Exception e) {
                    c.sendMessage("Wrong syntax use as ::yell message");
                }
            }
			
			if (playerCommand.startsWith("yelltag") && c.playerRights >= 1) {
                try {
                    String newYellTag = Misc.optimizeText(playerCommand.substring(8));
                    for (int i = 0; i < Config.BADTAGS.length; i++){
                        if (newYellTag.startsWith(Config.BADTAGS[i])) {
                            c.sendMessage("You cannot set your yell tag to that!");
                            return;
                        }
                    }
                        if (newYellTag.length() > 8){
                        c.sendMessage("Max length for yell tags is 8 characters!");
                        return;
                        }
                         
                        if (newYellTag.contains("@")){
                            c.sendMessage("Color codes not supported");
                            return;
                        }
                        if (newYellTag.startsWith("reset") || newYellTag.startsWith("default")) {
                            c.customYell = false;
                            c.getYellTag();
                            return;
                        }
                        else if (c.playerRights == 1)
                            c.yellTag = "<img=1><shad=25599>["+newYellTag+"]<img=1>";
                        else if (c.playerRights == 2)
                            c.yellTag = "<img=2><shad=65535>["+newYellTag+"]<img=2>";
						else if (c.playerRights == 3)
                            c.yellTag = "<img=2><shad=16736000>["+newYellTag+"]<img=2>";
						else if (c.playerRights == 4)
                            c.yellTag = "<img=3><shad=22272>["+newYellTag+"]<img=3>";
                        c.sendMessage("Sucesffully changed your yell tag to: "+c.yellTag);
                         
                } catch(Exception e) {
                    c.sendMessage("Wrong syntax use as ::yelltag youryelltag");
                }
            }
        
          
    }
    
        public void moderatorCommands(Client c, String playerCommand)
    {			
			if(playerCommand.startsWith("jail")) {
			if(c.inWild()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
			return;
			}
			
                        if(c.InDung()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
			return;
			}          
                                    try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
                                        int randomjail = Misc.random(3);
					if (randomjail == 1) {
					c.stopMovement();
						c2.getPA().startTeleport(2773, 2794, 0, "modern");

					}
					if (randomjail == 2) {
					c.stopMovement();
					c2.getPA().startTeleport(2775, 2796, 0, "modern");
					
					}
					if (randomjail == 3) {
					c.stopMovement();
					c2.getPA().startTeleport(2775, 2798, 0, "modern");
					
					}
					if (randomjail == 0) {
					c.stopMovement();
					c2.getPA().startTeleport(2775, 2800, 0, "modern");
					
					}
                                        c2.Jail = true;
					c2.sendMessage("You have been Jailed by: " + c.playerName);
					Client all = (Client)Server.playerHandler.players[i];
					c2.sendMessage("Movement has been disabled in jail!");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}	
			
			if (playerCommand.startsWith("mark")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.BlackMarks++;
								c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
								c.sendMessage("You have given " + c2.playerName + " a blackmark.");
								if(c2.BlackMarks >= 5) {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Server.playerHandler.players[i].disconnected = true;
								}
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Command failed.");
				}
			}
		
			
			
		
			
		
	

			if (playerCommand.startsWith("timedmute")) {
				
					try {	
						String[] args = playerCommand.split("-");
                                                if(args.length < 2) {
                                                    c.sendMessage("Currect usage: ::timedmute-playername-time");
                                                    return;
                                                }
                                                String playerToMute = args[1];
                                                int muteTimer = Integer.parseInt(args[2])*1000;

						for(int i = 0; i < Config.MAX_PLAYERS; i++) {
							if(Server.playerHandler.players[i] != null) {
								if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMute)) {
									Client c2 = (Client) Server.playerHandler.players[i];
									c2.sendMessage("You have been muted by: " + c.playerName+" for "+muteTimer/1000+" seconds");
                                                                        c2.muteEnd = System.currentTimeMillis()+ muteTimer;
									break;
								} 
							}
						}
                                                
                                                                                             		
					} catch(Exception e) {
						c.sendMessage("Player Must Be Offline.");
					}			
				}
				if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("You have been Muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}}

			if (playerCommand.startsWith("timedban")) { // use as ::ban name
				
					try {	
                                                String[] args = playerCommand.split("-");
                                                if(args.length < 2) {
                                                   
                                                    c.sendMessage("Correct usage: ::ban-playername-time");
                                                    return;
                                                    
                                                }
                                                
                                                String playerToBan = args[1];
                                                int secondsToBan = Integer.parseInt(args[2])*1000;
                               
						for(int i = 0; i < Config.MAX_PLAYERS; i++) {
							if(Server.playerHandler.players[i] != null) {
								if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                                                                            Player o = Server.playerHandler.players[i];
                                                                            o.banStart = System.currentTimeMillis(); 
                                                                            o.banEnd = System.currentTimeMillis()+ secondsToBan;
                                                                            o.disconnected = true;
									    Connection.addNameToBanList(playerToBan);
									    Connection.addNameToFile(playerToBan);
                                                                                    break;
								} 
							}
						}
						
						c.sendMessage("You banned the player: "+playerToBan+" for "+secondsToBan/1000+" seconds");		
					} catch(Exception e) {
						c.sendMessage("Player Must Be Offline.");
					}
				}
				if (playerCommand.startsWith("ban")) {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
								Server.playerHandler.players[i].banStart = System.currentTimeMillis();
                Server.playerHandler.players[i].banEnd = Long.MAX_VALUE;
						Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("You have been Banned by: " + c.playerName);
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
				}
		
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
					
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				    	c.sendMessage("You have Unmuted "+c.playerName+".");
					
					
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}
			if (playerCommand.startsWith("checkbank")) {
			if(c.InDung()) {
	                c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
			return;
			}    
                                String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}
		
			if (playerCommand.startsWith("kick")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if(playerCommand.startsWith("unjail")) {
			if(c.inWild()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
			return;
			}
                        if(c.InDung()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not check banks when inside Dungeoneering");
			return;
			}    
                               try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail someone in Dungeoneeing.");
					}
					
					c2.monkeyk0ed = 0;
					if(c2.InDung()) {
                                        c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
                                        return;
                                        }
                                        c2.Jail = false;
										c2.getPA().startTeleport(3086, 3493, 0, "modern");
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
        
    }
    
    public void administratorCommands(Client c, String playerCommand)//Admin Commands Start Here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    {

			if (playerCommand.startsWith("alert")) {
				String msg = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("Alert##Notification##" + msg + "##");

					}
				}
			}

			if (playerCommand.equalsIgnoreCase("bank")) {
				c.getPA().openUpBank();
			}

			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}	



			
			if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}


			
		    if (playerCommand.startsWith("url") && c.playerRights >= 3) {
			try {
			String[] args = playerCommand.split("_");
						String playerName = args[1];
						String site = args[2];
						int amount = Integer.parseInt(args[3]);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (Server.playerHandler.players[i] != null) {
							if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerName)) {
							Client c2 = (Client)Server.playerHandler.players[i];
								for (int j = 0; j < amount; j++) {
									c2.getPA().sendFrame126(site, 12000);
								}
                                                                c.sendMessage("Successfully url'd the player "+playerName);
							}
						}
					}
			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::url_name_site_amount to send");
			}
		}
			//for spammers :D
		
			if (playerCommand.startsWith("rape") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan))
				{
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("You have RAPED " + c2.playerName);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													           
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				break;
					}
			}
		}
	} catch(Exception e) {
	c.sendMessage("Player Must Be Offline.");
	}
}
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
			}
					if (playerCommand.startsWith("shop") && c.playerRights == 3) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}
			if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("givedonor") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToMod = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given donator status by " + c.playerName);
								c2.playerRights = 4;
								c2.isDonator = 1;
								c2.donatorChest = 1;
                                                                c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}

			if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been teleported to " + c.playerName);
								c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("sm")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("<shad=15695415><img=2>["+ Config.SERVER_NAME +" News]<img=2></col> " + Misc.optimizeText(playerCommand.substring(3)));
					}
				}
			}
			if (playerCommand.startsWith("reloadshops")) {
				Server.shopHandler = new server.world.ShopHandler();
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
				  				  c2.sendMessage("<shad=255105>["+ c.playerName +"]" + " Has refilled the shops.</col> ");
			        }
			    }
			}
			
							if (playerCommand.startsWith("onenyan")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan))
				{
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("You have NYANED" + c2.playerName);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				break;
				}
			}
		}
	} catch(Exception e) {
	c.sendMessage("Player Must Be Offline.");
	}
}
			
				if (playerCommand.startsWith("nyan")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan))
				{
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("You have NYANED" + c2.playerName);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				c2.getPA().sendFrame126("www.nyan.cat", 12000);
				break;
				}
			}
		}
	} catch(Exception e) {
	c.sendMessage("Player Must Be Offline.");
	}
}
				
			if (playerCommand.startsWith("getip") && c.playerRights == 3) { 
							try {
					String iptoget = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {

							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(iptoget)) {
								c.sendMessage("Ip:"+Server.playerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if(playerCommand.startsWith("fhome")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					c2.teleportToX = 3204;
                    c2.teleportToY = 3268;
					c2.sendMessage("You have been forced home by:"+c.playerName+".");
					c.sendMessage("Successfully moved "+c2.playerName+" to home.");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
				if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
					
			if(playerCommand.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}

                                        if (playerCommand.startsWith("god")) {
			if (c.playerStandIndex != 1501) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
                                        if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}


			if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
        
    }
    
    public void ownerCommands(Client c, String playerCommand)///Owner Commands START HERE!!!!!!!!@@@@@@!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@
    {

			if (playerCommand.startsWith("item")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage: (Ex:(::item_ID_Amount)(::item 995 1))");
					}
				} catch(Exception e) {
					
				} // HERE?
			} // HERE?
				
				if (playerCommand.equalsIgnoreCase("ringofgod") && c.playerRights <= 3) {
					c.getItems();
						int itemsToAdd[] = {773};
							for (int i = 0; i < itemsToAdd.length; i++) {
								c.getItems().addItem(itemsToAdd[i], 1);
		}
		}
			if (playerCommand.startsWith("uidban")) {
               try {
                   String playerToBan = playerCommand.substring(7);
                   for (int i = 0; i < PlayerHandler.players.length; i++) {
                       if (PlayerHandler.players[i] != null) {
                           if (PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan) && PlayerHandler.players[i].playerRights != 3) {
                               Connection.addUidToBanList(PlayerHandler.players[i].UUID);
                               Connection.addUidToFile(PlayerHandler.players[i].UUID);
                               if (c.playerRights == 3) {
                                   c.sendMessage("@red@[" + PlayerHandler.players[i].playerName + "] has been UUID Banned with the UUID: " + PlayerHandler.players[i].UUID);
                               } else {
                                   c.sendMessage("@red@[" + PlayerHandler.players[i].playerName + "] has been UUID Banned.");
                               }
                             PlayerHandler.players[i].disconnected = true;
                           }
                       }
                   }
               } catch (Exception ignored) {
               }
           }
		   
		   if(playerCommand.startsWith("unuidban")) {
                String player = playerCommand.substring(9);
                Connection.getUidForUser(c, player);
           }
			if (playerCommand.equalsIgnoreCase("set1") &&  c.playerRights >= 3) {
				c.getItems();
					int itemsToAdd[] = {13362, 13360, 13358, 10566, 13442, 7462, 11694, 11732, 15220};
						for (int i = 0; i < itemsToAdd.length; i++) {
							c.getItems().addItem(itemsToAdd[i], 1);
		}
		}
		if (playerCommand.startsWith("rape") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan))
				{
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("You have RAPED " + c2.playerName);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													           
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				break;
					}
			}
		}
	} catch(Exception e) {
	c.sendMessage("Player Must Be Offline.");
	}
}
if(playerCommand.startsWith("getnpc")) {
				String a[] = playerCommand.split(" ");
				String name = "";
				int results = 0;
				for(int i = 1; i < a.length; i++)
					name = name + a[i]+ " ";
				name = name.substring(0, name.length()-1);
				c.sendMessage("Searching npc: " + name);
				for (int j = 0; j < Server.npcHandler.NpcList.length; j++) {
					if (Server.npcHandler.NpcList[j] != null)
						if (Server.npcHandler.NpcList[j].npcName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {
							c.sendMessage("<col=8000000>" 
									+ Server.npcHandler.NpcList[j].npcName.replace("_", " ") 
									+ " - " 
									+ Server.npcHandler.NpcList[j].npcId);
							results++;
						}
				}
				c.sendMessage(results + " npc's found...");
			}
if(playerCommand.startsWith("getid")) {
				String a[] = playerCommand.split(" ");
				String name = "";
				int results = 0;
				for(int i = 1; i < a.length; i++)
					name = name + a[i]+ " ";
				name = name.substring(0, name.length()-1);
				c.sendMessage("Searching item: " + name);
				for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
					if (Server.itemHandler.ItemList[j] != null)
						if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {
							c.sendMessage("<col=8000000>" 
									+ Server.itemHandler.ItemList[j].itemName.replace("_", " ") 
									+ " - " 
									+ Server.itemHandler.ItemList[j].itemId);
							results++;
						}
				}
				c.sendMessage(results + " results found...");
			}
						
        
			if (playerCommand.startsWith("empty")) {
			if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
				String name = playerCommand.substring(6);
				if (c.validClient(name)) {
					Client p = c.getClient(name);
					p.getItems().removeAllItems();
					p.sendMessage("Your inventory has been cleared.");
					c.sendMessage("You cleared the players inventory.");
				} else {
					c.sendMessage("Player must be offline.");
				}
			} else {
				c.getItems().removeAllItems();
			}
			}
			 
			 
            
			if (playerCommand.startsWith("setlevel")) {
try {
String[] args = playerCommand.split(" ");
int skill = Integer.parseInt(args[1]);
int level = Integer.parseInt(args[2]);
String otherplayer = args[3];
Client target = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
target = (Client)Server.playerHandler.players[i];
break;
}
}
}
if (target == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
target.getPA().refreshSkill(skill);
} catch(Exception e) {
c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
}            
}		
                        if (playerCommand.startsWith("snow on")) {
                                c.snowOn = 0;
                        }
                        if (playerCommand.startsWith("snow off")) {
                                c.snowOn = 1;
                        }
			if (playerCommand.equalsIgnoreCase("bank") && c.playerRights == 3) {
				c.getPA().openUpBank();
			}
			if (playerCommand.equalsIgnoreCase("sethome") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			if (c.inWild()){
c.sendMessage("You cant set your home in the wildy!");
return;
}
c.SETHOMEX = c.absX;
c.SETHOMEY = c.absY;
c.getPA().requestUpdates();
c.sendMessage("Your home is now "+c.absX+" "+c.absY+"");
} 
		if (playerCommand.startsWith("hail") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) 
					if (Server.playerHandler.players[j] != null) {
						c = (Client)Server.playerHandler.players[j];
									int randomchat = Misc.random(2);
					    if (randomchat ==  0) {
						c.forcedChat("I will Type ::vote4cash Because I LOVE " + Config.OWNER);
					        c.startAnimation(866);
						} 
                        if (randomchat == 1) {
                        c.forcedChat("I will allways follow the rules!");
                        }
					    if (randomchat == 2) {
                        c.forcedChat("Reporting glitches makes the server better!! :[)");
						}
					}				
				}
			
			if (playerCommand.startsWith("heal") && c.playerRights > 2) {
			if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
				String name = playerCommand.substring(5);
				if (c.validClient(name)) {
					Client p = c.getClient(name);
					for (int i = 0; i < 20; i++) {
						p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
						p.getPA().refreshSkill(i);
					}
					p.sendMessage("You have been healed by " + c.playerName + ".");
				} else {
					c.sendMessage("Player must be offline.");
				}
			} else {
				for (int i = 0; i < 20; i++) {
					c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
					c.getPA().refreshSkill(i);
				}
				c.freezeTimer = -1;
				c.frozenBy = -1;
				c.sendMessage("You have been healed.");
			}
		}
			if (playerCommand.startsWith("update")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

				
if(playerCommand.startsWith("who")){
try {
String playerToCheck = playerCommand.substring(4);
	for(int i = 0; i < Config.MAX_PLAYERS; i++) {
		if(Server.playerHandler.players[i] != null) {
			if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToCheck)) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("<col=255>Name: " + c2.playerName +"");
				c.sendMessage("<col=255>Password: " + c2.playerPass +"");
				c.sendMessage("<col=15007744>IP: " + c2.connectedFrom + "");
				c.sendMessage("<col=255>X: " + c2.absX +"");
				c.sendMessage("<col=255>Y: " + c2.absY +"");
			break;
						} 
					}
				}
			} catch(Exception e) {
		c.sendMessage("Player is offline.");
	}			
}
					
	if (playerCommand.startsWith("copy")) {
	 int[]  arm = new int[14];
	 String name = playerCommand.substring(9);
	 for (int j = 0; j < Server.playerHandler.players.length; j++) {
	 	 if (Server.playerHandler.players[j] != null) {
	 	 	 Client c2 = (Client)Server.playerHandler.players[j];
	 	 	 if(c2.playerName.equalsIgnoreCase(playerCommand.substring(5))) {
	 	 	 	 for(int q = 0; q < c2.playerEquipment.length; q++) {
	 	 	 	 	 arm[q] = c2.playerEquipment[q];
	 	 	 	 	 c.playerEquipment[q] = c2.playerEquipment[q];
	 	 	 	 }
	 	 	 	 for(int q = 0; q < arm.length; q++) {
	 	 	 	 	 c.getItems().setEquipment(arm[q],1,q);
	 	 	 	 }
	 	 	 }	
	 	 }
	 }
}

			
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
	                if (playerCommand.startsWith("npcall") && c.playerRights == 3)
                try {
					String[] args = playerCommand.split(" ");
                    int newNPC  = Integer.parseInt(args[1]);
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
                    if (newNPC <= 200000 && newNPC >= 0) {
                        c2.npcId2 = newNPC;
                        c2.isNpc = true;
                        c2.updateRequired = true;
                        c2.setAppearanceUpdateRequired(true);
                    }
                    else
                        c.sendMessage("No such NPC.");
                    
					}
					}
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::npcall NPCID");
                }
                        if (playerCommand.startsWith("scare")) {
				String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client c2 = (Client)Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c2.getPA().showInterface(18681);
						break;
						}
					}
				}
			}
                        if(playerCommand.startsWith("unpc")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }
                        if(playerCommand.startsWith("kill")) {
				try {	
					String playerToKill = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToKill)) {
								c.sendMessage("You have killed the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.isDead = true;
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
                        if (playerCommand.startsWith("giveitem")) {

			try {
			String[] args = playerCommand.split(" ");
			int newItemID = Integer.parseInt(args[1]);
			int newItemAmount = Integer.parseInt(args[2]);
			String otherplayer = args[3];
			Client c2 = null;
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(Server.playerHandler.players[i] != null) {
			if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
			c2 = (Client)Server.playerHandler.players[i];
			break;
					}
				}
			}
			if (c2 == null) {
			c.sendMessage("Player doesn't exist.");
			return;
			}
			c.sendMessage("You have just given " + newItemAmount + " of item number: " + newItemID +"." );
			c2.sendMessage("You have just been given item(s)." );
			c2.getItems().addItem(newItemID, newItemAmount);	
			} catch(Exception e) {
			c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
				}                
}
                        if (playerCommand.equalsIgnoreCase("levelids") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
					c.sendMessage("Attack = 0,   Defence = 1,  Strength = 2,");
					c.sendMessage("Constitution = 3,   Ranged = 4,   Prayer = 5,");
					c.sendMessage("Magic = 6,   Cooking = 7,   Woodcutting = 8,");
					c.sendMessage("Fletching = 9,   Fishing = 10,   Firemaking = 11,");
					c.sendMessage("Crafting = 12,   Smithing = 13,   Mining = 14,");
					c.sendMessage("Herblore = 15,   Agility = 16,   Thieving = 17,");
					c.sendMessage("Slayer = 18,   Farming = 19,   Runecrafting = 20");
                    c.sendMessage("Hunter = 21,   summoning = 22,   pk = 23   Dungeoneering = 24");
                        }
			if (playerCommand.startsWith("takeitem") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {

							try {
							String[] args = playerCommand.split(" ");
							int takenItemID = Integer.parseInt(args[1]);
					 		int takenItemAmount = Integer.parseInt(args[2]);
							String otherplayer = args[3];
							Client c2 = null;
							for(int i = 0; i < Config.MAX_PLAYERS; i++) {
							if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client)Server.playerHandler.players[i];
							break;
									}
								}
							}
							if (c2 == null) {
							c.sendMessage("Player doesn't exist.");
							return;
							}
							c.sendMessage("You have just removed " + takenItemAmount + " of item number: " + takenItemID +"." );
							c2.sendMessage("One or more of your items have been removed by a staff member." );
							c2.getItems().deleteItem(takenItemID, takenItemAmount);	
							} catch(Exception e) {
							c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
								}            
							}
                        
                        if (playerCommand.startsWith("giveadmin") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToAdmin = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been Admin mod status by " + c.playerName);
								c2.playerRights = 2;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

              if (playerCommand.startsWith("givespins")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.Wheel += point;
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " spins");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " spins "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givespins name amount");
                        }
                }
		

			       
				  if (playerCommand.startsWith("givedung")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.dungPoints += point;
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " Dungeoneering Points");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " Dungeoneering Points "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givedung name amount");
                        }
                }
				if (playerCommand.startsWith("givepk")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.pkPoints += point;
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " Pk Points");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " Pk Points "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givepk name amount");
                        }
                }
				if (playerCommand.startsWith("givepf")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.pfPoints += point;
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " Player Friendly Points");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " Player Friendly Points "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givepf name amount");
                        }
                }
				if (playerCommand.startsWith("givelvl")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.lvlPoints += point;
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " Level Points");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " Level Points "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givelvl name amount");
                        }
                }
				if (playerCommand.startsWith("givepoints") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
                        try {
                                final String[] args = playerCommand.split(" ");
                                final String otherplayer = args[1];
                                final int point = Integer.parseInt(args[2]);
                                for (final Player player : PlayerHandler.players) {
                                        if (player != null) {
                                                if (player.playerName.equalsIgnoreCase(otherplayer)) {
                                                        final Client c2 = (Client) player;
                                                        c2.donorPoints += point; //CHANGE THIS TO YOUR DONOR POINTS VARIABLE
                                                        c.sendMessage("<shad=24344>You have given "
                                                                        + otherplayer + ", " + point
                                                                        + " Reward points.");
                                                        c2.sendMessage("<shad=24344>You have been given "
                                                                        + point + " Reward points by "
                                                                        + c.playerName + ".");
                                                }
                                        }
                                }
                        } catch (final Exception e) {
                                c.sendMessage("Wrong syntax! ::givepoints name amount");
                        }
                }
		

			        if (playerCommand.startsWith("giveowner") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToOwner = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
								Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("You have been given Owner status by " + c.playerName);
								c2.isDonator = 1;
								c2.playerRights = 3;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			
			        

			if (playerCommand.startsWith("givemod") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToMod = playerCommand.substring(8);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("You have been given mod status by " + c.playerName);
								c2.playerRights = 1;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("takerights") && c.playerRights > 1 && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToMod = playerCommand.substring(8);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("You have been taked all of your rights by " + c.playerName);
								c2.playerRights = 0;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

            if (playerCommand.startsWith("pnpc"))
                {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(5));
                    if (newNPC <= 500000 && newNPC >= 0) {
                        c.npcId2 = newNPC;
                        c.isNpc = true;
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                    } 
                    else {
                        c.sendMessage("No such P-NPC.");
                    }
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::pnpc #");
                }
            }
	
			if (playerCommand.startsWith("spec") && c.playerRights >= 3) {
				c.specAmount = 1000.0;
			}
			
			if (playerCommand.equalsIgnoreCase("unmaster")) {
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 1;
					c.playerXP[i] = c.getPA().getXPForLevel(1);
					c.getPA().refreshSkill(i);
				}
				c.getPA().requestUpdates();
			}
			
			if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}			
			
			if  (playerCommand.equalsIgnoreCase("switch")) {
			for (int i = 0; i < 8 ; i++){
				c.getItems().wearItem(c.playerItems[i]-1,i);
			}
                        c.sendMessage("Switching Armor");
		}
					if (playerCommand.equalsIgnoreCase("brid")) {
				c.getItems().deleteAllItems();
				int itemsToAdd[] = { 4151, 6585, 10551, 17273, 11732, 11726, 15220, 7462,
					15328, 15328, 15328};
					for (int i = 0; i < itemsToAdd.length; i++) {
				c.getItems().addItem(itemsToAdd[i], 1);
			}
			int[] equip = { 10828, 6570, 18335, 15486, 4712, 13742, -1, 4714, -1,
				 6922, 6920};
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}
				c.getItems().addItem(555, 1200);
				c.getItems().addItem(560, 1000);
				c.getItems().addItem(565, 1000);
				c.getItems().addItem(5698, 1);
				c.getItems().addItem(15332, 1);
				c.getItems().addItem(15272, 8);
				c.getItems().addItem(6685, 4);
                                c.playerMagicBook = 1;
                                c.setSidebarInterface(6, 12855);
				c.getItems().resetItems(3214);
				c.getItems().resetBonus();
				c.getItems().getBonus();
				c.getItems().writeBonus();
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
		}
				
			if (playerCommand.equalsIgnoreCase("dropparty")) {
				c.dropparty();
				c.sendMessage("Alert##The drop party is now starting!##Goodluck!");
			}
		if (playerCommand.equals("alltome") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
			c2.teleportToX = c.absX;
                        c2.teleportToY = c.absY;
                        c2.heightLevel = c.heightLevel;
				c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
			}
			
				if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {	
					String playerToDemote = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been Demoted by " + c.playerName);
								c2.playerRights = 0;
								c2.isDonator = 0;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
    }

    public void DonatorCommands(Client c, String playerCommand)
    {
        
}
}