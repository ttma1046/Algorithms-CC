package sweepline;

import java.util.Arrays;
import java.util.Comparator;

class multiple {
    public int maxGirls(int[][] input) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int[] girl : input) {
            for (int i = girl[0]; i <= girl[1]; ++i) {
                hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
            }
        }

        int ans = 0;
        for (int i : hashMap.keySet()) {
            if (hashMap.get(i) > ans) ans = hashMap.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] girls = new int[][] {{0, 30}, {5, 10}, {15, 20}, {17, 25}};
        System.out.println(new multiple().maxGirls(girls));
    }
}

class MeetingRooms_252 {
    public boolean canAttendMettings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            for (int j = i - 1; j >= 0; j--) {
                int[] comparedOne = intervals[j];
                if (!(current[0] >= comparedOne[1] || current[1] <= comparedOne[0])) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canAttendMettingsII(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        boolean[] myArray = new boolean[1000];

        for (int[] current : intervals) {
            for (int index = current[0] + 1; index < current[1]; index++) {
                if (myArray[index] == true) {
                    return false;
                }

                myArray[index] = true;
            }
        }

        return true;
    }

    public boolean canAttendMettingsIII(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);

        for(int i = 0; i < intervals.length - 1; i++)
            if (intervals[i][1] > intervals[i + 1][0]) return false;

        return true;
    }

    /*
        Time complexity : O(nlogn).
        The time complexity is dominated by sorting.
        Once the array has been sorted,
        only O(n) time is taken to go through the array and determine if there is any overlap.

        Space complexity : O(1). Since no additional space is allocated.
    */

    public static void main(String[] args) {
        System.out.println(new MeetingRooms_252().canAttendMettingsIII(new int[][] {{ 7, 10 }, { 2, 4 }}));

        System.out.println(new MeetingRooms_252().canAttendMettingsIII(new int[][] {{ 0, 30 }, { 5, 10 }, { 15, 20 }}));
    }
}