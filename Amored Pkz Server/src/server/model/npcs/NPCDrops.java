package server.model.npcs;

import java.io.File;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * @author Sanity
 */

public class NPCDrops {
	
	public NPCDrops() {
		loadDrops();
	}
	

	
	public static HashMap<Integer, int[][]> normalDrops = new HashMap<Integer, int[][]>();
	public static HashMap<Integer, int[][]> rareDrops = new HashMap<Integer, int[][]>();
	public static HashMap<Integer, int[]> constantDrops = new HashMap<Integer, int[]>();
	public static HashMap<Integer, Integer> dropRarity = new HashMap<Integer,Integer>();
	
	public void loadDrops() {
		try {
			int[][][] npcDrops = new int [62585][][];
			int[][][] rareDrops2 = new int [62585][][];
			int[] itemRarity = new int [62585];
			File f = new File("./Data/cfg/NPCDrops.TSM");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.startsWith("#"))
					continue;
				StringTokenizer normalTok = new StringTokenizer(line, "\t");
				line = s.nextLine();
				if (line.startsWith("#"))
					continue;
				StringTokenizer rareTok = new StringTokenizer(line, "\t");
				String[] information = normalTok.nextToken().split(":");
				int npcId = Integer.parseInt(information[0]);
				itemRarity[npcId] = Integer.parseInt(information[1])-1;
				npcDrops[npcId] = new int[normalTok.countTokens()][2];
				rareDrops2[npcId] = new int[rareTok.countTokens()][2];
				int count = 0;
				while (normalTok.hasMoreTokens()) {
					String[] temp = normalTok.nextToken().split(":");
					npcDrops[npcId][count][0] = Integer.parseInt(temp[0]);
					npcDrops[npcId][count][1] = Integer.parseInt(temp[1]);
					count++;
				}
				count = 0;
				while (rareTok.hasMoreTokens()) {
					String[] temp = rareTok.nextToken().split(":");
					rareDrops2[npcId][count][0] = Integer.parseInt(temp[0]);
					//System.out.println("Raredrop: " + count + " " + rareDrops2[npcId][count][0]);
					rareDrops2[npcId][count][1] = Integer.parseInt(temp[1]);
					//System.out.println("Raredrop: " + count + " " + rareDrops2[npcId][count][1]);
					count++;
				}
				normalDrops.put(npcId, npcDrops[npcId]);
				rareDrops.put(npcId, rareDrops2[npcId]);
				dropRarity.put(npcId, itemRarity[npcId]);
			}
			loadConstants();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void loadConstants() {
		try {
			File f = new File("./Data/cfg/NpcConstants.TSM");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.startsWith("#"))
					continue;
				StringTokenizer constantTok = new StringTokenizer(line, "\t");
				int npcId = Integer.parseInt(constantTok.nextToken());
				int count = 0;
				int[] temp = new int[constantTok.countTokens()];
				while (constantTok.hasMoreTokens()) {
					temp[count] = Integer.parseInt(constantTok.nextToken());
					count++;
				}
				constantDrops.put(npcId,temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}
