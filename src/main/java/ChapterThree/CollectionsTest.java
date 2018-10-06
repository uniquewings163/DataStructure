package ChapterThree;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionsTest
{
    @Test
    public void testIterator()
    {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++)
        {
            list.add(i);
        }
        for (Integer tmp : list)
        {
            System.out.println(tmp);
        }
    }

    private void addEventsVar(List<Integer> list)
    {
        Integer tmp;
        ListIterator<Integer> iter = list.listIterator();
        while (iter.hasNext())
        {
            if ((tmp = iter.next()) % 2 == 0)
            {
                iter.set(tmp + 10);
            }
        }
    }

    private void removeEventsVar(List<Integer> list)
    {
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext())
        {
            if (iter.next() % 2 == 0)
            {
                iter.remove();
            }
        }
    }

    @Test
    public void testRemove()
    {
        List<Integer> array = new ArrayList<Integer>();
        List<Integer> list = new LinkedList<Integer>();

        for (int i = 1; i <= 10; i++)
        {
            array.add(i);
            list.add(i);
        }

        System.out.println(array);
        System.out.println(list);

//        removeEventsVar(array);
        addEventsVar(array);
//        removeEventsVar(list);
        addEventsVar(list);

        System.out.println(array);
        System.out.println(list);
    }
}
