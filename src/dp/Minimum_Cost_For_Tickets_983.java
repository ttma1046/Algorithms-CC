package dp;
/*
You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.



Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.


Constraints:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
*/

class Minimum_Cost_For_Tickets_983 {
    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet<>();
        for (int d : days) dayset.add(d);

        return dp(1);
    }

    public int dp(int i) {
        if (i > 365)
            return 0;

        if (memo[i] != null)
            return memo[i];

        int ans;

        if (dayset.contains(i)) {
            ans = Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]);
            ans = Math.min(ans, d(i + 30) + costs[2]);
        } else {
            ans = dp(i + 1);
        }

        memo[i] = ans;
        return ans;
    }
    /*
    Complexity Analysis:
    Time Complexity: O(W), where W = 365 is the maximum numbered day in your travel plan.
    Space Complexity: O(W).
    */

    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[] {1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];

        return dp(0);
    }

    public int dp(int i) {
        if (i >= days.length)
            return 0;

        if (memo[i] != null)
            return memo[i];

        int ans = Integer.MAX_VALUE;

        int j = i;

        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }


    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];

        Set<Integer> dayset = new HashSet<>();
        for (int d : days) dayset.add(d);

        for (int i = 1; i < 366; ++i) {
            dp[i] = dp[i - 1];
            if (dayset.contains(i)) {
                dp[i] = dp[i - 1] + costs[0];

                dp[i] = i - 7 >= 0 ? Math.min(dp[i], dp[i - 7] + costs[1]) : Math.min(dp[i], costs[1]);
                dp[i] = i - 30 >= 0 ? Math.min(dp[i], dp[i - 30] + costs[2]) : Math.min(dp[i], costs[2]);
            }
        }

        return dp[365];
    }

    /*
    Complexity Analysis

    Time Complexity: O(N), where NN is the number of unique days in your travel plan.

    Space Complexity: O(N).
    */


    TreeSet<Integer> daysSet = new TreeSet<Integer>();
    private int[] dp;

    public int mincostTickets(int[] days, int[] costs) {
        dp = new int[400];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0; i < days.length; i++) {
            daysSet.add(days[i]);
        }

        return getMinCost(days, costs, days[0]);
    }

    public int mincostTickets(int[] days, int[] costs) {
        // using queue so that the oldest ticket is at the top.
        Deque<int[]> last7days = new ArrayDeque<>(), 
        last30days = new ArrayDeque<>();

        int totalCost = 0;
        for(int i = 0; i < days.length; i++) {
            // discarding expired 7days pass
            while(!last7days.isEmpty() && last7days.peek()[0] + 7 <= days[i])
                last7days.poll();

            last7days.offer(new int[] {days[i], totalCost + costs[1]});

            // discarding expired 30 days pass.
            while(!last30days.isEmpty() && last30days.peek()[0] + 30 <= days[i])
                last30days.poll();

            last30days.offer(new int[] {days[i], totalCost + costs[2]});

            // taking the min of daily pass and current valid 7 days or 30 days pass.
            totalCost = Math.min(totalCost + costs[0], Math.min(last30days.peek()[1], last7days.peek()[1]));
        }

        return totalCost;
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[30];
        int d = 0; // d means the index of next travel day
        int lastday = days[days.length - 1];

        for (int i = days[0]; i <= lastday; i++) {
            if (i != days[d]) dp[i % 30] = dp[(i - 1) % 30]; // we don't have thid day for travel, price as yesterday
            else { // i == days[d]
                dp[i % 30] = Math.min(dp[(i - 1) % 30] + costs[0], Math.min(dp[Math.max(i - 7, 0) % 30] + costs[1], dp[Math.max(i - 30, 0) % 30] + costs[2]));
                d++;
            }
        }

        return dp[lastday % 30];
    }


    
}

// Math.min( dp[day - 1] + costs[0], dp[day - 7] + costs[1], dp[day - 30] + costs[2])