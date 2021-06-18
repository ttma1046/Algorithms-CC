package string;

/*
Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:

Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
*/

class Count_Binary_Substrings_696 {
    public int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];

        int t = 0;

        groups[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1))	groups[++t] = 1;
            else groups[t]++;
        }

        int ans = 0;
        for (int i = 1; i <= t; i++) ans += Math.min(groups[i - 1], groups[i]);
        return ans;
    }
    /*
        Time Complexity: O(N), where N is the length of s. Every loop is through O(N) items with O(1) work inside the for-block.

    	Space Complexity: O(N), the space used by groups.
    */

    public int countBinarySubstrings(String s) {
        int ans = 0, prev = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans + Math.min(prev, cur);
    }

    public int countBinarySubstrings(String s) {
        if (s.length() < 2) {
            return 0;
        }
        char[] cArr = s.toCharArray();
        int result = 0;
        char c = cArr[0];
        //previous
        int previous = 0;
        //curr
        int curr = 0;
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == c) {
                curr++;
            } else {
                c = cArr[i];
                result += Math.min(curr, previous);
                previous = curr;
                curr = 1;
            }
        }
        result += Math.min(curr, previous);
        return result;
    }

    /*
    Time Complexity: O(N), where N is the length of s. Every loop is through O(N) items with O(1) work inside the for-block.

    Space Complexity: O(1), the space used by prev, cur, and ans.
    */
    public static void main(String[] args) {
        Count_Binary_Substrings_696 obj = new Count_Binary_Substrings_696();

        System.out.println(obj.countBinarySubstrings("00110011"));
        System.out.println(obj.countBinarySubstrings("10101"));
    }
}