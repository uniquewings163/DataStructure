package ChapterThree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>
{
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList()
    {
        doClear();
    }

    public void doClear()
    {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }

    public void clear()
    {
        doClear();
    }

    public int size()
    {
        return theSize;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void trimToSize()
    {
        ensureCapacity(size());
    }

    public T get(int index)
    {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return theItems[index];
    }

    public T set(int index, T item)
    {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        T old = theItems[index];
        theItems[index] = item;
        return old;
    }

    public void ensureCapacity(int capacity)
    {
        if (capacity < theSize)
            return;
        T[] old = theItems;
        //泛型数组的创建是非法的，只能通过类型转换
        theItems = (T[]) new Object[capacity];
        for (int i = 0; i < size(); i++)
        {
            theItems[i] = old[i];
        }
    }

    public boolean add(T item)
    {
        add(size(), item);
        return true;
    }

    public void add(int index, T item)
    {
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > index; i--)
            theItems[i] = theItems[i-1];
        theItems[index] = item;
        theSize++;
    }

    public T remove(int index)
    {
        T removeItem = theItems[index];
        for (int i = index; i < theSize - 1; i++)
            theItems[i] = theItems[i+1];
        theSize--;
        return removeItem;
    }

    public Iterator<T> iterator()
    {
        return new ArraListIterator();
    }

    //内部类，如果声明中包含 static 则为嵌套类，二者不同
    //当声明一个内部类时，编译器添加对外部类对象的一个隐式引用
    private class ArraListIterator implements Iterator<T>
    {
        private int current = 0;

        public boolean hasNext()
        {
            return current < size();
        }

        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        public void remove()
        {
            MyArrayList.this.remove(--current);
        }
    }

}
