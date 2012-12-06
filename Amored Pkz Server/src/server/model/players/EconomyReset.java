import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class EconomyReset
{

        private static File charDir = new File("./characters/");

        public static void main(String[] args)
        {
                if(charDir.exists() && charDir.isDirectory())
                {
                        File[] charFiles = charDir.listFiles();
                for(int i = 0; i < charFiles.length; i++)
                {
                        resetEcoChar(charFiles[i]);
                        System.out.println("Reset player economy levels for... "+charFiles[i].toString());
                        }
                }
        }

		@SuppressWarnings("deprecation")
        private static void resetEcoChar(File charFile)
        {
                try
                {

                        String cheatStatus, tempData, tempAdd = ""; int curEquip = 0, curItem = 0, curBank = 0;
                        File tempCharFile = new File(charDir.toString()+"ECOBOOST$TEMP");
                        DataInputStream fileStream = new DataInputStream(new FileInputStream(charFile));
                        BufferedWriter tempOut = new BufferedWriter(new FileWriter(tempCharFile));
						
                while((tempData = fileStream.readLine()) != null)
                {
                        if((!tempData.trim().startsWith("character-item =")) && (!tempData.trim().startsWith("character-bank =")))
                        {
                                tempAdd = tempData.trim();

                                if(tempData.trim().startsWith("character-equip =")) 
                                {
                                        tempAdd = "character-equip = "+curEquip+"\t-1\t0";
                                        curEquip++;
                                }
                        tempOut.write(tempAdd); tempOut.newLine();
                                }
                        }
                                fileStream.close(); tempOut.close();
                                charFile.delete();
                                tempCharFile.renameTo(charFile);
                        }
                catch(Exception e) { e.printStackTrace(); 
                }
        }
}

