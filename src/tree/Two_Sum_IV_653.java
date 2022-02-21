package tree;

/*
Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input: root = [5,3,6,2,4,null,7], k = 9
Output: true

Example 2:

Input: root = [5,3,6,2,4,null,7], k = 28
Output: false

Constraints:

The number of nodes in the tree is in the range [1, 104].
-104 <= Node.val <= 104
root is guaranteed to be a valid binary search tree.
-105 <= k <= 105
*/

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
/**/
class Two_Sum_IV_653 {
    public boolean findTargetI(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        dfs(root, k, set);
    }

    private boolean dfs(TreeNode node, int k, Set<Integer> set) {
        if (node != null) return false;

        if (set.contains(k - node.val))
            return true;

        set.add(node.val);

        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }

    public boolean findTargetI(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(queue.size() > 0) {
            TreeNode node = queue.poll();

            if (set.contains(k - node.val)) return true;

            set.add(node.val);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    /*
    Time complexity : O(n). We need to traverse over the whole tree once in the worst case.
    Here, n refers to the number of nodes in the given tree.

    Space complexity : O(n). The size of the setset can grow atmost upto nn.
    */

    public boolean findTarget(TreeNode root, int k) {
        List <Integer> list = new ArrayList();
        inorder(root, list);

        int low = 0, hight = list.size() - 1;
        while (low < high) {
            int sum = list.get(l) + list.get(r);

            if (sum == k) return true;

            if (sum < k) l++;
            else r--;
        }
        return false;
    }
    public void inorder(TreeNode node, List <Integer> list) {
        if (node == null) return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        for(int i = 0, j = nums.size() - 1; i < j;) {
            if(nums.get(i) + nums.get(j) == k)return true;
            if(nums.get(i) + nums.get(j) < k)i++;
            else j--;
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if(root == null)return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root,  k);
    }

    public boolean dfs(TreeNode root,  TreeNode cur, int k) {
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value) {
        if(root == null)return false;
        return (root.val == value) && (root != cur)
               || (root.val < value) && search(root.right, cur, value)
               || (root.val > value) && search(root.left, cur, value);
    }
}