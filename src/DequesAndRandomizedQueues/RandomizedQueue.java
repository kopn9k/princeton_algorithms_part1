package DequesAndRandomizedQueues;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_ARRAY_SIZE = 4;
    private int size = 0;
    private Item[] array;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.array = (Item[]) new Object[INITIAL_ARRAY_SIZE];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size >= array.length) resize(array.length * 2);
        array[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        if (size == array.length / 4) resize(array.length / 2);
        int randomIndex = StdRandom.uniformInt(0, size);
        Item item = array[randomIndex];
        array[randomIndex] = array[--size];
        array[size] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        return array[StdRandom.uniformInt(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] iteratorArray;
        private int iteratorIndex = 0;

        public RandomizedQueueIterator() {
            iteratorArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorArray[i] = array[i];
            }
            StdRandom.shuffle(iteratorArray);
        }

        public boolean hasNext() {
            return iteratorIndex < iteratorArray.length;
        }

        public Item next() {
            if (iteratorIndex >= iteratorArray.length) throw new NoSuchElementException();
            return iteratorArray[iteratorIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        // single item test
        queue.enqueue(1);
        int randomElement = queue.dequeue();
        StdOut.println("Dequeued element: " + randomElement + " equals 1? " + (randomElement == 1));
        StdOut.println("RandomizedQueue should be empty: " + queue.isEmpty());
        // multiple enqueue with sample test
        queue.enqueue(1);
        queue.enqueue(2);
        StdOut.println("Size of deque: " + queue.size() + " equals 2? " + (queue.size() == 2));
        randomElement = queue.sample();
        StdOut.println("Sample element: " + randomElement + " equals 2 or 1? " +
                               (randomElement == 2 || randomElement == 1));
        // multiple enqueue with deque test
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        StdOut.println("Size of queue: " + queue.size() + " equals 5? " + (queue.size() == 5));
        int prevRandomElement = queue.dequeue();
        StdOut.println("Size of queue: " + queue.size() + " equals 4? " + (queue.size() == 4));
        randomElement = queue.dequeue();
        StdOut.println("Dequeue element: " + randomElement + " equals 5, 4, 3, 2 or 1? and not "
                               + prevRandomElement + " " + (randomElement != prevRandomElement));
        StdOut.println("_______________________________________________________________");

        int n = 5;
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            randomizedQueue.enqueue(i);
        for (int a : randomizedQueue) {
            for (int b : randomizedQueue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
