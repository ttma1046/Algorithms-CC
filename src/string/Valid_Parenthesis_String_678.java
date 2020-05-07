package string;

import java.util.Stack;

class Valid_Parenthesis_String_678 {
    public boolean checkValidString(String s) {
        Stack<Integer> leftID = new Stack<>();
        Stack<Integer> starID = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftID.push(i);
            } else if (ch == '*') {
                starID.push(i);
            } else {
                if (leftID.isEmpty() && starID.isEmpty()) {
                    return false;
                }
                if (!leftID.isEmpty()) {
                    leftID.pop();
                } else {
                    starID.pop();
                }
            }
        }

        while (!leftID.isEmpty() && !starID.isEmpty()) {
            if (leftID.pop() > starID.pop()) {
                return false;
            }
        }

        return leftID.isEmpty();
    }

    public boolean checkValidString(String s) {
        int cmin = 0, cmax = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Valid_Parenthesis_String_678().checkValidString("(())"));
        System.out.println(new Valid_Parenthesis_String_678().checkValidString("(*)"));
        System.out.println(new Valid_Parenthesis_String_678().checkValidString("(*))"));
        System.out.println(new Valid_Parenthesis_String_678().checkValidString("((*)"));
    }
}