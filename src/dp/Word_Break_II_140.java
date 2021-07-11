package dp;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
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

        List<String> res = obj.wordBreakII(s, wordDict);

        for (String k : res) System.out.println(k);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null) return new ArrayList<String>();
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();

        return dfs(s, set, map, 0);
    }

    public List<String> dfs(String s, Set<String> set, Map<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) return map.get(start);

        List<String> res = new ArrayList<String>();

        if (set.contains(s.substring(start))) res.add(s.substring(start));

        for (int i = start + 1; i < s.length(); i++) {
            String word = s.substring(start, i);
            if (set.contains(word)) {
                List<String> subList = dfs(s, set, map, i);
                if (subList.size() > 0) {
                    StringBuilder sb;
                    for (String sub : subList) {
                        sb = new StringBuilder();
                        sb.append(word);
                        sb.append(" ");
                        sb.append(sub);
                        res.add(sb.toString());
                    }
                }
            }
        }
        map.put(start, res);
        return res;
    }

    /*
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();
        return dfs(s, dict, map, 0);
    }

    private List<String> dfs(String s, Set<String> dict, Map<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) return map.get(start);
        List<String> res = new ArrayList<>();
        if (dict.contains(s.substring(start))) res.add(s.substring(start));

        for (int i = start + 1; i < s.length(); i++) {
            // Top-down
            String word = s.substring(start, i);
            if (dict.contains(word)) {
                List<String> temp = dfs(s, dict, map, i);
                if (temp.size() != 0) {
                    StringBuilder sb;
                    for (String tst : temp) {
                        sb = new StringBuilder();
                        sb.append(word);
                        sb.append(" ");
                        sb.append(tst);
                        res.add(sb.toString());
                    }
                }
            }
        }
        map.put(start, res);
        return res;
    }
    */

    List<String> ans;
    public List<String> wordBreakIV(String s, List<String> wordDict) {
        ans = new ArrayList<String>();
        scan(s, 0, wordDict, new ArrayList<Integer>());
        return ans;
    }

    void scan(String s, int start, List<String> words, List<Integer> path) {
        // base
        if (start > s.length()) return;
        if (start == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(words.get(path.get(i)));
                if (i < path.size() - 1) sb.append(" ");
            }
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (s.startsWith(word, start)) {
                path.add(i);
                scan(s, start + word.length(), words, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public List<String> wordBreakIII(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>();
        for(String word : wordDict) {
            wordDictSet.add(word);
        }
        List<String> result = new ArrayList<>();
        List<List<String>> listOfTmps = _wordBreak(s, wordDictSet);
        for(List<String> list : listOfTmps) {
            StringBuilder sw = new StringBuilder();
            for(int i = 0; i < list.size(); i++) {
                if(i < list.size() - 1) {
                    sw.append(list.get(i));
                    sw.append(" ");
                } else {
                    sw.append(list.get(i));
                }
            }
            result.add(sw.toString());
        }
        return result;
    }

    private List<List<String>> _wordBreak(String s, Set<String> wordDictSet) {
        List<List<String>> listOfTmps = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            String tmp = s.substring(0, i + 1);
            if(i == s.length() - 1) {
                if(wordDictSet.contains(tmp)) {
                    List<String> tmpArr = new ArrayList<>();
                    tmpArr.add(tmp);
                    listOfTmps.add(tmpArr);
                }
            } else {
                if(wordDictSet.contains(tmp)) {
                    String _nuS = s.substring(tmp.length());
                    for(List<String> list : _wordBreak(_nuS, wordDictSet)) {
                        list.add(0, tmp);
                        listOfTmps.add(list);
                    };
                }
            }
        }
        return listOfTmps;
    }

    public List<String> wordBreakII(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null) return new ArrayList<>();
        Set<String> set = new HashSet<String>(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();
        return wordBreakIIdfs(s, set, map, 0);
    }

    private List<String> wordBreakIIdfs(String s, Set<String> set, Map<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) return map.get(start);

        List<String> res = new ArrayList<String>();

        if (set.contains(s.substring(start))) {
            System.out.println("start:" + start);
            res.add(s.substring(start));
        }

        for (String word : set) {
            if (s.substring(start).startsWith(word)) {
                int wordLength = word.length();
                List<String> sublist = wordBreakIIdfs(s, set, map, start + wordLength);
                if (sublist.size() != 0) {
                    StringBuilder sb;
                    for(String sub : sublist) {
                        sb = new StringBuilder();
                        sb.append(word);
                        sb.append(" ");
                        sb.append(sub);

                        res.add(sb.toString());
                    }
                }
            }
        }
        map.put(start, res);

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey());
            for (String t : entry.getValue()) {
                System.out.print("value:" + t);
                System.out.print(",");
            }
            // System.out.println();
        }

        return res;
    }
}
