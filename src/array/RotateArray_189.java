package array;

class RotateArray_189 {
  public void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public void reverse(int[] nums, int start, int end) {
    while (start < end) {
      nums[start] = nums[start] + nums[end];
      nums[end] = nums[start] - nums[end];
      nums[start] = nums[start] - nums[end];
      start++;
      end--;
    }
  }
}