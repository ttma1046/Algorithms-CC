package math;
import java.util.Arrays;
import java.util.Collections;
/*
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.

 

Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.
 

Constraints:

0 <= num <= 108
*/

class Maximum_Swap_670 {
	public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] lastIndex = new int[10];
        for (int i = 0; i < A.length; i++) {
            lastIndex[A[i] - '0'] = i;
        }

		/*
        	 ['9', '1', '9', '3']
               0 1 2 3 4 5 6 7 8 9
        last [ 0 1 0 3 0 0 0 0 0 2 ]
        */
        
        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (lastIndex[d] > i) {
                    char tmp = A[i];
                    A[i] = A[lastIndex[d]];
                    A[lastIndex[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }

        return num;
    }

    public int maximumSwapMy(int num) {
    	char[] charArr = String.valueOf(num).toCharArray();

    	int[] lastIndex = new int[10];

    	for(int i = 0; i < charArr.length; ++i) {
    		lastIndex[charArr[i] - '0'] = i;
    	}

    	for (int i = 0; i < charArr.length; ++i) {
    		for (int j = 9; j > charArr[i] - '0'; --j) {
    			if (lastIndex[j] > i) {
    				char temp = charArr[i];

    				charArr[i] = charArr[lastIndex[j]];

    				charArr[lastIndex[j]] = temp;

    				return Integer.valueOf(new String(charArr));
    			}
    		}
    	}

    	return num;
    }

    public static void main(String[] args) {
    	System.out.println(new Maximum_Swap_670().maximumSwapMy(2736));

    	System.out.println(new Maximum_Swap_670().maximumSwapMy(703));

    	System.out.println(new Maximum_Swap_670().maximumSwapMy(9193));
    }
}