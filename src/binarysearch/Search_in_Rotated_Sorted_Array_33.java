package binarysearch;

class Search_in_Rotated_Sorted_Array_33 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Search_in_Rotated_Sorted_Array_33().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new Search_in_Rotated_Sorted_Array_33().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
        /* 
        Time complexity: O(logN).
        Space complexity: O(1).
        */
    }
}