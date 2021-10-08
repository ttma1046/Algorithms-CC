package linkedlist;
import java.util.Map;
import java.util.HashMap;
/*
A linked list of length n is given such that each node contains an additional random pointer,

which could point to any node in the list, or null.

Construct a deep copy of the list.

The deep copy should consist of exactly n ```brand new``` nodes,

where each new node has its value set to the value of its corresponding original node.

Both the next and random pointer of the new nodes

should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state.

None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes.

Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.

Your code will only be given the head of the original linked list.

Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Example 4:

Input: head = []
Output: []
Explanation: The given linked list is empty (null pointer), so return null.

Constraints:

0 <= n <= 1000
-10000 <= Node.val <= 10000
Node.random is null or is pointing to some node in the linked list.
*/

/*
Just iterate the linked list and create copies of the nodes on the go.

Since a node can be referenced from multiple nodes due to the random pointers, make sure you are not making multiple copies of the same node.

You may want to use extra space to keep old node ---> new node mapping to prevent creating multiples copies of same node.

We can avoid using extra space for old node ---> new node mapping, by tweaking the original linked list. Simply interweave the nodes of the old and copied list. For e.g.
Old List: A --> B --> C --> D
InterWeaved List: A --> A' --> B --> B' --> C --> C' --> D --> D'

The interweaving is done using next pointers and we can make use of interweaved structure to get the correct reference nodes for random pointers.
*/

// Definition for singly-linked list.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Copy_List_with_Random_Pointer_138 {
    // iterative iteration recursive recursion
    Map<Node, Node> map = new HashMap<Node, Node>();

    public Node copyRandomListRecursive(Node head) {
        if (head == null) return head;

        if (map.containsKey(head)) return map.get(head);

        Node res = new Node(head.val);

        map.put(head, res);

        if (head.next != null) res.next = copyRandomListRecursive(head.next);
        if (head.random != null) res.random = copyRandomListRecursive(head.random);

        return res;
    }
    /*
    Time Complexity : O(N) because we make one pass over the original linked list.
    Space Complexity : O(N) as we have a dictionary containing mapping from old list nodes to new list nodes. Since there are N nodes, we have O(N) space complexity.
    */
    Map<Node, Node> visited = new HashMap<Node, Node>();

    public Node getClonedNode(Node node) {
        if (node == null) return node;

        if (this.visited.containsKey(node)) {
            return this.visited.get(node);
        } else {
            this.visited.put(node, new Node(node.val));
            return this.visited.get(node);
        }
    }

    public Node copyRandomListIterative(Node head) {
        if (head == null) return head;

        Node oldNode = head;

        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);

        while(oldNode != null) {
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return this.visited.get(head);
    }

    /*
    Time Complexity: O(N)
    	where N is the number of nodes in the linked list.

    Space Complexity: O(N).
    	If we look closely, we have the recursion stack and we also have the space complexity to keep track of nodes already cloned i.e. using the visited dictionary.
    	But asymptotically, the complexity is O(N).
    */
    public Node copyRandomListOnePass(Node head) {
        if (head == null) return head;

        Node curr = head;

        while(curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        /*
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        */

        /*
        node    -> node ->
		  |          |
		  |-newNode--|
		*/

        curr = head;

        while(curr != null) {
            if (curr.random != null) curr.next.random = curr.random.next; // right!!! cos curr.random.next is the clone of curr.random.            

            curr = curr.next.next; // right!! cos curr.next is the clone, so have to do curr.next.next.
        }

        /*
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        */

        Node ptr_old_list = head;
        Node ptr_new_list = head.next;

        Node res = head.next;

        while(ptr_new_list != null) {
            // ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;

            // ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }

        return res;
    }

    // Time Complexity : O(N)
    // Space Complexity : O(1)

    public Node copyRandomListMap(Node head) {
    	Map<Node, Node> map = new HashMap<>():

    	for (Node cur = head; cur != null; cur = cur.next)
    		map.put(cur, new Node(cur.val));

    	for (Node cur = head; cur != null; cur = cur.next) {
    		map.get(cur).next = map.get(cur.next);
    		map.get(cur).random = map.get(cur.random);
    	}

    	return map.get(head);
    }

    public static void main(String[] args) {
    	Node seven = new Node(7);
    	Node thirteen = new Node(13);
    	Node eleven = new Node(11);
    	Node ten = new Node(10);
    	Node one = new Node(1);

    	seven.next = thirteen;
    	seven.random = null;

    	thirteen.next = eleven;
    	thirteen.random = seven;

    	eleven.next = ten;
    	eleven.random = one;

    	ten.next = one;
    	ten.random = eleven;

    	one.random = seven;

        Copy_List_with_Random_Pointer_138 obj = new Copy_List_with_Random_Pointer_138();
        Node res = obj.copyRandomListOnePass(seven);

        while(res != null) {
        	System.out.println(res.val);
        	res = res.next;
        }
    }
}