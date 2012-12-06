package server.model.players;

public class SkillGuides {

	public static void sendSkillInterface(Client client, int id[]) {
		client.getOutStream().createFrameVarSizeWord(53);
		client.getOutStream().writeWord(8847);
		client.getOutStream().writeWord(id.length);
		for (int i = 0; i < id.length; i++) {
			client.getOutStream().writeByte(1);
			if(id[i] > 0) {
				client.getOutStream().writeWordBigEndianA(id[i]+1);
			} else {
				client.getOutStream().writeWordBigEndianA(0);
			}
		}
		client.getOutStream().endFrameVarSizeWord();   
		client.flushOutStream();
	}
	
	private static int item[] = new int[31];

	public static void skillsInterface(Client client, String skill, 
					int item1ItemId, int item1lvl, String item1lvldef, 
					int item2ItemId, int item2lvl, String item2lvldef, 
					int item3ItemId, int item3lvl, String item3lvldef, 
					int item4ItemId, int item4lvl, String item4lvldef, 
					int item5ItemId, int item5lvl, String item5lvldef, 
					int item6ItemId, int item6lvl, String item6lvldef, 
					int item7ItemId, int item7lvl, String item7lvldef, 
					int item8ItemId, int item8lvl, String item8lvldef) {

		client.getPA().sendFrame126("@dre@"+skill, 8716);
		for(int i = 0;i<31;i++) {
			item[i] = 0;
		}
		item[0] = item1ItemId;
		item[1] = item2ItemId;
		item[2] = item3ItemId;
		item[3] = item4ItemId;
		item[4] = item5ItemId;
		item[5] = item6ItemId;
		item[6] = item7ItemId;
		item[7] = item8ItemId;
		if(item1lvl > 0)
		client.getPA().sendFrame126(""+item1lvl,8720);
		client.getPA().sendFrame126(""+item1lvldef,8760);
		if(item2lvl > 0)
		client.getPA().sendFrame126(""+item2lvl,8721);
		client.getPA().sendFrame126(""+item2lvldef, 8761);
		if(item3lvl > 0)
		client.getPA().sendFrame126(""+item3lvl,8722);
		client.getPA().sendFrame126(""+item3lvldef,8762);
		if(item4lvl > 0)
		client.getPA().sendFrame126(""+item4lvl,8723);
		client.getPA().sendFrame126(""+item4lvldef, 8763);
		if(item5lvl > 0)
		client.getPA().sendFrame126(""+item5lvl,8724);
		client.getPA().sendFrame126(""+item5lvldef, 8764);
		if(item6lvl > 0)
		client.getPA().sendFrame126(""+item6lvl,8725);
		client.getPA().sendFrame126(""+item6lvldef,8765);
		if(item7lvl > 0)
		client.getPA().sendFrame126(""+item7lvl,8726);
		client.getPA().sendFrame126(""+item7lvldef, 8766);
		if(item8lvl > 0)
		client.getPA().sendFrame126(""+item8lvl,8727);
		client.getPA().sendFrame126(""+item8lvldef, 8767);
		client.getPA().sendFrame126("",8849);
		client.getPA().sendFrame126("Attack",8846);
		client.getPA().sendFrame126("Strength",8823);
		client.getPA().sendFrame126("Defence",8824);
		client.getPA().sendFrame126("Hitpoints",8827);
		client.getPA().sendFrame126("Ranged",8837);
		client.getPA().sendFrame126("Magic",8840);
		client.getPA().sendFrame126("Prayer",8843);
		client.getPA().sendFrame126("RuneCraf",8859);
		client.getPA().sendFrame126("Agility",8862);
		client.getPA().sendFrame126("Herblore",8865);
		client.getPA().sendFrame126("Thieving",15303);
		client.getPA().sendFrame126("Crafting",15306);
		client.getPA().sendFrame126("Slayer",15309);
		
		sendSkillInterface(client, item);
		client.getPA().showInterface(8714);
	}
	public static void atkInterface(Client client) {
		skillsInterface(client, "Attack", 
					1291, 1, "Bronze", 
					1293, 1, "Iron", 
					1295, 5, "Steel", 
					1297, 10, "Black", 
					1299, 20, "Mithril", 
					1301, 30, "Adamant", 
					1303, 40, "Rune", 
					1305, 60, "Dragon");
	}
	public static void strInterface(Client client) {
		skillsInterface(client, "Strength", 
					3196, 5, "Black halberd", 
					3198, 10, "Mithril halberd", 
					3200, 15, "Adamant halberd", 
					3202, 20, "Rune halberd", 
					3204, 30, "Dragon halberd", 
					4153, 50, "Granite maul", 
					6528, 60, "Tzhaar-Ket-Om", 
					4718, 70, "Dharok's greataxe");
	}
	public static void defInterface(Client client) {
		skillsInterface(client, "Defence", 
					1139, 1, "Bronze", 
					1137, 1, "Iron", 
					1141, 5, "Steel", 
					1151, 10, "Black", 
					1143, 20, "Mithril", 
					1145, 30, "Adamant", 
					1147, 40, "Rune", 
					1149, 60, "Dragon");
	}
	public static void rangeInterface(Client client) {
		skillsInterface(client, "Ranged", 
					1129, 1, "Plain Leather", 
					1131, 1, "Hard leather", 
					1133, 20, "Studded leather", 
					1135, 40, "Green d'hide leather", 
					2499, 50, "Blue d'hide leather", 
					2501, 60, "Red d'hide leather", 
					2503, 70, "Black d'hide leather", 
					4736, 70, "Karils");
	}
	public static void prayInterface(Client client) {
		skillsInterface(client, "Prayer", 
					526, 1, "Bones", 
					532, 1, "Big bones", 
					534, 1, "Baby dragon bones", 
					536, 1, "Dragon bones", 
					6729, 1, "Dagannoth bones",
					4812, 1, "Zogre bones", 
					4830, 1, "Fayrg bones", 
					4832, 1, "Raurg bones");
	}
	public static void hpInterface(Client client) {
		skillsInterface(client, "Hitpoints", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "", 
					0, 0, "");
	}
	public static void mageInterface(Client client) {
		skillsInterface(client, "Magic", 
					579, 1, "Wizard",
					4089, 40, "Mystic", 
					7400, 40, "Enchant", 
					3385, 40, "Splitbark",  
					6918, 50, "Infinity", 
					2412, 60, "God capes and staffs", 
					6914, 70, "Mage's book and Master Wand", 
					4708, 70, "Ahrims");
	}
	public static void rcInterface(Client client) {
		skillsInterface(client, "Runecrafting", 
					6422, 1, "Air runes",
					6436, 2, "Mind runes", 
					6438, 20, "Body runes", 
					6430, 35, "Chaos runes",  
					561, 44, "Nature runes", 
					6434, 54, "Law runes", 
					6432, 65, "Death runes", 
					565, 77, "Blood runes");
	}
	public static void agilityInterface(Client client) {
		skillsInterface(client, "Agility", 
					2150, 1, "Gnome stronghold agility course", 
					2996, 1, "Low-level agility arena", 
					2996, 20, "Medium-level agility arena", 
					2996, 40, "High-level agility arena", 
					1365, 35, "Barberian outpost agility course", 
					4024, 48, "Ape attol agility course", 
					964, 52, "Wilderness agility course", 
					4170, 60, "Werewolf agility course");
	}
	public static void herbloreInterface(Client client) {
		skillsInterface(client, "Agility", 
					221, 3, "Attack Potion", 
					235, 5, "Anti-poison Potion", 
					225, 12, "Strength Potion", 
					223, 22, "Restore Potion", 
					1975, 22, "Energy Potion", 
					239, 30, "Defence Potion", 
					1526, 38, "Prayer Potion", 
					221, 45, "Super Attack Potion");
	}
	public static void thievingInterface(Client client) {
		skillsInterface(client, "Thieving", 
					3241, 1, "Man", 
					3243, 10, "Farmer", 
					3245, 25, "Warrior", 
					3249, 40, "Guard", 
					3251, 53, "Knights of ardogne", 
					3255, 70, "Paladin",
					3257, 75, "Gnome",
					3259, 80, "Hero");
	}
	public static void craftingInterface(Client client) {
		skillsInterface(client, "Crafting", 
					1059, 1, "Leather", 
					1777, 10, "Flax into bow string", 
					1097, 20, "Hard leather", 
					1065, 57, "Green dragonhide leather", 
					2487, 66, "Blue dragonhide leather", 
					2489, 73, "Red dragonhide leather", 
					2491, 79, "Black dragonhide leather",
					6585, 90, "Onyx amulet");
	}
	public static void slayerInterface(Client client) {
		skillsInterface(client, "Slayer", 
					4133, 1, "Crawling hand", 
					4134, 10, "Cave crawler", 
					4140, 45, "Infernal Mage", 
					4144, 60, "Aberrant Spectre", 
					4145, 65, "Dust devil", 
					4147, 75, "Gargoyle", 
					4148, 80, "Nechryael", 
					4149, 85, "Abyssal demon");
	}
	public static void fletchingInterface(Client client) {
		skillsInterface(client, "Fletching", 
					50, 5, "Normal bows", 
					54, 20, "Oak bows", 
					60, 35, "Willow bows", 
					64, 50, "Maple bows", 
					68, 65, "Yew bows", 
					892, 75, "Rune arrow", 
					72, 80, "Magic shortbow",
					70, 85, "Magic longbow");
					
	}
	public static void miningInterface(Client client) {
		skillsInterface(client, "Mining", 
					436, 1, "Tin & Cooper", 
					440, 15, "Iron", 
					442, 30, "Silver", 
					453, 30, "Coal", 
					444, 40, "Gold", 
					447, 55, "Mithril", 
					449, 70, "Adamant", 
					451, 85, "Rune");
	}
	public static void smithingInterface(Client client) {
		skillsInterface(client, "Smithing", 
					2349, 1, "Bronze", 
					2351, 15, "Iron", 
					2355, 20, "Silver", 
					2353, 30, "Steel", 
					2357, 40, "Gold",
					2359, 50, "Mithril", 
					2361, 70, "Adamant", 
					2363, 85, "Rune");
	}
	public static void fishingInterface(Client client) {
		skillsInterface(client, "Fishing", 
					317, 1, "Shrimps", 
					335, 20, "Trouts", 
					359, 35, "Tunas", 
					377, 40, "Lobsters", 
					371, 50, "Swordfishes", 
					383, 76, "Sharks", 
					395, 79, "Sea Turtles", 
					389, 81, "Manta rays");
	}
	public static void cookingInterface(Client client) {
		skillsInterface(client, "Cooking", 
					315, 1, "Shrimps", 
					333, 20, "Trouts", 
					361, 30, "Tunas", 
					379, 40, "Lobsters", 
					373, 50, "Swordfishes", 
					385, 76, "Sharks", 
					397, 79, "Sea Turtles", 
					391, 81, "Manta rays");
	}
	public static void woodcuttingInterface(Client client) {
		skillsInterface(client, "Woodcutting", 
					1511, 1, "Normal tree", 
					1521, 15, "Oak tree", 
					1519, 30, "Willow tree",
					6333, 35, "Teak tree",
					1517, 45, "Maple tree", 
					6332, 50, "Mahogany tree",
					1515, 60, "Yews tree", 
					6739, 61, "Dragon axe");
	}
	public static void firemakingInterface(Client client) {
		skillsInterface(client, "Firemaking", 
					1511, 1, "Tree logs", 
					1521, 15, "Oak logs", 
					1519, 30, "Willow logs",
					6333, 35, "Teak logs",
					1517, 45, "Maple logs",
					4544, 49, "Bullseye lantern",
					6332, 50, "Mahogany logs",
					1515, 60, "Yew logs");
	}
	public static void farmingInterface(Client client) {
		skillsInterface(client, "Farming", 
					5291, 1, "Guam seed", 
					5292, 15, "Marrentill seed", 
					5293, 20, "Tarromin seed", 
					5295, 30, "Ranarr seed", 
					5298, 40, "Avanote seed", 
					5300, 60, "Snapdragon seed", 
					5302, 72, "Lantadyme seed", 
					5304, 90, "Torsol seed");
	}
}