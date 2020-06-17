Explanation
seen[i] means the current maximum possible sum that sum % 3 = i


Complexity
Time O(N)
Space O(1)


Java

    public int maxSumDivThree(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }
