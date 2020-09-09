package twopointers;

class Valid_Palindrome_II_680 {
    public boolean isPalindrome(String s) {
        if (s.length() <= 2 || s == "") {
            return true;
        }

        int end = s.length() - 1;
        for (int i = 0; i <= end / 2; i++) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(end - i))) {
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
        


        System.out.println(new Valid_Palindrome_II_680().validPalindrome("aba"));



        System.out.println(new Valid_Palindrome_II_680().validPalindrome("abca"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("abdca"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("caba"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("cbbcc"));
        System.out.println(new Valid_Palindrome_II_680().validPalindrome("ccbbc"));



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

    public boolean validPalindrome(String s) {
        boolean flag = false;
        boolean flagtwo = false;
        boolean result = true;
        boolean resulttwo = true;
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                if (flag) {
                    result = false;
                    break;
                } else {
                    i++;
                    flag = true;
                    continue;
                }
            }
            i++;
            j--;
        }



        i = 0;
        j = s.length() - 1;

        while (i < j) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                if (flagtwo) {
                    resulttwo = false;
                    break;
                } else {
                    j--;
                    flagtwo = true;
                    continue;
                }
            }
            i++;
            j--;
        }

        return result || resulttwo;
    }


    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindromeII(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }

    private boolean isValidCharacter(Character c) {
        return (c - 'A' >= 0 && c - 'A' <= 25) ||
               (c - 'a' >= 0 && c - 'a' <= 25) ||
               (c - '0' >= 0 && c - '0' <= 9);
    }
}



/*
0 1 2 3

0 3
1 2


0 1 2 3 4

end 4

i <= 2

0 4
1 3
2 2

*/