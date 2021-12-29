package twopointers;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/*
Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
*/

class LongestSubstringwithoutRepeatingCharacters_3 {
    public int lengthOfLongestSubstringBruteForce(String s) {
        // Brute Force
        if (s == null || s.length() <= 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int n = s.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (check(s, i, j) && j - i + 1 > result) result = j - i  + 1;
            }
        }

        return result;
    }

    private boolean check(String s, int i, int j) {
        int[] index = new int[128];

        for (int k = i; k <= j; k++) {
            char c = s.charAt(k);
            index[c - ' ']++;
            if (index[c - ' '] > 1) return false;
        }

        return true;
    }


    public int lengthOfLongestSubstringBruteForceII(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int result = 0;
        Set<Character> mySet = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            mySet = new HashSet<Character>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (mySet.contains(c)) {
                    break;
                }

                mySet.add(c);
                if (j - i + 1 > result) {
                    result = j - i + 1;
                }
            }
        }

        return result;
    }


    public int lengthOfLongestSubstringSlidingWindow(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int n = s.length();

        int result = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();

        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));

                if (j - i > result) {
                    result = j - i;
                }
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return result;
    }

    /*
    'abcabcbb'

    a
    i = 0
    j = 1
    1


    ab
    i = 0
    j = 2
    2

    abc
    i = 0
    j = 3
    3

    bc
    i = 1
    j = 3
    3

    bca
    i = 1
    j = 4
    3
    */

    // Sliding Window Optimized
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        int n = s.length();

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int max = 0;

        for (int i = 0, j = 0; j < n; ++j) {
            if (map.containsKey(s.charAt(j)))
                i = Math.max(i, map.get(s.charAt(j)) + 1);

            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j);
        }

        return max;
    }

    public int lengthOfLongestSubstringNoMap(String s) {
        if (s.length() == 0) return 0;

        int n = s.length();
        int[] index = new int[128];

        for (int i = 0; i < index.length; i++) index[i] = -1;

        int ans = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            if (index[s.charAt(j) - ' '] > -1) {
                i = Math.max(i, index[s.charAt(j) - ' '] + 1);
            }
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j) - ' '] = j;
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        Integer[] chars = new Integer[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);

            Integer index = chars[r];
            if (index != null && index >= left && index < right) left = index + 1;

            res = Math.max(res, right - left + 1);

            chars[r] = right;
            right++;
        }

        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int begin = 0, end = 0, count = 0, res = 0;

        while(end < s.length()) {
            char endC = s.charAt(end);

            map.put(endC, map.getOrDefault(endC, 0) + 1);
            if (map.get(endC) > 1) count++;


            while(count > 0) {
                char beginC = s.charAt(begin);
                if (map.get(beginC) > 1) count--;
                map.put(beginC, map.get(beginC) - 1);

                begin++;
            }

            res = Math.max(res, end - begin + 1);

            end++;
        }

        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int begin = 0;
        int end = 0;
        int count = 0;

        while(end < s.length()) {
            char endC = s.charAt(end);

            map.put(endC, map.getOrDefault(endC, 0) + 1);
            if (map.get(endC) > 1) count++;

            while(count > 0) {
                char beginC = s.charAt(begin);
                map.put(beginC, map.get(beginC) - 1);
                if (map.get(beginC) == 1) count--;

                begin++;
            }

            if (end - begin + 1 > res) res = end - begin + 1;

            end++;    
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("a"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("abbbeda"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("au"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring(" "));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("        "));
        System.out.println();

        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("pwwkew"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("bbbbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("abcabcbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("a"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("abbbeda"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("au"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap(" "));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstringNoMap("        "));
        System.out.println();
    }
}