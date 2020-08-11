package dp;

import java.util.HashMap;

public class CoinChange {
    public static long makeChange(int[] coins, int money) {
        return makeChange(coins, money, 0, new HashMap<String, Long>());
    }

    public static long makeChange(int[] coins, int money, int index, HashMap<String, Long> memo) {
        if (money == 0) {
            return 1;
        }

        if (index >= coins.length) {
            return 0;
        }

        String key = money + "-" + index; // "29" + "1" || "2" + "91"

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int amountWithCoin = 0;
        long ways = 0;
        while (amountWithCoin <= money) {
            int remaining = money - amountWithCoin;
            ways += makeChange(coins, remaining, index + 1, memo);
            amountWithCoin += coins[index];
        }
        memo.put(key, ways);
        return ways;
    }

    public static long makeChangeIII(int[] coins, int money) {
        long[] dp = new long[money + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < money + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[money];
    }
    
    public static long makeChangeII(int[] coins, int money) {
        return makeChangeII(coins, money, 0);
    }

    private static long makeChangeII(int[] coins, int money, int index) {
        if (money == 0) {
            return 1;
        }

        if (index >= coins.length) {
            return 0;
        }

        int amountWithCoin = 0;
        int ways = 0;
        while (amountWithCoin <= money) {
            int remaining = money - amountWithCoin;
            ways += makeChangeII(coins, remaining, index + 1);
            amountWithCoin += coins[index];
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(makeChangeIII(new int[] { 1, 2, 5 }, 5));

        System.out.println(makeChangeIII(new int[] { 16, 30, 9, 17, 40, 13, 42, 5, 25, 49, 7, 23, 1, 44, 4, 11, 33, 12, 27,
                2, 38, 24, 28, 32, 14, 50 }, 245));
    }
}
