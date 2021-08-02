package bucketsort;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.PriorityQueue;
/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
*/
class Top_k_Frequent_Words_692 {
    public static void main(String[] args) {
        List<String> list = new Top_k_Frequent_Words_692().topKFrequent(new String[] { "i", "love", "leetcode", "i", "love", "coding" }, 2);

        for (String test : list) {
            System.out.print(test);
            System.out.print(",");
        }
        System.out.println();

        list = new Top_k_Frequent_Words_692().topKFrequent(new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "make" }, 4);

        for (String test : list) {
            System.out.print(test);
            System.out.print(",");
        }
    }

    // bucket sort
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();

        for(String word : words) freq.put(word, freq.getOrDefault(word, 0) + 1);

        int maxFreq = Collections.max(freq.values());

        List<List<String>> bucket = new ArrayList<>();

        for (int i = 0; i <= maxFreq; ++i) bucket.add(new ArrayList<String>());

        for (String word : freq.keySet()) {
            int freqCount = freq.get(word);
            bucket.get(freqCount).add(word);
        }

        List<String> res = new ArrayList<>();

        int index = maxFreq;

        while (k > 0 && index > 0) {
            List<String> list = bucket.get(index--);
            Collections.sort(list, (wordOne, wordTwo) -> wordOne.compareTo(wordTwo));
            for (String t : list) {
                res.add(t);

                if (--k == 0) break;
            }
        }

        return res;
    }

    // Hash and Sort
    public List<String> topKFrequentIV(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) freq.put(word, freq.getOrDefault(word, 0) + 1);

        List<String> wordUniques = new ArrayList<>(freq.keySet());

        Collections.sort(wordUniques, (wordOne, wordTwo) -> freq.get(wordTwo) == freq.get(wordOne) ? wordOne.compareTo(wordTwo) : freq.get(wordTwo) - freq.get(wordOne));

        List<String> res = new ArrayList<String>();

        for (int i = 0; i < k; ++i) res.add(wordUniques.get(i));

        return res;
    }

    public List<String> topKFrequentIII(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String item : words) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        List<String> res = new ArrayList<String>(map.keySet());

        // Collections.sort(words, (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1));

        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1);
            }
        });

        return res.subList(0, k);
    }

    // Priority Queue
    public List<String> topKFrequentII(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String item : words) map.put(item, map.getOrDefault(item, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<String>((w1, w2) -> map.get(w1).equals(map.get(w2)) ?
                w2.compareTo(w1) : map.get(w1) - map.get(w2)
                                                            );

        for (String item : map.keySet()) {
            pq.offer(item);
            if (pq.size() > k) pq.poll();
        }

        List<String> ans = new ArrayList<String>();
        while(!pq.isEmpty()) ans.add(pq.poll());
        Collections.reverse(ans);
        return ans;
    }
}