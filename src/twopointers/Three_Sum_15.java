package twopointers;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
/*
Given an integer array nums,

return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:

Input: nums = []
Output: []

Example 3:

Input: nums = [0]
Output: []


Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/
class Three_Sum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();

        int n = nums.length;
        for(int i = 0; i < n - 2; ++i) {
            if ((i == 0) || (i > 0 && nums[i - 1] != nums[i])) {
                int low = i + 1, high = n - 1, sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));

                        while(low < high && nums[low] == nums[low + 1])
                            ++low;
                        while(low < high && nums[high] == nums[high - 1])
                            --high;

                        ++low;
                        --high;
                    } else if (nums[low] + nums[high] < sum)
                        ++low;
                    else
                        --high;
                }
            }
        }

        return res;
    }

    /*
    Complexity Analysis

    Time Complexity: O(n^2). twoSumII is O(n), and we call it n times.

    Sorting the array takes O(nlogn), so overall complexity is O(nlogn+n^2 This is asymptotically equivalent to O(n^2).

    Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
    For the purpose of complexity analysis, we ignore the memory required for the output.
    */

    public List<List<Integer>> threeSumI(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;

        for (int i = 0; i < n && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                Set<Integer> seen = new HashSet<>();

                for (int j = i + 1; j < n; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.contains(complement)) {

                        res.add(Arrays.asList(nums[i], nums[j], complement));

                        while (j + 1 < n && nums[j] == nums[j + 1])
                            ++j;
                    }

                    seen.add(nums[j]);
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                Set<Integer> set = new HashSet<>();

                for (int j = i + 1; j < n; j++) {
                    int rest = -nums[i] - nums[j];

                    if (set.contains(rest)) {
                        res.add(Arrays.asList(nums[i], nums[j], rest));

                        while(j < n - 1 && nums[j] == nums[j + 1])
                            j++;
                    }

                    set.add(nums[j]);
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }

    public static void main(String[] args) {
        Three_Sum_15 obj = new Three_Sum_15();
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        obj.wtf(nums);
    }
}