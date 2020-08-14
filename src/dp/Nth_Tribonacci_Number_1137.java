package dp;

/*
The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

 

Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537
 

Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
*/
class Nth_Tribonacci_Number_1137 {
    public int tribonacci(int n) {
        if (n < 2) return n;
        
        if (n == 2) {
            return 1;
        }
        
        int first = 0;
        int second = 1;
        int third = 1;

        for (int i = 4; i < n + 1; i++) {
            int temp = first + second;
            first = second;
            second = third;
            third += temp;
        }

        return first + second + third;
    }

    public int tribonacciII(int n) {
        if (n < 2) return n;
        int a = 0, b = 1, c = 1, d;
        while (n-- > 2) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new Nth_Tribonacci_Number_1137().tribonacci(4));
        System.out.println(new Nth_Tribonacci_Number_1137().tribonacci(5));
    }
}