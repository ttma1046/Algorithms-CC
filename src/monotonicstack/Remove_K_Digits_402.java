package monotonicstack;
import java.util.Stack;
/*
Given string num representing a non-negative integer num,
 and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
*/
class Remove_K_Digits_402 {
	public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);

        	// for(char i: stack) System.out.print(i);
			// System.out.println();
        	// System.out.println("k:" + k);
        }

        // System.out.println("end k:" + k);


        // while(stack.size() > 0) System.out.print(stack.pop());

        
        while(k-- > 0) stack.pop();
        StringBuilder sb = new StringBuilder();

        boolean zero = true;

        for (int element : stack) {
            if (element == '0' && zero) continue;
            else zero = false;
            sb.append(element - '0');
        }
        return sb.length() == 0 ? "0" : sb.toString();
        
        return "";
    }

    public static void main(String[] args) {
    	Remove_K_Digits_402 obj = new Remove_K_Digits_402();
    	obj.removeKdigits("1432219", 3);
    	/*
    	1    k = 3

    	1 4  k = 3 

    	1 3  k = 2
 
 		1 2  k = 1

 		1 2 2 
 		1 2 1 
 		1 2 1 9

    	*/
    }
}