package ChapterFour;

public class BinaryNode<T>
{
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T element)
    {
        this(element, null, null);
    }

    public BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt)
    {
        this.element = element;
        left = lt;
        right = rt;
    }
}
