package bfs;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/*
There are a total of numCourses  courses you have to take labelled from 0 to n - 1.

You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.

Given the total number of courses numCourses and a list of the prerequisite pairs,

return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them.

If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
*/
class Course_Schedule_II_210 {
    public static void main(String[] args) {
        int[] result = new Course_Schedule_II_210().findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}});

        for(int i : result) {
            System.out.println(i);
        }
    }

    /*
    map outdegree

    0:1,2
    1:3
    2:3

    array indegree

    0:0
    1:1
    2:1
    3:2
    */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] prerequisiteMap = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            prerequisiteMap[i] = new ArrayList<Integer>();
        }

        for (int[] prerequisite : prerequisites) {
            prerequisiteMap[prerequisite[1]].add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        int[] result = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; ++i) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        while(queue.size() > 0) {
            int course = queue.poll();
            result[index++] = course;

            for (int nextCourse : prerequisiteMap[course]) {
                if (--indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        if (index == numCourses) return result;
        return new int[0];
    }
    /* 
        edge 边 vertice 顶点
        Time Complexity: O(V + E) where V represents the number of vertices and E represents the number of edges. 
        We pop each node exactly once from the zero in-degree queue and that gives us V. 
        Also, for each vertex, we iterate over its adjacency list and in totality, we iterate over all the edges in the graph which gives us EE. Hence, O(V + E)

        Space Complexity: O(V + E). 
        We use an intermediate queue data structure to keep all the nodes with 0 in-degree. 
        In the worst case, there won't be any prerequisite relationship and the queue will contain all the vertices initially since all of them will have 0 in-degree. 
        That gives us O(V). Additionally, we also use the adjacency list to represent our graph initially. 
        The space occupied is defined by the number of edges because for each node as the key, we have all its adjacent nodes in the form of a list as the value. Hence, O(E)O(E). So, the overall space complexity is O(V + E)O(V+E).
    */

    /*
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] map  = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

        int[] result = new int[numCourses];
        int count = 0;

        for (int i = 0; i < numCourses; i++) {
            map[i] = new ArrayList<Integer>();
        }

        for (int[] prerequisite : prerequisites) {
            map[prerequisite[1]].add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> myQueue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                myQueue.offer(i);
            }
        }

        while (!myQueue.isEmpty()) {
            int index = myQueue.poll();

            result[count++] = index;

            ArrayList<Integer> k = map[index];

            for (int j : k) {
                if (--indegree[j] == 0) {
                    myQueue.offer(j);

                }
            }
        }

        if (count == numCourses) return result;
        return new int[0];
    }
    */


    /*
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses], res = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                res[index++] = i;
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : graph[cur]) {
                if (--degree[nei] == 0) {
                    q.offer(nei);
                    res[index++] = nei;
                }
            }
        }
        return index == numCourses ? res : new int[0];
    }

    public int[] findOrderBFSII(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];


        int[] result = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.poll();
            result[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
            return result;
        }

        return new int[0];
    }
    */

    /*
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[0]].add(pre[1]);
        }
        Stack<Integer> postOrder = new Stack<>();
        int[] v = new int[numCourses];
        Arrays.fill(v, 0);

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, v, graph, postOrder)) {
                return new int[0];
            }
        }

        int[] orders = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--) {
            orders[i] = postOrder.pop();
        }

        return orders;
    }

    private boolean dfs(int curNode, int[] v, List<Integer>[] graph, Stack<Integer> postOrder) {
        if (v[curNode] == 1) return true;
        if (v[curNode] == 2) return false;
        v[curNode] = 1;
        for (var t : graph[curNode]) {
            if (dfs(t, v, graph, postOrder)) return true;
        }
        v[curNode] = 2;
        postOrder.push(curNode);

        return false;
    }

    public int[] findOrderDFSII(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        initialiseGraph(incLinkCounts, adjs, prerequisites);
        return solveByBFS(incLinkCounts, adjs);
    }

    private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites) {
        int n = incLinkCounts.length;
        while (n-- > 0) adjs.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            incLinkCounts[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }

    private int[] solveByDFS(List<List<Integer>> adjs) {
        BitSet hasCycle = new BitSet(1);
        BitSet visited = new BitSet(adjs.size());
        BitSet onStack = new BitSet(adjs.size());
        Deque<Integer> order = new ArrayDeque<>();
        for (int i = adjs.size() - 1; i >= 0; i--) {
            if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false) return new int[0];
        }
        int[] orderArray = new int[adjs.size()];
        for (int i = 0; !order.isEmpty(); i++) orderArray[i] = order.pop();
        return orderArray;
    }

    private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
        visited.set(from);
        onStack.set(from);
        for (int to : adjs.get(from)) {
            if (visited.get(to) == false) {
                if (hasOrder(to, adjs, visited, onStack, order) == false) return false;
            } else if (onStack.get(to) == true) {
                return false;
            }
        }
        onStack.clear(from);
        order.push(from);
        return true;
    }
    */

    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] visited;
    boolean isValid = true;
    int[] res;
    int index;
    public int[] findOrder(int numCourses, int[][] edges) {
        int N = numCourses, res = new int[N], visited = new int[N];
        for (int[] edge: edges)
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
        for (int i = 0; i < N; i++)
            if (visited[i] == 0) dfs(i);
        return isValid ? res : new int[0];
    }

    public void dfs(int node) {
        visited[node] = 1;
        for (int nei: graph.getOrDefault(node, new ArrayList<>()))
            if (visited[nei] == 0) dfs(nei);
            else if (visited[nei] == 1) isValid = false;

        res[index++] = node;
        visited[node] = 2;
    }
}

