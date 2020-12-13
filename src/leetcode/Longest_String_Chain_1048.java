package leetcode;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


class Longest_String_Chain_1048 {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (String word : words) {
            System.out.println(word);
        }


        int res = 0;
        for (String word : words) {
            System.out.println("word:" + word);

            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String head = word.substring(0, i);
                String tail =  word.substring(i + 1);

                System.out.println("head:" + head);
                System.out.println("tail:" + tail);


                String prev = head + tail;
                System.out.println("prev:" + prev);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
                System.out.println("best:" + best);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[] {
            "a", "b", "ba", "bca", "bda", "bdca"
        };

        new Longest_String_Chain_1048().longestStrChain(words);
    }
}