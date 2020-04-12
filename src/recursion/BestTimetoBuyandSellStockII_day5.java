package recursion;


/* this is a joke question */
class BestTimetoBuyandSellStockII_day5 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        int profit = 0;

        for(int price: prices) {
        	if (price < minPrice) {
        		minPrice = price;
        		System.out.println("minPrice" + minPrice);
        		continue;
        	}

        	if (price > maxPrice) {
        		maxPrice = price;
        		System.out.println("maxPrice" + maxPrice);
        		profit += maxPrice - minPrice;

        		maxPrice = 0;
        		minPrice = price;
        	}

        }

        return profit;
    }

        public int maxProfitII(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        int profit = 0;

        for(int price: prices) {
            if (price <= minPrice) {
                minPrice = price;
                System.out.println("minPrice" + minPrice);
                continue;
            }

            if (price > maxPrice) {
                maxPrice = price;
                System.out.println("maxPrice" + maxPrice);
                profit += maxPrice - minPrice;

                maxPrice = 0;
                minPrice = price;
            }

        }

        return profit;
    }

    public static void main(String[] args) {
    	// System.out.println(new BestTimetoBuyandSellStackII_day5().maxProfit(new int[] {7,1,5,3,6,4}));
    	// System.out.println(new BestTimetoBuyandSellStackII_day5().maxProfit(new int[] {1,2,3,4,5}));
    	// System.out.println(new BestTimetoBuyandSellStackII_day5().maxProfit(new int[] {7,6,4,3,1}));
    	System.out.println(new BestTimetoBuyandSellStockII_day5().maxProfit(new int[] {2,1,2,0,1}));
    }
}