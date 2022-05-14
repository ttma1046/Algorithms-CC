package tree;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:

Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:

Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Constraints:

1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/
class Minimum_Height_Trees_310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 2) {
            List<Integer> res = new ArrayList<>();

            for(int i = 0; i < n; ++i)
                res.add(i);
            
            return res;
        }

        Map<Integer, Set<Integer>> neighbors = new HashMap<>();

        for (int i = 0; i < n; i++)
            neighbors.put(i, new HashSet<>());

        for (int[] edge: edges) {
            neighbors.get(edge[0]).add(edge[1]);
            neighbors.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();

        for (int i = 0; i < n; ++i)
            if (neighbors.get(i).size() == 1)
                leaves.add(i);
        
        int remaining = n;

        while(remaining > 2) {
            remaining -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();

            for (Integer leaf: leaves) {
                Integer neighbor = neighbors.get(leaf).iterator().next();
                neighbors.get(neighbor).remove(leaf);

                if (neighbors.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }

            leaves = newLeaves;
        }

        return leaves;
    }
    
    public static void main(String[] args) {
        Minimum_Height_Trees_310 obj = new Minimum_Height_Trees_310();
    }
}