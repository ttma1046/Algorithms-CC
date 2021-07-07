package dp;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

/*
Given a string s and a dictionary of strings wordDict,

add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

class Word_Break_II_140 {
    public static void main(String[] args) {
        Word_Break_II_140 obj = new Word_Break_II_140();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        System.out.println(s.substring(4));
        System.out.println(s.substring(4, s.length()));

        s = "aaaaaaa";
        wordDict = new ArrayList<>();
        wordDict.add("aaaa");
        wordDict.add("aa");
        wordDict.add("a");



        List<String> res = obj.wordBreakII(s, wordDict);

        for (String k : res) System.out.println(k);
    }


    public List<String> wordBreakII(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);

        return dfs(s, wordSet, new HashMap<Integer, List<String>>(), 0);
    }

    List<String> dfs(String s, Set<String> wordDict, HashMap<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) return map.get(start);

        List<String> res = new ArrayList<String>();

        if (wordDict.contains(s.substring(start))) res.add(s.substring(start));

        for (String word : wordDict) {
            if (s.substring(start).startsWith(word)) {
                int n = word.length();
                List<String> sublist = dfs(s, wordDict, map, start + n);
                if (sublist.size() != 0) {
                    StringBuilder sb;
                    for (String sub : sublist) {
                        sb = new StringBuilder();
                        sb.append(word);
                        if (!sub.isEmpty()) {
                            sb.append(" ");
                            sb.append(sub);
                        }
                        res.add(sb.toString());
                    }
                }
            }
        }

        map.put(start, res);
        return res;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> map = new HashMap<>();
        return dfs(s, dict, map, 0);
    }

    private List<String> dfs(String s, Set<String> dict, HashMap<Integer, List<String>> map, int start) {
        List<String> res = new ArrayList<>();
        if (map.containsKey(start)) return map.get(start);
        if (dict.contains(s.substring(start, s.length()))) res.add(s.substring(start, s.length()));

        for (int i = start + 1; i < s.length(); i++) {
            // Top-down
            String t = s.substring(start, i);
            if (dict.contains(t)) {
                List<String> temp = dfs(s, dict, map, i);
                if (temp.size() != 0) {
                    StringBuilder sb;
                    for (int j = 0 ; j < temp.size() ; j++) {
                        sb = new StringBuilder();
                        sb.append(t);
                        sb.append(" ");
                        sb.append(temp.get(j));
                        res.add(sb.toString());
                    }
                }
            }
        }
        map.put(start, res);
        return res;
    }
}