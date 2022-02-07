package binarysearch;

import java.util.Arrays;

public class BinarySearch {
    /*
    Template #1 is the most basic and elementary form of Binary Search. 
    It is the standard Binary Search Template that most high schools or universities use 
    when they first teach students computer science. 

    Template #1 is used to search for an element or condition which 
    can be determined by accessing a single index in the array.

    Key Attributes:

    Most basic and elementary form of Binary Search
    Search Condition can be determined 
    without comparing to the element's neighbors 
    (or use specific elements around it)
    
    No post-processing required because at each step, 
    you are checking to see if the element has been found. 

    If you reach the end, then you know the element is not found

    Distinguishing Syntax:

    Initial Condition: start = 0, end = length - 1
    Termination: start > end
    Searching towards to left: end = mid - 1
    Searching towards to the right: start = mid + 1
    */
    int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int start = 0, end = nums.length - 1;
        while(start <= end) {
            // Prevent (start + end) overflow
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        // End Condition: start > end
        // (end, start)

        System.out.println(start);
        System.out.println(end);
        return -1;
    }

    /*
    Template #1:
    // Pre-prcessing
    start = 0; end = length - 1;
    while(start <= end) {
        mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    // end + 1 == start
    // No more candidate
    */ 

    /*
    Template #2 is an advanced form of Binary Search. It is used to search for an element or condition which requires accessing the current index and its immediate right neighbor's index in the array.

    Key Attributes:

    An advanced way to implement Binary Search.
    Search Condition needs to access element's immediate right neighbor
    Use element's right neighbor to determine if condition is met and decide whether to go left or right
    Gurantees Search Space is at least 2 in size at each step
    Post-processing required. Loop/Recursion ends when you have 1 element left. Need to assess if the remaining element meets the condition.

    Distinguishing Syntax:

    Initial Condition: left = 0, right = length
    Termination: left == right
    Searching Left: right = mid
    Searching Right: left = mid+1
    */
    int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while(left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) return left;
        return -1;
    }

    /*
    Template #2:
    // Pre-processing
    left = 0; right = length;
    while (left < right) {
        mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }

        ...
        // left == right
        // 1 more candidate
        // Post-Processing
    }
    */
    /*
    Template #3 is another unique form of Binary Search. 
    It is used to search for an element or condition 
    which requires accessing the current index and its immediate left and right neighbor's index in the array.

    Key Attributes:

    An alternative way to implement Binary Search
    Search Condition needs to access element's immediate left and right neighbors
    Use element's neighbors to determine if condition is met and decide whether to go left or right
    Gurantees Search Space is at least 3 in size at each step
    Post-processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet the condition.

    Distinguishing Syntax:

    Initial Condition: left = 0, right = length-1
    Termination: left + 1 == right
    Searching Left: right = mid
    Searching Right: left = mid
    */
    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            // Prevent (start + end) overflow
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // Post-processing:
        // End Condition: start + 1 == end
        if(nums[start] == target) return start;
        if(nums[end] == target) return end;
        return -1;
    }

    /*
    Template #3:
    // Pre-processing
    left = 0; right = length - 1;
    while (left + 1 < right) {
        mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }

        ...
        // left + 1 == right
        // 2 more candidate
        // Post-Processing
        if(nums[left] == target) return left;
        if(nums[right] == target) return right;
        return -1;
    }
    */

    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return - 1;

        int low = 0, high = nums.length - 1, mid = -1;

        while (low + 1 < high) {
            mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;
            if (nums[mid] < target) low = mid;
            else high = mid;
        }

        if (nums[low == target) return low;
                if (nums[high] == target) return high;
                return - 1;
    }

    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return - 1;

        int low = 0, high = nums.length, mid = 0;

        while (low < high) {
            mid = low + (high - low) / 2;
            int (nums[mid] == target) return mid;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }

        if(low != nums.length && nums[low] == target) return low;
        return -1;
    }

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

    /*
    public int secondBinarySearch(int[] nums, int target) {
        if (nums.length == 0 || nums == null) return -1;

        int left = 0, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid;
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
    */

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

    /*
    Template #2 is an advanced form of Binary Search.
    It is used to search for an element or condition which requires accessing the current index and its immediate right neighbor's index in the array.

    Key Attributes:

    An advanced way to implement Binary Search.
    Search Condition needs to access element's immediate right neighbor
    Use element's right neighbor to determine if condition is met and decide whether to go left or right
    Gurantees Search Space is at least 2 in size at each step
    Post-processing required. Loop/Recursion ends when you have 1 element left. Need to assess if the remaining element meets the condition.

    Distinguishing Syntax:

    Initial Condition: left = 0, right = length
    Termination: left == right
    Searching Left: right = mid
    Searching Right: left = mid+1
    */
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
