package monotonicstack;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
/*
You are given the head of a linked list with n nodes.

For each node in the list,

find the value of the next greater node.

That is, for each node,

find the value of the first node that is next to it and has a strictly larger value than it.

Return an integer array answer

where answer[i] is the value of the next greater node of the ith node (1-indexed).

If the ith node does not have a next greater node, set answer[i] = 0.

Example 1:
Input: head = [2,1,5]
Output: [5,5,0]

Example 2:
Input: head = [2,7,4,3,5]
Output: [7,0,5,5,0]

Constraints:

The number of nodes in the list is n.
1 <= n <= 104
1 <= Node.val <= 109
*/
/**
 * Definition for singly-linked list.
 */
// 直接转换成array, 或者倒过来list之后同样去遍历
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Next_Greater_Node_In_Linked_List_1019 {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();

        for (ListNode cur = head; cur != null; cur = cur.next) nums.add(cur.val);

        int n = nums.size();

        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; --i) {
            while (stack.size() > 0 && stack.peek() <= nums.get(i)) stack.pop();
            res[i] = stack.size() > 0 ? stack.peek() : 0;
            stack.push(nums.get(i));
        }

        return res;
    }

    public static void main(String[] args) {

    }
}