package server.world;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Hashtable;

public final class WorldMap {

	public static Hashtable<Integer, GameObject> gameObjects = new Hashtable<Integer, GameObject>();
	public static boolean solidObjectExists(int x, int y) {
		GameObject go = gameObjects.get(y + (x << 16));
		if (go != null && go.type() == 2 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 10 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 9 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 8 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 7 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 4 && go.x() == x && go.y() == y) {
			return true;
		}
		return false;
	}

	public static boolean canMove(int baseX, int baseY, int toX, int toY) {
		int diffX = baseX - toX;
		int diffY = baseY - toY;
		int moveX = 0;
		int moveY = 0;
		if (diffX < 0) {
			moveX = 1;
		} else if (diffX > 0) {
			moveX = -1;
		}
		if (diffY < 0) {
			moveY = 1;
		} else if (diffY > 0) {
			moveY = -1;
		}
		if (moveX > 0 && moveY > 0) {
			if (solidObjectExists(baseX + 1, baseY + 1)
					|| solidObjectExists(baseX + 1, baseY)
					|| solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY < 0) {
			if (solidObjectExists(baseX - 1, baseY - 1)
					|| solidObjectExists(baseX - 1, baseY)
					|| solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX > 0 && moveY < 0) {
			if (solidObjectExists(baseX + 1, baseY - 1)
					|| solidObjectExists(baseX + 1, baseY)
					|| solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY > 0) {
			if (solidObjectExists(baseX - 1, baseY + 1)
					|| solidObjectExists(baseX - 1, baseY)
					|| solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY == 0) {
			if (solidObjectExists(baseX - 1, baseY)) {
				return false;
			}
		} else if (moveX > 0 && moveY == 0) {
			if (solidObjectExists(baseX + 1, baseY)) {
				return false;
			}
		} else if (moveX == 0 && moveY < 0) {
			if (solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX == 0 && moveY > 0) {
			if (solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void loadWorldMap() {
		try {
			RandomAccessFile in = null;
			byte[] cache = null;
			int ptr = 0;
			long a = System.currentTimeMillis();
			in = new RandomAccessFile("./Data/worldmap.bin", "r");
			cache = new byte[(int) in.length()];
			in.read(cache, 0, (int) in.length());
			in.close();
			for (int i = 0; i < 1280618; i++/* ,j++ */) {
				int objectId = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				int objectX = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				int objectY = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				int objectHeight = cache[ptr++] & 0xFF;
				int objectType = cache[ptr++] & 0xFF;
				int objectFace = cache[ptr++] & 0xFF;
				GameObject go = new GameObject(objectId, objectType, objectX, objectY, objectFace);
				if (go.type() != 0) {
					gameObjects.put(go.y() + (go.x() << 16), go);
				}
			}
			long took = System.currentTimeMillis() - a;
			System.out.println("Loaded " + gameObjects.size() + " clips.");
			System.out.println("Loaded WorldMap In (" + took + " ms)... ");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static final HashMap<Integer, String> map = new HashMap<Integer, String>();
	
}