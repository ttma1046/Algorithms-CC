package heap;
/*
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

What is heap? What is heapify? What if there is a lot of solutions?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
Return any of them.
Example
Example 1

Input : [3,2,1,4,5]
Output : [1,2,3,4,5]
Explanation : return any one of the legitimate heap arrays
*/
public class Heapify_Lintcode_130 {
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    /*
    public void heapify(int[] A) {
        // write your code here
        return Arrays.sort(A);
    }
    */

    public static void main(String[] args) {
        Heapify_Lintcode_130 obj = new Heapify_Lintcode_130();
        int[] abc = new int[] {6, 3, 1, 8, 9, 5};

        obj.sort(abc);
    }

    /*
    这个版本的算法，乍一看也是O(nlogn),
    但是我们仔细分析一下, 算法从第 n/2个数开始，倒过来进行 shiftdown.

    也就是说，相当于从heap的倒数第二层开始进行 shiftdown 操作，
    倒数第二层的节点大约 n/4 个数开始，
    这 n/4 个数，最多shiftdown 1次就到底了，
    所以这一层的时间复杂度耗费是O(n/4), 然后倒数
    第三层差不多 n/8个点，最多shiftdown 2次就到底了。
    所以这里的耗费是O(n/8 * 2), 倒数第4层是
    O(n/16 * 3), 倒数第5层是O(n/32 * 4)....

    因此累加所有的时间复杂度耗费为

    T(n) = O(n/4) + O(n/8 * 2) + O(n/16 * 3) ....

    然后我们用2T - T得到：

    2 * T(n) = O(n/2) + O(n/4 * 2) + O(n/8 * 3) + O(n/16 * 4)....
        T(n) =          O(n/4)     + O(n/8 * 2) + O(n/16 * 3)....

    2 * T(n) - T(n) = O(n/2) + O(n/4) + O(n/8) + ....
                    = O(n/2 + n/4 + n/8 + ...)
                    = O(n)

    因此得到 T(n) = 2 * T(n) - T(n) = O(n)
    */
    void buildHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; --i)
            heapify(nums, nums.length, i);
    }

    void heapify(int arr[], int total, int currentIndex) {
        int largestIndex = currentIndex;

        int leftNodeIndex = 2 * currentIndex + 1;
        int rightNodeIndex = 2 * currentIndex + 2;

        if (leftNodeIndex < total && arr[leftNodeIndex] > arr[largestIndex]) largestIndex = leftNodeIndex;
        if (rightNodeIndex < total && arr[rightNodeIndex] > arr[largestIndex]) largestIndex = rightNodeIndex;

        if (largestIndex != currentIndex) {
            int temp = arr[currentIndex];
            arr[currentIndex] = arr[largestIndex];
            arr[largestIndex] = temp;

            heapify(arr, total, largestIndex);
        }
    }

    public void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        /*
        for (int i = 0; i < arr.length; i++) 
            System.out.print(arr[i] + ",");
        System.out.println();
        */

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            /*
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + ",");
            }
            System.out.println();
            */

            heapify(arr, i, 0);
        }
    }

    /*
    1. Build a max heap fro the input data.

    2. At this point, the largest item is stored at the root of the heap.
    Replace it with the last item of the heap followed 
    by reducing the size of the heap by 1.

    Finally, heapify the root of the tree.

    3.Repeat above steps while size of heap is greater than 1.
    */

}