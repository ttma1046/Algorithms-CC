package leetcode;

import java.util.HashMap;
import java.util.Map;
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.



Example 1:

Input: num = 3
Output: "III"
Example 2:

Input: num = 4
Output: "IV"
Example 3:

Input: num = 9
Output: "IX"
Example 4:

Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= num <= 3999

*/

class Integer_to_Roman_12 {
	public String intToRoman(int num) {
		int temp = num;
		int[] nums = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] strs = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			int items = temp / nums[i];

			for (int j = items; j > 0; j--) {
				sb.append(strs[i]);
			}

			temp %= nums[i];
		}

		return sb.toString();
	}

	public String intToRoman(int num) {
		int temp = num;
		StringBuilder s = new StringBuilder("");
		while (temp > 0) {
			if (temp >= 1000) {
				s.append("M");
				temp -= 1000;
			} else if (temp >= 900) {
				s.append("CM");
				temp -= 900;
			} else if (temp >= 500) {
				s.append("D");
				temp -= 500;
			} else if (temp >= 400) {
				s.append("CD");
				temp -= 400;
			} else if (temp >= 100) {
				s.append("C");
				temp -= 100;
			} else if (temp >= 90) {
				s.append("XC");
				temp -= 90;
			} else if (temp >= 50) {
				s.append("L");
				temp -= 50;
			} else if (temp >= 40) {
				s.append("XL");
				temp -= 40;
			} else if (temp >= 10) {
				s.append("X");
				temp -= 10;
			} else if (temp >= 9) {
				s.append("IX");
				temp -= 9;
			} else if (temp >= 5) {
				s.append("V");
				temp -= 5;
			} else if (temp >= 4) {
				s.append("IV");
				temp -= 4;
			} else if (temp >= 1) {
				s.append("I");
				temp -= 1;
			}
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Integer_to_Roman_12().intToRoman(3));
		System.out.println(new Integer_to_Roman_12().intToRoman(4));
		System.out.println(new Integer_to_Roman_12().intToRoman(9));
		System.out.println(new Integer_to_Roman_12().intToRoman(58));
		System.out.println(new Integer_to_Roman_12().intToRoman(1994));
	}
}