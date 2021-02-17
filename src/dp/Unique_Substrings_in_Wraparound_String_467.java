class Solution {
	public int findSubstringInWraproundString(String p) {
		// count[i] is the maximum unique substring end with ith letter.
		// 0 - 'a', 1 - 'b', ..., 25 - 'z'.
		int[] count = new int[26];

		// store longest contiguous substring ends at current position.
		int maxLengthCur = 0;

		for (int i = 0; i < p.length(); i++) {
			if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) == 'z' && p.charAt(i) == 'a'))) {
				maxLengthCur++;
			} else {
				maxLengthCur = 1;
			}

			count[index] = Math.max(count[p.charAt(i) - 'a'], maxLengthCur);
		}

		// Sum to get result
		int sum = 0;
		for (int i = 0; i < 26; i++) {
			sum += count[i];
		}
		return sum;
	}

	// 成为s的子数组的意思是，p里存在连续的字符串，或者zabc这种顺序的字符串
	// 经典的后缀和问题，abc已c结尾的连续字符串的个数 = 已b结尾的连续的字符串个数，+1（c自己）
	// 需要查重 如上代码是有问题。 比如 cac会被计算为 3，实际上应该是 2。根本原因在于 c 被错误地计算了两次。因此一个简单的思路就是用 set 记录一下访问过的子字符串即可，但有序字符串是连续的，只需要记录是以谁结尾，长度多少的hashmap即可
	public int findSubstringInWraproundString(String p) {
		int[]map = new int[26];//代表已c结尾最长

		if (p == null || p.length() == 0) return 0;
		int res = 0;
		
		char[] arr = p.toCharArray();
		
		int pre = 1;
		
		map[arr[0] - 'a'] = 1;
		
		for (int i = 1; i < p.length(); i++) {
			if (arr[i] - arr[i - 1] == 1 || arr[i] - arr[i - 1] == -25 ) {
				pre += 1;
			} else {
				pre = 1;
			}
			if (map[arr[i] - 'a'] < pre) map[arr[i] - 'a'] = pre;
		}

		for (int i = 0; i < 26; i++) {
			if (map[i] != 0) res += map[i];
		}

		return res;
	}
}
