package binarysearch;
/*
Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.

Note that the letters wrap around.

For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.

Example 1:
Input: letters = ["c","f","j"], target = "a"
Output: "c"
Example 2:

Input: letters = ["c","f","j"], target = "c"
Output: "f"
Example 3:

Input: letters = ["c","f","j"], target = "d"
Output: "f"
Example 4:

Input: letters = ["c","f","j"], target = "g"
Output: "j"
Example 5:

Input: letters = ["c","f","j"], target = "j"
Output: "c"

Constraints:

2 <= letters.length <= 104
letters[i] is a lowercase English letter.
letters is sorted in non-decreasing order.
letters contains at least two different characters.
target is a lowercase English letter.
*/
class Find_Smallest_Letter_Greater_Than_Target_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return '0';
        int low = 0, high = letters.length, mid = -1;

        while(low < high) {
            mid = low + (high - low) / 2;

            if (letters[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return letters[low % letters.length];
    }

    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;
            // else if(nums[mid] > target) high = mid;
            // else low = mid + 1;
            else if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }       

        if (low != nums.length && nums[low] == target) return low;
        return -1;
    }

    public static void main(String[] args) {
        Find_Smallest_Letter_Greater_Than_Target_744 obj = new Find_Smallest_Letter_Greater_Than_Target_744();

        char[] letters = new char[] {'c','f','j'};

        System.out.println(obj.nextGreatestLetter(letters, 'd'));
    }
}