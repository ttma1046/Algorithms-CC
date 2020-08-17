package dp;
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

/*
How can we reuse a previously computed palindrome to compute a larger palindrome?
*/

/*
If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
*/

/*
Complexity based hint:
If we use brute-force and check whether for every start and end position a substring is a palindrome
we have O(n^2) start - end pairs and O(n) palindromic checks.
Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.
*/

class Longest_Palindromic_Substring_5 {
	public String longestPalindrome(String s) {
		int n = s.length();
		String res = "";

		boolean[][] dp = new boolean[n][n];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}

		return res;
	}


	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}

		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);

			int len2 = expandAroundCenter(s, i, i + 1);

			int len = Math.max(len1, len2);

			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}

		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	public String longestPalindrome(String s) {
		Deque<Character> mydeque = new ArrayDeque<Character>();
		StringBuilder resultsb = new StringBuilder();
		StringBuilder finalresult = new StringBuilder();

		int maxresultLength = 0;
		int resultLength = 0;
		for (int i = 0 ; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!mydeque.isEmpty() && mydeque.peekLast() - c == 0) {

				resultLength++;
				resultsb.append(c);

				if (resultLength > maxresultLength) {
					maxresultLength = resultLength;
					finalresult = resultsb;
				}

				mydeque.pollLast();
			} else if (!mydeque.isEmpty() && (mydeque.peekLast() - c != 0)) {
				char middle = mydeque.pollLast();

				if (!mydeque.isEmpty() && mydeque.peekLast() - c == 0) {
					resultLength++;
					resultsb.append(c);

					if (resultLength > maxresultLength) {
						maxresultLength = resultLength;
						finalresult = resultsb;
					}

					mydeque.pollLast();
				} else {
					mydeque.addLast(c);
					resultLength = 0;
					resultsb = new StringBuilder();
				}
			} else {
				mydeque.addLast(c);
				resultLength = 0;
				resultsb = new StringBuilder();
			}
		}

		String temp = finalresult.toString();
		return finalresult.reverse().append(temp).toString();
	}

	public static void main(String[] args) {
		System.out.println(new Longest_Palindromic_Substring_5().longestPalindrome("cbbcaxdskcddck"));
		System.out.println(new Longest_Palindromic_Substring_5().longestPalindrome("babad"));
	}
}