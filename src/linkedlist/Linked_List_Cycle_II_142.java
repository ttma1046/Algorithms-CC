
package linkedlist;
import java.util.HashSet;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.

Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Notice that you should not modify the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
*/
public class Linked_List_Cycle_II_142 {
    public ListNode detectCycleHashSet(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            }

            set.add(curr);
            curr = curr.next;
        }

        return null;
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public ListNode detectCycleTwopointers(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null ) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Linked_List_Cycle_II_142 obj = new Linked_List_Cycle_II_142();

        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode zero = new ListNode(0);
        ListNode four = new ListNode(-4);

        three.next = two;
        two.next = zero;
        zero.next = four;

        four.next = two;

        ListNode res = obj.detectCycle(three);

        System.out.println(res.val);
    }
}

/*
2 * (head to cycle entry + B) = head to cycle entry + B  + round

round = (head to cycle entry) + B

head to cycle = round - B
*/


