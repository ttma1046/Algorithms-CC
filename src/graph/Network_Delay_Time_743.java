package graph;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
*/

class Network_Delay_Time_743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            // time[0] , time[1], time[2]
            graph.putIfAbsent(time[0], new ArrayList<int[]>());
            graph.get(time[0]).add(new int[] {time[1], time[2]});
        }

        boolean[] visited = new boolean[n + 1];

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; ++i)
            dist.put(i, Integer.MAX_VALUE);

        dist.put(k, 0);

        while (true) {
            int currNode = -1;
            int currDist = Integer.MAX_VALUE;

            // find the node with the shortest distance among all unvistied ones.
            for (int i = 1; i <= n; ++i) {
                if (!visited[i] && dist.get(i) < currDist)  {
                    currNode = i;
                    currDist = dist.get(i);
                }
            }

            if (currNode <= 0) break;
            visited[currNode] = true;

            if (graph.containsKey(currNode))
                for (int[] edge : graph.get(currNode))
                    dist.put(edge[0], Math.min(dist.get(edge[0]), dist.get(currNode) + edge[1]));
        }

        int ans = 0;

        for (int i : dist.values()) {
            if (i == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, i);
        }

        return ans;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int [] time = new int[n];
        Arrays.fill(time, n * 100 + 1);
        time[k - 1] = 0;
        
        int[][] matrix = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], -1);
        }
            
        
        for (int i = 0; i < times.length; i++) {
            matrix[times[i][0] - 1][times[i][1] - 1] = times[i][2];
        }
            
        
        int processed = 0;
        boolean [] used = new boolean[n];
        
        while (processed < n)
        {
            int i = 0;
            while (i < n && used[i]) ++i;
            
            int v = i;
            for (; i < n; ++i)
                if (!used[i] && time[i] < time[v])
                    v = i;
            
            used[v] = true;
            processed++;
            
            for (i = 0; i < n; ++i)
                if (matrix[v][i] >= 0 && matrix[v][i] + time[v] < time[i])
                    time[i] = matrix[v][i] + time[v];
        }
        
        int t = 0;
        for (int i = 0; i < n; ++i)
        {
            if (time[i] == n*100 + 1) return -1;
            
            t = Math.max(t, time[i]);
        }
        
        return t;
    }

    public static void main(String[] args) {
        System.out.println(new Network_Delay_Time_743().networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }
}
