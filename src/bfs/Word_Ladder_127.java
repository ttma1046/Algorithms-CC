package bfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList

is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList,

return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/
class Word_Ladder_127 {
    public static void main(String[] args) {
        Word_Ladder_127 obj = new Word_Ladder_127();
        String beginWord = "hot";
        String endWord = "cog";
        List<String> list = new ArrayList<String>();
        list.add("hot");
        obj.ladderLength(beginWord, endWord, new ArrayList<String>());
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(), visited = new HashSet<>();
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) return 0;

        int step = 1, length = beginWord.length();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            Set<String> nextSet = new HashSet<>();

            for (String word: beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < length; ++i) 
                    for (char c = 'a'; c <= 'z'; ++c) {
                        char pre = chs[i];
                        chs[i] = c;
                        String nextWord = new String(chs);
                        if (endSet.contains(nextWord)) return step + 1;
                        if (visited.add(nextWord) && wordSet.contains(nextWord)) nextSet.add(nextWord);
                        chs[i] = pre;
                    }
            }

            if (endSet.size() < nextSet.size()) {
                beginSet = endSet;
                endSet = nextSet;
            } else beginSet = nextSet;
            step++;
        }

        return 0;
    }

    public int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Set<String> left = new HashSet<>();
        Set<String> right = new HashSet<>();

        left.add(beginWord);
        right.add(endWord);
        int step = 0;
        
        while (!left.isEmpty() && !right.isEmpty()) {
            step++;
            Set<String> next = new HashSet<>();
            if (left.size() > right.size()) {
                Set<String> temp = right;
                right = left;
                left = temp;
            }
            for (String s : left) {
                char[] cur = s.toCharArray();
                for (int i = 0; i < cur.length; i++) {
                    char curChar = cur[i];
                    for (int j = 0; j < 26; j++) {
                        char c = (char) ('a' + j);
                        if (c == curChar) continue;
                        cur[i] = c;
                        String newStr = new String(cur);
                        if (right.contains(newStr)) return step + 1;
                        if (words.contains(newStr)) {
                            words.remove(newStr);
                            next.add(newStr);
                        }
                    }
                    cur[i] = curChar;
                }
            }
            left = next;
        }
        return 0;
    }

    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        int step = 1, N = beginWord.length();

        while(queue.size() > 0) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                String str = queue.poll();

                for (int j = 0; j < N; ++j) {
                    for (char letter = 'a'; letter <= 'z'; ++letter) {
                        StringBuilder next = new StringBuilder(str);
                        next.setCharAt(j, letter);

                        String nextWord = next.toString();

                        if (set.contains(nextWord)) {
                            if (nextWord.equals(endWord)) return step + 1;

                            set.remove(nextWord);
                            queue.offer(nextWord);
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }
}