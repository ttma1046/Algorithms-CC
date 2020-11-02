package leetcode;
/*
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.

Example 1:
Input:
26

Output:
"1a"


Example 2:
Input:
-1

Output:
"ffffffff"

*/
class Convert_a_Number_to_Hexadecimal_405 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        char[] map = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'e', 'f'};
        int temp = num;
        StringBuilder sb = new StringBuilder();

        while (temp > 0) {
            sb.append(map[temp % 16]);
            temp /= 16;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(26));

        System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(27));

        System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(28));

        System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(-1));
    }
}