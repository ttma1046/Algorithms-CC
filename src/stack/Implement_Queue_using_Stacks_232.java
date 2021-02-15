package stack;
import java.util.Stack;


class Implement_Queue_using_Stacks_232 {

    Stack<Integer> stackOne = new Stack<Integer>();
    Stack<Integer> stackTwo = new Stack<Integer>();

    /** Initialize your data structure here. */
    public Implement_Queue_using_Stacks_232() {}
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stackOne.push(x);

        // stackOne.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {

        if(stackTwo.isEmpty()) 
            while(!stackOne.isEmpty()) 
                stackTwo.push(stackOne.pop())
        
        return stackTwo.pop();
    }
    
    /** Get the front element. */
    public int peek() {

        if(stackTwo.isEmpty()) 
            while(!stackOne.isEmpty()) 
                stackTwo.push(stackOne.pop())
        
        
        return stackTwo.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackTwo.isEmpty() && stackOne.isEmpty();
    }
}