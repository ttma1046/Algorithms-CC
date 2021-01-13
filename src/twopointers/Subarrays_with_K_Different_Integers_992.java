package twopointers;
import java.util.Map;
import java.util.HashMap;

/*
Given an array A of positive integers, 

call a (contiguous, not necessarily distinct) subarray of A good 

if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
*/

class Subarrays_with_K_Different_Integers_992 {
    public int subarraysWithKDistinct(int[] nums, int K) {
    	return atMostK(nums, K) - atMostK(nums, K - 1);
    }

    private int atMostK(int[] nums, int K) {
    	int result = 0, i = 0;
    	// Map<Integer, Integer> map = new HashMap<>();

    	int[] map = new int[nums.length];


    	for (int j = 0; j < nums.length; j++) {
    		if (map[nums[j] - 1] == 0) K--;
    		map[nums[j] - 1] += 1;

    		while(K < 0) {
    			map[nums[i] - 1] -= 1;

    			if (map[nums[i] - 1] == 0) K++;

    			i++;
    		}

    		result += j - i + 1; 
    	}

    	return result;
    }

    public static void main(String[] args) {
    	System.out.println(new Subarrays_with_K_Different_Integers_992().subarraysWithKDistinct(new int[] {1, 2, 1, 2, 3}, 2));
    }
}