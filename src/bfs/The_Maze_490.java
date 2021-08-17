package bfs;
import java.util.Queue;
import java.util.LinkedList;
/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).

The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.

When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination,

where start = [startrow, startcol] and destination = [destinationrow, destinationcol],

return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

Example 1:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.

Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false

Constraints:
* m == maze.length
* n == maze[i].length
* 1 <= m, n <= 100
* maze[i][j] is 0 or 1.
* start.length == 2
* destination.length == 2
* 0 <= startrow, destinationrow <= m
* 0 <= startcol, destinationcol <= n
* Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
* The maze contains at least 2 empty spaces.
*/

/*
Approach 1: Depth First Search
We can view the given search space in the form of a tree. The root node of the tree represents the starting position. Four different routes are possible from each position i.e. left, right, up or down. These four options can be represented by 4 branches of each node in the given tree. Thus, the new node reached from the root traversing over the branch represents the new position occupied by the ball after choosing the corresponding direction of travel.

Maze_Tree

In order to do this traversal, one of the simplest schemes is to undergo depth first search. In this case, we choose one path at a time and try to go as deep as possible into the levels of the tree before going for the next path. In order to implement this, we make use of a recursive function dfs(maze, start, desination, visited). This function takes the given mazemaze array, the startstart position and the destinationdestination position as its arguments along with a visitedvisited array. visitedvisited array is a 2-D boolean array of the same size as that of mazemaze. A True value at visited[i][j]visited[i][j] represents that the current position has already been reached earlier during the path traversal. We make use of this array so as to keep track of the same paths being repeated over and over. We mark a True at the current position in the visitedvisited array once we reach that particular positon in the mazemaze.

From every startstart position, we can move continuously in either left, right, upward or downward direction till we reach the boundary or a wall. Thus, from the startstart position, we determine all the end points which can be reached by choosing the four directions. For each of the cases, the new endpoint will now act as the new start point for the traversals. The destination, obviously remains unchanged. Thus, now we call the same function four times for the four directions, each time with a new start point obtained previously.

If any of the function call returns a True value, it means we can reach the desination.

The following animation depicts the process:

Current
6 / 10

Complexity Analysis

Time complexity : O(mn)O(mn). Complete traversal of maze will be done in the worst case. Here, mm and nn refers to the number of rows and coloumns of the maze.

Space complexity : O(mn)O(mn). visitedvisited array of size m*nm∗n is used.
*/

// https://leetcode.com/problems/the-maze/solution/

public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited))
            return true;
        return false;
    }
}

class The_Maze_490 {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while(queue.size() > 0) {
            int[] curr = queue.poll();
            if (curr[0] == destination[0] && curr[1] == destination[1]) return true;

            for(int[] dir : directions) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];

                while(x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }

                x -= dir[0];
                y -= dir[1];

                if(!visited[x][y]) {
                    queue.add(new int[] {x, y});
                    visited[x][y] = true;
                }
            }
        }

        return false;
    }
    /*
    Time complexity: O(mn). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and coloumns of the maze.

    Space complexity: O(mn). visited array of size m∗n is used and queue size can grow upto m∗n in worst case.
    */

    public static void main(String[] args) {
        The_Maze_490 obj = new The_Maze_490();

        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};

        System.out.println(obj.hasPath(maze, start, destination));
    }
}