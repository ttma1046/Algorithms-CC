package string;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
Given two strings s and t of lengths m and n respectively,

return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

class Minimum_Window_Substring_76 {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || s == null || t.length() == 0 || t == null) return "";

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }


        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue())
                formed++;


            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);

                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) formed--;


                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }



    public String minWindowIV(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";

        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int required = tMap.size();

        Map<Character, Integer> sMap = new HashMap<>();
        int formed = 0;
        int l = 0, r = 0;
        int[] res = new int[] {-1, l, r};

        while (r < s.length()) {
            char c = s.charAt(r);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);

            if (tMap.containsKey(c) && sMap.get(c).intValue() == tMap.get(c).intValue()) formed++;

            while(l <= r && formed == required) {
                if (res[0] == -1 || r - l + 1 < res[0]) {
                    res[0] = r - l + 1;
                    res[1] = l;
                    res[2] = r;
                }

                c = s.charAt(l);
                sMap.put(c, sMap.get(c) - 1);
                if (tMap.containsKey(c) && sMap.get(c).intValue() < tMap.get(c).intValue()) formed--;

                l++;
            }

            r++;
        }


        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    /*
    Complexity Analysis

    Time Complexity: O(|S| + |T|)O(∣S∣+∣T∣) where |S| and |T| represent the lengths of strings SS and TT. In the worst case we might end up visiting every element of string SS twice, once by left pointer and once by right pointer. |T|∣T∣ represents the length of string TT.

    Space Complexity: O(|S| + |T|)O(∣S∣+∣T∣). |S|∣S∣ when the window size is equal to the entire string SS. |T|∣T∣ when TT has all unique characters.
    */

    public static void main(String[] args) {
        Minimum_Window_Substring_76 obj = new Minimum_Window_Substring_76();
    }

    /*
    public String minWindowII(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c))
                filteredS.add(new Pair<Integer, Character>(i, c));
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) formed++;

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                c = filteredS.get(l).getValue();
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) formed--;

                l++;
            }
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    */

    /*
    Complexity Analysis

    Time Complexity : O(|S| + |T|)O(∣S∣+∣T∣) where |S| and |T| represent the lengths of strings SS and TT. The complexity is same as the previous approach. But in certain cases where |filtered\_S|∣filtered_S∣ <<< |S|∣S∣, the complexity would reduce because the number of iterations would be 2*|filtered\_S| + |S| + |T|2∗∣filtered_S∣+∣S∣+∣T∣.
    Space Complexity : O(|S| + |T|)O(∣S∣+∣T∣).
    */

    public String minWindowIII(String s, String t) {
        if (t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap<>();

        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        int counter = map.size();

        int begin = 0;
        int end = 0;
        int head = 0;
        int length = Integer.MAX_VALUE;

        while(end < s.length()) {
            char endC = s.charAt(end);
            if (map.containsKey(endC)) {
                map.put(endC, map.get(endC) - 1);

                if (map.get(endC) == 0) counter--;
            }

            while(counter == 0) {
                if (end - begin + 1 < length) {
                    length = end - begin + 1;
                    head = begin;
                }

                char beginC = s.charAt(begin);
                if (map.containsKey(beginC)) {
                    map.put(beginC, map.get(beginC) + 1);
                    if (map.get(beginC) > 0) counter++;
                }

                begin++;
            }

            end++;
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length);
    }

    public String minWindow(String s, String t) {
        int left = 0, right = 0;
        int[] need = new int[58];
        
        int size = 0;

        for(char ch : t.toCharArray()) {
            if(need[ch - 'A'] == 0) size++;
            need[ch - 'A']++;
        }

        int len = s.length() + 1;
        int start = 0;
        int end = 0;
        int[] cur = new int[58];
        int curSize = 0;
        
        while(right < s.length()) {
            char ch = s.charAt(right);
            cur[ch - 'A']++;
            if(need[ch - 'A'] > 0 && cur[ch - 'A'] == need[ch - 'A']) curSize++;
        
            while(size == curSize) {
                char lch = s.charAt(left);
                cur[lch - 'A']--;
                if(need[lch - 'A'] > 0 && cur[lch - 'A'] < need[lch - 'A']) {
                    if(right - left + 1 < len) {
                        start = left;
                        end = right + 1;
                        len = right - left + 1;
                    }
                    curSize--;
                }
                left++;
            }

            right++;
        }
        
        return len == s.length() + 1 ? "" : s.substring(start, end);
    }
}