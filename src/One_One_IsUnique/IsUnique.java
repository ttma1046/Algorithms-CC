package One_One_IsUnique;

import java.util.HashMap;
import java.util.HashSet;

public class IsUnique {
    private static int NUMBER_LETTERS = 26;
    public static void main(String[] args) {
        System.out.println(isUnique("tes"));
    }

    static Boolean IsStringUnique(String input)
    {
        if (input == "" || input == null)
            return true;

        for (int i = 0; i < input.length() - 1; i++)
        {
            for (int j = i + 1; j < input.length(); j++)
            {
                if (input.charAt(i) == input.charAt(j))
                {
                    return false;
                }
            }
        }

        return true;
    }

    static Boolean IsUniqueHash(String input) {
        if (input == "" || input == null)
            return true;

        HashMap<Character, Boolean> hash = new HashMap<Character, Boolean>();

        for (int i = 0; i < input.length(); i++)
        {
            if (hash.containsKey(input.charAt(i))) {
                return false;
            }

            hash.put(input.charAt(i), true);
        }
        return true;
    }

    static int getCharCode(Character c) {
        int offset = (int)'a';
        int code = c - offset;
        
        return code;
    }


    static Boolean IsUniqueArray(String input) {
        if (input == "" || input == null)
            return true;

        boolean[] charCounts = new boolean[NUMBER_LETTERS];
        for (int i = 0; i < input.length(); i++)
        {
            int index = getCharCode(input.charAt(i));
            if (charCounts[index]) {
                return false;
            }

            charCounts[index] = true;
        }
        return true;
    }

    private static boolean isUnique(String input) {
        char[] phrase = input.toCharArray();

        java.util.Arrays.sort(phrase);
        for (int i = 0; i < phrase.length - 1; i++)
            if (phrase[i] == phrase[i + 1])
                return false;
        return true;
    }
}
