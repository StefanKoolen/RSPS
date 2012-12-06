package server.model.items;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import server.Config;
import server.Server;

public class Item {

public static boolean playerCape(int itemId) {
                String[] data = {
                        "cloak", "cape", "Cape", "attractor", "Attractor", "Ava's", "TokHaar-Kal", "TokHaar", "Tok", "-Kal", "Kal", "Cape of Death", "Death"
                };
                String item = getItemName(itemId);
                if (item == null) {
                        return false;
                }
                boolean item1 = false;
                for(int i = 0; i < data.length; i++ ) {
                        if(item.endsWith(data[i]) || item.contains(data[i])) {
                                item1 = true;
                        }
                }
                return item1;
        }

	public static boolean playerBoots(int itemId) {
		String[] data = {
			"Shoes", "shoes", "boots", "Boots", "Flippers", "flippers", "lvl",
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerGloves(int itemId) {
		String[] data = {
			"Gloves", "gloves", "glove", "Glove", "Vamb", "vamb", "gauntlets", "Gauntlets", "bracers", "Bracers", "Vambraces", "vambraces"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerShield(int itemId) {
		String[] data = {
			"kiteshield", "book", "Kiteshield", "toktz-ket-xil", "Toktz-ket-xil", "shield", "Shield", "Kite", "kite", "Defender", "defender", "Tome"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerAmulet(int itemId) {
		String[] data = {
			"amulet", "Amulet", "scarf", "Necklace", "necklace", "Pendant", "pendant", "Symbol", "symbol", "stole", "Stole"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerArrows(int itemId) {
		String[] data = {
			"Arrows", "arrows", "Arrow", "arrow", "Bolts", "bolts", "Shot", "shot", "rack", "Rack",
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerRings(int itemId) {
		String[] data = {
			"ring", "rings", "Ring", "Rings",
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerHats(int itemId) {
		String[] data = {
			"boater", "cowl", "head", "peg", "coif", "helm", 
			"Coif", "mask", "hat", "headband", "hood", 
			"disguise", "cavalier", "full", "tiara",
			"helmet", "Hat", "ears", "crown", "partyhat", "helm(t)",
			"helm(g)", "beret", "facemask", "sallet",
			"hat(g)", "hat(t)", "bandana", "Helm", "Mitre", "mitre",
			"Bomber cap", "cap", "visor", "Visor",
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerLegs(int itemId) {
		String[] data = {
			"tassets", "chaps", "bottoms", "gown", "trousers", 
			"platelegs", "robe", "plateskirt", "legs", "leggings", 
			"shorts", "Skirt", "skirt", "cuisse", "Trousers", "Pantaloons",
			"Leggings", "leggings", "Ganodermic", "ganodermic", 
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if((item.endsWith(data[i]) || item.contains(data[i])) && (!item.contains("top") && (!item.contains("robe (g)") && (!item.contains("robe (t)"))))) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerBody(int itemId) {
		String[] data = {
			"body", "top", "Priest gown", "apron", "shirt", 
			"platebody", "robetop", "body(g)", "body(t)", 
			"Wizard robe (g)", "Wizard robe (t)", "body", "brassard", "blouse", 
			"tunic", "leathertop", "Saradomin plate", "chainbody", 
			"hauberk", "Shirt", "torso", "chestplate", "jacket", "Poncho", "poncho", 
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	private static String[] fullbody = {
		"top", "chestplate", "shirt","platebody","Ahrims robetop",
		"Karils leathertop","brassard","Robe top","robetop",
		"platebody (t)","platebody (g)","chestplate",
		"torso", "hauberk", "Dragon chainbody", "blouse", "jacket", "Poncho", "poncho",
	};

	private static String[] fullhat = {
		"med helm", "coif", "Dharok's helm", "Slayer helmet", "hood", "Initiate helm",
		"Coif","Helm of neitiznot","Armadyl helmet","Berserker helm", 
		"Archer helm", "Farseer helm", "Warrior helm", "Void", "Lumberjack hat", "Reindeer hat",
		"Larupia hat", "mask", "Kyatt hat", "Bomber cap", "Dwarven helmet", "Pernix", "pernix", "Pernix cowl", "Pernix Cowl", "Cowl", "cowl", "visor", "Visor"
	};

	private static String[] fullmask = {
		"full helm", "Afro", "afro", "mask", "Pernix", "pernix", "Pernix cowl", "Pernix Cowl", "Cowl", "cowl", "visor", "Visor", "Verac's helm", "Guthan's helm", "Karil's coif", "mask", "Torag's helm", "sallet", "Saradomin helm", "Lunar helm",
	};

	public static boolean isFullBody(int itemId) {
		String weapon = getItemName(itemId);
		if (weapon == null)
			return false;
		for (int i = 0; i < fullbody.length; i++) {
			if (weapon.endsWith(fullbody[i]) || weapon.contains(fullbody[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullHelm(int itemId) {
		String weapon = getItemName(itemId);
			if (weapon == null)
				return false;
		for (int i = 0; i < fullhat.length; i++) {
			if (weapon.endsWith(fullhat[i]) && itemId != 2631 && itemId != 11277 && itemId != 11278 && itemId != 922) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullMask(int itemId) {
		String weapon = getItemName(itemId);
			if (weapon == null)
				return false;
		for (int i = 0; i < fullmask.length; i++) {
			if (weapon.endsWith(fullmask[i]) && itemId != 2631 && itemId != 9925 && itemId != 10728 && itemId != 11277 && itemId != 11278 && itemId != 922) {
				return true;
			}
		}
		return false;
	}
	
	public static String getItemName(int id) {
		for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
			if (Server.itemHandler.ItemList[j] != null)
				if (Server.itemHandler.ItemList[j].itemId == id)
					return Server.itemHandler.ItemList[j].itemName;	
		}
		return null;
	}
	
	
	public static boolean[] itemStackable = new boolean[Config.ITEM_LIMIT];
	public static boolean[] itemIsNote = new boolean[Config.ITEM_LIMIT];
	public static int[] targetSlots = new int[Config.ITEM_LIMIT];
	static {
		int counter = 0;
		int c;
		
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/stackable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					itemStackable[counter] = false;
itemStackable[counter] = false;
					itemStackable[13883] = true; //morrigan throwing axe
					itemStackable[13879] = true; //morrigan javelin
itemStackable[13957] = true; //morrigan javelin
itemStackable[18016] = true;
itemStackable[12158] = true;
itemStackable[12159] = true;
itemStackable[12160] = true;
itemStackable[12163] = true;
itemStackable[12155] = true;
itemStackable[15243] = true;

				} else {
					itemStackable[counter] = true;
				}
				counter++;
			}
int[] stackableItems = {15243};
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading stackabledata! Trace:");
			e.printStackTrace();
		}

		counter = 0;
		
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/notes.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					itemIsNote[counter] = true;
				} else {
					itemIsNote[counter] = false;
				}
				counter++;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
		
		counter = 0;
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/equipment.dat"));
			while ((c = dataIn.read()) != -1) {
				targetSlots[counter++] = c;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
	}
}