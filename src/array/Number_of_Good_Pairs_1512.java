package array;
import java.util.Arrays;
class Number_of_Good_Pairs_1512 {
	public int numIdenticalPairsI(int[] nums) {
		if (nums != null && nums.length <= 0) {
			return 0;
		}

		int result = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == nums[j]) {
					result++;
				}
			}
		}

		return result;
	}

	public int numIdenticalPairsII(int[] nums) {
		if (nums != null && nums.length <= 0) {
			return 0;
		}

		Arrays.sort(nums);

		int result = 0;
		int n = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				if (n == 0) {
					n = 2;
				} else {
					n++;
				}
			} else {
				result += n * (n - 1) / 2;
				n = 0;
			}
		}

		if (n > 0) {
			result += n * (n - 1) / 2;
		}

		return result;
	}

	public int numIdenticalPairsIII(int[] nums) {
		if (nums != null && nums.length <= 0) {
			return 0;
		}

		int[] hash = new int[101];
		int result = 0;
		for (int i : nums) {
			result += hash[i]++;
		}

		return result;
	}

	public int numIdenticalPairs(int[] nums) {
		int result = 0, hash[] = new int[101];
		for (int item : nums) {
			result += hash[item]++;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsI(new int[] { 1, 2, 3, 1, 1, 3 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsI(new int[] { 1, 1, 1, 1 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsI(new int[] { 1, 2, 3 }));


		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsII(new int[] { 1, 2, 3, 1, 1, 3 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsII(new int[] { 1, 1, 1, 1 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsII(new int[] { 1, 2, 3 }));

		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsIII(new int[] { 1, 2, 3, 1, 1, 3 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsIII(new int[] { 1, 1, 1, 1 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairsIII(new int[] { 1, 2, 3 }));

		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairs(new int[] { 1, 2, 3, 1, 1, 3 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairs(new int[] { 1, 1, 1, 1 }));
		System.out.println(new Number_of_Good_Pairs_1512().numIdenticalPairs(new int[] { 1, 2, 3 }));
	}
}