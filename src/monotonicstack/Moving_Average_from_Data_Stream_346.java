package monotonicstack;

/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.


Example 1:

Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3


Constraints:

1 <= size <= 1000x
-105 <= val <= 105
At most 104 calls will be made to next.
*/
class Moving_Average_from_Data_Stream_346 {
    int size;
    List queue = new ArrayList<Integer>();
    public Moving_Average_from_Data_Stream_346(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        // calculate the sum of the moving window
        int windowSum = 0;
        for(int i = Math.max(0, queue.size() - this.size); i < queue.size(); ++i)
            windowSum += (int)queue.get(i);

        return windowSum * 1.0 / Math.min(queue.size(), size);
    }

    /*
    Complexity

    Time Complexity:
    O(N) where N is the size of the moving window,
    since we need to retrieve N elements from the queue at each invocation of next(val) function.

    Space Complexity: O(M), where M is the length of the queue which would grow at each invocation of the next(val) function.
    */


    int size, windowSum = 0, count = 0;
    Deque queue = new ArrayDeque<Integer>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        ++this.count;
        // calculate the new sum by shifting the window
        this.queue.add(val);
        int tail = this.count > this.size ? (int)this.queue.poll() : 0;

        this.windowSum = this.windowSum - tail + val;

        return this.windowSum * 1.0 / Math.min(this.size, this.count);
    }

    int size, head = 0, windowSum = 0, count = 0;
    int[] queue;
    public MovingAverage(int size) {
        this.size = size;
        queue = new int[size];
    }

    public double next(int val) {
        ++count;
        // calculate the new sum by shifting the window
        int tail = (head + 1) % size;
        windowSum = windowSum - queue[tail] + val;
        // move on to the next head
        head = (head + 1) % size;
        queue[head] = val;
        return windowSum * 1.0 / Math.min(size, count);
    }

    /*
    Complexity

	Time Complexity: \mathcal{O}(1)O(1), as we can see that there is no loop in the next(val) function.

	Space Complexity: \mathcal{O}(N)O(N), where NN is the size of the circular queue.
	*/

    public static void main(String[] args) {
        Moving_Average_from_Data_Stream_346 obj = new Moving_Average_from_Data_Stream_346(3);

        obj.next(1);
        obj.next(10);
        obj.next(3);
        obj.next(5);
    }
}