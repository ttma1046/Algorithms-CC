package string;

/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/


class Solution {
	public String addStrings(String num1, String num2) {
		int len1 = num1.length() - 1;
		int len2 = num2.length() - 1;

		StringBuilder sb = new StringBuilder();
		int sum = 0, carry = 0;
		while (len1 >= 0 || len2 >= 0) {
			int first = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
			int second = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
			sum = carry + first + second;
			if (sum <= 9) {
				sb.insert(0, sum);
				sum = 0;
				carry = 0;
			} else {
				sb.insert(0, sum % 10);
				sum = 0;
				carry = 1;
			}
			len1--;
			len2--;
		}
		if (carry == 1)sb.insert(0, "1");
		return sb.toString();
	}

	public String addStrings(String num1, String num2) {
		/*
		       char base = '0'
		       int carry = 1
		       num1 =  2321
		               i
		       num2 =  9451
		               j
		       sum =  1 7 7 2
		             k

		       toCharArray

		       */
		/*
		Time Complexity: O(max(M, N)) two different strings; M - length of num1; N - length of num2
		Space Complexity: O(max(M, N))
		TIME: 00:50:01
		*/
		char base = '0';
		int carry = 0;

		int length = num1.length() > num1.length() ? num1.length() : num2.length();
		// int[] sum = new int[length];
		StringBuilder sb = new StringBuilder();

		int i = num1.length() - 1;
		int j = num2.length() - 1;
		// int k = sum.length - 1;

		while (i >= 0 && j >= 0) {
			int res = (num1.charAt(i) - base) + (num2.charAt(j) - base) + carry;
			carry = res / 10;
			// sum[k] = res % 10;
			sb.append(res % 10);
			// k--;
			i--;
			j--;
		}

		while (i >= 0) {
			// sum[k] = num1.charAt(i) - base + carry;
			int res = num1.charAt(i) - base + carry;
			carry = res / 10;
			sb.append(res % 10);
			// k--;
			i--;
		}

		while (j >= 0) {
			// sum[k] = num1.charAt(i) - base + carry;
			int res = num2.charAt(j) - base + carry;
			carry = res / 10;
			sb.append(res % 10);
			// k--;
			j--;
		}

		if (carry > 0) {
			sb.append(carry);
		}

		// if(k < 0 && carry > 0) {
		//     int[] tmp = sum;
		//     sum = new int[tmp.length + 1];
		//     sum[0] = carry;
		//     for(i = 0; i < tmp.length; i++) {
		//         sum[i+1] = tmp[i];
		//     }
		// }
		return sb.reverse().toString();
	}
}