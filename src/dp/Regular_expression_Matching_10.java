package dp;
/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:

Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:

Input: s = "acdcb", p = "a*c?b"
Output: false


Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
*/
enum Result {
    TRUE,
    FALSE,
}

class Regular_Expression_Matching_10 {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    private Result[][] memo;

    public boolean isMatchII(String s, String p) {
        memo = new Result[s.length() + 1][p.length() + 1];

        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] != null) return memo[i][j] == Result.TRUE;

        if (i == s.length()) return j == p.length();

        boolean ans = false;

        boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
        } else {
            ans = firstMatch && dp(i + 1, j + 1, s, p);
        }

        memo[i][j] = ans ? Result.TRUE : Result.FALSE;

        return ans;
    }

    public static void main(String[] args) {
        Wildcard_Matching_44 obj = new Wildcard_Matching_44();
    }

    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    /*
    Time Complexity: Let T, PT,P be the lengths of the text and the pattern respectively. The work for every call to dp(i, j) for i=0, ... ,Ti=0,...,T; j=0, ... ,Pj=0,...,P is done once, and it is O(1)O(1) work. Hence, the time complexity is O(TP)O(TP).

    Space Complexity: The only memory we use is the O(TP)O(TP) boolean entries in our cache. Hence, the space complexity is O(TP)O(TP).
    */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配

        for (int i = 0; i < p.length(); i++) { // here's the p's length, not s's
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true; // here's y axis should be i+1
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                        /*
                        dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                        or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                        or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                         */

                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public boolean isMatchBest(String s, String p) {
        if (s == null || p == null) return false;

        int sLength = s.length();
        int pLength = p.length();

        boolean[][] dp = new boolean[sLength + 1][pLength + 1];

        dp[0][0] = true;

        for (int i = 1; i <= pLength; ++i)
            if (p.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 2];

        for (int i = 1; i <= sLength; ++i) {
            for (int j = 1; j <= pLength; ++j) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')  {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sLength][pLength];
    }
}