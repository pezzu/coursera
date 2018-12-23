import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item value;
        Node next;
        Node previous;

        Node(Item value, Node next, Node previous) {
            if(value == null) {
                throw new java.lang.IllegalArgumentException();
            }

            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove");
        }
    }

    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
       Node node = new Node(item, first, null);

       if(first != null) {
           first.previous = node;
       }
       else {
           last = node;
       }
       first = node;

       size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        Node node = new Node(item, null, last);

        if(last != null) {
            last.next = node;
        }
        else {
            first = node;
        }
        last = node;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(first == null) {
            throw new java.util.NoSuchElementException();
        }
        Item value = first.value;

        first = first.next;
        if(first == null) {
            last = null;
        }
        else {
            first.previous = null;
        }

        size--;

        return value;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if(last == null) {
            throw new java.util.NoSuchElementException();
        }
        Item value = last.value;

        last = last.previous;
        if(last == null) {
            first = null;
        }
        else {
            last.next = null;
        }

        size--;

        return value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
}