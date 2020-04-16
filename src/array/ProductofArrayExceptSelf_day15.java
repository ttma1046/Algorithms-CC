package array;

/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
    Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/


class ProductofArrayExceptSelf_day15 {
    public int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        int zeroIndex = -1;
        int product = 1;
        
        for (int i = 0; i < nums.length;i++){
            if (nums[i] == 0) {
                zeroCount++;
                zeroIndex = i;
            }
            else product *= nums[i];
        }

        int[] ans = new int[nums.length];
        
        if (zeroCount > 1) return ans;
        
        if (zeroCount == 1){
            ans[zeroIndex] = product;
            return ans;
        }
        
        for (int i = 0; i < ans.length;i++){
            ans[i] = product / nums[i];
        }
        
        return ans;
    }

    public int[] productExceptSelfTwoArray(int[] nums) {
        if (nums == null || nums.length < 0) {
            return null;
        }

        int [] result = new int[nums.length];

        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        for (int i = 0; i < prefix.length; i++) {
            if (i == 0) {
                prefix[i] = 1;
            } else {
                prefix[i] = prefix[i - 1] * nums[i - 1];
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                suffix[i] = 1;
            } else {
                suffix[i] = suffix[i + 1] * nums[i + 1];
            }
        }

        for (int i = 0; i < prefix.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    public int[] productExceptSelfOneArray(int[] nums) {
        if (nums == null || nums.length < 0) {
            return null;
        }

        int [] result = new int[nums.length];

        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * suffix;
            suffix *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[] {1, 2, 3, 4};

        int[] output = new ProductofArrayExceptSelf_day15().productExceptSelfOneArray(input);

        // input = new int[] {0, 0};

        // output = new ProductofArrayExceptSelf_day15().productExceptSelfII(input);

        for(int i: output) {
            System.out.println(i);
        }
    }
}