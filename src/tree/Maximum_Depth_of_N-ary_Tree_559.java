package tree;

/*
Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: 3

Example 2:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5

Constraints:

The total number of nodes is in the range [0, 104].
The depth of the n-ary tree is less than or equal to 1000.
*/

/*
// Definition for a Node.
*/
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
/**/

class Maximum_Depth_of_N-ary_Tree_559 {
    public int maxDepth(Node root) {
        if (root == null)
            return 0;

        if (root.children.size() == 0)
            return 1;

        List<Integer> heights = new LinkedList<>();

        for (Node item : root.children)
            heights.add(maxDepth(item));

        return Collections.max(heights) + 1;
    }

    /*
    Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes.

    Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only one child node, the recursion call would occur NN times (the height of the tree), therefore the storage to keep the call stack would be \mathcal{O}(N)O(N). But in the best case (the tree is completely balanced), the height of the tree would be \log(N)log(N). Therefore, the space complexity in this case would be \mathcal{O}(\log(N))O(log(N)).
    */

    public int maxDepth(Node root) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();

        if (root != null)
            stack.add(new Pair(root, 1));
        
        int depth = 0;
        
        while (stack.size() > 0) {
            Pair<Node, Integer> current = stack.poll();

            Node current_node = current.getKey();
            int current_depth = current.getValue();
        
            if (root != null) {
                depth = Math.max(depth, current_depth);

                for (Node child: root.children)
                    stack.add(new Pair(child, current_depth + 1));
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        Maximum_Depth_of_N-ary_Tree_559 obj = new Maximum_Depth_of_N-ary_Tree_559();

        obj.maxDepth(root);
    }
}