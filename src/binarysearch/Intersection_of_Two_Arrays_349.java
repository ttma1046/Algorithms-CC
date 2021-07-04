package binarysearch;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
*/
public class Intersection_of_Two_Arrays_349 {

    /*
    This is a Facebook interview question.
    They ask for the intersection, which has a trivial solution using a hash or a set.

    Then they ask you to solve it under these constraints:
    O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
    You are told the lists are sorted.

    Cases to take into consideration include:
    duplicates, negative values, single value lists, 0's, and empty list arguments.
    Other considerations might include sparse arrays.
    */

    /*
    function intersections(l1, l2) {
        l1.sort((a, b) => a - b) // assume sorted
        l2.sort((a, b) => a - b) // assume sorted
        const intersections = []
                              let l = 0, r = 0;
        while ((l2[l] && l1[r]) != = undefined) {
            const left = l1[r], right = l2[l];
            if (right == = left) {
                intersections.push(right)
                while (left == = l1[r]) r++;
                while (right == = l2[l]) l++;
                continue;
            }
            if (right > left) while (left == = l1[r]) r++;
            else while (right == = l2[l]) l++;

        }
        return intersections;
    }
    */
    public int[] intersectionSet(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }

    public int[] intersectionSetII(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    public int[] intersectionBS(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);

        for (int i : nums1) if (binarysearch(nums2, i)) set.add(i);

        int i = 0;
        int[] result = new int[set.size()];
        for (int num : set) result[i++] = num;
        return result;
    }

    public boolean binarysearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = -1;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] == target) return true;
            else if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        /*
        459
        44899
        */
        int[] intersections = new int[nums1.length];
        int resultIndex = 0;

        int l = 0, r = 0;
        while (l < nums1.length && r < nums2.length) {
            int left = nums1[l], right = nums2[r];

            if (right == left) {
                intersections[resultIndex++] = right;

                while (l < nums1.length && left == nums1[l]) l++;
                while (r < nums2.length && right == nums2[r]) r++;
                continue;
            }

            if (right > left) while (l < nums1.length && left == nums1[l]) l++;
            else while (r < nums2.length && right == nums2[r]) r++;
        }

        return Arrays.copyOf(intersections, resultIndex);
    }

    public int[] intersectionQuick(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList<>();
        boolean[] exist1 = new boolean[1001];
        boolean[] exist2 = new boolean[1001];
        for (int num : nums1) exist1[num] = true;
        for (int num : nums2) exist2[num] = true;
        
        for (int num = 0; num <= 1000; num++) if (exist1[num] && exist2[num]) ans.add(num);
        
        int[] res = new int[ans.size()];
        
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        
        return res;
    }

    public static void main(String[] args) {
        Intersection_of_Two_Arrays_349 obj = new Intersection_of_Two_Arrays_349();
        int[] nums1 = new int[] {4, 9, 5};
        int[] nums2 = new int[] {9,4,9,8,4};
        int[] res = obj.intersectionQuick(nums1, nums2);
        for (int i: res) System.out.println(i);
    }
}