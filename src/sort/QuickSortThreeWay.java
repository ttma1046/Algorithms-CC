public class QuickSortThreeWay {
	public void sort(int[] a) {
		// StdRandom.shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}

	public void sort(int[] array, int lo, int hi) {
		// See page 289 for public sort() that calls this method.
		if (hi <= lo) return;

		int lt = lo;
		int gt = hi;

		int i = lo + 1;

		int pivot = array[lo];

		while (i <= gt) {
			if (array[i] < pivot) { 
				exchange(array, lt++, i++); 
			} else if (array[i] > povit) { 
				exchange(array, i, gt--); 
			} else { 
				i++; 
			}
		}
		// Now array[lo..lt-1] < v = array[lt..gt] < array[gt+1..hi].
		sort(array, lo, lt - 1);
		sort(array, gt + 1, hi);
	}

	private void exchange(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {

	}
}