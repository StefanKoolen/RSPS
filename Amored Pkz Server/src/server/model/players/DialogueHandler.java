package server.model.players;

import server.model.players.packets.ClickItem;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		
		
                case 850:
    sendOption4("Normal Mode", "Coming Soon", "Coming Soon", "Coming Soon");
c.dialogueAction = 850;
break;
		//Shop Dialogues
		case 90:
			sendOption5("", "Weapons Shop", "", "Armour Shop", "");
			c.dialogueAction = 90;
			c.dialogueId = 90;
			c.teleAction = -1;
		break;
		case 91:
			sendOption5("", "Weapons Shop", "", "Armour Shop", "");
			c.dialogueAction = 91;
			c.dialogueId = 91;
			c.teleAction = -1;
		break;
		case 92:
			sendOption5("", "Weapons Shop", "", "Armour Shop", "");
			c.dialogueAction = 92;
			c.dialogueId = 92;
			c.teleAction = -1;
		break;
		
		//end of shops dialogues
		
		//Flower Game Dialogue
		
		case 9999:
                      sendOption2("Pick", "Leave");
                      c.dialogueAction = 1000;
                      c.dialogueId = 999;
                      c.teleAction = -1;
                       
              break;
		
		//Tax Bags Upgrades Anti-Dupe
		
		case 365:
			sendOption2("Upgrade", "Take Cash");
			c.dialogueAction = 366;
			c.dialogueId = 365;
			c.teleAction = -1;
		break;
		
		case 366:
			sendOption2("Upgrade", "Take Cash");
			c.dialogueAction = 367;
			c.dialogueId = 366;
			c.teleAction = -1;
		break;
		
		case 367:
			sendOption2("Upgrade", "Take Cash");
			c.dialogueAction = 368;
			c.dialogueId = 367;
			c.teleAction = -1;
		break;
		
		
		//Milestone Capes Crafting
		
		case 400:
			sendOption5("10 Milestone Cape", "20 Milestone Cape", "30 Milestone Cape", "40 Milestone Cape", "More");
			c.dialogueAction = 401;
			c.dialogueId = 400;
			c.teleAction = -1;
		break;
		
		case 401:
			sendOption5("50 Milestone Cape", "60 Milestone Cape", "70 Milestone Cape", "80 Milestone Cape", "More");
			c.dialogueAction = 402;
			c.dialogueId = 401;
			c.teleAction = -1;
		break;
		
		case 402:
			sendOption5("90 Milestone Cape", "", "Completionist Cape", "", "Max Cape");
			c.dialogueAction = 403;
			c.dialogueId = 402;
			c.teleAction = -1;
		break;
		
		
		
		case 190:
			sendOption5("Sea Troll Queen", "Chaos Elemental", "Nomad", "Skele", "More");
			c.dialogueAction = 120;
			c.dialogueId = 190;
			c.teleAction = -1;
break;
case 191:
			sendOption5("Skele", "Option2", "Option3", "Option4", "Option5");
			c.dialogueAction = 121;
			c.dialogueId = 191;
			c.teleAction = -1;
		break;
case 600:
    sendNpcChat4("Halloween Tunnel", "This Is a very dangerous tunnel", "Be careful Down There!", "Are You Sure You Want To Enter?", c.talkingNpc, "Tunnel");
    c.nextChat = 601;
break;
case 601:
    sendOption2("Yes", "No");
    c.dialogueAction = 601;
break;
case 702:
                        sendNpcChat2("*Pshh* Hey Man This is secret.", "i have found these cool tunnels around here", c.talkingNpc, "Dragon Snowman");
                        c.nextChat = 703;
                break;
case 703:
                        sendNpcChat2("So im going to take u down one that u choose", "Choose wisley Now there both hard i saw dead bodys!", c.talkingNpc, "Dragon Snowman");
                        c.nextChat = 71;
                break;
case 507:
                        sendNpcChat2("Ho ho my god. You have a christmas cracker!", "please let me pull it!!!", c.talkingNpc, "Santa Claus");
                        c.nextChat = 508;
                break;
                case 508:
                        sendOption2("yes, lets do it!", "Hmm, I'll get back to you on that.");
                        c.dialogueAction = 508;
                        c.dialogueId = 508;
                        c.teleAction = -1;
                        c.nextChat = 509;
                break;
                case 509:
                        sendNpcChat2("Ho Ho Hoooo", "Merrrryyyy Christmas", c.talkingNpc, "Santa Claus");
                        c.nextChat = -1;
                break;
