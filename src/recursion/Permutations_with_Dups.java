package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
    public List<List<Integer>> permute(int[] nums)     {
        ArrayList<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int num: nums) {
            arrayList.add(num);
        }

        HashMap<Integer, Integer> map = buildFreqTable(arrayList);

        getPermutations(map, new ArrayList<Integer>(), arrayList.size(), permutations);
        return permutations;
    }

    private HashMap<Integer, Integer> buildFreqTable(ArrayList<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int item: nums) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        return map;
    }

    private void getPermutations(HashMap<Integer, Integer> map, ArrayList<Integer> perfix, int remaining, ArrayList<List<Integer>> permutations) {
        if (remaining == 0) {
            permutations.add(perfix);
        } else {
            for(int item: map.keySet()) {
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
    
    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations_with_Dups().permute(new int [] {1, 1, 3});

        for (List<Integer> item: result) {
            for(Integer num: item) {
                System.out.println(num);
            }
        }
    }
}