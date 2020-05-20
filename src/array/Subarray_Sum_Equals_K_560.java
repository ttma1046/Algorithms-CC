package array;

import java.util.HashMap;

class Subarray_Sum_Equals_K_560 {
    public int subarraySumIII(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;
        
        for(int i = 0; i < nums.length; i++) {
            int sum = nums[i];

            if (sum == k) {
                result++;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (sum + nums[j] == k) {
                    result++;
                }

                sum += nums[j];
            }
        }

        return result;
    }


    public int subarraySumII(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;
        int sum = 0;

        HashMap<Integer, Integer> myHash = new HashMap<Integer, Integer>();
        myHash.put(0, 1); // super-duper

        for (int i = 0; i < nums.length; i++) {
            sum +=  nums[i];
            if (myHash.containsKey(sum - k)) {
                result += map.get(sum - k);
            }

            myHash.put(sum, myHash.getOrDefault(sum, 0)++);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Subarray_Sum_Equals_K_560().subarraySum(new int[] { 28, 54, 7, -70, 22, 65, -6 }, 100));

        System.out.println(new Subarray_Sum_Equals_K_560().subarraySum(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));
    }
}