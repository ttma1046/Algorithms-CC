package bfs;

class BfsTraverseTree {
    public List<List<Integer>> bfsTree(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int length = queue.size();

            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < length; ++i) {
                TreeNode curr = queue.poll();

                temp.add(curr.val);

                if (curr.left != null)
                    queue.offer(curr.left);
                if (curr.right != null)
                    queue.offer(curr.right);
            }

            result.add(temp);
        }

        return result;
    }

    // 输入一棵二叉树的根节点，层序遍历这棵二叉树
    void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // 从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {
            int sz = q.size();
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 将下一层节点放入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
    }

    List<List<Integer>> res = new ArrayList<>();

    List<List<Integer>> levelTraverse(TreeNode root) {
        if (root == null)
            return res;

        // root 视为第 0 层
        traverse(root, 0);
        return res;
    }

    void traverse(TreeNode root, int depth) {
        if (root == null)
            return;

        // 前序位置，看看是否已经存储 depth 层的节点了
        if (res.size() <= depth)
            // 第一次进入 depth 层
            res.add(new LinkedList<>());

        // 前序位置，在 depth 层添加 root 节点的值
        res.get(depth).add(root.val);
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }


    List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> levelTraverse(TreeNode root) {
        if (root == null)
            return res;
        
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        traverse(nodes);
        return res;
    }

    void traverse(List<TreeNode> curLevelNodes) {
        // base case
        if (curLevelNodes.size() <= 0)
            return;
        
        // 前序位置，计算当前层的值和下一层的节点列表
        List<Integer> nodeValues = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();

        for (TreeNode node : curLevelNodes) {
            nodeValues.add(node.val);

            if (node.left != null)
                nextLevelNodes.add(node.left);
            
            if (node.right != null)
                nextLevelNodes.add(node.right);
        }
        // 前序位置添加结果，可以得到自顶向下的层序遍历
        res.add(nodeValues);
        traverse(nextLevelNodes);
        // 后序位置添加结果，可以得到自底向上的层序遍历结果
        // res.add(nodeValues);
    }
}