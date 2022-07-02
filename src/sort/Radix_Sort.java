package sort;

class Radix_Sort {
    void radixSort(int[] arr) {
        int m = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, arr.length, exp);
    }

    void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];

        int i;

        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
}