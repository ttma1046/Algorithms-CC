package recursion;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

class Parens_8point9 {
    /*
    Our first thought here might be to apply a recursive approach where we build the solution for f(n) by
    adding pairs of parentheses to f(n -1) . That's certainly a good instinct.
    Let's consider the solution for n = 3:
    
    How might we build this from n = 2?

    We can do this by inserting a pair of parentheses inside every existing pair of parentheses, as well as one
        at the beginning of the string. Any other places that we could insert parentheses, such as at the end of the
        string, would reduce to the earlier cases.
        So, we have the following:
        But wait- we have some duplicate pairs listed. The string () ( () ) is listed twice.
    If we're going to apply this approach, we'll need to check for duplicate values before adding a string to our
    list.
    */
    Set<String> myGenerateParens(int remaining) {
        Set<String> result = new HashSet<String>();
        if (remaining == 1) {
            result.add("()");
        } else {
            Set<String> prev = myGenerateParens(remaining - 1);

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

    Set<String> generateParens(int remaining) {
        Set<String> result = new HashSet<String>();
        if (remaining == 0) {
            result.add("");
        } else {
            Set<String> prev = generateParens(remaining - 1);

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

    /*

We can avoid this duplicate string issue by building the string from scratch. 

Under this approach, we add left and right parens, as long as our expression stays valid.

On each recursive call, we have the index for a particular character in the string. 
We need to select either a left or a right paren. 

When can we use a left paren, and when can we use a right paren?
1. Left Paren: As long as we haven't used up all the left parentheses, we can always insert a left paren.
2. Right Paren: We can insert a right paren as long as it won't lead to a syntax error. When will we get a
syntax error? We will get a syntax error if there are more right parentheses than left.

So, we simply keep track of the number of left and right parentheses allowed. If there are left parens
remaining, we'll insert a left paren and recurse. If there are more right parens remaining than left (i.e., if
there are more left parens in use than right parens), then we'll insert a right paren and recurse.

    */

    ArrayList<String> myGenerateParensII(int count) {
        ArrayList<String> result = new ArrayList<String>();

        int leftRem = count;
        int rightRem = count;

        return buildString(result, "(", leftRem - 1, rightRem);
    }

    ArrayList<String> buildString(ArrayList<String> result, String head, int leftRem, int rightRem) {
        if (leftRem > 0) {
            buildString(result, head + "(", leftRem - 1, rightRem);
        }

        if (rightRem > leftRem) {
            buildString(result, head + ")", leftRem, rightRem - 1);
        }

        if (leftRem == 0 && rightRem == 0) {
            result.add(head);
        }

        return result;
    }

    void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) return; // invalid state;

        if (leftRem == 0 && rightRem == 0) { /* Out of left and right parentheses */
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '('; // Add left and recurse.
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right and recurse.
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    ArrayList<String> generateParensII(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<String>();

        addParen(list, count, count, str, 0);
        return list;
    }

    public static void main(String[] args) {
/*        Set<String> result = new Parens_8point9().myGenerateParens(4);

        for(String word: result) {
            System.out.println(word);
        }

        result = new Parens_8point9().generateParens(4);

        for(String word: result) {
            System.out.println(word);
        }*/

        ArrayList<String> resulttwo = new Parens_8point9().myGenerateParensII(4);

        for(String word: resulttwo) {
            System.out.println(word);
        }

        resulttwo = new Parens_8point9().myGenerateParensII(4);

        for(String word: resulttwo) {
            System.out.println(word);
        }
    }
}