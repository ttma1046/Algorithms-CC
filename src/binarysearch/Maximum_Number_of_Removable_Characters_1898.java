package binarysearch;
import java.util.Arrays;
/*
You are given two strings s and p where p is a subsequence of s.

You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).

You want to choose an integer k (0 <= k <= removable.length) such that,

after removing k characters from s using the first k indices in removable,

p is still a subsequence of s.

More formally, you will mark the character at s[removable[i]] for each 0 <= i < k,

then remove all marked characters and check if p is still a subsequence.

Return the maximum k you can choose such that p is still a subsequence of s after the removals.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

Example 1:

Input: s = "abcacb", p = "ab", removable = [3,1,0]
Output: 2
Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
"ab" is a subsequence of "accb".
If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
Hence, the maximum k is 2.
Example 2:

Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
Output: 1
Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
"abcd" is a subsequence of "abcddddd".
Example 3:

Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
Output: 0
Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.

Constraints:

1 <= p.length <= s.length <= 105
0 <= removable.length < s.length
0 <= removable[i] < s.length
p is a subsequence of s.
s and p both consist of lowercase English letters.
The elements in removable are distinct.
*/
class Maximum_Number_of_Removable_Characters_1898 {
    public int maximumRemovals(String s, String p, int[] removable) {
        char[] letters = s.toCharArray();

        int left = 0, right = removable.length;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            for (int i = 0; i < mid; ++i) letters[removable[i]] = '/';

            if (checkSubSequence(letters, p)) {
                left = mid + 1;
            } else {
                for (int i = 0; i < mid; ++i) letters[removable[i]] = s.charAt(removable[i]);
                right = mid - 1;
            }
        }

        return right;
    }

    private boolean checkSubSequence(char[] c, String p) {
        int i1 = 0, i2 = 0;

        while(i1 < c.length && i2 < p.length()) {
            char curr = c[i1], curr2 = p.charAt(i2);
            if (curr != '/' && curr2 == curr) i2++;
            i1++;
        }

        if (i2 == p.length()) return true;
        return false;
    }

    public int maximumRemovalsII(String s, String p, int[] removable) {
        int left = 0, right = removable.length;
        int[] map = new int[s.length()];
        
        //  a b c a c b   removable = 3 [3,1,0]
        Arrays.fill(map, removable.length);
        // [3,3,3,3,3,3]

        for (int i = 0; i < removable.length; ++i)
            map[removable[i]] = i;
        // [2,1,3,0,3,3]
        
        while (left < right) {
            int mid = (left + right + 1) / 2, j = 0;
                       // 2
            for (int i = 0; i < s.length() && j < p.length(); ++i)
                if (map[i] >= mid && s.charAt(i) == p.charAt(j))
                    ++j;

            if (j == p.length())
                left = mid;
            else
                right = mid - 1;
        }
        
        return left;
    }

    public static void main(String[] args) {
    	Maximum_Number_of_Removable_Characters_1898 obj = new Maximum_Number_of_Removable_Characters_1898();
    }




























}