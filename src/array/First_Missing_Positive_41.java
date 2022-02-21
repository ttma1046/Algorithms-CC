package array;
import java.util.Arrays;
/*
Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
*/
class First_Missing_Positive_41 {
    public int firstMissingPositiveI(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                if (nums[i] != ++res) return res;
            }
        }

        return res + 1;
    }

    public int firstMissingPositive(int[] nums) {
    	int n = nums.length;
        for(int i = 0; i < n; ++i)
            while(nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);

        for (int i = 0; i < nums.length; i++) {
        	System.out.print(nums[i]);
        	System.out.print(",");
        }
        
        for(int i = 0; i < n; ++ i)
            if(nums[i] != i + 1)
                return i + 1;
        
        return n + 1;
    }

    private void swap(int[] nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }

    public static void main(String[] args) {
    	int[] arr = new int[] {1,2,0};
    	First_Missing_Positive_41 obj = new First_Missing_Positive_41();
    	System.out.println(obj.firstMissingPositive(arr));

    	arr = new int[] {3,4,-1,1};
    	System.out.println(obj.firstMissingPositive(arr));


    	arr = new int[] {7,8,9,11,12};
    	System.out.println(obj.firstMissingPositive(arr));

    	arr = new int[] {0, 2, 2, 1, 1};
    	System.out.println(obj.firstMissingPositive(arr));
    }
}