package bfs;
/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 

The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 

When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, 

where start = [startrow, startcol] and destination = [destinationrow, destinationcol], 

return the shortest distance for the ball to stop at the destination. 

If the ball cannot stop at destination, return -1.

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).

Example 1:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: 12
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: -1
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.

Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: -1

Constraints:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
*/
import java.util.Arrays;
import java.util.PriorityQueue;

class The_Maze_II_505 {
	int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);

        distance[start[0]][start[1]] = 0;

    	dijkstra(maze, start, distance);

    	return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
    	PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
    	q.offer(new int[] {start[0], start[1], 0});

    	while(q.size() > 0) {
    		int[] curr = q.poll();
    		for (int[] dir: directions) {
    			int x = curr[0] + dir[0], y = curr[1] + dir[1], count = 0;
    			while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
    				x += dir[0];
    				y += dir[1];
    				count++;
    			}
    			x -= dir[0];
    			y -= dir[1];
    			if (distance[curr[0]][curr[1]] + count < distance[x][y]) {
    				distance[x][y] = distance[curr[0]][curr[1]] + count;

    				q.add(new int[] { x, y, distance[x][y]});
    			}
    		}
    	}
    }

    public static void main(String[] args) {
    	int[][] maze = {{0, 0}};
    	int[] start = {0, 1};
    	int[] destination = {9, 9};

    	The_Maze_II_505 obj = new The_Maze_II_505();

    	obj.shortestDistance(maze, start, destination);
    }
}