package ChapterThree;

public class StackOnArrayList<T>
{
    private MyArrayList<T> arrayList;

    public StackOnArrayList()
    {
        arrayList = new MyArrayList<T>();
    }

    public void push(T data)
    {
        arrayList.add(arrayList.size(), data);
    }

    public T pop()
    {
        if (arrayList.size() == 0)
            throw new IllegalStateException();
        return arrayList.remove(arrayList.size() - 1);
    }

    public T top()
    {
        if (arrayList.size() == 0)
            throw new IllegalStateException();
        return arrayList.get(arrayList.size() - 1);
    }
}
