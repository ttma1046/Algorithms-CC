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

    private int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            swap(arr, minIndex, i);
        }

        return arr;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {38, 27, 43, 3, 9, 82, 10};

        int[] res = obj.selectionSort(array);

        for (int i : res)
            System.out.println(i);
    }

    // time complexity: O(n^2)
    // space complexity: constant O(1)
}