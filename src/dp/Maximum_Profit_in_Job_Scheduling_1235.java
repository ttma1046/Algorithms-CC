package dp;
import java.util.Arrays;
/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
*/

class Maximum_Profit_in_Job_Scheduling_1235 {
    int[] memo = new int[50001];
    public int jobScheduling(int[] startTimes, int[] endTimes, int[] profits) {
        Arrays.fill(memo, -1);
        int n = profits.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; ++i)
            jobs[i] = new int[] {startTimes[i], endTimes[i], profits[i]};


        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) startTimes[i] = jobs[i][0];

        return findMax(jobs, 0, startTimes);
    }

    int findMax(int[][] jobs, int position, int[] startTimes) {
        if (position >= startTimes.length) return 0;

        if (memo[position] > -1) return memo[position];

        int nextStartTimeIndex = findNext(jobs[position][1], startTimes);

        int max = Math.max(findMax(jobs, position + 1, startTimes), jobs[position][2] + findMax(jobs, nextStartTimeIndex, startTimes));

        memo[position] = max;

        return memo[position];
    }

    int findNextStartTimeIndex(int endTime, int[] startTimes) {
        int start = 0, end = startTimes.length - 1, nextStartTimeIndex = startTimes.length;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (startTimes[mid] >= endTime) {
                nextStartTimeIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return nextStartTimeIndex;
    }

    public static void main(String[] args) {
        Maximum_Profit_in_Job_Scheduling_1235 obj = new Maximum_Profit_in_Job_Scheduling_1235();
    }
}
/*
Let N be the length of the jobs array.

Time complexity: O(NlogN)

Sorting jobs according to their starting time will take O(NlogN).

The time complexity for the recursion (with memoization) is equal to the number of times findMaxProfit is called times the average time of findMaxProfit.

The number of calls to findMaxProfit is 2*N because each non-memoized call will call findMaxProfit twice.

Each memoized call will take O(1) time while for the non-memoized call,

we will perform a binary search that takes O(log N) time, hence the time complexity will be O(NlogN + N).

The total time complexity is therefore equal to O(NlogN).

Space complexity: O(N)

Storing the starting time, ending time, and profit of each job will take 3 * N space. Hence the complexity is O(N).

The space complexity of the sorting algorithm depends on the implementation of each programming language.

For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(logN).

In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst-case space complexity of O(logN).

Thus the use of inbuilt sort() function adds O(logN) to space complexity.

The result for each position will be stored in memo and position can have the values from 0 to N,

thus the space required is O(N).

Also, stack space in recursion is equal to the maximum number of active functions.

In the scenario where every job is not scheduled, the function call stack will be of size N.

The total space complexity is therefore equal to O(N).
*/

class Maximum_Profit_in_Job_Scheduling_1235 {
    int[] memo = new int[50001];
    public int jobScheduling(int[] startTimes, int[] endTimes, int[] profits) {
        Arrays.fill(memo, -1);
        int n = profits.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; ++i)
            jobs[i] = new int[] {startTimes[i], endTimes[i], profits[i]};

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) startTimes[i] = jobs[i][0];

        return findMax(jobs, startTimes);
    }

    int findMax(int[][] jobs, int[] startTimes) {
        int length = startTimes.length;

        for (int position = length - 1; position >= 0; position--) {
            // nextIndex is the index of next non-conflicting job
            int nextStartTimeIndex = findNextStartTimeIndex(jobs[position][1], startTimes);

            // if there is a non-conflicting job possible add it's profit
            // else just consider the curent job profit
            int currProfit = nextStartTimeIndex != length ? jobs[position][2] + memo[nextStartTimeIndex] : jobs[position][2];

            // storing the maximum profit we can achieve by scheduling
            // non - conflicting jobs from index position to the end of array
            memo[position] = position == length - 1 ? currProfit : Math.max(currProfit, memo[position + 1]);
        }

        return memo[0];
    }

    int findNextStartTimeIndex(int endTime, int[] startTimes) {
        int start = 0, end = startTimes.length - 1, nextStartTimeIndex = startTimes.length;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (startTimes[mid] >= endTime) {
                nextStartTimeIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return nextStartTimeIndex;
    }

    public static void main(String[] args) {
        Maximum_Profit_in_Job_Scheduling_1235 obj = new Maximum_Profit_in_Job_Scheduling_1235();
    }
}

