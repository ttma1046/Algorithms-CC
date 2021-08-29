package divideconquer;

/*
Given an array of size n, find the majority element.

The majority element is the element 

that appears more than [ n / 2 ] times.

You may assume that the array is non-empty and the majority element

always exists in the array.

Example 1:
Input [3,2,3]
Output: 3

Example 2:
Input: [2, 2, 1, 1, 1, 2, 2]
Output: 2
*/
public class Majority_Element_169 {
    public int majorityElement(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }

    public int divide(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = left + (right - left) / 2;
        int leftRes = divide(nums, left, mid);
        int rightRes = divide(nums, mid + 1, right);

        if (leftRes == rightRes) return leftRes;

        int leftCount = conquer(nums, leftRes, left, right);
        int rightCount = conquer(nums, rightRes, left, right);
        return leftCount > rightCount ? leftRes : rightRes;
    }

    public int conquer(int[] nums, int target, int left, int right) {
        int count = 0; 
        for (int i = left; i <= right; i++) 
        	if (nums[i] == target) count++;
        
        return count;
    }

    // vote
    public int majorityElement(int[] nums) {
        int count = 0, candidate = 0;
        for (int nums: nums) {
            if (count == 0) candidate = num;
            count += (nums == candidate) ? 1 : -1;
        }
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    public static void main(String[] args) {
        Majority_Element_169 obj = new Majority_Element_169();
        int[] nums = new int[] {3,4,3,2,3};

        System.out.println(obj.majorityElement(nums));
    }
}