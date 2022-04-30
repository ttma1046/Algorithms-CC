package stack;
import java.util.LinkedList;
import java.util.Stack;

/*
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.

Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
*/
class Asteroid_Collision_735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int i : asteroids) {
            if (i > 0)
                stack.push(i);
            else {
                while(stack.size() > 0 && stack.peek() > 0 && stack.peek() < -i)
                    stack.pop();

                if (stack.size() > 0 && stack.peek() == -i)
                    stack.pop();
                else if (stack.size() == 0 || stack.peek() < 0)
                    stack.push(i);
            }
        }

        /*
        for (int i : a) {
            if (i > 0)
                s.push(i);
            else {
                while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                    s.pollLast();
                if (!s.isEmpty() && s.getLast() == -i)
                    s.pollLast();
                else if (s.isEmpty() || s.getLast() < 0)
                    s.add(i);
            }
        }

        return s.stream().mapToInt(i->i).toArray();
        */

        int length = stack.size();

        int[] res = new int[length];

        for (int i = 0; i < length; i++)
            res[length - 1 - i] = stack.pop();

        return res;
    }

    public int[] asteroidCollisionII(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>(); // use LinkedList to simulate stack so that we don't need to reverse at end.
        for (int i : asteroids) {
            while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                s.pollLast();
            if (s.isEmpty() || i > 0 || s.getLast() < 0)
                s.add(i);
            else if (i < 0 && s.getLast() == -i)
                s.pollLast();
        }
        return s.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        Asteroid_Collision_735 obj = new Asteroid_Collision_735();

        int[] asterorids = new int[] {5, 10, -5};

        int[] res = obj.asteroidCollision(asterorids);

        for(int r: res) {
            System.out.println(r);
        }
    }
}
