package ChapterFive;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainHashTable<T>
{
    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<T>[] theLists;

    private int currentSize;

    public SeparateChainHashTable()
    {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainHashTable(int size)
    {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
        {
            theLists[i] = new LinkedList<>();
        }
    }

    public void makeEmpty()
    {
        for (int i = 0; i < theLists.length; i++)
        {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    public boolean contains(T x)
    {
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public void insert(T x)
    {
        List<T> whichList = theLists[myhash(x)];
        if (!whichList.contains(x))
        {
            whichList.add(x);

            if (++currentSize > theLists.length)
            {
                rehash();
            }
        }
    }

    public void rehash()
    {
        List<T>[] oldList = theLists;
        theLists = new List[nextPrime(oldList.length * 2)];
        for (int i = 0; i < theLists.length; i++)
        {
            theLists[i] = new LinkedList<>();
        }

        currentSize = 0;
        for (int i = 0; i < oldList.length; i++)
        {
            for (T e : oldList[i])
            {
                insert(e);
            }
        }
    }

    public void remove(T x)
    {
        List whichList = theLists[myhash(x)];
        if (whichList.contains(x))
        {
            whichList.remove(x);
            currentSize--;
        }
    }

    private int myhash(T x)
    {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0)
        {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    private int nextPrime(int key)
    {
        for (int i = key; ; i++)
        {
            if (isPrime(i))
            {
                return i;
            }
        }
    }

    private boolean isPrime(int key)
    {
        if (key < 2)
        {
            return false;
        }

        double len = Math.sqrt(key);
        for (int i = 2; i <= len; i++)
        {
            if (key % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}
