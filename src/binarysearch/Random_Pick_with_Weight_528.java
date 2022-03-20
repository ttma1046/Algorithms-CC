package binarysearch;

/*
You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].

pickIndex() should return the integer proportional to its weight in the w array.

For example, for w = [1, 3],

the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)

while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex();

// return 0.

// Since there is only one single element on the array the only option is to return the first element.

Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.


Constraints:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
*/

// 0528-random-pick-with-weight
// Q: https://leetcode.com/problems/random-pick-with-weight/

// S1: tree map
// Rank: 25.88%
class Random_Pick_with_Weight_528 {
    private int[] prefixSums;
    private int totalSum;

    public Solution(int[] w) {
        this.prefixSums = new int[w.length];

        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    int total = 0;
    Random rand = new Random();
    TreeMap<Integer, Integer> index = new TreeMap();

    public Random_Pick_with_Weight_5281(int[] w) {
        for (int i = 0; i < w.length; i++) {
            total += w[i];
            index.put(total, i);
        }
    }

    public int pickIndex() {
        return index.higherEntry(rand.nextInt(total)).getValue();
    }

    // S2: binary search
    // Rank: 87.84%
    int[] prefix;
    Random rand = new Random();

    public Random_Pick_with_Weight_5282(int[] w) {
        prefix = w;
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1];
        }
    }

    // find the first GreatThanOrEqual
    public int pickIndex() {
        int n = prefix.length, 
        lo = 0, hi = n - 1, 
        sum = rand.nextInt(prefix[n - 1]) + 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefix[mid] < sum) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public int pickIndex1() {
        int n = prefix.length, lo = 0, hi = n - 1, sum = rand.nextInt(prefix[n - 1]) + 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (sum == prefix[mid]) {
                return mid;
            } else if (prefix[mid] < sum) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // S3:
    // Rank:

    int[] indexes = new int[100];
    int size = 0;

    public Random_Pick_with_Weight_528(int[] w) {
        int sum = 0;
        for (int i : w) {
            sum += i;
        }

        for (int i = 0; i < w.length; i++) {
            int weightInPercentage = (int) ((double) w[i] / sum * 100);
            while (weightInPercentage-- > 0) {
                indexes[size++] = i;
            }
        }

    }

    public int pickIndex() {
        if (size == 0) {
            return 0;
        }
        int rand = (int) (Math.random() * (size));
        return indexes[rand];
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */
/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */