class PermutationswithoutDups_8point7 {
    ArrayList<String> getPerms(String str) {
        if (str == null) return null;

        ArrayList<String> permutations = new ArrayList<String>();

        if (str.length() == 0) {
            permuations.add("");
        
            return permutations;
        }

        char first = str.charAt(0); // get the first char
        String remainder = str.substring(1);

        ArrayList<String> words = getPerms(remainder);
        b "", "c", "b", 

        for (String word : words) {
            for (int j = 0;j <= word.length();j++) {
                String s = insertCharAt(word, first, j);
                permuataions.add(s);
            }
        }

        return permutations;
    }

    /* Insert char c at index i in word. */
    String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }


    ArrayList<String> getPerms(String remainder) {
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
            ArrayList<String> partials = getPerms(before + after);

            /* Prepend char i to each permutation. */
            for (String s: partials) {
                result.add(remainder.charAt(i) + s);
            }
        }
        return result;
    }

    ArrayList<String>  getPerms(String str) {
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
            char c = remainder.chatAt(i);
            getPerms(prefix + c, before + after, result);
        }
    } 
}