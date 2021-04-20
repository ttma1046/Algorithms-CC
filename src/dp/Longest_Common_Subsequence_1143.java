package dp;
import java.util.Stack;
class Longest_Common_Subsequence_1143 {

	public int lcs(char str1[], char str2[], int len1, int len2) {
		if (len1 == str1.length || len2 == str2.length) {
			return 0;
		}
		if (str1[len1] == str2[len2]) {
			return 1 + lcs(str1, str2, len1 + 1, len2 + 1);
		} else {
			return Math.max(lcs(str1, str2, len1 + 1, len2), lcs(str1, str2, len1, len2 + 1));
		}
	}

	public int longestCommonSubsequence(String text1, String text2) {
		int lengthOne = text1.length();
		int lengthTwo = text2.length();
		int max = 0;
		int[][] dp = new int[lengthTwo + 1][lengthOne + 1];

		for (int i = 1; i <= lengthTwo; i++) {
			for (int j = 1; j <= lengthOne; j++) {
				if (text2.charAt(i - 1) == text1.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
			}
		}

        int one = lengthOne;
		int two = lengthTwo;

		StringBuilder sb = new StringBuilder();

		while (lengthOne > 0 && lengthTwo > 0) {
			if (dp[lengthTwo][lengthOne] != dp[lengthTwo - 1][lengthOne] && dp[lengthTwo][lengthOne] != dp[lengthTwo][lengthOne - 1]) {
				sb.append(text2.charAt(lengthTwo - 1));
				lengthTwo--;
				lengthOne--;
			} else if (dp[lengthTwo][lengthOne] == dp[lengthTwo - 1][lengthOne]) {
				lengthTwo--;
			} else if (dp[lengthTwo][lengthOne] == dp[lengthTwo][lengthOne - 1]) {
				lengthOne--;
			}
		}

		System.out.println(sb.reverse().toString());
		return dp[two][one];
	}



	public static void main(String[] args) {
		int res = new Longest_Common_Subsequence_1143().longestCommonSubsequence("abcdaf", "acbcf");

		System.out.println(res);

		res = new Longest_Common_Subsequence_1143().longestCommonSubsequence("abcde", "ace");

		System.out.println(res);

		res = new Longest_Common_Subsequence_1143().longestCommonSubsequence("abc", "abc");

		System.out.println(res);

		res = new Longest_Common_Subsequence_1143().longestCommonSubsequence("abc", "def");

		System.out.println(res);
	}
}