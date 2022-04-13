
package array;

import java.util.List;
import java.util.ArrayList;


/*
You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the smallest sorted list of ranges that cover every missing number exactly.

That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b

Example 1:

Input: nums = [0,1,3,50,75], lower = 0, upper = 99
Output: ["2","4->49","51->74","76->99"]
Explanation: The ranges are:
[2,2] --> "2"
[4,49] --> "4->49"
[51,74] --> "51->74"
[76,99] --> "76->99"

Example 2:

Input: nums = [-1], lower = -1, upper = -1
Output: []
Explanation: There are no missing ranges since there are no missing numbers.

Constraints:

-109 <= lower <= upper <= 109
0 <= nums.length <= 100
lower <= nums[i] <= upper
All the values of nums are unique.
*/
class Missing_Ranges_163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();

        int prev = lower - 1;

        for (int i = 0; i <= nums.length; ++i) {
            int curr = i < nums.length ? nums[i] : upper + 1;

            if (prev + 1 <= curr - 1)
                res.add(formatStr(prev + 1, curr - 1));

            prev = curr;
        }

        return res;
    }

    // formats range in the requested format
    private String formatRange(int lower, int upper) {
        if (lower == upper)
            return String.valueOf(lower);

        return lower + "->" + upper;
    }

    public static void main(String[] args) {
        Missing_Ranges_163 obj = new Missing_Ranges_163();
        int[] nums = new int[] {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        List<String> abc = obj.findMissingRanges(nums, lower, upper);

        for (String res : abc) {
            System.out.println(res);
        }
    }
    /*
    Complexity Analysis
        Let N be the length of the input array.

        Time complexity : O(N).

        This is because we are only iterating over the array once, and at each step, 
        we're performing O(1) operations. We treat the string building as O(1) because the strings can never be more than a fixed size.

        Space complexity : O(1).

        The output list has a worst case size of O(N). 

        This case occurs when we have a missing range between each of the consecutive elements in the input array 

        (for example, if the input array contains all even numbers between lower and upper). 

        We aren't using any other additional space, beyond fixed-sized constants that don't grow with the size of the input.

        However, output space that is simply used to return the output (and not to do any processing) 

        is not counted for the purpose of space complexity analysis. 

        For this reason, the overall space complexity is O(1).
    */
    private String formatStr(int lower, int upper) {
        if (lower == upper)
            return String.valueOf(lower);

        return lower + "->" + upper;
    }
}