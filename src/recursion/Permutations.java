package recursion;

import java.util.ArrayList;
import java.util.List;

class Permutations {
  /*
    array: {1,2,3}
    1
    newArray: {2,3}
    newPermutation: {1}
    permutations: {{}}
        array: {2,3}
        2
        newArray:{3}
        newPermutation: {1,2}
        permutations: {{}}
            array:{3}
            3
            newAarray;{},
            newPermutation: {1,2,3}
            permutations: {{}}
            permutations: {{1,2,3}}
        3
        newArray:{2}
        newPermutation: {1,3}
        permutations: {{1,2,3}}
            array: {2}
            2
            newArray:{}
            newPermutation: {1,3,2}
            permutations: {{1,2,3}}
            permutations: {{1,2,3},{1,3,2}}
    2
    newArray: {1,3}
    newPermutation: {2}
    permutations: {{1,2,3},{1,3,2}}
        array: {1,3}
        1
        newArray: {3}
        newPermutation: {2,1}
        permutations: {{1,2,3},{1,3,2}}
            array: {3}
            3
            newArray: {}
            newPermutation: {2,1,3}
            permutations: {{1,2,3},{1,3,2}}
            permutations: {{1,2,3},{1,3,2},{2,1,3}}
        3
        newArray: {1}
        newPermutation: {2,3}
        permutations: {{1,2,3},{1,3,2}}
            array: {1}
            1
            newArray: {}
            newPermutation: {2,3,1}
            permutations: {{1,2,3},{1,3,2},{2,1,3}}
            permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
    3
    newArray: {1,2}
    newPermutation: {3}
    permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
        array: {1,2}
        1
        newArray: {2}
        newPermutation: {3,1}
        permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
            array: {2}
            2
            newArray: {}
            newPermutation: {3,1,2}
            permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1}}
            permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
        2
        newArray: {1}
        newPermutation: {3,2}
        permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
            array: {1}
            1
            newArray: {}
            newPermutation: {3,2,1}
            permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2}}
            permutations: {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}}
    */

    public static ArrayList<ArrayList<Integer>> getPermutationsII(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        getPermutationsII(array, 0, permutations);
        return permutations;
    }

    public static void getPermutationsII(ArrayList<Integer> array, int i, ArrayList<ArrayList<Integer>> permutations) {
        if (i == array.size() - 1) {
            permutations.add(new ArrayList<Integer>(array));
        } else {
            for (int j = i; j < array.size(); j++) {
                swap(array, i, j);
                getPermutationsII(array, i + 1, permutations);
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
        ArrayList<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int num : nums) {
            array.add(num);
        }
        getPermutations(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }

    // Upper Bound: O(n^2*n!) time | O(n*n!) space
    // Roughly: O(n*n!) time | O(n*n!) space
    public static void getPermutations(ArrayList<Integer> array, ArrayList<Integer> currentPermutation,
            ArrayList<List<Integer>> permutations) {
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

    public List<List<Integer>> permuteIII(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        permutateArrIII(nums, 0, result);
        return result;
    }
    
    private void permutateArrIII(int[] nums, int n, List<List<Integer>> result){
        if (n == nums.length - 1){
            ArrayList<Integer> arrList = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++){
                arrList.add(nums[i]);
            }
            result.add(arrList);
        }

        for (int i = n; i < nums.length; i++){
            swap(nums, n, i);
            permutateArrIII(nums, n + 1, result);
            swap(nums, n, i);
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    nums: [1,2,3]
    n: 0
    result: []

    i: 0,1,2
    0
    swap(0, 0)
    nums: [1,2,3]

        nums: [1,2,3]
        n: 1
        result: []

        i: 1,2
        1
        swap(1, 1)
        nums: [1,2,3]

            nums: [1,2,3]
            n: 2
            result: []

            result: [[1,2,3]]
        swap(1, 1)
        nums:  [1,2,3]

        2
        swap(1, 2)
        nums: [1,3,2]

            nums: [1,3,2]
            n: 2
            result: [[1,2,3]]

            result: [[1,2,3],[1,3,2]]
        swap(1, 2)
        nums: [1,2,3]

    swap(0, 0)
    nums: [1,2,3]

    1
    swap(0, 1)
    nums: [2,1,3]

        nums: [2,1,3]
        n: 1
        result: [[1,2,3],[1,3,2]]

        i: 1,2
        1
        swap(1, 1)
        nums: [2,1,3]

            nums: [2,1,3]
            n: 2
            result: [[1,2,3],[1,3,2]]
            
            result: [[1,2,3],[1,3,2],[2,1,3]]
        swap(1, 1)
        nums:  [2,1,3]

        2
        swap(1, 2)
        nums: [2,3,1]

            nums: [2,3,1]
            n: 2
            result: [[1,2,3],[1,3,2],[2,1,3]]

            result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]
        swap(1, 2)
        nums: [2,1,3]

    swap(1, 0)
    nums: [1,2,3]

    2
    swap(0, 2)
    nums: [3,2,1]

        nums: [3,2,1]
        n: 1
        result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]

        i: 1,2
        1
        swap(1, 1)
        nums: [3,2,1]

            nums: [3,2,1]
            n: 2
            result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
            
            result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1]]
        swap(1, 1)
        nums: [3,2,1]

