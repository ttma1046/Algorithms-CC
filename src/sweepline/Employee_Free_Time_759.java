package sweepline;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 
Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
*/

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};

class Employee_Free_Time_759 {
    public List<Interval> employeeFreeTimeII(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);

        for (List<Interval> s : schedule)
            for (Interval i : s)
                pq.add(i);

        Interval curr = pq.poll();

        while(pq.size() > 0) {
            Interval next = pq.peek();
            if (curr.end >= next.start) {
                curr.end = Math.max(curr.end, pq.poll().end);
            } else {
                res.add(new Interval(curr.end, next.start));
                curr = pq.poll();
            }
        }

        return res;
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> sortedSchedule = new ArrayList<>();

        schedule.forEach(e -> sortedSchedule.addAll(e));

        Collections.sort(sortedSchedule, (s0, s1) -> s0.start - s1.start);

        int prevEnd = sortedSchedule.get(0).end;
        List<Interval> results = new ArrayList<>();
        for(int i = 0; i < sortedSchedule.size(); i++) {
            Interval current = sortedSchedule.get(i);

            if(current.start > prevEnd) {
                results.add(new Interval(prevEnd, current.start));
            }

            prevEnd = Math.max(prevEnd, current.end);
        }

        return results;
    }

    public static void main(String[] args) {
        Employee_Free_Time_759 obj = new Employee_Free_Time_759();

        List<Interval> interval = new ArrayList<>();

        interval.add(new Interval(1, 4));

        List<List<Interval>> intervals = new ArrayList<>();
        intervals.add(interval);
        obj.employeeFreeTime(intervals);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        pq.add(8);
        pq.add(3);
        pq.add(2);
        pq.add(1);
        pq.add(5);
        pq.add(4);

        while(pq.size() > 0) {
            System.out.println(pq.poll());
        }
    }
}