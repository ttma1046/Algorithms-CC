package dp;
import java.util.HashMap;
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
    public int changeBest(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coin: coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChangeTwo_518().changeBest(new int[] { 1, 2, 5 }, 5));

        System.out.println(new CoinChangeTwo_518().changeBest(new int[] { 16, 30, 9, 17, 40, 13, 42, 5, 25, 49, 7, 23, 1, 44, 4, 11, 33, 12, 27,
                           2, 38, 24, 28, 32, 14, 50
                                                                           }, 245));

        long result = new CoinChangeTwo_518().changeBest(new int [] { 2, 5, 10 }, 11);
        System.out.println(result);

        result = new CoinChangeTwo_518().changeBest(new int [] { 10 }, 10);
        System.out.println(result);
    }

    public int change(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    public int[] Change(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int x = 0; x < amount + 1; ++x) {
            for (int coin : coins) {
                if (x - coin >= 0) dp[x] += dp[x - coin];
            }
        }
        return dp;
    }

    // O(nd) time | O(n) space
    public static int numberOfWaysToMakeChange(int[] denoms, int n) {
        int [] ways = new int[n + 1];
        ways[0] = 1;
        for(int denom: denoms) {
            for(int targetAmount = 1; targetAmount < ways.length; targetAmount++) {
                if (denom <= targetAmount) {
                    ways[targetAmount] += ways[targetAmount - denom];
                }
            }
        }


        return ways[n];
    }

    public static long makeChangeLong(int[] coins, int money) {
        return makeChangeLong(coins, money, 0, new HashMap<String, Long>());
    }

    public static long makeChangeLong(int[] coins, int money, int index, HashMap<String, Long> memo) {
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
            ways += makeChangeLong(coins, remaining, index + 1, memo);
            amountWithCoin += coins[index];
        }
        memo.put(key, ways);
        return ways;
    }

    public long makeChangeII(int[] coins, int money) {
        return makeChangeII(coins, money, 0);
    }

    private long makeChangeII(int[] coins, int money, int index) {
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

    /*
    This works, but it's not as optimal as it could be.
    The issue is that we will be recursively calling makeChange several times for the same values of amount and index.
    We can resolve this issue by storing the previously computed values.
    We'll need to store a mapping from each pair (amount, index) to the precomputed result.
    */
    int makeChange(int n, int[] denoms) {
        int[][] map = new int[n + 1][denoms.length];
        // return makeChange(n, denoms, 0);
        return makeChangeII(n, denoms, 0, map);
    }

    int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length - 1) return 1; // last denom;
        int denomAmount = denoms[index];
        int ways = 0;

        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1);
        }

        return ways;
    }


    int makeChangeII(int amount, int[] denoms, int index, int[][] map) {
        if (map[amount][index] > 0) {
            // retrieve value
            return map[amount][index];
        }

        if (index >= denoms.length - 1) return 1; // one denom remaining;
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            // got to next denom, assuming i coins of denomAmount
            int amountRemaining = amount - i * denomAmount;
            ways += makeChangeII(amountRemaining, denoms, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }
    /*
    Note that we've used a two-dimensional array of integers to store the previously computed values.
    This is simpler, but takes up a little extra space.
    Alternatively, we could use an actual hash table that maps from amount to a new hash table,
    which then maps from denom to the precomputed value.
    There are other alternative data structures as well.
    */
}