package sort;

public class InsertionSort {
	public static void sort(int[] array) {
		// Sort a[] into increasing order.
		int N = array.length;
		for (int i = 1; i < N; i++) {
			// Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
				int temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;

				/*
				for (int k : array) {
					System.out.print(k);
					System.out.print(",");

				}
				System.out.println();
				*/
			}
		}
	}

	public static void main(String[] args) {
		int[] array = new int[] {38, 27, 43, 3, 9, 82, 10};

		new InsertionSort().sort(array);

		for (int i : array) {
			System.out.println(i);
		}
	}
}




for (int i = 1; i < N; i++) {
	for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
		int temp = array[j - 1];
		array[j - 1] = array[j];
		array[j - 1] = temp;
	}
}