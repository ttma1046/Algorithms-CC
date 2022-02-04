package recursion

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
*/

class BestTimetoBuyandSellStock_121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 0)
            return 0;

        int maxProfit = 0;
        for (int i = prices.length - 1; i >= 1; i--)
            for (int j = i - 1; j >= 0; j--)
                if (prices[i] > prices[j] && prices[i] - prices[j] > maxProfit)
                    maxProfit = prices[i] - prices[j];

        return maxProfit;
    }

    public int maxProfitII(int[] prices) {
        /*
        int minPrice = Integer.MAX_VALUE;

        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfilt) {
                maxProfilt = prices[i] - minPrice;
            }
        }

        return maxProfit;
        */

        int min = Integer.MAX_VALUE, max = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            max = Math.max(price - min, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new BestTimetoBuyandSellStock_121().maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(new BestTimetoBuyandSellStock_121().maxProfit(new int[] {7, 6, 4, 3, 1}));
    }
}