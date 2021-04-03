package graph;
import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private int[] colors;
    private boolean[] visited;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int n = graph.length;
        colors = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;
            if (!differentSet(i)) return false;
        }

        return true;
    }

    private boolean differentSet(int v) {
        visited[v] = true;

        if (colors[v] == 0) colors[v] = 1;

        for (int adj : graph[v]) {
            if (colors[adj] != 0 && colors[adj] == colors[v])
                return false;
            if (colors[adj] == 0)
                colors[adj] = -1 * colors[v];
        }

        for (int adj : graph[v]) {
            if (!visited[adj] && !differentSet(adj)) return false;
        }

        return true;
    }
}

class Is_Graph_Bipartite_785 {
  public boolean isBipartite(int[][] graph) {
    if (graph == null)
      return false;

    Set<Integer> resultA = new HashSet<Integer>();
    Set<Integer> resultB = new HashSet<Integer>();

    resultA.add(0);

    Queue<int[]> myQueue = new LinkedList<int[]>();
    myQueue.add(graph[0]);
    int[] edge;

    while (!myQueue.isEmpty()) {
      edge = myQueue.poll();

      for (int i : edge) {
        if (resultA.contains(i))
          return false;

        if (resultB.contains(i)) {
          resultB.add(i);
        }

        myQueue.add(graph[i]);

        Set<Integer> temp = resultA;
        resultA = resultB;
        resultB = temp;
      }
    }

    return true;
  }

  public boolean isBipartiteII(int[][] graph) {
    int n = graph.length;
    int[] color = new int[n];
    Arrays.fill(color, -1);

    for (int start = 0; start < n; ++start) {
      if (color[start] == -1) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        color[start] = 0;

        while (!stack.empty()) {
          Integer node = stack.pop();
          for (int nei : graph[node]) {
            if (color[nei] == -1) {
              stack.push(nei);
              System.out.println("color[node]:" + color[node]);
              color[nei] = color[node] ^ 1; // XOR
              System.out.println("color[nei]:" + color[nei]);
            } else if (color[nei] == color[node]) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  public boolean isBipartiteIII(int[][] graph) {
    int len = graph.length;
    int[] colors = new int[len];

    for (int i = 0; i < len; i++) {
      if (colors[i] != 0)
        continue;
      Queue<Integer> queue = new LinkedList<>();
      queue.offer(i);
      colors[i] = 1; // Blue: 1; Red: -1.

      while (!queue.isEmpty()) {
        int cur = queue.poll();
        for (int next : graph[cur]) {
          if (colors[next] == 0) { // If this node hasn't been colored;
            colors[next] = -colors[cur]; // Color it with a different color;
            queue.offer(next);
          } else if (colors[next] != -colors[cur]) { // If it is colored and its color is different, return
            // false;
            return false;
          }
        }
      }
    }

    return true;
  }

  public boolean isBipartiteIV(int[][] graph) {
    // BFS
    // 0(not meet), 1(black), 2(white)
    int[] visited = new int[graph.length];

    for (int i = 0; i < graph.length; i++) {
      if (graph[i].length != 0 && visited[i] == 0) {
        visited[i] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
          int current = q.poll();
          for (int c : graph[current]) {

            if (visited[c] == 0) {
              visited[c] = (visited[current] == 1) ? 2 : 1;
              q.offer(c);
            } else {
              if (visited[c] == visited[current])
                return false;
            }
          }
        }

      }
    }

    return true;
  }

  public boolean isBipartiteV(int[][] g) {
    int[] colors = new int[g.length];
    for (int i = 0; i < g.length; i++)
      if (colors[i] == 0) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        colors[i] = 1;
        while (!q.isEmpty()) {
          Integer node = q.poll();
          for (int adjacent : g[node])
            if (colors[adjacent] == colors[node])
              return false;
            else if (colors[adjacent] == 0) {
              q.add(adjacent);
              colors[adjacent] = -colors[node];
            }
        }
      }
    return true;
  }

  public boolean isBipartiteVII(int[][] graph) {
    int n = graph.length;
    int[] colors = new int[n];

    for (int i = 0; i < n; i++) {              //This graph might be a disconnected graph. So check each unvisited node.
      if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
        return false;
      }
    }
    return true;
  }

  public boolean validColor(int[][] graph, int[] colors, int color, int node) {
    if (colors[node] != 0) {
      return colors[node] == color;
    }
    colors[node] = color;
    for (int next : graph[node]) {
      if (!validColor(graph, colors, -color, next)) {
        return false;
      }
    }
    return true;
  }

  public boolean isBipartiteVIII(int[][] graph) {
    int len = graph.length;
    int[] colors = new int[len];

    for (int i = 0; i < len; i++) {
      if (colors[i] != 0) continue;
      Queue<Integer> queue = new LinkedList<>();
      queue.offer(i);
      colors[i] = 1;   // Blue: 1; Red: -1.

      while (!queue.isEmpty()) {
        int cur = queue.poll();
        for (int next : graph[cur]) {
          if (colors[next] == 0) {          // If this node hasn't been colored;
            colors[next] = -colors[cur];  // Color it with a different color;
            queue.offer(next);
          } else if (colors[next] != -colors[cur]) {   // If it is colored and its color is different, return false;
            return false;
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(new Is_Graph_Bipartite_785().isBipartiteII(new int[][] {{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
  }
}