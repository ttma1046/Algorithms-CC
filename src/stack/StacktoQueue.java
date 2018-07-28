package stack;

import java.util.Stack;

public class StacktoQueue {
    public static class MyQueue<T> {
        private Stack<T> stackNewestOnTop = new Stack<T>();
        private Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) {
            stackNewestOnTop.push(value);  // Add item.
        }

        public T peek() {  // Get "oldest" item
            shiftStacks();

            return stackOldestOnTop.peek();
        }

        public T dequeue() { // Get "oldest" item and remove it
            shiftStacks();

            return stackOldestOnTop.pop();
        }

        private void shiftStacks() {
            // move elements from stackNewest to stackOldest
            if (stackOldestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<Integer>();
        myQueue.enqueue(1);

        new MyQueueV2<Integer>().enqueue(2);
    }

    public static class MyQueueV2<T> {
        private Stack<T> stackNewestOnTop = new Stack<T>();
        private Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) {
            while (!stackNewestOnTop.isEmpty()) {
                stackOldestOnTop.push(stackNewestOnTop.pop());
            }

            stackNewestOnTop.push(value);  // Add item.

            while (!stackOldestOnTop.isEmpty()) {
                stackNewestOnTop.push(stackOldestOnTop.pop());
            }
        }

        public T peek() {  // Get "oldest" item
            return stackNewestOnTop.peek();
            // move elements back
        }

        public T dequeue() { // Get "oldest" item and remove it
            return stackNewestOnTop.pop();
            // move elements back.
        }
    }
}

