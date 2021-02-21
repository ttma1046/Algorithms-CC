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

class Lowest_Common_Ancestor_of_a_Binary_Tree_III_1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
     Node p1 = p, p2 = q;
	 while (p1 != p2) {
		 p1 = p1 == null ? q : p1.parent;
		 p2 = p2 == null ? p : p2.parent;    
	  }
	  return p1;
    }
}