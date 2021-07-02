package array;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/*
Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

Implement the WordDistance class:

WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.


Example 1:

Input
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
Output
[null, 3, 1]

Explanation
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // return 3
wordDistance.shortest("makes", "coding");    // return 1


Constraints:

1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
At most 5000 calls will be made to shortest.
*/
class Shortest_Word_Distance_II_244 {
    private Map<String, List<Integer>> map;
    private int length;

    public Shortest_Word_Distance_II_244(String[] words) {
        map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!map.containsKey(word)) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(word, list);
            } else {
                map.get(word).add(i);
            }
        }

        this.length = words.length;
    }

    int shortest(String word1, String word2) {
        List<Integer> index1List = map.get(word1);
        List<Integer> index2List = map.get(word2);

        int min = this.length;
        for (int i = 0, j = 0; i < index1List.size() && j < index2List.size(); ) {
            int list1 = index1List.get(i), list2 = index2List.get(j);
            if (list2 > list1) {
                min = Math.min(min, list2 - list1);
                i++;
            } else                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        {
                min = Math.min(min, list1 - list2);
                j++;
            }
        }
        return min;
    }

    /*
    private Map<String, List<Integer>> mapII;

    public WordDistanceII(String[] words) {
        map = new HashMap<String, List<Integer>>();
        for(int i = 0; i < words.length; i++) {
            String w = words[i];
            if(map.containsKey(w)) {
                map.get(w).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(w, list);
            }
        }
    }

    public int shortestII(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int ret = Integer.MAX_VALUE;
        for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            int index1 = list1.get(i), index2 = list2.get(j);
            if(index1 < index2) {
                ret = Math.min(ret, index2 - index1);
                i++;
            } else {
                ret = Math.min(ret, index1 - index2);
                j++;
            }
        }
        return ret;
    }
    */

    /*
    private String[] wordsDict;

    public Shortest_Word_Distance_II(String[] words) {
        this.wordsDict = words;
    }

    public int shortest(String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = this.wordsDict.length;
        for (int i = 0; i < this.wordsDict.length; i++) {
            if (this.wordsDict[i].equals(word1)) {
                i1 = i;
            } else if (this.wordsDict[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }
        return minDistance;
    }
    */

    public static void main(String[] args) {
        Shortest_Word_Distance_II_244 swd = new Shortest_Word_Distance_II_244(new String[] { "practice", "makes", "perfect", "coding", "makes" });
        System.out.println(swd.shortest("coding", "practice"));
        System.out.println(swd.shortest("makes", "coding"));
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */