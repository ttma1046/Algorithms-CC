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
        int n = nums.size();

        Map<Integer, LinkedList<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            List<Integer> numList = nums.get(i);

            for (int j = 0; j < numList.size(); j++) {
                int index = i + j;

                LinkedList<Integer> list = map.get(index);

                if(list == null)
                    list = new LinkedList<>();

                list.addFirst(numList.get(j));

                map.put(index, list);
            }
        }

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

        int maxLen = Collections.max(map.keySet());

        List<Integer> resultList = new ArrayList<Integer>();

        for(int i = 0; i <= maxLen; i++) {
            List<Integer> diagValue = map.get(i);
            resultList.addAll(diagValue);
        }

        int[] res = new int[resultList.size()];

        for(int i = 0; i < res.length; i++)
            res[i] = resultList.get(i);

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