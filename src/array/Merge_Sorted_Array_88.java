package array;

/*
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?
*/

class Merge_Sorted_Array_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = nums1.length - 1;
        while (p > -1) {
            int cand1 = p1 > -1 ? nums1[p1] : Integer.MIN_VALUE;
            int cand2 = p2 > -1 ? nums2[p2] : Integer.MIN_VALUE;
            if (cand1 > cand2) {
                nums1[p] = cand1;
                p1--;
            } else {
                nums1[p] = cand2;
                p2--;
            }
            p--;
        }
    }

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p, m + n - p1 - p2);

        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p, m + n - p1 - p2);

        return nums1;
    }

    public int[] mergeII(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0)) {
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }

        return nums1;
    }

    public static void main(String[] args) {
        int[] result = new Merge_Sorted_Array_88().mergeII(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);

        for(int i : result) {
            System.out.println(i);
        }

        result = new Merge_Sorted_Array_88().mergeII(new int[] {0, 0, 0}, 0, new int[] {2, 5, 6}, 3);

        for(int i : result) {
            System.out.println(i);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        for (int i = 0; i < m; i++)
            temp[i] = nums1[i];

        int p1 = 0;
        int p2 = 0;

        for (int p = 0; p < m + n; ++p) {
            if (p1 == m) {
                nums1[p] = nums2[p2++];
            } else if (p2 == n) {
                nums1[p] = temp[p1++];
            } else if (temp[p1] > nums2[p2]) {
                nums1[p] = nums2[p2++];
            } else {
                nums1[p] = temp[p1++];
            }
        }
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] nums1_copy = new int[m];
        for (int i = 0; i < m; i++)
            nums1_copy[i] = nums1[i];

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0, p2 = 0;
        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        
        // if there are still elements to add
        while (p1 < m)
            nums1[p++] = nums1_copy[p1++];

        while (p2 < n)
            nums1[p++] = nums2[p2++];
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {   
        // Make a copy of the first m elements of nums1.
        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) 
            nums1Copy[i] = nums1[i];
        
        // Read pointers for nums1Copy and nums2 respectively.
        int p1 = 0;
        int p2 = 0;
                
        // Compare elements from nums1Copy and nums2 and write the smallest to nums1.
        for (int p = 0; p < m + n; p++) {
            // We also need to ensure that p1 and p2 aren't over the boundaries
            // of their respective arrays.
            if (p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2]))
                nums1[p] = nums1Copy[p1++];
            else
                nums1[p] = nums2[p2++];
        }
    }
}