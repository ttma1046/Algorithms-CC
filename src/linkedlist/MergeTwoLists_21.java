package linkedlist;

/*
Merge two sorted linked lists and return it as a sorted list.

The list should be made by splicing together the nodes of the first two lists.

Example 1:

Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:

Input: l1 = [], l2 = []
Output: []

Example 3:

Input: l1 = [], l2 = [0]
Output: [0]

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
*/

class MergeTwoLists_21 {
    public ListNode mergeTwoListsMy(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        ListNode prevHead = new ListNode(-1);
        ListNode curr = prevHead;

        while(temp1 != null && temp2 != null) {
            if (temp1.val > temp2.val) {
                curr.next = temp2;
                temp2 = temp2.next;
            } else {
                curr.next = temp1;
                temp1 = temp1.next;
            }
            curr = curr.next;
        }

        curr.next = temp1 == null ? temp2 : temp1;

        return prevHead.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsIV(ListNode l1, ListNode l2) {
        ListNode tempOne = l1;
        ListNode tempTwo = l2;

        ListNode result = new ListNode(-1);

        if (tempOne == null && tempTwo == null) {
            return null;
        }

        if (tempOne == null) {
            return tempTwo;
        }

        if (tempTwo == null) {
            return tempOne;
        }

        if (tempOne.val < tempTwo.val) {
            result = new ListNode(tempOne.val);
            tempOne = tempOne.next;
        } else {
            result = new ListNode(tempTwo.val);
            tempTwo = tempTwo.next;
        }

        ListNode temp = result;

        while (tempOne != null && tempTwo != null) {
            if (tempOne.val < tempTwo.val) {
                temp.next = new ListNode(tempOne.val);
                tempOne = tempOne.next;
            } else {
                temp.next = new ListNode(tempTwo.val);
                tempTwo = tempTwo.next;
            }

            temp = temp.next;
        }

        temp.next = tempOne == null ? tempTwo : tempOne;

        return result;
    }

    public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoListsII(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsII(l1, l2.next);
            return l2;
        }
    }

    /*
    public ListNode MergeTwoListsOrder(ListNode l1, ListNode l2) {
        ListNode first = l1, prev = l2;

        while(prev.next != null) {
            ListNode tmp = first.next;
            first.next = prev;
            first = tmp;

            tmp = prev.next;
            prev.next = first;
            prev = tmp;
        }

        return l1;
    }
    */

    public ListNode MergeTwoListsOrder(ListNode l1, ListNode l2) {
        ListNode first = l1, prev = l2;

        while (prev.next != null) {
            ListNode tmp = first.next;
            first.next = prev;

            first = tmp;

            tmp = prev.next;
            prev.next = first;

            prev = tmp;
        }

        return l1;
    }

    public ListNode MergeTwoListsIIII(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // At least one of l1 and l2 can still have nodes at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    public static void main(String[] args) {
        ListNode six = new ListNode(6);
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4);
        six.next = five;
        five.next = four;


        ListNode fourtwo = new ListNode(4);

        ListNode three = new ListNode(3);
        three.next = fourtwo;

        ListNode two = new ListNode(2);
        two.next = three;
        ListNode one = new ListNode(1);
        one.next = two;

        ListNode result = new MergeTwoLists_21().MergeTwoListsOrder(one, six);


        System.out.println("==============");
        System.out.println("result:");

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}