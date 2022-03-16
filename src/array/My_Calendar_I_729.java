class array;
import java.util.TreeMap;
/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.

A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).

The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.

Implement the MyCalendar class:

MyCalendar() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Example 1:

Input
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
Output
[null, true, false, true]

Explanation
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.

Constraints:

0 <= start < end <= 109
At most 1000 calls will be made to book.
*/
public class My_Calendar_I_729 {
    MyCalendar myCalendar = new MyCalendar();
    myCalendar.book(10, 20); // return True
    myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
    myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
}

class MyCalendar {
    List<int[]> cal;
    public MyCalendar() {
        cal = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] cur : cal)
            if (start < cur[1] && cur[0] < end) return false;

        cal.add(new int[] { start, end });

        return true;
    }
    /*
    Complexity Analysis

    Time Complexity: O(N^2), where N is the number of events booked. For each new event, we process every previous event to decide whether the new event can be booked. This leads to \sum_k^N O(k) = O(N^2)∑
    k
    N
    ​
    O(k)=O(N
    2
    ) complexity.

    Space Complexity: O(N), the size of the calendar.
    */
}

class MyCalendarII {
    TreeMap<Integer, Integer> calendar;

    MyCalendarII() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */