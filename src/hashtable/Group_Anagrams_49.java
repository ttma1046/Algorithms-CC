package hashtable;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]


Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/
class Group_Anagrams_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length <= 0) return null;

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String sortedStr = this.sortString(s);

            List<String> strArr = new ArrayList<>();

            if (map.containsKey(sortedStr))
                strArr = map.get(sortedStr);

            strArr.add(s);

            map.put(sortedStr, strArr);
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> list : map.values()) result.add(list);

        return result;
    }

    public List<List<String>> groupAnagramsI(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            String canonical = sortString(str);
            groups.putIfAbsent(canonical, new ArrayList<>());
            List<String> l = groups.get(canonical);
            l.add(str);
        }
        List<List<String>> result = new ArrayList<>();
        result.addAll(groups.values());
        return result;
    }

    private String sortString(String inputString) {
        char[] charArr = inputString.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }

    public List<List<String>> groupAnagramsII(String[] strs) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();

        List<List<String>> l1 = new ArrayList<>();

        for(int i = 0; i < strs.length; i++) {
            char arr[] = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = new String(arr);

            if(map.containsKey(s)) {
                int j = map.get(s);
                l1.get(j).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                l1.add(temp);
                map.put(s, count++);
            }
        }
        
        return l1;
    }

    public static void main(String[] args) {
        String[] test = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        var result = new Group_Anagrams_49().groupAnagrams(test);

        for(List<String> item : result) {
            for(String re : item) {
                System.out.println(re);
            }
        }
    }
}