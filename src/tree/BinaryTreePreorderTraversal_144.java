package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<Integer>();

        preorderTraversal(root, result);

        return result;
    }

    public void preorderTraversal(TreeNode current, List<Integer> list) {
        list.add(current.val);

        if (current.left != null)
            (current.left, list);

        if (current.right != null)
            preorderTraversal(current.right, list);
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode current, List<Integer> list) {
        if (current == null)
            return;
        list.add(current.val);
        preorder(current.left, list);
        preorder(current.right, list);
    }

    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // preorder traversal : node -> left -> right top -> bottom left -> right
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }

        return result;
    }

    public List<Integer> preorderTraversalGu(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // preorder traversal : node -> left -> right top -> bottom left -> right
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                curr = curr.right;
            }
        }

        return result;
    }

    public List<Integer> preorderTraversalIII(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> output = new ArrayList<>();
        if (root == null) return output;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) 
                stack.push(node.right);
            if (node.left != null) 
                stack.push(node.left);
        }
        return output;
    }

    public List<Integer> preorderTraversalIV(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        res.add(root.val);

        for (int a : preorderTraversalIV(root.left))
            res.add(a);

        for (int a : preorderTraversalIV(root.right))
            res.add(a);

        return res;
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreePreorderTraversal_144().preorderTraversal(root);

        for (int result : results)
            System.out.println(result);

        // 这样调用
        traverse(root, 1);
    }

    // 二叉树遍历函数
    void traverse(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        // 前序位置
        printf("节点 %s 在第 %d 层", root, level);
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }

    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        // 后序位置
        printf("节点 %s 的左子树有 %d 个节点，右子树有 %d 个节点",
               root, leftCount, rightCount);

        return leftCount + rightCount + 1;
    }
}
