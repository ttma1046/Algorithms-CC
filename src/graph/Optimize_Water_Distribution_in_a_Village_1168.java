
package graph;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

/*
1168. Optimize Water Distribution in a Village

There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional.

Return the minimum total cost to supply water to all houses.

Example 1:

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation:
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

Example 2:

Input: n = 2, wells = [1,1], pipes = [[1,2,1]]
Output: 2

Constraints:

2 <= n <= 104
wells.length == n
0 <= wells[i] <= 105
1 <= pipes.length <= 104
pipes[j].length == 3
1 <= house1j, house2j <= n
0 <= costj <= 105
house1j != house2j
*/
class Optimize_Water_Distribution_in_a_Village_1168 {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++)
            graph.computeIfAbsent(0, value -> new HashMap<>()).put(i, wells[i - 1]);

        for (int i = 0; i < pipes.length; ++i) {
            int[] edge = pipes[i];
            int minFrom0To1 = graph.computeIfAbsent(edge[0], value -> new HashMap<>()).getOrDefault(edge[1], Integer.MAX_VALUE);
            int minFrom1To0 = graph.computeIfAbsent(edge[1], value -> new HashMap<>()).getOrDefault(edge[0], Integer.MAX_VALUE);

            graph.get(edge[0]).put(edge[1], Math.min(edge[2], minFrom0To1));
            graph.get(edge[1]).put(edge[0], Math.min(edge[2], minFrom1To0));
        }

        int res = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        Set<Integer> visited = new HashSet<>();

        pq.offer(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int currLoc = cur[0], distance = cur[1];

            if (!visited.add(currLoc)) continue;

            res += distance;

            for (int nei : graph.getOrDefault(currLoc, new HashMap<>()).keySet())
                if (!visited.contains(nei))
                    pq.offer(new int[] {nei, graph.get(currLoc).get(nei)});
        }

        return res;
    }

    public static void main(String[] args) {
        Optimize_Water_Distribution_in_a_Village_1168 obj = new Optimize_Water_Distribution_in_a_Village_1168();
        int n = 3;
        int[] wells = {1, 2, 2};
        int[][] pipes = {{1, 2, 1}, {2, 3, 1}};

        System.out.println(obj.minCostToSupplyWaterII(n, wells, pipes));
    }

    public int minCostToSupplyWaterII(int n, int[] wells, int[][] pipes) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Set<Integer> visited = new HashSet<>();

        int costs = 0;

        for (int i = 1; i <= n; ++i)
            map.computeIfAbsent(0, value -> new HashMap<>()).put(i, wells[i - 1]);

        for (int[] pipe : pipes) {
            int start = pipe[0], end = pipe[1], distance = pipe[2];

            int minZeroToOne = map.computeIfAbsent(start, value -> new HashMap<>()).getOrDefault(end, Integer.MAX_VALUE);
            int minOneToZero = map.computeIfAbsent(end, value -> new HashMap<>()).getOrDefault(start, Integer.MAX_VALUE);

            map.get(start).put(end, Math.min(minZeroToOne, distance));
            map.get(end).put(start, Math.min(minOneToZero, distance));
        }

        pq.offer(new int[] {0, 0});

        while(pq.size() > 0) {
            int[] curr = pq.poll();

            int end = curr[0], distance = curr[1];

            if (!visited.add(end)) continue;

            costs += distance;

            for (int neigh : map.getOrDefault(end, new HashMap<>()).keySet())
                if (!visited.contains(neigh))
                    pq.offer(new int[] {neigh, map.get(end).get(neigh)});
        }

        return visited.size() == n + 1 ? costs : -1;
    }
}