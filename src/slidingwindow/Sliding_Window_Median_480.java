package slidingwindow;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.TreeSet;
/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6

Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]


Constraints:

1 <= k <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/
public class Sliding_Window_Median_480 {
    /*
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] medians = new double[nums.length + 1 - k];

        for (int i = 0; i + k <= nums.length; i++) {
            List<Integer> temp = new ArrayList<>();

            for (int j = i; j < i + k; j++)
                temp.add(nums[j]);

            Collections.sort(temp);

            System.out.println(k & 1);

            if ((k & 1) == 1)
                medians[i] = temp.get(k / 2);
            else
                medians[i] = (double)(temp.get(k / 2 - 1) + (double)temp.get(k / 2)) / 2.0;
        }

        return medians;
    }
    */

    public static void main(String[] args) {
        Sliding_Window_Median_480 obj = new Sliding_Window_Median_480();

        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};

        int k = 3;

        double[] res = obj.medianSlidingWindow(nums, k);

        for (int i = 0; i < res.length; i++)
            System.out.println(res[i]);
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] medians = new double[nums.length + 1 - k];

        Map<Integer, Integer> hash = new HashMap<>();

        PriorityQueue<Integer> low = new PriorityQueue<Integer>(   new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });

        PriorityQueue<Integer> high = new PriorityQueue<Integer>(
        );

        int i = 0;      // index of current incoming element being processed

        int index = 0;

        // initialize the heaps
        while (i < k)
            low.offer(nums[i++]);

        for (int j = 0; j < k / 2; j++)
            high.offer(low.poll());

        // low   2 1
        // high    3 4


        // low   1
        // high    2 3

        while (true) {
            // get median of current window
            medians[index++] =
                (k & 1) == 1 ? low.peek() : ((double)high.peek() + (double)low.peek()) * 0.5;

            if (i >= nums.length)
                break;                          // break if all elements processed

            int outgoingElement = nums[i - k];        // outgoing element
            int incomingElement = nums[i++];          // incoming element
            int balance = 0;                    // balance factor

            // number `outgoingElement` exits window
            balance += (outgoingElement <= low.peek() ? -1 : 1);
            hash.put(outgoingElement, hash.getOrDefault(outgoingElement, 0) + 1);

            // number `incomingElement` enters window
            if (low.size() > 0 && incomingElement <= low.peek()) {
                balance++;
                low.offer(incomingElement);
            } else {
                balance--;
                high.offer(incomingElement);
            }

            // re-balance heaps
            if (balance < 0) {                  // `lo` needs more valid elements
                low.offer(high.poll());
                balance++;
            }

            if (balance > 0) {                  // `hi` needs more valid elements
                high.offer(low.poll());
                balance--;
            }

            // remove invalid numbers that should be discarded from heap tops
            while (low.size() > 0 && hash.size() > 0 && hash.containsKey(low.peek()) && hash.get(low.peek()) > 0)
                hash.put(low.peek(), hash.get(low.poll()) - 1);


            while (high.size() > 0 && hash.size() > 0 && hash.containsKey(high.peek()) && hash.get(high.peek()) > 0)
                hash.put(high.peek(), hash.get(high.poll()) - 1);
        }

        return medians;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];

        Comparator<Integer> comparator = new Comparator<>(){
             public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]);
                }
                else {
                    return a - b;
                }
            } 
        };
        
        TreeSet<Integer> low = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> high = new TreeSet<>(comparator);

        for (int i = 0; i < k; ++i)
            low.add(i);


        balance(low, high);
        res[0] = getMedian(nums, k, low, high);

        int index = 1;

        for (int i = k; i < nums.length; i++) {
            if (!low.remove(i - k))
                high.remove(i - k);

            high.add(i);
            low.add(high.pollFirst());
            balance(low, high);
            res[index++] = getMedian(nums, k, low, high);
        }

        return res;
    }

    private void balance(TreeSet<Integer> low, TreeSet<Integer> high) {
        while(low.size() > high.size())        
            high.add(low.pollFirst());
    }

    private double getMedian(int[] nums, int k, TreeSet<Integer> low, TreeSet<Integer> high) {
        if (k % 2 == 0) {
            return ((double)nums[low.first()] + nums[high.first()]) / 2;
        } else {
            return (double)nums[high.first()];
        }
    }
}
