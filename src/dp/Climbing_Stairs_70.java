package dp;

class Climbing_Stairs_70 {
    public int climbStairsI(int n) {
        return climb_StairsI(0, n);
    }

    public int climb_StairsI(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_StairsI(i + 1, n) + climb_StairsI(i + 2, n);
    }

    public int climbStairsII(int n) {
        int[] memo = new int[n];
        return climb_StairsII(0, n, memo);
    }

    public int climb_StairsII(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }

        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_StairsI(i + 1, n) + climb_StairsI(i + 2, n);
        return memo[i];/
    }


    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = first;
            first = second;
            second = temp + first;
        }

        return second;
    }
}