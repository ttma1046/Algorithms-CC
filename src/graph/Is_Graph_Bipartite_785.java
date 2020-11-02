package graph;
import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

class Is_Graph_Bipartite_785 {
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] color = new int[n];
    Arrays.fill(color, -1);

    for (int i = 0; i < n; i++) {
      if (color[i] == -1) {
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(i);
        color[i] = 0;

        while (!myStack.isEmpty()) {
          int node = myStack.pop();

          for (int neighbour : graph[node]) {
            if (color[neighbour] == -1) {
              myStack.push(neighbour);
              color[neighbour] = color[node] ^ 1;
            } else if (color[neighbour] == color[node]) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }



  public boolean isBipartiteII(int[][] graph) {
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

  public boolean isBipartiteIII(int[][] graph) {
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
    System.out.println(new Is_Graph_Bipartite_785().isBipartite(new int[][] {{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
  }

}