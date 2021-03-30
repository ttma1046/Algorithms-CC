package sort;

public class MergeSort {
    public void mergesort(int[] array) {
        mergesort(array, new int[array.length], 0, array.length - 1);
    }

    public void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }

        int middle = (leftStart + rightEnd) / 2;
        mergesort(array, temp, leftStart, middle);
        mergesort(array, temp, middle + 1, rightEnd);

        margeHalves(array, temp, leftStart, rightEnd);
    }

    public void margeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        // Walking thru each half of the array
        // and copy the smaller element
        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
            }

            index++;
        }

        // Jump out of the while loop when any half is out of boundary
        // Copy the remaining elements in left/right half into temp

        // arraycopy: copy from array starting from left to temp starting from index,
        // copy (leftEnd -left + 1) elements
        // only one of the two lines will be valid

        // System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(array, left, temp, index, leftEnd - left + 1);

        // System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);

        // System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(temp, leftStart, array, leftStart, size);
    }

    public static void main(String[] args) {
        int[] array = new int[] {38, 27, 43, 3, 9, 82, 10};
        new MergeSort().mergesort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    /*
    public void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);

        for (int i: temp) {
            System.out.println(i);
        }
    }

    private void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(array, temp, left, middle);
        mergeSort(array, temp, middle + 1, right);
        mergeHalves(array, temp, left, right);
    }

    private void mergeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2; // 0
        int rightStart = leftEnd + 1;   // 1
        int size = rightEnd - leftStart + 1;  // 2

        int left = leftStart;   // 0
        int right = rightStart; // 1

        int index = leftStart;  // 0

        // 5 3
        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];   // temp[0] = array[1] = 3
                right++;                      // right = 2
            }
            index++;                          // index = 1
        }

        if (left <= leftEnd) {                // 0 <= 0
            temp[index] = array[left];        // temp[1] = array[0] = 5
            index++;                          // index = 2
            left++;                           // left = 1
        }

        if (right <= rightEnd) {
            temp[index] = array[right];
            index++;
            right++;
        }
        // System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(temp, leftStart, array, leftStart, size);
    }
    */
}

/*
                      0 7

              0 3             4 7

          0 1     2 3     4 5     6 7

        0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7
*/