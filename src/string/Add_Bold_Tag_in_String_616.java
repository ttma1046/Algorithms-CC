package string;

/*
You are given a string s and an array of strings words.

You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.

If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.

If two substrings wrapped by bold tags are consecutive, you should combine them.

Return s after adding the bold tags.

Example 1:

Input: s = "abcxyz123", words = ["abc","123"]
Output: "<b>abc</b>xyz<b>123</b>"
Example 2:

Input: s = "aaabbcc", words = ["aaa","aab","bc"]
Output: "<b>aaabbc</b>c"


Constraints:

1 <= s.length <= 1000
0 <= words.length <= 100
1 <= words[i].length <= 1000
s and words[i] consist of English letters and digits.
All the values of words are unique.
*/
class Add_Bold_Tag_in_String_616 {
    public String addBoldTag(String s, String[] words) {
        boolean[] map = new boolean[s.length()];
        int end = 0;

        for (int i = 0; i < s.length(); ++i) {
            for (String word: words) 
                if (s.startsWith(word, i))
                    end = Math.max(end, i + word.length());
            
            map[i] = end > i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!map[i]) {
                sb.append(s.charAt(i));
                continue;
            }

            int j = i;

            while(j < s.length() && map[j])
                j++;

            sb.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }

        return sb.toString();
    }

    // O(m * n * l)

    public static void main(String[] args) {
        String s = "abcxyz123";
        String[] words = new String[] {"abc", "123"};

        Add_Bold_Tag_in_String_616 obj = new Add_Bold_Tag_in_String_616();

        obj.addBoldTag(s, words);
    }
}