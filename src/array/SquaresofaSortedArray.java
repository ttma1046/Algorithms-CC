package array;
class SquaresofaSortedArray {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }

        for (int i = 0; i < A.length; i++) {
            A[i] *= A[i];
        }

        quicksort(A, 0, A.length - 1);

        return A;
    }

    public int[] sortedSquaresII(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        int i = 0, j = n - 1;
        for (int p = n - 1; p >= 0; p--) {
            if (Math.abs(A[i]) > Math.abs(A[j])) {
                result[p] = A[i] * A[i];
                i++;
            } else {
                result[p] = A[j] * A[j];
                j--;
            }
        }
        return result;
    }

    private void quicksort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = array[left + (right - left) / 2];

        int index = partition(array, left, right, pivot);

        quicksort(array, left, index - 1);

        quicksort(array, index, right);
    }

    private int partition(int[] array, int left, int right, int pivot) {
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}