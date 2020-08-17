package dp;

import java.util.Arrays;

/*
You are given coins of different denominations and a total amount of money amount.

Write a function to compute the fewest number of coins that you need to make up that amount.

If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/
public class CoinChange_322 {
    public int coinChangeBest(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idxCoin, int[] coins, int amount) {
        String space = "";

        if (amount == 0) {
            return 0;
        }

        if (idxCoin < coins.length && amount > 0) {
            int maxAmountofThisCoin = amount / coins[idxCoin];

            int minAmountofCoins = Integer.MAX_VALUE;
            for (int currentamountofthiscoin = 0; currentamountofthiscoin <= maxAmountofThisCoin; currentamountofthiscoin++) {
                if (currentamountofthiscoin * coins[idxCoin] <= amount) {
                    int res = coinChange(idxCoin + 1, coins, amount - currentamountofthiscoin * coins[idxCoin]);

                    if (res != -1) {
                        minAmountofCoins = Math.min(minAmountofCoins, res + currentamountofthiscoin);
                    }
                }
            }
            return (minAmountofCoins == Integer.MAX_VALUE) ? -1 : minAmountofCoins;
        }

        return -1;
    }

    public int coinChangeII(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return coinChangeII(coins, amount, new int[amount]);
    }

    private int coinChangeII(int[] coins, int rem, int[] count) {
        if (rem < 0)
            return -1;
        if (rem == 0)
            return 0;
        if (count[rem - 1] != 0)
            return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeII(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public int coinChangeIII(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChangeIV(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount + 1];
        int sum = 0;

        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        new CoinChange_322().coinChange(new int[] { 1, 2, 5 }, 15);
    }
}
