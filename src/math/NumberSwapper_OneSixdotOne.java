package math;

public class NumberSwapper_OneSixdotOne {
    public int[] swap(int[] nums, int i, int j) {
        nums[j] = nums[i] + nums[j];
        nums[i] = nums[j] - nums[i];
        nums[j] = nums[j] - nums[i];
        return nums;
    }

    public int[] swapII(int[] nums, int i, int j) {
        nums[i] = nums[j] - nums[i];
        nums[j] = nums[j] - nums[i];
        nums[i] = nums[j] + nums[i];
        return nums;
    }

    public int[] swapIII(int[] nums, int i, int j) {
        nums[j] = nums[i] * nums[j];
        nums[i] = nums[j] / nums[i];
        nums[j] = nums[j] / nums[i];
        return nums;
    }

    public static void main(String[] args) {
        int [] nums = new int[] {4, 11};

        int [] result = new NumberSwapper_OneSixdotOne().swapII(nums, 0, 1);

        for(int i: result) {
            System.out.println(i);
        }
    }
}