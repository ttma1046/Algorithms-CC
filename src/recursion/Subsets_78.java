package recursion;
import java.util.List;
import java.util.ArrayList;

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

class Subsets_78 {
    List<List<Integer>> output = new ArrayList();
    int n, k;

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        return subsets(nums, nums.length - 1);
    }

    public ArrayList<List<Integer>> subsets(int[] nums, int index) {
        if (index < 0) {
            ArrayList<List<Integer>> empty = new ArrayList<List<Integer>>();
            empty.add(new ArrayList<Integer>());
            return empty;
        }

        int ele = nums[index];

        ArrayList<List<Integer>> currentSubsets = subsets(nums, index - 1);
        int length = currentSubsets.size();
        for (int i = 0; i < length; i++) {
            List<Integer> temp = new ArrayList<Integer>(currentSubsets.get(i));
            temp.add(ele);
            currentSubsets.add(temp);
        }

        return currentSubsets;
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        List<List<Integer>> result = new ArrayList();
        result.add(new ArrayList<Integer>());

        for (int num : nums) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                List<Integer> subset = new ArrayList<Integer>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }

        return result;
    }
    * /

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        res.add(new ArrayList<Integer>());

        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset);
            }
        }


        return res;
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{add(num);}});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> res = new Subsets_78().subsets(nums);

        for (List<Integer> item : res) {
            for (int k : item) {
                System.out.print(k);
                System.out.print(',');
            }

            System.out.println();
        }
    }
}