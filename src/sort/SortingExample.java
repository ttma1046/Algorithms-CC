package sort;

public class SortingExample {

    public static void sort(Comparable[] a) {
        /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a)
    { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }

        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a)
    { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args)
    { // Read strings from standard input, sort them, and print.
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
