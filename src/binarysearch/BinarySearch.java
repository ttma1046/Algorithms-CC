package binarysearch;

import java.util.Arrays;

public class BinarySearch {
    public static boolean binarySearchIterative(int[] array, int x) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == x) {
                return true;
            } else if (array[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static boolean binarySearchRecursive(int[] array, int x) {
        return binarySearchRecursive(array, x, 0, array.length - 1);
    }

    public static boolean binarySearchRecursive(int[] array, int x, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == x) {
            return true;
        } else if (x < array[mid]) {
            return binarySearchRecursive(array, x, left, mid - 1);
        } else {
            return binarySearchRecursive(array, x, mid + 1, right);
        }
    }

    public static int binarySearch(int[] nums, int low, int high, int target) {
        if (high <= low) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (nums[mid] > target) {
            return binarySearch(nums, low, mid, target);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid, high, target);
        } else {
            return mid;
        }
    }

    public static int binarySearchII(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearchIII(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }
        }
        return -1;
    }

    public static int binarySearchIV(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                return mid;
            }
        }

        if (target == nums[left]) {
            return left;
        } else if (target == nums[right]) {
            return right;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,3,8,1,3,5};
        Arrays.sort(nums);

        Arrays.binarySearch(nums, 4);
    }
}
