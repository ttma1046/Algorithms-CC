package leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Longest_String_Chain_1048 {
    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();

        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());

        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {

                // String start = word.substring(0, i);
                // String end = word.substring(i + 1);
                // String prev = start + end;

                StringBuilder sb = new StringBuilder(word);
                String prev = sb.deleteCharAt(i).toString();

                best = Math.max(best, map.getOrDefault(prev, 0) + 1);
            }

            map.put(word, best);
            res = Math.max(res, best);
        }

        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[] {
            "a", "b", "ba", "bca", "bda", "bdca"
        };

        System.out.println(new Longest_String_Chain_1048().longestStrChain(words));
    }
}


/*
class Longest_String_Chain_1048_I {
    public int longestStrChain(String[] words) {
        if (words.length < 2) {
            return words.length;
        }

        int maxLen = 0;

        List<String>[] map = new ArrayList[17];

        for (int i = 0; i < 17; i++) {
            map[i] = new ArrayList<>();
        }

        // O(N); N = num of words
        for (String word : words) {
            int len = word.length();
            map[len].add(word);
            maxLen = Math.max(len, maxLen);
        }

        int longestChain = 0;

        for (int j = maxLen; j > 1 && j > longestChain; j--) {
            longestChain = Math.max(longestChain, calculateMaxDepth(map, j, null));
        }

        return longestChain;
    }

    private int calculateMaxDepth(List<String>[] map, int len, String prev) {
        List<String> words = map[len];
        if (words.size() == 0) {
            return 0;
        }
        int max = 0;
        for (String s : words) {
            if (isPredecessor(s, prev)) {
                int depth = calculateMaxDepth(map, len - 1, s);
                max = Math.max(max, depth + 1);
            }
        }
        return max;
    }

    private boolean isPredecessor(String word, String prev) {
        if (prev == null) return true;
        int i = 0, j = 0;
        while (i < prev.length() && j < word.length()) {
            if (prev.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                if (i - j > 1) return false;
            }
        }
        return i - j <= 1;
    }
}
*/

class Longest_String_Chain_1048 {
    public int longestStrChain(String[] words) {
        if (words.length == 1) return 1;

        ArrayList<List<String>> map = new ArrayList<List<String>>(18);

        for (int i = 0; i < 18; i++) {
            map.add(new ArrayList<String>());
        }

        int maxlen = 0;
        for (String w : words) {
            int len = w.length();
            maxlen = Math.max(maxlen, len);
            map.get(len).add(w);
        }

        int longestChain = 0;
        for (int j = maxlen; j > 0 && j > longestChain; j--) {
            longestChain = Math.max(longestChain, dfs(j, map, null));
        }

        return longestChain;
    }

    private int dfs(int len, ArrayList<List<String>> map, String node) {
        int maxDepth = 0;
        List<String> result = map.get(len);
        for (int i = 0; i < result.size(); i++) {
            String child = result.get(i);
            if (isMatch(child, node)) {
                int d = dfs(len - 1, map, child);
                maxDepth = Math.max(maxDepth,  d + 1);
            }
        }

        return maxDepth;
    }

    private boolean isMatch(String child, String node) {
        if (node == null) return true;
        int i = 0;
        int j = 0;
        while (j < child.length() && i < node.length()) {
            if (child.charAt(j) == node.charAt(i)) {
                j++;
                i++;
            } else {
                if (i - j > 1) {
                    return false;
                }
                i++;
            }
        }
        return i - j <= 1;
    }

    public static void main(String[] args) {
        String[] words = new String[] {
            "a", "b", "ba", "bca", "bda", "bdca"
        };

        System.out.println(new Longest_String_Chain_1048().longestStrChain(words));
    }
}

