package leetcode;
import java.util.Set;
import java.util.HashSet;

/*
Design a data structure that supports adding new words and finding

if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure
that matches word or false otherwise.
word may contain dots '.' where dots can be matched with any letter.

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
*/

public class Design_Add_and_Search_Words_Data_Structure_211 {
    Map<Integer, Set<String>> map;

    /** Initialize your data structure here. */
    public Design_Add_and_Search_Words_Data_Structure_211() {
        map = new HashMap<Integer, HashSet<String>>();
    }

    public void addWord(String word) {
        int length = word.length();
        if (!map.containsKey(length)) {
            Set<String> mySet = new HashSet<String>();
            map.put(length, mySet);
        }
        map.get(length).add(word);
    }

    public boolean search(String word) {
        int m = word.length();

        if (map.containsKey(m)) {
            for (String w : map.get(m)) {
                int i = 0;
                while(i < m && (w.charAt(i) == word.charAt(i) || word.charAt(i) == '.')) i++;

                if (i == m) return true;
            }
        }

        return false;
    }

    /*
    This solution passes all leetcode test cases, and formally has \mathcal{O}(M \cdot N)O(M⋅N) time complexity for the search, where MM is a length of the word to find, and NN is the number of words. Although this solution is not efficient for the most important practical use cases:

    Finding all keys with a common prefix.

    Enumerating a dataset of strings in lexicographical order.

    Scaling for the large datasets. Once the hash table increases in size, there are a lot of hash collisions and the search time complexity could degrade to \mathcal{O}(N^2 \cdot M)O(N
    2
    ⋅M), where NN is the number of the inserted keys.

    Trie could use less space compared to hashmap when storing many keys with the same prefix. In this case, using trie has only \mathcal{O}(M \cdot N)O(M⋅N) time complexity, where MM is the key length, and NN is the number of keys.
    */
    public static void main(String[] args) {
        Design_Add_and_Search_Words_Data_Structure_211 wordDictionary = new Design_Add_and_Search_Words_Data_Structure_211();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap();
    boolean word = false;
    public TrieNode() {}
}

class WordDictionary {
    TrieNode trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = trie;

        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.word = true;
    }
    /*
    Complexity Analysis

    Time complexity: \mathcal{O}(M)O(M), where MM is the key length. At each step, we either examine or create a node in the trie. That takes only MM operations.

    Space complexity: \mathcal{O}(M)O(M). In the worst-case newly inserted key doesn't share a prefix with the keys already inserted in the trie. We have to add MM new nodes, which takes \mathcal{O}(M)O(M) space.
    */
    /** Returns if the word is in the node. */
    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                // if the current character is '.'
                // check all possible nodes at this level
                if (ch == '.') {
                    for (char x : node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                // if no nodes lead to answer
                // or the current character != '.'
                return false;
            } else {
                // if the character is found
                // go down to the next level in trie
                node = node.children.get(ch);
            }
        }
        return node.word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchInNode(word, trie);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */