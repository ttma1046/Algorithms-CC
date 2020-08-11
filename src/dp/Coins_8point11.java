package dp;

class Coins_8point11 {
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
    
    /*
    This works, but it's not as optimal as it could be. 
    The issue is that we will be recursively calling makeChange several times for the same values of amount and index.
    We can resolve this issue by storing the previously computed values. 
    We'll need to store a mapping from each pair (amount, index) to the precomputed result.
    */
    int makeChange(int n) {
        int[] denoms = { 25, 10, 5, 1 };
        int[][] map = new int[n + 1][denoms.length];
        return makeChange(n, denoms, 0, map);
    }

    int makeChange(int amount, int[] denoms, int index, int[][] map) {
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
            ways += makeChange(amountRemaining, denoms, index + 1, map);
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