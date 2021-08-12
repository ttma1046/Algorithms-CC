package sweepline;
import java.util.Arrays;
/*
Intuition
Imagine that, after removing all covered intervals,
all intervals must have different bounds,
After sorting, their left and right bound are increasing at the same time.

Explanation
Sort the array, and note the previous left and right bound.
For evert interval v,
if v[0] > left && v[1] > right,
there is a new uncovered interval,
so we increment ++res.

Just a tip, in Java, C++ and Python,
they sort in different ways.

Complexity
Time O(NlogN)
Space O(N)


Test Case
The test cases set only contains random useless test cases.
(In my opinion the problem maker didn't do his work.)

Also the solution judger is wrong,
input = [[1,4],[1,8],[3,6],[2,8]]
my solution output = 1
but expected = 2
(This has been fixed)

Here are some useful small test cases for debugging.
[[1,2],[1,3]]
[[1,3],[1,8],[5,8]]
[[1,6],[4,6],[4, 8]
*/

/*
Given a list of intervals, remove all intervals that are covered by another interval in the list.

Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

Example 2:

Input: intervals = [[1,4],[2,3]]
Output: 1

Example 3:

Input: intervals = [[0,10],[5,12]]
Output: 2

Example 4:

Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2

Example 5:

Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1

Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= intervals[i][0] < intervals[i][1] <= 10^5
All the intervals are unique.
*/
class Remove_Covered_Intervals_1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        int res = 0, left = -1, right = -1;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            if (interval[0] > left && interval[1] > right) {
                left = interval[0];
                ++res;
            }
            right = Math.max(right, interval[1]);
        }
        return res;
    }

    public int removeCoveredIntervalsII(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 0, cur = 0;

        for (int[] interval : intervals)
            if (cur < interval[1]) {
                cur = interval[1];
                count++;
            }

        return count;
    }

    public static void main(String[] args) {
        Remove_Covered_Intervals_1288 obj = new Remove_Covered_Intervals_1288();
        int[][] intervals = new int[][] {{1, 4}, {3, 6}, {2, 8}};
        System.out.println(obj.removeCoveredIntervalsII(intervals));
    }
}