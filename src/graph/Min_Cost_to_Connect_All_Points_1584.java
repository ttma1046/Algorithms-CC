package graph;
import java.util.Arrays;
/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:

Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18

Example 3:

Input: points = [[0,0],[1,1],[1,0],[-1,1]]
Output: 4

Example 4:

Input: points = [[-1000000,-1000000],[1000000,1000000]]
Output: 4000000

Example 5:

Input: points = [[0,0]]
Output: 0

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
*/
class Min_Cost_to_Connect_All_Points_1584 {
    // int[][] points = new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7,0}};
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; ++i)
        	for (int j = 0; j < n; ++j)
        		matrix[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

        boolean[] visited = new boolean[n];

        int[] distance = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        for (int i = 0; i < n; ++i) {
        	int nextClose = -1;
        	for (int j = 0; j < n; ++j) {
        		if (!visited[j] && (nextClose == -1 || distance[j] < distance[nextClose])) {
                    System.out.println("nextClose:" + nextClose);
                    nextClose = j;
                    System.out.println("j:" + j);
                }
            }

        	visited[nextClose] = true;

        	for (int unvisited = 0; unvisited < n; unvisited++) {
        		if (!visited[unvisited]) { 
                    // distance[y] = Math.min(distance[y], matrix[nextClose][y]); 
                    if (matrix[nextClose][unvisited] < distance[unvisited]) {
                        distance[unvisited] = matrix[nextClose][unvisited];
                    }
                }
            }
        }

        return Arrays.stream(distance).sum();
    }

    public static void main(String[] args) {
    	Min_Cost_to_Connect_All_Points_1584 obj = new Min_Cost_to_Connect_All_Points_1584();

        int[][] points = new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7,0}};

        obj.minCostConnectPoints(points);
    }
}