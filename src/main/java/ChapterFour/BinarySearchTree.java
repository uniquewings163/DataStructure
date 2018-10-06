package ChapterFour;

import java.nio.BufferUnderflowException;
import java.util.function.Consumer;

public class BinarySearchTree<T extends  Comparable<? super T>>
{
    private BinaryNode<T> root;

    public BinarySearchTree()
    {
        root = null;
    }

    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public boolean contains(T x)
    {
        return contains(x, root);
    }

    public T findMin()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public T findMax()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void walkTree(BinaryNode<T> t, Consumer<T> f)
    {
        if (t != null)
        {
            walkTree(t.left, f);
            f.accept(t.element);
            walkTree(t.right, f);
        }
    }

    //后序遍历
    public int height(BinaryNode<T> t)
    {
        if (t == null)
            return -1;
        return 1 + Math.max(height(t.left), height(t.right));
    }

    private static class BinaryNode<T>
    {
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
        {
            element = theElement;
            left = lt;
            right = rt;
        }

        public BinaryNode(T theElement)
        {
            this(theElement, null, null);
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t)
    {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t)
    {
        if (t == null)
            return null;
        while (t.right != null)
            t = t.right;
        return t;
    }

    //递归插入，比当前节点小递归插入到左节点，比当前节点大递归插入到右节点，递归结束条件为当前节点为空
    private BinaryNode<T> insert(T x, BinaryNode<T> t)
    {
        if (t == null)
            return new BinaryNode<T>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
        {

        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t)
    {
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null)
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else
        {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private boolean contains(T x, BinaryNode<T> t)
    {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
        {
            return contains(x, t.left);
        }
        else if (compareResult > 0)
        {
            return contains(x, t.right);
        }
        else
        {
            return true;
        }
    }
}
