package stack;
import java.lang.StringBuilder;
import java.util.Deque;
import java.util.ArrayDeque;

class Longest_Palindromic_Substring_5 {
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
			} else if (!mydeque.isEmpty() && (mydeque.peekLast() - c != 0)){
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