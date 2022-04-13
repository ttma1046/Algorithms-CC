package string;
import java.util.ArrayList;
import java.util.List;

/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"


Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/
class Multiply_Strings_43 {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];

        for(int i = num1.length() - 1; i >= 0; i--) {
            int carry = 0;
            for(int j = num2.length() - 1; j >= 0; j--) {
                int sum = res[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                int tmp = sum % 10;
                res[i + j + 1] = tmp;
                carry = sum / 10;
            }
            res[i] = carry;
        }

        /*
        0 1 2 3 4

        0 1 2
        0 1


        2 + 1 + 1

        4 3 2

        3 2 1

        2 1 0
        */

        int m = 0;
        while(m < res.length && res[m] == 0) m++;
        if(m >= res.length) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(int n = m; n < res.length; n++) 
            sb.append(res[n]);
        
        return sb.toString();
    }

    public String multiplyII(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        int[] products = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }

        /*
        999
         99

        ------
        81 + 81

        81 + 81

        81 + 81
         0, 81, 81 + 81, 81 + 81,81
        */

        /*
        for (int i = 0; i < products.length; i++)
            System.out.println(products[i] + ",");
        */

        int carry = 0;

        for (int i = products.length - 1; i >= 0; i--) {
            // System.out.println("products[i] + carry:" + (products[i] + carry));
            int tmp = (products[i] + carry) % 10;
            // System.out.println("tmp:" + tmp);
            carry = (products[i] + carry) / 10;
            // System.out.println("carry:" + carry);
            products[i] = tmp;
        }

        /*
        81
        1
        8
             1
        162 + 8 = 170
        0
        17
             0 1
        162 + 17 =  179
        9
        17

            9,0,1
        81 + 17 = 98
        8
        9
          8,9,0,1

        0 + 9 = 9
        9

        9 8 9 0 1

        */

        /*
        for (int i = 0; i < products.length; i++)
            System.out.print(products[i] + ",");

        System.out.println();
        */

        StringBuilder sb = new StringBuilder();

        for (int num : products)
            sb.append(num);

        while (sb.length() != 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Multiply_Strings_43 obj = new Multiply_Strings_43();

        String res = obj.multiply(
                         "999",
                         "99"
                     );

        System.out.println(res);
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);

        // Reverse both the numbers.
        firstNumber.reverse();
        secondNumber.reverse();

        // For each digit in secondNumber, multipy the digit by firstNumber and
        // store the multiplication result (reversed) in results.
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        for (int i = 0; i < secondNumber.length(); ++i)
            results.add(multiplyOneDigit(firstNumber, secondNumber.charAt(i), i));

        // Add all the results in the results array, and store the sum in the answer string.
        StringBuilder answer = sumResults(results);

        // answer is reversed, so reverse it to get the final answer.
        answer.reverse();
        return answer.toString();
    }

    /*
    {
      {1,9,9,8}
      {0,1,9,9,8}
      {0,0,1,9,9,8}
    }
    */
    ArrayList<Integer> multiplyOneDigit(StringBuilder firstNumber, char secondNumberDigit, int numZeros) {
        ArrayList<Integer> currentResult = new ArrayList<>();

        for (int i = 0; i < numZeros; ++i)
            currentResult.add(0);

        int carry = 0;

        for (int i = 0; i < firstNumber.length(); ++i) {
            char firstNumberDigit = firstNumber.charAt(i);
            int multiplication = (secondNumberDigit - '0') * (firstNumberDigit - '0') + carry;

            carry = multiplication / 10;

            currentResult.add(multiplication % 10);
        }

        if (carry != 0)
            currentResult.add(carry);

        return currentResult;
    }

    // {{1,9,9,8}, {0,1,9,9,8}}
    // Calculate the sum of all of the results from multiplyOneDigit.
    private StringBuilder sumResults(ArrayList<ArrayList<Integer>> results) {
        // Initialize answer as a number from results.
        ArrayList<Integer> answer = new ArrayList<>(results.get(results.size() - 1));
        ArrayList<Integer> newAnswer = new ArrayList<>();

        // Sum each digit from answer and result
        for (int j = 0; j < results.size() - 1; ++j) {
            ArrayList<Integer> result = new ArrayList<>(results.get(j));
            newAnswer = new ArrayList<>();

            int carry = 0;

            for (int i = 0; i < answer.size() || i < result.size(); ++i) {
                // If answer is shorter than result or vice versa, use 0 as the current digit.
                int digit1 = i < result.size() ? result.get(i) : 0;
                int digit2 = i < answer.size() ? answer.get(i) : 0;
                // Add current digits of both numbers.
                int sum = digit1 + digit2 + carry;
                // Set carry equal to the tens place digit of sum.
                carry = sum / 10;
                // Append the ones place digit of sum to answer.
                newAnswer.add(sum % 10);
            }

            if (carry != 0)
                newAnswer.add(carry);

            answer = newAnswer;
        }

        // Convert answer to a string.
        StringBuilder finalAnswer = new StringBuilder();

        for (int digit : answer)
            finalAnswer.append(digit);

        return finalAnswer;
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);

        // Reverse both the numbers.
        firstNumber.reverse();
        secondNumber.reverse();

        // To store the multiplication result of each digit of secondNumber with firstNumber.
        int length = firstNumber.length() + secondNumber.length();

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; ++i)
            ans.add(0);

        // For each digit in secondNumber, multipy the digit by firstNumber and
        // add the multiplication result to ans.
        for (int i = 0; i < secondNumber.length(); ++i)
            // Add the current result to final ans.
            ans = addStrings(multiplyOneDigit(firstNumber, secondNumber.charAt(i), i), ans);

        // Pop excess 0 from the rear of ans.
        if (ans.get(ans.size() - 1) == 0)
            ans.remove(ans.size() - 1);

        // Ans is in the reversed order.
        // Copy it in reverse order to get the final ans.
        StringBuilder answer = new StringBuilder();

        for (int i = ans.size() - 1; i >= 0; --i)
            answer.append(ans.get(i));

        return answer.toString();
    }

    ArrayList<Integer> multiplyOneDigit(StringBuilder firstNumber, char secondNumberDigit, int numZeros) {
        ArrayList<Integer> currentResult = new ArrayList<>();

        for (int i = 0; i < numZeros; ++i)
            currentResult.add(0);

        int carry = 0;

        for (int i = 0; i < firstNumber.length(); ++i) {
            char firstNumberDigit = firstNumber.charAt(i);
            int multiplication = (secondNumberDigit - '0') * (firstNumberDigit - '0') + carry;
            // Set carry equal to the tens place digit of multiplication.
            carry = multiplication / 10;
            // Append last digit to the current result.
            currentResult.add(multiplication % 10);
        }

        if (carry != 0)
            currentResult.add(carry);

        return currentResult;
    }

    private ArrayList<Integer> addStrings(ArrayList<Integer> num1, ArrayList<Integer> num2) {
        ArrayList<Integer> ans = new ArrayList<>();
        int carry = 0;

        for (int i = 0; i < num1.size() || i < num2.size(); ++i) {
            // If num2 is shorter than num1 or vice versa, use 0 as the current digit.
            int digit1 = i < num1.size() ? num1.get(i) : 0;
            int digit2 = i < num2.size() ? num2.get(i) : 0;

            // Add current digits of both numbers.
            int sum = digit1 + digit2 + carry;
            // Set carry equal to the tens place digit of sum.
            carry = sum / 10;
            // Append the ones place digit of sum to answer.
            ans.add(sum % 10);
        }

        if (carry != 0)
            ans.add(carry);

        return ans;
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";        
        
        StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);
        
        // Reverse both the numbers.
        firstNumber.reverse();
        secondNumber.reverse();
        
        // To store the multiplication result of each digit of secondNumber with firstNumber.
        int N = firstNumber.length() + secondNumber.length();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; ++i)
            answer.append(0);
        
        
        for (int place2 = 0; place2 < secondNumber.length(); place2++) {
            int digit2 = secondNumber.charAt(place2) - '0';
            
            // For each digit in secondNumber multiply the digit by all digits in firstNumber.
            for (int place1 = 0; place1 < firstNumber.length(); place1++) {
                int digit1 = firstNumber.charAt(place1) - '0';
                
                // The number of zeros from multiplying to digits depends on the 
                // place of digit2 in secondNumber and the place of the digit1 in firstNumber.
                int currentPos = place1 + place2;
                
                // The digit currently at position currentPos in the answer string
                // is carried over and summed with the current result.
                int carry = answer.charAt(currentPos) - '0';
                int multiplication = digit1 * digit2 + carry;
                
                // Set the ones place of the multiplication result.
                answer.setCharAt(currentPos, (char)(multiplication % 10 + '0'));
                
                // Carry the tens place of the multiplication result by 
                // adding it to the next position in the answer array.
                int value = (answer.charAt(currentPos + 1) - '0') + multiplication / 10;
                answer.setCharAt(currentPos + 1, (char)(value + '0'));
            }
        }
        
        // Pop excess 0 from the rear of answer.
        if (answer.charAt(answer.length() - 1) == '0') {
            answer.deleteCharAt(answer.length() - 1);
        }
        
        answer.reverse();
        return answer.toString();
    }
}
/*
123
 34
----
 492
369
*/