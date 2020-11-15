package leetcode;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.PriorityQueue;

class Top_k_Frequent_Words_692 {
/*    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                         w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
            (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
            w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }*/

    public List<String> topKFrequent(String[] input, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String item : input) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        List<String> words = new ArrayList<String>(map.keySet());

        // Collections.sort(words, (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1));

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1);
            }
        });

        return words.subList(0, k);
    }

    public List<String> topKFrequentII(String[] input, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String item: input) map.put(item, map.getOrDefault(item, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<String>((w1, w2) -> map.get(w1).equals(map.get(w2)) ? 
            w2.compareTo(w1) : map.get(w1) - map.get(w2)
        );

        for (String item: map.keySet()) {
            pq.offer(item);
            if (pq.size() > k) pq.poll();
        }

        List<String> ans = new ArrayList<String>();
        while(!pq.isEmpty()) ans.add(pq.poll());
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        List<String> list = new Top_k_Frequent_Words_692().topKFrequentII(new String[] { "leetcode", "I", "I", "leetcode", "next"}, 2);

        for (String test : list) {
            System.out.println(test);
        }

        list = new Top_k_Frequent_Words_692().topKFrequentII(new String[] { "i", "love", "leetcode", "i", "love", "coding" }, 2);

        for (String test : list) {
            System.out.println(test);
        }
        
        list = new Top_k_Frequent_Words_692().topKFrequentII(new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "make" }, 4);

        for (String test : list) {
            System.out.println(test);
        }
    }
}