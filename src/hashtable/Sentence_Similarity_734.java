package hashtable;

import java.util.*;

/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

 */
public class Sentence_Similarity_734 {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if ((words1 == null && words2 == null) || (words1 == null && words2.length <= 0) || (words1.length <= 0 && words2 == null) || (words1.length <=0 && words2.length <= 0)) {
            return true;
        }

        if (words1.length <= 0) {
            return false;
        }

        if (words2.length <= 0) {
            return false;
        }

        if (words1.length != words2.length) {
            return false;
        }

        if (pairs == null || pairs.size() <= 0) {
            Set<String> words2Set = new HashSet<String>();

            for(String word: words2) {
                words2Set.add(word);
            }

            for(String word: words1) {
                if (!words2Set.contains(word)) {
                    return false;
                }
            }
        }

        /*
        Map<String, Set<String>> myMap = new HashMap<String, Set<String>>();
        for (List<String> pair : pairs) {
            myMap.putIfAbsent(pair.get(0), new HashSet<>());
            myMap.putIfAbsent(pair.get(1), new HashSet<>());
            myMap.get(pair.get(0)).add(pair.get(1));
            myMap.get(pair.get(1)).add(pair.get(0));
        }
        Set<String> words2Set = new HashSet<String>();

        for (String word: words2) {
            words2Set.add(word);
        }

        for (String word: words1) {
            // if ((myMap.containsKey(word) && words2Set.contains(myMap.get(word))))
        }
        */
        
        Set<String> pairset = new HashSet();
        for (List<String> pair: pairs)
            pairset.add(pair.get(0) + "#" + pair.get(1));

        for (int i = 0; i < words1.length; ++i) {
            if (!words1[i].equals(words2[i]) &&
                    !pairset.contains(words1[i] + "#" + words2[i]) &&
                    !pairset.contains(words2[i] + "#" + words1[i]))
                return false;
        }
        return true;



    }

    public static void main(String[] args) {
        List<List<String>> test = new ArrayList<List<String>>();
        System.out.println(new Sentence_Similarity_734().areSentencesSimilar(
                new String[]{"great"},
                new String[]{"great"},
                test
        ));

        System.out.println(new Sentence_Similarity_734().areSentencesSimilar(
                new String [] { "great", "acting", "skills" },
                new String [] { "fine", "drama", "talent" },
                Arrays.asList(
                        Arrays.asList("great", "fine"),
                        Arrays.asList("acting", "drama"),
                        Arrays.asList("skills", "talent")
                )
        ));

        System.out.println(new Sentence_Similarity_734().areSentencesSimilar(
                new String [] { "an","extraordinary","meal" },
                new String [] { "one","good","dinner" },
                Arrays.asList(
                        Arrays.asList("great", "fine"),
                        Arrays.asList("acting", "drama"),
                        Arrays.asList("skills", "talent")
                )
        ));

// [["great","good"],["extraordinary","good"],["well","good"],["wonderful","good"],["excellent","good"],["fine","good"],["nice","good"],["any","one"],["some","one"],["unique","one"],["the","one"],["an","one"],["single","one"],["a","one"],["truck","car"],["wagon","car"],["automobile","car"],["auto","car"],["vehicle","car"],["entertain","have"],["drink","have"],["eat","have"],["take","have"],["fruits","meal"],["brunch","meal"],["breakfast","meal"],["food","meal"],["dinner","meal"],["super","meal"],["lunch","meal"],["possess","own"],["keep","own"],["have","own"],["extremely","very"],["actually","very"],["really","very"],["super","very"]]
    }
}
