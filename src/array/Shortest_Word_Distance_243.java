
package array;

/*
Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.

Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3

Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1

Constraints:

1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
*/
class Shortest_Word_Distance_243 {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int i1 = -1, i2 = -1;

        int min = wordsDict.length;

        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) i1 = i;
            else if (wordsDict[i].equals(word2)) i2 = i;

            if (i1 != -1 && i2 != -1) min = Math.min(min, Math.abs(i1 - i2));
        }

        return min;
    }

    public static void main(String[] args) {
        Shortest_Word_Distance_243 obj = new Shortest_Word_Distance_243();
        String[] wordsDict = new String[] { "practice", "makes", "perfect", "coding", "makes" };

        String word1 = "coding";
        String word2 = "practice";

        System.out.println(obj.shortestDistance(wordsDict, word1, word2));
    }
}