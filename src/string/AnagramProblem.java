package string;

public class AnagramProblem {
    public static int NUMBER_LETTERS = 26;

    public static int getDelta(int[] arrayA, int[] arrayB) {
        if(arrayA.length != arrayB.length) {
            return -1;
        }
        int delta = 0;

        for (int i = 0; i < arrayA.length; i++) {
            int difference = Math.abs(arrayA[i] - arrayB[i]);
            delta += difference;
        }

        return delta;
    }

    public static int[] getCharCounts(String s) {
        int[] charCounts = new int[NUMBER_LETTERS];
        for (int i = 0;i < s.length(); i++) {
            char c = s.charAt(i);
            int offset = (int)'a';
            int code = c - offset;
            charCounts[code]++;
        }
        return charCounts;
    }

    // Complete the makeAnagram function below.
    static int makeAnagram(String first, String second) {
        int[] charCountA = getCharCounts(first);
        int[] charCountB = getCharCounts(second);

        return getDelta(charCountA, charCountB);

    }
}
