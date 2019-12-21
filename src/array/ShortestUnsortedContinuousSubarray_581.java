package array;

import java.util.Arrays;
import java.util.Stack;

public class ShortestUnsortedContinuousSubarray_581 {
    public int findUnsortedSubarrayFailed(int[] nums) {
        if (nums.length <= 0) { return 0; }
        if (nums.length == 1) { return 1; }

        int i = 0;
        int j = nums.length - 1;

        int right = 0;
        int left = nums.length - 1;

        while (i < j) {
            if (nums[i] > nums[i + 1])
                right = i;


            if (nums[j] < nums[j - 1])
                left = j;

            i++;
            j--;
        }

        return left - right + 1;
    }

    public int findUnsortedSubarrayBruteForce(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j <= nums.length; j++) {
                int min = Integer.MAX_VALUE,
                    max = Integer.MIN_VALUE,
                    prev = Integer.MIN_VALUE;

                for (int k = i; k < j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }

                if ((i > 0 && nums[i - 1] > min) || (j < nums.length && nums[j] < max)) {
                    continue;
                }

                int k = 0;

                while (k < i && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }

                if (k != i) {
                    continue;
                }

                k = j;

                while (k < nums.length && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }

                if (k == nums.length) {
                    res = Math.min(res, j - i);
                }
            }
        }
        return res;
    }

    public int findUnsortedSubarrayBetterBruteForce(int[] nums) {
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    public int findUnsortedSubarraySorting(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }

    public int findUnsortedSubarrayStack(int[] nums) {
        Stack< Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    public int findUnsortedSubarrayStackLessSpace(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    public int findUnsortedSubarrayII(int[] A) {
        int n = A.length,
                beg = -1,
                end = -2,
                min = A[n - 1],
                max = A[0];

        for (int i = 1; i < n; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n - 1 - i]);
            if (A[i] < max) end = i;
            if (A[n - 1 - i] > min) beg = n - 1 - i;
        }

        return end - beg + 1;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayBruteForce(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayBetterBruteForce(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarraySorting(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayStack(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayStackLessSpace(new int[] { 2, 6, 4, 8, 10, 9, 15 }));

        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayFailed(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
        System.out.println(new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarrayII(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
    }
}
