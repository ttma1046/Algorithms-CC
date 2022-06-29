package sort;

public class InsertionSort {
	int[] insertionSortI(int[] nums) {
		int N = nums.length;

		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && nums[j - 1] > nums[j]; j--)
				swap(nums, j, j - 1);
		}

		return nums;
	}

	void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	void insertionSort(int[] nums) {
		int N = nums.length;

		for (int i = 0; i < N; i++) {
			int temp = nums[i];	
			int j = i;
			while(j > 0 && nums[j - 1] > temp) {
				nums[j] = nums[j - 1];
				j--;
			}

			nums[j] = temp;
		}
	}

	public static void main(String[] args) {
		int[] array = new int[] {38, 27, 43, 3, 9, 82, 10};

		new InsertionSort().insertionSortI(array);

		for (int i : array) {
			System.out.println(i);
		}
	}
}