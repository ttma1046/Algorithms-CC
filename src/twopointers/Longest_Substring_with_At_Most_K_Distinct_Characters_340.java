package twopointers;

/*
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.


Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50
*/
// sliding window
class Longest_Substring_with_At_Most_K_Distinct_Characters_340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        
        int n = s.length(), i = 0, j = 0, res = 0;
        int[] map = new int[94];

        for (j = 0; j < n; ++j) {
            if (map[s.charAt(j) - ' ']++ == 0) k--;

            while (k < 0) {
                --map[s.charAt(i) - ' '];
                if (map[s.charAt(i) - ' '] == 0) ++k;
                i++;
            }

            if (j - i + 1 > res) {
                res = j - i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Longest_Substring_with_At_Most_K_Distinct_Characters_340().lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(new Longest_Substring_with_At_Most_K_Distinct_Characters_340().lengthOfLongestSubstringKDistinct("aa", 1));
    }
}