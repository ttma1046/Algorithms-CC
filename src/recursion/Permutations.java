package recursion;

import java.util.ArrayList;
import java.util.List;

class Permutations {
    // Upper Bound: O(n^2*n!) time | O(n*n!) space
    // Roughly: O(n*n!) time | O(n*n!) space
    public static ArrayList<ArrayList<Integer>> getPermutations(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        getPermutations(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }

    /*
     * array: {1,2,3} 1 newArray: {2,3} newPermutation: {1} permutations: {{}}
     * 
     * array: {2,3} 2 newArray:{3} newPermutation: {1,2} permutations: {{}}
     * 
     * array:{3} 3 newAarray;{}, newPermutation: {1,2,3} permutations: {{}}
     * 
     * permutations: {{1,2,3}}
     * 
     * 3 newArray:{2} newPermutation: {1,3} permutations: {{1,2,3}}
     * 
     * array: {2} 2 newArray:{} newPermutation: {1,3,2} permutations: {{1,2,3}}
     * 
     * permutations: {{1,2,3},{1,3,2}}
     * 
     * 2 newArray: {1,3} newPermutation: {2} permutations: {{1,2,3},{1,3,2}}
     * 
     * array: {1,3} 1 newArray: {3} newPermutation: {2,1} permutations:
     * {{1,2,3},{1,3,2}}
     * 
     * array: {3} 3 newArray: {} newPermutation: {2,1,3} permutations:
     * {{1,2,3},{1,3,2}}
     * 
     * permutations: {{1,2,3},{1,3,2},{2,1,3}}
     * 
     * 3 newArray: {1} newPermutation: {2,3} permutations: {{1,2,3},{1,3,2}}
     * 
     * array: {1} 1 newArray: {} newPermutation: {2,3,1} permutations:
     * {{1,2,3},{1,3,2},{2,1,3}}
     * 
     * permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
     * 
     * 3 newArray: {1,2} newPermutation: {3} permutations:
     * {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
     * 
     * array: {1,2} 1 newArray: {2} newPermutation: {3,1} permutations:
     * {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
     * 
     * array: {2} 2 newArray: {} newPermutation: {3,1,2} permutations:
     * {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
     * 
     * permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
     * 
     * 2 newArray: {1} newPermutation: {3,2} permutations:
     * {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
     * 
     * array: {1} 1 newArray: {} newPermutation: {3,2,1} permutations:
     * {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
     * 
     * permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}}
     */

    public static ArrayList<ArrayList<Integer>> getPermutationsII(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        getPermutationsII(0, array, permutations);
        return permutations;
    }

    public static void getPermutationsII(int i, ArrayList<Integer> array, ArrayList<ArrayList<Integer>> permutations) {
        if (i == array.size() - 1) {
            permutations.add(new ArrayList<Integer>(array));
        } else {
            for (int j = i; j < array.size(); j++) {
                swap(array, i, j);
                getPermutations(i + 1, array, permutations);
                swap(array, i, j);
            }
        }
    }

    public static void swap(ArrayList<Integer> array, int i, int j) {
        Integer tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int num : nums) {
            array.add(num);
        }
        getPermutations(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }

    public static void getPermutations(ArrayList<Integer> array, ArrayList<Integer> currentPermutation,
            ArrayList<ArrayList<Integer>> permutations) {
        if (array.size() == 0 && currentPermutation.size() > 0) {
            permutations.add(currentPermutation);
        } else {
            for (int i = 0; i < array.size(); i++) {
                ArrayList<Integer> newArray = new ArrayList<Integer>(array);
                newArray.remove(i);
                ArrayList<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(array.get(i));
                getPermutations(newArray, newPermutation, permutations);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int[] { 1, 2, 3 });

        for (List<Integer> item : result) {
            for (Integer num : item) {
                System.out.println(num);
            }
        }
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteV(int[] nums) {

        if (nums.length == 0) {
            return result;
        }
        permuteV(nums, 0, nums.length - 1);
        return result;
    }

    private void permuteV(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }

        if (left == right) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                current.add(nums[j]);
            }
            result.add(current);
            return;
        }

        for (int i = left; i <= right; i++) {
            swap(nums, i, left);
            permuteV(nums, left + 1, right);
            swap(nums, i, left);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> permuteIV(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        backTrack(res, nums, new ArrayList<>());

        return res;

    }

    public void backTrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (temp.contains(nums[i])) {
                    continue;
                }
                temp.add(nums[i]);
                backTrack(res, nums, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
