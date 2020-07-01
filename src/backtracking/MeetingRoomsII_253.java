package backtracking;
import java.util.Arrays;
import java.util.Comparator;
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
class MeetingRoomsII_253 {
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
        for(int i = 1; i < intervals.length; i++) {
            if (myMinHeap.peek() > intervals[i][0]) {
                result++;
            } else {
                myMinHeap.poll();
                myMinHeap.add(intervals[i][1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 7, 10 }, { 2, 4 }}));

        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 0, 30 }, { 5, 17 }, { 15, 20 }}));

        System.out.println(new MeetingRoomsII_253().minMeetingRooms(new int[][] {{ 0, 7 }, { 5, 17 }, { 15, 20 }}));
    }
}