package sort;

/*
Given two strings s and t,

return true if t is an anagram of s, and false otherwise.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true

Example 2:

Input: s = "rat", t = "car"
Output: false

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/
class Valid_Anagram_242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.equals(t)) return false;

        int[] map = new int[26];

        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }

        for (int i : map)
            if (i != 0) return false;

        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.equals(t)) return false;

        int[] map = new int[26];

        for (char c : s.toCharArray()) map[c - 'a']++;

        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
            if (map[t.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }

    /*
    Time complexity : O(n).
    Time complexity is O(n) because accessing the counter table is a constant time operation.

    Space complexity : O(1). Although we do use extra space, the space complexity is O(1)
    because the table's size stays constant no matter how large nn is.
    */

    /*
    Follow up

    What if the inputs contain unicode characters? How would you adapt your solution to such case?

    Answer

    Use a hash table instead of a fixed size counter. 
    Imagine allocating a large size array to fit the entire range of unicode characters, 
    which could go up to more than 1 million. 

    A hash table is a more generic solution and could adapt to any range of characters.
    */

    public static void main(String[] args) {
        Valid_Anagram_242 obj = new Valid_Anagram_242();
    }
}