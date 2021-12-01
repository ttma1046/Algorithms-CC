package graph;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.

Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,

The cost is the sum of the connections' costs used.

Example 1:

Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:

Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.

Constraints:

1 <= n <= 104
1 <= connections.length <= 104
connections[i].length == 3
1 <= xi, yi <= n
xi != yi
0 <= costi <= 105
*/
class Connecting_Cities_With_Minimum_Cost_1135 {
    public int minimumCost(int n, int[][] connections) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int costs = 0;

        for (int i = 0; i < connections.length; ++i) {
            int[] conn = connections[i];
            int start = conn[0], end = conn[1], cost = conn[2];

            map.computeIfAbsent(start, (k) -> new ArrayList<>()).add(new int[] {end, cost});
            map.computeIfAbsent(end, (k) -> new ArrayList<>()).add(new int[] {start, cost});
        }

        pq.offer(new int [] { 1, 0 });

        while(pq.size() > 0) {
            int[] curr = pq.poll();

            int end = curr[0], cost = curr[1];

            if (!visited.add(end)) continue;
            costs += cost;

            for (int[] neigh : map.get(end))
                if (!visited.contains(neigh[0]))
                    pq.offer(new int[] { neigh[0], neigh[1] });
        }

        return visited.size() == n ? costs : -1;
    }

    /*
    int[] root;
    public int minimumCost(int n, int[][] con) {
        int res = 0, cnt = n;
        root = new int[n + 1];

        Arrays.sort(con, (int[] a, int[] b) -> (a[2] - b[2]));
        for (int i = 0; i < n; ++i) root[i] = i;
        for (int[] c : con) {
            int a = find(c[0]);
            int b = find(c[1]);
            if (a != b) {
                --cnt;
                res += c[2];
                if (a > b) root[b] = a;
                else root[a] = b;
            }
        }
        return cnt == 1 ? res : -1;
    }

    int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }
    */

    public static void main(String[] args) {
        Connecting_Cities_With_Minimum_Cost_1135 obj = new Connecting_Cities_With_Minimum_Cost_1135();

        int[][] connections = new int[][] {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        int n = 3;

        System.out.println(obj.minimumCost(connections.length, connections));
    }
    /*
    // Prim's algorithm
    public int minimumCostII(int n, int[][] connections) {
        List<Vertex>[] graph = new ArrayList<Vertex>[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] connection : connections) {
            int source = connection[0];
            int destination = connection[1];
            int cost = connection[2];
            graph[source].add(new Vertex(destination, cost));
            graph[destination].add(new Vertex(source, cost));
        }

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(1, 0));
        int numOfVertices = 0;
        int minCost = 0;
        while (!pq.isEmpty() && numOfVertices < n) {
            Vertex current = pq.poll();
            int node = current.node;
            int cost = current.cost;
            if (!visited[node]) {
                minCost += cost;
                numOfVertices++;
                visited[node] = true;

                for (Vertex neighbor : graph[node])
                    if (!visited[neighbor.node])
                        pq.offer(neighbor);
            }
        }

        return numOfVertices == n ? minCost : -1;
    }
    */
}

class Vertex implements Comparable<Vertex> {
    int node;
    int cost;

    public Vertex(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex vertex) {
        return cost - vertex.cost;
    }
}