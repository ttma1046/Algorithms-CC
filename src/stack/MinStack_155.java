package stack;
import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
*/

public class MinStack_155 {
    Stack<int[]> myStack = new Stack<int[]>();

    public void push(int x) {
        if (myStack.isEmpty())
            myStack.push(new int[] { x, x });
        else
            myStack.push(new int[] { x, Math.min(x, myStack.peek()[1]) });
    }

    public void pop() {
        myStack.pop();
    }

    public int top() {
        return myStack.peek()[0];
    }

    public int getMin() {
        return myStack.peek()[1];
    }
}

class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public MinStack() {}

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<int[]> minStack = new Stack<>();

    public MinStack() {
    }

    public void push(int x) {
        // We always put the number onto the main stack.
        stack.push(x);

        // If the min stack is empty, or this number is smaller than
        // the top of the min stack, put it on with a count of 1.
        if (minStack.isEmpty() || x < minStack.peek()[0]) 
            minStack.push(new int[] { x, 1 });

        // Else if this number is equal to what's currently at the top
        // of the min stack, then increment the count at the top by 1.
        else if (x == minStack.peek()[0]) 
            minStack.peek()[1]++;
    }

    public void pop() {

        // If the top of min stack is the same as the top of stack
        // then we need to decrement the count at the top by 1.
        if (stack.peek().equals(minStack.peek()[0])) {
            minStack.peek()[1]--;
        }

        // If the count at the top of min stack is now 0, then remove
        // that value as we're done with it.
        if (minStack.peek()[1] == 0) {
            minStack.pop();
        }

        // And like before, pop the top of the main stack.
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }
}