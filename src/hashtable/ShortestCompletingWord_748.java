package hashtable;

public class ShortestCompletingWord_748 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        if (licensePlate == null || words.length <= 0) {
            return null;
        }

        int[] plateCounts = new int[26];
        for (Character c : licensePlate.toCharArray()) {
            int index = Character.toLowerCase(c) - 'a';
            if (index >= 0 && index < 26) {
                plateCounts[index]++;
            }
        }

        int shorestLength = Integer.MAX_VALUE;
        String result = null;
        for (String word : words) {
            int[] wordCount = new int[26];
            for (Character c : word.toCharArray()) {
                int index = Character.toLowerCase(c) - 'a';
                if (index >= 0 && index < 26) {
                    wordCount[index]++;
                }
            }

            int i;
            for (i = 0; i < 26; i++) {
                if (plateCounts[i] != 0 && plateCounts[i] > wordCount[i]) {
                    break;
                }
            }

            if (i == 26 && word.length() < shorestLength) {
                result = word;
                shorestLength = word.length();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestCompletingWord_748().shortestCompletingWord("1s3 PSt",
                new String[] { "step", "steps", "stripe", "stepple" }));
        System.out.println(new ShortestCompletingWord_748().shortestCompletingWord("1s3 456",
                new String[] { "looks", "pest", "stew", "show" }));
        System.out.println(new ShortestCompletingWord_748().shortestCompletingWord("PP",
                new String[] { "pair", "supper", "stew", "show" }));
    }
}
