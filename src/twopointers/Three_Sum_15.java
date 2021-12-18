package twopointers;

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

                        while(low < high && nums[low] == nums[low + 1]) ++low;
                        while(low < high && nums[high] == nums[high - 1]) --high;

                        ++low;
                        --high;
                    } else if (nums[low] + nums[high] < sum) ++low;
                    else --high;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Three_Sum_15 obj = new Three_Sum_15();
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            if (i == 0 || (i > 0 && nums[i - 1] != nums[i])) {
                int low = i + 1, high = n - 1, sum = 0 - nums[i];
                while(low < high) {
                	if (num[low] + num[high] == sum) {
                		res.add(new ArrayList(num[low], num[high], sum));

                		while(low < high && nums[low] == nums[low + 1]) low++;
                		while(low < high && nums[high] == nums[high - 1]) high--;

                		low++;
                		high--;
                	} else if (nums[low] + nums[high] < sum) low++;
                	else high--;
                }
            }
        }

        return res;
    }
}