package leetcode;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/
class Group_Shifted_Strings_249 {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (String item : strings) {
            int offset = item.charAt(0) - 'a';

            for (char c : item.toCharArray()) {

                char convertedChar = (char)(c - offset);

                if (convertedChar < 'a') {
                    convertedChar += 26;
                }

                sb.append(convertedChar);
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(item);
            map.put(key, list);
            sb.setLength(0);
        }

        List<List<String>> result = new ArrayList<List<String>>();
        for (String key : map.keySet()) {
            List<String> item = map.get(key);
            Collections.sort(item);
            result.add(item);
        }

        return result;
    }

    public List<List<String>> groupStrings(String[] strings) {
        /*
        abc -> 012
        xyz -> 012
        */
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String normalized = normalize(str);
            map.putIfAbsent(normalized, new ArrayList<>());
            map.get(normalized).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String normalize(String str) {
        if (str.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char normalized = (char) ((str.charAt(i) - str.charAt(0) + 26) % 26 + 'a');
            sb.append(normalized);
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Group_Shifted_Strings_249().groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }
}