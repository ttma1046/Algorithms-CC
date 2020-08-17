package binarysearch;

public class MedianofTwoSortedArray_4 {
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
    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        // merge two sorted array
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

    public static void main(String[] args) {
        System.out.println(new MediumofTwoSortedArray_4().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println(new MedianofTwoSortedArray_4().findMedianSortedArrays(new int[] {2}, new int[] {1, 3}));
        System.out.println(new MedianofTwoSortedArray_4().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
        System.out.println(new MedianofTwoSortedArray_4().findMedianSortedArrays(new int[] {3, 4}, new int[] {1, 2}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int smallLength = nums1.length;
        int largeLength = nums2.length;

        if (largeLength < smallLength) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            largeLength = smallLength - largeLength;
            smallLength = smallLength - largeLength;
            largeLength = smallLength + largeLength;
        }

        int left = 0, right = smallLength, halfLen = (smallLength + largeLength + 1)/2;

        while (left <= right) {
            int smallIndex = left + (right - left) / 2;
            int largeIndex = halfLen - smallIndex;

            if (smallIndex < right && nums2[largeIndex - 1] > nums1[smallIndex]) {
                left = smallIndex + 1;
            } else if (smallIndex > left && nums1[smallIndex - 1] > nums2[largeIndex]) {
                right = smallIndex - 1;
            } else {
                int maxLeft = 0;

                if (smallIndex == 0) {
                    maxLeft = nums2[largeIndex - 1];
                } else if (largeIndex == 0) {
                    maxLeft = nums1[smallIndex - 1];
                } else {
                    maxLeft = Math.max(nums1[smallIndex - 1], nums2[largeIndex - 1]);
                }

                if ((smallLength + largeLength) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;

                if (smallIndex == smallLength) {
                    minRight = nums2[largeIndex];
                } else if (largeIndex == largeLength) {
                    minRight = nums1[smallIndex];
                } else {
                    minRight = Math.min(nums1[smallIndex], nums2[largeIndex]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }
}