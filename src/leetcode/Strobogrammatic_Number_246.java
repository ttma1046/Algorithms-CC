package leetcode;
import java.util.Map;
import java.util.HashMap;
/*
Given a string num which represents an integer, return true if num is a strobogrammatic number.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Example 1:

Input: num = "69"
Output: true

Example 2:

Input: num = "88"
Output: true

Example 3:

Input: num = "962"
Output: false

Example 4:

Input: num = "1"
Output: true

Constraints:

1 <= num.length <= 50
num consists of only digits.
num does not contain any leading zeros except for zero itself.
*/
class Strobogrammatic_Number_246 {
    public boolean isStrobogrammatic(String num) {
        /*
        0 = 0
        1 = 1
        6 = 9
        9 = 6
        8 = 8
        */

        StringBuilder sb = new StringBuilder();

        Map<Character, Character> map = new HashMap<Character, Character>();

        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        for (int i = num.length() - 1; i >= 0; --i) {
            char c = num.charAt(i);

            if (!map.containsKey(c)) return false;

            sb.append(map.get(c));
        }

        System.out.println(sb.toString());

        return num.equals(sb.toString());
    }

    public boolean isStrobogrammaticII(String num) {
        /*
        0 = 0
        1 = 1
        6 = 9
        9 = 6
        8 = 8
        */
        Map<Character, Character> map = new HashMap<Character, Character>();

        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        for (int i = 0, j = num.length() - 1; i <= j; ++i, --j) {
            char l = num.charAt(i);
            char r = num.charAt(j);

            if (!map.containsKey(r) || map.get(r) != l) return false;
        }

        return true;
    }

    public static void main(String[] args) {
    	Strobogrammatic_Number_246 obj = new Strobogrammatic_Number_246();
    	boolean res = obj.isStrobogrammaticII("69");
		// res = obj.isStrobogrammatic("88");
		System.out.println(res);

        res = obj.isStrobogrammaticII("962");

        System.out.println(res);
    }
}