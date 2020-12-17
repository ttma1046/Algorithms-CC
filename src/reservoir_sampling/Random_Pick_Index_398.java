package reservoir_sampling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
/*
Given an array of integers with possible duplicates,
randomly output the index of a given target number.

You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/
class Random_Pick_Index_398 {
	private int[] nums;
	private Random rand;


	public Random_Pick_Index_398(int[] target) {
		this.nums = target;
		this.rand = new Random();
	}

	public int pick(int target) {
		int length = this.nums.length;

		int count = 0;
		int idx = 0;

		for (int i = 0; i < length; ++i) {
			if (this.nums[i] == target) {
				count++;

				if (rand.nextInt(count) == 0) {
					idx = i;
				}
			}
		}

		return idx;
	}

	public static void main(String[] args) {

	}
}

class Random_Pick_Index_398_II {
	private Map<Integer, List<Integer>> map;
	private Random rand;

	public Random_Pick_Index_398_II(int[] nums) {
		this.rand = new Random();
		this.map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (!this.map.containsKey(nums[i])) {
				this.map.put(nums[i], new ArrayList<Integer>());
			}

			this.map.get(nums[i]).add(i);
		}
	}

	public int pick(int target) {
		int l = map.get(target).size();

		return map.get(target).get(rand.nextInt(l));
	}

	public static void main(String[] args) {

	}
}
