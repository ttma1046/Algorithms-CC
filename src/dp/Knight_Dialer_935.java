package dp;
/*
The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:

We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).

Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.

Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.

Constraints:

1 <= n <= 5000
*/
class Knight_Dialer_935 {
    public static final int max = (int) Math.pow(10, 9) + 7;

    public int knightDialer(int n) {
        long s = 0;
        //do n hops from every i, j index (the very requirement of the problem)
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 3; j++)
                s = paths(i, j, n));
        
        return (int) s;
    }

    private long paths(int i, int j, int n) {
        // if the knight hops outside of the matrix or to * return 0
        //as there are no unique paths from here
        if(i < 0 || j < 0 || i > 3 || j > 2 || (i == 3 && j != 1)) return 0;
        //trivial case
        if(n == 1) return 1;
        //non trivial case
        long s = paths(i - 1, j - 2, n - 1) % max + // jump to a
                 paths(i - 2, j - 1, n - 1) % max + // jump to b
                 paths(i - 2, j + 1, n - 1) % max + // jump to c
                 paths(i - 1, j + 2, n - 1) % max + // jump to d
                 paths(i + 1, j + 2, n - 1) % max + // jump to e
                 paths(i + 2, j + 1, n - 1) % max + // jump to f
                 paths(i + 2, j - 1, n - 1) % max + // jump to g
                 paths(i + 1, j - 2, n - 1) % max; // jump to h
        return s;
    }

    public static final int max = (int)Math.pow(10, 9) + 7;

    public int knightDialer(int n) {
        long temp[][][] = new long[n + 1][4][3];

        long s = 0;

        for(int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++) 
                s = (s + paths(temp, i, j, n)) % max;
        
        return (int)s;
    }

    private long paths(long[][][] temp, int i, int j, int n) {
        if (i < 0 || j < 0 || i > 3 || j > 2 || (i == 3 && j != 1)) return 0;

        if (n == 1) return 1;

        if (temp[n][i][j] > 0) return temp[n][i][j];

        temp[n][i][j] = paths(temp, i - 1, j - 2, n - 1) % max +
                        paths(temp, i - 1, j + 2, n - 1) % max +
                        paths(temp, i + 1, j - 2, n - 1) % max +
                        paths(temp, i + 1, j + 2, n - 1) % max +
                        paths(temp, i - 2, j + 1, n - 1) % max +
                        paths(temp, i - 2, j - 1, n - 1) % max +
                        paths(temp, i + 2, j + 1, n - 1) % max +
                        paths(temp, i + 2, j - 1, n - 1) % max;

        return temp[n][i][j];
    }
}