package tree;
import java.util.Queue;
import java.util.LinkedList;
/*
Given a binary tree, write a function to get the maximum width of the given tree. The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.

Example 1:

Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Constraints:

The given binary tree will have between 1 and 3000 nodes.
*/

// Definition for a binary tree node.

/*
 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

 */

 /*
 Let NN be the total number of nodes in the input tree.

Time Complexity: \mathcal{O}(N)O(N)

We visit each node once and only once. And at each visit, it takes a constant time to process.
Space Complexity: \mathcal{O}(N)O(N)

We used a queue to maintain the nodes along with its indices, which is the main memory consumption of the algorithm.

Due to the nature of BFS, at any given moment, the queue holds no more than two levels of nodes. In the worst case, a level in a full binary tree contains at most half of the total nodes (i.e. \frac{N}{2} 
2
N
​ 
 ), i.e. this is also the level where the leaf nodes reside.

Hence, the overall space complexity of the algorithm is \mathcal{O}(N)O(N).
*/

class Maximum_Width_of_Binary_Tree_662 {
    // Time Complexity: O(N)
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        queue.offer(new Pair<TreeNode, Integer>(root, 0));

        int res = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> head = null;
            Pair<TreeNode, Integer> curr = null;

            int length = queue.size();

            for (int i = 0; i < length; ++i) {
                curr = queue.poll();

                if (i == 0) head = curr;

                TreeNode node = curr.getKey();
                int index = curr.getValue();

                if (node.left != null) queue.offer(new Pair<TreeNode, Integer>(node.left, 2 * index));
                if (node.right != null) queue.offer(new Pair<TreeNode, Integer>(node.right, 2 * index + 1));
            }

            if ((curr.getValue() - head.getValue() + 1) > res) res = curr.getValue() - head.getValue() + 1;
        }

        return res;
    }
    
    /*
    Let N be the total number of nodes in the input tree.

    Time Complexity: O(N).

    Similar to the BFS traversal, we visit each node once and only once in DFS traversal. And each visit takes a constant time to process as well.
    
    Space Complexity: O(N)

    Unlike the BFS traversal, we used an additional table to keep the index for the first element per level. In the worst case where the tree is extremely skewed, there could be as many levels as the number of nodes. As a result, the space complexity of the table would be O(N).

    Since we implement DFS traversal with recursion which would incur some additional memory consumption in the function call stack, we need to take this into account for the space complexity.

    The consumption of function stack is proportional to the depth of recursion. Again, in the same worst case above, where the tree is extremely skewed, the depth of the recursion would be equal to the number of nodes in the tree. Therefore, the space complexity of the function stack would be O(N).

    To sum up, the overall space complexity of the algorithm is O(N) + O(N) = O(N) + O(N) = O(N).
    */
    private Integer maxWidth = 0;
    private HashMap<Integer, Integer> firstColIndexTable;

    protected void DFS(TreeNode node, Integer depth, Integer colIndex) {
        if (node == null)
            return;
        // initialize the value, for the first seen colIndex per level
        if (!firstColIndexTable.containsKey(depth)) {
            firstColIndexTable.put(depth, colIndex);
        }
        Integer firstColIndex = firstColIndexTable.get(depth);

        maxWidth = Math.max(this.maxWidth, colIndex - firstColIndex + 1);

        // Preorder DFS. Note: it is important to put the priority on the left child
        DFS(node.left, depth + 1, 2 * colIndex);
        DFS(node.right, depth + 1, 2 * colIndex + 1);
    }

    public int widthOfBinaryTree(TreeNode root) {
        // table contains the first col_index for each level
        this.firstColIndexTable = new HashMap<Integer, Integer>();

        // start from depth = 0, and colIndex = 0
        DFS(root, 0, 0);

        return this.maxWidth;
    }

    public static void main(String[] args) {
        TreeNode five = new TreeNode(5);

        TreeNode three = new TreeNode(3);
        three.left = five;
        TreeNode two = new TreeNode(2);

        TreeNode one = new TreeNode(1);
        one.left = three;
        one.right = two;

        System.out.println(new Maximum_Width_of_Binary_Tree_662().widthOfBinaryTree(one));
    }
}

class Pair<T, V> {
    T key;
    V value;

    Pair(T x, V y) {
        this.key = x;
        this.value = y;
    }

    T getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}

