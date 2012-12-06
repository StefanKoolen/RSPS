package server.world.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import server.model.players.*;
import server.*;


public class I
{

    public I()
    {
    }

    public static final void I(boolean flag)
    {
        try
        {
            if(flag)
            {
                B.I();
                containsKey();
                close();
                equals = null;
                get = null;
                for(int i = 0; i < 10331; i++)
                    if(VirtualWorld.I[i].size() <= 0)
                        VirtualWorld.I[i] = null;

            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private static int KKLI(int i)
    {
        return equals[i];
    }

    private static int add(int i)
    {
        return get[i];
    }

    public static final int append(int i, int j)
    {
        int k = i + j;
        if(k <= 3 && k >= 0)
            return k;
        if(k > 3)
        {
            int l = k - 4;
            k = l;
        } else
        if(k < 0)
        {
			int i1 = k + 4;
            k = k;
        }
        return k;
    }

    private static void close()
    {
        RandomAccessFile randomaccessfile = null;
        int i = 0;
        byte byte0 = 70;
        byte abyte0[] = null;
        int j = 0;
        long l = System.currentTimeMillis();
        try
        {
            randomaccessfile = new RandomAccessFile("./Data/worldmap.bin", "r");
            abyte0 = new byte[(int)randomaccessfile.length()];
            randomaccessfile.read(abyte0, 0, (int)randomaccessfile.length());
            randomaccessfile.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        long l1 = System.currentTimeMillis() - l;
        System.out.println((new StringBuilder()).append("Loaded WorldMap In (").append(l1).append(" ms)... ").toString());
        for(int k = 0; k < B.j[0]; k++)
        {
            int i1 = 0;
            int j1 = 0;
            int k1 = 0;
            int i2 = 0;
            int j2 = 0;
            int k2 = 0;
            int l2 = 0;
            try
            {
                i1 = (abyte0[j++] & 0xff) << B.j[2] | abyte0[j++] & 0xff;
                j1 = (abyte0[j++] & 0xff) << B.j[2] | abyte0[j++] & 0xff;
                k1 = (abyte0[j++] & 0xff) << B.j[2] | abyte0[j++] & 0xff;
                i2 = abyte0[j++] & 0xff;
                j2 = abyte0[j++] & 0xff;
                l2 = k2 = abyte0[j++] & 0xff;
            }
            catch(Exception exception1)
            {
                exception1.printStackTrace();
            }
            int i3 = 0;
            int j3 = 0;
            boolean flag = false;
            if(j2 >= B.j[3] && j2 <= B.j[4] || j2 == B.j[5])
                continue;
            Z az[] = new Z[byte0];
            int ai[] = new int[byte0];
            int k3 = add(i1);
            int l3 = KKLI(i1);
            byte byte1 = -1;
            for(int i4 = 0; i4 < byte0; i4++)
                ai[i4] = -1;

            if(j2 <= 5)
            {
                if(j2 == B.j[10])
                {
                    if(k2 == 0)
                        k2 = B.j[6];
                    else
                    if(k2 == 1)
                        k2 = B.j[7];
                    else
                    if(k2 == 2)
                        k2 = B.j[8];
                    else
                    if(k2 == 3)
                        k2 = B.j[9];
                    else
                        System.out.println((new StringBuilder()).append("Invalid face: ").append(k2).toString());
                } else
                if(j2 == B.j[11])
                {
                    if(k2 == 0)
                    {
                        k2 = -1;
                        j1++;
                    } else
                    if(k2 == 1)
                    {
                        k2 = -1;
                        k1++;
                    } else
                    if(k2 == 2)
                    {
                        k2 = -1;
                        j1++;
                    } else
                    if(k2 == 3)
                    {
                        k2 = -1;
                        k1++;
                    } else
                    {
                        System.out.println((new StringBuilder()).append("Invalid face: ").append(k2).toString());
                    }
                } else
                if(j2 == B.j[12])
                {
                    if(k2 == 0)
                        k2 = -1;
                    else
                    if(k2 == 1)
                        k2 = -1;
                    else
                    if(k2 == 2)
                        k2 = -1;
                    else
                    if(k2 == 3)
                        k2 = -1;
                    else
                        System.out.println((new StringBuilder()).append("Invalid face: ").append(k2).toString());
                } else
                if(j2 == B.j[14])
                {
                    if(k2 == 0)
                        k2 = B.j[14];
                    else
                    if(k2 == 1)
                        k2 = 0;
                    else
                    if(k2 == 2)
                        k2 = 12;
                    else
                    if(k2 == 3)
                        k2 = 8;
                    else
                        System.out.println((new StringBuilder()).append("Invalid face: ").append(k2).toString());
                } else
                if(j2 == B.j[15] || j2 == B.j[13])
                {
                    k2 = -1;
                    flag = true;
                } else
                if(j2 >= B.j[16] && j2 <= B.j[19])
                    k2 = -1;
            } else
            {
                if((k2 == 0 || k2 == 2) && j2 >= 10)
                {
                    int j4 = k3;
                    k3 = l3;
                    l3 = j4;
                }
                k2 = -1;
            }
            int k4 = k2;
            if(i1 == 9374 || i1 == 4513 || i1 >= 4518 && i1 <= 4520 || i1 >= 5122 && i1 <= 5125 || i1 == 5112 || i1 >= 1140 && i1 <= 1205 || i1 >= 4735 && i1 <= 4740 || i1 <= 1299 && i1 >= 1298 || i1 == 1174 || i1 >= 446 && i1 <= 447 || i1 >= 1240 && i1 <= 1265 || i1 >= 950 && i1 <= 953 || i1 >= 4342 && i1 <= 4345 || i1 >= 3948 && i1 <= 3950 || i1 == 1032 || i1 == 4436 || i1 == 4446 || i1 == 4447)
            {
                flag = true;
                i3 = 1;
            } else
            if(i1 == 5113 || i1 >= 471 && i1 <= 474 || i1 == 1161 || i1 == 312 || i1 == 1341 || i1 >= 312 && i1 <= 313 || i1 == 1341 || i1 == 336 || i1 == 11603 || i1 >= 11930 && i1 <= 11945 || i1 == 11629 || i1 == 9623 || i1 == 1392 || i1 == 1394 || i1 >= 980 && i1 <= 982 || i1 == 1297 || i1 == 9375)
                i3 = 1;
            else
            if(j1 <= 2425 && k1 <= 3091 && j1 >= 2411 && k1 >= 3085 || j1 >= 2413 && k1 >= 3074 && j1 <= 2417 && k1 <= 3084 || j1 >= 2374 && k1 >= 3116 && j1 <= 2387 && k1 <= 3122 || j1 <= 2386 && k1 <= 3133 && j1 >= 2382 && k1 >= 3122)
                i3 = 1;
            else
            if(i1 >= 6716 && i1 <= 6750)
                k4 = -1;
            if(!flag)
                if(k3 >= 2 || l3 >= 2)
                {
                    for(int l4 = 0; l4 < k3; l4++)
                    {
                        for(int i5 = 0; i5 < l3; i5++)
                            az[j3++] = new Z(j1 + l4, k1 + i5, i2, i3);

                    }

                } else
                {
                    if(k4 != -1)
                        if(k4 == B.j[20])
                        {
                            ai[j3] = 0;
                            az[j3++] = new Z(j1, k1 - 1, i2, i3);
                            byte1 = 0;
                        } else
                        if(k4 == B.j[21])
                        {
                            ai[j3] = 4;
                            az[j3++] = new Z(j1 + 1, k1, i2, i3);
                            byte1 = 12;
                        } else
                        if(k4 == B.j[22])
                        {
                            ai[j3] = 8;
                            az[j3++] = new Z(j1, k1 + 1, i2, i3);
                            byte1 = 8;
                        } else
                        if(k4 == B.j[23])
                        {
                            ai[j3] = 12;
                            az[j3++] = new Z(j1 - 1, k1, i2, i3);
                            byte1 = 4;
                        } else
                        if(k4 != 2 && k4 != 6 && k4 != 14)
                            if(k4 != 10);
                    az[j3++] = new Z(j1, k1, i2, i3);
                }
            byte byte2 = -1;
            int j5 = contains(j1, k1);
            for(int k5 = 0; k5 < j3; k5++)
            {
                if(byte1 != -1 && k5 == 1)
                    k4 = byte1;
                if(VirtualWorld.I[j5].containsKey(az[k5]))
                {
                    C c1 = (C)VirtualWorld.I[j5].get(az[k5]);
                    ArrayList arraylist1 = new ArrayList();
                    int l5;
                    for(Iterator iterator = c1.I.iterator(); iterator.hasNext(); arraylist1.add(Integer.valueOf(l5)))
                        l5 = ((Integer)iterator.next()).intValue();

                    if(arraylist1.contains(Integer.valueOf(k4)))
                        continue;
                    ArrayList arraylist2 = new ArrayList();
                    if(!arraylist1.contains(Integer.valueOf(-1)))
                    {
                        Iterator iterator1 = arraylist1.iterator();
                        do
                        {
                            if(!iterator1.hasNext())
                                break;
                            int i6 = ((Integer)iterator1.next()).intValue();
                            if(!arraylist2.contains(Integer.valueOf(i6)))
                                arraylist2.add(Integer.valueOf(i6));
                        } while(true);
                        arraylist2.add(Integer.valueOf(k4));
                    } else
                    {
                        arraylist2.add(Integer.valueOf(-1));
                    }
                    C c3 = new C(arraylist2, l2);
                    VirtualWorld.I[j5].remove(az[k5]);
                    VirtualWorld.I[j5].put(az[k5], c3);
                    i++;
                } else
                {
                    ArrayList arraylist = new ArrayList();
                    arraylist.add(Integer.valueOf(k4));
                    C c2 = new C(arraylist, l2);
                    VirtualWorld.I[j5].put(az[k5], c2);
                    i++;
                }
            }

            az = null;
            ai = null;
        }

        l1 = System.currentTimeMillis() - l;
        System.out.println((new StringBuilder()).append("	-Fully Processed in (").append(l1).append(" ms)... ").toString());
        System.out.println((new StringBuilder()).append("	-Loaded ").append(i).append(" world objects").toString());
        abyte0 = null;
        randomaccessfile = null;
    }

    //(futureX, futureY)
    public static final int contains(int futureX, int futureY)
    {
        String s = (new StringBuilder()).append(futureY / 100).append("").append(futureX / 100).toString();
        int k = Integer.parseInt(s);
        if(k > hasNext)
            hasNext = k;
        return k;
    }

    public static final void containsKey()
    {
        equals = new byte[B.j[24]];
        get = new byte[B.j[24]];
        String s = "";
        String s1 = "";
        String s3 = "";
        String s5 = "";
        String as[] = new String[10];
        boolean flag = false;
        BufferedReader bufferedreader = null;
        try
        {
            bufferedreader = new BufferedReader(new FileReader("./Data/objectSize.cfg"));
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
            return;
        }
        try
        {
            s = bufferedreader.readLine();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            return;
        }
        while(!flag && s != null) 
        {
            s = s.trim();
            int i = s.indexOf("=");
            if(i > -1)
            {
                String s2 = s.substring(0, i);
                s2 = s2.trim();
                String s4 = s.substring(i + 1);
                s4 = s4.trim();
                String s6 = s4.replaceAll("		", "	");
                s6 = s6.replaceAll("		", "	");
                s6 = s6.replaceAll("		", "	");
                s6 = s6.replaceAll("		", "	");
                s6 = s6.replaceAll("		", "	");
                String as1[] = s6.split("	");
                if(s2.startsWith("object"))
                {
                    int j = Integer.parseInt(as1[0]);
                    String s7 = as1[2];
                    String as2[] = s7.split("x");
                    equals[j] = (byte)Integer.parseInt(as2[0]);
                    get[j] = (byte)Integer.parseInt(as2[1]);
                }
            } else
            if(s.equals("[END]"))
            {
                try
                {
                    bufferedreader.close();
                }
                catch(IOException ioexception2) { }
                return;
            }
            try
            {
                s = bufferedreader.readLine();
            }
            catch(IOException ioexception3)
            {
                flag = true;
            }
        }
        try
        {
            bufferedreader.close();
        }
        catch(IOException ioexception1) { }
    }

    public static final int currentTimeMillis(int i, int j, int k)
    {
        Z z = new Z(i, j, k, 0);
        int l = contains(i, j);
        if(VirtualWorld.I[l] == null)
            return -1;
        if(VirtualWorld.I[l].containsKey(z))
        {
            C c1 = (C)VirtualWorld.I[l].get(z);
            return c1.Z;
        } else
        {
            return -1;
        }
    }
    
    //(int height, int currentX, int currentY, int futureX, int futureY, int a)
    public static final boolean I(int height, int currentX, int currentY, int futureX, int futureY, int j1)
    {
        if(currentY == futureX && currentY == futureY)
            return true;
        Z z = new Z(futureX, futureY, height, 0);
        Z z1 = new Z(futureX, futureY, height, 1);
        int k1 = contains(futureX, futureY);
        int l1 = equals(currentX, currentY, futureX, futureY);
        C c1 = null;
        C c2 = null;
        if(k1 > 14000)
        {
            System.out.println((new StringBuilder()).append("error in WorldMap X: ").append(currentY).append(" Y: ").append(currentY).toString());
            return false;
        }
        if(VirtualWorld.I[k1] == null)
            return true;
        if(VirtualWorld.I[k1].containsKey(z))
            c1 = (C)VirtualWorld.I[k1].get(z);
        else
        if(VirtualWorld.I[k1].containsKey(z1))
            c2 = (C)VirtualWorld.I[k1].get(z1);
        if(c1 == null && c2 == null)
            return true;
        ArrayList arraylist = null;
        if(c1 != null)
            arraylist = c1.I;
        else
        if(c2 != null)
            arraylist = c2.I;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            int i2 = ((Integer)iterator.next()).intValue();
            if(l1 == B.j[25])
                return I(height, currentX, currentY, futureX - 1, futureY, j1) && I(height, currentX, currentY, futureX, futureY - 1, j1);
            if(l1 == B.j[26])
                return I(height, currentX, currentY, futureX + 1, futureY, j1) && I(height, currentX, currentY, futureX, futureY - 1, j1);
            if(l1 == B.j[27])
                return I(height, currentX, currentY, futureX + 1, futureY, j1) && I(height, currentX, currentY, futureX, futureY + 1, j1);
            if(l1 == B.j[28])
                return I(height, currentX, currentY, futureX - 1, futureY, j1) && I(height, currentX, currentY, futureX, futureY + 1, j1);
            if(i2 != -1)
            {
                if(j1 == 1 && VirtualWorld.I[k1].containsKey(z1))
                    return true;
                if(j1 == 0 && VirtualWorld.I[k1].containsKey(z1) && i2 == l1)
                    return false;
                if(VirtualWorld.I[k1].containsKey(z) && i2 == l1)
                    return false;
            } else
            {
                if(j1 == 1 && VirtualWorld.I[k1].containsKey(z1))
                    return true;
                if(j1 == 0 && VirtualWorld.I[k1].containsKey(z1))
                    return false;
                if(VirtualWorld.I[k1].containsKey(z))
                    return false;
            }
        }

        return true;
    }

    public static final int equals(int currentX, int currentY, int futureX, int futureY)
    {
        int valX = futureX - currentX;
        int valY = futureY - currentY;
        if(valX < 0)
        {
            if(valY < 0)
            {
                if(valX < valY)
                    return 11;
                return valX <= valY ? 10 : 9;
            }
            if(valY > 0)
            {
                if(-valX < valY)
                    return 15;
                return -valX <= valY ? 14 : 13;
            } else
            {
                return 12;
            }
        }
        if(valX > 0)
        {
            if(valY < 0)
            {
                if(valX < -valY)
                    return 7;
                return valX <= -valY ? 6 : 5;
            }
            if(valY > 0)
            {
                if(valX < valY)
                    return 1;
                return valX <= valY ? 2 : 3;
            } else
            {
                return 4;
            }
        }
        if(valY < 0)
            return 8;
        return valY <= 0 ? -1 : 0;
    }

    public static int c;
    public static int KKLI;
    public static int add;
    public static int append;
    public static int close;
    public static int contains;
    public static int containsKey;
    public static int currentTimeMillis = 0;
    public static byte equals[];
    public static byte get[];
    public static int hasNext = 0;

}
