package linkedlist;
import java.util.Set;
import java.util.HashSet;

/*
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. 

If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node ```c1```:

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

Example 1:

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

Example 2:

Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

Example 3:

Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
*/

/*
Given the heads of two singly linked-lists headA and headB,

return the node at which the two lists intersect.

If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

It is guaranteed that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Example 1:

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

Example 2:

Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

Example 3:

Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection;
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
0 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 

Follow up: Could you write a solution that runs in O(n) time and use only O(1) memory?
*/

class Intersection_of_two_linked_lists_160 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Set<ListNode> set = new HashSet<>();

		ListNode currentA = headA;

		while (headA != null) {
			set.add(headA);

			headA = headA.next;
		}

		while (headB != null) {
			if (set.contains(headB)) return headB;
			headB = headB.next;
		}

		return null;
	}

	/*
	Time complexity : O(N+M).

	Firstly, we need to build up the hash table. It costs O(1) to insert an item into a hash table, and we need to do this for each of the MM nodes in list B. This gives a cost of O(M)O(M) for building the hash table.

	Secondly, we need to traverse list A, and for each node, we need to check whether or not it is in the hash table. In the worst case, there will not be a match, requiring us to check all NN nodes in list A. As it is also O(1)O(1) to check whether or not an item is in a hash table, this checking has a total cost of O(N).

	Finally, combining the two parts, we get O(M) + O(N) = O(M + N).

	Space complexity : O(M).

	As we are storing each of the nodes from list B into a hash table, the hash table will require O(M)O(M) space. Note that we could have instead stored the nodes of list A into the hash table, this would have been a space complexity of O(N)O(N). Unless we know which list is longer though, it doesn't make any real difference.
	*/

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode pA = headA;
		ListNode pB = headB;

		while(pA.val != pB.val) {
			pA = pA == null ? headB : pA.next;
			pB = pB == null ? headA : pB.next;
		}

		return pA;
	}

	/*
	Time complexity : O(N+M).

	In the worst case, each list is traversed twice giving 2 \cdot M + 2 \cdot N2⋅M+2⋅N, which is equivalent to O(N + M)O(N+M). This is because the pointers firstly go down each list so that they can be "lined up" and then in the second iteration, the intersection node is searched for.

	An interesting observation you might have made is that when the lists are of the same length, this algorithm only traverses each list once. This is because the pointers are already "lined up" from the start, so the additional pass is unnecessary.

	Space complexity : O(1).

	We aren't allocating any additional data structures, so the amount of extra space used does not grow with the size of the input. For this reason, Approach 3 is better than Approach 2.
	*/
	public static void main(String[] args) {
		Intersection_of_two_linked_lists_160 obj = new Intersection_of_two_linked_lists_160();

		obj.getIntersectionNode();
	}
}