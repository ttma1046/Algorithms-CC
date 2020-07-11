/*

A string S of lowercase English letters is given.

We want to partition this string into as many parts as possible

so that each letter appears in at most one part,

and return a list of integers representing the size of these parts.

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.


Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.


Approach 1: Greedy

Intuition

Let's try to repeatedly choose the smallest left-justified partition. Consider the first label, say it's 'a'. The first partition must include it, and also the last occurrence of 'a'. However, between those two occurrences of 'a', there could be other labels that make the minimum size of this partition bigger. For example, in "abccaddbeffe", the minimum first partition is "abccaddb". This gives us the idea for the algorithm: For each letter encountered, process the last occurrence of that letter, extending the current partition [anchor, j] appropriately.

Algorithm

We need an array last[char] -> index of S where char occurs last. Then, let anchor and j be the start and end of the current partition. If we are at a label that occurs last at some index after j, we'll extend the partition j = last[c]. If we are at the end of the partition (i == j) then we'll append a partition size to our answer, and set the start of our new partition to i+1.

Complexity Analysis

Time Complexity: O(N), where N is the length of S.

Space Complexity: O(1) to keep data structure last of not more than 26 characters.
*/

package greedy;
import java.util.List;
import java.util.ArrayList;

class Partition_Labels_763 {
    public List<Integer> partitionLabels(String s) {
        if (s.length() <= 0 || s == null || s == "") {
            return null;
        }

        int[] lastPoss = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            lastPoss[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<Integer>();
        int lastPos = 0, j = 0;
        for (int i = 0; i < s.length(); i++) {
            j = Math.max(j, lastPoss[s.charAt(i) - 'a']);

            // j = lastPoss[s.charAt(i) - 'a'];
            if (i == j) {
                result.add(j - lastPos + 1);
                lastPos = j + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new Partition_Labels_763().partitionLabels("ababcbacadefegdehijhklij");

        for (int s: result) {
            System.out.println(s);
        }
    }
}