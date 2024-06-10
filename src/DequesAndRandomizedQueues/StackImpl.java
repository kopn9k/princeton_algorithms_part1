package DequesAndRandomizedQueues;

public class StackImpl {

    private class Node {
        String value;
        Node next;
    }

    private Node first = null;

    public void push(String string) {
        Node oldFirst = first;
        first = new Node();
        first.value = string;
        first.next = oldFirst;
    }

    public String pop() {
        String value = first.value;
        first = first.next;
        return value;
    }
}
