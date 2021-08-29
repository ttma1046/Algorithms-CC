package heap;
import java.util.PriorityQueue;
import java.util.Arrays;
/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 104
-104 <= nums[i] <= 104
*/
class Kth_Largest_Element_in_an_Array_215 {
    int [] nums;

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }
 
    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public int quickselect(int left, int right, int k_smallest) {
        /*
        Returns the k-th smallest element of list within left..right.
        */

        if (left == right) // If the list contains only one element,
            return this.nums[left];  // return that element

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index)
            return this.nums[k_smallest];
        // go left side
        else if (k_smallest < pivot_index)
            return quickselect(left, pivot_index - 1, k_smallest);
        // go right side
        return quickselect(pivot_index + 1, right, k_smallest);
    }

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }


    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestI(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
            new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }

    public int findKthLargestII(int[] nums, int k) {
        divide(nums, 0, nums.length - 1, k);
        return nums[nums.length - k];
    }

    private void divide(int[] nums, int left, int right, int k) {
        if (left >= right) return;
        int position = conquer(nums, left, right);

        if (position == nums.length - k) return;
        else if (position < nums.length - k)
            divide(nums, position + 1, right, k);
        else divide(nums, left, position - 1, k);
    }

    private int conquer(int[] nums, int left, int right) {
        int pivot = nums[right], wall = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, wall);
                wall++;
            }
        }

        swap(nums, wall, right);
        return wall;
    }

    private void swap(int[] nums, int i, int k) {
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }

    /*
    private void shuffle(int nums[]) {
        Random random = new Random();
        for (int i = 0; j < nums.length; i++) {
                int j = random.nextInt(i + 1); // [i, j);
                swap(nums, i, j);
        }
    }
    */

    // O(n) average case, O(n ^ 2) worst case

    public static void main(String[] args) {
        Kth_Largest_Element_in_an_Array_215 obj = new Kth_Largest_Element_in_an_Array_215();
        int[] test = new int[] {1, 1};

        obj.findKthLargestII(test, 1);
    }
}

