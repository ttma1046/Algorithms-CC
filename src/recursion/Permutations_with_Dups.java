package recursion;

import java.util.*;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/
class Permutations_with_Dups {
    public List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int num : nums) {
            arrayList.add(num);
        }

        HashMap<Integer, Integer> map = buildFreqTable(arrayList);

        getPermutations(map, new ArrayList<Integer>(), arrayList.size(), permutations);
        return permutations;
    }

    private HashMap<Integer, Integer> buildFreqTable(ArrayList<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int item : nums) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        return map;
    }

    private void getPermutations(HashMap<Integer, Integer> map, ArrayList<Integer> perfix, int remaining,
            ArrayList<List<Integer>> permutations) {
        if (remaining == 0) {
            permutations.add(perfix);
        } else {
            for (int item : map.keySet()) {
                int count = map.get(item);
                if (count > 0) {
                    map.put(item, count - 1);
                    ArrayList<Integer> list = new ArrayList<Integer>(perfix);
                    list.add(item);
                    getPermutations(map, list, remaining - 1, permutations);
                    map.put(item, count);
                }
            }
        }
    }

    public List<List<Integer>> permuteUniqueII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void backTracking(List<List<Integer>> resultList, List<Integer> current, int[] nums, boolean[] used) {
        if (current.size() == nums.length)
            resultList.add(new ArrayList<Integer>(current));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) // remove duplicate
                    continue;
                current.add(nums[i]);
                used[i] = true;
                backTracking(resultList, current, nums, used);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations_with_Dups().permuteUnique(new int[] { 1, 1, 3 });

        for (List<Integer> item : result) {
            for (Integer num : item) {
                System.out.println(num);
            }
        }
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUniqueIII(int[] nums) {
        if (nums.length != 0) {
            helper(nums, 0);
        }

        return res;
    }

    private void helper(int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums)
                list.add(n);
            res.add(list);

            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (check(nums, index, i))
                continue;

            swap(nums, i, index);
            helper(nums, index + 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private boolean check(int[] nums, int s, int e) {
        for (int i = s; i < e; i++) {
            if (nums[i] == nums[e])
                return true;
        }

        return false;
    }
}