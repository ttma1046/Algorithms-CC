package linkedlist;

/**
 * Given a NON-NEGATIVE integer represented as non-empty a singly linked list
 * of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number
 * 0 itself.
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 *
 * E.g. Input: 1->2->3
 * Output: 1->2->4
 *
 * Input: 0
 * Output: 1
 *
 * Input: 7->9
 * Output: 8->0
 *
 * Input: 9->9
 * Output: 1->0->0
 *
 */
public class SolutionPlusOneLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        // constructor
        ListNode(int x) {
            val = x;
        }
    }
    private static ListNode plusOne(ListNode head) {
        // Step 1: Define return value
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 3: fill in business logic
        // think about e.g. 1999, 1989
        // j -> the last digit
        // i -> the most significant digit that could have a carry
        ListNode i = dummy;
        ListNode j = dummy;
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            j.val++;
        } else {
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }

        // Step 2: Handle corner cases
        // No negative, may have carry.
        if (dummy.val == 0) {
            return dummy.next;
        }
        return dummy;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(9);
        System.out.printf("Input: %d->%d \n",head.val, head.next.val);
        ListNode rst = SolutionPlusOneLinkedList.plusOne(head);
        System.out.printf("Output: %d->%d",rst.val, rst.next.val);
    }

}
