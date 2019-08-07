package dp;

import java.util.Arrays;

public class MinNumberOfCoinsforChange {
    // O(nd) time | O(n) space
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        int [] numOfCoins = new int[n + 1];
        Arrays.fill(numOfCoins, Integer.MAX_VALUE);
        numOfCoins[0] = 0;

        for(int denom: denoms) {
            for(int amount = 0; amount < numOfCoins.length; amount++) {
                if (denom <= amount) {

                    if (numOfCoins[amount - denom] == Integer.MAX_VALUE) {
                        numOfCoins[amount] = Math.min(numOfCoins[amount], numOfCoins[amount - denom]);
                    } else {
                        numOfCoins[amount] = Math.min(numOfCoins[amount], numOfCoins[amount - denom] + 1);
                    }
                }
            }
        }

        return numOfCoins[n] > 0 && numOfCoins[n] < Integer.MAX_VALUE ? numOfCoins[n] : -1;
    }
}
