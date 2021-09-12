package tree;
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

/*
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

According to the definition of LCA on Wikipedia:
    |"The lowest common ancestor of two nodes p and q in a tree T
    |is the lowest node that has both p and q as descendants
    |(where we allow a node to be a descendant of itself)."

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation:
The LCA of nodes 5 and 4 is 5
since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
*/

/*
       3
  5        1
6   2     0 8
   7 4
*/

/*
   5 2
   3 5
   null 3
   4 null
   2 6
   5 5
*/

class ParentNode {
    public int val;
    public ParentNode left;
    public ParentNode right;
    public ParentNode parent;

    public ParentNode(int a) {
        this.val = a;
    }
}



/*
6 -> 5 -> 3 <- 1
 >
/
> 2 <
/     \
7      4
*/

class Lowest_Common_Ancestor_of_a_Binary_Tree_III_1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> path = find_Path(p);
        while (q.parent != null) {
            for (Node node : path) {
                if (node == q) return q;
            }
            q = q.parent;
        }
        return q;
    }

    public List<Node> find_Path(Node p) {
        List<Node> path = new ArrayList<>();
        while (p.parent != null) {
            path.add(p);
            p = p.parent;
        }
        return path;
    }

    public ParentNode lowestCommonAncestor(ParentNode p, ParentNode q) {
        ParentNode p1 = p, p2 = q;
        while (p1 != p2) {
            p1 = p1 == null ? q : p1.parent;
            p2 = p2 == null ? p : p2.parent;
        }
        return p1;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while(true) {
            if (p != null && !set.add(p)) return p;
            if (q != null && !set.add(q)) return q;

            if (p != null) p = p.parent;
            if (q != null) q = q.parent;
        }
    }

    public ParentNode lowestCommonAncestor(ParentNode p, ParentNode q) {
        List<ParentNode> pList = new ArrayList<>(), qList = new ArrayList<>();
        pList.add(p); 
        qList.add(q);
        while(p != null && q != null){
            if(p.parent != null){
                p = p.parent;
                pList.add(p);
            }
            if(q.parent != null){
                q = q.parent;
                qList.add(q);
            }
            if(pList.indexOf(q) != -1){
                return q;
            }
            if(qList.indexOf(p) != -1){
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ParentNode three = new ParentNode(3);
        ParentNode five = new ParentNode(5);
        ParentNode two = new ParentNode(2);
        ParentNode six = new ParentNode(6);

        ParentNode seven = new ParentNode(7);
        ParentNode four = new ParentNode(4);

        ParentNode one = new ParentNode(1);
        ParentNode zero = new ParentNode(0);
        ParentNode eight = new ParentNode(8);

        three.left = five;
        three.right = one;

        five.parent = three;
        five.left = six;
        five.right = two;

        six.parent = five;

        two.parent = five;
        two.left = seven;
        two.right = four;

        seven.parent = two;
        four.parent = two;

        one.left = zero;
        one.right = eight;
        one.parent = three;
        zero.parent = one;
        eight.parent = one;

        new Lowest_Common_Ancestor_of_a_Binary_Tree_III_1650()
        .lowestCommonAncestor(six, four);
    }
}