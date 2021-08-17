
package array;

/*
Design your implementation of the circular queue.

The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle

and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces

in front of the queue. In a normal queue, once the queue becomes full,

we cannot insert the next element even if there is a space in front of the queue.

But using the circular queue, we can use the space to store new values.

Implementation the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language.

Example 1:

Input
["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 3, true, true, true, 4]

Explanation
MyCircularQueue myCircularQueue = new MyCircularQueue(3);
myCircularQueue.enQueue(1); // return True
myCircularQueue.enQueue(2); // return True
myCircularQueue.enQueue(3); // return True
myCircularQueue.enQueue(4); // return False
myCircularQueue.Rear();     // return 3
myCircularQueue.isFull();   // return True
myCircularQueue.deQueue();  // return True
myCircularQueue.enQueue(4); // return True
myCircularQueue.Rear();     // return 4

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
*/

/*
class MyCircularQueue {
    private Node head, tail;
    private int count;
    private int capacity;
    // Additional variable to secure the access of our queue
    private ReentrantLock queueLock = new ReentrantLock();

    // Initialize your data structure here. Set the size of the queue to be k.
    public MyCircularQueue(int k) {
        this.capacity = k;
    }

    // Insert an element into the circular queue. Return true if the operation is successful.
    public boolean enQueue(int value) {
        // ensure the exclusive access for the following block.
        queueLock.lock();
        try {
            if (this.count == this.capacity)
                return false;

            Node newNode = new Node(value);
            if (this.count == 0) {
                head = tail = newNode;
            } else {
                tail.nextNode = newNode;
                tail = newNode;
            }
            this.count += 1;

        } finally {
            queueLock.unlock();
        }
        return true;
    }
}
*/
class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class Design_Circular_Queue_622 {
    private Node head, tail;
    private int count;
    private int capa;

    public Design_Circular_Queue_622(int k) {
        this.capa = k;
    }

    public boolean enQueue(int value) {
        if (this.count == this.capa) return false;

        Node newNode = new Node(value);

        if (this.count == 0) { 
          this.head = this.tail = newNode; 
        } else {
          this.tail.next = newNode;
          this.tail = this.tail.next;
        }
        this.count++;
        return true;
    }

    public boolean deQueue() {
        if (this.count == 0) return false;
        // Node temp = this.head;
        this.head = this.head.next;
        // temp.next = null;
        this.count--;
        // return temp.value;
        return true;
    }

    public int Front() {
      if (this.count <= 0 || this.count > this.capa) return -1;
      else return this.head.value;
    }

    public int Rear() {
      if (this.count <= 0 || this.count > this.capa) return -1;
      else return this.tail.value;
    }

    public boolean isEmpty() {
      return this.count == 0;
    }

    public boolean isFull() {
      return this.count == this.capa;
    }

    public static void main(String[] args) {
        Design_Circular_Queue_622 myCircularQueue = new Design_Circular_Queue_622(3);
        System.out.println(myCircularQueue.enQueue(1)); // return True
        System.out.println(myCircularQueue.enQueue(2)); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.enQueue(4)); // return False
        System.out.println(myCircularQueue.Front());     // return 1
        System.out.println(myCircularQueue.Rear());     // return 3

        System.out.println(myCircularQueue.isFull());   // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.deQueue());  // return True

        System.out.println(myCircularQueue.Front());     // return 2
        System.out.println(myCircularQueue.Rear());     // return 3

        System.out.println(myCircularQueue.enQueue(4)); // return True
        System.out.println(myCircularQueue.Front());     // return 2
        System.out.println(myCircularQueue.Rear());     // return 4
    }
}

/*
class Design_Circular_Queue_622 {
    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    // Initialize your data structure here. Set the size of the queue to be k.
    public Design_Circular_Queue_622(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    // Insert an element into the circular queue. Return true if the operation is successful.
    public boolean enQueue(int value) {
        if (this.count == this.capacity) return false;
        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    // Delete an element from the circular queue. Return true if the operation is successful.

    // public int deQueue() {
    //  if (this.count == 0) return -1;
    //  int index = this.headIndex;
    //  this.headIndex = (this.headIndex + 1) % this.capacity;
    //  this.count -= 1;
    //  return this.queue[index];
    // }


    // Delete an element from the circular queue. Return true if the operation is successful.
    public boolean deQueue() {
        if (this.count == 0) return false;
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count--;
        return true;
    }

    // Get the front item from the queue.
    public int Front() {
        if (this.count <= 0 || this.count > this.capacity) return -1;
        return this.queue[this.headIndex];
    }

    // Get the last item from the queue.
    public int Rear() {
        if (this.count <= 0 || this.count > this.capacity) return -1;

        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    // Checks whether the circular queue is empty or not.
    public boolean isEmpty() {
        return this.count == 0;
    }

    // Checks whether the circular queue is full or not.
    public boolean isFull() {
        return this.count == this.capacity;
    }

    // Time complexity: O(1).
    // All of the methods in our circular data structure is of constant time complexity.
    // Space Complexity: O(N).
    // The overall space complexity of the data structure is linear,
    // where N is the pre-assigned capacity of the queue.
    // However, it is worth mentioning that the memory consumption of the data structure remains as its pre-assigned capacity during its entire life cycle.
}
*/