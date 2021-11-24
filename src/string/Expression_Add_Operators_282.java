package string;

import java.util.List;
import java.util.ArrayList;
/*
Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.

Note that operands in the returned expressions should not contain leading zeros.



Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Explanation: Both "1*0+5" and "10-5" evaluate to 5.
Note that "1-05" is not a valid expression because the 5 has a leading zero.
Example 4:

Input: num = "00", target = 0
Output: ["0*0","0+0","0-0"]
Explanation: "0*0", "0+0", and "0-0" all evaluate to 0.
Note that "00" is not a valid expression because the 0 has a leading zero.
Example 5:

Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.


Constraints:

1 <= num.length <= 10
num consists of only digits.
-231 <= target <= 231 - 1
*/
class Expression_Add_Operators_282 {

    private List<String> operators = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        List<String> result = new ArrayList<>();

        recursive(num.length(), num, 0, target, 0, result, new StringBuilder());

        return result;
    }


    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<String>();
        if(num == null || num.length() == 0) return result;
        helper(result, "", num, target, 0, 0, 0);
        return result;
    }
 
    public void helper(List<String> result, String path, String num, int target, int pos, long eval, long multed) {
        if(pos == num.length()) {
            if(target == eval)
                result.add(path);
            return;
        }

        for(int i = pos; i < num.length(); i++) {
            if(i != pos && num.charAt(pos) == '0') break;

            long cur = Long.parseLong(num.substring(pos, i + 1));
            
            if(pos == 0) {
                helper(result, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(result, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                helper(result, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                helper(result, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }

    private void recursive(int length, String leftStr, int left, int target, int pos, List<String> res, StringBuilder sb) {
        if (pos == length) {
            if (target == left) res.add(sb.toString());
            return;
        }

        for (String operator: operators) {
            StringBuilder newSb = new StringBuilder(sb.toString());

            String onecar = leftStr.substring(0, 1);
            int val = Integer.valueOf(onecar);

            newSb.append(onecar);

            int newLeft = left;

            if (operator == "+")
                newLeft += val;

            if (operator == "-")
                newLeft -= val;

            if (operator == "*")
                newLeft *= val;

            if (operator == "/" && val != 0)
                newLeft /= val;

            newSb.append(operator);

            recursive(length, leftStr.substring(1), newLeft, target, pos + 1, res, newSb);
        }
    }

    private List<String> operators = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        List<String> result = new ArrayList<>();

        String onechar = num.substring(0, 1);

        int q = Integer.valueOf(onechar);

        StringBuilder sb = new StringBuilder();
        sb.append(onechar);

        recursive(num.substring(1), q, sb, target, result, operators);

        return result;
    }

    private void recursive(String num, int left, StringBuilder remain, int target, List<String> result, List<String> operators) {
        String onechar = num.substring(0, 1);
        int q = Integer.valueOf(onechar);

        for (String operator: operators) {
            if (operator == "+") {
                left += q;
                indeep(num, left, remain, target, result, operators, operator, onechar);
            } else if (operator == "-") {
                left -= q;
                indeep(num, left, remain, target, result, operators, operator, onechar);
            } else if (operator == "*") {
                left *= q;
                indeep(num, left, remain, target, result, operators, operator, onechar);
            } else if (operator == "/" && q != 0) {
                left *= q;
                indeep(num, left, remain, target, result, operators, operator, onechar);
            }
        }
    }

    private void indeep(String num, int left, StringBuilder remain, int target, List<String> result, List<String> operators, String operator, String onechar) {
        StringBuilder sb = new StringBuilder();
        sb.append(remain.toString());
        sb.append(operator);
        sb.append(onechar);

        if (num.length() == 1) {
            if (left == target) {
                result.add(sb.toString());
            }
        } else {
            if (left <= target) recursive(num.substring(1), left, sb, target, result, operators);
        }
    }

    public static void main(String[] args) {
        Expression_Add_Operators_282 obj = new Expression_Add_Operators_282();

        String num = "123";

        List<String> result = obj.addOperators(num, 6);

        for (String res : result) System.out.println(res);
    }
}