package recursion;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
*/

class Subsets_II_90 {
	public List<List<Integer>> subsetsWithDupI(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<>(), nums, 0, false);
		return res;
	}

	public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos, boolean choosePre) {
		if (pos == nums.length) {
			res.add(new ArrayList<>(ls));
			return;
		}

		helper(res, ls, nums, pos + 1, false);

		if (pos >= 1 && nums[pos] == nums[pos - 1] && !choosePre) return;

		ls.add(nums[pos]);

		helper(res, ls, nums, pos + 1, true);

		ls.remove(ls.size() - 1);
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return null;
		}

		int base = 0;
		Arrays.sort(nums);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());

		for (int j = 0; j < nums.length; j++) {
			if (j == 0 || nums[j] != nums[j - 1]) {
				base = result.size();
			}

			int length = result.size();
			for (int i = length - base; i < length; i++) {
				List<Integer> subset = new ArrayList<Integer>(result.get(i));
				subset.add(nums[j]);
				result.add(subset);
			}
		}

		return result;
	}

	public List<List<Integer>> subsetsWithDupII(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public List<List<Integer>> subsetsWithDupMy(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtracking(res, new ArrayList<Integer>(), nums, 0);
		return res;
	}

	void backtracking(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
		List<Integer> temp = new ArrayList<>();
		for (int i: list) temp.add(i);
		res.add(temp);

		for(int i = index; i < nums.length; ++i) {
			if (i > index && nums[i] == nums[i - 1]) continue;
			list.add(nums[i]);
			backtracking(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] {3, 1, 2};
		List<List<Integer>> res = new Subsets_II_90().subsetsWithDupMy(nums);

		for (List<Integer> item : res) {
			for (int k : item) {
				System.out.print(k);
				System.out.print(',');
			}

			System.out.println();
		}
	}
}