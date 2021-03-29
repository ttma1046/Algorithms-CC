package sort;

public class BubbleSort {
    /*
    public static void bubbleSort(int[] array) {
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
        array[j] = temp;
    }
    */

    /*
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

    }
    */

    public void bubbleSort(int[] array) {
        boolean isSorted = false;
        int isUnsortedArrayLength = array.length - 1;

        while (!isSorted) {
            isSorted = true;

            for (int i = 0; i < isUnsortedArrayLength; ++i) {
                if (array[i] > array[i + 1]) {
                    array[i] -= array[i + 1];
                    array[i + 1] += array[i];
                    array[i] = array[i + 1] - array[i];

                    isSorted = false;
                }
            }

            --isUnsortedArrayLength;
        }
    }


    public static void main(String[] args) {
        int[] array = new int[] { 4, 2, 7, 10, 8};
        new BubbleSort().bubbleSort(array);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
