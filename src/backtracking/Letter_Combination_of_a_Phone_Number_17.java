package backtracking;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
*/
class Letter_Combination_of_a_Phone_Number_17 {
    HashMap<String, String> phone = new HashMap<String, String>() {
        {
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }
    };

    List<String> result = new ArrayList<String>();


    public void backtrack(String combination, String next_digits) {
        if (next_digits.length() == 0) { // if there is no more digits to check;
            System.out.println(combination);
            result.add(combination);     // the combination is done.
        } else {
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinationsAnswer(String digits) {
        if (digits.isEmpty() || digits.length() <= 0) {
            return null;
        }

        backtrack("", digits);

        return result;
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length() == i) {
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }


    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while(ans.peek().length() != digits.length()) {
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length()) - '0'];
            for(char c : map.toCharArray()) {
                ans.addLast(remove + c);
            }
        }
        return ans;
    }


    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };



    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ret = new LinkedList<String>();
        if (digits == null || digits.isEmpty() || digits.length == 0) return ret;
        combination("", digits, 0, ret);
        return ret;
    }

    public static void main(String[] args) {
        List<String> result = new Letter_Combination_of_a_Phone_Number_17().letterCombinationsAnswer("23");

        for(String item : result) {
            System.out.println(item);
        }
    }
}