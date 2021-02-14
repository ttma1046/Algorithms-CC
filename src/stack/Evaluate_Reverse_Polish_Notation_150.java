package stack;
import java.util.Stack;
/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

class Evaluate_Reverse_Polish_Notation_150 {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();

		for (String token : tokens) {
			switch (token) {
			case "+":
				int a = stack.pop();
				int b = stack.pop();
				stack.push(b + a);
				break;
			case "-":
				int c = stack.pop();
				int d = stack.pop();
				stack.push(d - c);
				break;
			case "*":
				int e = stack.pop();
				int f = stack.pop();
				stack.push(f * e);
				break;
			case "/":
				int g = stack.pop();
				int h = stack.pop();
				stack.push(h / g);
				break;
			default:
				stack.push(Integer.parseInt(token));
				break;
			}
		}

		return stack.peek();
	}

	public static int evalRPN(String[] tokens) {
		int[] numStack = new int[tokens.length / 2 + 1];
		int index = 0;
		for (String s : tokens) {
			if (s.equals("+")) {
				numStack[index - 2] += numStack[--index];
			} else if (s.equals("-")) {
				numStack[index - 2] -= numStack[--index];
			} else if (s.equals("*")) {
				numStack[index - 2] *= numStack[--index];
			} else if (s.equals("/")) {
				numStack[index - 2] /= numStack[--index];
			} else {
				numStack[index++] = Integer.parseInt(s);
			}
		}
		return numStack[0];
	}

	public static void main(String[] args) {
		System.out.println(new Evaluate_Reverse_Polish_Notation_150().evalRPN(new String[] { "2", "1", "+", "3", "*" }));
		System.out.println(new Evaluate_Reverse_Polish_Notation_150().evalRPN(new String[] { "4", "13", "5", "/", "+" }));

		System.out.println(new Evaluate_Reverse_Polish_Notation_150().evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }));
	}


	public static int evalRPN(String[] tokens) {
		int[] stack = new int[tokens.length / 2 + 1];

		int index = 0;

		for(String s: tokens) {
			if (s.equals("+")) {
				stack[index - 2] += stack[--index];
			} else if (s.equals("-")) {
				stack[index - 2] -= stack[--index];
			} else if (s.equals("*")) {
				stack[index - 2] *= stack[--index];
			} else if (s.equals("/")) {
				stack[index - 2] /= stack[--index];
			} esle {
				stack[index++] = Integer.parseInt(s);
			}
		}

		return stack[0];

	}

}