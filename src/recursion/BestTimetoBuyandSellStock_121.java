package recursion;

class BestTimetoBuyandSellStock_121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <=0) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = prices.length - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[i] > prices[j] && prices[i] - prices[j] > maxProfit) {
                    maxProfit = prices[i] - prices[j];
                }
            }
        }

        return maxProfit;
    }

    public int maxProfit(int[] prices) {
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

        int min = Integer.MAX_VALUE, 
            max = 0;
        for (int price: prices) {
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