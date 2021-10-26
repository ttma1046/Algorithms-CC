package backtracking;
import java.util.List;
import java.util.ArrayList;
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
*/
class Palindrome_Partitioning_131 {
    /*
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<String>(), s.toCharArray(), 0);
        return res;
    }

    void dfs(List<List<String>> res, ArrayList<String> list, char[] c, int pos) {
        if (pos == c.length) res.add(new ArrayList<>(list));

        for (int i = pos; i < c.length; i++) {
            if (isPal(c, pos, i)) {
                list.add(new String(c, pos, i - pos + 1));
                dfs(res, list, c, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    boolean isPal(char[] c, int lo, int hi) {
        while (lo < hi) if (c[lo++] != c[hi--]) return false;
        return true;
    }



    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    public List<List<String>> partitionII(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<List<String>>();
        boolean[][] dp = new boolean[len][len];
        dfs(0, s, res, new ArrayList<>(), dp);
        return res;
    }

    void dfs(int start, String s, List<List<String>> result, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));

        for(int end = start; end < s.length(); ++end) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;

                currentList.add(s.substring(start, end + 1));

                dfs(end + 1, s, result, currentList, dp);

                currentList.remove(currentList.size() - 1);
            }
        }
    }
    */

    /*
    Complexity Analysis:

    Time Complexity : O(N⋅2^N), where N is the length of string s. In the worst case, there could be 2^N possible substrings and it will take O(N) to generate each substring using substr as in Approach 1.

    However, we are eliminating one additional iteration to check if substring is a palindrome or not.

    Space Complexity: O(N⋅N), where N is the length of the string s. The recursive call stack would require N space as in Approach 1. Additionally we also use 2 dimensional array dp of size N⋅N.

    This gives us total space complexity as O(N⋅N) + O(N) = O(N⋅N)
    */

    public static void main(String[] args) {
        Palindrome_Partitioning_131 obj = new Palindrome_Partitioning_131();
        List<List<String>> res = obj.partition("aab");

        for (List<String> item : res) {
            for (String s : item) {
                System.out.print(s);
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();

        recursive(s, 0, res, new ArrayList<String>());

        return res;
    }

    private void recursive(String s, int start, List<List<String>> res, List<String> temp) {
        if (start >= s.length()) res.add(new ArrayList<String>(temp));

        for (int end = start; end < s.length(); ++end) {
            if (isPalindrome(s, start, end)) {
                temp.add(s.substring(start, end + 1));
                recursive(s, end + 1, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) if (s.charAt(start++) != s.charAt(end--)) return false;
        return true;
    }

    /*
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(s, 0, res, new ArrayList<String>());
        return res;
    }

    private void dfs(String s, int start, List<List<String>> res, List<String> currentList) {
        if (start >= s.length()) res.add(new ArrayList<String>(currentList));

        for (int end = start; end < s.length(); ++end) {
            if (isPalindrome(s, start, end)) {
                currentList.add(s.substring(start, end + 1));
                dfs(s, end + 1, res, currentList);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
    */
}



























