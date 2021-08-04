package sweepline;
/*
Given an array of intervals where intervals[i] = [starti, endi],

merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/
class Merge_Intervals_56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals == null) return new int[0][];

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();

        int[] curr = intervals[0];

        for (int[] next : intervals) {
            if (cur[1] >= next[0]) cur[1] = Math.max(cur[1], next[1]);
            else {
                res.add(curr);
                curr = next;
            }
        }

        res.add(curr);
        return res.toArray(new int[0][]);
    }

    public int[][] mergeII(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;

        int length = intervals.length;
        if (length <= 1) return intervals;

        int[] starts = new int[length];
        int[] ends = new int[length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        List<int[]> result = new ArrayList<>();

        int startIndex = 0;
        int endIndex = 0;

        while (endIndex < length) {
            if (endIndex == length - 1 || starts[endIndex + 1] > ends[endIndex]) {
                result.add(new int[] { starts[startIndex], ends[endIndex] });
                startIndex = endIndex + 1;
            }
            endIndex++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Merge_Intervals_56 obj = new Merge_Intervals_56();
        int[][] intervals = new int[][] {{ 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 }};
        obj.merge(intervals);
    }
}