package array;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class AddtoArrayFormofInteger {
    /*
        For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

        Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

        Example 1:

            Input: A = [1,2,0,0], K = 34
            Output: [1,2,3,4]
            Explanation: 1200 + 34 = 1234
        Example 2:

            Input: A = [2,7,4], K = 181
            Output: [4,5,5]
            Explanation: 274 + 181 = 455
        Example 3:

            Input: A = [2,1,5], K = 806
            Output: [1,0,2,1]
            Explanation: 215 + 806 = 1021
        Example 4:

            Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
            Output: [1,0,0,0,0,0,0,0,0,0,0]
            Explanation: 9999999999 + 1 = 10000000000

        Note：
            1 <= A.length <= 10000
            0 <= A[i] <= 9
            0 <= K <= 10000
            If A.length > 1, then A[0] != 0
     */
    public int[] addToArrayForm(int[] A, int K) {
        if (A == null) return null;
        int reminder = K;
        int[] result = new int[A.length];
        int[] resultTwo = new int[A.length + 1];

        for (int index = A.length - 1; index >= 0; index--) {
            reminder = (A[index] + reminder);
            result[index] = reminder % 10;
            reminder /= 10;

            if (index == 0 && reminder > 0) {
                int length = A.length;

                // while(length-- > 0)
                while (--length >= 0) {
                    resultTwo[length + 1] = result[length];
                }
                resultTwo[0] = reminder;
                return resultTwo;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = new AddtoArrayFormofInteger().addToArrayForm(new int[] { 2,5,1 }, 806);
        for(int num: result) {
            System.out.println(num);
        }
        // result = new AddtoArrayFormofInteger_989().addToArrayForm(new int[] { 9,9,9,9,9,9,9,9,9,9 }, 1);
        for(int num: result) {
            System.out.println(num);
        }
    }

    /*
        Approach 1: Schoolbook Addition
        Intuition

        Let's add numbers in a schoolbook way, column by column. For example, to add 123 and 912, we add 3+2, then 2+1, then 1+9. Whenever our addition result is more than 10, we carry the 1 into the next column. The result is 1035.

        Algorithm

        We can do a variant of the above idea that is easier to implement - we put the entire addend in the first column from the right.

        Continuing the example of 123 + 912, we start with [1, 2, 3+912]. Then we perform the addition 3+912, leaving 915. The 5 stays as the digit, while we 'carry' 910 into the next column which becomes 91.

        We repeat this process with [1, 2+91, 5]. We have 93, where 3 stays and 90 is carried over as 9. Again, we have [1+9, 3, 5] which transforms into [1, 0, 3, 5].

        Complexity Analysis

            Time Complexity: O(\max(N, \log K))O(max(N,logK)) where NN is the length of A.

            Space Complexity: O(\max(N, \log K))O(max(N,logK)).
     */

    public List<Integer> addToArrayFormII(int[] A, int K) {
        int i = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList<Integer>();

        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);

        return ans;
    }

    public List<Integer> addToArrayFormIII(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 1; i >= 0 || K > 0; --i) {
            res.add(0, (i >= 0 ? A[i] + K : K) % 10);
            K = (i >= 0 ? A[i] + K : K) / 10;
        }
        return res;
    }

    /*
    public List<Integer> addToArrayFormIV(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        for (int i = A.length - 1; i >= 0; --i) {
            res.add(0, (A[i] + K) % 10);
            K = (A[i] + K) / 10;
        }
        while (K > 0) {
            res.add(0, K % 10);
            K /= 10;
        }
        return res;
    }
     */
}
