package stack;
import java.util.Stack;
/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:

Input: s = "3+2*2"
Output: 7

Example 2:

Input: s = " 3/2 "
Output: 1

Example 3:

Input: s = " 3+5 / 2 "
Output: 5

Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
*/

class Basic_Calculator_II_227 {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;

        int len = s.length();

        Stack<Integer> stack = new Stack<Integer>();

        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
    /*
    Time Complexity: \mathcal{O}(n)O(n), where nn is the length of the string ss. We iterate over the string ss at most twice.

    Space Complexity: \mathcal{O}(n)O(n), where nn is the length of the string ss.
    */

    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();

        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < len; ++i) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) currentNumber = currentNumber * 10 + (currentChar - '0');
            
            if (!Character.isDigit(currentChar) || !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') stack.push(-currentNumber);
                if (operation == '+') stack.push(currentNumber);
                if (operation == '*') stack.push(stack.pop() * currentNumber);
                if (operation == '/') stack.push(stack.pop() / currentNumber);

                operation = currentChar;
                currentNumber = 0;
            }
        }

        int result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public int calculateII(String s) {
        if (s == null || s.isEmpty()) return 0;

        int length = s.length();
        
        int currentNumber = 0, lastNumber = 0, result = 0;
        
        char operation = '+';
        
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }

                operation = currentChar;
                currentNumber = 0;
            }
        }
        
        result += lastNumber;
        return result;
    }

    public static void main(String[] args) {
        Basic_Calculator_II_227 obj = new Basic_Calculator_II_227();

        obj.calculate("3 + 5 - 7");
    }
}