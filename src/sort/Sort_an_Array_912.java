package sort;
import java.util.List;
import java.util.ArrayList;
/*
Given an array of integers nums, sort the array in ascending order.

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]

Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]

Constraints:

1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
*/
class Sort_an_Array_912 {
    private static int[] temp;
    public static void main(String[] args) {
        Sort_an_Array_912 obj = new Sort_an_Array_912();
        int[] nums = new int[] { 15, 3, 9, 8, 5, 2, 7, 1, 6 };
        nums = new int[] {1, 2, 3, 5};
        obj.sortArray(nums);
    }

    public int[] sortArray(int[] nums) {
        int n = nums.length;

        sortArray(nums, 0, n - 1);

        return nums;
    }

    private void sortArray(int[] nums, int low, int high) {
        if (low >= high)
            return;

        int mid = low + (high - low) / 2;

        sortArray(nums, low, mid);
        sortArray(nums, mid + 1, high);

        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        for (int i = lo; i <= hi; i++)
            temp[i] = nums[i];

        int i = low,
            j = mid + 1;

        for (int p = low; p <= high; ++p) {
            if (i == mid + 1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == high + 1) {
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
    /*
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if(start >= end) return;
        int lo = start, hi = end;
        int pivot = nums[lo + ((hi - lo) >> 1)];
        while(lo <= hi) {
            while(lo <= hi && nums[lo] < pivot) {
                lo ++;
            }
            while(lo <= hi && nums[hi] > pivot) {
                hi --;
            }
            if(lo <= hi) {
                int tmp = nums[lo];
                nums[lo] = nums[hi];
                nums[hi] = tmp;
                lo ++;
                hi --;
            }
        }
        quickSort(nums, start, hi);
        quickSort(nums, lo, end);
    }
    */

    public int[] sortArray(int[] nums) {
        int n = nums.length;
        quick_sort(nums, 0, n - 1);
        return nums;
    }

    private void quick_sort(int[] q, int left, int right) {
        System.out.println("left:" + left);
        System.out.println("right:" + right);
        if (left >= right) return;

        int x = q[left], i = left - 1, j = right + 1;
        while(i < j) {
            do {
                i++;
            } while(q[i] < x);


            do {
                j--;
            } while(q[j] > x);

            if (i < j) swap(q, i, j);
        }

        quick_sort(q, left, j);
        quick_sort(q, j + 1, right);
    }

    private void swap(int[] q, int i, int j) {
        System.out.println("i:" + i);
        System.out.println("j:" + j);

        q[i] = q[i] + q[j];
        q[j] = q[i] - q[j];
        q[i] = q[i] - q[j];
    }

    /*
    public List<Integer> sortArray(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        quickSort(nums, 0, nums.length - 1);
        for (int i : nums) res.add(i);
        return res;
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = partition(nums, l, r);
        quickSort(nums, l, mid);

        quickSort(nums, mid + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= pivot)
                r--;

            nums[l] = nums[r];

            while (l < r && nums[l] <= pivot)
                l++;

            nums[r] = nums[l];
        }
        nums[l] = pivot;

        for (int i : nums) {
            System.out.print(i);
            System.out.print(",");
        }

        System.out.println();

        System.out.println("l:" + l);

        return l;
    }
    */

    /*
    15 3 9 8 5 2 7 1 6

    15
    15 3 9 8 5 2 7 1 6
    l                r

    6 3 9 8 5 2 7 1 6
    l               r
    */
}