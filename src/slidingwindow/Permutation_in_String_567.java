package slidingwindow;

/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*/
class Permutation_in_String_567 {
    
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++; // s1 = "ab", s2 = "eidbaooo"
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)) return true;

            s2map[s2.charAt(i + s1.length()) - 'a']++;

            s2map[s2.charAt(i) - 'a']--;
        }

        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++)
            if (s1map[i] != s2map[i])
                return false;

        return true;
    }

    /*
    Time complexity: O(l1 + 26 * (l2 - l1)), where l1 is the length of string l1 and l2 is the length of string l2.
    Space complexity: O(1). Constant space is used.
    */

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];
        
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        
        int count = 0;

        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
                
        for (int i = 0; i < s2.length() - s1.length(); i++) {
        
            if (count == 26) return true;

            int r = s2.charAt(i + s1.length()) - 'a', 
            int l = s2.charAt(i) - 'a';

            s2map[r]++;

            if (s2map[r] == s1map[r]) 
                count++;
            else if (s2map[r] == s1map[r] + 1) 
                count--;

            s2map[l]--;

            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }

        return count == 26;
    }

    boolean flag = false;

    public boolean checkInclusion(String s1, String s2) {
        flag = false;
        permute(s1, s2, 0);
        return flag;
    }

    public String swap(String s, int i0, int i1) {
        if (i0 == i1)
            return s;
        String s1 = s.substring(0, i0);
        String s2 = s.substring(i0 + 1, i1);
        String s3 = s.substring(i1 + 1);
        return s1 + s.charAt(i1) + s2 + s.charAt(i0) + s3;
    }

    void permute(String s1, String s2, int l) {
        if (l == s1.length()) {
            if (s2.indexOf(s1) >= 0)
                flag = true;
        } else {
            for (int i = l; i < s1.length(); i++) {
                s1 = swap(s1, l, i);
                permute(s1, s2, l + 1);
                s1 = swap(s1, l, i);
            }
        }
    }

    /*
    Time complexity: O(n!).
    We match all the permutations of the short string s1, of length s1, with s2. Here, n refers to the length of s1.

    Space complexity: O(n^2).
    The depth of the recursion tree is n(n refers to the length of the short string s1). Every node of the recursion tree contains a string of max. length n.
    */

    public boolean checkInclusion(String s1, String s2) {
        s1 = sort(s1);
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (s1.equals(sort(s2.substring(i, i + s1.length()))))
                return true;
        }
        return false;
    }

    public String sort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }

    /*
    Time complexity: O(l1log(l1) + (l2 - l1)l1log(l1)). where l1 is the length of string l1 and l2 is the length of string l22.
    */

    /*
    Space complexity: O(l1). t array is used.
    */

    public static void main(String[] args) {
        Permutation_in_String_567 obj = new Permutation_in_String_567();

        System.out.println(obj.checkInclusion("ab", "eidbaooo"));
        System.out.println(obj.checkInclusion("ab", "eidboaoo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> s1map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++)
            s1map.put(s1.charAt(i), s1map.getOrDefault(s1.charAt(i), 0) + 1);

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            Map<Character, Integer> s2map = new HashMap<>();

            for (int j = 0; j < s1.length(); j++)
                s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);

            if (matches(s1map, s2map)) return true;
        }

        return false;
    }

    public boolean matches(HashMap <Character, Integer> s1map, HashMap <Character, Integer> s2map) {
        for (char key : s1map.keySet())
            if (s1map.get(key) - s2map.getOrDefault(key, -1) != 0)
                return false;

        return true;
    }

    /*
    Time complexity: O(l1 + 26 ∗ l1 ∗ (l2 − l1)). hashmap contains atmost 26 keys. where l1 is the length of string l1 and l2 is the length of string l2.

    Space complexity: O(1). Hashmap contains at most 26 key-value pairs.
    */

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1map = new int[26];
        for (int i = 0; i < s1.length(); i++)
            s1map[s1.charAt(i) - 'a']++;

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < s1.length(); j++) s2map[s2.charAt(i + j) - 'a']++;

            if (matches(s1map, s2map)) return true;
        }

        return false;
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++)
            if (s1map[i] != s2map[i])
                return false;
        
        return true;
    }

    /*
    Time complexity: O(l1 + 26 ∗ l1 * (l2 − l1)),  where l1 is the length of string l1 and l2 is the length of string l2.
    Space complexity: O(1). s1map and s2map of size 26 is used.
    */
}