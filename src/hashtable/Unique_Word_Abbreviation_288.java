package hashtable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/*
The abbreviation of a word is a concatenation of its first letter,
the number of characters between the first and last letter,
and its last letter.

If a word has only two characters, then it is an abbreviation of itself.

For example:

dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'.
internationalization --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'.
it --> it because any word with only two characters is an abbreviation of itself.
Implement the ValidWordAbbr class:

ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary of words.
boolean isUnique(string word) Returns true if either of the following conditions are met (otherwise returns false):
There is no word in dictionary whose abbreviation is equal to word's abbreviation.
For any word in dictionary whose abbreviation is equal to word's abbreviation, that word and word are the same.

Example 1:

Input
["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique", "isUnique"]
[[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"], ["cake"]]
Output
[null, false, true, false, true, true]

Explanation
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("dear"); // return false, dictionary word "deer" and word "dear" have the same abbreviation "d2r" but are not the same.
validWordAbbr.isUnique("cart"); // return true, no words in the dictionary have the abbreviation "c2t".
validWordAbbr.isUnique("cane"); // return false, dictionary word "cake" and word "cane" have the same abbreviation  "c2e" but are not the same.
validWordAbbr.isUnique("make"); // return true, no words in the dictionary have the abbreviation "m2e".
validWordAbbr.isUnique("cake"); // return true, because "cake" is already in the dictionary and no other word in the dictionary has "c2e" abbreviation.

Constraints:

1 <= dictionary.length <= 3 * 104
1 <= dictionary[i].length <= 20
dictionary[i] consists of lowercase English letters.
1 <= word.length <= 20
word consists of lowercase English letters.
At most 5000 calls will be made to isUnique.
*/
class Unique_Word_Abbreviation_288 {
    Map<String, List<String>> map = new HashMap<>();

    public Unique_Word_Abbreviation_288(String[] dictionary) {
        for (String abc : dictionary) {
		    String abbr = getAbbr(abc);

            if (!map.containsKey(abbr)) {
                map.put(abbr, new ArrayList<String>());
            }

            map.get(abbr).add(abc);
        }
    }

    public boolean isUnique(String word) {
        if (!map.containsKey(getAbbr(word))) return true;

        if (map.containsKey(getAbbr(word)) && map.get(getAbbr(word)).size() == 1 && map.get(getAbbr(word)).get(0).equals(word)) return true;

        return false;
    }

    String getAbbr(String item) {
        int length = item.length();

        return length > 2 ? item.substring(0, 1) + (length - 2) + item.substring(length - 1, length) : item;
    }

    public static void main(String[] args) {
        String[] dictionary = new String[] { "deer", "door", "cake", "card" };

        Unique_Word_Abbreviation_288 obj = new Unique_Word_Abbreviation_288(dictionary);

        System.out.println(obj.isUnique("dear")); // return false, dictionary word "deer" and word "dear" have the same abbreviation "d2r" but are not the same.
        System.out.println(obj.isUnique("cart")); // return true, no words in the dictionary have the abbreviation "c2t".
        System.out.println(obj.isUnique("cane")); // return false, dictionary word "cake" and word "cane" have the same abbreviation  "c2e" but are not the same.
        System.out.println(obj.isUnique("make")); // return true, no words in the dictionary have the abbreviation "m2e".
        System.out.println(obj.isUnique("cake")); // return true, because "cake" is already in the dictionary and no other word in the dictionary has "c2e" abbreviation.
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */