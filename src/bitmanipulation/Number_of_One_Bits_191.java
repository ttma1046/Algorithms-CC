package bitmanipulation;

/*
Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.


Example 1:

Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
Example 2:

Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
Example 3:

Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.


Constraints:

The input must be a binary string of length 32.
*/

public class Number_of_One_Bits_191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) res++;

            mask <<= 1;
        }

        return res;
    }

    public static void main(String[] args) {
        Number_of_One_Bits_191 obj = new Number_of_One_Bits_191();
        int res = obj.hammingWeight(11);
        System.out.println(res);

        int k = 1;
        System.out.println(k << 1);

        k = 2;
        System.out.println(k << 1);

        k = 6;
        System.out.println(k << 1);

        k = 6;
        System.out.println(k >> 1);

        k = 14;
        System.out.println(k >> 1);

        k = 11;
        System.out.println(k >> 1);

        k = 11;
        System.out.println(k >>> 1);
    }
}