        2
        swap(1, 2)
        nums: [3,1,2]

            nums: [3,1,2]
            n: 2
            result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1]]

            result: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1],[3,1,2]]
        swap(1, 2)
        nums: [3,2,1]

    swap(2, 0)
    nums: [1,2,3]
    */
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

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permuteIV(new int[] { 1, 2, 3 });

        for (List<Integer> item : result) {
            for (Integer num : item) {
                System.out.print(num);
            }
            System.out.println("");
        }
    }

    /*
    nums: 123
    temp: []
    1
    temp: [1]
    res: [[]]
        
        nums: 123
        temp: [1]
        1
        2
        temp: [1,2]
        res: [[]]

            nums: 123
            temp: [1,2]
            1
            2
            3
            temp: [1,2,3]
            res: [[]]

                nums:123
                temp: [1,2,3]
                res: [[1,2,3]]

            temp: [1,2]
        temp: [1]

        3
        temp: [1,3]
        res: [[1,2,3]]

            nums: 123
            temp: [1,3]
            1
            2
            temp: [1,3,2]
            res: [[1.2.3]]

                nums: 123
                temp: [1,3,2]
                res: [[1,2,3],[1,3,2]]

            temp: [1,3]
            3
        temp: [1]
    temp[]
    2
    temp[2]
    res: [[1,2,3],[1,3,2]]

        nums: 123
        temp: [2]
        1
        temp: [2,1]
        res: [[1,2,3],[1,3,2]]

            nums: 123
            temp: [2,1]
            1
            2
            3
            temp: [2,1,3]
            res: [[1,2,3],[1,3,2]]

                nums: 123
                temp: [2,1,3]
                res: [[1,2,3],[1,3,2],[2,1,3]]

            temp: [2,1]
        temp: [2]
        2
        3
        temp: [2,3]
        res: [[1,2,3],[1,3,2],[2,1,3]]

            nums: 123
            temp: [2,3]
            1
            temp: [2,3,1]
            res: [[1,2,3],[1,3,2],[2,1,3]]

                nums: 123
                temp: [2,3,1]
                res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]

            temp: [2,3]
            2
            3
        temp: [2]
    temp: []
    3
    temp: [3]
    res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]

        nums: 123
        temp: [3]
        1
        temp: [3,1]
        res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]        

            nums: 123
            temp: [3,1]
            1
            2
            temp: [3,1,2]
            res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]

                nums: 123
                temp: [3,1,2]
                res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]

            temp: [3,1]
            3
        temp:[3]
        2
        temp: [3,2]
        res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]

            nums: 123
            temp: [3,2]
            1
            temp: [3,2,1]
            res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]

                nums: 123
                temp: [3,2,1]
                res: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

            temp: [3,2]
            2
            3
        temp: [3]
        3
    temp: []
    */
}
