package twopointers;
// import java.util.Character;

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

	public static void main(String[] args) {
		System.out.println(new Valid_Palindrome_125().isPalindrome("A man, a plan, a canal: Panama")); // true
		System.out.println(new Valid_Palindrome_125().isPalindrome("race a car")); // false
	}
}