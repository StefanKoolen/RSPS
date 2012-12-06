package server.model.players;
 
import server.model.players.*;
 
/**
 *
 * @author Karmakaidan <mark@project-innovation.org>
 * @author Advocatus <davidcntt@hotmail.com>
 *
 */
public class ArmourSets {
         
    private static int[][] armourSets = {        
      /* prossy */         { 9666, 9672, 9674, 9676 },
	  /* initiatie*/		 { 9668, 5574, 5575, 5576},
        /*bronze_(l)*/       { 11814, 1155, 1117, 1075, 1189},
        /*bronze_(sk)*/      { 11816, 1155, 1117, 1087, 1189},
        /*iron_(l)*/         { 11818, 1153, 1115, 1067, 1191},
        /*iron_(sk)*/        { 11820, 1153, 1115, 1081, 1191},
        /*steel_(l)*/        { 11822, 1157, 1119, 1069, 1193},
        /*steel_(sk)*/       { 11824, 1157, 1119, 1083, 1193},
        /*black_(l)*/        { 11826, 1165, 1125, 1077, 1195},
        /*black_(sk)*/       { 11828, 1165, 1125, 1089, 1195},
        /*mithril_(l)*/      { 11830, 1159, 1121, 1071, 1197},
        /*mithril_(sk)*/     { 11832, 1159, 1121, 1085, 1197},
        /*adamant_(l)*/      { 11834, 1161, 1123, 1073, 1199},
        /*adamant_(sk)*/     { 11836, 1161, 1123, 1091, 1199},
        /*rune_(l)*/         { 11838, 1163, 1127, 1079, 1201},
        /*rune_(sk)*/        { 11840, 1163, 1127, 1093, 1201},
        /*dragon_chain_(l)*/ { 11842, 1149, 3140, 4087, 1187},
        /*dragon_chain_(sk)*/{ 11844, 1149, 3140, 4585},
        /*ahrim's*/          { 11846, 4708, 4712, 4714, 4710},
        /*dharok's*/         { 11848, 4716, 4720, 4722, 4718},
        /*guthan's*/         { 11850, 4724, 4728, 4730, 4726},
        /*karil's*/          { 11852, 4732, 4736, 4738, 4734},
        /*torag's*/          { 11854, 4745, 4749, 4751, 4747},
        /*verac's*/          { 11856, 4753, 4757, 4759, 4755},
        /*third_age_melee*/  { 11858, 10350, 10346, 10348, 10352},
        /*third_age_range*/  { 11860, 10330, 10332, 10334, 10336},
        /*third_age_mage*/   { 11862, 10338, 10340, 10342, 10344},
        /*green_dragonhide*/ { 11864, 1135, 1099, 1065},
        /*blue_dragonhide*/  { 11866, 2499, 2493, 2487},
        /*red_dragonhide*/   { 11868, 2501, 2495, 2489},
        /*black_dragonhide*/ { 11870, 2503, 2497, 2491},
        /*infinity_robes*/   { 11874, 6918, 6916, 6924},
        /*splitbark*/        { 11876, 3385, 3387, 3389},
        /*black_(t)*/        { 11878, 2587, 2583, 2585, 2589},
        /*black_(g)*/        { 11882, 2595, 2591, 2593, 2597},
        /*adamant_(t)*/      { 11886, 2605, 2599, 2601, 2603},
        /*adamant_(g)*/      { 11890, 2613, 2607, 2609, 2611},
        /*rune_(t)*/         { 11894, 2627, 2623, 2625, 2629},
        /*rune_(g)*/         { 11898, 2619, 2615, 2617, 2621},
        /*enchanted_robe*/   { 11902, 7400, 7399, 7398},
        /*wizard_robe_(t)*/  { 11904, 7396, 10687, 7388},
        /*wizard_robe_(g)*/  { 11906, 7394, 10686, 7386},
        /*guthix*/           { 11926, 2673, 2669, 2671, 2675},
        /*saradomin*/        { 11928, 2665, 2661, 2663, 2667},
        /*zamorak*/          { 11930, 2657, 2653, 2655, 2659},
        /*gilded*/           { 11938, 3486, 3481, 3483, 3488},
        /*rockshell*/        { 11942, 6128, 6129, 6130},
        /*spined*/           { 11944, 6131, 6133, 6135},
        /*skeletal*/         { 11946, 6137, 6139, 6141},
        /*dragon'hai_robes*/ { 14525, 14499, 14497, 14501},
        /*elite_black*/      { 14527, 14494, 14492, 14490},
        /*dragon_plate_(l)*/ { 14529, 11335, 14479, 4087},
        /*dragon_plate_(sk)*/{ 14531, 11335, 14479, 4585},
        /*armadyl*/          { 19588, 19422, 19413, 19416, 19425},
        /*bandos*/           { 19592, 19437, 19428, 19431, 19440},
        /*ancient*/          { 19596, 19407, 19388, 19401, 19410},
    };
     
    public static boolean isSet(int id) {
        return getSet(id) != null;
    }
     
    private static int[] getSet(int id) {
        for (int i = 0; i < armourSets.length; i++)
            if(armourSets[i][0] == id)
                return armourSets[i];
        return null;
    }
 
    public static void handleSet(Client c, int id) {
        if (!c.getItems().playerHasItem(id)) {
            return;
        }
        int [] data = getSet(id);
        if(data == null)
            return;
        if (c.getItems().freeSlots() < (data.length - 2)) {
            c.sendMessage("You do not have enough free inventory slots to do this.");
            return;
        }
        c.getItems().deleteItem(id, 1);
        for(int i = 1; i < data.length; i++) {
            c.getItems().addItem(data[i], 1);
        }
    }
}