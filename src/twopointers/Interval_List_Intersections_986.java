package twopointers;
import java.util.List;
import java.util.ArrayList;
/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
*/

/*
You are given two lists of closed intervals,

firstList and secondList,

where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].

Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

Example 1:

Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []

Example 3:

Input: firstList = [], secondList = [[4,8],[10,12]]
Output: []

Example 4:

Input: firstList = [[1,7]], secondList = [[3,10]]
Output: [[3,7]]

Constraints:

0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 109
endi < starti+1
0 <= startj < endj <= 109
endj < startj+1
*/

class Interval_List_Intersections_986 {
    public static void main(String[] args) {
        int[][] result = new Interval_List_Intersections_986().intervalIntersection(
        new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}},
        new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}}
        );

        for(int[] item : result)
            for(int i : item)
                System.out.println(i);
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();

        int i = 0,
            j = 0;

        while(i < firstList.length && j < secondList.length) {
            int interStart = Math.max(firstList[i][0], secondList[j][0]);
            int interEnd = Math.min(firstList[i][1], secondList[j][1]);

            if (interStart <= interEnd)
                res.add(new int[] {interStart, interEnd});

            if (firstList[i][1] < secondList[j][1])
                i++;
            else
                j++;
        }

        // return res.toArray(new int[res.size()][]);
        int[][] result = new int[res.size()][2];

        int index = 0;
        for (int[] p : res)
            result[index++] = p;

        return result;
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0,
            j = 0;

        List<int[]> res = new ArrayList<>();

        while(i < firstList.length && j < secondList.length) {
            int low = Math.max(firstList[i][0], secondList[j][0]);
            int high = Math.min(firstList[i][1], secondList[j][1]);

            if (low <= high)
                res.add(new int[] { low, high });

            if (firstList[i][1] < secondList[j][1])
                i++;
            else
                j++;
        }

        int[][] result = new int[res.size()][2];

        int index = 0;
        for (int[] p : res)
            result[index++] = p;

        return result;
    }
}