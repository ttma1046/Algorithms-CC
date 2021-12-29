package twopointers;

/*
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:

Input: s = "aba"
Output: true

Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:

Input: s = "abc"
Output: false

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
*/

class Valid_Palindrome_II_680 {
    public static void main(String[] args) {
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("abba"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("abcba"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("A man, a plan, a canal: Panama"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("race a car"));

        System.out.println(new Valid_Palindrome_II_680().validPalindrome("aba"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("abca"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("abdca"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("caba"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("cbbcc"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("ccbbc"));
    }

    public boolean validPalindrome(String s) {
        if (s.length() <= 1) return true;
        return helper(s, 0, s.length() - 1, 0);
    }

    private boolean helper(String s, int left, int right, int countDeleted) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }

            // If we have a mismatch and one char has already been deleted
            if (countDeleted == 1) return false;

            return helper(s, left + 1, right, 1) || helper(s, left, right - 1, 1);
        }
        return true;
    }
}