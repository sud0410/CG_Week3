import java.util.Random;
public class SortComparison {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }
    public static void main(String[] args) {
        int[] sizes = {1000, 10000}; // Avoid 1,000,000 for bubble sort
        for (int size : sizes) {
            int[] data = new int[size];
            Random rand = new Random();
            for (int i = 0; i < size; i++) data[i] = rand.nextInt(size * 10);
            int[] data1 = copyArray(data);
            int[] data2 = copyArray(data);
            int[] data3 = copyArray(data);
            long start = System.nanoTime();
            bubbleSort(data1);
            long end = System.nanoTime();
            double bubbleTime = (end - start) / 1e6;
            start = System.nanoTime();
            mergeSort(data2, 0, data2.length - 1);
            end = System.nanoTime();
            double mergeTime = (end - start) / 1e6;
            start = System.nanoTime();
            quickSort(data3, 0, data3.length - 1);
            end = System.nanoTime();
            double quickTime = (end - start) / 1e6;
            System.out.println("Dataset Size: " + size);
            System.out.printf("Bubble Sort Time: %.4f ms%n", bubbleTime);
            System.out.printf("Merge Sort Time: %.4f ms%n", mergeTime);
            System.out.printf("Quick Sort Time: %.4f ms%n", quickTime);
            System.out.println("-----");
        }
    }
}
