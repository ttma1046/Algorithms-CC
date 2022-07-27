
package prefix_sum;

class Product_of_Array_Except_Self_238 {
    public int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        int zeroIndex = -1;
        int product = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                zeroIndex = i;
            } else product *= nums[i];
        }

        int[] ans = new int[nums.length];

        if (zeroCount > 1)
            return ans;

        if (zeroCount == 1) {
            ans[zeroIndex] = product;
            return ans;
        }

        for (int i = 0; i < ans.length; i++)
            ans[i] = product / nums[i];

        return ans;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 0)
            return null;

        int [] result = new int[nums.length];

        result[0] = 1;

        for (int i = 1; i < nums.length; i++)
            result[i] = result[i - 1] * nums[i - 1];


        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * suffix;
            suffix *= nums[i];
        }

        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 0)
            return null;

        int [] result = new int[nums.length];
        int product = 1;
        int product_of_non_zeros = 1;
        int count_zero = 0;
        int zero_index = 0;

        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];

            if (nums[i] == 0) {
                count_zero++;
                zero_index = i;
            } else {
                product_of_non_zeros *= nums[i];
            }
        }

        if (product != 0) {
            for (int i = 0; i < nums.length; i++)
                result[i] = product / nums[i];
        } else if (count_zero == 1) {
            result[zero_index] = product_of_non_zeros;
        }

        return result;
    }
}