case 809:
                        sendNpcChat2("Hey "+c.playerName+" want to go to Dung?", "Now is Your chance to get yourself there!!", c.talkingNpc, "Dragon Snowman");
                        c.nextChat = 810;
                break;

		case 55:
		sendNpcChat4("Hello i am in charage of ArmoredPkz SOF", "This is for Donators only, even if your a donator",
			"You must have spins! Are You a donator and have spins?", "", c.talkingNpc, "ArmoredPkz SOF MASTER");
			c.nextChat = 56;
			break;
		case 56:
			sendOption2("Yes i am a donator and have spins", "No i do not have any spins");
			c.dialogueAction = 56;
			break;
		
		case 42:
		sendNpcChat4("Finaly!", "*cough*",
			"I have waited long for a warrior to aid me", "", c.talkingNpc, "Captain Donnie");
			c.nextChat = 43;
			break;
		case 43:
		sendNpcChat4("An army of undead zombies has taken over my home", "I need you to defeat them.",
			"You will be rewarded in the end if you succeed.", "", c.talkingNpc, "Captain Donnie");
			c.nextChat = 44;
			break;
		case 44:
			sendOption2("Okay let's do this", "No sorry that's too much for me.");
			c.dialogueAction = 44;
			break;

                case 185:
                        sendNpcChat4("Hello, im Lottie im in charge of ArmoredPkz's Lottery.", "Entering costs 5 million gp but you could win up to", "50 million gp! you can enter up to 5 times per draw.", "Would you like to enter the lottery?", c.talkingNpc, "Lottie");
                        c.nextChat = 186;
                break;
				
                case 186:
                        sendOption2("Yes i would like to enter!", "No, Id rather not.");
                        c.dialogueAction = 186;
                break;

		case 106:
				sendOption5("One 6-sided die", "Two 6-sided dice", "One 4-sided die", "One 8-sided die", "More...");
				c.dialogueAction = 106;
				c.teleAction = 0;
				c.nextChat = 0;
				break;

			case 107:
				sendOption5("One 10-sided die", "One 12-sided die", "One 20-sided die", "Two 10-sided dice for 1-100", "Back...");
				c.dialogueAction = 107;
				c.teleAction = 0;
				c.nextChat = 0;
				break;
                case 200:
			sendNpcChat4("Hello there "+c.playerName+"!"," I have the ability to reset your combat stats for free!","But remember, this is irreversable!","What would you like me to do?", c.talkingNpc, "Town Crier");
			c.nextChat = 210;
		break;
		case 210:
			sendOption4("Reset Defence", "Reset Prayer", "Reset Attack", "Reset All Combat Stats");
			c.dialogueAction = 42;
		break;
		case 230:
			sendNpcChat2("Congratulations!", "Your defence has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 240:
			sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 250:
			sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 260:
			sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 1337:
			sendOption2("PK'er Scoreboard", "Skiller Scoreboard");
			c.dialogueAction = 1337;
		break;

		case 80:
                        sendStatement("Should I tele you ?");
                        c.nextChat = 81;
                break;

                case 81:
                        sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
                        c.dialogueAction = 27;
                        c.nextChat = 0;
                break;
		
                case 38:
                        sendStatement("Congratulations, you open the chest and got a reward!");
                        c.nextChat = 0;
                break;

                case 37:
                        sendStatement("You need atleast 1 free inventory spaces! and a crystal key!");
                        c.nextChat = 0;
                break;	

				case 57:
                        sendStatement("You do not have any spins to use the SOF!!");
                        c.nextChat = 0;
                break;
				case 58:
                        sendStatement("Congratulations you have won an random item!");
                        c.nextChat = 0;
                break;
				case 59:
						sendStatement("You must be level 80 in fishing to go to the fishing guild!");
						c.nextChat = 0;
				break;
				case 60:
						sendStatement("You have Earned 10m, 3 Spins and 50 lvl points for Voting!");
						c.nextChat = 0;
				break;
		
		

		case 30:
			sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something..", "would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;	
			break;
	case 51:
			sendOption2("Castle Pk (14)",  "Clan War Zone (34)");
			c.dialogueAction = 51;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20","Kthxbai", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 20:
			sendOption4("Information", "Black Jack","Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("","Black Jack", "Five","");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4("The way we play this game is simple. The way you win is", 
					"You need to get a higher number than me and you win the", 
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 22;
					break;

		case 22:
			sendNpcChat4("", 
					"If i get 22+ I bust and I lose. If you get 21 then you have black", 
					"jack and you win double of what you bet.",
					"", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 0;
					break;

		case 23:
			sendNpcChat4("This is my own game which I made. It's pretty simple", 
					"and resembles poker but it's a lot different. The aim of this", 
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 24;
					break;
		case 24:
			sendNpcChat4("", 
					"To play this game you need to bet 1,000,000 coins. You", 
					"can win a lot of good items but also lose a lot of cash.",
					"", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 0;
					break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena");
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
               case 70:
			sendNpcChat4("I have found two secret tunnels!", "I saw a giant mole and some Red Dragons!", "Help me defeat them?", 
			"Would you like me, to bring you there?", c.talkingNpc, "Bonafido");
			c.nextChat = 71;
		break;
		case 810:
			sendOption3("Floor1", "Shop", "Floor2");
			c.dialogueAction = 18;
			c.dialogueId = 809;
			c.teleAction = -1;
		break;
		case 71:
			sendOption2("Giant Mole", "Red Dragons (43 Wildy)");
			c.dialogueAction = 13;
			c.dialogueId = 71;
			c.teleAction = -1;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Select an Option", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	private void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	private void sendNpcChat1(String s) {
		
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
