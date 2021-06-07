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
    public double findMedianSortedArraysI(int[] nums1, int[] nums2) {
        // merge two sorted array
        int length = nums1.length + nums2.length;

        int[] mergedArray = new int[length];
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int z = length - 1;
        while (i >= 0 && j >= 0 && z >= (length / 2 - 1)) {
            mergedArray[z--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }

        while (i >= 0 && z >= (length / 2 - 1)) {
            mergedArray[z--] = nums1[i--];
        }

        while (j >= 0 && z >= (length / 2 - 1)) {
            mergedArray[z--] = nums2[j--];
        }

        if (length % 2 == 1) {
            return mergedArray[length / 2];
        } else {
            return (double)((mergedArray[length / 2] + mergedArray[length / 2 - 1]) / (double)2);
        }
    }

    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;
        int mergedLength = x + y;
        int halfLen = (mergedLength) / 2 + 1;
        int[] mergedArray = new int[halfLen];

        int i = 0;
        int j = 0;
        int z = 0;
        while (z < halfLen && i < x && j < y) {
            mergedArray[z++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        while (z < halfLen && i < x) {
            mergedArray[z++] = nums1[i++];
        }

        while (z < halfLen && j < y) {
            mergedArray[z++] = nums2[j++];
        }

        return mergedLength % 2 == 0 ? (double)(mergedArray[halfLen - 1] + mergedArray[halfLen - 2]) / 2 : mergedArray[halfLen - 1];
    }

    public static void main(String[] args) {
        MedianofTwoSortedArray_4 obj = new MedianofTwoSortedArray_4();
        System.out.println(obj.findMedianSortedArraysII(new int[] {1, 3}, new int[] {2}));
        System.out.println(obj.findMedianSortedArraysII(new int[] {2}, new int[] {1, 3}));
        System.out.println(obj.findMedianSortedArraysII(new int[] {1, 2}, new int[] {3, 4}));
        System.out.println(obj.findMedianSortedArraysII(new int[] {3, 4}, new int[] {1, 2}));
    }

    /*
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;

        int halfLen = (x + y + 1) / 2;

        while(low <= high) {
            int partitionX = low + (high - low) / 2;
            int partitionY = halfLen - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftY <= minRightX && maxLeftX <= minRightY) return (x + y) % 2 == 0 ? (Math.max(maxLeftY, maxLeftX) + Math.min(minRightY, minRightX)) / 2.0 : (double)Math.max(maxLeftY, maxLeftX);
            else if (maxLeftY > minRightX) low = partitionX + 1;
            else high = partitionX - 1;
        }

        return 0.0;
    }
    */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;

        int halfLen = (x + y + 1) / 2;

        while (low <= high) {
            int partitionX = low + (high - low) / 2;
            int partitionY = halfLen - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftY <= minRightX && maxLeftX <= minRightY) return (x + y) % 2 == 0 ? (double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2 : (double)Math.max(maxLeftX, maxLeftY);
            else if (maxLeftX > minRightY) high = partitionX - 1;
            else low = partitionX + 1;
        }

        return 0.0;
    }

    public double findMedianSortedArraysIII(int[] nums1, int[] nums2) {
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

        int left = 0, right = smallLength, halfLen = (smallLength + largeLength + 1) / 2;

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