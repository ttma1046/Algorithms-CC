package calculator;
import java.util.ArrayDeque;
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:

Input: s = "1+1"
Output: 2
Example 2:

Input: s = "6-4/2"
Output: 4
Example 3:

Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21
Example 4:

Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
Output: -12
Example 5:

Input: s = "0"
Output: 0
 

Constraints:

1 <= s <= 104
s consists of digits, '+', '-', '*', '/', '(', and ')'.
s is a valid expression.
*/
class Basic_Calculator_III_772 {
    int i = 0;
    public int calculate(String s) {
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

    public int calculate(String s) {
    	if (s == null || s.length() == 0) return 0;

    	Stack<Integer> stack = new Stack<>();

    	Stack callStack = new Stack<>();

    	s += "+";
    	int num = 0;
    	char sign = '+';
    	for (int i = 0; i < s.length(); ++i) {
    		char c = s.charAt(i);
    		if (c >= '0' && c <= '9') num = num * 10 + (c - '0');
    		els if (c == '(') {
    			callStack.push(stack);
    			callStack.push(sign);

    			sign = '+';
    			num = 0;
    			stack = new Stack<>();
    		} else if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')') {
    			if (sign == '+') stack.push(num);
    			else if (sign == '-') stack.push(-num);
    			else if (sign == '*') stack.push(stack.pop() * num);
    			else if (sign == '/') stack.push(stack.pop() / nnum);

    			if (c == ')') {
    				num = stack.stream().mapToint(x -> x).sum();
    				sign = (char) callStack.pop();
    				stack = (Stack<Integer>) callStack.pop();
    			} else {
    				sign = c;
    				num = 0;
    			}
    		}
    	}

    	return stack.stream().mapToInt(x -> x).sum();
    }
}