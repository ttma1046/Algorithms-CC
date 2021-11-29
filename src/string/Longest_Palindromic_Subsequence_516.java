package string;
/*
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
*/
public class Longest_Palindromic_Subsequence_516 {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        dp[s.length() - 1][s.length() - 1] = 1;

        for (int i = s.length() - 2; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        Longest_Palindromic_Subsequence_516 obj = new Longest_Palindromic_Subsequence_516();
        obj.longestPalindromeSubseq("aaa");
    }

    public int longestPalindromeSubseqII(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) return memo[i][j];

        if (i > j)      return 0;
        if (i == j)     return 1;

        if (s.charAt(i) == s.charAt(j)) memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        else memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        return memo[i][j];
    }
}