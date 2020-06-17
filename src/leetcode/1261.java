  private Set<Integer> seen = new HashSet<>();
    
    public FindElements(TreeNode root) {
        dfs(root, 0);
    }
    private void dfs(TreeNode n, int v) {
        if (n == null) return;
        seen.add(v);
        n.val = v;
        dfs(n.left, 2 * v + 1);
        dfs(n.right, 2 * v + 2);
    }
    
    public boolean find(int target) {
        return seen.contains(target);
    }
    def __init__(self, root: TreeNode):
        self.seen = set()
        
        def dfs(node: TreeNode, v: int) -> None:
            if node:
                node.val = v    
                self.seen.add(v)
                dfs(node.left, 2 * v + 1)
                dfs(node.right, 2 * v + 2)
            
        dfs(root, 0)
        
    def find(self, target: int) -> bool:
        return target in self.seen
Analysis:
HashSet cost space O(N), dfs() cost space and time O(N), therefore

FindElements() (dfs()) cost
time & space: O(N).

find() cost
time & space: O(1) excluding the space of HashSet.

Where N is the total number of nodes in the tree.