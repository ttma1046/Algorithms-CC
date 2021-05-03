package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*

Given the root of an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)



Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]
Example 2:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]


Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000.


Follow up: Recursive solution is trivial, could you do it iteratively?
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

class N_ary_Tree_Preorder_Traversal_589 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderI(Node root) {
        if (root == null) return res;
        res.add(root.val);

        for (Node kk : root.children) preorderI(kk);        

        return res;
    }

    public List<Integer> preorderII(Node root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return res;

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            for (int i = curr.children.size() - 1; i >= 0; --i) {
                stack.push(curr.children.get(i));
            }
        }

        return res;
    }


    public List<Integer> preorderIII(Node root) {
        Stack<Node> st = new Stack<>();
        Node node = root;
        List<Integer> res = new ArrayList<>();
        while (!st.isEmpty() || node != null) {
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                st.push(node.children.get(i));
            }
            node = (!st.isEmpty()) ? st.pop() : null;
        }
        return res;
    }

    public List<Integer> preorderIIII(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        return dfs(root, res);
    }

    List<Integer> dfs(Node root, List<Integer> result) {
        result.add(root.val);

        if (root.children != null && root.children.size() > 0) {
            for (Node kk : root.children) {
                dfs(kk, result);
            }
        }

        return result;
    }
}