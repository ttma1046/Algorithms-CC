Update: check below the generic solution for K binary search trees.

We are doing in-order traversal independently for two trees. When it's time to 'visit' a node on the top of the stack, we are visiting the smallest of two.

Nodes are visited in the ascending order during the in-order traversal.

void pushLeft(stack<TreeNode*> &s, TreeNode* n) {
    if (n == nullptr) return;
    s.push(n);
    pushLeft(s, n->left);
}
vector<int> getAllElements(TreeNode* root1, TreeNode* root2) {
    vector<int> res;
    stack<TreeNode*> s1, s2;
    pushLeft(s1, root1);
    pushLeft(s2, root2);
    while (!s1.empty() || !s2.empty()) {
        stack<TreeNode*> &s = s1.empty() ? s2 : s2.empty() ? s1 : 
            s1.top()->val < s2.top()->val ? s1 : s2;
        auto n = s.top(); s.pop();
        res.push_back(n->val);
        pushLeft(s, n->right);
    }
    return res;
}
Complexity Analysis

Time: O(n), where n is the total number of nodes in both trees.
Memory: O(n) for the stack - in the worst case, we may need to store the entire tree there.
Generic Solution
A generic version of this problem would be to combine elements from K binary search trees. We can solve it by applying two existing solutions:

173. Binary Search Tree Iterator
23. Merge k Sorted Lists
Since we need a peek method, we can also use 284. Peeking Iterator, as suggested by @StefanPochmann to keep the iterator interface prestine. That way, you can plug a different BST iterator implementation, e.g. Morris traversal for O(1) memory.

Below is my take on this combined solution. I am not doing the proper memory management here to not to obfuscate the code (and it's specific to C++).

class BSTIterator {
  stack<TreeNode*> s;
  void pushLeft(TreeNode* n) {
    while (n != nullptr) s.push(exchange(n, n->left));
  }
public:
  BSTIterator(TreeNode* root) { pushLeft(root); }
  bool hasNext() const { return !s.empty(); }
  int next() {
    auto n = s.top(); s.pop();
    pushLeft(n->right);
    return n->val;
  }
};
class PeekingIterator : public BSTIterator {
  bool peeked = false;
  int last_val = 0;
public:
  PeekingIterator(TreeNode* root) : BSTIterator(root) { }
  int peek() {
    if (peeked) return last_val;
    peeked = true;
    return last_val = BSTIterator::next();
  }
  int next() {
    if (peeked) {
      peeked = false;
      return last_val;
    }
    return BSTIterator::next();
  }
  bool hasNext() const {
    return peeked || BSTIterator::hasNext();
  }
};
vector<int> mergeKTrees(vector<TreeNode*> trees) {
  vector<int> res;
  priority_queue <pair<int, PeekingIterator*>,
    vector<pair<int, PeekingIterator*>>, greater<pair<int, PeekingIterator*>> > q;
  for (auto t : trees) {
    auto it = new PeekingIterator(t);
    if (it->hasNext()) q.push({ it->peek(), it });
  }
  while (!q.empty()) {
    auto p = q.top(); q.pop();
    res.push_back(p.second->next());
    if (p.second->hasNext())
      q.push({ p.second->peek(), p.second });
  }
  return res;
}
vector<int> getAllElements(TreeNode* root1, TreeNode* root2) {
  return mergeKTrees({ root1, root2 });
}
Complexity Analysis

Time: O(n log k), where n is the total number of nodes in all trees, and k is the number of trees.
Memory: O(n + k) for the stack and priority queue.

JAVA

class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Generator t1 = new Generator(root1);
        Generator t2 = new Generator(root2);
        LinkedList<Integer> res = new LinkedList<>();
        
        // the logic of merging two sorted linked list
        while (t1.hasNext() && t2.hasNext()) {
            if (t1.peek() > t2.peek()) {
                res.add(t2.next());
            } else {
                res.add(t1.next());
            }
        }
        
        while (t1.hasNext()) {
            res.add(t1.next());
        }
        while (t2.hasNext()) {
            res.add(t2.next());
        }
        return res;
    }
    
}

class Generator {
    Stack<TreeNode> s = new Stack<>();
    
    public Generator(TreeNode root) {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }
    
    public int peek() {
        return s.peek().val;
    }
    
    public boolean hasNext() {
        return !s.isEmpty();
    }
    
    public int next() {
        TreeNode n = s.pop();
        if (n.right != null) {
            s.push(n.right);
            TreeNode r = n.right;
            while (r.left != null) {
                s.push(r.left);
                r = r.left;
            }
        }
        return n.val;
    }
}