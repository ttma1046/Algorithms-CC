package sweepling;

import java.util.Arrays;
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
class MeetingRooms_II_253 {
    private MinHeap myMinHeap = new MinHeap();
    public int minMeetingRooms(int [][] intervals) {
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
            if (starts[i] < ends[ends]) rooms++;
            else end++;
        }

        return rooms;
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 7, 10 }, { 2, 4 }}));

        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 0, 30 }, { 5, 15 }, { 15, 20 }}));

        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 0, 7 }, { 5, 17 }, { 15, 20 }}));
    }
}