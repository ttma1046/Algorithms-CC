package resursion;

import java.util.ArrayList;

class Permutations_without_Dups_8point7 {
    ArrayList<String> getPermsI(String str) {
        if (str == null) {
            System.out.println("null str");
            return null;
        }

        ArrayList<String> permutations = new ArrayList<String>();

        if (str.length() == 0) {
            System.out.println("empty string");
            permutations.add("");
        
            return permutations;
        }

        char first = str.charAt(0); // get the first char
        System.out.println("first:" + first);
        String remainder = str.substring(1);
        System.out.println("remainder:" + remainder);
        ArrayList<String> words = getPermsI(remainder);
        // b "", "c", "b",

        for (String word : words) {
            System.out.println("word:" + word);
            System.out.println("word.length(): " + word.length());
            for (int j = 0;j <= word.length();j++) {
                String s = insertCharAt(word, first, j);
                System.out.println("s:" + s);
                permutations.add(s);
                for(String perm: permutations) {
                    System.out.println("perm:" + perm);
                }
            }
        }

        return permutations;
    }

    /* Insert char c at index i in word. */
    String insertCharAt(String word, char first, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        System.out.println("insertCharAt return:" + start + first + end);
        return start + first + end;
    }

    public static void main(String[] args) {
        ArrayList<String> result = new Permutations_without_Dups_8point7().getPermsI("abcd");

        for(String word: result)   {
            System.out.println(word);
        }
    }



    ArrayList<String> getPermsII(String remainder) {
        int len = remainder.length();

        ArrayList<String> result = new ArrayList<String>();

        /* Base case. */
        if (len == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < len; i++) {
            /* Remove char i and find permuataions of remaining chars. */
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            ArrayList<String> partials = getPermsII(before + after);

            /* Prepend char i to each permutation. */
            for (String s: partials) {
                result.add(remainder.charAt(i) + s);
            }
        }
        return result;
    }

    ArrayList<String> getPerms(String str) {
        ArrayList<String> result = new ArrayList<String>();

        getPerms("", str, result);
        return result;
    }

    void getPerms(String prefix, String remainder, ArrayList<String> result) {
        if (remainder.length() == 0) result.add(prefix);

        int len = remainder.length();
        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            char c = remainder.charAt(i);
            getPerms(prefix + c, before + after, result);
        }
    } 
}