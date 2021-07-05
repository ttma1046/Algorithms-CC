package binarysearch;
/*
This is an interactive problem.

You have a sorted array of unique elements and an unknown size. 
You do not have an access to the array but you can use the ArrayReader interface to access it. 

You can call ArrayReader.get(i) that:

returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
returns 231 - 1 if the i is out of the boundary of the array.
You are also given an integer target.

Return the index k of the hidden array where secret[k] == target or return -1 otherwise.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: secret = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in secret and its index is 4.

Example 2:

Input: secret = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in secret so return -1.

Constraints:

1 <= secret.length <= 104
-104 <= secret[i], target <= 104
secret is sorted in a strictly increasing order.
*/

class ArrayReader {
    private int[] secret = new int [] {-1, 0, 3, 5, 9, 12};

    int get(int i) {
        return secret[i];
    }
}


public class Search_in_a_Sorted_Array_of_Unknown_Size_702 {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 1;

        while (reader.get(right) < target) {
            left = right;
            right <<= 1;
        }

        int mid = -1, value = Integer.MIN_VALUE;
        while(left <= right) {
            mid = left + ((right - left) >> 1);
            value = reader.get(mid);
            if (value == target) return mid;
            else if (value > target) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }

    public int binarySearch(int left, int right, ArrayReader reader, int target) {
        int mid = -1, value = Integer.MIN_VALUE;
        while(left <= right) {
            mid = left + ((right - left) >> 1);
            value = reader.get(mid);
            if (value == target) return mid;
            else if (value > target) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        Search_in_a_Sorted_Array_of_Unknown_Size_702 obj = new Search_in_a_Sorted_Array_of_Unknown_Size_702();
        ArrayReader ar = new ArrayReader();
        System.out.println(obj.search(ar, 9));
        System.out.println(obj.search(ar, 2));
    }
}