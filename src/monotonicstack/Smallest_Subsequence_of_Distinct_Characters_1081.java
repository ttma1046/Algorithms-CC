package monotonicstack;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
/*
Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/

Example 1:

Input: s = "bcabc"
Output: "abc"

Example 2:

Input: s = "cbacdcbc"
Output: "acdb"

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/
/*
class Smallest_Subsequence_of_Distince_Characters_1081 {
    public String smallestSubsequence(String s) {
        return "";
    }
}
*/

/*
Given a string s, remove duplicate letters so that every letter appears once and only once. 

You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/
class Smallest_Subsequence_of_Distince_Characters_1081 {
    public String removeDuplicateLetters(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] last = new int[128];
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) last[s.charAt(i)] = i;
        for (int i = 0; i < s.length(); ++i) {
        	int c = s.charAt(i);
        	if (!visited.add(c)) continue;
        	while(stack.size() > 0 && c < stack.peek() && i < last[stack.peek()]) visited.remove(stack.pop());
        	stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i: stack) sb.append((char)(i));
       	return sb.toString();
    }

    public static void main(String[] args) {
    	Smallest_Subsequence_of_Distince_Characters_1081 obj = new Smallest_Subsequence_of_Distince_Characters_1081();
		String res = obj.removeDuplicateLetters("abc");
		System.out.println(res);
    }
}