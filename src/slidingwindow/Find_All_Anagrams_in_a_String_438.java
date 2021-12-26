package slidingwindow;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/
class Find_All_Anagrams_in_a_String_438 {
    public List<Integer> findAnagramsII(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLength = s.length();
        int pLength = p.length();
        if (pLength > sLength) return res;

        int[] sHash = new int[26];
        int[] pHash = new int[26];

        for (char c: p.toCharArray()) pHash[c - 'a']++;
        
        for (int i = 0; i < s.length(); ++i) {
            sHash[s.charAt(i) - 'a']++;

            if (i - pLength >= 0) sHash[s.charAt(i - pLength) - 'a']--;

            if (Arrays.equals(pHash, sHash)) res.add(i - pLength + 1);
        }

        return res;
    }

    /*
    public List<Integer> findAnagrams(String s, String p) {
        int lengthS = s.length(), lengthP = p.length();

        if (lengthS < lengthP) return new ArrayList<Integer>();

        HashMap<Character, Integer> sHash = new HashMap<Character, Integer>();
        HashMap<Character, Integer> pHash = new HashMap<Character, Integer>();

        for (Character ch : p.toCharArray()) {
            pHash.put(ch, pHash.getOrDefault(ch, 0) + 1);
        }

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sHash.put(ch, sHash.getOrDefault(ch, 0) + 1);

            if (i >= lengthP) {
                char temp = s.charAt(i - lengthP);
                if (sHash.get(temp) == 1) {
                    sHash.remove(temp);
                } else {
                    sHash.put(temp, sHash.get(temp) - 1);
                }
            }

            if (pHash.equals(sHash)) {
                result.add(i - lengthP + 1);
            }
        }

        return result;
    }
    */

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (p.length() > s.length()) return res;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : p.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        int start = 0, end = 0, count = map.size();

        while(end < s.length()) {
            char endC = s.charAt(end);

            if (map.containsKey(endC)) {
                map.put(endC, map.get(endC) - 1);
                if (map.get(endC) == 0) count--;
            }

            while(count == 0) {
                if (end - start + 1 == p.length()) res.add(start);

                char startC = s.charAt(start);

                if (map.containsKey(startC)) {
                    map.put(startC, map.get(startC) + 1);
                    if (map.get(startC) > 0) count++;
                }

                start++;
            }

            end++;
        }

        return res;
    }

    public static void main(String[] args) {
        Find_All_Anagrams_in_a_String_438 obj = new Find_All_Anagrams_in_a_String_438();
        List<Integer> result = obj.findAnagramsII("cbaebabacd", "abc");

        for (int item : result) {
            System.out.print(item);
        }

        System.out.println();

        result = obj.findAnagramsII("abab", "ab");

        for (int item : result) {
            System.out.print(item);
        }
    }
}