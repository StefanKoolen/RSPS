package server.model.players.skills;

import server.Config;
import server.model.players.Client;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * RuneCrafting.java
 *
 * @author Sanity
 *
 **/
 
public class Runecrafting {
	
	private Client c;
		
	public Runecrafting(Client c) {
		this.c = c;
	}
	
    /**
     * Rune essence ID constant.
     */
    private static final int RUNE_ESS = 1436;

    /**
     * Pure essence ID constant.
     */
    private static final int PURE_ESS = 7936;	
	
    /**
     * An array containing the rune item numbers.
     */
    public int[] runes = {
        556, 558, 555, 557, 554, 559, 564, 562, 561, 563, 560, 565
    };

    /**
     * An array containing the object IDs of the runecrafting altars.
     */
    public int[] altarID = {
        2478, 2479, 2480, 2481, 2482, 2483, 2484, 2487, 2486, 2485, 2488, 2489
    };
	
    /**
     * 2D Array containing the levels required to craft the specific rune.
     */
    public int[][] craftLevelReq = {
        {556, 1}, {558, 2}, {555, 5}, {557, 9}, {554, 14}, {559, 20},
        {564, 27}, {562, 35}, {561, 44}, {563, 54}, {560, 65}, {565, 77}
    };

    /**
     * 2D Array containing the levels that you can craft multiple runes.
     */
    public int[][] multipleRunes = {
        {11, 22, 33, 44, 55, 66, 77, 88, 99},
        {14, 28, 42, 56, 70, 84, 98},
        {19, 38, 57, 76, 95},
        {26, 52, 78},
        {35, 70},
        {46, 92},
        {59},
        {74},
        {91},
		{100},
		{100},
		{100}
    };
	
    public int[] runecraftExp = {
        5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 11
    };
	/**
     * Checks through all 28 item inventory slots for the specified item.
     */
    private boolean itemInInv(int itemID, int slot, boolean checkWholeInv) {
		if (checkWholeInv) {
			for (int i = 0; i < 28; i++) {
				if (c.playerItems[i] == itemID + 1) {
					return true;
                }
			}
		} else {
			if (c.playerItems[slot] == itemID + 1) {
				return true;
			}
		}
        return false;
    }

    /**
     * Replaces essence in the inventory with the specified rune.
     */
    private void replaceEssence(int essType, int runeID, int multiplier, int index) {
		System.out.println("multipler: " + multiplier);
		int exp = 0;
        for (int i = 0; i < 28; i++) {
			if (itemInInv(essType, i, false)) {
                c.getItems().deleteItem(essType, i, 1);
                c.getItems().addItem(runeID, 1 * multiplier);
                exp += runecraftExp[index];                
            }
        }
        c.getPA().addSkillXP(exp * Config.RUNECRAFTING_EXPERIENCE, c.playerRunecrafting);
    }

    /**
     * Crafts the specific rune.
     */
    public void craftRunes(int altarID) {
        int runeID = 0;

        for (int i = 0; i < this.altarID.length; i++) {
            if (altarID == this.altarID[i]) {
                runeID = runes[i];
            }
        }
        for (int i = 0; i < craftLevelReq.length; i++) {
            if (runeID == runes[i]) {
				if (c.playerLevel[20] >= craftLevelReq[i][1]) {
                    if (c.getItems().playerHasItem(RUNE_ESS) || c.getItems().playerHasItem(PURE_ESS)) {
                        int multiplier = 1;
						for (int j = 0; j < multipleRunes[i].length; j++) {
                            if (c.playerLevel[20] >= multipleRunes[i][j]) {
								multiplier += 1;
                            }
                        }
                        replaceEssence(RUNE_ESS, runeID, multiplier, i);
                        c.startAnimation(791);
                        //c.frame174(481, 0, 0); for sound
                        c.gfx100(186);
                        return;
                    }
                    c.sendMessage("You need to have essence to craft runes!");
                    return;
                }
                c.sendMessage("You need a Runecrafting level of "+ craftLevelReq[i][1] +" to craft this rune.");
            }
        }
    }
	
}