package twopointers;
import java.util.Deque;
import java.util.ArrayDeque;
/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9

Sliding window with Deque

209 Minimum Size Subarray Sum
*/
class Shortest_Subarray_with_Sum_at_Least_K_862 {
	public int shortestSubarray(int[] A, int K) {
		int n = A.length, res = n + 1;
		int[] sums = new int[n + 1];
		
		for (int i = 0; i < n; i++) { 
			sums[i + 1] = sums[i] + A[i];
		};
		
		Deque<Integer> d = new ArrayDeque<>();

		for (int i = 0; i < n + 1; i++) {
			while (d.size() > 0 && sums[i] - sums[d.getFirst()] >=  K) {
				res = Math.min(res, i - d.pollFirst());
			}
						
			while (d.size() > 0 && sums[i] <= sums[d.getLast()]) {
				d.pollLast();
			}

			d.addLast(i);
		}

		return res <= n ? res : -1;
	}

    public int shortestSubarray(int[] A, int K) {
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i-1];
        }
   
        int[] deque = new int[A.length];
        int head = 0;
        int tail = 0;
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < A.length; i++) {
            if(A[i] >= K && i + 1 < result){
                result = i + 1;
            }

            while(tail > head && A[i] < A[deque[tail-1]]) {
                tail--;
            }
            while(tail > head && A[i] - A[deque[head]] >= K) {
                result = Math.min(result, i - deque[head]);
                head++;
            }

            deque[tail++] = i;
        }

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

	public static void main(String[] args) {
		System.out.println(new Shortest_Subarray_with_Sum_at_Least_K_862().shortestSubarray(new int[] { 2, 3, 1, 2, 4, 3 }, 7));
		System.out.println(new Shortest_Subarray_with_Sum_at_Least_K_862().shortestSubarray(new int[] { 2, -1, 2 }, 3));
	}
}