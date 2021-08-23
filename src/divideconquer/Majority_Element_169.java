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
}