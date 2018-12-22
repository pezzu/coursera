import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class DequeTest {

    @Test
    public void construction() {
        Deque<String> d = new Deque<>();

        assertEquals(d.size(), 0);
        assertTrue(d.isEmpty());
    }

    @Test
    public void isEmpty() {
        Deque<String> d = new Deque<>();
        assertTrue(d.isEmpty());

        d.addFirst("test");
        assertFalse(d.isEmpty());

        d.removeFirst();
        assertTrue(d.isEmpty());
    }

    @Test
    public void size() {
        Deque<Integer> d = new Deque<>();
        assertEquals(d.size(), 0);

        d.addFirst(1);
        d.addLast(2);
        assertEquals(d.size(), 2);

        d.removeFirst();
        assertEquals(d.size(), 1);

        d.removeLast();
        assertEquals(d.size(), 0);
    }

    @Test
    public void addRemoveFirst() {
        Deque<Integer> d = new Deque<>();

        d.addFirst(1);

        int result = d.removeFirst();
        assertEquals(result, 1);
    }

    @Test
    public void addRemoveLast() {
        Deque<Integer> d = new Deque<>();

        d.addLast(0);

        int result = d.removeLast();
        assertEquals(result, 0);
    }

    @Test
    public void iterator() {
        Deque<Integer> d = new Deque<>();

        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        d.addLast(4);
        d.addLast(5);

        Iterator<Integer> i = d.iterator();

        assertEquals((int)i.next(), 1);
        assertTrue(i.hasNext());
        assertEquals((int)i.next(), 2);
        assertTrue(i.hasNext());
        assertEquals((int)i.next(), 3);
        assertTrue(i.hasNext());
        assertEquals((int)i.next(), 4);
        assertTrue(i.hasNext());
        assertEquals((int)i.next(), 5);
        assertFalse(i.hasNext());
    }
}
