package twopointers;

/*
In an array A of 0s and 1s, how many non-empty subarrays have sum S?

Example 1:

Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[[1,0,1,] 0, 1]
[[1,0,1,0,] 1]
[1, [0,1,0,1]]
[1,0, [1,0,1]]
 

Note:

At most S
 1     0       1        0            1
[1]   [0]     [1]      [0]          [1]
    [1 0]   [0 1]    [1 0]        [0,1]
          [1 0 1]  [0 1 0]      [1,0,1]
                 [1 0 1 0]    [0,1,0,1]

At most S - 1
 1     0       1        0            1
[1]   [0]     [1]      [0]          [1]
    [1 0]   [0 1]    [1 0]        [0,1]
                   [0 1 0]

A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.

sliding window
*/
class Binary_Subarrays_with_Sum_930 {
    public int numSubarraysWithSum(int[] A, int S) {
    	return atMost(A, S) - atMost(A, S - 1);
    }

    private int atMost(int[] nums, int S) {
    	if (S < 0) return 0;

    	int i = 0, j = 0, res = 0, n = nums.length;

    	for (j = 0; j < n; j++) {
    		S -= nums[j];
    		while(S < 0) S += nums[i++];
    		res += j - i + 1;
    	}

    	return res;
    }

    public static void main(String[] args) {
        System.out.println(new Binary_Subarrays_with_Sum_930().numSubarraysWithSum(new int[]{1,0,1,0,1}, 2));
    }
}