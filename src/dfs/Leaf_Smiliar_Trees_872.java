package dfs;

import java.util.ArrayList;
import java.util.List;

public class Leaf_Smiliar_Trees_872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }

        List<Integer> leafValueOne = new ArrayList<Integer>();
        List<Integer> leafValueTwo = new ArrayList<Integer>();

        generateLeafValuesBFS(root1, leafValueOne);
        generateLeafValuesBFS(root2, leafValueTwo);

        if (leafValueOne.size() != leafValueTwo.size()) {
            return false;
        }

        for(int i = 0; i < leafValueOne.size(); i++) {
            if (!leafValueOne.get(i).equals(leafValueTwo.get(2))) {
                return false;
            }
        }

        return true;
    }

    private void generateLeafValuesBFS(TreeNode current, List<Integer> result) {
        if (current == null) return;

        if (current.left == null && current.right == null) {
            result.add(current.val);
            return;
        }

        generateLeafValuesBFS(current.left, result);
        generateLeafValuesBFS(current.right, result);
    }
}
