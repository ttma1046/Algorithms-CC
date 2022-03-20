package binarysearch;

/*
Given a non - negative integer x, compute and return the square root of x.

Since the return type is an integer, the decimal digits are truncated,

and only the integer part of the result is returned.

Note: You are not allowed to use any built - in exponent function or operator, such as pow(x, 0.5) or x * * 0.5.

Example 1:
Input: x = 4
Output: 2

Example 2:
Input: x = 8
Output: 2

Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

Constraints:
0 <= x <= 231 - 1
*/
class Sqrt_X_69 {
    public int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int mid, left = 2, right = x / 2;
        while (left <= right) {
            mid = left + (right - left) / 2;
            num = (long)mid * mid;
            if (num > x) right = mid - 1;
            else if (num < x) left = mid + 1;
            else return mid;
        }

        return right;
    }

    public int mySqrtII(int x) {
        if (x < 2) return x;

        int left = 0, right = x;
        int pivot = 0;
        while (left < right) {
            pivot = left + (right - left) / 2;

            if ((long)pivot * pivot <= x)
                left = pivot + 1;
            else
                right = pivot;
        }

        return left - 1;
    }

    public static void main(String[] args) {
        Sqrt_X_69 obj = new Sqrt_X_69();

        obj.mySqrt(10);
    }
}