package string;

import java.util.Stack;
/*
Given a string s of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

Example 1:

Input: s = "())"
Output: 1

Example 2:

Input: s = "((("
Output: 3

Example 3:

Input: s = "()"
Output: 0

Example 4:

Input: s = "()))(("
Output: 4

Note:

s.length <= 1000
s only consists of '(' and ')' characters.
*/
class Minimum_Add_to_Make_Parentheses_Valid_921 {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
                continue;
            }

            if (stack.isEmpty() && c == ')') {
                ++res;
            }

            if (!stack.isEmpty() && c == ')' && stack.peek() == '(') {
                stack.pop();
                continue;
            }
        }

        return stack.size() + res;
    }

    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;

        char[] c = s.toCharArray();

        for(char ch : c) {
            if(ch == '(') open++;
            else if(open > 0) open--;
            else close++;
        }

        return open + close;
    }

    public int minAddToMakeValid(String s) {
        int res = 0;
        int stack = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') stack++;
            else {
                if (stack == 0) res++;
                else stack--;
            }
        }
        
        return res + stack;
    }
     
    public static void main(String[] args) {
        Minimum_Add_to_Make_Parentheses_Valid_921 obj = new Minimum_Add_to_Make_Parentheses_Valid_921();
        System.out.println(obj.minAddToMakeValid("())"));
        System.out.println(obj.minAddToMakeValid("((("));
        System.out.println(obj.minAddToMakeValid("()"));
        System.out.println(obj.minAddToMakeValid("()))(("));
    }
}