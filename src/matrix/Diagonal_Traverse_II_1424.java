package matrix;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import java.util.stream.Collectors;
import java.util.Collections;


/*
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:

Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:

Input: nums = [
   [1,2,3,4,5],
   [6,7],
   [8],
   [9,10,11],
   [12,13,14,15,16]
  ]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

Constraints:

1 <= nums.length <= 105
1 <= nums[i].length <= 105
1 <= sum(nums[i].length) <= 105
1 <= nums[i][j] <= 105
*/
class Diagonal_Traverse_II_1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        /*
        [1,2,3,4,5],
        [6,7],
        [8],
        [9,10,11],
        [12,13,14,15,16]

        0 -> [1]

        1 -> [2]

        2 -> [3]

        3 -> [4]

        4 -> [5]

        1 -> [6,2]

        2 -> [8,7,4]

        3 -> [9,4]

        4 -> [12, 10, 5]

        5 -> [13, 11]

        6 -> [14]

        7 -> [15]

        8 -> [16]
        */

        Map<Integer, LinkedList<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.size(); ++i) {
            List<Integer> list = nums.get(i);

            for (int j = 0; j < list.size(); ++j) {
                int index = i + j;

                if (!map.containsKey(index))
                    map.put(index, new LinkedList<Integer>());

                LinkedList<Integer> k = map.get(index);

                k.addFirst(list.get(j));

                map.put(index, k);
            }
        }

        int maxLen = Collections.max(map.keySet());
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < maxLen; i++)
            resList.addAll(map.get(i));

        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++)
            res[i] = resList.get(i);

        return res;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int count = 0;
        int maxKey = 0;

        for(int i = 0; i < nums.size(); i++)
            for(int j = 0; j < nums.get(i).size(); j++) {
                map.computeIfAbsent(i + j, x -> new LinkedList<>()).addFirst(nums.get(i).get(j));//add at starting
                count++;
                maxKey = Math.max(maxKey, i + j);//store max key
            }

        int[] res = new int[count];
        count = 0;

        for(int i = 0; i <= maxKey; i++)
            for(int e : map.get(i))
                res[count++] = e;//populate the result array

        return res;
    }

    public static void main(String[] args) {
        Diagonal_Traverse_II_1424 obj = new Diagonal_Traverse_II_1424();

        int[] a = new int[] {1, 2, 3, 4, 5};
        int[] b = new int[] {6, 7};
        int[] c = new int[] {8};
        int[] d = new int[] {9, 10, 11};
        int[] e = new int[] {12, 13, 14, 15, 16};

        List<List<Integer>> test = new ArrayList<>();
        List<Integer> ap = Arrays.stream(a).boxed().collect(Collectors.toList());
        test.add(ap);

        List<Integer> bp = Arrays.stream(b).boxed().collect(Collectors.toList());
        test.add(bp);

        List<Integer> cp = Arrays.stream(c).boxed().collect(Collectors.toList());
        test.add(cp);

        List<Integer> dp = Arrays.stream(d).boxed().collect(Collectors.toList());
        test.add(dp);

        List<Integer> ep = Arrays.stream(e).boxed().collect(Collectors.toList());
        test.add(ep);

        obj.findDiagonalOrder(test);
    }
}