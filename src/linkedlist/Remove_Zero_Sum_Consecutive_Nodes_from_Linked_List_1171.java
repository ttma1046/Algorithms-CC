package linkedlist;
import java.util.Map;
import java.util.HashMap;
/*
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.

Example 2:

Input: head = [1,2,3,-3,4]
Output: [1,2,4]

Example 3:

Input: head = [1,2,3,-3,-2]
Output: [1]

Constraints:

The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
*/

/*
Intuition
Assume the input is an array.
Do you know how to solve it?
Scan from the left, and calculate the prefix sum.
Whenever meet the seen prefix,
remove all elements of the subarray between them.

Solution 1
Because the head ListNode can be removed in the end,
I create a dummy ListNode and set it as a previous node of head.
prefix calculates the prefix sum from the first node to the current cur node.

Next step, we need an important hashmap m (no good name for it),
It takes a prefix sum as key, and the related node as the value.

Then we scan the linked list, accumulate the node's value as prefix sum.

If it's a prefix that we've never seen, we set m[prefix] = cur.
If we have seen this prefix, m[prefix] is the node we achieve this prefix sum.
We want to skip all nodes between m[prefix] and cur.next (exclusive).
So we simplely do m[prefix].next = cur.next.
We keep doing these and it's done.

Complexity
Time O(N), one pass
SpaceO(N), for hashmap

Java:
*/

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

public class Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List_1171 {
    /*
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        dummy.next = head;
        int prefix = 0;
        Map<Integer, ListNode> m = new HashMap<>();
        while (cur != null) {
            prefix += cur.val;

            if (m.containsKey(prefix)) {
                cur =  m.get(prefix).next;
                int p = prefix + cur.val;
                while (p != prefix) {
                    m.remove(p);
                    cur = cur.next;
                    p += cur.val;
                }
                m.get(prefix).next = cur.next;
            } else {
                m.put(prefix, cur);
            }

            cur = cur.next;
        }
        return dummy.next;
    }
    */

    public static void main(String[] args) {
        ListNode head =
            new ListNode(1,
                         new ListNode(2,
                                      new ListNode(3,
                                              new ListNode(-3,
                                                      new ListNode(4)))));

        Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List_1171 obj = new Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List_1171();

        obj.removeZeroSumSublists(head);
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        // The observation here is that the sum from index 0 to index M will be
        // equal to sum from index 0 to index N if sum from index (M+1) to index N is 0.
        // Thus, here we track the sum from index 0 to each index, using a Map to indicate
        // the farthest index N that we can remove from index M, then we shall be able to
        // remove M+1 -> N and continue from N+1. This works since we don't have to optimize
        // for the number of sequences to be removed

        // Map from sum from index 0 to the farthest value that the sum stays unchanged.
        Map<Integer, ListNode> sumToFarthestNodeMap = new HashMap<>();

        // Need the dummy node to track the new head if changed.
        ListNode preHead = new ListNode(0);
        preHead.next = head;

        // First iteration to compute the map.
        int sum = 0;
        for (ListNode p = preHead; p != null; p = p.next) {
            sum += p.val;
            sumToFarthestNodeMap.put(sum, p);
        }

        for (int i : sumToFarthestNodeMap.keySet()) {
            ListNode temp = sumToFarthestNodeMap.get(i);

            while(temp != null) temp = temp.next;
        }

        // Second iteration to re-connect the nodes to the farthest node where the sum stays unchanged
        sum = 0;
        for (ListNode p = preHead; p != null; p = p.next) {
            sum += p.val;

            p.next = sumToFarthestNodeMap.get(sum).next;
        }

        // Done, return the head from preHead
        return preHead.next;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        int preFix = 0;

        ListNode preHead = new ListNode(0);

        preHead.next = head;

        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();

        map.put(0, preHead);

        ListNode curr = preHead;

        while(curr != null) {
            preFix += curr.val;
            map.put(preFix, curr);
        }

        preFix = 0;
        curr = preHead;

        while(curr != null) {
            preFix += curr.val;
            curr.next = map.get(preFix).next;
        }

        return preHead.next;
    }

    /*
    1 2 4

    p => 1 2 3 -3 4

    【
       0 -> 0 1 2 3 -3 4
       1 -> 1 2 3 -3 4
       3 -> -3 4
       6 -> 3 -3 4
       7 -> 4
     ]

     0
     0 1 2 3 -3 4
     0 + 1 = 1
     0 1 2 3 -3 4
     1 + 2 = 3
     0 1 2 4
     3 + 4 = 7



     */
    /*
    Improvement
    I think that's the best part of my post.
    It's a great discuss in the leetcode's discuss.

    People are willing to read my article and help me improve it.
    To be honest, I think I take good responsiblilty to maintain my solution.
    (Though the case I don't have prime membership and canot even read my own post in locked problem)

    Thanks to @alexjst inspired me the follwing solution.


    Soluiton 2: Two Passes
    The story is that,
    I wrote the really concise solution,
    it got accepted but actully it's wrong.
    I fixed it by adding another while loop.
    That is the Solution 1.

    If we don't insist on one pass,
    we can find the two passes is actually really neat.

    That turned back to the intuition that I mentioned:
    Assume the input is an array.
    How will you solve the problem?

    Iterate for the first time,
    calculate the prefix sum,
    and save the it to seen[prefix]

    Iterate for the second time,
    calculate the prefix sum,
    and directly skip to last occurrence of this prefix
    */

    /*
        public ListNode removeZeroSumSublists(ListNode head) {
            int prefix = 0;
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            Map<Integer, ListNode> seen = new HashMap<>();
            seen.put(0, dummy);
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                seen.put(prefix, i);
            }
            prefix = 0;
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                i.next = seen.get(prefix).next;
            }
            return dummy.next;
        }
    */

    /*
    Update 2019-08-25
    The OJ solution was wrong.
    It didn't block the right submit,
    but wrong submit can also get accepted.

    Following the test case given by @kay_deep:
    [1, 3, 2, -3, -2, 5, 100, -100, 1]
    The expected result should be [1,5,1] or [1,3,2,1].

    Some solution in the discuss part are still wrong.
    */
}