package leetcode;

/*
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.


Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
*/

class Custom_Sort_String_791 {
	public String customSortString(String S, String T) {
		int[] order = new int[26];
		int i = 0;
		for (i = 0; i < S.length(); i++) {
			order[S.charAt(i) - 'a'] = i + 1;
		}

		int[] temp = new int[T.length()];

		int j = temp.length - 1;

		for (i = 0; i < T.length(); i++) {
			if (order[T.charAt(i) - 'a'] == 0) {
				System.out.println("not in the order");
				// result.append(T.charAt(i));
				temp[j] = T.charAt(i) - 'a';
				j--;
			} else {
				if (temp[order[T.charAt(i) - 'a'] - 1] > 0) {
					while (temp[order[T.charAt(i) - 'a'] - 1] > 0) {
						order[T.charAt(i) - 'a']++;
					}

					temp[order[T.charAt(i) - 'a'] - 1] = T.charAt(i) - 'a';
				} else {

					temp[order[T.charAt(i) - 'a'] - 1] = T.charAt(i) - 'a';
				}
			}
		}

		for (int p : order) {
			System.out.println(p);
		}

		for (int q : temp) {
			System.out.println(q);
		}

		StringBuilder result = new StringBuilder();

		for (i = 0; i < temp.length; i++) {
			result.append((char)(temp[i] + 'a'));
		}

		return result.toString();
	}

	public String customSortString(String S, String T) {
		int[] count = new int[26];
		for (char c : T.toCharArray()) { ++count[c - 'a']; }  // count each char in T.
		StringBuilder sb = new StringBuilder();
		for (char c : S.toCharArray()) {
			while (count[c - 'a']-- > 0) { sb.append(c); }    // sort chars both in T and S by the order of S.
		}
		for (char c = 'a'; c <= 'z'; ++c) {
			while (count[c - 'a']-- > 0) { sb.append(c); }    // group chars in T but not in S.
		}
		return sb.toString();


		int[] count = new int[26];
        
        for (char c: T.toCharArray()) count[c - 'a']++;
        //append the characters in S first according to their frequncies in T
        StringBuilder sb = new StringBuilder();
        for (char c: S.toCharArray()){
            for (int i = 0; i < count[c - 'a']; i++) sb.append(c);
            count[c - 'a'] = 0;
        }
        //append the rest 26 characters
        for (char c = 'a'; c <= 'z'; c++){
            for (int i = 0; i < count[c - 'a']; i++) sb.append(c);
        }
        return sb.toString();
	}


	public static void main(String[] args) {
		// System.out.println(new Custom_Sort_String_791().customSortString("cba", "abcd"));
		System.out.println(new Custom_Sort_String_791().customSortString("kqep", "pekeq"));
	}
}