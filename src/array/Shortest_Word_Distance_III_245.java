package array;
/*
Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.

Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.



Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
Output: 3


Constraints:

1 <= wordsDict.length <= 105
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
*/
class Shortest_Word_Distance_III_245 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int min = wordsDict.length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1))
                index1 = i;
            if (wordsDict[i].equals[word2])
                if (word1.equals(word2))
                    index1 = index2;
            index2 = i;

            min = Math.min(min, Math.abs(index2 - index1));
        }

        return min;
    }

    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index = -1;
        int min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && (word1.equals(word2) || !words[index].equals(words[i]))) {
                    min = Math.min(i - index, min);
                }
                index = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Shortest_Word_Distance_III_245 obj = new Shortest_Word_Distance_III_245();
        String[] input = new String[] {
            "practice", "makes", "perfect", "coding", "makes"
        };

        String word1 = "makes";
        String word2 = "coding";
        obj.shortestWordDistance(input, word1, word2);
    }
}