package queue;
/*
Design your implementation of the circular double-ended queue (deque).

Implement the MyCircularDeque class:

MyCircularDeque(int k) Initializes the deque with a maximum size of k.
boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
boolean isEmpty() Returns true if the deque is empty, or false otherwise.
boolean isFull() Returns true if the deque is full, or false otherwise.

Example 1:

Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]

Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
*/
class Design_Circular_Deqeue_641 {
	int[] q;
	int front = 0, rear = -1, size = 0;
    public Design_Circular_Deqeue_641(int k) {
        q = new int[k];
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;
        --front;
        if (front < 0) front += q.length;
        q[front] = value;
        size++;
        if(size == 1) rear = front;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;

        rear = (rear + 1) % q.length;
        q[rear] = value;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;

        front = (front + 1) % q.length;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;
        --rear;
        if (rear < 0) rear += q.length;
        size--;
        return true;
    }
    
    public int getFront() {
        return isEmpty() ? -1 : q[front];
    }
    
    public int getRear() {
        return isEmpty() ? -1 : q[rear];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == q.length;
    }
}

class Design_Circular_Deqeue_641_II {
	int[] arr;
	int front, rear, capa;

    public Design_Circular_Deqeue_641(int k) {
        this.arr = new int[k];
        this.capa = k;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;
        arr[((--front % capa) + capa) % capa] = value;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;
        arr[((rear++ % capa) + capa) % capa] = value;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front++;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear--;
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) return -1;
        return arr[(front + capa) % capa];
    }
    
    public int getRear() {
        if (isEmpty()) return -1;
        return arr[(rear - 1 + capa) % capa];
    }
    
    public boolean isEmpty() {
        return rear == front;
    }
    
    public boolean isFull() {
        return rear - front == capa;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */