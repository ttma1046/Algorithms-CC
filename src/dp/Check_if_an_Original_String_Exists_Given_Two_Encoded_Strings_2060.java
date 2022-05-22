package dp;
/*
An original string, consisting of lowercase English letters, can be encoded by the following steps:

Arbitrarily split it into a sequence of some number of non-empty substrings.
Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
Concatenate the sequence as the encoded string.
For example, one way to encode an original string "abcdefghijklmnop" might be:

Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
Concatenate the elements of the sequence to get the encoded string: "ab121p".
Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

Example 1:

Input: s1 = "internationalization", s2 = "i18n"
Output: true
Explanation: It is possible that "internationalization" was the original string.
- "internationalization"
  -> Split:       ["internationalization"]
  -> Do not replace any element
  -> Concatenate:  "internationalization", which is s1.
- "internationalization"
  -> Split:       ["i", "nternationalizatio", "n"]
  -> Replace:     ["i", "18",                 "n"]
  -> Concatenate:  "i18n", which is s2

Example 2:

Input: s1 = "l123e", s2 = "44"
Output: true
Explanation: It is possible that "leetcode" was the original string.
- "leetcode"
  -> Split:      ["l", "e", "et", "cod", "e"]
  -> Replace:    ["l", "1", "2",  "3",   "e"]
  -> Concatenate: "l123e", which is s1.
- "leetcode"
  -> Split:      ["leet", "code"]
  -> Replace:    ["4",    "4"]
  -> Concatenate: "44", which is s2.

Example 3:

Input: s1 = "a5b", s2 = "c5b"
Output: false
Explanation: It is impossible.
- The original string encoded as s1 must start with the letter 'a'.
- The original string encoded as s2 must start with the letter 'c'.

Constraints:

1 <= s1.length, s2.length <= 40
s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
The number of consecutive digits in s1 and s2 does not exceed 3.
*/
class Check_if_an_Original_String_Exists_Given_Two_Encoded_Strings_2060 {
    String s1;
    String s2;
    Boolean[][][] memo;
    public boolean possiblyEquals(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;

        int n = s1.length();
        int m = s2.length();
        memo = new Boolean[n][m][2000];

        return dfs(0, 0, 0, n, m);
    }

    private boolean dfs(int i, int j, int diff, int n, int m) {
        if (i >= n && j >= m && diff == 0)
            return true;

        boolean res = false;

        if (memo[i][j][diff + 1000] != null)
            return memo[i][j][diff + 1000];

        if (i < n) {
            if (Character.isDigit(s1.charAt(i))) {
                int count = 0;
                int value = 0;
                while (i + count < n && count < 3 && Character.isDigit(s1.charAt(i + count))) {
                    value = value * 10 + (s1.charAt(i + count) - '0');
                    count++;
                    if (dfs(i + count, j, diff - value, n, m))
                        res = true;
                }
            } else {
                if (diff > 0) {
                    if (dfs(i + 1, j, diff - 1, n, m))
                        res = true;
                } else if (diff == 0 & j < m && s1.charAt(i) == s2.charAt(j)) {
                    if (dfs(i + 1, j + 1, diff, n, m))
                        res = true;
                }
            }
        }

        if (j < m) {
            if (Character.isDigit(s2.charAt(j))) {
                int count = 0;
                int value = 0;
                while (j + count < m && count < 3 && Character.isDigit(s2.charAt(j + count))) {
                    value = value * 10 + (s2.charAt(j + count) - '0');
                    count++;
                    if (dfs(i, j + count, diff + value, n, m))
                        res = true;
                }
            } else if (diff < 0 && dfs(i, j + 1, diff + 1, n, m))
                res = true;
        }

        memo[i][j][diff + 1000] = res;
        return res;
    }

    public static void main(String[] args) {
        Check_if_an_Original_String_Exists_Given_Two_Encoded_Strings_2060 Obj
            = new Check_if_an_Original_String_Exists_Given_Two_Encoded_Strings_2060();

        boolean res = obj.possiblyEquals("internationalization", "i18n");

        System.out.println(res);
    }
}