/*
class Longest_String_Chain_1048_III {
// BFS
    public int longestStrChain(String[] words) {
        if (words.length == 1) return 1;
        List<String>[] m = new List[18];

        for (String w : words) {
            int len = w.length();
            if (m[len] == null) m[len] = new ArrayList<>();
            m[len].add(w);
        }
        Queue<LinkedList<String>> q = new LinkedList<>();
        int maxlen = 0;
        for (int i = 1; i < 17; i++) {
            if (m[i] == null) continue;
            maxlen = 1;
            for (String s : m[i]) {
                LinkedList<String> ll = new LinkedList<>();
                ll.add(s);
                q.offer(ll);
            }
        }
        while (!q.isEmpty()) {
            LinkedList<String> ll = q.poll();
            int len = ll.peekLast().length() + 1;
            if (m[len] == null) continue;
            String small = ll.peekLast();
            for (String large : m[len]) {
                if (match(small, large)) {
                    LinkedList<String> newl = new LinkedList<>(ll);
                    newl.addLast(large);
                    q.offer(newl);
                    maxlen = Math.max(maxlen, newl.size());
                }
            }
        }
        return maxlen;
    }

    boolean match(String s, String l) {
        int i = 0, j = 0;
        boolean flag = false;
        while (i < s.length() && j < l.length()) {
            if (s.charAt(i) == l.charAt(j)) {
                i++; j++;
            } else if (!flag) {
                flag = false;
                j++;
            } else {
                return false;
            }
        }
        return true;
    }
}

// DFS from submissions best performant
class Longest_String_Chain_1048_IV {
    public int longestStrChain(String[] words) {
        if (words.length < 2) {
            return words.length;
        }
        int maxLen = 0;
        List<String>[] map = new List[17];
        for (int i = 0; i < 17; i++) {
            map[i] = new ArrayList<>();
        }
        // O(N); N = num of words
        for (String word : words) {
            int len = word.length();
            map[len].add(word);
            maxLen = Math.max(len, maxLen);
        }
        int longestChain = 0;
        for (int j = maxLen; j > 1 && j > longestChain; j--) {
            longestChain = Math.max(longestChain, calculateMaxDepth(map, j, null));
        }
        return longestChain;
    }

    private int calculateMaxDepth(List<String>[] map, int len, String prev) {
        List<String> words = map[len];
        if (words.size() == 0) {
            return 0;
        }
        int max = 0;
        for (String s : words) {
            if (isPredecessor(s, prev)) {
                int depth = calculateMaxDepth(map, len - 1, s);
                max = Math.max(max, depth + 1);
            }
        }
        return max;
    }

    private boolean isPredecessor(String word, String prev) {
        if (prev == null) return true;
        int i = 0, j = 0;
        while (i < prev.length() && j < word.length()) {
            if (prev.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                if (i - j > 1) return false;
            }
        }
        return i - j <= 1;
    }
}

// My original solution works after all! Just modify
// match() to not use HashSet.
class Longest_String_Chain_1048_V {
    public int longestStrChain(String[] words) {
        if (words.length == 1) return 1;
        List<Node>[] m = new List[18];
        int maxlen = 0;
        for (String w : words) {
            int len = w.length();
            maxlen = Math.max(len, maxlen);
            if (m[len] == null) m[len] = new ArrayList<>();
            m[len].add(new Node(w, 1));
        }
        int res = 1;
        for (int i = maxlen; i >= 0; i--) {
            if (m[i] == null) continue;
            for (Node n1 : m[i]) {
                String w1 = n1.w;
                int w1len = w1.length();
                if (m[w1len - 1] == null) break;
                for (Node n2 : m[w1len - 1]) {
                    if (n2.len >= n1.len + 1) continue;
                    String w2 = n2.w;
                    if (match(w1, w2)) {
                        n2.len = Math.max(n2.len, n1.len + 1);
                        res = Math.max(res, n2.len);
                    }
                }
            }
        }
        return res;
    }

    boolean match(String s, String t) {
        int i = 0, j = 0;
        boolean flag = false;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else if (!flag) {
                i++;
                flag = true;
            } else {
                return false;
            }
        }
        return true;
    }

    class Node {
        String w;
        int len;
        public Node(String s, int l) {
            w = s;
            len = l;
        }
    }
}

class Longest_String_Chain_1048_VI {

    public int longestStrChain(String[] words) {
        int startLen = 0;
        // No need to sort, just need to iterate from the longest length to 1;
        // And preprocessing the words and store their index to the map based on the length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            map.computeIfAbsent(w.length(), e -> new ArrayList<>());
            map.get(w.length()).add(i);
            startLen = Math.max(startLen, w.length());
        }
        // array which store the longest sequence which start from this word.
        int[] len = new int[words.length];
        Arrays.fill(len, 1);
        while (startLen > 0) {
            if (!map.containsKey(startLen - 1)) {
                startLen--;
                continue;
            }
            List<Integer> sourceList = map.get(startLen);
            List<Integer> targetList = map.get(startLen - 1);
            for (int i : sourceList) {
                String source = words[i];
                for (int j : targetList) {
                    String target = words[j];
                    if (isOneDiff(source, target)) {
                        // DP: only update the target len[i] when current length is bigger
                        len[j] = Math.max(len[j], len[i] + 1);
                    }
                }
            }
            startLen--;
        }
        int res = 0;
        for (int e : len) {
            res = Math.max(res, e);
        }
        return res;
    }

    public boolean isOneDiff(String s, String t) {
        int i = 0, j = 0;
        boolean flag = false;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else if (!flag) {
                i++;
                flag = true;
            } else {
                return false;
            }
        }
        return true;
    }
}

class Longest_String_Chain_1048_X {
    public int longestStrChain(String[] words) {
        List<String>[] bm = new List[18];
        int maxlen = 0;
        for (String w : words) {
            int len = w.length();
            maxlen = Math.max(len, maxlen);
            if (bm[len] == null) bm[len] = new ArrayList<>();
            bm[len].add(w);
        }
        Map<String, Integer> m = new HashMap<>();
        int res = 0;
        for (int j = 1; j <= maxlen; j++) {
            if (bm[j] == null) continue;
            for (String w : bm[j]) {
                int best = 1;
                for (int i = 0; i < w.length(); i++) {
                    String w2 = w.substring(0, i) + w.substring(i + 1);
                    best = Math.max(best, m.getOrDefault(w2, 0) + 1);
                }
                m.put(w, best);
                res = Math.max(best, res);
            }
        }
        return res;
    }
}

class Longest_String_Chain_1048_VII {
    // No hashmap, again a bruteforce
    public int longestStrChain(String[] words) {
        if (words.length == 1) return 1;
        List<Node>[] m = new List[18];
        int maxlen = 0;
        for (String w : words) {
            int len = w.length();
            maxlen = Math.max(len, maxlen);
            if (m[len] == null) m[len] = new ArrayList<>();
            m[len].add(new Node(w, 1));
        }
        int res = 1;
        for (int i = maxlen; i >= 0; i--) {
            if (m[i] == null) continue;
            for (Node n1 : m[i]) {
                String w1 = n1.w;
                int w1len = w1.length();
                if (m[w1len - 1] == null) break;
                for (Node n2 : m[w1len - 1]) {
                    if (n2.len >= n1.len + 1) continue;
                    String w2 = n2.w;
                    if (match(w2, w1)) {
                        n2.len = Math.max(n2.len, n1.len + 1);
                        res = Math.max(res, n2.len);
                    }
                }
            }
        }
        return res;
    }

    boolean match(String s, String l) {
        Set<Character> set = new HashSet<>();
        for (char c : l.toCharArray()) {
            set.add(c);
        }
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) return false;
        }
        return true;
    }

    class Node {
        String w;
        int len;
        public Node(String s, int l) {
            w = s;
            len = l;
        }
    }
}
*/