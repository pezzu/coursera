import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    static final int INITIAL_CAPACITY = 20;

    private Item[] elements = (Item[]) new Object[INITIAL_CAPACITY];
    int tail = 0;

    private class RandomIterator implements Iterator<Item> {

        private Item[] randomized;
        private int current = 0;

        private RandomIterator() {
            randomized = (Item[]) new Object[tail];
            for(int i = 0; i < tail; i++) {
                randomized[i] = elements[i];
            }
            StdRandom.shuffle(randomized);
        }

        public boolean hasNext() {
            return current < randomized.length;
        }

        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return randomized[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private void resize(int newCapacity) {
        Item[] newElements = (Item[]) new Object[newCapacity];
        for(int i = 0; i < tail; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return tail == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return tail;
    }

    // add the item
    public void enqueue(Item value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        if(tail == elements.length) {
            resize(elements.length * 2);
        }
        elements[tail++] = value;
    }

    // remove and return a random item
    public Item dequeue() {
        if(tail == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(tail);
        Item result = elements[index];
        elements[index] = elements[--tail];
        elements[tail+1] = null;
        if(tail <= elements.length / 4) {
            resize( elements.length / 2);
        }
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(tail == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(tail);
        return elements[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
}