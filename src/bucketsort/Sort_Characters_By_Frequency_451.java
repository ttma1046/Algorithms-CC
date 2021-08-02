package bucketsort;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/*
Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

Constraints:

1 <= s.length <= 5 * 105
s consists of English letters and digits.
*/
class Sort_Characters_By_Frequency_451 {

    // Arrays and Sorting
    public String frequencySortIII(String s) {
        if (s == null || s.isEmpty()) return s;

        // Create a sorted Array of chars.
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        // Convert identical chars into single Strings.
        List<String> charStrings = new ArrayList<String>();
        StringBuilder currentString = new StringBuilder();
        currentString.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                charStrings.add(currentString.toString());
                currentString = new StringBuilder();
            }
            currentString.append(chars[i]);
        }
        charStrings.add(currentString.toString());

        // Our comparator is (a, b) -> b.length() - a.length().
        // If a is longer than b, then a negative number will be returned
        // telling the sort algorithm to place a first. Otherwise, a positive
        // number will be returned, telling it to place a second.
        // This results in a longest to shortest sorted list of the strings.
        Collections.sort(charStrings, (a, b) -> b.length() - a.length());

        // Use StringBuilder to build the String to return.
        StringBuilder sb = new StringBuilder();
        for (String str : charStrings) sb.append(str);
        return sb.toString();
    }

    /*
    Time Complexity : O(nlogn).

    The first part of the algorithm, converting the String to a List of characters, has a cost of O(n), because we are adding nn characters to the end of a List.

    The second part of the algorithm, sorting the List of characters, has a cost of O(nlogn).

    The third part of the algorithm, grouping the characters into Strings of similar characters,

    has a cost of O(n) because each character is being inserted once into a StringBuilder and once converted into a String.

    Finally, the fourth part of the algorithm, sorting the Strings by length, has a worst case cost of O(n), which occurs when all the characters in the input String are unique.

    Because we drop constants and insignificant terms, we get O(nlogn) + 3⋅O(n) = O(nlogn).

    Be careful with your own implementation—if you didn't do the string building process in a sensible way, then your solution could potentially be O(n^2).

    Space Complexity : O(n).

    It is impossible to do better with the space complexity, because Strings are immutable.

    The List of characters, List of Strings, and the final output String, are all of length nn, so we have a space complexity of O(n).
    */
    // HashMap and Sort
    public String frequencySortI(String s) {
        // Count up the occurances.
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Make a list of the keys, sorted by frequency.
        List<Character> characters = new ArrayList<>(counts.keySet());
        Collections.sort(characters, (a, b) -> counts.get(b) - counts.get(a));

        // Convert the counts into a string with a sb.
        StringBuilder sb = new StringBuilder();
        for (char c : characters) {
            int copies = counts.get(c);
            for (int i = 0; i < copies; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /*
    Let n be the length of the input String and k be the number of unique characters in the String.

    We know that k ≤ n,

    because there can't be more unique characters than there are characters in the String.

    We also know that kk is somewhat bounded by the fact that there's only a finite number of characters in Unicode

    (or ASCII, which I suspect is all we need to worry about for this question).

    There are two ways of approaching the complexity analysis for this question.

    We can disregard k, and consider that in the worst case, k = n.
    We can consider k, recognising that the number of unique characters is not infinite.

    This is more accurate for real world purposes.

    I've provided analysis for both ways of approaching it.

    I choose not to bring it up for the previous approach, because it made no difference there.

    Time Complexity : O(nlogn) OR O(n+klogk).

    Putting the characterss into the HashMap has a cost of O(n),

    because each of the nn characterss must be put in, and putting each in is an O(1) operation.

    Sorting the HashMap keys has a cost of O(klogk),

    because there are k keys,

    and this is the standard cost for sorting.

    If only using nn, then it's O(nlogn).

    For the previous question, the sort was carried out on nn items, not kk, so was possibly a lot worse.

    Traversing over the sorted keys and building the String has a cost of O(n), as n characters must be inserted.

    Therefore, if we're only considering nn, then the final cost is O(nlogn).

    Considering kk as well gives us O(n+klogk),

    because we don't know which is largest out of nn and klogk.

    We do, however, know that in total this is less than or equal to O(nlogn).

    Space Complexity : O(n).

    The HashMap uses O(k) space.

    However, the StringBuilder at the end dominates the space complexity,

    pushing it up to O(n),
    as every character from the input String must go into it.

    Like was said above, it's impossible to do better with the space complexity here.

    What's interesting here is that if we only consider n,

    the time complexity is the same as the previous approach.

    But by considering k, we can see that the difference is potentially substantial.
    */

    // Multiset and Bucket Sort
    public String frequencySortII(String s) {
        if (s == null || s.isEmpty()) return s;

        // Count up the occurances.
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        int maximumFrequency = Collections.max(counts.values());

        // Make the list of buckets and apply bucket sort.
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= maximumFrequency; i++) {
            buckets.add(new ArrayList<Character>());
        }
        for (Character key : counts.keySet()) {
            int freq = counts.get(key);
            buckets.get(freq).add(key);
        }

        // Build up the string.
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.size() - 1; i >= 1; i--) {
            for (Character c : buckets.get(i)) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    /*
    Let n be the length of the input String. 

    The k (number of unique characters in the input String that we considered for the last approach makes no difference this time).

    Time Complexity : O(n).

    Like before, the HashMap building has a cost of O(n).

    The bucket sorting is O(n), because inserting items has a cost of O(k) (each entry from the HashMap),

    and building the buckets initially has a worst case of O(n) (which occurs when k = 1). Because k ≤ n, we're left with O(n).

    So in total, we have O(n).

    It'd be impossible to do better than this, because we need to look at each of the nn characters in the input String at least once.

    Space Complexity : O(n).

    Same as above. The bucket Array also uses O(n) space, because its length is at most n, 

    and there are k items across all the buckets.
    */

    public static void main(String[] args) {
        Sort_Characters_By_Frequency_451 obj = new Sort_Characters_By_Frequency_451();
        System.out.println(obj.frequencySort("cccaaa"));
    }

    public String frequencySort(String s) {
        if (s == null || s.isEmpty()) return s;

        int n = s.length();

        int freq[] = new int[256];

        for (char c : s.toCharArray()) freq[(int) c]++;

        char output[] = new char[n];
        int index = 0;
        int maxFreq;
        int maxFreqNumberChar;

        while (index < n) {
            maxFreq = 0;
            maxFreqNumberChar = -1;
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > maxFreq) {
                    maxFreq = freq[i];
                    maxFreqNumberChar = i;
                }
            }
            //all elements are over
            if (maxFreq == 0) return new String(output);

            while (maxFreq-- > 0) output[index++] = (char)maxFreqNumberChar;

            freq[maxFreqNumberChar] = 0; //nullify this character as it used.
        }

        return new String(output);
    }
}