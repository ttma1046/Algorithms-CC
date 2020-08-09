package dp;
class Longest_Palindromic_Substring_5 {
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) return "";
		int start = 0, end = 0;
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