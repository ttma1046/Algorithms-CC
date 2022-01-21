package hashtable;

/*
In an alien language, surprisingly they also use english lowercase letters,

but possibly in a different order.

The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language,

and the order of the alphabet,

return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language,
then words[0] > words[1], hence the sequence is unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match,
and the second string is shorter (in size.) According to lexicographical rules "apple" > "app",
because 'l' > '∅',
where '∅' is defined as the blank character which is less than any other character (More info).

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/

public class Verifying_an_Alien_Dictionary_953 {
    int[] orderList = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 0 || order == null) return false;

        for (int i = 0; i < order.length(); i++)
            orderList[order.charAt(i) - 'a'] = i;

        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;

        return true;
    }

    boolean bigger(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        for (int i = 0; i < s1Length && i < s2Length; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                return orderList[s1.charAt(i) - 'a'] > orderList[s2.charAt(i) - 'a'];

        return s1Length > s2Length;
    }

    public static void main(String[] args) {
        String[] words = new String[] {"hello", "leetcode"};
        System.out.println(new VerifyinganAlianDictionary_953().isAlienSorted(words, "hlabcdefgijkmnopqrstuvwxyz"));

        words = new String[] {"word", "world", "row"};
        System.out.println(new VerifyinganAlianDictionary_953().isAlienSorted(words, "worldabcefghijkmnpqstuvxyz"));

        words = new String[] {"apple", "app"};
        System.out.println(new VerifyinganAlianDictionary_953().isAlienSortedII(words, "abcdefghijklmnopqrstuvwxyz"));

        words = new String[] {"acpl", "adpp"};
        System.out.println(new VerifyinganAlianDictionary_953().isAlienSortedII(words, "abcdefghijklmnopqrstuvwxyz"));
    }

    public boolean isAlienSortedII(String[] words, String order) {
        int[] orderArray = new int[26];

        for (int i = 0; i < order.length(); ++i)
            orderArray[order.charAt(i) - 'a'] = i;

        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i], orderArray))
                return false;

        return true;
    }

    private boolean bigger(String prev, String next, int[] orderArray) {
        int prevLength = prev.length();
        int nextLength = next.length();

        for (int i = 0; i < prevLength && i < nextLength; ++i)
            if (prev.charAt(i) != next.charAt(i))
                return orderArray[prev.charAt(i) - 'a'] > orderArray[next.charAt(i) - 'a'];    

        return prevLength > nextLength;
    }
}
