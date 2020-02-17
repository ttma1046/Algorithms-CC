class MagicIndex_8point3 {
    int magicFast(int[] array) {
        return magicFast(array, 0, array.length - 1);
    }

    int magicFast(int[] array, int start, int end) {
        if (end < start) return -1;

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