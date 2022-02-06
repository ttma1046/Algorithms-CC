
package queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
/*
You have a RecentCounter class which counts the number of recent requests within a certain time frame.

Implement the RecentCounter class:

RecentCounter() Initializes the counter with zero recent requests.
int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.



Example 1:

Input
["RecentCounter", "ping", "ping", "ping", "ping"]
[[], [1], [100], [3001], [3002]]
Output
[null, 1, 2, 3, 3]

Explanation
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3


Constraints:

1 <= t <= 109
Each test case will call ping with strictly increasing values of t.
At most 104 calls will be made to ping.
*/

/*
class Number_of_Recent_Calls_933 {
    TreeMap<Integer, Integer> tm;

    public RecentCounter() {
        tm = new TreeMap<>();
    }

    public int ping(int t) {
        tm.put(t, 1 + tm.size());
        return tm.tailMap(t - 3000).size();
    }
}

class Number_of_Recent_Calls_933 {
    TreeSet<Integer> ts;

    public RecentCounter() {
        ts = new TreeSet<>();
    }

    public int ping(int t) {
        ts.add(t);
        return ts.tailSet(t - 3000).size();
    }
}
*/

class Number_of_Recent_Calls_933 {
    /*
    List<Integer> list;

    public Number_of_Recent_Calls_933() {
        list = new ArrayList<>();
    }

    public int ping(int t) {
        list.add(t);
        int index = Collections.binarySearch(list, t - 3000); // search the index of t - 3000.
        // System.out.println(index);
        if (index < 0) { index = ~index; } // if t - 3000 is not in list, use the index of the ceiling of t - 3000.
        return list.size() - index;
    }

    public static void main(String[] args) {
        Number_of_Recent_Calls_933 obj = new Number_of_Recent_Calls_933();

        System.out.println(obj.ping(1));
        System.out.println(obj.ping(100));
        System.out.println(obj.ping(3001));
        System.out.println(obj.ping(3002));
    }
    */

    Queue<Integer> q;

    public Number_of_Recent_Calls_933() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.offer(t);
        // System.out.println(q.peek());
        
        while (q.peek() < t - 3000) q.poll();        

        return q.size();
    }

    /*
     O(Math.min(N, 3000)).
    */
    
    public static void main(String[] args) {
        Number_of_Recent_Calls_933 obj = new Number_of_Recent_Calls_933();

       obj.ping(1);
       obj.ping(100);
       obj.ping(3001);
       obj.ping(3002);
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */