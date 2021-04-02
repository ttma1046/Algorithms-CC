package sort;

class SelectionSort {
	public void sort(int[] array) {
		int N = array.length;

		for (int i = 0; i < N; i++) {
			int minIndex = i;

			for (int j = i + 1; j < N; j++) 
				if (array[j] < array[minIndex]) 
					minIndex = j;

			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}

	public void sortII(int[] a) {
		// Sort a[] into increasing order.
		int N = a.length; // array length

		for (int i = 0; i < N; i++) {
			// Exchange a[i] with smallest entry in a[i+1...N).
			int minIndex = i; // index of minimal entr.
			for (int j = i + 1; j < N; j++)
				if (less(a[j], a[minIndex])) 
					minIndex = j;

			exch(a, i, minIndex);
		}
	}

	private boolean less(int v, int w) {
		return v < w;
	}

	private void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
        int[] array = new int[] {38, 27, 43, 3, 9, 82, 10};

		new SelectionSort().sort(array);

		for (int i: array) {
			System.out.println(i);
		}
	}
}