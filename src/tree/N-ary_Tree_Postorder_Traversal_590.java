package tree;
import java.util.List;
import java.util.ArrayList;

/*
// Definition for a Node.
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
*/


class N - ary_Tree_Postorder_Traversal_590 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        if (root == null) return res;

        for (Node kk : root.children) postorder(kk);

        res.add(root.val);

        return res;
    }

    public List<Integer> postorderII(Node root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            for(Node node : curr.children)
                stack.push(node);
        }

        Collections.reverse(res);
        return res;
    }
}