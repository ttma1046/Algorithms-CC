package tree;
/*
Given an integer n, return the number of structurally unique BST's

(binary search trees) which has exactly n nodes of unique values from 1 to n.

Example 1:

Input: n = 3
Output: 5

Example 2:

Input: n = 1
Output: 1

Constraints:

1 <= n <= 19
*/
class Unique_Binary_Search_Trees_96 {
    public int numTrees(int n) {
        int[] memo = new int[n + 1];
        return topToBottom(n, memo);
    }

    private int topToBottom(int n, int[] memo) {
        if (n == 0 || n == 1)
            return 1;

        if (memo[n] > 0)
            return memo[n];

        int sum = 0;

        for (int i = 1; i <= n; ++i)
            sum += topToBottom(i - 1, memo) * topToBottom(n - i, memo);

        memo[n] = sum;
        return memo[n];
    }

    public int numTreesBottomToTop(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i)
            for (int j = 1; j <= i; ++j)
                G[i] += G[j - 1] * G[i - j];

        return G[n];
    }

    public static void main(String[] args) {
        Unique_Binary_Search_Trees_96 obj = new Unique_Binary_Search_Trees_96();
    }
}