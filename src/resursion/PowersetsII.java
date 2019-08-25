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


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<Integer>(),result);
        return result;
    }

    public void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){
        result.add(path);
        for(int i=index;i<nums.length;i++){
            if(i>index&&nums[i]==nums[i-1]) continue;
            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i+1,nPath,result);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
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

// Each recursion level focuses on all the following elements.
// We scan through all the following elements and decide whether to choose or not choose that element.
// (Every level split into N branches.)
class SolutionI {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for(int i=pos;i<nums.length;i++) {
            if(i>pos&&nums[i]==nums[i-1]) continue;
            ls.add(nums[i]);
            helper(res,ls,nums,i+1);
            ls.remove(ls.size()-1);
        }
    }
}

// Each recursion level focuses on one element, we need to decide choose or not choose this element.
// (Every level split into 2 branches.)
class SolutionII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0,false);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos, boolean choosePre) {
        if(pos==nums.length) {
            res.add(new ArrayList<>(ls));
            return;
        }
        helper(res,ls,nums,pos+1,false);
        if(pos>=1&&nums[pos]==nums[pos-1]&&!choosePre) return;
        ls.add(nums[pos]);
        helper(res,ls,nums,pos+1,true);
        ls.remove(ls.size()-1);
    }
}