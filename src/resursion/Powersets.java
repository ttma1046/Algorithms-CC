package resursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Powersets {
    public static void main(String[] args) {
        System.out.println(new Powersets().subsetsWithDupIte(new int[]{1, 2, 3}));

        System.out.println(new Powersets().subsetsWithDupRec(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsetsWithDupIte(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList();
        result.add(new ArrayList<Integer>());


        for(int num: nums) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                List<Integer> subset = new ArrayList<Integer>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }

        return result;
    }

    public List<List<Integer>> subsetsWithDupRec(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        return this.subsetsWithDupRec(nums, nums.length - 1);
    }

    public ArrayList<List<Integer>> subsetsWithDupRec(int[] nums, int index) {
        if (index < 0) {
            ArrayList<List<Integer>> empty = new ArrayList<List<Integer>>();
            empty.add(new ArrayList<Integer>());
            return empty;
        }

        int ele = nums[index];

        ArrayList<List<Integer>> currentSubsets = subsetsWithDupRec(nums, index - 1);
        int length = currentSubsets.size();
        for (int i = 0; i < length; i++) {
            List<Integer> temp = new ArrayList<Integer>(currentSubsets.get(i));
            temp.add(ele);
            currentSubsets.add(temp);
        }

        return currentSubsets;
    }

    ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allsubsets;

        if (set.size() == index) {
            // Base case - add empty set;
            allsubsets = new ArrayList<ArrayList<Integer>>();

            allsubsets.add(new ArrayList<Integer>()); // Empty set;
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);

            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> subset: allsubsets) {
                ArrayList<Integer> newsubset = new ArrayList<Integer>();

                newsubset.addAll(subset); //
                newsubset.add(item);
                moresubsets.add(newsubset);
            }

            allsubsets.addAll(moresubsets);
        }

        return allsubsets;
    }
}