package server.world.map;

public class Z
{

    public Z(int futureX, int futureY, int height, int l)
    {
        x = futureX;
        y = futureY;
        KKLI = height;
        s = l;
    }

    public final int hashCode()
    {
        int i = 1;
        i = 31 * i + KKLI;
        i = 31 * i + s;
        i = 31 * i + x;
        i = 31 * i + y;
        return i;
    }

    public final boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Z z = (Z)obj;
        if(KKLI != z.KKLI)
            return false;
        if(s != z.s)
            return false;
        if(x != z.x)
            return false;
        return y == z.y;
    }

    public int x;
    public int y;
    public int KKLI;
    public int s;
}

