package stack;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;
/*
Given an array of integers arr,

find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
Since the answer may be large, return the answer modulo 109 + 7.

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.

Example 2:

Input: arr = [11,81,94,43,3]
Output: 444

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
*/
public class Sum_of_Subarray_Minimums_907 {
    /*
    vector<int> previous_less(A.size(), -1);
    for(int i = 0; i < A.size(); i++){
      while(!in_stk.empty() && A[in_stk.top()] > A[i]){
        in_stk.pop();
      }
      previous_less[i] = in_stk.empty()? -1: in_stk.top();
      in_stk.push(i);
    }
    */

    public int[] letsgetpreviouslessequal(int[] a) {
        int[] previous_less = new int[a.length];
        for (int i = 0; i < a.length; i++)
            previous_less[i] = -1;

        Deque<Integer> monoStack = new ArrayDeque<>();

        for (int i = 0; i < a.length; i++) {
            while(monoStack.size() > 0 && a[monoStack.peek()] > a[i])
                monoStack.pop();

            previous_less[i] = monoStack.size() == 0 ? -1 : monoStack.peek();
            monoStack.push(i);
        }

        return previous_less;
    }

    public static void main(String[] args) {
        Sum_of_Subarray_Minimums_907 obj = new Sum_of_Subarray_Minimums_907();

        int[] array = new int[] { 3, 7, 7, 8, 4 };
        int[] res = obj.letsgetpreviouslessequal(array);

        // array = new int[] {3, 1, 2, 4};
        // System.out.println(obj.sumSubarrayMins(array));
        System.out.println(obj.sumSubarrayMinsII(array));
    }

    /*
    public int sumSubarrayMinsIII(int[] arr) {
        int n = arr.length;
        int[] leftLess = new int[length];
        int[] rightLess = new int[length];
        // 2 6 7 5
        // 1 2 3 4
        Deque<Integer> stack = new ArrayDeque<Integer>();

        for (int i = 0; i < n; ++i) {
            while(stack.size() > 0 && stack.peek().value >= arr[i])
                stack.pop();

            leftLess[i] = stack.size() > 0 ? i - stack.peek().index : i + 1;

            stack.push(new Pair(arr[i], i));
        }

        return 0;
    }
    */

    public int sumSubarrayMinsII(int[] arr) {
        int n = arr.length;

        Deque<Pair> stack = new ArrayDeque<>();

        int[] leftLess = new int[n];
        int[] rightLess = new int[n];

        for (int i = 0; i < n; ++i) {
            while(stack.size() > 0 && arr[i] <= stack.peek().value)
                stack.pop();

            leftLess[i] = stack.size() == 0 ? i + 1 : i - stack.peek().index;
            stack.push(new Pair(arr[i], i));
        }

        for (int i = 0; i < leftLess.length; ++i) {
            System.out.print(leftLess[i]);
            System.out.print(",");
        }

        System.out.println();

        stack.clear();

        for (int i = n - 1; i >= 0; --i) {
            while (stack.size() > 0 && arr[i] < stack.peek().value)
                stack.pop();

            rightLess[i] = stack.size() == 0 ? n - i : stack.peek().index - i;
            stack.push(new Pair(arr[i], i));
        }

        for (int i = 0; i < rightLess.length; ++i) {
            System.out.print(rightLess[i]);
            System.out.print(",");
        }

        System.out.println();

        long MOD = (long)1000000007;
        long res = 0;
        for (int i = 0; i < n; ++i)
            res = (res + (long)leftLess[i] * rightLess[i] * arr[i]) % MOD;

        return (int)res;
    }

    public int sumSubarrayMins(int[] A) {
        // initialize previous less element and next less element of
        // each element in the array
        // initialize previous less element and next less element of
        // each element in the array

        Stack<int[]> previousLess = new Stack<>();
        Stack<int[]> nextLess = new Stack<>();
        int[] leftDistance = new int[A.length];
        int[] rightDistance = new int[A.length];

        for(int i = 0; i < A.length; i++) {
            // use ">=" to deal with duplicate elements
            while(!previousLess.isEmpty() && previousLess.peek()[0] >= A[i])
                previousLess.pop();

            leftDistance[i] = previousLess.isEmpty() ? i + 1 : i - previousLess.peek()[1];
            previousLess.push(new int[] {A[i], i});
        }

        for(int i = A.length - 1; i >= 0; i--) {
            while(!nextLess.isEmpty() && nextLess.peek()[0] > A[i])
                nextLess.pop();

            rightDistance[i] = nextLess.isEmpty() ? A.length - i : nextLess.peek()[1] - i;
            nextLess.push(new int[] {A[i], i});
        }

        long MOD = (long)1000000007;
        long res = 0;
        for(int i = 0; i < A.length; i++)
            res = (res + (long)A[i] * leftDistance[i] * rightDistance[i]) % MOD;

        return (int)res;
    }
}

class Pair {
    int value;
    int index;
    public Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }
}
