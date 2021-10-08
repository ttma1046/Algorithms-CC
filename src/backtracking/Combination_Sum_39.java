package backtracking;
import java.util.List;
import java.util.ArrayList;
/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:

Input: candidates = [2], target = 1
Output: []

Example 4:

Input: candidates = [1], target = 1
Output: [[1]]

Example 5:

Input: candidates = [1], target = 2
Output: [[1,1]]

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
*/
class Combination_Sum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        recursive(candidates, target, res, new ArrayList<Integer>(), 0);

        return res;
    }

    public void recursive(int[] candidates, int remain, List<List<Integer>> res, List<Integer> rest, int start) {
        if (remain == 0) {
            res.add(new ArrayList<Integer>(rest));
            return;
        }

        if (remain < 0) {
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            rest.add(candidates[i]);
            recursive(candidates, remain - candidates[i], res, rest, i);
            rest.remove(rest.size() - 1);
        }
    }

    /*
    Let NN be the number of candidates, TT be the target value, and MM be the minimal value among the candidates.

    Time Complexity: O(N^{\frac{T}{M}+1})

    * As we illustrated before, the execution of the backtracking is unfolded as a DFS traversal in a n-ary tree. 
        The total number of steps during the backtracking would be the number of nodes in the tree.

    * At each node, it takes a constant time to process, except the leaf nodes which could take a linear time to make a copy of combination. 
        So we can say that the time complexity is linear to the number of nodes of the execution tree.

    * Here we provide a loose upper bound on the number of nodes.

        * First of all, the fan-out of each node would be bounded to N, i.e. the total number of candidates.

        * The maximal depth of the tree, would be \frac{T}{M}, where we keep on adding the smallest element to the combination.

        * As we know, the maximal number of nodes in N-ary tree of \frac{T}{M} height would be N^{\frac{T}{M}+1}.

    Note that, the actual number of nodes in the execution tree would be much smaller than the upper bound, since the fan-out of the nodes are decreasing level by level.

    Space Complexity: \mathcal{O}(\frac{T}{M})

    * We implement the algorithm in recursion, which consumes some additional memory in the function call stack.

    * The number of recursive calls can pile up to \frac{T}{M}, where we keep on adding the smallest element to the combination. As a result, the space overhead of the recursion is \mathcal{O}(\frac{T}{M})

    * In addition, we keep a combination of numbers during the execution, which requires at most \mathcal{O}(\frac{T}{M}) space as well.

    * To sum up, the total space complexity of the algorithm would be \mathcal{O}(\frac{T}{M}).

    Note that, we did not take into the account the space used to hold the final results for the space complexity.
    */

    public static void main(String[] args) {
        Combination_Sum_39 obj = new Combination_Sum_39();
        int[] candidates = new int[] {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = obj.combinationSum(candidates, target);
        for (List<Integer> res : result) {
            for (int l : res) {
                System.out.print(l);
                System.out.print(",");
            }

            System.out.println();
        }
    }
}