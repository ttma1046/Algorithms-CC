package string;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
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
        // int[] map = new int[26];
        int[] map = new int[128];
        if (!isPalindromes(s, map))
            return new ArrayList<String>();

        char ch = 0;
        int k = 0;
        char[] d = new char[s.length() / 2];

        for (int i = 0; i < map.length; ++i) {
            /*
            if (map[i] % 2 == 1)
                ch = (char)('a' + i);

            for (int j = 0; j < map[i] / 2; ++j)
                d[k++] = (char)('a' + i);
            */

            if (map[i] % 2 == 1)
                ch = (char)i;
            for (int j = 0; j < map[i] / 2; j++)
                d[k++] = (char)i;
        }

        premute(d, 0, ch);

        return new ArrayList<String>(set);
    }

    private void swap(char[] s, int l, int i) {
        char temp = s[l];
        s[l] = s[i];
        s[i] = temp;
    }

    private void premute(char[] chars, int l, char ch) {
        if (l == chars.length) {
            set.add(new String(chars) + (ch == 0 ? "" : ch) + new StringBuffer(new String(chars)).reverse());
        } else {
            for (int i = l; i < chars.length; ++i) {
                if (chars[l] != chars[i] || l == i) {
                    swap(chars, l, i);
                    premute(chars, l + 1, ch);
                    swap(chars, l, i);
                }
            }
        }
    }

    private boolean isPalindromes(String s, int[] map) {
        int count = 0;

        for (int i = 0; i < s.length(); ++i) {
            // map[s.charAt(i) - 'a']++;
            map[s.charAt(i)]++;

            // if (map[s.charAt(i) - 'a'] % 2 == 0)
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }

        return count <= 1;
    }

    /*
    Time complexity : O((n/2 + 1)!). Atmost n/2! permutations need to be generated in the worst case. 

    Further, for each permutation generated, string.reverse() function will take n/4 time.

    Space complexity: O(n). The depth of recursion tree can go upto n/2 in the worst case.
    */
    public static void main(String[] args) {
        Palindrome_Permutation_II_267 obj = new Palindrome_Permutation_II_267();
        List<String> res = obj.generatePalindromes("aabb");
        for (String temp: res) 
            System.out.println(temp);
    }
}