package dp;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Example 4:

Input: prices = [1]
Output: 0

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/
class Best_Time_to_Buy_and_Sell_Stock_III_123 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int length = prices.length;

        int leftMin = prices[0];
        int rightMax = prices[length - 1];

        int[] leftProfits = new int[length];
        // pad the right DP array with an additional zero for convenience.
        int[] rightProfits = new int[length + 1];

        // construct the bidirectional DP array
        for (int l = 1; l < length; ++l) {
            leftProfits[l] = Math.max(leftProfits[l - 1], prices[l] - leftMin);
            leftMin = Math.min(leftMin, prices[l]);

            int r = length - 1 - l;
            rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
        }

        int maxProfit = 0;
        for (int i = 0; i < length; ++i)
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);

        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int t1Cost = Integer.MAX_VALUE;
        int t2Cost = Integer.MAX_VALUE;
        int t1Profit a= 0;
        int t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }
    /*
    Time Complexity: O(N), where N is the length of the input sequence.

    Space Complexity: O(1), only constant memory is required, which is invariant from the input sequence.
    */
}