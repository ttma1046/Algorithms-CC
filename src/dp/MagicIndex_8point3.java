package dp;

class MagicIndex_8point3 {
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

	int FindMagicIndexII(int[] array) {
		if (array == null) {
			return -1;
		}

		return FindMagicIndexII(array, 0, array.length - 1);
	}

	int FindMagicIndexII(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}

		int mid = start + (end - start) / 2;

		if (array[mid] == mid) {
			return mid;
		}

		if (array[mid] > mid) {
			return FindMagicIndexII(array, start, mid - 1);
		}

		if (array[mid] < mid) {
			return FindMagicIndexII(array, mid + 1, end);
		}

		return -1;
	}

	int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}

	int magicFast(int[] array, int start, int end) {
		if (end < start)
			return -1;

		int midIndex = (start + end) / 2;
		int midValue = array[midIndex];

		if (midValue == midIndex) {
			return midIndex;
		}

		/* Search left */
		int leftIndex = Math.min(midIndex - 1, midValue);
		int left = magicFast(array, start, leftIndex);

		if (left >= 0) {
			return left;
		}

		/* Search right */
		int rightIndex = Math.max(midIndex + 1, midValue);
		int right = magicFast(array, rightIndex, end);

		return right;
	}

}