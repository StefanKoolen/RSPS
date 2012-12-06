import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ItemReplace {
	
	public int toReplace = 9010;
	public int altRemove = 9011;
	public int replaceWith = 995;
	public int altReplace = 996;
	public int replaceAmount = 700;
	
	public static void main(String[] args) {
		ItemReplace ir = new ItemReplace();
		File dir = new File("characters");
		if(dir.exists()) {
			File files[] = dir.listFiles();	
			for (int j = 0; j < files.length; j++) {
				File loaded = files[j];
				if (loaded.getName().endsWith(".txt")) {
					ir.handleCharacter(loaded);
				}			
			}
		}
	}
	
	
	public void handleCharacter(File f) {
		try {
			Scanner s = new Scanner(f);
			String[] contents = new String[getLineCount(s)];
			s = new Scanner(f);
			for (int j = 0; j < contents.length; j++) {
				String temp = s.nextLine();
				if (temp != "") {
					if (temp.split("\t").length > 2) {
						if (temp.contains("item") || temp.contains("bank")) {
							String[] items = temp.split("\t");
							if (Integer.parseInt(items[1]) == altRemove) {
								items[1] = "" + altReplace;
								items[2] = "" + Integer.parseInt(items[2])*700;
							}
							temp = items[0] + "\t" + items[1] + "\t" + items[2];
						} else if (temp.contains("character-equip = 13")) {
							String[] items = temp.split("\t");
							if (Integer.parseInt(items[1]) == toReplace) {
								items[1] = "" + replaceWith;
								items[2] = "" + Integer.parseInt(items[2])*700;					
							}
							temp = items[0] + "\t" + items[1] + "\t" + items[2];
						}
					}	
				}	
				contents[j] = temp;
			}
			FileWriter fw = new FileWriter(f);
			for (int j = 0; j < contents.length; j++) {
				fw.write(contents[j] + "\r\n");			
			}
			fw.close();
		} catch (IOException ioe){ioe.printStackTrace();}	
	}
	
	public int getLineCount(Scanner s) {
		int count = 0;
		while (s.hasNextLine()) {
			s.nextLine();
			count++;
		}
		return count;
	}
}	