import org.junit.jupiter.api.Test;
import ChapterThree.*;

public class ChapterThree
{
    @Test
    public void testCombine()
    {
        MyArrayList<Integer> list1 = new MyArrayList();
        MyArrayList<Integer> list2 = new MyArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        list1.add(7);
        list2.add(2);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        MyArrayList<Integer> list = new MyArrayList<Integer>();

        for (int i =0, j =0; i < list1.size() && j < list2.size(); )
        {
            if (list1.get(i) == list2.get(j))
            {
                list.add(list1.get(i));
                i++;
                j++;
            }
            else if (list1.get(i) > list2.get(j))
            {
                j++;
            }
            else
            {
                i++;
            }
        }
        for (Integer i : list)
        {
            System.out.println(i);
        }
    }

    @Test
    public void testUnion()
    {
        MyArrayList<Integer> list1 = new MyArrayList();
        MyArrayList<Integer> list2 = new MyArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        list1.add(7);
        list2.add(2);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        MyArrayList<Integer> list = new MyArrayList<Integer>();
        int i =0, j =0;
        for (; i < list1.size() && j < list2.size(); )
        {
            if (list1.get(i) == list2.get(j))
            {
                list.add(list1.get(i++));
                j++;
            }
            else if (list1.get(i) > list2.get(j))
            {
                list.add(list2.get(j++));
            }
            else
            {
                list.add(list1.get(i++));
            }
        }

        if (i < list1.size())
        {
            for (; i < list1.size(); i++)
                list.add(list1.get(i));
        }
        if (j < list2.size())
        {
            for (; j < list2.size(); j++)
                list.add(list2.get(j));
        }

        for (Integer tmp : list)
        {
            System.out.println(tmp);
        }

    }
}
