package sort;

public class MergeSort {
    public static void mergesort(int[] array) {
        mergesort(array, new int[array.length], 0, array.length - 1);
        for(int i = 0;i < array.length;i++) {
            System.out.println(array[i]);
        }
    }

    public static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }

        int middle = (leftStart + rightEnd) / 2;
        mergesort(array, temp, leftStart, middle);
        mergesort(array, temp, middle + 1, rightEnd);

        margeHalves(array, temp, leftStart, rightEnd);
    }

    public static void margeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        // Walking thru each half of the array
        // and copy the smaller element
        while(left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            }
            else {
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
        mergesort(new int[] {38, 27, 43, 3, 9, 82, 10});
    }
}

/*
                      0 7

              0 3             4 7

          0 1     2 3     4 5     6 7

        0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7
*/