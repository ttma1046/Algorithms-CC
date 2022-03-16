package array;
import java.util.TreeSet;
/*
Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true

Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false

Constraints:

1 <= nums.length <= 2 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^4
0 <= t <= 2^31 - 1
*/
class Contains_Duplicate_III_220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = Math.max(i - k, 0); j < i; ++j) {
                if (Math.abs((long)nums[i] - nums[j]) <= t) return true;
            }
        }

        return false;
    }

    /*
    Complexity Analysis

    Time complexity: O(nmin(k,n)). It costs O(min(k,n)) time for each linear search.
    Note that we do at most nn comparisons in one search even if k can be larger than n.

    Space complexity: O(1). We only used constant auxiliary space.
    */

    public boolean containsNearbyAlmostDuplicateII(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length; ++i) {
            Integer s = set.ceiling(nums[i]);
            if (s != null && t >= (long)s - nums[i]) return true;

            Integer x = set.floor(nums[i]);
            if (x != null && t >= (long)nums[i] - x) return true;

            set.add(nums[i]);

            if (set.size() > k) set.remove(nums[i - k]);
        }

        return false;
    }
    /*
    Time complexity: O(nlog(min(n,k))). We iterate through the array of size n.
    For each iteration, it costs O(logmin(k,n)) time (search, insert or delete) in the BST,
    since the size of the BST is upper bounded by both k and n.

    Space complexity: O(min(n,k)). Space is dominated by the size of the BST,
    which is upper bounded by both k and n.
    */

    public static void main(String[] args) {
        Contains_Duplicate_III_220 obj = new Contains_Duplicate_III_220();
        int[] nums = new int[] { 1, 2, 3, 1 };
        int k = 3;
        int t = 0;
        boolean res = obj.containsNearbyAlmostDuplicateII(nums, k, t);
        System.out.println(res);

        nums = new int[] { 1, 0, 1, 1 };
        k = 1;
        t = 3;
        res = obj.containsNearbyAlmostDuplicateII(nums, k, t);
        System.out.println(res);

        nums = new int[] { 1, 5, 9, 1, 5, 9 };
        k = 2;
        t = 3;
        res = obj.containsNearbyAlmostDuplicateII(nums, k, t);
        System.out.println(res);

        nums = new int[] { -2147483648, 2147483647 };
        k = 1;
        t = 1;
        res = obj.containsNearbyAlmostDuplicateII(nums, k, t);
        System.out.println(res);
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicateIII(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    /*
    Time complexity: O(n). 
    For each of the n elements, we do at most three searches, one insert, and one delete on the HashMap, which costs constant time on average. 
    Thus, the entire algorithm costs O(n) time.

    Space complexity: O(min(n,k)). 
    Space is dominated by the HashMap, which is linear to the size of its elements. 
    The size of the HashMap is upper bounded by both n and k. Thus the space complexity is O(min(n,k)).
    */
}