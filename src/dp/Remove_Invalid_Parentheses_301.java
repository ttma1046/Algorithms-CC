package dp;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;

/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.



Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]


Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
*/


class MyPair {
    String str;
    int count;

    MyPair(string s, int c) {
        this.str = s;
        this.count = c;
    }
}

class Remove_Invalid_Parentheses_301 {
    /*
    public boolean removeInvalidParentheses(String s) {

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;

            if (s.charAt(i) == ')') {
                if (count == 0) return false;
                count--;
            }
        }

        if (count > 0) return false;
        return true;
    }
    */

    /*
    private Set<String> validExpressions = new HashSet<String>();


    public List<String> removeInvalidParentheses(String s) {
        int count = 0;
        int leftCount = 0;
        int rightCount = 0;

        String[] result = 0;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') leftCount++;

            if (c == ')') rightCount--;

            StringBuilder sb = new StringBuilder();
            sb.append(c);

            if (leftCount == rightCount) validExpressions.add(sb.toString());
        }


        for (String s : validExpressions) {
            result.add(s);
        }

        return result;
    }
    */

    /*
    private Set<String> validExpressions = new HashSet<String>();
    private int minimumRemoved;

    public List<String> removeInvalidParentheses(String s) {
        this.reset();
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList(this.validExpressions);
    }

    private void reset() {
        this.validExpressions.clear();
        this.minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
        // If we have reached the end of string.

        if (index < s.length()) {
            char currentCharacter = s.charAt(index);
            int length = expression.length();

            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {
                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);

                expression.append(currentCharacter);

                // If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }

                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length);
            }
        } else  {
            // If the current expression is valid.
            if (leftCount == rightCount) {
                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= this.minimumRemoved) {

                    // Convert StringBuilder to a String. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        }
    }
    */

    /*
    Time Complexity : O(2^N) since in the worst case we will have only left parentheses in the expression and for every bracket we will have two options i.e. whether to remove it or consider it.

    Considering that the expression has NN parentheses, the time complexity will be O(2^N).


    Space Complexity : O(N) because we are resorting to a recursive solution and for a recursive solution there is always stack space used as internal function states are saved

     onto a stack during recursion. The maximum depth of recursion decides the stack space used.

     Since we process one character at a time and the base case for the recursion is when we have processed all of the characters of the expression string, the size of the stack would be O(N)O(N). Note that we are not considering the space required to store the valid expressions.

     We only count the intermediate space here.
    */

    /*
    private Set<String> validExpressions = new HashSet<String>();

    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // First, we find out the number of misplaced leftRem and rightRem parentheses.
        for (int i = 0; i < s.length(); i++) {

            // Simply record the leftRem one.
            if (s.charAt(i) == '(') {
                leftRem++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching leftRem, then this is a misplaced rightRem, record it.
                rightRem = leftRem == 0 ? rightRem + 1 : rightRem;

                // Decrement count of leftRem parentheses because we have found a rightRem
                // which CAN be a matching one for a leftRem.
                leftRem = leftRem > 0 ? leftRem - 1 : leftRem;
            }
        }

        this.recurse(s, 0, 0, 0, leftRem, rightRem, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }

    private void recurse(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder expression) {
        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0)
                this.validExpressions.add(expression.toString());
        } else {
            char character = s.charAt(index);
            int length = expression.length();

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse(s, index + 1, leftCount, rightCount, leftRem - (character == '(' ? 1 : 0), rightRem - (character == ')' ? 1 : 0), expression);
            }

            expression.append(character);

            // Simply recurse one step further if the current character is not a parenthesis.
            if (character != '(' && character != ')') {

                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

            } else if (character == '(') {

                // Consider an opening bracket.
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

            } else if (rightCount < leftCount) {

                // Consider a closing bracket.
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }

            // Delete for backtracking.
            expression.deleteCharAt(length);
        }
    }
    */

    /*
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();

        queue.offer(s);

        List<String> result = new ArrayList<>();

        while(queue.size() > 0) {
            String currentString = queue.poll();

            if(set.contains(currentString)) continue;

            set.add(currentString);

            if(isValid(currentString)) result.add(currentString);
            else if (result.size() == 0)
                for(int i = 0; i < currentString.length(); i++)
                    if(currentString.charAt(i) == ')' || currentString.charAt(i) == '(')
                        queue.offer(currentString.substring(0, i) + currentString.substring(i + 1));
        }

        return result;
    }

    private Boolean isValid(String s) {
        int count = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') count++;
            if(c == ')')
                if(count > 0) count--;
                else
                    return false;
        }

        return count < 1;
    }
    */

    public List<String> removeInvalidParentheses(String s) {
        Queue<MyPair> queue = new LinkedList<>();

        queue.offer(new MyPair(s, 0));

        List<String> result = new ArrayList<>();

        while(queue.size() > 0) {
            MyPair currentPair = queue.poll();

            String currentString = currentPair.str;

            if(isValid(currentString)) result.add(currentString);
            else if (result.size() == 0)
                for(int i = currentPair.count; i < currentString.length(); i++)
                    if ((currentString.charAt(i) == ')' || currentString.charAt(i) == '(') && (i == currentPair.count || currentString.charAt(i) != currentString.charAt(i - 1)))
                        queue.offer(new MyPair(currentString.substring(0, i) + currentString.substring(i + 1), i));
        }

        return result;
    }

    public static void main(String[] args) {
        Remove_Invalid_Parentheses_301 obj = new Remove_Invalid_Parentheses_301();

        String s = "()())()";
        System.out.println(obj.removeInvalidParentheses(s));


        // s = "(((()))()";
        // System.out.println(obj.removeInvalidParentheses(s));


        // s = "((()))()";
        // System.out.println(obj.removeInvalidParentheses(s));
    }

    /*
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        helper(0, s, 0, 0, ans, new char[] {'(', ')'});
        return ans;
    }

    private void helper(int level, String s, int left, int right, List<String> ans, char[] pars) {

        // for (int p = 0; p < level; ++p) System.out.print(" ");
        // System.out.print("pass in left:" + left);
        // System.out.print(" pass in right:" + right);
        // System.out.println();

        int stack = 0;

        for (; right < s.length(); right++) {
            if (s.charAt(right) == pars[0]) stack++;
            else if (s.charAt(right) == pars[1]) stack--;
            if (stack < 0) break;
        }

        if (stack < 0) {
            for (; left <= right; left++) {
                if (s.charAt(left) != pars[1])
                    continue;
                else if (left > 1 && s.charAt(left - 1) == s.charAt(left))
                    continue;
                else {
                    helper(level + 2, s.substring(0, left) + s.substring(left + 1, s.length()), left, right, ans, pars);
                }
            }
        } else if (stack > 0)
            helper(level + 2, new StringBuilder(s).reverse().toString(), 0, 0, ans, new char[] {')', '('});
        else
            ans.add(pars[0] == '(' ? new String(s) : new StringBuilder(s).reverse().toString());
    }
    */
}

/*
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openParen) numOpenParen++;
            if (s.charAt(i) == closedParen) numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))
                        // Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                        removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen, closedParen);
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')', '(');
        else
            output.add(reversed);
    }
}

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }
}

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) rmL--;
                else rmR++;
            }
        }

        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<String>(res);
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) return;

        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) res.add(sb.toString());
            return;
        }

        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);         // not use (
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (
        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);             // not use  )
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);       // use )
        } else dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);


        sb.setLength(len);
    }
}
*/