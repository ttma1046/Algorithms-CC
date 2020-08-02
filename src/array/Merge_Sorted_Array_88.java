package array;

class Merge_Sorted_Array_88 {
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
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p, m + n - p1 - p2);
        }

        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p, m + n - p1 - p2);
        }

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

        for(int i: result) {
            System.out.println(i);
        }

        result = new Merge_Sorted_Array_88().mergeII(new int[] {0, 0, 0}, 0, new int[] {2, 5, 6}, 3);

        for(int i: result) {
            System.out.println(i);
        }
    }
}