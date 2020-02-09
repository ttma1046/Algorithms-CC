class TreeNode {
    private int data;
    public TreeNode left;
    public TreeNode right;
    private int size = 0;

    public TreeNode(int d) {
        data = d;
        size = 1;
    }

    public TreeNode getRandomNode() {
        int leftSize = left == null ? 0 left.size();
        Random random = new Random();
        int index = random.nextlnt(size);

        if (index < leftSize) {
            return left.getRandomNode();
        } else if (index == leftSize) {
            return this;
        } else {
            return right.getRandomNode();
        }
    }

    public void insertlnOrder(int d) {
        if (d <= data) {
            if (left == nUll) {
                left = new TreeNode(d);
            } else {
                left.insertlnOrder(d);
            }
        } else {
            if (right == nUll) {
                right = new TreeNode(d);
            } else {
                right.insertlnOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return size;
    }
    public int data() {
        return data;
    }

    public TreeNode find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        } else if (d > data) {
            return right != null ? right.find(d) : null;
        }
        return null;
    }
}

class Tree {
    TreeNode root = null;

    public int size() {
        return root == null ? 0 : root.size();
    }

    public TreeNode getRandomNode() {
        if (root == nUll) return null;

        Random random = new Random();
        int i = random.nextlnt(size());
        return root.getlthNode(i);
    }

    public void insertlnOrder(int value) {
        if (root == nUll) {
            root = new TreeNode(value);
        } else {
            root. insertlnOrder(value) j
        }
    }
}

class TreeNode {
    /* constructor and variables are the same. */

    public TreeNode getlthNode(int i) {
        int leftSize = left == null ? 0 : left.size();
        if (i < leftSize) {
            return left.getlthNode(i);
        } else if (i == leftSize) {
            return this;
        } else {
            / * Skipping over leftSize + 1 nodes, so subtract them. * /
            return right.getlthNode(i - (leftSize + 1));
        }
    }

    public void insertlnOrder(int d) {
        / * same * /
    }

    public int size() {
        return size;
    }
    
    public TreeNode find(int d) {
        / * same * /
    }
}