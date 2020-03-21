package recursion;

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
    public int change(int amount, int[] coins) {
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

    public int[] Change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int x = 0; x < amount + 1; ++x) {
            for (int coin : coins) {
                if (x - coin >= 0) dp[x] += dp[x - coin];
            }
        }
        return dp;
    }

    public int changeII(int amount, int[] coins) {
        int[] approaches = new int[amount + 1];
        approaches[0] = 1;

        for (int coin : coins) {
          for (int x = coin; x < amount + 1; ++x) {
            approaches[x] += approaches[x - coin];
          }
        }
        return approaches[amount];
    }

    public static void main(String[] args) {
        int [] result = new CoinChangeTwo_518().Change(11, new int [] { 2, 5, 10 });

        for(int r: result) {
            System.out.println(r);
        }
    }
}