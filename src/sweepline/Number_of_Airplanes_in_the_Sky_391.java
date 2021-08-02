package sweepline;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
/**
 * Definition of Interval:
 */
class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int key, value;

    Point(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

public class Number_of_Airplanes_in_the_Sky_391 {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        int max = 0;

        List<Point> list = new ArrayList<>();

        for (Interval item : airplanes) {
            list.add(new Point(item.start, 1));
            list.add(new Point(item.end, -1));
        }

        Collections.sort(list, (a, b) -> a.key == b.key ? a.value - b.value : a.key - b.key);

        int temp = 0;

        for (Point item : list) {
            temp += item.value;
            max = Math.max(temp, max);
        }

        return max;
    }

    public static void main(String[] args) {
        Number_of_Airplanes_in_the_Sky_391 obj = new Number_of_Airplanes_in_the_Sky_391();

        List<Interval> airplanes = new ArrayList<>();

        airplanes.add(new Interval(1, 10));
        airplanes.add(new Interval(2, 3));
        airplanes.add(new Interval(5, 8));
        airplanes.add(new Interval(4, 7));

        System.out.println(obj.countOfAirplanes(airplanes));
    }
}