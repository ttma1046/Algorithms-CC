package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
    /*
    public List<List<Integer>> subsetsV(int[] nums) {
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

    List<List<Integer>> output = new ArrayList<>();
    int n, k;

    public List<List<Integer>> subsetsI(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrackI(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

    public void backtrackI(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList<Integer>(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrackI(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }
    */

    /*
    public List<List<Integer>> subsetsIII(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        return subsetsIII(nums, nums.length - 1);
    }

    public ArrayList<List<Integer>> subsetsIII(int[] nums, int index) {
        if (index < 0) {
            ArrayList<List<Integer>> empty = new ArrayList<>();
            empty.add(new ArrayList<Integer>());
            return empty;
        }

        int ele = nums[index];

        ArrayList<List<Integer>> currentSubsets = subsetsIII(nums, index - 1);
        int length = currentSubsets.size();
        for (int i = 0; i < length; i++) {
            List<Integer> temp = new ArrayList<Integer>(currentSubsets.get(i));
            temp.add(ele);
            currentSubsets.add(temp);
        }

        return currentSubsets;
    }

    public List<List<Integer>> subsetsIV(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int j = 0; j < nums.length; j++) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(nums[j]);
                result.add(subset);
            }
        }

        return result;
    }

    public List<List<Integer>> subsetsII(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrackII(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrackII(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrackII(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    */

    /*
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        backtracking(nums, new ArrayList<Integer>(), res, 0);

        return res;
    }

    void backtracking(int[] nums, List<Integer> list, List<List<Integer>> res, int index) {
        List<Integer> temp = new ArrayList<Integer>();
        for(int i: list) temp.add(i);
        res.add(temp);

        for(int i = 0; i < nums.length; ++i) {
            list.add(nums[i]);
            backtracking(nums, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }
    */

    public List<List<Integer>> subsets(int[] nums) {
        int totalNumber = 1 << nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int mask = 0; mask < totalNumber; ++mask) {
            List<Integer> set = new ArrayList<>();

            for (int j = 0; j < nums.length; j++) {
                System.out.println("mask:" + mask);
                System.out.println("1 << j:" + (1 << j));


                System.out.println("mask & (1 << j):" + (mask & (1 << j)));


                if ((mask & (1 << j)) != 0) set.add(nums[j]);
            }

            res.add(set);
        }
        return res;
    }

    public static void main(String[] args) {

        int j = 2;

        int z = 1 << j;

        System.out.println(j);
        System.out.println(z);


        
        int[] nums = new int[] {3, 1, 2};
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


/*
00000000
00000000

00000000  
00000010  

00000000  3
00000100

*/

