package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DeleteTreeNodes_1273 {
    /*
    A tree rooted at node 0 is given as follows:

    The number of nodes is nodes;
    The value of the i-th node is value[i];
    The parent of the i-th node is parent[i].
    Remove every subtree whose sum of values of nodes is zero.

    After doing so, return the number of nodes remaining in the tree.

    Example 1:

    Input:
    nodes = 7,
    parent = [-1,0,0,1,2,2,2],
    value = [1,-2,4,0,-2,-1,-1]

    Output: 2


    Constraints:

            1 <= nodes <= 10^4
            -10^5 <= value[i] <= 10^5
            parent.length == nodes
            parent[0] == -1 which indicates that 0 is the root.
    */

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        if (nodes <= 0 || parent == null || value == null) {
            return Integer.MIN_VALUE;
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(nodes);
        for (int i = 0; i < nodes; i++) { graph.add(new ArrayList<Integer>()); };

        for (int i = 0; i < nodes; i++) {
            if (parent[i] != -1) {
                graph.get(parent[i]).add(i);
            }
        }

        return dfs(graph, value, 0) [1];
    }

    private int[] dfs(ArrayList<ArrayList<Integer>> graph, int[] value, int root) {
        int sum = value[root];
        int currentLeftNodes = 1;
        for (int child: graph.get(root)) {
            int[] temp = dfs(graph, value, child);
            sum += temp[0];
            currentLeftNodes += temp[1];
        }

        if (sum == 0) currentLeftNodes = 0;
        return new int[] {sum, currentLeftNodes};
    }

    public int deleteTreeNodesII(int nodes, int[] parent, int[] value) {
        int[] res = new int[nodes];
        for (int i = nodes - 1; i > 0; --i) {
            value[parent[i]] += value[i];
            res[parent[i]] += value[i] != 0 ? res[i] + 1 : 0;
        }
        return value[0] != 0 ? res[0] + 1 : 0;
    }


    public int deleteTreeNodesIII(int nodes, int[] parent, int[] value) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(nodes);
        for (int i = nodes; i > 0; i--) { graph.add(new ArrayList<Integer>()); }

        for (int i = 0; i < nodes; i++) {
            if (parent[i] != -1) {
                graph.get(parent[i]).add(i);
            }
        }

        return myDFS(graph, value, 0)[1];
    }

    private int[] myDFS(ArrayList<ArrayList<Integer>> graph, int[] value, int root) {
        int result = value[root];
        int remainingNodeCount = 1;

        if (graph.get(root).size() > 0) {
            for(int child: graph.get(root)) {
                int[] childResult = myDFS(graph, value, child);
                result += childResult[0];
                remainingNodeCount += childResult[1];
            }
        }

        if (result == 0) {
            remainingNodeCount = 0;
        }

        return new int[] { result, remainingNodeCount };
    }

    public static void main(String[] args) {
        System.out.println(new DeleteTreeNodes_1273().deleteTreeNodes(7, new int[] { -1, 0, 0, 1, 2, 2, 2 }, new int[] {1, -2, 4, 0, -2, -1, -1}));
    }
}
