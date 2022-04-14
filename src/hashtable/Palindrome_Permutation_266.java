package hashtable;

import java.util.Map;
import java.util.HashMap;


/*
Given a string s, return true if a permutation of the string could form a palindrome.

Example 1:

Input: s = "code"
Output: false

Example 2:

Input: s = "aab"
Output: true

Example 3:

Input: s = "carerac"
Output: true

Constraints:

1 <= s.length <= 5000
s consists of only lowercase English letters.
*/

class Palindrome_Permutation_266 {
	public boolean canPermutePalindrome(String s) {
		boolean flag = false;

		/*
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (int frequency : map.values()) {
		*/

		int[] table = new int[128];
		for (char c : s.toCharArray()) {
			table[c]++;
		}

		for (int frequency : table) {
			if (frequency > 0 && frequency % 2 == 1) {
				if (flag) return false;
				flag = true;
			}
		}

		return true;
	}

	public boolean canPermutePalindromeII(String s) {
		int[] table = new int[128];
		

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

	public boolean canPermutePalindrome(String s) {
		int[] dict = new int[128];
		int l = s.length();

		while (char c: s.toCharArray()) {
			dict[c - 'a']++;
		}

		int countOdd = 0;

		for (int c: dict) {
			if (c % 2 != 0) {
				countOdd++;
			}
		}
		
		if (l % 2 == 1) {
			return countOdd == 1;
		} else {
			return countOdd == 0;
		}
	}

    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
}