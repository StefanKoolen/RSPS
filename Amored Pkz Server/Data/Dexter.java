
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Dexter {


	public static void main(String[] args) {
		Dexter dexter = new Dexter();
		//dexter.checkForFlag();
		dexter.checkBanks();
	}

	public void checkBanks() {
		try {
			File dir = new File("characters");
			if(dir.exists()) {
				String read;
				File files[] = dir.listFiles();	
				for (int j = 0; j < files.length; j++) {
					File loaded = files[j];
					if (loaded.getName().endsWith(".txt")) {
						Scanner s = new Scanner (loaded);
						int cash = 0;
						while (s.hasNextLine()) {
							read = s.nextLine();
							if (read.startsWith("character-item") || read.startsWith("character-bank")) {
								String[] temp = read.split("\t");
								int token1 = Integer.parseInt(temp[1]);
								int token2 = Integer.parseInt(temp[2]);
								if (token1 == 996) {
									cash += token2;
									if (cash > 12500000) {
										System.out.println("name: " + loaded.getName());
									}
								}
							
							}				
						
						
						}					
					}			
				}
			} else {
				System.out.println("FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkForFlag() {
		try {
			File dir = new File("characters");
			if(dir.exists()) {
				String read;
				File files[] = dir.listFiles();	
				for (int j = 0; j < files.length; j++) {
					File loaded = files[j];
					if (loaded.getName().endsWith(".txt")) {
						Scanner s = new Scanner (loaded);
						while (s.hasNextLine()) {
							read = s.nextLine();
							if (read.equalsIgnoreCase("flagged = true")) {
								System.out.println(loaded.getName());
								break;							
							}						
						}					
					}			
				}
			}
		} catch (Exception e) {
		
		}
	
	}
	
	public void logFile(String name ) {
		try {
		FileWriter fw = new FileWriter("dupers.txt");
		fw.write(name + "\r\n");
		fw.close();	
		} catch (Exception e){ 
		
		}
	}
	
	


}