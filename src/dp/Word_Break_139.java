package dp;
import java.util.List;
import java.util.Arrays;
/*
Given a non-empty string `s` and
a dictionary `wordDict` containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/
class Word_Break_139 {
    public boolean wordBreakBruteForce(String s, List<String> wordDict) {
        return word_BreakBruteForce(s, new HashSet(wordDict), 0);
    }

    public boolean wordBreakBruteForce(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakBruteForce(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

    /*
    n is the length of the input string.

    Time complexity : O(2^n).

    Given a string of length n, there are n + 1 ways to split it into two parts.

    At each step, we have a choice: to split or not to split. In the worse case, when all choices are to be checked, that results in O(2^n).

    Space complexity : O(n). The depth of the recursion tree can go upto n.
    */

    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    }

    /*
    Time complexity : O(n^3). Size of recursion tree can go up to n^2.

    Space complexity : O(n). The depth of recursion tree can go up to n.
    */

    public boolean wordBreakDP(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[wordDict.length() + 1];
        dp[0] = true;

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean wordBreakII(String s, List<String> wordDict) {
        return wordBreakII(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean wordBreakII(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakII(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    public boolean wordBreakIV(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Word_Break_139().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new Word_Break_139().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(new Word_Break_139().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));

    }
}