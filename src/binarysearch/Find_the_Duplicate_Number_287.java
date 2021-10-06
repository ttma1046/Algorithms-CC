package binarysearch;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Example 2:

Input: nums = [3,1,3,4,2]
Output: 3

Example 3:

Input: nums = [1,1]
Output: 1

Example 4:

Input: nums = [1,1,2]
Output: 1

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.

Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
*/

class Find_the_Duplicate_Number_287 {
    public int findDuplicate(int[] nums) {
        int nums2[] = new int[nums.length];
        int i = 0;
        while (nums2[nums[i]] == 0) {
            nums2[nums[i]] = nums[i];
            i++;
        }
        return nums[i];
    }

    public int findDuplicateSort(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return nums[i];
        }

        return -1;
    }

    public int findDuplicateSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (set.contains(i)) return i;
            set.add(i);
        }

        return -1;
    }

    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) nums[index] *= -1;
            else return index + 1;
        }

        reture -1;
    }

    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }

    public int findDuplicate(int[] nums) {
    	int slow = nums[0], fast = nums[0];

    	while(true) {
    		slow = nums[slow];
    		fast = nums[nums[fast]];
    		if (slow == fast) {
    			fast = nums[0];
    			while (fast != slow) {
    				slow = nums[slow];
    				fast = nums[fast];
    			}

    			return slow;
    		}
    	}
    }

    public int findDuplicateBinarySearch(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int count = 0;
            for (int a : nums) {
                if (a <= mid) count++;
            }
            if (count <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static void main(String[] args) {
        Find_the_Duplicate_Number_287 obj = new Find_the_Duplicate_Number_287();

        int[] input = new int[] {1, 2, 3, 4, 3};

        System.out.println(obj.findDuplicateBinarySearch(input));
    }
}