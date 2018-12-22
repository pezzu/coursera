import org.testng.annotations.Test;

import java.util.ArrayList;

import java.util.List;

import static org.testng.Assert.*;

public class RandomizedQueueTest {

    @Test
    public void construction() {
        RandomizedQueue<String> r = new RandomizedQueue<>();

        assertEquals(r.size(), 0);
        assertTrue(r.isEmpty());
    }

    @Test
    public void isEmpty() {
        RandomizedQueue<String> r = new RandomizedQueue<>();
        assertTrue(r.isEmpty());

        r.enqueue("test");
        assertFalse(r.isEmpty());

        r.dequeue();
        assertTrue(r.isEmpty());
    }

    @Test
    public void size() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        assertEquals(r.size(), 0);

        r.enqueue(1);
        r.enqueue(2);
        assertEquals(r.size(), 2);

        r.dequeue();
        assertEquals(r.size(), 1);

        r.dequeue();
        assertEquals(r.size(), 0);
    }

    @Test
    public void dequeueAtMostOnce() {
        RandomizedQueue<String> r = new RandomizedQueue<>();

        r.enqueue("test");
        assertEquals(r.dequeue(), "test");

        r.enqueue("A");
        r.enqueue("B");
        r.enqueue("C");
        r.enqueue("D");
        r.enqueue("E");

        List<String> result = new ArrayList<>();
        result.add(r.dequeue());
        result.add(r.dequeue());
        result.add(r.dequeue());
        result.add(r.dequeue());
        result.add(r.dequeue());

        assertEquals(result.stream().filter(s -> s.equals("A")).count(), 1);
        assertEquals(result.stream().filter(s -> s.equals("B")).count(), 1);
        assertEquals(result.stream().filter(s -> s.equals("C")).count(), 1);
        assertEquals(result.stream().filter(s -> s.equals("D")).count(), 1);
        assertEquals(result.stream().filter(s -> s.equals("E")).count(), 1);
    }

    @Test
    public void dequeueRandom() {
        RandomizedQueue<String> r = new RandomizedQueue<>();

        r.enqueue("A");
        r.enqueue("B");
        r.enqueue("C");
        r.enqueue("D");
        r.enqueue("E");
        r.enqueue("F");

        String res1 = r.dequeue();
        String res2 = r.dequeue();
        String res3 = r.dequeue();

        assertTrue(!res1.equals("A") || !res2.equals("B") || !res3.equals("C"));
    }

    @Test
    public void sample() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();

        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.enqueue(6);

        int sample = r.sample();
        assertTrue(sample >= 1 && sample <= 6);
    }

    @Test
    public void sampleRandom() {
        RandomizedQueue<String> r = new RandomizedQueue<>();

        r.enqueue("A");
        r.enqueue("B");
        r.enqueue("C");
        r.enqueue("D");
        r.enqueue("E");
        r.enqueue("F");

        String sample1 = r.sample();
        String sample2 = r.sample();
        String sample3 = r.sample();
        assertTrue(!sample1.equals("A") || !sample2.equals("A") || !sample3.equals("A"));
    }

    @Test
    public void iteratorAtMostOnce() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.enqueue(6);

        List<Integer> result = new ArrayList<>();
        for(int i : r) {
            result.add(i);
        }
        assertEquals(result.stream().filter(i -> i==1).count(), 1);
        assertEquals(result.stream().filter(i -> i==2).count(), 1);
        assertEquals(result.stream().filter(i -> i==3).count(), 1);
        assertEquals(result.stream().filter(i -> i==4).count(), 1);
        assertEquals(result.stream().filter(i -> i==5).count(), 1);
        assertEquals(result.stream().filter(i -> i==6).count(), 1);
    }

    @Test
    public void iteratorRandom() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.enqueue(6);

        List<Integer> result = new ArrayList<>();
        for(int i : r) {
            result.add(i);
        }

        assertFalse(result.get(0) == 1 && result.get(1) == 2 && result.get(2) == 3);
    }
}
