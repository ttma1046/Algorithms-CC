package sort;

class Shell_Sort {
	public void shellSort(int arr[]) {
		int N = arr.length;

		for (int gap = N / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < N; i += 1) {
				int temp = arr[i];
				int j = i;

				while (arr[j - gap] > temp) {
					arr[j] = arr[j - gap];
					j -= gap;
				}

				arr[j] = temp;
			}
		}
	}
}