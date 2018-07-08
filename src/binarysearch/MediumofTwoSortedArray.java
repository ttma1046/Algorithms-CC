package binarysearch;

public class MediumofTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
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
