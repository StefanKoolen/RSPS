package server.model.players.skills;

import server.model.players.*;
import server.Config;
import server.Server;
import server.util.Misc;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* @Author Sanity
*/

public class Summoning {
	
	Client c;
	

	public Summoning(Client c) {
		this.c = c;
		}
public int shards = 18016;
public int charm = 1;
public int item = 1;
public int amountofshard = 1;
public int gold = 12158;
public int green = 12159;
public int crim = 12160;
public int blue = 12163;
public int pouchreq;
public boolean hasitem()
{
if(c.getItems().playerHasItem(charm, 1) && c.getItems().playerHasItem(item, 1) && c.getItems().playerHasItem(18016, amountofshard) && c.getItems().playerHasItem(12155, 1) && c.playerLevel[22] >= req) {
c.getItems().deleteItem(charm, 1);
c.getItems().deleteItem(item, 1);
c.getItems().deleteItem(shards, amountofshard);
c.getItems().deleteItem(12155, 1);
return true;
} else {
c.sendMessage("<shad=2945010>You need the following items: 1x "+c.getItems().getItemName(charm)+" ");
c.sendMessage("<shad=2945010>1x "+c.getItems().getItemName(item)+" ");
c.sendMessage("<shad=2945010>"+amountofshard+"x "+c.getItems().getItemName(shards)+" ");
c.sendMessage("<shad=2945010>You also need a summoning Level of "+req+" to make this pouch ");
return false;
}


}


public void store()
{

c.getPA().sendFrame126("Summoning BoB", 7421);
				for (int k = 0; k < 29; k++)
{
if(c.storeditems[k] > 0)
{
					c.getPA().Frame34(7423, c.storeditems[k], k, 1);
}


if(c.storeditems[k] <= 0)
{
					c.getPA().Frame34(7423, -1, k, 1);
}

}
			

				c.isBanking = true;
				c.storing = true;
				c.getItems().resetItems(5064);
	
				c.getItems().rearrangeBank();
				c.getItems().resetBank();
				c.getItems().resetTempItems();
				c.getOutStream().createFrame(248);

				c.getOutStream().writeWordA(4465);
				c.getOutStream().writeWord(5063);
//c.getOutStream().writeWord(10600);
c.getPA().sendFrame87(286, 0);

				c.flushOutStream();




				
				//c.ResetKeepItems();
				//c.getPA().showInterface(17100);
}
public void SummonNewNPC(int npcID) {
int maxhit = 0;
int attack = 0;
int defence = 0;
switch(npcID)
{
case 6830:
maxhit = 4;
attack = 10;
defence = 80;

break;

case 6826:
maxhit = 6;
attack = 10;
defence = 80;
break;

case 6842:
maxhit = 6;
attack = 10;
defence = 80;
break;

case 6807:
maxhit = 5;
attack = 20;
defence = 80;
c.maxstore = 3;
break;

case 6797:
maxhit = 8;
attack = 20;
defence = 80;
break;


case 7332:
maxhit = 8;
attack = 20;
defence = 80;
break;

case 6832:
maxhit = 8;
attack = 20;
defence = 80;
break;


case 6838:
maxhit = 8;
attack = 20;
defence = 80;
break;

case 7362:
maxhit = 8;
attack = 20;
defence = 80;
break;


case 6848:
maxhit = 8;
attack = 20;
defence = 80;
break;

case 6995:
maxhit = 10;
attack = 20;
defence = 80;
break;

case 6872:
maxhit = 10;
attack = 20;
defence = 80;
break;

case 7354:
maxhit = 11;
attack = 20;
defence = 80;
break;

case 6836:
maxhit = 12;
attack = 20;
defence = 80;
break;

case 6846:
maxhit = 14;
attack = 40;
defence = 80;
break;

case 6808:
maxhit = 12;
attack = 40;
defence = 80;;
break;

case 7371:
case 7369:
case 7368:
case 7370:
case 7352:
maxhit = 11;
attack = 40;
defence = 80;
break;

case 6854:
case 68:
maxhit = 12;
attack = 40;
defence = 80;
break;

case 6868:
maxhit = 12;
attack = 40;
defence = 80;
c.maxstore = 6;
break;

case 6852:
maxhit = 8;
attack = 40;
defence = 80;
break;
case 6834:
maxhit = 14;
attack = 40;
defence = 80;
break;

case 6856:
maxhit = 15;
attack = 40;
defence = 80;
break;

case 7378:
maxhit = 14;
attack = 40;
defence = 80;
break;

case 6824:
maxhit = 13;
attack = 40;
defence = 80;
break;

case 6844:
maxhit = 12;
attack = 40;
defence = 80;
break;

case 6795:
c.maxstore = 12;
maxhit = 11;
attack = 60;
defence = 80;
break;

case 6819:
maxhit = 13;
attack = 60;
defence = 80;
break;
case 6993:
maxhit = 15;
attack = 60;
defence = 80;
break;

case 6858:
maxhit = 11;
attack = 60;
defence = 80;
break;

case 6991:
maxhit = 11;
attack = 60;
defence = 80;
break;


case 7364:
case 7366:
case 7338:
maxhit = 20;
attack = 60;
defence = 80;
break;

case 6810:
maxhit = 11;
attack = 60;
defence = 80;
break;


case 6821:
maxhit = 11;
attack = 60;
defence = 80;
break;


case 6803:
maxhit = 14;
attack = 60;
defence = 80;
break;

case 6828:
maxhit = 18;
attack = 60;
defence = 80;
break;

case 6860:
maxhit = 20;
attack = 60;
defence = 80;
break;


case 6890:
maxhit = 20;
attack = 60;
defence = 80;
break;

case 6816:
c.maxstore = 18;
maxhit = 21;
attack = 60;
defence = 80;
break;

case 6814:
maxhit = 17;
attack = 60;
defence = 80;
break;


case 7372:
case 7373:
case 7374:
maxhit = 11;
attack = 60;
defence = 80;
break;

case 6840:
pouchreq = 71;
break;

case 6817:
maxhit = 11;
attack = 60;
defence = 80;
break;
case 8576:
pouchreq = 999;
break;

case 7346:
maxhit = 25;
attack = 80;
defence = 80;
break;

case 6799:
maxhit = 11;
attack = 60;
defence = 80;
break;

case 6850:
maxhit = 11;
attack = 60;
defence = 80;
break;

case 6862:
maxhit = 22;
attack = 60;
defence = 80;
break;

case 7336:
maxhit = 24;
attack = 60;
defence = 80;
break;

case 6801:
maxhit = 11;
attack = 60;
defence = 80;
break;

case 7356:
case 7358:
case 7360:
maxhit = 26;
attack = 60;
defence = 80;
break;

case 6812:
maxhit = 28;
attack = 60;
defence = 80;
break;


case 6805:
case 7342:
maxhit = 30;
attack = 60;
defence = 80;
break;

case 7330:
maxhit = 31;
attack = 60;
defence = 80;
break;
case 6864:
maxhit = 32;
attack = 60;
defence = 80;
break;
case 6823:
maxhit = 33;
attack = 60;
defence = 80;
break;
case 7340:
maxhit = 34;
attack = 60;
defence = 80;
break;

case 6870:
maxhit = 35;
attack = 60;
defence = 80;
break;


case 7350:
maxhit = 36;
attack = 60;
defence = 80;
break;

case 7376:
maxhit = 37;
attack = 60;
defence = 80;
break;
case 6874:
c.maxstore = 30;
maxhit = 38;
attack = 60;
defence = 80;
break;
case 7344:
maxhit = 39;
attack = 90;
defence = 80;
break;
}
switch(npcID)
{
case 6830:
pouchreq = 0;
break;

case 6826:
pouchreq = 4;
break;

case 6842:
pouchreq = 10;
break;

case 6807:
pouchreq = 13;
break;

case 6797:
pouchreq = 16;
break;


case 7332:
pouchreq = 17;
break;

case 6832:
pouchreq = 18;
break;


case 6838:
pouchreq = 19;
break;

case 7362:
pouchreq = 22;
break;


case 6848:
pouchreq = 23;
break;

case 6995:
pouchreq = 25;
break;

case 6872:
pouchreq = 28;
break;

case 7354:
pouchreq = 29;
break;

case 6836:
pouchreq = 31;
break;

case 6846:
pouchreq = 32;
break;

case 6808:
pouchreq = 33;
break;

case 7371:
case 7369:
case 7368:
case 7370:
case 7352:
pouchreq = 34;
break;

case 6854:
case 68:
pouchreq = 36;
break;

case 6868:
pouchreq = 40;
break;

case 6852:
pouchreq = 41;
break;
case 6834:
pouchreq = 42;
break;

case 6856:
pouchreq = 46;
break;

case 7378:
pouchreq = 46;
break;

case 6824:
pouchreq = 47;
break;

case 6844:
pouchreq = 49;
break;

case 6795:
pouchreq = 52;
break;

case 6819:
pouchreq = 54;
break;
case 6993:
pouchreq = 55;
break;

case 6858:
pouchreq = 56;
break;

case 6991:
pouchreq = 56;
break;


case 7364:
case 7366:
case 7338:
pouchreq = 57;
break;

case 6810:
pouchreq = 58;
break;

case 6866:
pouchreq = 999;
break;

case 6821:
pouchreq = 62;
break;


case 6803:
pouchreq = 63;
break;

case 6828:
pouchreq = 64;
break;

case 6860:
pouchreq = 66;
break;


case 6890:
pouchreq = 66;
break;

case 6816:
pouchreq = 67;
break;

case 6814:
pouchreq = 68;
break;


case 7372:
case 7373:
case 7374:
pouchreq = 70;
break;

case 6840:
pouchreq = 71;
break;

case 6817:
pouchreq = 69;
break;
case 8576:
pouchreq = 999;
break;

case 7346:
pouchreq = 73;
break;

case 6799:
pouchreq = 75;
break;

case 6850:
pouchreq = 74;
break;

case 6862:
pouchreq = 76;
break;

case 7336:
pouchreq = 76;
break;

case 6801:
pouchreq = 78;
break;

case 7356:
case 7358:
case 7360:
pouchreq = 79;
break;

case 6812:
pouchreq = 80;
break;


case 6805:
case 7342:
pouchreq = 83;
break;

case 7330:
pouchreq = 85;
break;
case 6864:
pouchreq = 86;
break;
case 6823:
pouchreq = 88;
break;
case 7340:
pouchreq = 89;
break;

case 6870:
pouchreq = 92;
break;


case 7350:
pouchreq = 93;
break;

case 7376:
pouchreq = 95;
break;
case 6874:
pouchreq = 96;
break;
case 7344:
pouchreq = 99;
break;
}

if(c.playerLevel[22] >= pouchreq)
{
Server.npcHandler.Summon(c, npcID, c.absX, c.absY-1, c.heightLevel, 0, 100, maxhit, false, attack, defence);
c.getItems().deleteItem(c.s, 1);
for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
		if (Server.npcHandler.npcs[i] != null) {
c.npcslot = Server.npcHandler.npcs[i].npcId;
}
}
} else {

c.sendMessage("You need "+pouchreq+" Summoning to summon this monster");
}

}
//c.gfx0(1315);

