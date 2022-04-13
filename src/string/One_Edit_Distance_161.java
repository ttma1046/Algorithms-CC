package string;
/*
Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.


Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.


Constraints:

0 <= s.length, t.length <= 104
s and t consist of lowercase letters, uppercase letters, and digits.
*/
class One_Edit_Distance_161 {
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        if (ns > nt)
            return isOneEditDistance(t, s);

        if (nt - ns > 1) return false;

        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) != t.charAt(i))
                if (nt == ns)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else
                    return s.substring(i).equals(t.substring(i + 1));

        return ns + 1 == nt;
    }

    public static void main(String[] args) {
        One_Edit_Distance_161 obj = new One_Edit_Distance_161();
        obj.isOneEditDistance("abc", "abcd");
    }
}