package resursion;

import java.util.*;

public class PowersetsII {
    public static void main(String[] args) {
        System.out.println(new PowersetsII().subsetsWithDupIte(new int[]{1, 2, 2}));

        // System.out.println(new PowersetsII().subsetsWithDupRec(new int[]{1, 2, 2}));
    }

    private List<List<Integer>> subsetsWithDupIte(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        int base = 0;

        for (int j = 0; j < nums.length; j++) {
            if (j == 0 || nums[j - 1] != nums[j]) {
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


    public List<List<Integer>> subsetsWithDupRec(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDupHelper(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int position, List<List<Integer>> result, List<Integer> tmpResult) {
        // subset means it does not need contain all elements, so the condition is <= rather than ==
        // and do not return after this statement
        if(position <= nums.length) result.add(new ArrayList<>(tmpResult));

        for(int i = position; i < nums.length; i++) {
            if(i > position && nums[i] == nums[i - 1]) continue;   // avoid duplicates
            tmpResult.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, result, tmpResult);
            tmpResult.remove(tmpResult.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDupTwo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        recurse(nums, 0, set, result);
        return result;
    }

    private int recurse(int[] nums, int index, Set<Integer> set, List<List<Integer>> result) {
        // base case
        if (index == nums.length) {
            result.add(new LinkedList<Integer>());
            return 1;
        }

        // recursive case
        int delta = recurse(nums, index + 1, set, result);
        int finalSize = result.size();

        if (set.contains(nums[index])) {
            for (int i = finalSize - delta; i < finalSize; i ++) {
                LinkedList<Integer> newList = new LinkedList<>(result.get(i));
                newList.addFirst(nums[index]);
                result.add(newList);
            }
            return delta;
        }

        set.add(nums[index]);
        for (int i = 0; i < finalSize; i ++) {
            LinkedList<Integer> newList = new LinkedList<>(result.get(i));
            newList.addFirst(nums[index]);
            result.add(newList);
        }
        return finalSize;
    }
}

// Each recursion level focuses on one element, we need to decide choose or not choose this element.
// (Every level split into 2 branches.)
class SolutionII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), nums,0,false);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> list, int[] nums, int position, boolean choosePre) {
        if(position == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        helper(result, list, nums,position + 1,false);
        if(position >= 1 && nums[position] == nums[position - 1] && !choosePre) return;
        list.add(nums[position]);
        helper(result, list, nums,position + 1,true);
        list.remove(list.size() - 1);
    }
}

// Each recursion level focuses on all the following elements.
// We scan through all the following elements and decide whether to choose or not choose that element.
// (Every level split into N branches.)
class SolutionI {
    public static void main(String[] args) {
        System.out.println(new SolutionI().subsetsWithDup(new int[]{44, 55, 66}));

        // System.out.println(new PowersetsII().subsetsWithDupRec(new int[]{1, 2, 2}));
    }

    private List<List<Integer>> subsetsWithDup(int[] numbs) {
        Arrays.sort(numbs);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), numbs,0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> tempList, int[] numbs, int index) {
        System.out.println("Recursion starts by index:" + index);
        System.out.println("temp list:" + tempList);

        System.out.println("result before:" + result);
        result.add(new ArrayList<>(tempList));
        System.out.println("result after:" + result);

        System.out.println("FOR starts by index:" + index);
        for(int i = index; i < numbs.length; i++) {
            System.out.println("index:" + index);
            System.out.println("i:" + i);
            if(i > index && numbs[i] == numbs[i - 1]) {
                continue;
            }

            System.out.println("temp list before add:" + tempList);
            tempList.add(numbs[i]);
            System.out.println("temp list after add:" + tempList);
            helper(result, tempList, numbs,i + 1);

            System.out.println("temp list before remove:" + tempList);
            tempList.remove(tempList.size() - 1);
            System.out.println("temp list remove:" + tempList);
        }
        System.out.println("FOR ends by index:" + index);
        System.out.println("Recursion ends by index:" + index);
    }
}

class SolutionDfs {
    public static void main(String[] args) {
        // System.out.println(new SolutionDfs().subsetsWithDupRec(new int[]{1, 2, 2}));

        System.out.println(new SolutionDfs().subsetsWithDup(new int[]{1, 2, 2}));
    }