//    c.summonedNPCS++;
   // c.sendMessage("You Summon a "+name);
 

public int pouch = 12155;
public int req;
public void ItemonItem(int itemUsed, int useWith)
{


//variables
//charm = charm id, item = itemmatirial, amountofshard = shard amount
switch(itemUsed)
{
case 2138:
useWith = pouch;
charm = gold;
req = 1;
item = 2138;
amountofshard = 8;
if(hasitem())
{
c.getItems().addItem(12043, 1);
c.getPA().addSkillXP(300, 22); //AmtExp is different so its defined in the method
}

break;


case 2859:
req = 1;
useWith = pouch;
charm = gold;
item = 2859;
amountofshard = 7;
if(hasitem())
{
c.getItems().addItem(12047, 1);
c.getPA().addSkillXP(500, 22); //AmtExp is different so its defined in the method
}
break;

case 6291:
useWith = pouch;
charm = gold;
item = 6291;
amountofshard = 8;
req = 10;
if(hasitem())
{
c.getItems().addItem(12059, 1);
c.getPA().addSkillXP(800, 22); //AmtExp is different so its defined in the method
}
break;

case 3369:
req = 13;
useWith = pouch;
charm = gold;
item = 3369;
amountofshard = 9;
if(hasitem())
{
c.getItems().addItem(12019, 1);
c.getPA().addSkillXP(1000, 22); //AmtExp is different so its defined in the method
}
break;


case 440:
req = 16;
useWith = pouch;
charm = gold;
item = 440;
amountofshard = 7;
if(hasitem())
{
c.getItems().addItem(12009, 1);
c.getPA().addSkillXP(1500, 22); //AmtExp is different so its defined in the method
}
break;


case 6319:
req = 17;
useWith = pouch;
charm = gold;
item = 6319;
amountofshard = 1;
if(hasitem())
{
c.getItems().addItem(12778, 1);
c.getPA().addSkillXP(1600, 22); //AmtExp is different so its defined in the method
}
break;


case 1783:
req = 18;
useWith = pouch;
charm = green;
item = 1783;
amountofshard = 45;
if(hasitem())
{
c.getItems().addItem(12049, 1);
c.getPA().addSkillXP(2000, 22); //AmtExp is different so its defined in the method
}
break;


case 3095:
req = 19;
useWith = pouch;
charm = green;
item = 3095;
amountofshard = 57;
if(hasitem())
{
c.getItems().addItem(12055, 1);
c.getPA().addSkillXP(2100, 22); //AmtExp is different so its defined in the method
}
break;


case 12168:
req = 22;
useWith = pouch;
charm = crim;
item = 3095;
amountofshard = 64;
if(hasitem())
{
c.getItems().addItem(12808, 1);
c.getPA().addSkillXP(2400, 22); //AmtExp is different so its defined in the method
}
break;


case 2134:
req = 23;
useWith = pouch;
charm = blue;
item = 2134;
amountofshard = 75;
if(hasitem())
{
c.getItems().addItem(12067, 1);
c.getPA().addSkillXP(2800, 22); //AmtExp is different so its defined in the method
}
break;

case 3138:
req = 25;
useWith = pouch;
charm = blue;
item = 3138;
amountofshard = 51;
if(hasitem())
{
c.getItems().addItem(12063, 1);
c.getPA().addSkillXP(3000, 22); //AmtExp is different so its defined in the method
}
break;

case 6032:
req = 28;
useWith = pouch;
charm = green;
item = 6032;
amountofshard = 47;
if(hasitem())
{
c.getItems().addItem(12091, 1);
c.getPA().addSkillXP(4000, 22); //AmtExp is different so its defined in the method
}
break;

case 9976:
req = 29;
useWith = pouch;
charm = green;
item = 9976;
amountofshard = 84;
if(hasitem())
{
c.getItems().addItem(12800, 1);
c.getPA().addSkillXP(4500, 22); //AmtExp is different so its defined in the method
}
break;


case 3325:
req = 31;
useWith = pouch;
charm = crim;
item = 3325;
amountofshard = 81;
if(hasitem())
{
c.getItems().addItem(12053, 1);
c.getPA().addSkillXP(5000, 22); //AmtExp is different so its defined in the method
}
break;


case 12156:
req = 32;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 84;
if(hasitem())
{
c.getItems().addItem(12065, 1);
c.getPA().addSkillXP(5400, 22); //AmtExp is different so its defined in the method
}
break;

case 1519:
req = 33;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 72;
if(hasitem())
{
c.getItems().addItem(12021, 1);
c.getPA().addSkillXP(5300, 22); //AmtExp is different so its defined in the method
}
break;

case 12164:
req = 34;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 74;
if(hasitem())
{
c.getItems().addItem(12818, 1);
c.getPA().addSkillXP(5200, 22); //AmtExp is different so its defined in the method
}
break;

case 12165:
req = 34;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 74;
if(hasitem())
{
c.getItems().addItem(12814, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;

case 12167:
req = 34;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 74;
if(hasitem())
{
c.getItems().addItem(12798, 1);
c.getPA().addSkillXP(6000, 22); //AmtExp is different so its defined in the method
}
break;

case 2349:
req = 36;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 102;
if(hasitem())
{
c.getItems().addItem(12073, 1);
c.getPA().addSkillXP(6100, 22); //AmtExp is different so its defined in the method
}
break;

case 6010:
req = 40;
useWith = pouch;
charm = gold;
item = itemUsed;
amountofshard = 11;
if(hasitem())
{
c.getItems().addItem(12087, 1);
c.getPA().addSkillXP(6200, 22); //AmtExp is different so its defined in the method
}
break;


case 249:
req = 41;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 78;
if(hasitem())
{
c.getItems().addItem(12071, 1);
c.getPA().addSkillXP(6300, 22); //AmtExp is different so its defined in the method
}
break;


case 12153:
req = 42;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 104;
if(hasitem())
{
c.getItems().addItem(12051, 1);
c.getPA().addSkillXP(6400, 22); //AmtExp is different so its defined in the method
}
break;



case 2351:
req = 46;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 125;
if(hasitem())
{
c.getItems().addItem(12075, 1);
c.getPA().addSkillXP(6500, 22); //AmtExp is different so its defined in the method
}
break;


case 13403:
req = 46;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 111;
if(hasitem())
{
c.getItems().addItem(12816, 1);
c.getPA().addSkillXP(6600, 22); //AmtExp is different so its defined in the method
}
break;


case 1635:
req = 47;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 88;
if(hasitem())
{
c.getItems().addItem(12041, 1);
c.getPA().addSkillXP(6700, 22); //AmtExp is different so its defined in the method
}
break;

case 2132:
req = 49;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 117;
if(hasitem())
{
c.getItems().addItem(12061, 1);
c.getPA().addSkillXP(6800, 22); //AmtExp is different so its defined in the method
}
break;


case 9978:
req = 52;
useWith = pouch;
charm = gold;
item = itemUsed;
amountofshard = 12;
if(hasitem())
{
c.getItems().addItem(12007, 1);
c.getPA().addSkillXP(6900, 22); //AmtExp is different so its defined in the method
}
break;


case 12161:
req = 54;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 106;
if(hasitem())
{
c.getItems().addItem(12036, 1);
c.getPA().addSkillXP(7000, 22); //AmtExp is different so its defined in the method
}
break;

case 1937:
req = 55;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 151;
if(hasitem())
{
c.getItems().addItem(12027, 1);
c.getPA().addSkillXP(7100, 22); //AmtExp is different so its defined in the method
}
break;

case 2353:
req = 56;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 141;
if(hasitem())
{
c.getItems().addItem(12077, 1);
c.getPA().addSkillXP(7200, 22); //AmtExp is different so its defined in the method
}
break;

case 311:
req = 56;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 109;
if(hasitem())
{
c.getItems().addItem(12531, 1);
c.getPA().addSkillXP(7300, 22); //AmtExp is different so its defined in the method
}

break;


case 10099:
req = 57;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 154;
if(hasitem())
{
c.getItems().addItem(12810, 1);
c.getPA().addSkillXP(7400, 22); //AmtExp is different so its defined in the method
}
break;


case 10103:
req = 57;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 153;
if(hasitem())
{
c.getItems().addItem(12812, 1);
c.getPA().addSkillXP(7500, 22); //AmtExp is different so its defined in the method
}
break;


case 10095:
req = 57;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 155;
if(hasitem())
{
c.getItems().addItem(12784, 1);
c.getPA().addSkillXP(7600, 22); //AmtExp is different so its defined in the method
}
break;


case 9736:
req = 58;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 141;
if(hasitem())
{
c.getItems().addItem(12805, 1);
c.getPA().addSkillXP(7700, 22); //AmtExp is different so its defined in the method
}
break;

//case 12161:
//useWith = pouch;
//charm = green;
//item = itemUsed;
//amountofshard = 141;
//if(hasitem())
//{
//c.getItems().addItem(12037, 1);

//}
//break;

case 7801:
req = 63;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 116;
if(hasitem())
{
c.getItems().addItem(12015, 1);
c.getPA().addSkillXP(7800, 22); //AmtExp is different so its defined in the method
}
break;


case 8431://stranger plant
req = 64;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 128;
if(hasitem())
{
c.getItems().addItem(12045, 1);
c.getPA().addSkillXP(7900, 22); //AmtExp is different so its defined in the method
}
break;


case 2359://stranger plant
req = 66;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 152;
if(hasitem())
{
c.getItems().addItem(12079, 1);
c.getPA().addSkillXP(8000, 22); //AmtExp is different so its defined in the method
}
break;


case 2150://stranger plant
req = 66;
useWith = pouch;
charm = gold;
item = itemUsed;
amountofshard = 11;
if(hasitem())
{
c.getItems().addItem(12123, 1);
c.getPA().addSkillXP(8100, 22); //AmtExp is different so its defined in the method
}
break;


case 7939://stranger plant
req = 67;
useWith = pouch;
charm = gold;
item = itemUsed;
amountofshard = 1;
if(hasitem())
{
c.getItems().addItem(12031, 1);
c.getPA().addSkillXP(8200, 22); //AmtExp is different so its defined in the method
}
break;


case 383://stranger plant
req = 68;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 110;
if(hasitem())
{
c.getItems().addItem(12029, 1);
c.getPA().addSkillXP(8300, 22); //AmtExp is different so its defined in the method
}
break;


case 1963://stranger plant
req = 69;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 130;
if(hasitem())
{
c.getItems().addItem(12033, 1);
c.getPA().addSkillXP(8400, 22); //AmtExp is different so its defined in the method
}
break;


case 1933://stranger plant
req = 70;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 79;
if(hasitem())
{
c.getItems().addItem(12820, 1);
c.getPA().addSkillXP(8500, 22); //AmtExp is different so its defined in the method
}
break;


case 10117://stranger plant
req = 71;
useWith = pouch;
charm = gold;
item = itemUsed;
amountofshard = 14;
if(hasitem())
{
c.getItems().addItem(12057, 1);
c.getPA().addSkillXP(8600, 22); //AmtExp is different so its defined in the method
}
break;


case 14616://stranger plant
req = 72;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 165;
if(hasitem())
{
c.getItems().addItem(14623, 1);
c.getPA().addSkillXP(8700, 22); //AmtExp is different so its defined in the method
}
break;

case 4188://changed
req = 73;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 195; 
if(hasitem())
{
c.getItems().addItem(12792, 1);
c.getPA().addSkillXP(8800, 22); //AmtExp is different so its defined in the method
}
break;



case 6979://changed
req = 74;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 166; 
if(hasitem())
{
c.getItems().addItem(12069, 1);
c.getPA().addSkillXP(8900, 22); //AmtExp is different so its defined in the method
}
break;


case 2460://changed
req = 75;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 168; 
if(hasitem())
{
c.getItems().addItem(12011, 1);
c.getPA().addSkillXP(9000, 22); //AmtExp is different so its defined in the method
}
break;


case 2361://changed
req = 75;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 144; 
if(hasitem())
{
c.getItems().addItem(12081, 1);
c.getPA().addSkillXP(9100, 22); //AmtExp is different so its defined in the method
}
break;


case 10020://changed
req = 76;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 141; 
if(hasitem())
{
c.getItems().addItem(12782, 1);
c.getPA().addSkillXP(9200, 22); //AmtExp is different so its defined in the method
}
break;


case 12162: //changed
req = 77;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 174; 
if(hasitem())
{
c.getItems().addItem(12794, 1);
c.getPA().addSkillXP(9300, 22); //AmtExp is different so its defined in the method
}
break;



case 5933: //changed
req = 78;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 124; 
if(hasitem())
{
c.getItems().addItem(12013, 1);
c.getPA().addSkillXP(9400, 22); //AmtExp is different so its defined in the method
}
break;


case 1442: //changed
req = 79;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 198; 
if(hasitem())
{
c.getItems().addItem(12802, 1);
c.getPA().addSkillXP(9500, 22); //AmtExp is different so its defined in the method
}
break;

case 1438: //changed
req = 79;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 198; 
if(hasitem())
{
c.getItems().addItem(12806, 1);
c.getPA().addSkillXP(9600, 22); //AmtExp is different so its defined in the method
}
break;


case 1440: //changed
req = 79;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 202; 
if(hasitem())
{
c.getItems().addItem(12804, 1);
c.getPA().addSkillXP(9700, 22); //AmtExp is different so its defined in the method
}
break;

case 571: //changed
req = 80;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 128;
if(hasitem())
{
c.getItems().addItem(12025, 1);
c.getPA().addSkillXP(9900, 22); //AmtExp is different so its defined in the method
}
break;

case 6155: //changed
req = 83;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 1;
if(hasitem())
{
c.getItems().addItem(12017, 1);
c.getPA().addSkillXP(10000, 22); //AmtExp is different so its defined in the method
}
break;


case 4699: //changed lava rune
req = 85;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 219;
if(hasitem())
{
c.getItems().addItem(12788, 1);
c.getPA().addSkillXP(11000, 22); //AmtExp is different so its defined in the method
}
break;


case 10149: //changed
req = 86;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 150;
if(hasitem())
{
c.getItems().addItem(12776, 1);
c.getPA().addSkillXP(12000, 22); //AmtExp is different so its defined in the method
}
break;


case 2363: //changed
req = 88;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 1;
if(hasitem())
{
c.getItems().addItem(12083, 1);
c.getPA().addSkillXP(13800, 22); //AmtExp is different so its defined in the method
}
break;



case 1486: //changed
req = 89;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 140;
if(hasitem())
{
c.getItems().addItem(12039, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;


case 1444: //changed
req = 92;
useWith = pouch;
charm = blue;
item = itemUsed;
amountofshard = 222;
if(hasitem())
{
c.getItems().addItem(12786, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;

case 3228: //changed
req = 93;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 203;
if(hasitem())
{
c.getItems().addItem(12089, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;


case 7979: //changed abyss head
req = 93;
useWith = pouch;
charm = green;
item = itemUsed;
amountofshard = 113;
if(hasitem())
{
c.getItems().addItem(12796, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;


case 1115: //changed
req = 95;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 198;
if(hasitem())
{
c.getItems().addItem(12822, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;

case 10818: //changed
req = 96;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 211;
if(hasitem())
{
c.getItems().addItem(12093, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;


case 1119: //changed
req = 99;
useWith = pouch;
charm = crim;
item = itemUsed;
amountofshard = 178;
if(hasitem())
{
c.getItems().addItem(12790, 1);
c.getPA().addSkillXP(5800, 22); //AmtExp is different so its defined in the method
}
break;


}
}

}