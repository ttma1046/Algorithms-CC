package string;

/*
A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.

Given a string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

Example 1:

Input: word = "internationalization", abbr = "i12iz4n"
Output: true

Example 2:

Input: word = "apple", abbr = "a2e"
Output: false

Constraints:

1 <= word.length, abbr.length <= 20
word consists of only lowercase English letters.
abbr consists of lowercase English letters and digits.
*/
class Valid_Word_Addreviation_408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
                continue;
            }

            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }

            int start = 0;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                start = start * 10 + (abbr.charAt(j++) - '0');
            }

            i += start;
        }

        return i == word.length() && j == abbr.length();
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) return false;

        char[] arr = abbr.toCharArray();
        char[] fake = new char[word.length()];

        int num = 0;
        int idx = 0;

        for(char c : arr) {
            if(c == '0' && num == 0) return false;
            if(c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                idx += num;
                if(idx >= word.length() || c != word.charAt(idx)) return false;
                num = 0;
                idx++;
            }
        }

        if(idx + num !=  word.length()) return false;
        return true;
    }

    public static void main(String[] args) {
        Valid_Word_Addreviation_408 obj = new Valid_Word_Addreviation_408();

        System.out.println(obj.validWordAbbreviation("internationalization", "i12iz4n"));
    }
}






