package sweepline;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]

Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]

Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105
*/
class Insert_Interval_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for (int[] curr : intervals) {
            if (curr[1] < newInterval[0]) {
                res.add(curr);
            } else if (curr[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(curr);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(curr[0], newInterval[0]);
                newInterval[1] = Math.max(curr[1], newInterval[1]);
            }
        }

        if (newInterval != null) res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }

    public int[][] insertII(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<int[]>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            result.add(intervals[i++]);
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = new int[] { // we could mutate newInterval here also
                Math.min(newInterval[0], intervals[i][0]),
                Math.max(newInterval[1], intervals[i][1])
            };
            i++;
        }

        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.length) {
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][2]);
    }

    public int[][] insertMy(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<int[]>();
        boolean flag = false;

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        int i = 0, length = intervals.length;

        while(i < length && intervals[i][1] < newStart) {
            result.add(intervals[i++]);
        }

        int[] overlapping = new int[] { newStart, newEnd };
        while(i < length && intervals[i][0] <= overlapping[1]) {
            if (!flag || (overlapping[1] < intervals[i][1])) {
                overlapping = new int[] { Math.min(intervals[i][0], overlapping[0]), Math.max(intervals[i][1], overlapping[1]) };
            }
            i++;
            flag = true;
        }

        // result.add(overlapping);
        result.add(newInterval);

        while(i < length) {
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][2]);
    }

    public int[][] insertIntervalI(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            intervals[0] = newInterval;
        }

        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int i = 0, length = intervals.length;

        LinkedList<int[]> result = new LinkedList<int[]>();

        while (intervals[i][0] < newStart) {
            result.add(intervals[i++]);
        }

        int[] interval = new int[2];

        if (result.isEmpty() || result.getLast()[1] < newStart) {
            result.add(newInterval);
        } else {
            interval = result.removeLast();               // [ ]             [            ] [             ]
            interval[1] = Math.max(interval[1], newEnd);  //  [newInterval]   [newInterval]  [newInterval]
            result.add(interval);
        }

        while (i < length) {
            interval = intervals[i++];
            int start = interval[0], end = interval[1];

            if (start > result.getLast()[1]) {
                result.add(interval);
            } else {
                interval = result.removeLast();              //           [   ]          [ ]  [   ]
                interval[1] = Math.max(interval[1], end);    // [newinterval]  [newinterval]  [newinterval]
                result.add(interval);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[0][2];
        int[] newInterval = new int[] {5, 7};

        int[][] result = new Insert_Interval_57().insertMy(intervals, newInterval);

        for (int[] item : result) {
            for (int a : item) {
                System.out.println(a);
            }
        }

        intervals = new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        newInterval = new int[] {4, 8};

        result = new Insert_Interval_57().insert(intervals, newInterval);

        for (int[] item : result) {
            for (int a : item) {
                System.out.println(a);
            }
        }

        intervals = new int[][] {{1, 3}, {6, 9}};
        newInterval = new int[] {2, 5};

        result = new Insert_Interval_57().insert(intervals, newInterval);

        for (int[] item : result) {
            for (int a : item) {
                System.out.println(a);
            }
        }
    }
}