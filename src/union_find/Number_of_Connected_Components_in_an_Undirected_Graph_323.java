package union_find;

class Number_of_Connected_Components_in_an_Undirected_Graph_323 {
    public int countComponentsII(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) roots[i] = i;

        for (int[] e : edges) {
            int root1 = find(roots, e[0]);
            System.out.println("root1:" + root1);
            int root2 = find(roots, e[1]);
            System.out.println("root2:" + root2);
            if (root1 != root2) {
                roots[root2] = root1;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        int oldId = id;
        while (roots[id] != id) {
            id = roots[id];
        }

        roots[oldId] = id;
        return id;
    }

    public static void main(String[] args) {
        System.out.println(new Number_of_Connected_Components_in_an_Undirected_Graph_323().countComponentsII(5, new int[][] {{0, 1}, {1, 2}, {3, 4}}));

        System.out.println(new Number_of_Connected_Components_in_an_Undirected_Graph_323().countComponentsII(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}}));

        System.out.println(new Number_of_Connected_Components_in_an_Undirected_Graph_323().countComponentsII(5, new int[][] {{0, 1}, {1, 2}, {0, 2}, {3, 4}}));
    }
}

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

