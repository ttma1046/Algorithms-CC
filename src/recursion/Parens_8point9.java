package recursion;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
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
    Set<String> generateParensIII(int remaining) {
        Set<String> result = new HashSet<String>();
        if (remaining == 1) {
            result.add("()");
        } else {
            Set<String> prev = generateParensIII(remaining - 1);

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

    void addParen(List<String> result, String currentString, int left, int right, int max) {
        if (currentString.length() == max * 2) { /* Out of left and right parentheses */
            result.add(currentString);
            return;
        } else {
            if (left < max) {
                addParen(result, currentString + "(", left + 1, right, max);
            }

            if (left > right) {
                addParen(result, currentString + ")", left, right + 1, max);
            }
        }
    }



    List<String> generateParenthesis(int count) {
        List<String> result = new ArrayList<String>();

        addParen(result, "", 0, 0, count);
        return result;
    }

/*
Complexity Analysis

Our complexity analysis rests on understanding how many elements there are in generateParenthesis(n). This analysis is outside the scope of this article, but it turns out this is the n-th Catalan number \dfrac{1}{n+1}\binom{2n}{n} 
Catalan number 
  1    2n
----- (  )
 n+1   n
, which is bounded asymptotically by
  Math.pow(4, n)
-------------------
 n * Math.sqrt(n)

* Time Complexity: 

    Math.pow(4, n)
O(-------------------)
     Math.sqrt(n)

Each valid sequence has at most n steps during the backtracking procedure.

* Space Complexity: 

    Math.pow(4, n)
O(-------------------)
     Math.sqrt(n)   

, as described above, and using O(n) space to store the sequence.
*/
    public List<String> generateParenthesis(int n) {
         List<String> res = new ArrayList<>();
         helper(res, new StringBuilder(), 0, 0, n);
         return res;
    }

    private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
        if(open == n && close == n) {
            res.add(sb.toString());
            return;
        }

        if(open < n) {
            sb.append("(");
            helper(res, sb, open + 1, close, n);
            sb.setLength(sb.length() - 1);
        }

        if(close < open) {
            sb.append(")");
            helper(res, sb, open, close + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        /*
        Set<String> result = new Parens_8point9().generateParensIII(4);

        for(String word: result) {
            System.out.println(word);
        }

        result = new Parens_8point9().generateParens(4);

        for(String word: result) {
            System.out.println(word);
        }
        */

        List<String> resulttwo = new Parens_8point9().generateParenthesis(3);

        for(String word: resulttwo) {
            System.out.println(word);
        }
    }
}