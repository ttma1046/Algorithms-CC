package binarysearch;

public class MediumofTwoSortedArray_4 {
    /*
    There are two sorted arrays nums1 and nums2 of size m and n respectively.

    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    You may assume nums1 and nums2 cannot be both empty.

    Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0
    Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


        // merge two sorted array
        // binaryseach the middle

        int length = nums1.length + nums2.length;

        int[] mergedArray = new int[length];
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int z = length - 1;
        while (i >= 0 && j >= 0) {
            mergedArray[z--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }

        while (i >= 0) {
            mergedArray[z--] = nums1[i--];
        }

        while (j >= 0) {
            mergedArray[z--] = nums2[j--];
        }

        if (length % 2 == 1) {
            return mergedArray[length / 2];
        } else {
            return (double)((mergedArray[length / 2] + mergedArray[length / 2 - 1]) / (double)2);
        }
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }


    public static void main(String[] args) {
        System.out.println(new MediumofTwoSortedArray_4().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println(new MediumofTwoSortedArray_4().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }

    // findKthElement
    public int BinarySearch() {


        // first time:
        // k = 5
        // k/2 - 1 = 1

        // 1, 1, 1, 7, 9
        // 2, 4, 6, 8

        // cos 1 < 4
        // 1, 7, 9
        // 2, 4, 6, 8

        // second time:
        // k = 3
        // k/2 - 1 = 0

        // cos 1 < 2
        // 7, 9
        // 2, 4, 6, 8

        // third time:
        // k = 2
        // k/2 - 1 = 0

        // cos 2 < 7
        // 7, 9
        // 4, 6, 8

        // fourth time:
        // k = 1
        // cos 4 < 7
        // so result = 4.

        return 0;
    }
}
