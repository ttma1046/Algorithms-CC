package sort;

public class QuickSort {
    public void sortII(int[] array) {
        quicksortII(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void quicksortII(int[] array, int left , int right) {
        if (left >= right) {
            return;
        }

        int pivot = array[left + (right - left) / 2];

        int index = partitionII(array, left, right, pivot);

        quicksortII(array, left, index - 1);

        quicksortII(array, index, right);
    }

    public int partitionII(int[] array, int left, int right, int pivot) {
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

    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        /*
        int[] result = new int[] {15, 3, 9, 8, 5, 2, 7, 1, 6};
        new QuickSort().sort(result);

        for (int i : result) {
            System.out.print(i);
            System.out.print(",");
        }

        System.out.println();
        */
        int[] result = new int[] {7, 1, 2, 3, 4, 5};

        new QuickSort().sort(result);

        for (int i : result) {
            System.out.print(i);
            System.out.print(",");
        }
    }

    public void sort(int[] a) {
        // StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;

        // if (hi <= lo + M) { Insertion.sort(a, lo, hi); return; }
         
        int j = partition(a, lo, hi); // Partition (see page 291).
        System.out.println("sort(a, " + lo + ", " + (j - 1) + ")");
        sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
        System.out.println("sort(a, " + (j + 1) + ", " + hi + ")");
        sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
    }

    private int partition(int[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1; // left and right scan indices
        int pivot = a[lo]; // partitioning item
        while (true) {
            // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], pivot)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(pivot, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exchange(a, i, j);
        }

        exchange(a, lo, j); // Put pivot = a[j] into position
        for (int k : a) {
            System.out.print(k);
            System.out.print(",");
        }
        System.out.println();
        System.out.println("j: " + j);
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    private boolean less(int a, int b) {
        return a < b;
    }

    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /*
    7 1 2 3 4

    pivot = 7

    i = 4

    j = 4


    4

    1 2 3 7
    */

    /*
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    */
}

