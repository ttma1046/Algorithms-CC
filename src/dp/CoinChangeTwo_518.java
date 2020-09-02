package dp;
import java.util.Arrays;
/*
    You are given coins of different denominations and a total amount of money.
    Write a function to compute the number of combinations that make up that amount.
    You may assume that you have infinite number of each kind of coin.

    Example 1:

    Input: amount = 5, coins = [1, 2, 5]
    Output: 4
    Explanation: there are four ways to make up the amount:
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1

    Example 2:

    Input: amount = 3, coins = [2]
    Output: 0
    Explanation: the amount of 3 cannot be made up just with coins of 2.

    Example 3:

    Input: amount = 10, coins = [10]
    Output: 1


    Note:

    You can assume that

    0 <= amount <= 5000
    1 <= coin <= 5000
    the number of coins is less than 500
    the answer is guaranteed to fit into signed 32-bit integer
*/
public class CoinChangeTwo_518 {
    public int changeDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChangeTwo_518().change(new int[] { 1, 2, 5 }, 5));

        /*
        System.out.println(new CoinChangeTwo_518().change(new int[] { 16, 30, 9, 17, 40, 13, 42, 5, 25, 49, 7, 23, 1, 44, 4, 11, 33, 12, 27,
                           2, 38, 24, 28, 32, 14, 50
                                                                        }, 245));

        long result = new CoinChangeTwo_518().change(new int [] { 2, 5, 10 }, 11);
        System.out.println(result);

        result = new CoinChangeTwo_518().change(new int [] { 10 }, 10);
        System.out.println(result);
        */
    }

    public int change(int[] coins, int amount) {
        if (amount == 0 || coins == null || coins.length == 0) {
            return 0;
        }

        Arrays.sort(coins);

        int[][] map = new int[amount + 1][coins.length];
        for (int i = 0; i < amount + 1; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                map[i][j] = -1;
            }
        }

        return changeRec(amount, coins, 0, map);
    }

    private int changeRec(int remaining, int[] coins, int index, int[][] memo) {
        if (remaining == 0) return 1;
        if (index == coins.length) return 0;
        if (memo[remaining][index] != -1) return memo[remaining][index];

        int ways = 0;
        for (int i = index; i < coins.length; i++) {
            if (coins[i] > remaining) break;

            int times = 1;
            while (times * coins[i] <= remaining) {
                ways += changeRec(remaining - times * coins[i], coins, i + 1, memo);
                times++;
            }
        }

        memo[remaining][index] = ways;        
        return memo[remaining][index];
    }

    public int changeIII(int amount, int[] coins) {
        return makeChange(coins, amount, 0, new int[amount + 1][coins.length]);
    }

    public int makeChange(int[] coins, int remaining, int index, int[][] memo) {
        if (remaining == 0) {
            return 1;
        }

        if (index >= coins.length) {
            return 0;
        }

        if (memo[remaining][index] > 0) {
            return memo[remaining][index];
        }

        int amountWithCoin = 0;
        int ways = 0;
        while (amountWithCoin <= remaining) {
            ways += makeChange(coins, remaining - amountWithCoin, index + 1, memo);
            amountWithCoin += coins[index];
        }
        memo[remaining][index] = ways;
        return memo[remaining][index];
    }

    public int changeII(int[] coins, int amount) {
        // order coins in order to prune recursion
        Arrays.sort(coins);

        // init memorization to -1 (unvisited)
        int[][] map = new int[amount + 1][coins.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = -1;
            }
        }

        // DFS
        return Count(coins, amount, 0, map, 0);
    }

    public int Count(int[] coins, int remaining, int index, int[][] map, int level) {
        if (remaining == 0) {
            /*
            for (int a = level; a >= 0; a--) {
                System.out.print("   ");
            }
            System.out.println("Got it");
            */
            return 1;
        }
        if (index >= coins.length) return 0;

        if (map[remaining][index] != -1) {
            /*
            for (int p = level; p >= 0; p--) {
                System.out.print("   ");
            }
            System.out.println("Return Cache:" + remaining + ":" + coins[index] + ":" + map[remaining][index]);
            */
            return map[remaining][index];
        }

        int cnt = 0;
        for (int i = index; i < coins.length; i++) {
            /*
            for (int b = level; b >= 0; b--) {
                System.out.print("   ");
            }
            System.out.println("Pick Coin " + coins[i]);
            */
            if (coins[i] > remaining) {
                /*
                for (int c = level; c >= 0; c--) {
                    System.out.print("   ");
                }
                System.out.println("End Coin " + coins[i]);
                */
                break;
            }

            // using this coin as many times as possible before going to next coin
            int times = 1;
            while (times * coins[i] <= remaining) {
                /*
                for (int d = level; d >= 0; d--) {
                    System.out.print("   ");
                }
                System.out.println("While Coin " + coins[i] + ":Get " + times + " Coins: Total " + times * coins[i]);
                for (int e = level; e >= 0; e--) {
                    System.out.print("   ");
                }
                System.out.println("Remaining :" + (remaining - times * coins[i]));
                */
                cnt += Count(coins, remaining - times * coins[i], i + 1, map, level + 1);
                times++;
                /*
                if (times * coins[i] > remaining) {
                    for (int f = level; f >= 0; f--) {
                        System.out.print("   ");
                    }
                    System.out.println("End while");
                }
                */
            }
        }

        /*
        for (int q = level; q >= 0; q--) {
            System.out.print("   ");
        }
        System.out.println("Save Cache:" + remaining + ":" + coins[index] + ":" + cnt);
        */

        // memorize
        map[remaining][index] = cnt;
        return cnt;
    }
}