package bfs;

import java.util.Deque;
import java.util.ArrayDeque;
/*
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.



Example 1:


Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
Example 2:

Input: rooms = [[-1]]
Output: [[-1]]


Constraints:

m == rooms.length
n == rooms[i].length
1 <= m, n <= 250
rooms[i][j] is -1, 0, or 231 - 1.
*/
class Walls_and_Gates_286 {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;

        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (rooms[i][j] == GATE)
                    q.offer(new int[] {i, j});

        while(q.size() > 0) {
            int[] curr = q.poll();

            for (int i = 0; i < directions.length; ++i) {
                int newI = curr[0] + directions[i][0];
                int newJ = curr[1] + directions[i][1];

                if (newI < 0 || newI >= n || newJ < 0 || newJ >= m || rooms[newI][newJ] != EMPTY)
                    continue;

                rooms[newI][newJ] = rooms[curr[0]][curr[1]] + 1;
                q.offer(new int[] {newI, newJ});
            }
        }
    }

    public static void main(String[] args) {
        Walls_and_Gates_286 obj = new Walls_and_Gates_286();
    }

    /*
    Complexity analysis

    Time complexity : O(mn).

    If you are having difficulty to derive the time complexity, start simple.

    Let us start with the case with only one gate. The breadth-first search takes at most m×n steps to reach all rooms,

    therefore the time complexity is O(mn). But what if you are doing breadth-first search from kk gates?

    Once we set a room's distance, we are basically marking it as visited, which means each room is visited at most once.

    Therefore, the time complexity does not depend on the number of gates and is O(mn).

    Space complexity: O(mn). The space complexity depends on the queue's size. We insert at most m \times nm×n points into the queue.
    */
}