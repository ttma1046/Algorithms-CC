package hashtable;

import java.util.Map;
import java.util.HashMap;

class Palindrome_Permutation_266 {
	public boolean canPermutePalindrome(String s) {
		boolean flag = false;

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (Integer frequency : map.values()) {
			if (flag && frequency % 2 == 1) {
				return false;
			} else if (frequency % 2 == 1) {
				flag = true;
			}
		}

		return true;
	}


	public boolean canPermutePalindromeII(String s) {
		int[] table = new int[128];
		for (char c : s.toCharArray()) {
			table[c]++;
		}

		int countOdd = 0;

		for (int ele : table) {
			if (ele % 2 != 0) {
				countOdd++;
			}
		}

		if (s.length() % 2 == 0) {
			return countOdd == 0;
		} else {
			return countOdd == 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Palindrome_Permutation_266().canPermutePalindrome("aab"));
		System.out.println(new Palindrome_Permutation_266().canPermutePalindrome("carerac"));
		System.out.println(new Palindrome_Permutation_266().canPermutePalindrome("code"));
	}
}