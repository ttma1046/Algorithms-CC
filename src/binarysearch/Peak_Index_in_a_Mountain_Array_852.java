package binarysearch;
/*
Let's call an array arr a mountain if the following properties hold:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
Given an integer array arr that is guaranteed to be a mountain, return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].



Example 1:

Input: arr = [0,1,0]
Output: 1
Example 2:

Input: arr = [0,2,1,0]
Output: 1
Example 3:

Input: arr = [0,10,5,2]
Output: 1


Constraints:

3 <= arr.length <= 104
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.


Follow up: Finding the O(n) is straightforward, could you find an O(log(n)) solution?
*/
class Peak_Index_in_a_Mountain_Array_852 {
    public int peakIndexInMountainArray(int[] arr) {
        int i = 0;
        while (A[i] < A[i + 1]) i++;

        return i;
    }


    public int peakIndexInMountainArray(int[] A) {
        int lo = -1, hi = A.length;

        while (lo + 1 < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi;
            else
                hi = mi;
        }

        return lo + 1;
    }

    public int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    public int peakIndexInMountainArray(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < arr[mid + 1])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
 
   int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length - 1;
        int first = golden(left, right, true), second = golden(left, right, false);
        while (first < second) {
            if (A[first] < A[second]) {
                left = first;
                first = second;
                second = golden(first, right, true);
            } else {
                right = second;
                second = first;
                first = golden(left, second, false);
            }
        }
        return first;
    }
    
	int golden(int l, int r, boolean smaller) {
        return smaller ? (int)(l + Math.round((r - l) * 0.382)) : (int)(Math.round(l + (r - l) * 0.618));
    }
}