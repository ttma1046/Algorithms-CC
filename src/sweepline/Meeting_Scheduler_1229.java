package sweepline;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/*
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,

return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other.

That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []

Constraints:

1 <= slots1.length, slots2.length <= 104
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 109
1 <= duration <= 106
*/
class Meeting_Scheduler_1229 {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int i = 0, j = 0;
        int n = slots1.length;
        int m = slots2.length;
        while(i < n && j < m) {
            int intersectStart = Math.max(slots1[i][0], slots2[j][0]);
            int intersectEnd = Math.min(slots1[i][1], slots2[j][1]);

            if (intersectEnd - intersectStart >= duration) {
                ArrayList<Integer> res = new ArrayList<>();
                res.add(intersectStart, intersectStart + duration);
                return res;
            } else if (slots1[i][1] < slots2[j][1]) i++;
            else j++;
        }

        return new ArrayList<>();
    }

    public List<Integer> minAvailableDurationII(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int i = 0, j = 0;
        int n = slots1.length, m = slots2.length;
        while(i < n && j < m) {
            int interStart = Math.max(slots1[i][0], slots2[j][0]);
            int interEnd = Math.min(slots1[i][1], slots2[j][1]);

            if (interEnd - interStart >= duration) {
                List<Integer> res = new ArrayList<Integer>();
                res.add(interStart);
                res.add(interStart + duration);
                return res;
            } else if (slots1[i][1] < slots2[j][1]) i++;
            else j++;
        }

        return new ArrayList<Integer>();
    }


    public static void main(String[] args) {
        int[][] slots1 = new int[][] {{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = new int[][] {{0, 15}, {60, 70}};

        Meeting_Scheduler_1229 obj = new Meeting_Scheduler_1229();

        obj.minAvailableDurationII(slots1, slots2, 8);
    }
}