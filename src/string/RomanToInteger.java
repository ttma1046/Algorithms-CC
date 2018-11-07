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

        int result = hash.get(s.charAt(length - 1));
        int pivit = result;

        for (int i = length - 2;i >= 0;i--) {
            int current = hash.get(s.charAt(i));

            if (current < pivit) {
                result -= current;
            } else {
                result += current;
            }

            pivit = current;
        }

        return result;
    }

    public int romanToIntII(String s) {
        int nums[]=new int[s.length()];

        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }

        int sum=0;

        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] < nums[i + 1])
                sum -= nums[i];
            else
                sum += nums[i];
        }

        return sum + nums[nums.length - 1];
    }
}
