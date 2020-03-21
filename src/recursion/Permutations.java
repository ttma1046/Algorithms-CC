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

    public static void getPermutations(ArrayList<Integer> array, ArrayList<Integer> currentPermutation, ArrayList<ArrayList<Integer>> permutations) {
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

    public static ArrayList<ArrayList<Integer>> getPermutationsTwo(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        getPermutations(0, array, permutations);
        return permutations;
    }

    public static void getPermutations(int i, ArrayList<Integer> array, ArrayList<ArrayList<Integer>> permutations) {
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

    public List<List<Integer>> permute(int[] nums)     {
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int num: nums) {
            array.add(num);
        }
        getPermutationsII(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }

    public static void getPermutationsII(ArrayList<Integer> array, List<Integer> currentPermutation, List<List<Integer>> permutations) {
        if (array.size() == 0 && currentPermutation.size() > 0) {
            permutations.add(currentPermutation);
        } else {
            for (int i = 0; i < array.size(); i++) {
                ArrayList<Integer> newArray = new ArrayList<Integer>(array);
                newArray.remove(i);
                List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(array.get(i));
                getPermutationsII(newArray, newPermutation, permutations);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int [] {1, 2, 3});

        for (List<Integer> item: result) {
            for(Integer num: item) {
                System.out.println(num);
            }
        }
    }




}
