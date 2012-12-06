package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Ream
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
	c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(3192, 2962, 3219, 2, 10); //ingamehighscores
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(2213, 2837, 3442, 1, 10); //Bank Stall
		c.getPA().checkObjectSpawn(4150, 2962, 3214, 0, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(4008, 2851, 2965, 1, 10); //spec alter
		c.getPA().checkObjectSpawn(11356, 2826, 3355, 0, 10); //frost dragon portals
		c.getPA().checkObjectSpawn(8972, 2474, 3440, 0, 10); //Strykeworms portal
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10); //Dungeoneering Rock
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); //Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(2014, 1921, 4640, 0, 10); //Zombie Minigame Chalice of Eternity
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); //Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); //Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); //dungie blocker
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); //dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); //dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); //dung floor 7 portalEND
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2422, 9429, 0, 10); //escape ladder
                c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
				 c.getPA().checkObjectSpawn(-1, 3090, 3503, 0, 10);
		c.getPA().checkObjectSpawn(16078, 1920, 4636, 0, 10);
		c.getPA().checkObjectSpawn(7315, 2871, 2952, 1, 10);
		c.getPA().checkObjectSpawn(2996, 3348, 3338, 2, 10);//al key chest
                c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
				
					///Home Objects!	
c.getPA().checkObjectSpawn(3192, 1, 1, 0, 10); //hs board
		c.getPA().checkObjectSpawn(411, 2963, 3217, 0, 10);   /// Choras Altar
		c.getPA().checkObjectSpawn(4008, 3096, 3500, 0, 10);  ///Spec Attack Altar
		c.getPA().checkObjectSpawn(6552, 2967, 3217, 0, 10);  ///Ancient Altar
		c.getPA().checkObjectSpawn(409, 2965, 3217, 0, 10);  /// Altar/
		c.getPA().checkObjectSpawn(409, 2090, 4425,1, 10);  /// Altar/
		c.getPA().checkObjectSpawn(410, 2968, 3223, 0, 10);   ///Guthix Altar
		c.getPA().checkObjectSpawn(12356, 2962, 3215, 0, 10); ///Portal at home/
		c.getPA().checkObjectSpawn(2996, 3091, 3488, 2, 10);  ///al key chest/
		c.getPA().checkObjectSpawn(103, 3094, 3488, 0, 10);  ///Squeal Of Fortune Chest/
		c.getPA().checkObjectSpawn(3192, 2403, 3492, 0, 10);  ///hs board
		c.getPA().checkObjectSpawn(11356, 2947, 3219, 0, 10); ///frost dragon portals/
		///End of home objects

		c.getPA().checkObjectSpawn(4874, 2848, 3430, 1, 10);///Home Stalls lvl 1
		c.getPA().checkObjectSpawn(4875, 2847, 3430, 1, 10);///Home Stalls lvl 25
		c.getPA().checkObjectSpawn(4876, 2846, 3430, 0, 10);///Home Stalls lvl 50
		c.getPA().checkObjectSpawn(4877, 2845, 3430, 0, 10);///Home Stalls lvl 75
		c.getPA().checkObjectSpawn(4878, 2844, 3430, 0, 10);///Home Stalls lvl 90
		///
		c.getPA().checkObjectSpawn(4008, 2850, 3352, 0, 10);
		c.getPA().checkObjectSpawn(6552, 2850, 3345, 0, 10);
		c.getPA().checkObjectSpawn(2403, 2847, 3333, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2846, 3333, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
                c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
                c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
                c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(1277, 2048, 3244, 0, 10);
c.getPA().checkObjectSpawn(13405, 3352, 3348, 0, 10);//home portal
		c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
//Rimmington Object Being removed (Replaced)
		c.getPA().checkObjectSpawn(3, 2948, 3213, 0, 10);
		c.getPA().checkObjectSpawn(3, 2948, 3203, 0, 10);
		c.getPA().checkObjectSpawn(3, 2962, 3207, 0, 10);
		c.getPA().checkObjectSpawn(3, 2947, 3204, 0, 10);
		c.getPA().checkObjectSpawn(3, 2958, 3204, 0, 10);
		c.getPA().checkObjectSpawn(3, 2958, 3205, 0, 10);
		c.getPA().checkObjectSpawn(3, 3082, 3516, 0, 10);
		c.getPA().checkObjectSpawn(3, 2967, 3205, 0, 10);
		c.getPA().checkObjectSpawn(3, 3206, 3263, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2048, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2049, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3243, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2071, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3263, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2072, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3263, 0, 10);
		//end of trees
		//Custom Ham Home Spawn
		c.getPA().checkObjectSpawn(1292, 3167, 9628, 0, 10);
		//Trees around Skeletal Horror
		c.getPA().checkObjectSpawn(1277, 2080, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2081, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2082, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2083, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2084, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2086, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3218, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3217, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3216, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3215, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3214, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3213, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3212, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3211, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3209, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3208, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3207, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3206, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3205, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2085, 3203, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2084, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2083, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2082, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2081, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2080, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2079, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2078, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2077, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3204, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3206, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3207, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3208, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3209, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3211, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3212, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3213, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3214, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3215, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3216, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3217, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3218, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3219, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2076, 3220, 0, 10);
		c.getPA().checkObjectSpawn(1277, 3221, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1277, 3222, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1279, 3222, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1278, 3221, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1277, 3221, 3210, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2080, 3221, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2080, 3222, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2079, 3222, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2078, 3222, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2077, 3222, 0, 10);
		//Tunnel
		c.getPA().checkObjectSpawn(5008, 2956, 3224, 2, 10);

         
       
		//empty bulding spaces
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3247, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3248, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3249, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3250, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3251, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3252, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3253, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3254, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3255, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3256, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3257, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3258, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(12356, 2845, 2957, 1, 10);
				c.getPA().checkObjectSpawn(2403, 2844, 2957, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2854, 2962, 1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	    c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4774, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(411, 2850, 3349, 1, 10); // Curse Prayers

		//Donator Portals @ Custom Donor place
		c.getPA().checkObjectSpawn(13615, 2656, 4560, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2654, 4560, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2651, 4560, 2, 10); // tormented demons donor
		c.getPA().checkObjectSpawn(13617, 2649, 4560, 2, 10); //Barrelportal donor
		c.getPA().checkObjectSpawn(2213, 2649, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2650, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2651, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2652, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2653, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2654, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2655, 4574, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2088, 4428, 1, 10); //Bank Stall
		c.getPA().checkObjectSpawn(2213, 2088, 4429, 1, 10); //Bank Stall
		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10); 

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2842, 2954, 1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2852, 2950, 2, 10);
		c.getPA().checkObjectSpawn(409, 2853, 3348, 1, 10);
		c.getPA().checkObjectSpawn(410, 2854, 3348, 1, 10);
		c.getPA().checkObjectSpawn(409, 2530, 4779, 3, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
				c.getPA().checkObjectSpawn(104, 2646, 4567, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		
		//New Armored Pkz by dr house {
		
		//Add
		
		//Home
		
		c.getPA().checkObjectSpawn(2213, 2384, 4454, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4455, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4456, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4457, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4458, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4459, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4461, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2384, 4462, 1, 10);
		
		c.getPA().checkObjectSpawn(411, 2396, 4458, 3, 10);
		c.getPA().checkObjectSpawn(409, 2392, 4458, 1, 10);
		c.getPA().checkObjectSpawn(6552, 2393, 4460, 2, 10);
		
		c.getPA().checkObjectSpawn(12356, 2400, 4456, 1, 10);
		c.getPA().checkObjectSpawn(2403, 2400, 4457, 3, 10);
		c.getPA().checkObjectSpawn(4150, 2400, 4458, 1, 10);
		
		c.getPA().checkObjectSpawn(2644, 2390, 4443, 1, 10);
		
		//End of Home
		
		//Donator Zone
		if (c.heightLevel == 0) {
		c.getPA().checkObjectSpawn(2213, 2724, 4894, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4895, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4896, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4897, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4898, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4899, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4900, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4901, 1, 10);
		c.getPA().checkObjectSpawn(104, 2720, 4901, 2, 10);
		c.getPA().checkObjectSpawn(13615, 2717, 4905, 2, 10);
		c.getPA().checkObjectSpawn(13617, 2719, 4905, 2, 10);
		c.getPA().checkObjectSpawn(13619, 2723, 4905, 2, 10);
		c.getPA().checkObjectSpawn(13620, 2725, 4905, 2, 10);
		} else {
		c.getPA().checkObjectSpawn(2213, 2724, 4894, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4895, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4896, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4897, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4898, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4899, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4900, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2724, 4901, 1, 10);
		}
		
		
		//End of Donator Zone
		
		//StaffZone
		
		
		
		//End of Staffzone
		
		//Remove
		
		//Home
		
		c.getPA().checkObjectSpawn(-1, 2383, 4445, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2383, 4446, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2393, 4458, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2395, 4459, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2391, 4459, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2383, 4454, -1, 10);
		
		//End of Home
		
		//Donator Zone
		
		
		
		
		//End of Donator Zone
		
		//StaffZone
		
		
		
		//End of Staffzone
		

		
		
		//}
	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}