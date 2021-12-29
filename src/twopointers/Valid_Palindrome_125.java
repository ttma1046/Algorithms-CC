package twopointers;
// import java.util.Character;

/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 

Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/
class Valid_Palindrome_125 {
	public boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			while (i < j && !isValidChar(s.charAt(i))) {
				i++;
			}

			while (i < j && !isValidChar(s.charAt(j))) {
				j--;
			}

			if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
				return false;
			}
		}

		return true;
	}

	private boolean isValidChar(char c) {
		return (c - 'a' >= 0 && c - 'a' <= 25) || (c - 'A' >= 0 && c - 'A' <= 25) || (c - '0' >= 0 && c - '0' <= 9);
	}

	/*
	Complexity Analysis

	Time complexity : O(n), in length nn of the string. We traverse over each character at-most once, 
	until the two pointers meet in the middle, or when we break and return early.

	Space complexity : O(1). No extra space required, at all.
	*/

	public static void main(String[] args) {
		System.out.println(new Valid_Palindrome_125().isPalindrome("A man, a plan, a canal: Panama")); // true
		System.out.println(new Valid_Palindrome_125().isPalindrome("race a car")); // false
	}
}