    private List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<List<Integer>>();
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void dfs(int[] numbs, int index, List<Integer> path, List<List<Integer>> result){
        System.out.println("DFS starts by index:" + index);
        System.out.println("path:" + path);
        System.out.println("result before:" + result);
        result.add(path);
        System.out.println("result after:" + result);


        System.out.println("FOR starts by index:" + index);
        for(int i = index; i < numbs.length; i++) {
            System.out.println("index:" + index);
            System.out.println("i:" + i);

            if (i > index && numbs[i] == numbs[i - 1]) {
                continue;
            }

            List<Integer> nPath= new ArrayList<Integer>(path);
            System.out.println("nPath Before:" + nPath);

            nPath.add(numbs[i]);
            System.out.println("nPath After:" + nPath);
            dfs(numbs, i + 1, nPath, result);
        }
        System.out.println("FOR ends by index:" + index);
        System.out.println("DFS ends by index:" + index);
    }
}

class MySolution {
    public static void main(String[] args) {
        // System.out.println(new SolutionDfs().subsetsWithDupRec(new int[]{1, 2, 2}));

        System.out.println(new MySolution().subsetsWithDup(new int[]{1, 2, 3}));
    }

    private List<List<Integer>> subsetsWithDup(int[] numbs) {
        if (numbs == null || numbs.length <= 0) {
            return null;
        }

        Arrays.sort(numbs);

        List<List<Integer>> result = new  ArrayList<List<Integer>>();
        subsetsWithDupRecur(result, new ArrayList<Integer>(), numbs, 0);
        return result;

        /*
        ArrayList<List<Integer>> result = new ArrayList();
        result.add(new ArrayList<Integer>());

        int base = 0;
        for(int j = 0; j < numbs.length; j++) {
            if (j == 0 || numbs[j] != numbs[j - 1]) {
                base = result.size();
            }

            int length = result.size();
            for(int i = length - base;i < length;i++) {
                List<Integer> temp = new ArrayList<Integer>(result.get(i));
                temp.add(numbs[j]);
                result.add(temp);
            }
        }

        return result;
        */
    }

    private void subsetsWithDupRecur(List<List<Integer>> result, List<Integer> temp, int[] numbs, int index) {
        result.add(new ArrayList<Integer>(temp));

        for (int i = index; i < numbs.length; i++) {
            temp.add(numbs[i]);
            subsetsWithDupRecur(result, temp, numbs, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

/*
    Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

        Note: The solution set must not contain duplicate subsets.

        Example:

        Input: [1,2,2]
        Output:
        [[2], [1], [1,2,2], [2,2], [1,2], [] ]
*/

class MySolutionII {
    public static void main(String [] args) {
        System.out.println(new MySolutionII().subsetsWithDupRecur(new int [] {1, 2, 3}));
        System.out.println(new MySolutionII().subsetsWithDupRecur(new int [] {1, 2, 2}));
        System.out.println(new MySolutionII().subsetsWithDupIte(new int [] {1, 2, 3}));
        System.out.println(new MySolutionII().subsetsWithDupIte(new int [] {1, 2, 2}));
    }

    private List<List<Integer>> subsetsWithDupIte(int [] numbs) {
        if (numbs == null || numbs.length <= 0) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        int base = 0;
        for (int i = 0; i < numbs.length; i++) {

            if (i == 0 || numbs[i] != numbs[i - 1]) {
                base = result.size();
            }

            int length = result.size();
            for (int j = length - base; j < length; j++) {
                List<Integer> temp = new ArrayList<Integer>(result.get(j));
                temp.add(numbs[i]);
                result.add(temp);
            }
        }

        return result;
    }

    private List<List<Integer>> subsetsWithDupRecur(int [] numbs) {
        if (numbs == null || numbs.length <= 0) {
            return null;
        }

        Arrays.sort(numbs);
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        subsetsWithDupRecur(result, new ArrayList<Integer>(), numbs, 0);
        return result;
    }

    private void subsetsWithDupRecur(List<List<Integer>> result , List<Integer> tempList ,int [] numbs, int index) {
        result.add(new ArrayList<Integer>(tempList));

        for (int i = index; i < numbs.length; i++) {
            if (i > index && numbs[i] == numbs[i - 1]) {
                continue;
            }

            tempList.add(numbs[i]);
            subsetsWithDupRecur(result, tempList, numbs, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}