package linkedlist;

public class ReverseLinkedList_206 {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next;
 
            curr.next = prev;

            prev = curr;

            curr = temp;
        }

        return prev;
    }

    public ListNode reverseListII(ListNode current) {
        if (current == null || current.next == null) return current;
        ListNode p = reverseListII(current.next);
        current.next.next = current;
        current.next = null;

        return p;
    }


    public ListNode reverseListIII(ListNode head) {
        /* recursive solution */
        return reverseListInt(null, head);
    }

    private ListNode reverseListInt(ListNode prev, ListNode current) {
        if (current == null)
            return prev;
        ListNode next = current.next;
        current.next = prev;
        return reverseListInt(current, next);
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        five.next = new ListNode(6);

        ListNode four  = new ListNode(4);
        four.next = five;

        ListNode three = new ListNode(3);
        three.next = four;

        ListNode two = new ListNode(2);
        two.next = three;

        ListNode node = new ListNode(1);
        node.next = two;

        new ReverseLinkedList_206().print(node);

        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }

/*        ListNode reverseNode = new ReverseLinkedList_206().reverseListIII(node);
        while(reverseNode != null) {
            System.out.println(reverseNode.val);
            reverseNode = reverseNode.next;
        }*/
    }

    private void print(ListNode node) {
/*        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }*/
        node = node.next;

        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        // node.next = new ListNode(7);
    }
}
