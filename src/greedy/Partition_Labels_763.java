/*

A string S of lowercase English letters is given. 

We want to partition this string into 

as many parts as possible 

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

Time Complexity: O(N)O(N), where NN is the length of SS.

Space Complexity: O(1)O(1) to keep data structure last of not more than 26 characters.
*/

package greedy;

class Partition_Labels_763 {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    /*
    traverse the string record the last index of each char.

    using pointer to record end of the current sub string.
    */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, map[S.charAt(i) - 'a']);
            if (last == i) {
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }

    /*
    Figure out the rightmost index first and use it to denote the start of the next section.

    Reset the left pointer at the start of each new section.

    Store the difference of right and left pointers + 1 as in the result for each section.
    */

    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Integer> map = new HashMap();

        for (int i = 0; i < S.length(); i ++) {
            map.put(S.charAt(i), i);
        }

        List<Integer> result = new LinkedList();
        int right = 0;
        int size = 0;

        for (int left = 0; left < S.length(); left ++) {
            size++;
            right = Math.max(right, map.get(S.charAt(left)));

            if (left == right) {
                result.add(size);
                size = 0;
            }
        }

        return result;
    }
}