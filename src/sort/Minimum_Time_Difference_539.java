
package sort;
/*
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.

Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:

2 <= timePoints <= 2 * 104
timePoints[i] is in the format "HH:MM".
*/
class Minimum_Time_Difference_539 {
    public int findMinDifference(List<String> timePoints) {
        boolean[] present = new boolean[24 * 60];

        for (String time : timePoints) {
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));

            if (present[t]) return 0;

            present[t] = true;
        }

        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < present.length; ++i) {
            if (present[i]) {
                if (last != -1) min = Math.min(min, i - last);
                if (first == -1) first = i;
                last = i;
            }
        }

        return Math.min(min, present.length - last + first);
    }

    public static void main(String[] args) {
        Minimum_Time_Difference_539 obj = new Minimum_Time_Difference_539();

        List<String> timePoints = new ArrayList<>();

        timePoints.add("00:00");
        timePoints.add("23:59");
        timePoints.add("00:00");

        obj.findMinDifference(timePoints);
    }
}