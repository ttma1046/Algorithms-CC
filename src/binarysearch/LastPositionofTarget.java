package binarysearch;

/*
 * Find the last position of a target number in a sorted array.
 * Return -1 if target does not exist.
 * Given [1, 2, 2, 4, 5, 5]
 * For target = 2, return 2.
 * For target = 5, return 5.
 * For target = 6, return -1.
 */
public class LastPositionofTarget {
    public int FindLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;

        // start < end
        // start <= end
        // start + 1 < end
        while (start + 1 < end) {
            // while (start < end) [1,2] target = 1;
            // start = 2^31 - 1, end = 2^31 - 2
            // int = [-2^31, 2^31 - 1]
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
                // return mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new LastPositionofTarget().FindLastPosition(new int[] { 1, 2, 2, 4, 5, 5 }, 2));
        System.out.println(new LastPositionofTarget().FindLastPosition(new int[] { 1, 2, 2, 4, 5, 5 }, 5));
        System.out.println(new LastPositionofTarget().FindLastPosition(new int[] { 1, 2, 2, 4, 5, 5 }, 6));
    }
}
