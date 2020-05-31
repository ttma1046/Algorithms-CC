package string;

import java.util.HashSet;

class LongestSubstringwithoutRepeatingCharacters_3 {
    public int lengthOfLongestSubstringII(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int result = 0;
        HashSet<Character> mySet = new HashSet<Character>();

        for(int i = 0; i < s.length(); i++) {
            mySet = new HashSet<Character>();
            for(int j = i; j < s.length(); j++) {
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

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("a"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("au"));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring(" "));
        System.out.println(new LongestSubstringwithoutRepeatingCharacters_3().lengthOfLongestSubstring("        "));
    }
}