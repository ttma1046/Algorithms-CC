package dfs;

import java.util.ArrayList;
import java.util.List;

class BinaryTreePaths_257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();

        if (root != null) {

            StringBuilder sb = new StringBuilder();
            myDfs(root, result, sb);
        }

        return result;
    }

    private void myDfs(TreeNode current, List<String> result, StringBuilder currentString) {
        if (current == null)
            return;

        currentString.append(Integer.toString(current.val));

        if (current.right == null && current.left == null) {
            result.add(currentString.toString());
            return;
        }

        currentString.append("->");

        myDfs(current.left, result, currentString);
        myDfs(current.right, result, currentString);
    }
}