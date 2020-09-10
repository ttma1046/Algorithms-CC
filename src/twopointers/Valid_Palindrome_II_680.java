package twopointers;

class Valid_Palindrome_II_680 {
    public boolean isPalindrome(String s) {
        if (s.length() <= 2 || s == "") {
            return true;
        }

        int end = s.length();

        for (int i = 0; i < end / 2; i++) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(end - 1 - i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("abba"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("abcba"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("A man, a plan, a canal: Panama"));
        System.out.println(new Valid_Palindrome_II_680().isPalindromeII("race a car"));

        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("aba"));
        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("abca"));
        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("abdca"));
        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("caba"));
        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("cbbcc"));
        System.out.println(new Valid_Palindrome_II_680().ValidPalindromeII("ccbbc"));
    }

    public boolean isPalindromeII(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !isValidCharacter(s.charAt(i))) {
                i++;
            }

            while (i < j && !isValidCharacter(s.charAt(j))) {
                j--;
            }

            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }

        return true;
    }

    private boolean isValidCharacter(Character c) {
        return (c - 'A' >= 0 && c - 'A' <= 25) ||
               (c - 'a' >= 0 && c - 'a' <= 25) ||
               (c - '0' >= 0 && c - '0' <= 9);
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