package array;

/*
Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
Example 2:

Input: A = "ab", B = "ab"
Output: false
Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
Example 3:

Input: A = "aa", B = "aa"
Output: true
Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Constraints:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist of lowercase letters.
*/

class Buddy_Strings_859 {
	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length()) return false;

		if (A.equals(B)) {
			int[] counts = new int[26];

			for (char c: A.toCharArray())
				counts[c - 'a']++;

			for (int count: counts) 
				if (count > 1) return true;

			return false;
		} else {
			int first = -1, second = -1;
			for (int i = 0; i < A.length(); i++) {
				if (A.charAt(i) != B.charAt(i)) {
					if (first == -1) {
						first = i;
					} else if (second == -1) {
						second = i;
					} else {
						return false;
					}
				} 
			}
			return second != -1 && A.charAt(first) == B.charAt(second) && B.charAt(first) == A.charAt(second);
		}
	}

	public static void main(String[] args) {
		System.out.println(new Buddy_Strings_859().buddyStrings("aaaaaaabc", "aaaaaaacb"));
	}
}