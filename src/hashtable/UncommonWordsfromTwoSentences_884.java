package hashtable;

import java.util.*;

public class UncommonWordsfromTwoSentences_884 {
    public String[] uncommonFromSentencesII(String A, String B) {
        Map<String, Integer> count = new HashMap<String, Integer>();
        for (String word: A.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);
        for (String word: B.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);

        List<String> ans = new LinkedList<String>();
        for (String word: count.keySet())
            if (count.get(word) == 1)
                ans.add(word);

        return ans.toArray(new String[ans.size()]);
    }

    public String[] uncommonFromSentencesIII(String A, String B) {
        Map<String, Integer> count = new HashMap<>();
        for (String w : (A + " " + B).split(" ")) {
            count.put(w, count.getOrDefault(w, 0) + 1);
        }

        ArrayList<String> res = new ArrayList<>();

        for (String w : count.keySet()) {
            if (count.get(w) == 1) {
                res.add(w);
            }
        }

        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] result = new UncommonWordsfromTwoSentences_884().uncommonFromSentencesIII("this apple is sweet", "this apple is sour");
        for (String s: result) {
            System.out.println(s);
        }

        result = new UncommonWordsfromTwoSentences_884().uncommonFromSentencesIII("apple apple", "banana");
        for (String s: result) {
            System.out.println(s);
        }
    }
}
