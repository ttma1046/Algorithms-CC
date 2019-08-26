package resursion;

import java.util.*;

public class PowersetsII {
    public static void main(String[] args) {
        System.out.println(new PowersetsII().subsetsWithDupIte(new int[]{1, 2, 2}));

        // System.out.println(new PowersetsII().subsetsWithDupRec(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDupIte(int[] nums) {
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
        List<List<Integer>> result = new ArrayList();
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

    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums, int position, boolean choosePre) {
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
        System.out.println(new SolutionI().subsetsWithDup(new int[]{1, 2, 2}));

        // System.out.println(new PowersetsII().subsetsWithDupRec(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), nums,0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums, int position) {
        System.out.println("result before:" + result);
        result.add(new ArrayList<>(list));
        System.out.println("result after:" + result);

        for(int i = position; i < nums.length; i++) {
            System.out.println("position:" + position);
            System.out.println("i:" + i);
            if(i > position && nums[i] == nums[i - 1]) {
                continue;
            }

            list.add(nums[i]);
            System.out.println("list add:" + list);
            helper(result, list, nums,i + 1);
            list.remove(list.size() - 1);
            System.out.println("list remove:" + list);
        }
    }
}

class SolutionDfs {
    public static void main(String[] args) {
        // System.out.println(new SolutionDfs().subsetsWithDupRec(new int[]{1, 2, 2}));

        System.out.println(new SolutionDfs().subsetsWithDup(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<List<Integer>>();
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> result){
        System.out.println("DFS starts by index:" + index);
        System.out.println("path:" + path);
        System.out.println("result before:" + result);
        result.add(path);
        System.out.println("result after:" + result);


        System.out.println("FOR starts by index:" + index);
        for(int i = index; i < nums.length; i++) {
            System.out.println("index:" + index);
            System.out.println("i:" + i);

            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            List<Integer> nPath= new ArrayList<Integer>(path);
            System.out.println("nPath Before:" + nPath);

            nPath.add(nums[i]);
            System.out.println("nPath After:" + nPath);
            dfs(nums, i + 1, nPath, result);
        }
        System.out.println("FOR ends by index:" + index);
        System.out.println("DFS ends by index:" + index);
    }
}