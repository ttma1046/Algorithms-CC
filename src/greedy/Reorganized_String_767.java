/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/
package greedy;
import java.util.Arrays;

class Reorganized_String_767 {
    /*
    Approach #1: Sort by Count [Accepted]
    Intuition

    If we should make no two 'a's adjacent, it is natural to write "aXaXaXa..." where "X" is some letter. For now, let's assume that the task is possible (ie. the answer is not "".)

    Let's sort the string S, so all of the same kind of letter occur in continuous blocks. Then when writing in the following interleaving pattern, like S[3], S[0], S[4], S[1], S[5], S[2], adjacent letters never touch. (The specific interleaving pattern is that we start writing at index 1 and step by 2; then start from index 0 and step by 2.)

    The exception to this rule is if N is odd, and then when interleaving like S[2], S[0], S[3], S[1], S[4], we might fail incorrectly if there is a block of the same 3 letters starting at S[0] or S[1]. To prevent failing erroneously in this case, we need to make sure that the most common letters all occur at the end.

    Finally, it is easy to see that if N is the length of the string, and the count of some letter is greater than (N + 1) / 2, the task is impossible.

    Algorithm

    Find the count of each character, and use it to sort the string by count.

    If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task is impossible.

    Otherwise, interleave the characters in the order described above.


    Complexity Analysis

    Time Complexity: O(A(N + logA)), where N is the length of S, and A is the size of the alphabet. In Java, our implementation is O(N + AlogA). If A is fixed, this complexity is O(N).

    Space Complexity: O(N). In Java, our implementation is O(N + A).
    */
    public String reorganizeString(String S) {
        int length = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray()) counts[c - 'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[length];
        int index = 1;
        for (int count : counts) {

            if (count > 26) {
                int frequency = count / 100;
                char cha = (char) ('a' + (count % 100));

                if (frequency > (length + 1) / 2) {
                    return "";
                }

                for (int i = 0; i < frequency; ++i) {
                    if (index >= length) index = 0;

                    ans[index] = cha;
                    index += 2;
                }
            }
        }

        return String.valueOf(ans);
    }

    /*
    No Sort O(N):

    1. count letter appearance and store in hash[i]
    2. find the letter with largest occurence.
    3. put the letter into even index numbe (0, 2, 4 ...) char array
    4. put the rest into the array
    */
    public String reorganizeStringIII(String S) {
        int[] hash = new int[26];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[S.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }


    /*
    Approach #2: Greedy with Heap [Accepted]
    Intuition

    One consequence of the reasoning in Approach #1,

    is that a greedy approach that tries to write the most common letter (that isn't the same as the previous letter written) will work.

    The reason is that the task is only impossible if the frequency of a letter exceeds (N+1) / 2.

    Writing the most common letter followed by the second most common letter keeps this invariant.

    A heap is a natural structure to repeatedly return the current top 2 letters with the largest remaining counts.

    Approach

    We store a heap of (count, letter). [In Python, our implementation stores negative counts.]

    We pop the top two elements from the heap (representing different letters with positive remaining count),

    and then write the most frequent one that isn't the same as the most recent one written.

    After, we push the correct counts back onto the heap.

    Actually, we don't even need to keep track of the most recent one written.

    If it is possible to organize the string, the letter written second can never be written first in the very next writing.

    At the end, we might have one element still on the heap, which must have a count of one.

    If we do, we'll add that to the answer too.

    Proof of Invariant

    The invariant mentioned in the [Intuition] section seems true when playing with it, but here is a proof.
    Let Ci be the count of each letter yet to be written, and N be the number of letters left to write.
    We want to show this procedure maintains the invariant 2 ∗ max(Ci) <= N + 1
    ​                                                            i
    Say C'_i are the counts after one writing step.

    This completes the proof of this invariant.

    * if max(Ci) > 3rdmax(Ci), then (C'_i) <= max(Ci) - 1, so 2max(C'i) <= 2max(Ci) - 2 <= N - 1 as desired.
    * if M = max(Ci) = 3rdmax(Ci), then 3M <= N, Also ,because M >= 1, N + 3. Then, 2M <= 2N/3 <= N -1 as desired.

    Complexity Analysis

    Time Complexity: O(NlogA)), where N is the length of S, and A is the size of the alphabet. If A is fixed, this complexity is O(N).

    Space Complexity: O(A). If A is fixed, this complexity is O(1).
    */
    public String reorganizeStringII(String S) {
        int length = S.length();

        int[] count = new int[26];

        for (char c : S.toCharArray()) {
            count[c - 'a']++;
        }

        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0) {
                if (count[i] > (length + 1) / 2) { 
                    return ""; 
                }

                pq.add(new MultiChar(count[i], (char) ('a' + i)));
            }
        }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) { 
                pq.add(mc1);
            }
            if (--mc2.count > 0) {
                pq.add(mc2);
            }
        }

        if (pq.size() > 0) { 
            ans.append(pq.poll().letter);
        }
        
        return ans.toString();
    }

    public String reorganizeString(String S) {
        // Create map of each char to its count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) return "";
            map.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : map.keySet()) {
            pq.add(new int[] {c, map.get(c)});
        }
        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }

    /*
    Time O(N): fill hash[] + find the letter + write results into char array
    Space O(N + 26): result + hash[]
    */
    public static void main(String[] args) {
        System.out.println(new Reorganized_String_767().reorganizeString("ccaaabb"));
    }
}

class MultiChar {
    int count;
    char letter;
    MultiChar(int ct, char ch) {
        count = ct;
        letter = ch;
    }
}
