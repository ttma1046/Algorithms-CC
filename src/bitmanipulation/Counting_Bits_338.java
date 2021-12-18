package bitmanipulation;
/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

Constraints:

0 <= n <= 105

Follow up:

It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
*/

/*
 000   001
 010   011
 100   101
 110   111
1000  1001
*/
class Counting_Bits_388 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int x = 1; x <= n; ++x)
            // x / 2 is x >> 1 and x % 2 is x & 1
            ans[x] = ans[x / 2] + (x % 2);

        return ans;
    }

    /*
    Time complexity: O(n)O(n). For each integer xx, in the range 11 to nn, we need to perform a constant number of operations which does not depend on the number of bits in xx.

    Space complexity: O(1)O(1). Since the output array does not count towards the space complexity.
    */

    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int x = 1; x <= num; ++x) {
            ans[x] = ans[x & (x - 1)] + 1;
        }
        return ans;
    }

    /*
    Time complexity: O(n)O(n). For each integer xx, in the range 11 to nn, we need to perform a constant number of operations which does not depend on the number of bits in xx.

    Space complexity: O(1)O(1). Since the output array does not count towards the space complexity.
    */

}





