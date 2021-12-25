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
	/*
    public List<Integer> findAnagrams(String s, String p) {
        int sLength = s.length(), pLength = p.length();
        if (sLength < pLength) return new ArrayList<Integer>();

        int[] sHash = new int [26];
        int[] pHash = new int [26];

        for (Character ch : p.toCharArray()) {
            pHash[ch - 'a']++;
        }

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < sLength; i++) {
            char c = s.charAt(i);
            sHash[c - 'a']++;

            if (i - pLength >= 0) sHash[s.charAt(i - pLength) - 'a']--;

            if (Arrays.equals(sHash, pHash)) result.add(i - pLength + 1);            
        }

        return result;
    }

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

    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();

        if(t.length() > s.length()) return result;

        Map<Character, Integer> map = new HashMap<>();

        for(char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        int counter = map.size();
        int begin = 0, end = 0;

        while(end < s.length()) {
            char c = s.charAt(end);

            if( map.containsKey(c) ) {
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0) {
                char tempCharacter = s.charAt(begin);
                if(map.containsKey(tempCharacter)) {
                    map.put(tempCharacter, map.get(tempCharacter) + 1);
                    if(map.get(tempCharacter) > 0) counter++;
                }

                if(end - begin == t.length()) result.add(begin);                

                begin++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
    	Find_All_Anagrams_in_a_String_438 obj = new Find_All_Anagrams_in_a_String_438();
        List<Integer> result = obj.findAnagrams("cbaebabacd", "abc");

        for (int item : result) {
            System.out.print(item);
        }

        System.out.println();

        result = obj.findAnagrams("abab", "ab");

        for (int item : result) {
            System.out.print(item);
        }
    }
}