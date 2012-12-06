package server.world;

public final class GameObject {
    private int id;
    private int type;
    private int x;
    private int y;
    private int face;

    public GameObject(int id, int type, int x, int y, int face) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.face = face;
    }

    public int id() {
        return id;
    }

    public int type() {
        return type;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

	public int getFace() {
		return face;
	}
}