package server.world.map;

import java.util.ArrayList;
import java.util.Iterator;

public class C
{

    public C(ArrayList arraylist, int i)
    {
        I = new ArrayList();
        I.clear();
        Z = i;
        int j;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); I.add(Integer.valueOf(j)))
            j = ((Integer)iterator.next()).intValue();

    }

    public final int hashCode()
    {
        int i = 1;
        i = 31 * i + (I != null ? I.hashCode() : 0);
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
        C c = (C)obj;
        if(I == null)
        {
            if(c.I != null)
                return false;
        } else
        if(!I.equals(c.I))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
	public ArrayList I;
    public int Z;
}

