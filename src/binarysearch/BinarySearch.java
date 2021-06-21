package binarysearch;

import java.util.Arrays;

public class BinarySearch {
    public boolean firstBinarySearchIterative(int[] array, int x) {
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

    public int firstBinarySearchIndex(int[] nums, int target) {
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

    public boolean binarySearchRecursive(int[] array, int x) {
        return binarySearchRecursive(array, x, 0, array.length - 1);
    }

    private boolean binarySearchRecursive(int[] array, int x, int left, int right) {
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

    public int binarySearchRecursiveII(int[] nums, int low, int high, int target) {
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

    public int binarySearch(int[] nums, int target) {
        if (nums.length == 0 || nums == null) return -1;

        int left = 0, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) left = mid + 1;
            else if (nums[min] > target) right = mid;
            else return mid;
        }
    }

    public int secondBinarySearchI(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
    }

    public int secondBinarySearchV(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;
            else if (target > nums[mid]) left = mid + 1;
            else right = mid;
        }

        if (left != nums.length && nums[left] == target) return left;
        return - 1;
    }

    int secondBinarySearchII(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length;
        while(left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) return left;
        return -1;
    }

    public int binarySearchIIII(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (int) Math.floor((right - left + 1) / 2);

            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[left] == target ? left : -1;
    };

    public int binarySearchIV(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        if (target == nums[left]) {
            return left;
        }
        if (target == nums[right]) {
            return right;
        }

        return -1;
    }

    public boolean binarySearchRecursiveII(int[] array, int target) {
        if (array == null || array.length <= 0) {
            return false;
        }

        return binarySearchRecursiveII(array, target, 0, array.length - 1);
    }

    private boolean binarySearchRecursiveII(int[] array, int target, int start, int end) {
        if (start > end) {
            return false;
        }

        int mid = start + (end - start) / 2;

        if (array[mid] == target) {
            return true;
        } else if (target < mid) {
            return binarySearchRecursiveII(array, target, start, mid - 1);
        } else {
            return binarySearchRecursiveII(array, target, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4 }, 2));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5 }, 2));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5, 6 }, 2));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 4, 5, 6 }, 6));
        System.out.println(new BinarySearch().binarySearchIV(new int[] { 2, 3, 4, 5 }, 2));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 3, 4, 5, 6 }, 6));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5, 6 }, 6));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5, 6 }, 4));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5, 6 }, 3));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 2, 3, 4, 5, 6 }, 5));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 3, 4, 5, 6 }, 4));
        System.out.println(new BinarySearch().binarySearchIII(new int[] { 3, 4, 5, 6 }, 5));
        /*
         * int[] nums = new int[] { 2, 3, 8, 1, 3, 5 }; Arrays.sort(nums);
         *
         * Arrays.binarySearch(nums, 4);
         */
    }
}
