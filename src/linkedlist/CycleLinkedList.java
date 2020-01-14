package linkedlist;

import java.util.HashSet;

public class CycleLinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null && slow != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public boolean hasCyclewithHashTable(ListNode head) {
        if (head == null) return false;
        HashSet<ListNode> visited = new HashSet<ListNode>();

        ListNode current = head;
        while(current != null) {
            if (visited.contains(current)) return true;
            visited.add(current);
            current = current.next;
        }

        return false;
    }
}
