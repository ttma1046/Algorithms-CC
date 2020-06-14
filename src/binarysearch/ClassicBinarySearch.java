package binarysearch;

public class ClassicBinarySearch {
    private int FindPosition(int[] nums, int target)
    {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;

        // start < end
        // start <= end
        while (start + 1 < end) {
            // start = 2^31 - 1, end = 2^31 - 2
            // int = [-2^31, 2^31 - 1]
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        /*
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) { 
                start = mid - 1;
            } else { 
                end = mid; 
            }
        }
        */

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    public int FindLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) { 
            return -1; 
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) { 
                start = mid; 
            } else if (nums[mid] < target) { 
                start = mid + 1; 
            } else {
                end = mid - 1;
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

    public int FindFirstPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) { 
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] == target) { 
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
    	/*
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {1}, 1));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {1,2}, 1));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {1,2}, 2));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {1}, 3));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {1,2,4,5}, 3));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16 }, 9));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18 }, 9)); 
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16 }, 0));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16 }, 16));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16 }, 8));
        */
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4}, 2));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5}, 2));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5,6}, 2));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {4,5,6}, 6));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {3,4,5,6}, 6));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5,6}, 6));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5,6}, 4));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5,6}, 3));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {2,3,4,5,6}, 5));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {3,4,5,6}, 4));
        System.out.println(new ClassicBinarySearch().FindPosition(new int[] {3,4,5,6}, 5));
    }
}