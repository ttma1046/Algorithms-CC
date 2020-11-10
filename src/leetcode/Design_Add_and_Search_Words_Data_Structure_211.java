package leetcode;
import java.util.Set;
import java.util.HashSet;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

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
	private Set<String> set;
	/** Initialize your data structure here. */
	public Design_Add_and_Search_Words_Data_Structure_211() {
		set = new HashSet<>();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		set.add(word);
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		if (set.contains(word)) {
			return true;
		}

		for (String matched: set) {
			if (matched.length() != word.length()) {
				continue;
			}

			int i = 0;
			for (i = 0; i < word.length(); i++) {
				if (word.charAt(i) == '.') {
					continue;
				}

				if (word.charAt(i) != matched.charAt(i)) {
					break;
				}
			}

			if (i == word.length()) {
				return true;
			} else {
				continue;
			}
		}

		return false;
	}

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

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */