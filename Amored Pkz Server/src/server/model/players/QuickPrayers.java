package server.model.players;

import server.*;
import server.util.*;

/**
 *
 * @author relex lawl / relex
 *
 * Old class I decided to upgrade
 */

public class QuickPrayers {

	public static final int MAX_PRAYERS = 26;
	private static final int[] defPrayId = {67050, 67055, 67063, 67074, 67075};
	private static final int[] strPrayId = {67051, 67056, 67064, 67074, 67075};
	private static final int[] atkPrayId = {67052, 67057, 67065, 67074, 67075};
	private static final int[] rangePrayId = {67053, 67061, 67069};
	private static final int[] magePrayId = {67054, 67062, 67070};
	private static final int[] headIconsId = {67066, 67067, 67068, 67071, 67072, 67073};
	private static final int[] defPray = {0, 5, 13, 24, 25};
	private static final int[] strPray = {1, 6, 14, 24, 25};
	private static final int[] atkPray = {2, 7, 15, 24, 25};
	private static final int[] rangePray = {3, 11, 19};
	private static final int[] magePray = {4, 12, 20};
	private static final int[] headIcons = {16, 17, 18, 21, 22, 23};
	private static final int[][] data = { 	{67050, 630, 0}, {67051, 631, 1}, {67052, 632, 2}, {67053, 633, 3},
											{67054, 634, 4}, {67055, 635, 5}, {67056, 636, 6}, {67057, 637, 7},
											{67058, 638, 8}, {67059, 639, 9}, {67060, 640, 10}, {67061, 641, 11},
											{67062, 642, 12}, {67063, 643, 13}, {67064, 644, 14}, {67065, 645, 15},
											{67066, 646, 16}, {67067, 647, 17}, {67068, 648, 18}, {67069, 649, 19},
											{67070, 650, 20}, {67071, 651, 21}, {67072, 652, 22}, {67073, 653, 23},
											{67074, 654, 24}, {67075, 655, 25}
										};//actionID, frameID, quickCurse = true

	public static void clickPray(Client c, int actionId) {
		canBeSelected(c, actionId);
		for (int j = 0; j < data.length; j++) {
			if (data[j][0] == actionId) {
				if (c.quickPrayers[data[j][2]]) {
					c.quickPrayers[data[j][2]] = false;
					c.getPA().sendFrame36(data[j][1], 0);
				} else {
					c.quickPrayers[data[j][2]] = true;
					c.getPA().sendFrame36(data[j][1], 1);
				}
			}
		}
	}

	public static void loadCheckMarks(Client player) {
		for (int j = 0; j < data.length; j++)
			player.getPA().sendFrame36(data[j][1], player.quickPrayers[data[j][2]] ? 1 : 0);
	}
	
	public static void canBeSelected(Client c, int actionId) {
		boolean[] prayer = new boolean[MAX_PRAYERS];
		for (int j = 0; j < prayer.length; j++)
			prayer[j] = true;
		switch (actionId) {
			case 67050:
			case 67055:
			case 67063:
				for (int j = 0; j < defPrayId.length; j++) {
					if (defPrayId[j] != actionId) {
						prayer[defPray[j]] = false;
					}								
				}
			break;

			case 67051:
			case 67056:
			case 67064:
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						prayer[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}								
				}
			break;

			case 67052:
			case 67057:
			case 67065:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						prayer[atkPray[j]] = false;
					}
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}							
				}
			break;

			case 67053:
			case 67061:
			case 67069:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						prayer[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						prayer[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}								
				}
			break;

			case 67054:
			case 67062:
			case 67070:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						prayer[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						prayer[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}								
				}
			break;

			case 67066:
			case 67067:
			case 67068:
			case 67071:
			case 67072:
			case 67073:
				for (int j = 0; j < headIcons.length; j++) {
					if (headIconsId[j] != actionId) {
						prayer[headIcons[j]] = false;
					}								
				}		
			break;

			case 67074:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						prayer[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						prayer[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}								
				}
				for (int j = 0; j < defPray.length; j++) {
					if (defPrayId[j] != actionId) {
						prayer[defPray[j]] = false;
					}								
				}
			break;

			case 67075:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						prayer[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						prayer[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						prayer[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						prayer[magePray[j]] = false;
					}								
				}
				for (int j = 0; j < defPray.length; j++) {
					if (defPrayId[j] != actionId) {
						prayer[defPray[j]] = false;
					}								
				}
			break;
		}
		for (int i = 0; i < MAX_PRAYERS; i++) {
			if (!prayer[i]) {
				c.quickPrayers[i] = false;
				for (int j = 0; j < data.length; j++) {
					if (i == data[j][2])
						c.getPA().sendFrame36(data[j][1], 0);
				}
			}
		}
	}
}
