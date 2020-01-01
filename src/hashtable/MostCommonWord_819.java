package hashtable;

import java.util.*;

public class MostCommonWord_819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || banned == null) {
            return null;
        }
        Set<String> ban = new HashSet<String>(Arrays.asList(banned));
        Map<String, Integer> myMap = new HashMap<String, Integer>();

        for (String word: paragraph.replaceAll("\\W+" , " ").toLowerCase().split("\\s+")) {
            if (!ban.contains(word))
                myMap.put(word, myMap.getOrDefault(word, 0) + 1);
        }

        int maxFrequence = 0;
        String maxFrequentWord = "";
        for (String word: myMap.keySet()) {
            if (myMap.get(word) > maxFrequence) {
                maxFrequence = myMap.get(word);
                maxFrequentWord = word;
            }
        }

        return maxFrequentWord;
    }

    public static void main(String[] args) {
        System.out.println(new MostCommonWord_819().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] { "hit" }));
    }
}
