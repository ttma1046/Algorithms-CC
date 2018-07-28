package stack;

/**
 * Implement a stack
 */
public class Stack {
    private static class Node {
        private int val;
        private Node next;
        private Node(int x) {
            this.val = x;
        }
    }

    private Node top; // Add and remove here

    public boolean isEmpty() {
        return top == null;
    }

    public int peek() {
        // ignore corner case (null pointer check)..
        if (top != null) {
            return top.val;
        }
        return 0;
    }

    public void push(int value) {
        Node newNode = new Node(value);

        newNode.next = top;

        top = newNode;
    }

    public int pop() {
        int value = top.val;

        top = top.next;

        return value;
    }
}
