package stack;

import java.util.*;

public class BalancedBrackets {
    /*
     * A bracket is considered to be any one of the following characters: (, ), {,
     * }, [, or ].
     * 
     * Two brackets are considered to be a matched pair if the an opening bracket
     * (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or })
     * of the exact same type. There are three types of matched pairs of brackets:
     * [], {}, and ().
     * 
     * A matching pair of brackets is not balanced if the set of brackets it
     * encloses are not matched. For example, {[(])} is not balanced because the
     * contents in between { and } are not balanced. The pair of square brackets
     * encloses a single, unbalanced opening bracket, (, and the pair of parentheses
     * encloses a single, unbalanced closing square bracket, ].
     * 
     * Some examples of balanced brackets are []{}(), [({})]{}() and ({(){}[]})[].
     * 
     * By this logic, we say a sequence of brackets is considered to be balanced if
     * the following conditions are met:
     * 
     * It contains no unmatched brackets. The subset of brackets enclosed within the
     * confines of a matched pair of brackets is also a matched pair of brackets.
     * Given strings of brackets, determine whether each sequence of brackets is
     * balanced. If a string is balanced, print YES on a new line; otherwise, print
     * NO on a new line.
     * 
     * Input Format
     * 
     * The first line contains a single integer, , denoting the number of strings.
     * Each line of the subsequent lines consists of a single string, , denoting a
     * sequence of brackets.
     * 
     * Constraints
     * 
     * , where is the length of the sequence. Each character in the sequence will be
     * a bracket (i.e., {, }, (, ), [, and ]). Output Format
     * 
     * For each string, print whether or not the string of brackets is balanced on a
     * new line. If the brackets are balanced, print YES; otherwise, print NO.
     * 
     * Sample Input
     * 
     * 3 {[()]} {[(])} {{[[(())]]}} Sample Output
     * 
     * YES NO YES Explanation
     * 
     * The string {[()]} meets both criteria for being a balanced string, so we
     * print YES on a new line. The string {[(])} is not balanced, because the
     * brackets enclosed by the matched pairs [(] and (]) are not balanced. Thus, we
     * print NO on a new line. The string {{[[(())]]}} meets both criteria for being
     * a balanced string, so we print YES on a new line.
     */

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String expression = scanner.nextLine();
            System.out.println(isBalancedBrackets(expression));
        }

        scanner.close();
    }

    public static char[][] TOKENS = { { '{', '}' }, { '[', ']' }, { '(', ')' } };

    public static boolean isOpenTerm(char c) {
        for (char[] openTerms : TOKENS) {
            if (openTerms[0] == c) {
                return true;
            }
        }

        return false;
    }

    private static boolean matches(char openTerm, char closeTerm) {
        for (char[] array : TOKENS) {
            if (array[0] == openTerm) {
                return array[1] == closeTerm;
            }
        }

        return false;
    }

    private static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : expression.toCharArray()) {
            if (isOpenTerm(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || !matches(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String isBalancedBrackets(String expression) {
        if (expression == null || expression.length() == 0)
            return "Yes";

        Stack<Character> result = new Stack<Character>();

        for (char c : expression.toCharArray()) {
            switch (c) {
            case '[':
                result.push(']');
                break;
            case '{':
                result.push('}');
                break;
            case '(':
                result.push(')');
                break;
            case ']':
            case '}':
            case ')':
                if (result.isEmpty() || result.pop() != c) {
                    return "No";
                }

                break;
            default:
                return "No";
            }
        }

        return result.isEmpty() ? "Yes" : "No";
    }
}
