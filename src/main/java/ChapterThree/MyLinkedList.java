package ChapterThree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T>
{
    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public Iterator<T> iterator()
    {
        return new LinkedListIterator();
    }

    private static class Node<T>
    {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T d, Node<T> p, Node<T> n)
        {
            data = d;
            prev = p;
            next = n;
        }
    }

    public MyLinkedList()
    {
        doClear();
    }

    public void clear()
    {
        doClear();
    }

    private void doClear()
    {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, null, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size()
    {
        return theSize;
    }

    public boolean isEmpty()
    {
        return 0 == size();
    }

    public boolean add(T item)
    {
        add(size(), item);
        return true;
    }

    public void add(int index, T item)
    {
        addBefore(getNode(index, 0, size()), item);
    }

    private void addBefore(Node<T> p, T data)
    {
        Node<T> newNode = new Node<T>(data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public T get(int index)
    {
        return getNode(index).data;
    }

    public T set(int index, T item)
    {
        Node<T> p = getNode(index);
        T old = p.data;
        p.data = item;
        return old;
    }

    public T remove(int index)
    {
        return remove(getNode(index));
    }

    public void exchange(int left, int right)
    {
        Node<T> l = getNode(left);
        Node<T> r = getNode(right);
        Node<T> oldP = l.prev;
        Node<T> oldN = l.next;
        l.prev = r.prev;
        l.next = r.next;
        r.next = oldN;
        r.prev = oldP;
    }

    public boolean contains(T key)
    {
        for(T tmp : this)
        {
            if (tmp.equals(key))
            {
                return true;
            }
        }
        return false;
    }

    private T remove(Node<T> node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
        theSize--;
        modCount--;
        return node.data;
    }

    private Node<T> getNode(int index)
    {
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int lower, int upper)
    {
        Node<T> p;

        if (index < lower || index > upper)
            throw new IndexOutOfBoundsException();

        if (index < size()/2)
        {
            p = beginMarker.next;
            for (int i = 0; i < index; i++)
                p = p.next;
        }
        else
        {
            p = endMarker.prev;
            for (int i = theSize; i > index; i--)
                p = p.prev;
        }
        return p;
    }

    private class LinkedListIterator implements Iterator<T>
    {
        private Node<T> current = beginMarker.next;
        private int expectedModeCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext()
        {
            return current != endMarker;
        }

        public T next()
        {
            if (modCount != expectedModeCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.next.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove()
        {
            if (modCount != expectedModeCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            expectedModeCount++;
            okToRemove = false;
        }
    }
}
