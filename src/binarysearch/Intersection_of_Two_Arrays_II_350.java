package binarysearch;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000


Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/
class Intersection_of_Two_Arrays_II_350 {
    public int[] intersectI(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList<>();
        int[] exist1 = new int[1001];
        int[] exist2 = new int[1001];
        for (int num : nums1) exist1[num]++;
        for (int num : nums2) exist2[num]++;

        for (int num = 0; num <= 1000; num++)
            if (exist1[num] > 0 && exist2[num] > 0) {
                int count = Math.min(exist1[num], exist2[num]);
                while (count-- > 0) ans.add(num);
            }

        int[] res = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);

        return res;
    }

    public static void main(String[] args) {
        Intersection_of_Two_Arrays_II_350 obj = new Intersection_of_Two_Arrays_II_350();

        int[] nums1 = new int[] {4, 9, 5};
        int[] nums2 = new int[] {9, 4, 9, 8, 4};
        int[] res = obj.intersect(nums1, nums2);
        for (int i : res) System.out.println(i);
    }

    /*
    Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

    Follow-up Questions
    What if the given array is already sorted? How would you optimize your algorithm?

    We can use either Approach 2 or Approach 3, dropping the sort of course. It will give us linear time and constant memory complexity.

    What if nums1's size is small compared to nums2's size? Which algorithm is better?

    Approach 1 is a good choice here as we use a hash map for the smaller array.

    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

    If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.

    If neither of the arrays fit into the memory, we can apply some partial processing strategies:

    Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).

    Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
    */

    /*
    The first question is relatively easy,
    create a hashmap base on number frequency of nums1(whichever one is longer).
    Then for every element of nums2, look upon the hashmap. If we found an intersection, deduct by 1 to avoid duplicate.
    */
    public int[] intersectII(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect(nums2, nums1);

        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums1) map.put(i, map.getOrDefault(i, 0) + 1);

        ArrayList<Integer> list = new ArrayList<>();

        for(int i : nums2) {
            int cnt = map.getOrDefault(i, 0);
            if (cnt > 0) {
                list.add(i);
                map.put(i, cnt - 1);
            }
        }

        int[] ret = new int[list.size()];

        for(int i = 0; i < list.size(); i++) ret[i] = list.get(i);

        return ret;
    }

    /*
    Complexity Analysis

    Time Complexity: O(n + m), where n and m are the lengths of the arrays.

    We iterate through the first, and then through the second array; insert and lookup operations in the hash map take a constant time.

    Space Complexity: O(min(n,m)). We use hash map to store numbers (and their counts) from the smaller array.
    */

    /*
    This solution is O(N + M) time complexity, O(N) for iterate one of the array to create a hashmap and O(M) to iterate the other array.

    O(N) space to store such hashmap.
    */

    /*
    Follow Up:
    What if the given array is already sorted? How would you optimize your algorithm?
    Classic two pointer iteration, i points to nums1 and j points to nums2.
    Because a sorted array is in ascending order, so if nums1[i] > nums[j], we need to increment j, and vice versa.
    Only when nums1[i] == nums[j], we add it to the result array.

    Time Complexity O(max(N, M)). Worst case, for example, would be nums1 = {100}, and nums2 = {1, 2, ..., 100 }.

    We will always iterate the longest array. The example code is below(I sorted it so it could go through OJ):
    */
    public int[] intersectIII(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while(i < n && j < m) {
            // int  = nums1[i], b= nums2[j];
            if(nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) i++;
            else j++;
        }
        int[] res = new int[list.size()];
        for(int k = 0; k < list.size(); k++) res[k] = list.get(k);
        return res;
    }
    /*
    Time Complexity: O(nlogn + mlogm), where n and m are the lengths of the arrays. We sort two arrays independently, and then do a linear scan.

    Space Complexity: from O(logn+logm) to O(n + m), depending on the implementation of the sorting algorithm.

    For the complexity analysis purposes, we ignore the memory required by inputs and outputs.
    */

    /*
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    This one is a bit tricky.
    Let's say nums1 is K size.
    Then we should do binary search for every element in nums1.

    Each lookup is O(log N), and if we do K times, we have O(K log N).

    If K this is small enough, O(K log N) < O(max(N, M)). Otherwise, we have to use the previous two pointers method.

    let's say A = [1, 2, 2, 2, 2, 2, 2, 2, 1], B = [2, 2].

    For each element in B, we start a binary search in A.

    To deal with duplicate entry, once you find an entry, all the duplicate element is around that that index, so you can do linear search scan afterward.

    Time complexity, O(K(logN) + N).

    Plus N is worst case scenario which you have to linear scan every element in A. But on average,

    that shouldn't be the case. so I'd say the Time complexity is O(K(logN) + c), c (constant) is number of linear scan you did.

    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
    This one is open-ended. But Map-Reduce I believe is a good answer.
    */


    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersect(nums2, nums1);  // apply bs on bigger array
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int lowerBound = 0;  // lower bound for binary search
        for (int i = 0; i < nums1.length; i++) {
            int index = binarySearch(nums2, lowerBound, nums1[i]);
            if (index < nums2.length && nums2[index] == nums1[i]) {  // found on B
                list.add(nums1[i]);
                lowerBound = index + 1;
            }
        }

        int[] res = new int[list.size()];
        int i = 0;
        for (int n : list)  res[i++] = n;
        return res;
    }

    private int binarySearch(int[] nums, int lowerBound, int target) {
        int low = lowerBound, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }
        return high;
    }
}