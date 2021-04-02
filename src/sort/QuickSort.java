package sort;

public class QuickSort {
    public static void sort(int[] array) {
        quicksort(array, 0, array.length -1);

        for(int i = 0;i < array.length;i++) {
            System.out.println(array[i]);
        }
    }

    public static void quicksort(int[] array, int left , int right) {
        if (left >= right) {
            return;
        }

        int pivot = array[left + (right - left) / 2];

        int index = partition(array, left, right, pivot);

        quicksort(array, left, index - 1);

        quicksort(array, index, right);
    }

    public static int partition(int[] array, int left, int right, int pivot) {
        System.out.println("pivot:" + pivot);
        System.out.println("left:" + left);
        System.out.println("right:" + right);
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                System.out.println("left index:" + left + ":value:" + array[left]);
                System.out.println("right index:" + right + ":value:" + array[right]);

                System.out.println("before swap:");
                for (int i: array) {
                    System.out.print(i);
                    System.out.print(",");
                }
                System.out.println();


                swap(array, left, right);

                System.out.println("after swap:");
                for (int i: array) {
                    System.out.print(i);
                    System.out.print(",");
                }
                System.out.println();

                left++;
                right--;
            }
        }

        return left;
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        sort(new int[] {15, 3, 9, 8, 5, 2, 7, 1, 6});
    }


    /*


    15 3 9 8 5 2 7 1 6
    L                R
    

    5

    15 3 9 8 5 2 7 1 6
    L              R

    1 3 9 8 5 2 7 15 6
      L         R

    1 3 9 8 5 2 7 15 6
        L     R

    1 3 2 8 5 9 7 15 6
          L R

    1 3 2 5 8 9 7 15 6
          R L
    */
}

