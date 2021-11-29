package string;
/*
Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.

Example 2:

Input: s = "abbababa", k = 1
Output: true

Constraints:

1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
*/
class Valid_Palindrome_III_125 {
    public boolean isValidPalindrome(String s, int k) {
        memo = new Integer[s.length()][s.length()];

        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return isValidPalindrome(s, 0, s.length() - 1) <= k;
    }

    Integer memo[][];

    int isValidPalindrome(String s, int i, int j) {

        // Base case, only 1 letter remaining.
        if (i == j) return 0;

        // Base case 2, only 2 letters remaining.
        if (i == j - 1) return s.charAt(i) != s.charAt(j) ? 1 : 0;

        //Return the precomputed value if exists.
        if (memo[i][j] != null) return memo[i][j];

        // Case 1: Character at `i` equals character at `j`
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = isValidPalindrome(s, i + 1, j - 1);
            return memo[i][j];
        }

        // Case 2: Character at `i` does not equal character at `j`.
        // Either delete character at `i` or delete character at `j`
        // and try to match the two pointers using recursion.
        // We need to take the minimum of the two results and add 1
        // representing the cost of deletion.
        memo[i][j] = 1 + Math.min(isValidPalindrome(s, i + 1, j), isValidPalindrome(s, i, j - 1));
        return memo[i][j];
    }

    /*
    Complexity Analysis

    Time complexity : O(n^2). Where n is the length of string s.

    This is due to the fact that we try to find result for all combinations of i and j where i and j range from 0 to n,

    to compute a combination we perform an O(1)O(1) operation thus the final complexity is O(n^2).

    Space complexity : O(n^2). Where n is the length of string s.

    This is due to caching all the results in memo table. The recursion stack depth can reach at max O(n) depth. Thus the complexity is dominated by the space required for memo.
    */


    public boolean isValidPalindrome(String s, int k) {
        int memo[][] = new int[s.length()][s.length()];

        // Generate all combinations of `i` and `j` in the correct order.
        for (int i = s.length() - 2; i >= 0; i--)
            for (int j = i + 1; j < s.length(); j++) {
                // Case 1: Character at `i` equals character at `j`
                if (s.charAt(i) == s.charAt(j))
                    memo[i][j] = memo[i + 1][j - 1];

                // Case 2: Character at `i` does not equal character at `j`.
                // Either delete character at `i` or delete character at `j`
                // and try to match the two pointers using recursion.
                // We need to take the minimum of the two results and add 1
                // representing the cost of deletion.
                else
                    memo[i][j] = 1 + Math.min(memo[i + 1][j], memo[i][j - 1]);
            }

        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return memo[0][s.length() - 1] <= k;
    }

    /*
    Time complexity : O(n^2). Where n is the length of string s. This is due to the fact that we try to find result for all combinations of i and j where i and j range from 0 to n, to compute a combination we perform an O(1)O(1) operation thus the final complexity is O(n^2).

    Space complexity : O(n^2). Where n is the length of string s. This is due to the memo table which is completely filled in this case.
    */

    public boolean isValidPalindrome(String s, int k) {
        int memo[] = new int[s.length()];

        // To store the previous required values from memo.
        int temp, prev;

        // Generate all combinations of `i` and `j` in the correct order.
        for (int i = s.length() - 2; i >= 0; i--) {
            // 'prev' stores the value at memo[i+1][j-1];
            prev = 0;
            for (int j = i + 1; j < s.length(); j++) {
                // Store the value of memo[i+1][j] temporarily.
                temp = memo[j];

                // Case 1: Character at `i` equals character at `j`
                if (s.charAt(i) == s.charAt(j))
                    memo[j] = prev;

                // Case 2: Character at `i` does not equal character at `j`.
                // Either delete character at `i` or delete character at `j`
                // and try to match the two pointers using recursion.
                // We need to take the minimum of the two results and add 1
                // representing the cost of deletion.
                else

                    // memo[j] will contain the value for memo[i+1][j]
                    // memo[j-1] will contain the value for memo[i][j-1]
                    memo[j] = 1 + Math.min(memo[j], memo[j - 1]);

                // Copy the value of memo[i+1][j] to `prev`
                // For the next iteration when j=j+1
                // `prev` will hold the value memo[i+1][j-1];
                prev = temp;
            }
        }

        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return memo[s.length() - 1] <= k;
    }

    public boolean isValidPalindrome(String s, int k) {
        char chArr[] = s.toCharArray();
        int n = chArr.length;
        int max = 0;
        int dp[] = new int[n];

        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            max = 0;

            for(int j = i - 1; j >= 0; j--) {
                int prev = dp[j];

                if(chArr[i] == chArr[j]) dp[j] = max + 2;
                
                max = Math.max(max, prev);
            }
        }

        for(int elem : dp) max = Math.max(elem, max);
        return n - max <= k;
    }

};