package leetcode;
import java.util.Map;

import java.util.HashMap;
/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
Example 4:

Input: numerator = 4, denominator = 333
Output: "0.(012)"
Example 5:

Input: numerator = 1, denominator = 5
Output: "0.2"

Constraints:

-231 <= numerator, denominator <= 231 - 1
denominator != 0
*/

class Fraction_to_Recurring_Decimal_166 {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		
		StringBuilder fractionSB = new StringBuilder();

		// If either one is negative (not both)
		
		if (numerator < 0 ^ denominator < 0) {
			fractionSB.append("-");
		}

		/*
		if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
			fractionSB.append("-");
		}

		0000 0000
		0000 

		0001 0000
		0001

		0000 0001
		0001

		0001 0001
		0000
		*/

		// Convert to Long or else abs(-2147483648) overflows
		long dividend = Math.abs(Long.valueOf(numerator));
		long divisor = Math.abs(Long.valueOf(denominator));
		fractionSB.append(String.valueOf(dividend / divisor));

		long remainder = dividend % divisor;
		if (remainder == 0) {
			return fractionSB.toString(); //  20/4
		}

		fractionSB.append(".");

		Map<Long, Integer> map = new HashMap<Long, Integer>();

		while (remainder != 0) {
			if (map.containsKey(remainder)) {
				fractionSB.insert(map.get(remainder), "(");
				fractionSB.append(")");
				break;
			}
		
			map.put(remainder, fractionSB.length());
		
			remainder *= 10;
		
			fractionSB.append(String.valueOf(remainder / divisor));
		
			remainder %= divisor;
		}

/*
	  0.01201
                                 fractionSB  "0" => | "0." => "0.0"  | "0.01"              |  "0.012"                     |  "0.(012)"
333 / 4.00000                    333/4  =>         | 4 => 40 => 40  |  40 => 400 => 67    |  67 => 670 => 4              |  4
                                 hashMap =>       | [{4, 2}]       |  [{4, 2}, {40, 3}]  |  [{4, 2}, {40, 3}, {67, 4}]  |  [{4, 2}, {40, 3}, {67, 4}]

      3 33 
        670
        666
          400
          333
           670
*/
		
		return fractionSB.toString();
	}

	public static void main(String[] args) {
		// System.out.println(new Fraction_to_Recurring_Decimal_166().fractionToDecimal(1, 2));
		// System.out.println(new Fraction_to_Recurring_Decimal_166().fractionToDecimal(2, 1));
		// System.out.println(new Fraction_to_Recurring_Decimal_166().fractionToDecimal(2, 3));
		// System.out.println(new Fraction_to_Recurring_Decimal_166().fractionToDecimal(4, 333));
		// System.out.println(new Fraction_to_Recurring_Decimal_166().fractionToDecimal(1, 5));

		StringBuilder sb = new StringBuilder();
		sb.append("test");

		sb.insert(1, "(");

		System.out.println(sb.toString());
	}
}