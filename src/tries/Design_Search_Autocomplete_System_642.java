package tries;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').

You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.

Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Implement the AutocompleteSystem class:

AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
List<String> input(char c) This indicates that the user typed the character c.
Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.


Example 1:

Input
["AutocompleteSystem", "input", "input", "input", "input"]
[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
Output
[null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]

Explanation
AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
obj.input("a"); // return []. There are no sentences that have prefix "i a".
obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.


Constraints:

n == sentences.length
n == times.length
1 <= n <= 100
1 <= sentences[i].length <= 100
1 <= times[i] <= 50
c is a lowercase English letter, a hash '#', or space ' '.
Each tested sentence will be a sequence of characters c that end with the character '#'.
Each tested sentence will have a length in the range [1, 200].
The words in each input sentence are separated by single spaces.
At most 5000 calls will be made to input.
*/

class TrieNode {
    Map<Character, TrieNode> children;
    Map<String, Integer> counts;
    boolean isWord;
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        counts = new HashMap<String, Integer>();
        isWord = false;
    }
}

class Pair {
    String s;
    int c;
    public Pair(String s, int c) {
        this.s = s;
        this.c = c;
    }
}

class AutocompleteSystem {
    TrieNode root;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";

        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }

        // TrieNode curr = root;
        // dfs(curr, 0);
    }

    private void dfs(TrieNode curr, int level) {
        if (curr.counts.size() > 0) {
            for (Map.Entry<String, Integer> entry : curr.counts.entrySet()) {
                for (int i = 0; i < level; i++) System.out.print(" ");
                System.out.print(entry.getKey() + ":" + entry.getValue());
                System.out.println();
            }
        } else {
            for (int i = 0; i < level; i++) System.out.print(" ");
            System.out.println("current node no counts");
        }

        if (curr.children.size() > 0) {
            for (Map.Entry<Character, TrieNode> entry : curr.children.entrySet()) {
                for (int i = 0; i < level; i++) System.out.print(" ");
                System.out.print(entry.getKey());
                System.out.println();
                dfs(entry.getValue(), level + 1);
            }
        }
    }

    private void add(String s, int count) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.children.get(c);
            if (next == null) {
                next = new TrieNode();
                curr.children.put(c, next);
            }

            curr = next;
            curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
        }
        curr.isWord = true;
    }

    // ["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]
    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }

        prefix = prefix + c;
        TrieNode curr = root;
        for (char cc : prefix.toCharArray()) {
            TrieNode next = curr.children.get(cc);
            if (next == null) return new ArrayList<String>();
            
            curr = next;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));

        for (String s : curr.counts.keySet())
            pq.add(new Pair(s, curr.counts.get(s)));


        List<String> res = new ArrayList<String>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++)
            res.add(pq.poll().s);

        return res;
    }
}


public class Design_Search_Autocomplete_System_642 {
    public static void main(String[] args) {
        String[] strs = new String[] { "i love you", "island", "iroman", "i love leetcode" };
        int[] times = new int[] {5, 3, 2, 2};
        AutocompleteSystem obj = new AutocompleteSystem(strs, times);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */