class Magic_Index {
	int FindMagicIndex(int[] array) {
		if (array == null) {
			return -1;
		}


		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}

		return -1;
	}

	int FindMagicIndex(int[] array) {
		if (array == null) {
			return -1;
		}

		return FindMagicIndex(array, 0, array.length - 1)
	}

	int FindMagicIndex(int[] array, start, end) {
		if (end < start) {
			return -1;
		}

		int mid = start + (end - start) / 2;

		if (array[mid] == mid) {
			return mid;
		}

		if (array[mid] > mid) {
			return FindMagicIndex(array, start, mid - 1);
		}

		if (array[mid] < mid) {
			return FindMagicIndex(array, mid + 1, end);
		}

		return - 1;
	}

}