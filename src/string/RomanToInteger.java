package string;

import java.util.HashMap;

public class RomanToInteger {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();

        hash.put('I', 1);
        hash.put('V', 5);
        hash.put('X', 10);
        hash.put('L', 50);
        hash.put('C', 100);
        hash.put('D', 500);
        hash.put('M', 1000);

        int result = 0;

        result = hash.get(s.charAt(length - 1));
        int pivot = result;
        for (int i = length - 2; i >= 0; i--) {
            int current = hash.get(s.charAt(i));
            if (pivot > current) {
                result -= current;
            } else {
                result += current;
            }
            pivot = current;
        }

        return result;
    }
}
