package server.model.players;

import java.util.HashMap;

/**
 * @author Sanity
 */

public class Food {


  private Client c;

  public Food (Client c) {
    this.c = c;  
  }
  public static enum FoodToEat {    
    
	HEIM_CRAB(18159,12,"Heim crab"),
	ROCKTAIL(15272,24,"Rocktail"),
    MANTA(391,22,"Manta Ray"),
    SHARK(385,20,"Shark"),
    LOBSTER(379,12,"Lobster"),
    TROUT(333,7,"Trout"),
    SALMON(329,9,"Salmon"),
    SWORDFISH(373,14,"Swordfish"),
    TUNA(361,10,"Tuna"),
    MONKFISH(7946,16,"Monkfish"),
    SEA_TURTLE(397,21,"Sea Turtle"),
    CAKE(1891,4,"Cake"),
    BASS(365,13,"Bass"),
    COD(339,7,"Cod"),
    POTATO(1942,1,"Potato"),
    BAKED_POTATO(6701,4,"Baked Potato"),
    POTATO_WITH_CHEESE(6705,16,"Potato with Cheese"),
    EGG_POTATO(7056,16,"Egg Potato"),
    CHILLI_POTATO(7054,14,"Chilli Potato"),
    MUSHROOM_POTATO(7058,20,"Mushroom Potato"),
    TUNA_POTATO(7060,22,"Tuna Potato"),
    SHRIMPS(315,3,"Shrimps"),
    HERRING(347,5,"Herring"),
    SARDINE(325,4,"Sardine"),
    CHOCOLATE_CAKE(1897,5,"Chocolate Cake"),
    ANCHOVIES(319,1,"Anchovies"),
    PLAIN_PIZZA(2289,7,"Plain Pizza"),
    MEAT_PIZZA(2293,8,"Meat Pizza"),
    ANCHOVY_PIZZA(2297,9,"Anchovy Pizza"),
    PINEAPPLE_PIZZA(2301,11,"Pineapple Pizza"),
    BREAD(2309,5,"Bread"),
    APPLE_PIE(2323,7,"Apple Pie"),
    REDBERRY_PIE(2325,5,"Redberry Pie"),
    MEAT_PIE(2327,6,"Meat Pie"),
    PIKE(351,8,"Pike"),
    POTATO_WITH_BUTTER(6703,14,"Potato with Butter"),
    BANANA(1963,2,"Banana"),
    PEACH(6883,8,"Peach"),
    ORANGE(2108,2,"Orange"),
    PINEAPPLE_RINGS(2118,2,"Pineapple Rings"),
    PINEAPPLE_CHUNKS(2116,2,"Pineapple Chunks");



    private int id; private int heal; private String name;

    private FoodToEat(int id, int heal, String name) {
      this.id = id;
      this.heal = heal;
      this.name = name;    
    }

    public int getId() {
      return id;
    }

    public int getHeal() {
      return heal;
    }

    public String getName() {
      return name;
    }
    public static HashMap <Integer,FoodToEat> food = new HashMap<Integer,FoodToEat>();

    public static FoodToEat forId(int id) {
      return food.get(id);
    }

    static {
    for (FoodToEat f : FoodToEat.values())
      food.put(f.getId(), f);
    }
  }
  public int gwdarmourStat(double amount) {
		return (int)(c.getLevelForXP(c.playerXP[3]) * amount);
	}
  public boolean hasFullPrimal() {
    return c.playerEquipment[c.playerHat] == 13362 && c.playerEquipment[c.playerChest] == 13360 && c.playerEquipment[c.playerLegs] == 13361;
  }

  public boolean torva() {
    return c.playerEquipment[c.playerHat] == 13362 && c.playerEquipment[c.playerChest] == 13360 && c.playerEquipment[c.playerLegs] == 13361;
  }

    	public void eat(int id, int slot) {
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(id,slot,1);
			FoodToEat f = FoodToEat.food.get(id);
			if (c.playerLevel[3] < gwdarmourStat(1.41) && c.torva()) {
				c.playerLevel[3] += f.getHeal();
				if (c.playerLevel[3] > gwdarmourStat(1.41))
					c.playerLevel[3] = gwdarmourStat(1.41);
			}
			else if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3]) && !c.torva()) {
				c.playerLevel[3] += f.getHeal();
				if(id != 15272) {
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
//this makes sure normal food doesn't overload
			} else {
// this says if their eating rocktails and their hp level is more then their player xp + 10, then make it playerxp + 10. If it isnt then it will overload anyway.
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3])) {
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]) + 10;
			}
			}
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the " + f.getName() + ".");
		}			
	}


  public boolean isFood(int id) {
    return FoodToEat.food.containsKey(id);
  }  


}