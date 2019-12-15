package array;
/*
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

        You may return any answer array that satisfies this condition.



        Example 1:

        Input: [3,1,2,4]
        Output: [2,4,3,1]
        The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.


        Note:

        1 <= A.length <= 5000
        0 <= A[i] <= 5000
*/

class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int length = A.length;

        for(int i = 0, j = 1; i < length && j < length; i++, j++) {
            if (A[i] % 2 == 1 && i < length - 1) {
                while (j < length) {
                    if (A[j] % 2 == 0) {
                        int tmp = A[i];
                        A[i] = A[j];
                        A[j] = tmp;
                        break;
                    } else {
                        j++;
                    }
                }
            }
        }
        return A;
    }
}