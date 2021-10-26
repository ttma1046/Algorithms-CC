package queue;

/*
Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.


Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False


Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.


Follow-up: Can you implement the stack using only one queue?
*/
class Implement_Stack_using_Queues_225 {
	Queue<Integer> queueOne = new LinkedList<>();
	Queue<Integer> queueTwo = new LinkedList<>();

    public Implement_Stack_using_Queues_225() {}
    
    public void push(int x) {
        queueTwo.offer(x);
        while(queueOne.size() > 0) 
        	queueTwo.offer(queueOne.poll());

        Queue<Integer> temp = queueOne;
        queueOne = queueTwo;
        queueTwo = temp;
    }
    
    public int pop() {
		return queueOne.poll();
    }
    
    public int top() {
        return queueOne.peek();
    }
    
    public boolean empty() {
        return queueOne.size() == 0 && queueTwo.size() == 0;
    }
}


class Implement_Stack_using_Queues_225_III {
	Queue<Integer> queueOne = new LinkedList<>();
	Queue<Integer> queueTwo = new LinkedList<>();

    public Implement_Stack_using_Queues_225_III() {}
    
    public void push(int x) {
    	if (queueOne.size() == 0) queueOne.offer(x);
    	while (!queueTwo.size() == 0) queueOne.offer(queueTwo.poll());
    	while (!queueOne.size() == 0) queueTwo.offer(queueOne.poll());
    }
    
    public int pop() {
    	return queueTwo.poll();
    }
    
    public int top() {
        return queueTwo.peek();
    }
    
    public boolean empty() {
        return queueOne.size() == 0 && queueTwo.size() == 0;
    }
}

class Implement_Stack_using_Queues_225_II {
	Queue<Integer> queueOne = new LinkedList<>();
	Queue<Integer> queueTwo = new LinkedList<>();
	int top;
    public Implement_Stack_using_Queues_225_II() {}
    
    public void push(int x) {
        queueOne.offer(x);
        top = x;
    }
    
    public int pop() {
		while(queueOne.size() > 1) {
			top = queueOne.poll();
			queueTwo.offer(top);
		}
		int value = queueOne.poll();
		Queue<Integer> temp = queueOne;
		queueOne = queueTwo;
		queueTwo = temp;

		return value;
    }
    
    public int top() {
        return top;
    }
    
    public boolean empty() {
        return queueOne.size() == 0 && queueTwo.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */