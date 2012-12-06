package server.model.minigames;

import server.model.players.Client;
public class CastleWarObjects {

	public void handleObject(Client c, int id, int x, int y) {
		switch (id) {
			case 4469:
				if (c.castleWarsTeam == 2) {
					c.sendMessage("You are not allowed in the other teams spawn point.");
					break;
				}
				if (x == 2426) {
					if (c.getY() == 3080)
						c.getPA().movePlayer(2426, 3081, c.heightLevel);
					else if (c.getY() == 3081)
						c.getPA().movePlayer(2426, 3080, c.heightLevel);
				} else if (x == 2422) {
					if (c.getX() == 2422)
						c.getPA().movePlayer(2423, 3076, c.heightLevel);
					else if (c.getX() == 2423)
						c.getPA().movePlayer(2422, 3076, c.heightLevel);
				}
			break;
			case 4470:
				if (c.castleWarsTeam == 1) {
					c.sendMessage("You are not allowed in the other teams spawn point.");
					break;
				}
				if (x == 2373 && y == 3126) {
					if (c.getY() == 3126)
						c.getPA().movePlayer(2373, 3127, 1);
					else if (c.getY() == 3127)
						c.getPA().movePlayer(2373, 3126, 1);
				} else if (x == 2377 && y == 3131) {
					if (c.getX() == 2376)
						c.getPA().movePlayer(2377, 3131, 1);
					else if (c.getX() == 2377)
						c.getPA().movePlayer(2376, 3131, 1);
				}
			break;
			case 4417:
				if (x == 2428 && y == 3081 && c.heightLevel == 1)
					c.getPA().movePlayer(2430, 3080, 2);
				if (x == 2425 && y == 3074 && c.heightLevel == 2)
					c.getPA().movePlayer(2426, 3074, 3);
				if (x == 2419 && y == 3078 && c.heightLevel == 0)
					c.getPA().movePlayer(2420, 3080, 1);
			break;
			case 4415:
				if (x == 2419 && y == 3080 && c.heightLevel == 1)
					c.getPA().movePlayer(2419, 3077, 0);
				if (x == 2430 && y == 3081 && c.heightLevel == 2)
					c.getPA().movePlayer(2427, 3081, 1);
				if (x == 2425 && y == 3074 && c.heightLevel == 3)
					c.getPA().movePlayer(2425, 3077, 2);
				if (x == 2374 && y == 3133 && c.heightLevel == 3)
					c.getPA().movePlayer(2374, 3130, 2);
				if (x == 2369 && y == 3126 && c.heightLevel == 2)
					c.getPA().movePlayer(2372, 3126, 1);
				if (x == 2380 && y == 3127 && c.heightLevel == 1)
					c.getPA().movePlayer(2380, 3130, 0);
			break;
			case 4411:
				if (x == 2421 && y == 3073 && c.heightLevel == 1)
					c.getPA().movePlayer(c.getX(), c.getY(), 0);
			break;
			case 4419:
				if (x == 2417 && y == 3074 && c.heightLevel == 0)
					c.getPA().movePlayer(2416, 3074, 0);
			break;
			case 4911:
				if (x == 2421 && y == 3073 && c.heightLevel == 1)
					c.getPA().movePlayer(2421, 3074, 0);
				if (x == 2378 && y == 3134 && c.heightLevel == 1)
					c.getPA().movePlayer(2378, 3133, 0);
			break;
			case 1747:
				if (x == 2421 && y == 3073 && c.heightLevel == 0)
					c.getPA().movePlayer(2421, 3074, 1);
				if (x == 2378 && y == 3134 && c.heightLevel == 0)
					c.getPA().movePlayer(2378, 3133, 1);
			break;
			case 4912:
				if (x == 2430 && y == 3082 && c.heightLevel == 0)
					c.getPA().movePlayer(c.getX(), c.getY() + 6400, 0);
				if (x == 2369 && y == 3125 && c.heightLevel == 0)
					c.getPA().movePlayer(c.getX(), c.getY() + 6400, 0);
			break;
			case 1757:
				if (x == 2400 && y == 9508 && c.heightLevel == 0)
					c.getPA().movePlayer(c.getX(), c.getY() - 6400, 0);
				if (x == 2399 && y == 9499 && c.heightLevel == 0)
					c.getPA().movePlayer(c.getX(), c.getY() - 6400, 0);
			break;
			
			case 4418:
				if (x == 2380 && y == 3127 && c.heightLevel == 0)
					c.getPA().movePlayer(2379, 3127, 1);
				if (x == 2369 && y == 3126 && c.heightLevel == 1)
					c.getPA().movePlayer(2369, 3127, 2);
				if (x == 2374 && y == 3131 && c.heightLevel == 2)
					c.getPA().movePlayer(2373, 3133, 3);	
			break;
			case 4420:
				if (x == 2382 && y == 3131 && c.heightLevel == 0)
					if (c.getX() >= 2383 && c.getX() <= 2385)
						c.getPA().movePlayer(2382,3130,0);
					else
						c.getPA().movePlayer(2383,3133,0);
			break;
			default:
			
			break;
		
		}	
	}

}