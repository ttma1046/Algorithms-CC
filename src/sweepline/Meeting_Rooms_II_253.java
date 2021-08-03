package sweepline;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import heap.MinHeap;
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),

find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

NOTE: input types have been changed on April 15, 2019.
Please reset to default code definition to get new method signature.
*/
/*
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
*/

class Point {
    int key;
    int value;
    Point(int k, int v) {
        this.key = k;
        this.value = v;
    }
}
class Meeting_Rooms_II_253 {
    public int minMeetingRoomsMyII(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        List<int[]> points = new ArrayList<>();

        for (int[] interval: intervals) {
            points.add(new int[] { interval[0], 1 });
            points.add(new int[] { interval[1], -1 });
        }

        Collections.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int max = 0;
        int cnt = 0;
        for (int[] point : points) {
            cnt += point[1];
            max = Math.max(cnt, max);
        }
        return max;
    }

    public int minMeetingRoomsMy(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            points.add(new Point(intervals[i][0], 1));
            points.add(new Point(intervals[i][1], -1));
        }

        Collections.sort(points, (a, b) -> a.key == b.key ? a.value - b.value : a.key - b.key);

        int max = 0;
        int cnt = 0;
        for (Point point : points) {
            cnt += point.value;
            max = Math.max(cnt, max);
        }

        return max;
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0 ; i < intervals.length; ++i) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int room = 0;
        int endIndex = 0;
        for (int i = 0; i < starts.length; ++i) {
            if (starts[i] < ends[endIndex]) room++;
            else endIndex++;
        }
        return room;
    }

    // O(nlgn)

    private MinHeap myMinHeap = new MinHeap();
    public int minMeetingRoomsI(int [][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int result = 1;
        myMinHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (myMinHeap.peek() > intervals[i][0]) {
                result++;
            } else {
                myMinHeap.poll();
            }
            myMinHeap.add(intervals[i][1]);
        }

        return result;
    }

    public int minMeetingRoomsIII(int[][] intervals) {
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(
            intervals.length,
        new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        }
        );

        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(final int[] a, final int[] b) {
                return a[0] - b[0];
            }
        });

        // Add the first meeting
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

    public int minMeetingRoomsII(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int end = 0;

        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[end]) rooms++;
            else end++;
        }

        return rooms;
    }

    public static void main(String[] args) {
        System.out.println(new Meeting_Rooms_II_253().minMeetingRoomsMyII(new int[][] {{ 7, 10 }, { 2, 4 }}));

        System.out.println(new Meeting_Rooms_II_253().minMeetingRoomsMyII(new int[][] {{ 0, 30 }, { 5, 15 }, { 15, 20 }}));

        System.out.println(new Meeting_Rooms_II_253().minMeetingRoomsMyII(new int[][] {{ 0, 7 }, { 5, 17 }, { 15, 20 }}));
    }
}