/*
Let NN be the length of the jobs array.

Time complexity: O(NlogN)

Sorting jobs according to their starting time will take O(NlogN) time.

We iterate over all N jobs from right to left and for each job we perform a binary search which takes O(logN), so this step also requires O(NlogN) time.

The total time complexity is therefore equal to O(N\log N)O(NlogN).

Space complexity: O(N)

Storing the start time, end time, and profit of each job takes 3 * N space. Hence the complexity is O(N).
*/

class Solution {
    private Map<Integer, Integer> dp;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        dp = new HashMap<>();
        return dfs(0, jobs);
    }

    private int dfs(int cur, int[][] jobs) {
        if (cur == jobs.length) {
            return 0;
        }

        if (dp.containsKey(cur)) {
            dp.get(cur);
        }

        int next = findNext(cur, jobs);
        int inclProf = jobs[cur][2] + (next == -1 ? 0 : dfs(next, jobs));
        int exclProf = dfs(cur + 1, jobs);

        dp.put(cur, Math.max(inclProf, exclProf));
        return Math.max(inclProf, exclProf);
    }

    int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }
}

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int[] dp = new int[jobs.length];
        dp[jobs.length - 1] = jobs[jobs.length - 1][2];
        for (int cur = jobs.length - 2; cur >= 0; cur--) {
            int next = findNext(cur, jobs);
            dp[cur] = Math.max(
                          jobs[cur][2] + (next == -1 ? 0 : dp[next]),
                          dp[cur + 1]
                      );
        }
        return dp[0];
    }

    private int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }
}

class Solution {
    class The_Comparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            return list1.get(0) - list2.get(0);
        }
    }

    private int findMaxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        // min heap having {endTime, profit}
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(new The_Comparator());

        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0), end = jobs.get(i).get(1), profit = jobs.get(i).get(2);

            // keep popping while the heap is not empty and
            // jobs are not conflicting
            // update the value of maxProfit
            while (pq.isEmpty() == false && start >= pq.peek().get(0)) {
                maxProfit = Math.max(maxProfit, pq.peek().get(1));
                pq.remove();
            }

            ArrayList<Integer> combinedJob = new ArrayList<>();
            combinedJob.add(end);
            combinedJob.add(profit + maxProfit);

            // push the job with combined profit
            // if no non-conflicting job is present maxProfit will be 0
            pq.add(combinedJob);
        }

        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while (pq.isEmpty() == false) {
            maxProfit = Math.max(maxProfit, pq.peek().get(1));
            pq.remove();
        }

        return maxProfit;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        // storing job's details into one list
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);

            jobs.add(currJob);
        }

        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        return findMaxProfit(jobs);
    }
}

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            arr[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(arr, (x, y)->x[1] - y[1]);
        int[] f = new int[n];
        for(int i = 0; i < n; i++) {
            int pre = binarySearch(arr, i);
            f[i] = Math.max(i > 0 ? f[i - 1] : 0, (pre >= 0 ? f[pre] : 0) + arr[i][2]);
        }
        return f[n - 1];
    }

    int binarySearch(int[][] arr, int i) {
        int l = 0, r = i - 1;
        while(l < r) {
            int mid = l + r + 1 >> 1;
            if(arr[mid][1] <= arr[i][0])l = mid;
            else r = mid - 1;
        }
        if(l < i && arr[l][1] <= arr[i][0])return l;
        return -1;
    }
}