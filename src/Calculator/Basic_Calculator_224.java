package calculator;
import java.util.ArrayDeque;
/*
Given a string s representing a valid expression,

implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:

Input: s = "1 + 1"
Output: 2

Example 2:

Input: s = " 2-1 + 2 "
Output: 3

Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23

Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation.
'-' could be used as a unary operation and in this case, it will not be used directly after a +ve or -ve signs (will be inside parentheses).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
*/
class Basic_Calculator_224 {
    public int calculate(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // the default operator
        char operator = '+';

        // the current number
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // or using api if (Character.isDigit(ch))
            if (ch >= '0' && ch <= '9') num = num * 10 + (ch - '0');

            // If we are in the end of the string, or if here is an operator, we know
            // that the number is end,
            // so we should push the number to stack.
            if (i == s.length() - 1 || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (operator == '+') {
                    stack.push(num);
                } else if (operator == '-') {
                    stack.push(-num);
                } else if (operator == '*') {
                    stack.push(stack.pop() *  num);
                } else {
                    stack.push(stack.pop() / num);
                }

                // update current operator
                // if i is s.length() - 2=1, the operator will become the character of a num,
                // it doesn't matter because it is end
                operator = ch;
                // clear number
                num = 0;
            }
        }

        int ans = 0;
        while(stack.size() > 0) ans += stack.pop();

        return ans;
    }

    public int calculateII(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // the default operator
        char operator = '+';

        // the current number
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // or using api if (Character.isDigit(ch))
            if (ch >= '0' && ch <= '9') num = num * 10 + (ch - '0');

            // If we are in the end of the string, or if here is an operator, we know
            // that the number is end,
            // so we should push the number to stack.
            if (i == s.length() - 1 || ch == '+' || ch == '-') {
                if (operator == '+') {
                    stack.push(num);
                } else {
                    stack.push(-num);
                }

                // update current operator
                // if i is s.length() - 2=1, the operator will become the character of a num,
                // it doesn't matter because it is end
                operator = ch;
                // clear number
                num = 0;
            }
        }

        int ans = 0;
        while(stack.size() > 0) ans += stack.pop();

        return ans;
    }

    int i = 0;

    public int calculateIII(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        char operator = '+';

        int num = 0;

        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch >= '0' && ch <= '9') num = num * 10 + (ch - '0');

            if (ch == '(') num = calculate(s);

            if (i >= s.length() || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')') {
                if (operator == '+')
                    stack.push(num);
                else if (operator == '-')
                    stack.push(-num);
                else if (operator == '*')
                    stack.push(stack.pop() * num);
                else if (operator == '/')
                    stack.push(stack.pop() / num);

                operator = ch;
                num = 0;
            }

            if (ch == ')') break;
        }
        int ans = 0;

        while(stack.size() != 0)
            ans += stack.pop();

        return ans;
    }

    public int calculateStack(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int sign = 1;
        int N = s.length();

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = 0;
                while(i < N && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;

                res += sign * sum;
            } else if (c == '+') sign = 1;
            else if (c == '-') sign = -1;
            else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res *= stack.pop();
                res += stack.pop();
            }
        }

        return res;
    }
}