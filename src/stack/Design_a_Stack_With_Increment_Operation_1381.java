package stack;

/*
Design a stack which supports the following operations.

Implement the CustomStack class:

CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack or do nothing if the stack reached the maxSize.
void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
int pop() Pops and returns the top of stack or -1 if the stack is empty.
void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, just increment all the elements in the stack.

Example 1:

Input
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
Output
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
Explanation
CustomStack customStack = new CustomStack(3); // Stack is Empty []
customStack.push(1);                          // stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.push(3);                          // stack becomes [1, 2, 3]
customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
customStack.increment(5, 100);                // stack becomes [101, 102, 103]
customStack.increment(2, 100);                // stack becomes [201, 202, 103]
customStack.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
customStack.pop();                            // return 202 --> Return top of the stack 102, stack becomes [201]
customStack.pop();                            // return 201 --> Return top of the stack 101, stack becomes []
customStack.pop();                            // return -1 --> Stack is empty return -1.
 
Constraints:

1 <= maxSize <= 1000
1 <= x <= 1000
1 <= k <= 1000
0 <= val <= 100
At most 1000 calls will be made to each method of increment, push and pop each separately.
*/

class Design_a_Stack_With_Increment_Operation_1381 {
	private int maxS;
	private int[] stack;
	private int top = 0;

	public Design_a_Stack_With_Increment_Operation_1381(int maxSize) {
		maxS = maxSize;
		stack = new int[maxS];
	}

	public void push(int x) {
		if (top == maxS) {
			System.out.println("Don't add another elements as size is 4");
		} else {
			stack[top++] = x;
		}
	}

	public int pop() {
		return top == 0 ? -1 : stack[--top];
	}

	public void increment(int k, int val) {
		/*
		if (top + 1 < k) {
			for (int i = 0; i < top; i++) {
				stack[i] += val;
			}
		} else  {
			for (int i = 0; i < k; i++) {
				stack[i] += val;
			}
		}
		*/

		for (int i = 0; i < (top + 1 < k ? top : k); i++) {
			stack[i] += val;
		}
	}

	public static void main(String[] args) {
		Design_a_Stack_With_Increment_Operation_1381 customStack = new Design_a_Stack_With_Increment_Operation_1381(3); // Stack is Empty []
		customStack.push(1);                          // stack becomes [1]
		customStack.push(2);                          // stack becomes [1, 2]
		System.out.println(customStack.pop());                            // return 2 --> Return top of the stack 2, stack becomes [1]
		customStack.push(2);                          // stack becomes [1, 2]
		customStack.push(3);                          // stack becomes [1, 2, 3]
		customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
		customStack.increment(5, 100);                // stack becomes [101, 102, 103]
		customStack.increment(2, 100);                // stack becomes [201, 202, 103]
		System.out.println(customStack.pop());                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
		System.out.println(customStack.pop());                            // return 202 --> Return top of the stack 102, stack becomes [201]
		System.out.println(customStack.pop());                            // return 201 --> Return top of the stack 101, stack becomes []
		System.out.println(customStack.pop());                            // return -1 --> Stack is empty return -1.
	}
}


class CustomStack {
    // lazy load

    int[] stackarray;
    int[] lazy;
    int pos;
    int size;
    public CustomStack(int maxSize) {
        stackarray = new int[maxSize];
        lazy = new int[1001];
        pos = 0;
        size = maxSize;
    }
    
    public void push(int x) {
        if (isFull()) return;
        stackarray[pos++] = x;
    }
    
    public int pop() {
        if (isEmpty()) return -1;
        if (lazy[pos - 1] != 0) {
            int tmp = lazy[pos - 1];
            if (pos - 2 >= 0) lazy[pos - 2] += tmp;
            lazy[pos - 1] = 0;
            return stackarray[--pos] + tmp;
        } else {
            return stackarray[--pos];
        }
    }
    
    public void increment(int k, int val) {
        if (isEmpty()) return;
        if (k > pos) {
            lazy[pos - 1] += val;
        } else {
            lazy[k - 1] += val;
        }
    }

    private boolean isEmpty() { return pos == 0; }
    private boolean isFull() { return pos == size; }
}

class CustomStack {
	int[] stackarray;
	int[] lazy;
	int top;
	int size;

	public CustomStack(int maxSize) {
		stackarray = new int[maxSize];
		
		lazy = new int[1001];

		top = 0;

		size = maxSize;
	}

	public void push(int x) {
		if (isFull()) System.out.println("");
		else stackarray[top++] = x;
	}

	public int pop() {
		if (isEmpty()) return -1;

		if (lazy[top - 1] != 0)  {
			int tmp = lazy[top - 1];
			if  (top - 2 >= 0) lazy[top - 2] += tmp;
			lazy[top - 1] = 0;
			return stackarray[--top] + tmp;
		} else {
			return stackarray[--top];
		}

	}

    public void increment(int k, int val) {
    	if (isEmpty()) return;
    	if (k >= top) {
    		lazy[top - 1] += val;
    	} else {
    		lazy[k - 1] += val;
    	}
    }

	private boolean isFull() { return top == size; }
	private boolean isEmpty() { return top == 0; }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */