package server.world;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class WalkingHandler {

	public static final int WIDTH = 12000;
	public static final int HEIGHT = 9900;

	private final TiledMap map;

	private WalkingHandler() {
		this.map = new TiledMap(WIDTH, HEIGHT);
	}

	private static class SingletonContainer {
		private static final WalkingHandler SINGLETON = new WalkingHandler();
	}

	public static WalkingHandler getSingleton() {
		return SingletonContainer.SINGLETON;
	}

	public boolean traversable(int x, int y, int direction) {
		int flag = map.getFlag(x, y);
		//System.out.println(direction);
        if (direction == 0 && (flag == 1 || flag == 4 || flag == 6 || flag == 7 || flag == 9 || flag == 11 || flag == 13 || flag == 14)) {
            return false;
        } else if (direction == 4 && (flag == 1 || flag == 7 || flag == 15 || flag == 10 || flag == 11 || flag == 12 || flag == 14 || flag == 5)) {
            return false;
        } else if (direction == 8 && (flag == 1 || flag == 2 || flag == 3 || flag == 4 || flag == 5 || flag == 6 || flag == 7 || flag == 12)) {
            return false;
        } else if (direction == 12 && (flag == 1 || flag == 3 || flag == 6 || flag == 9 || flag == 10 || flag == 11 || flag == 12 || flag == 8)) {
            return false;
        } else if(flag > 0 && flag < 15) {
			return false;
		}
        return true;
	}

	public void initialize() throws Exception {
		long delta = System.currentTimeMillis();
		RandomAccessFile raf = new RandomAccessFile("data/lolmap.bin", "r");
		FileChannel channel = raf.getChannel();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		int length = buffer.getInt();
		for(int i = 0; i < length; i++) {
			int x = buffer.getShort();
			int y = buffer.getShort();
			byte flag = buffer.get();
			map.flag(x, y, flag);
		}
		System.out.println("Loaded clipmap in " + (System.currentTimeMillis() - delta) + "ms.");
	}

	private static class TiledMap {
	
		private final byte[] plane;

		public TiledMap(int width, int height) {
			this.plane = new byte[width * 10000 + height];
		}

		public int getFlag(int x, int y) {
			return plane[x * 10000 + y];
		}

		public void flag(int x, int y, byte flag) {
			this.plane[x * 10000 + y] = flag;
		}

	}

}