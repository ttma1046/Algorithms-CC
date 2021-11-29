package graph;
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
        Map<Integer, List<int[]>> graph = new HashMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        Set<Integer> visited = new HashSet<>();

        int costs = 0;

        for (int[] edge : connections) {
            int start = edge[0], end = edge[1], cost = edge[2];

            graph.computeIfAbsent(start, (k) -> new ArrayList<>()).add(new int[] {end, cost});
            graph.computeIfAbsent(end, (k) -> new ArrayList<>()).add(new int[] {start, cost});
        }

        pq.add(new int[] {1, 1, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int start = cur[0], end = cur[1], cost = cur[2];

            if (visited.add(end)) {
                costs += cost;

                for (int[] neighbor : graph.get(end))
                    pq.add(new int[] {end, neighbor[0], neighbor[1]});
            }
        }

        return visited.size() == n ? costs : -1;
    }

    
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

    public static void main(String[] args) {
        Connecting_Cities_With_Minimum_Cost_1135 obj = new Connecting_Cities_With_Minimum_Cost_1135();
    }
}