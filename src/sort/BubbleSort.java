package sort;

public class BubbleSort {
    public static void bubblesort(int[] array) {
        if (array == null) {
            return;
        }

        boolean isSorted = false;

        int lastUnsorted = array.length - 1;
        while (!isSorted) {
            isSorted = true;

            for (int i = 0; i < lastUnsorted; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    isSorted = false;
                }
            }

            lastUnsorted--;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = array[i];
    }

    public static void bubblesort(int[] array) {
        int unSortedlength = array.length - 1;
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int j = 0; j < unSortedlength; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = array[j];
                    
                    isSorted = false;
                }

                --unSortedlength;
            }
        }

        return array;
        /*
        int l = array.length;
        for(int i = 0; i < l; ++i) {
            for (int j = 0; j < l - 1 - i; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = array[j];
                }
            }
        }
        */
    }
}
