/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5

*/

package greedy;

class Jump_Game_55 {
	public boolean canJumpFromPosition(int position, int[] nums) {
		if (position == nums.length - 1) {
			return true;
		}

		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
		// for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
			if (canJumpFromPosition(nextPosition, nums)) {
				return true;
			}
		}

		return false;
	}

	public boolean canJumpBackTracking(int[] nums) {
		return canJumpFromPosition(0, nums);
	}

	Index[] memo;

	public boolean canJumpFromPosition(int position, int[] nums) {
		if (memo[position] != Index.UNKNOWN) {
			return memo[position] == Index.GOOD ? true : false;
		}

		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
			if (canJumpFromPosition(nextPosition, nums)) {
				memo[position] = Index.GOOD;
				return true;
			}
		}

		memo[position] = Index.BAD;
		return false;
	}

	public boolean canJumpDpMemoTopBottom(int[] nums) {
		memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;
		return canJumpFromPosition(0, nums);
	}

	enum Index {
		GOOD, BAD, UNKNOWN
	}

	public boolean canJumpDpBottomTop(int[] nums) {
		Index[] memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;

		for (int i = nums.length - 2; i >= 0; i--) {
			int furthestJump = Math.min(i + nums[i], nums.length - 1);
			for (int j = i + 1; j <= furthestJump; j++) {
				if (memo[j] == Index.GOOD) {
					memo[i] = Index.GOOD;
					break;
				}
			}
		}

		return memo[0] == Index.GOOD;
	}

	public boolean canJumpGreedy(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			System.out.println("lastPos:" + lastPos);
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
			System.out.println("i:" + i);

		}
		return lastPos == 0;
	}

	public static void main(String[] args) {
		System.out.println(new Jump_Game_55().canJumpIII(new int[] {3, 2, 1, 0, 4}));
		System.out.println(new Jump_Game_55().canJumpIII(new int[] {2, 3, 1, 1, 4}));
	}
}