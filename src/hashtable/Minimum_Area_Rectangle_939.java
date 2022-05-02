package hashtable;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.

Example 1:
Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.
*/
class Minimum_Area_Rectangle_939 {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0]))
                map.put(p[0], new HashSet<>());
            
            map.get(p[0]).add(p[1]);
        }

        int min = Integer.MAX_VALUE;
        
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) // if have the same x or y
                    continue;

                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) // find other two points 
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        Minimum_Area_Rectangle_939 obj = new Minimum_Area_Rectangle_939();

        int[][] res = new int[][] {{1,1},{1,3},{3,1},{3,3}};

        obj.minAreaRect(res);
    }
}
