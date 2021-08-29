package binarysearch;

class BinarySearch_Template {
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
        // end + 1 == start
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
    Template #2 is an advanced form of Binary Search. 

    It is used to search for an element or condition 

    which requires accessing the current index and its immediate right neighbor's index in the array.

    Key Attributes:
    An advanced way to implement Binary Search.
    Search Condition needs to access element's immediate right neighbor
    Use element's right neighbor to determine if condition is met and decide whether to go left or right
    Gurantees Search Space is at least 2 in size at each step
    Post-processing required. 

    Loop/Recursion ends when you have 1 element left. 
    Need to assess if the remaining element meets the condition.

    Distinguishing Syntax:

    Initial Condition: left = 0, right = length
    Termination: left == right
    Searching Left: right = mid
    Searching Right: left = mid+1
    */
    int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int start = 0, end = nums.length;
        while(start < end) {
            // Prevent (start + end) overflow
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) start = mid + 1;
            else end = mid;
        }

        // Post-processing:
        // End Condition: start == end
        if(start != nums.length && nums[start] == target) return start;
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
    Post-processing required. 

    Loop/Recursion ends when you have 2 elements left. 

    Need to assess if the remaining elements meet the condition.

    Distinguishing Syntax:

    Initial Condition: left = 0, right = length - 1
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

    public static void main(String[] args) {
    	BinarySearch_Template obj = new BinarySearch_Template();
    	obj.binarySearch(new int[] { 1, 2, 4, 5}, 3);
    }
}