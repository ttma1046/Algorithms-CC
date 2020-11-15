## Maximum Difference Between Node and Ancester 1026

Given the root of a binary tree, find the maximum value `V` for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.

Example 1:
```
Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
```
Explanation: We have various ancestor-node differences, some of which are given below :
```
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
```
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:
```
Input: root = [1,null,2,null,0,3]
Output: 3
```

Constraints:
The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 105




```java
public class Node {
	public Node left;
	public Node right;
	public int val;

	Node(int value) {
		val = value;
	}
}



/*

        8
       / \
     3    10
    / \    \
   1   6    14
      / \   /
     4  7  13 
*/






public class Maximum_Difference_Between_Node_and_Ancestor_1026 {
	public int getMax(Node root) {
		return 0;
	}


	public static void main(String[] args) {

 		Node thirteen = new Node(13);
		Node fourteen = new Node(14);
		fourteen.left = thirteen;

        Node four = new Node(4);
        Node seven = new Node(7);

        Node six = new Node(6);
        six.left = four;
        six.right = seven;

        Node one = new Node(1);
		Node three = new Node(3);
		three.left = one;
		three.right = six;

		Node ten = new Node(10);
		ten.right = fourteen;
		Node root = new Node(8);

		root.left = three;
		root.right = seven;

		System.out.println(new Maximum_Difference_Between_Node_and_Ancestor_1026().getMax(new Node()));
	}
}






```