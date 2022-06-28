package heap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.util.PriorityQueue;
/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
*/
public class Find_Median_from_Data_Stream_295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}


class MedianFinderSort {
    List<Integer> temp;
    public MedianFinder() {
        temp = new ArrayList<>();
    }

    public void addNum(int num) {
        temp.add(num);
    }

    public double findMedian() {
        Collections.sort(temp);

        int size = temp.size();
        int mid = size / 2;

        int index = 0;
        int sum = 0;
        for (int i : temp) {
            if (size % 2 == 0) {
                if (index == mid - 1) {
                    sum = i;
                } else if (index == mid) {
                    sum += i;
                    return (double)sum / 2;
                }
            } else {
                if (index == mid) return i;
            }

            index++;
        }

        return 0;
    }

    // Returns the median of current data stream
    public double findMedian() {
        Collections.sort(temp);

        int n = temp.size();
        return (n & 1) == 1 ? 
            temp.get(n / 2) : ((double) temp.get(n / 2 - 1) + temp.get(n / 2)) * 0.5;
    }
}

class MedianFinder {
    PriorityQueue<Integer> lo = new PriorityQueue<>((a1, a2) -> a2 - a1);
    PriorityQueue<Integer> hi = new PriorityQueue<>();

    public void addNum(int num) {
        lo.offer(num);                                    // Add to max heap

        hi.offer(lo.poll());                               // balancing step

        if (lo.size() < hi.size())                      // maintain size property
            lo.offer(hi.poll());
    }

    // Returns the median of current data stream
    double findMedian() {
        return lo.size() > hi.size() ? lo.peek() : ((double) lo.peek() + hi.peek()) * 0.5;
    }
};

class MedianFinder {
    PriorityQueue<Integer> maxHeap; //containing first half of numbers
    PriorityQueue<Integer> minHeap; //containing second half of numbers

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // we have even number of elements, take the average of middle two elements
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        // because max-heap will have one more element than the min-heap
        return maxHeap.peek();
    }
}

/*
class MedianFinder {
    List<Integer> temp;
    public MedianFinder() {
        temp = new ArrayList<>();
    }

    public void addNum(int num) {
        int n = temp.size();
        if (n == 0)
            temp.add(num);
        else {
            int index = findIndex(n, num);
            temp.add(index, num);
        }
    }

    private int findIndex(int n, int num) {
        int[] myArray = new int[n];
        int j = 0;

        for (int i : temp) myArray[j++] = i;

        int start = 0, end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if (myArray[mid] == num)
                return mid;
            else if (myArray[mid] < num)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return end + 1;
        // for(int i = 0; i < n; i++)
        //    if (myArray[i] == num) // If num is found
        //        return i;
        //    else if (myArray[i] > num) // If current array element, exceeds num
        //        return i;

        // If all elements are smaller
        // than num
        // return n;
    }

    // Returns the median of current data stream
    public double findMedian() {
        int n = temp.size();
        return (n & 1) == 1 ? temp.get(n / 2) : ((double) temp.get(n / 2 - 1) + temp.get(n / 2)) * 0.5;
    }
}
*/
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */