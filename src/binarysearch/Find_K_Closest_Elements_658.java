package binarysearch;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]

Constraints:
1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*/
class Find_K_Closest_Elements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i : arr) {
            res.add(i);
        }

        Collections.sort(res, (num1, num2) -> Math.abs(num1 - x) - Math.abs(nums2 - x));

        sortedArr = sortedArr.subList(0, k);

        Collections.sort(sortedArr);

        return sortedArr;
    }
    /*
    Complexity Analysis:

    Given N as the length of arr,

    Time complexity: O(N * log(N) + k * log(k)).

    To build sortedArr, we need to sort every element in the array by a new criteria: x - num. This costs O(N * log(N)).
    Then, we have to sort sortedArr again to get the output in ascending order.

    This costs O(k * log(k)) time since sortedArr.length is only k.

    Space complexity: O(N).

    Before we slice sortedArr to contain only k elements, it contains every element from arr, which requires O(N)O(N) extra space. Note that we can use less space if we sort the input in place.
    */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();

        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }

            return result;
        }

        // Binary search to find the closest element
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] > x) {
                right = mid - 1;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                break;
            }
        }

        // Initialize our sliding window's bounds
        left = Math.max(0, mid - 1);
        right = left + 1;

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }
    /*
    Given N as the length of arr,

    Complexity Analysis

    Time complexity: O(log(N)+k).

    The initial binary search to find where we should start our window costs O(log(N)). Our sliding window initially starts with size 0 and we expand it one by one until it is of size k, thus it costs O(k) to expand the window.

    Space complexity: O(1)

    We only use integer variables left and right that are O(1) regardless of input size. Space used for the output is not counted towards the space complexity.
    */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
    /*
    Complexity Analysis

    Given NN as the length of arr,

    Time complexity: O(log(N−k)+k).

    Although finding the bounds only takes O(log(N−k)) time from the binary search, it still costs us O(k) to build the final output.

    Both the Java and Python implementations require O(k) time to build the result.

    However, it is worth noting that if the input array were given as a list instead of an array of integers,

    then the Java implementation could use the ArrayList.subList() method to build the result in O(1) time.

    If this were the case, the Java solution would have an (extremely fast) overall time complexity of O(log(N−k)).

    Space complexity: O(1).

    Again, we use a constant amount of space for our pointers, and space used for the output does not count towards the space complexity.
    */


    /*
    Intuition
    The array is sorted.
    If we want find the one number closest to x,
    we don't have to check one by one.
    it's straightforward to use binary research.

    Now we want the k closest,
    the logic should be similar.


    Explanation
    Assume we are taking A[i] ~ A[i + k -1].
    We can binary research i
    We compare the distance between x - A[mid] and A[mid + k] - x

    @vincent_gui listed the following cases:
    Assume A[mid] ~ A[mid + k] is sliding window

    case 1: x - A[mid] < A[mid + k] - x, need to move window go left
    -------x----A[mid]-----------------A[mid + k]----------

    case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
    -------A[mid]----x-----------------A[mid + k]----------

    case 3: x - A[mid] > A[mid + k] - x, need to move window go right
    -------A[mid]------------------x---A[mid + k]----------

    case 4: x - A[mid] > A[mid + k] - x, need to move window go right
    -------A[mid]---------------------A[mid + k]----x------

    If x - A[mid] > A[mid + k] - x,
    it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1],
    and we have mid smaller than the right i.
    So assign left = mid + 1.

    Important
    Note that, you SHOULD NOT compare the absolute value of abs(x - A[mid]) and abs(A[mid + k] - x).
    It fails at cases like A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3

    The problem is interesting and good.
    Unfortunately the test cases is terrible.
    The worst part of Leetcode test cases is that,
    you submit a wrong solution but get accepted.

    You didn't read my post and up-vote carefully,
    then you miss this key point.


    Complexity
    Time O(log(N - K)) to binary research and find result
    Space O(K) to create the returned list.
    */
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        int k = 4;
        int x = 3;

        Find_K_Closest_Elements_658 obj = new Find_K_Closest_Elements_658();

        System.out.println(obj.findClosestElements(arr, 4, 3));
    }
}