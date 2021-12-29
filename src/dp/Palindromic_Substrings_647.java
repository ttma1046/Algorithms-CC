package dp;

/*

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:

The input string length won't exceed 1000.
*/
class Palindromic_Substrings_647 {
    public int countSubstringsI(String s) {
        char[] data = s.toCharArray();
        int n = data.length;
        int count = 0;

        int central = 0;
        while (central < n) {
            char cc = data[central];
            int old = central;
            while (central < n && data[central] == cc) {
                central++;
            }
            int len = central - old;
            count += (len * len + len) / 2;
            int right = central;
            int left = old - 1;
            while (left >= 0 && right < n) {
                if (data[left] == data[right]) {
                    left--;
                    count++;
                    right++;
                } else break;
            }

        }
        return count;
    }

    public int countSubstringsII(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);
            count += expand(s, i, i + 1);
        }
        return count;
    }

    private int expand(String s, int l, int h) {
        int N = s.length();
        int count = 0;
        while (l >= 0 && h < N) {
            if (s.charAt(l) == s.charAt(h)) {
                l--;
                h++;
                count++;
            } else {
                return count;
            }
        }

        return count;
    }

    public int countSubstringsIII(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i  + 1 < 3 || dp[i + 1][j - 1]);
                if (dp[i][j]) ++res;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Palindromic_Substrings_647 obj = new Palindromic_Substrings_647();
        System.out.println(obj.countSubstrings("aaa"));
    }

    private boolean isPalindrome(int start, int end, String s) {
        while(start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            ++start;
            --end;
        }

        return true;
    }

    public int countSubstringsVII(String s) {
        if (s == null) return 0;

        int res = 0;

        for (int i = 0; i < s.length(); ++i)
            for (int j = i; j < s.length(); ++j)
                if (isPalindrome(i, j, s))
                    res++;

        return res;
    }
    /*
    Time Complexity: O(N^3) for input string of length N.
    Space Complexity: O(1). We don't need to allocate any extra space since we are repeatedly iterating on the input string itself.
    */

    public int countSubstringsV(String s) {
        int n = s.length();
        int ans = 0;

        if (n <= 0) return 0;

        boolean[][] dp = new boolean[n][n];

        // Base case: single letter substrings
        for (int i = 0; i < n; ++i, ++ans) dp[i][i] = true;

        // Base case: double letter substrings
        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            ans += (dp[i][i + 1] ? 1 : 0);
        }

        // All other cases: substrings of length 3 to n
        for (int len = 3; len <= n; ++len) {
            for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                ans += (dp[i][j] ? 1 : 0);
            }
        }

        return ans;
    }

    public int countSubstringsVI(String s) {
        int ans = 0;
        int n = s.length();

        if (n <= 0) return ans;
        int full = n * n;

        boolean[] dp = new boolean[full];

        // one letter
        for (int i = 0; i < n; ++i)  {
            dp[i * n + i] = true;
            ans++;
        }

        // two letters
        for (int i = 0; i < n - 1; ++i) {
            dp[i * n + i + 1] = (s.charAt(i) == s.charAt(i + 1));
            if (dp[i * n + i + 1]) ans++;
        }

        for (int len = 3; len <= n; ++len) {
            for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                dp[i * n + j] = s.charAt(i) == s.charAt(j) && dp[(i + 1) * n + j - 1];
                if (dp[i * n + j]) ans++;
            }
        }        

        return ans;
    }
    /*
    Time Complexity: O(N^2) for input string of length N. The number of dynamic programming states 

    that need to calculated is the same as the number of substrings i.e. {N \choose 2} = N(N-1)/2. Each state can be calculated in constant time using a previously calculated state. Thus overall time take in the order of O(N^2).

    Space Complexity: O(N^2) for an input string of length NN. We need to allocate extra space to hold all N \choose 2 dynamic programming states.
    */

    public int countSubstrings(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); ++i) {
            ans += whatever(s, i, i);
            ans += whatever(s, i, i + 1);
        }

        return ans;
    }

    private int whatever(String s, int i, int j) {
        int ans = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j))
                break;
            i--;
            j++;
            ans++;
        }

        return ans;
    }

    /*
    Time Complexity: O(N^2) for input string of length NN. The total time taken in this approach is dictated by two variables:

    The number of possible palindromic centers we process.
    How much time we spend processing each center.
    The number of possible palindromic centers is 2N-1: there are N single character centers and N−1 consecutive character pairs as centers.

    Each center can potentially expand to the length of the string, so time spent on each center is linear on average. Thus total time spent is N * (2N - 1) \simeq N^2.

    Space Complexity: O(1). We don't need to allocate any extra space since we are repeatedly iterating on the input string itself.
    */
}