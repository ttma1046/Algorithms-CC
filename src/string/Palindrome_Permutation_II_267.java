package string;
/*
Given a string s, return all the palindromic permutations (without duplicates) of it.

You may return the answer in any order. If s has no palindromic permutation, return an empty list.

Example 1:

Input: s = "aabb"
Output: ["abba","baab"]

Example 2:

Input: s = "abc"
Output: []

Constraints:

1 <= s.length <= 16
s consists of only lowercase English letters.
*/

public class Palindrome_Permutation_II_267 {
    Set<String> set = new HashSet<>();

    public List<String> generatePalindromes(String s) {
        int[] map = new int[28];

        char[] st = new char[s.length() / 2];
        
        if (!canPermutePalindrome(s, map))
            return new ArrayList<>();
        
        char ch = '~';

        int k = 0;
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1)
                ch = (char)i;

            for (int j = 0; j < map[i] / 2; j++)
                st[k++] = (char)(i + 'a');
        }
        
        permute(st, 0, ch);
        return new ArrayList<String>(set);
    }
    
    public boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;

            if (map[s.charAt(i) - 'a'] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
    
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set.add(
            	new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse()
            );
        } else {
            for (int i = l; i < s.length; i++) {
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }
}