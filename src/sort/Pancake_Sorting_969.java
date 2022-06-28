package sort;


class Pancake_Sorting_969 {
	public List<Integer> pancakeSort(int[] A) {
		List<Integer> result = new ArrayList<>();

		int N = A.length, largest = N;

		for (int i = 0; i < n; i++) {
			int index = find(A, largest);
			flip(A, index);
			flip(A, largest - 1);

			result.add(index + 1);
			result.add(largest--);
		}

		return result;
	}

	private int find(int[] A, int target) {
		for (int i = 0; i < A.length; i++) 
			if (A[i] == target)
				return i;

		return -1;
	}

	private void flip(int[] A, int index) {
		int i = 0;
		int j = index;
		while(i < j) {
			int temp = A[i];
			A[i++] = A[j];
			A[j--] = tmp;
		}
	}
}