package backtracking;
import java.util.List;
import java.util.ArrayList;
/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
```
[
	[1,1,6],
	[1,2,5],
	[1,7],
	[2,6]
]
```

Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
```
[
	[1,2,2],
	[5]
]
```

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
*/
class Combination_Sum_II_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);
        
        recursive(candidates, target, 0, res, new ArrayList<Integer>());

        return res;
    }

    void recursive(int[] candidates, int remain, int index, List<List<Integer>> res, List<Integer> rest) {
        if (remain == 0) {
            res.add(new ArrayList<Integer>(rest));
            return;
        }

        if (remain < 0) return;

        for(int i = index; i < candidates.length; ++i) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            if (remain - candidates[i] < 0) break;

        	rest.add(candidates[i]);
        	recursive(candidates, remain - candidates[i], i + 1, res, rest);
        	rest.remove(rest.size() - 1);
        }
    }
}