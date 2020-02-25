package recursion;

import java.util.HashSet;
import java.util.Set;

class Parens_8point9 {
    Set<String> generateParens(int remaining) {
        Set<String> result = new HashSet<String>();
        if (remaining == 1) {
            result.add("()");
        } else {
            Set<String> prev = generateParens(remaining - 1);

            for(String comb: prev) {
                for (int i = 0; i < comb.length(); i++) {
                    if (comb.charAt(i) == '(') {
                        result.add(comb.substring(0, i + 1) + "()" + comb.substring(i + 1, comb.length()));
                    }
                }
                
                result.add("()" + comb);
            }
        }

        return result;
    }

    Set<String> generateParensII(int remaining) {
        Set<String> result = new HashSet<String>();
        if (remaining == 0) {
            result.add("");
        } else {
            Set<String> prev = generateParensII(remaining - 1);

            for(String comb: prev) {
                for (int i = 0; i < comb.length(); i++) {
                    if (comb.charAt(i) == '(') {
                        String newComb = insertInside(comb, i);
                        result.add(newComb);
                    }
                }
                
                result.add("()" + comb);
            }
        }

        return result;
    }

    String insertInside(String str, int leftIndex) {
        String left = str.substring(0, leftIndex + 1);
        String right = str.substring(leftIndex + 1, str.length());
        return left + "()" + right;
    }

    public static void main(String[] args) {
        Set<String> result = new Parens_8point9().generateParens(3);

        for(String word: result) {
            System.out.println(word);
        }

        result = new Parens_8point9().generateParensII(3);

        for(String word: result) {
            System.out.println(word);
        }
    }
}