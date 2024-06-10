package DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

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
        validateNotNull(item);
        Node<Item> currentFirst = this.first;
        Node<Item> newFirst = new Node<>();
        newFirst.item = item;
        newFirst.next = currentFirst;
        this.first = newFirst;
        if (size == 0) this.last = newFirst;
        else currentFirst.previous = newFirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateNotNull(item);
        Node<Item> currentLast = this.last;
        Node<Item> newLast = new Node<>();
        newLast.item = item;
        newLast.previous = currentLast;
        this.last = newLast;
        if (size == 0) this.first = newLast;
        else currentLast.next = newLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateIsNotEmpty();
        Node<Item> currentFirst = this.first;
        Item item = currentFirst.item;
        size--;
        if (isEmpty()) {
            this.first = null;
            this.last = null;
        }
        else {
            this.first = currentFirst.next;
            currentFirst.next = null;
            this.first.previous = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateIsNotEmpty();
        Node<Item> currentLast = this.last;
        Item item = currentLast.item;
        size--;
        if (isEmpty()) {
            this.first = null;
            this.last = null;
        }
        else {
            this.last = currentLast.previous;
            currentLast.previous = null;
            this.last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(this.first);
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            this.current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            this.current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void validateNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validateIsNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        // addFirst test
        deque.addFirst(1);
        int firstElement = deque.removeFirst();
        StdOut.println("First element: " + firstElement + " equals 1? " + (firstElement == 1));
        StdOut.println("Deque should be empty: " + deque.isEmpty());
        // multiple addFirst test
        deque.addFirst(1);
        deque.addFirst(2);
        StdOut.println("Size of deque: " + deque.size() + " equals 2? " + (deque.size() == 2));
        firstElement = deque.removeFirst();
        StdOut.println("First element: " + firstElement + " equals 2? " + (firstElement == 2));
        // addLast test
        deque.addLast(3);
        int lastElement = deque.removeLast();
        StdOut.println("Last element: " + lastElement + " equals 3? " + (lastElement == 3));
        // multiple addLast test
        deque.addLast(3);
        deque.addLast(4);
        StdOut.println("Size of deque: " + deque.size() + " equals 3? " + (deque.size() == 3));
        lastElement = deque.removeLast();
        StdOut.println("Last element: " + lastElement + " equals 4? " + (lastElement == 4));
        // iterator test
        for (Integer integer : deque) {
            StdOut.println("Next element of deque " + integer);
        }
    }
}

