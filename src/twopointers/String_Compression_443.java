package twopointers;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.

Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
*/
class String_Compression_443 {
    public int compress(char[] chars) {
        if(a == null || a.length == 0) return 0;

        int index = 0;
        int i = 0;

        while (i < chars.length) {
            char c = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == c) {
                i++;
                count++;
            }

            chars[index++] = c;

            if (count > 1)
                for(char b : Integer.toString(count).toCharArray())
                    chars[index++] = b;
        }

        return index;
    }

    public static void main(String[] args) {
        String_Compression_443 obj = new String_Compression_443();

        // char[] chars = new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };

        //  System.out.println(obj.compress(chars));

        Integer[] digits = obj.getDigits(12345);
        System.out.println(Arrays.toString(digits));
    }

    public  Integer[] getDigits(int num) {
        List<Integer> digits = new ArrayList<Integer>();
        collectDigits(num, digits);
        return digits.toArray(new Integer[] {});
    }

    private void collectDigits(int num, List<Integer> digits) {
        System.out.println("num:" + num);
        if(num / 10 > 0) {
            System.out.println("reccu");
            collectDigits(num / 10, digits);
        }
        
        digits.add(num % 10);
        System.out.println(num % 10);
    }

    public int compressII(char[] chars) {
        int len = 0; // also a pointer to modify array in-place
        int i = 0;
        while(i < chars.length) {
            chars[len] = chars[i];
            int j = i + 1;

            while (j < chars.length && chars[j] == chars[i])
                j++;

            if (j - i > 1) { // need compression
                String freq = j - i + "";
                for (char c : freq.toCharArray())
                    chars[++len] = c;
            }
            
            len++;
            i = j;
        }
        return len;
    }
}