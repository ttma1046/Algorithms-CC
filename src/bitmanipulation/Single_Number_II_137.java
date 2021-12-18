package bitmanipulation;

/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:

Input: nums = [2,2,3,2]
Output: 3

Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99

Constraints:

1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.
*/
class Single_Number_II_137 {
    public int singleNumber(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;
        for(int n : nums) {
            sumArray += n;
            set.add((long)n);
        }
        for(Long s : set) sumSet += s;
        return (int)((3 * sumSet - sumArray) / 2);
    }

    public int singleNumber(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sum = 0, k = 0;

        for (int i : nums) {
            sum += i;
            set.add((long)i);
        }

        for (Long a : set) k += a;
        return (int)((3 * k - sum) / 2);
    }
    /*
    Complexity Analysis

    Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

    Space complexity : \mathcal{O}(N)O(N) to keep the set of N/3N/3 elements.8
    */

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums)
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);

        for (int k : hashmap.keySet())
            if (hashmap.get(k) == 1) return k;
        return -1;
    }

    /*
    Complexity Analysis

    Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

    Space complexity : \mathcal{O}(N)O(N) to keep the hashmap of N/3N/3 elements.
    */

    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }
    /*
    Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

    Space complexity : \mathcal{O}(1)O(1) since no additional data structures are allocated.
    */

    public static void main(String[] args) {
        Single_Number_II_137 obj = new Single_Number_II_137();
        int[] nums = new int[] {2, 2, 3, 2};
        obj.singleNumber(nums);

        nums = new int[] { 0, 1, 0, 1, 0, 1, 99 }
    }
}






